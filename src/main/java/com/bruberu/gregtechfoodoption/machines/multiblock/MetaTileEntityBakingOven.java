package com.bruberu.gregtechfoodoption.machines.multiblock;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.texture.TextureUtils;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;
import com.bruberu.gregtechfoodoption.recipe.GTFORecipeMaps;
import com.bruberu.gregtechfoodoption.recipe.multiblock.BakingOvenRecipe;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import gregtech.api.GTValues;
import gregtech.api.capability.impl.ItemHandlerProxy;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.render.Textures;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.*;

import static gregtech.api.unification.material.Materials.Iron;
import static gregtech.api.util.InventoryUtils.simulateItemStackMerge;

public class MetaTileEntityBakingOven extends MultiblockControllerBase {
    private int maxProgressDuration;
    private int currentProgress;
    private NonNullList<ItemStack> outputsList;
    private float fuelUnitsLeft;
    private boolean isActive;
    private boolean wasActiveAndNeedUpdate;

    private ItemStack lastInputStack = ItemStack.EMPTY;
    private BakingOvenRecipe previousRecipe;

    public MetaTileEntityBakingOven(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
    }

    @Override
    protected void updateFormedValid() {
        if (maxProgressDuration == 0) {
            if (tryPickNewRecipe()) {
                if (wasActiveAndNeedUpdate) {
                    this.wasActiveAndNeedUpdate = false;
                } else setActive(true);
            }
        } else if (++currentProgress >= maxProgressDuration) {
            finishCurrentRecipe();
            this.wasActiveAndNeedUpdate = true;
            return;
        }
        if (wasActiveAndNeedUpdate) {
            this.wasActiveAndNeedUpdate = false;
            setActive(false);
        }
    }

    private void finishCurrentRecipe() {
        this.maxProgressDuration = 0;
        this.currentProgress = 0;
        MetaTileEntity.addItemsToItemHandler(exportItems, false, outputsList);
        this.outputsList = null;
        markDirty();
    }

    private BakingOvenRecipe getOrRefreshRecipe(ItemStack inputStack) {
        BakingOvenRecipe currentRecipe = null;
        if (previousRecipe != null &&
                previousRecipe.getInput().getIngredient().apply(inputStack)) {
            currentRecipe = previousRecipe;
        } else if (!areItemStacksEqual(inputStack, lastInputStack)) {
            this.lastInputStack = inputStack.isEmpty() ? ItemStack.EMPTY : inputStack.copy();
            currentRecipe = GTFORecipeMaps.BAKING_OVEN_RECIPES.stream()
                    .filter(it -> it.getInput().getIngredient().test(inputStack))
                    .findFirst().orElse(null);
            if (currentRecipe != null) {
                this.previousRecipe = currentRecipe;
            }
        }
        return currentRecipe;
    }

    private static boolean areItemStacksEqual(ItemStack stackA, ItemStack stackB) {
        return (stackA.isEmpty() && stackB.isEmpty()) ||
                (ItemStack.areItemsEqual(stackA, stackB) &&
                        ItemStack.areItemStackTagsEqual(stackA, stackB));
    }

    private boolean setupRecipe(ItemStack inputStack, int fuelAmount, BakingOvenRecipe recipe) {
        List<ItemStack> outputs = new ArrayList<>();
        outputs.add(recipe.getOutput());
        return inputStack.getCount() >= recipe.getInput().getCount() && fuelAmount >= recipe.getFuelAmount() &&
                simulateItemStackMerge(outputs, exportItems);
    }

    private boolean tryPickNewRecipe() {
        ItemStack inputStack = importItems.getStackInSlot(0);
        ItemStack fuelStack = importItems.getStackInSlot(1);
        if (inputStack.isEmpty() || (fuelStack.isEmpty() && fuelUnitsLeft == 0)) {
            return false;
        }
        float fuelUnitsPerItem = getFuelUnits(fuelStack);
        float totalFuelUnits = fuelUnitsLeft + fuelUnitsPerItem * fuelStack.getCount();
        BakingOvenRecipe currentRecipe = getOrRefreshRecipe(inputStack);
        if (currentRecipe != null && setupRecipe(inputStack, (int) totalFuelUnits, currentRecipe)) {
            inputStack.shrink(currentRecipe.getInput().getCount());
            float fuelUnitsToConsume = currentRecipe.getFuelAmount();
            float remainderConsumed = Math.min(fuelUnitsToConsume, fuelUnitsLeft);
            fuelUnitsToConsume -= remainderConsumed;

            int fuelItemsToConsume = (int) Math.ceil(fuelUnitsToConsume / (fuelUnitsPerItem * 1.0));
            float remainderAdded = fuelItemsToConsume * fuelUnitsPerItem - fuelUnitsToConsume;

            this.fuelUnitsLeft += (remainderAdded - remainderConsumed);
            fuelStack.shrink(fuelItemsToConsume);
            this.maxProgressDuration = currentRecipe.getDuration();
            this.currentProgress = 0;
            NonNullList<ItemStack> outputs = NonNullList.create();
            outputs.add(currentRecipe.getOutput().copy());
            this.outputsList = outputs;
            markDirty();
            return true;
        }
        return false;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setBoolean("Active", isActive);
        data.setBoolean("WasActive", wasActiveAndNeedUpdate);
        data.setFloat("FuelUnitsLeft", fuelUnitsLeft);
        data.setInteger("MaxProgress", maxProgressDuration);
        if (maxProgressDuration > 0) {
            data.setInteger("Progress", currentProgress);
            NBTTagList itemOutputs = new NBTTagList();
            for (ItemStack itemStack : outputsList) {
                itemOutputs.appendTag(itemStack.writeToNBT(new NBTTagCompound()));
            }
            data.setTag("Outputs", itemOutputs);
        }
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.isActive = data.getBoolean("Active");
        this.wasActiveAndNeedUpdate = data.getBoolean("WasActive");
        this.fuelUnitsLeft = data.getFloat("FuelUnitsLeft");
        this.maxProgressDuration = data.getInteger("MaxProgress");
        if (maxProgressDuration > 0) {
            this.currentProgress = data.getInteger("Progress");
            NBTTagList itemOutputs = data.getTagList("Outputs", Constants.NBT.TAG_COMPOUND);
            this.outputsList = NonNullList.create();
            for (int i = 0; i < itemOutputs.tagCount(); i++) {
                this.outputsList.add(new ItemStack(itemOutputs.getCompoundTagAt(i)));
            }
        }
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeBoolean(isActive);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.isActive = buf.readBoolean();
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == 100) {
            this.isActive = buf.readBoolean();
            getWorld().checkLight(getPos());
            getHolder().scheduleChunkForRenderUpdate();
        }
    }

    public void setActive(boolean active) {
        this.isActive = active;
        if (!getWorld().isRemote) {
            writeCustomData(100, b -> b.writeBoolean(isActive));
            getWorld().checkLight(getPos());
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public double getProgressScaled() {
        return maxProgressDuration == 0 ? 0.0 : (currentProgress / (maxProgressDuration * 1.0));
    }

    @Override
    public int getLightValueForPart(IMultiblockPart sourcePart) {
        return sourcePart == null && isActive ? 15 : 0;
    }

    public static float getFuelUnits(ItemStack fuelType) {
        if (fuelType.isEmpty()) {
            return 0;
        }

        //Check the furnace values
        return TileEntityFurnace.getItemBurnTime(fuelType);
    }

    protected IBlockState getCasingState() {
        return MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.PRIMITIVE_BRICKS);
    }

    protected IBlockState getFrameState() {
        return MetaBlocks.FRAMES.get(Iron).getDefaultState();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.PRIMITIVE_BRICKS;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        Textures.PRIMITIVE_BLAST_FURNACE_OVERLAY.render(renderState, translation, pipeline, getFrontFacing(), isActive());
    }

    @Override
    protected void initializeInventory() {
        super.initializeInventory();
        ItemStackHandler emptyHandler = new ItemStackHandler(0);
        this.itemInventory = new ItemHandlerProxy(emptyHandler, emptyHandler);
    }

    @Override
    protected IItemHandlerModifiable createImportItemHandler() {
        return new ItemStackHandler(2) {
            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (slot == 1 && getFuelUnits(stack) == 0)
                    return stack;
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Override
    protected IItemHandlerModifiable createExportItemHandler() {
        return new ItemStackHandler(1);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXXX", "XXXX", "XXXX", "#XX#")
                .aisle("XXXX", "XFFX", "X##X", "#XX#")
                .aisle("XXXX", "YFFX", "X##X", "#XX#")
                .where('X', statePredicate(getCasingState()))
                .where('F', statePredicate(getFrameState()))
                .where('#', isAirPredicate())
                .where('Y', selfPredicate())
                .build();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityBakingOven(metaTileEntityId);
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        return ModularUI.builder(GuiTextures.BACKGROUND, 176, 166)
                .widget(new SlotWidget(importItems, 0, 53, 15, true, true)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.INGOT_OVERLAY))
                .widget(new SlotWidget(importItems, 1, 53, 33, true, true)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.FURNACE_OVERLAY))
                .progressBar(this::getProgressScaled, 87, 24, 20, 15, GuiTextures.PROGRESS_BAR_MACERATE, ProgressWidget.MoveType.HORIZONTAL)
                .widget(new SlotWidget(exportItems, 0, 105, 24, true, false)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.INGOT_OVERLAY))
                .bindPlayerInventory(entityPlayer.inventory, GuiTextures.SLOT)
                .build(getHolder(), entityPlayer);
    }

    public static List<ItemStack> getDisplayFuelsForRecipe(int recipeFuelTicks) {
        ArrayList<ItemStack> resultStacks = new ArrayList<>();

        List<ItemStack> displayedFuels = Arrays.asList(new ItemStack(Items.STICK),
                OreDictUnifier.get(OrePrefix.plank, Materials.Wood),
                OreDictUnifier.get(OrePrefix.gem, Materials.Coal),
                OreDictUnifier.get(OrePrefix.gem, Materials.Lignite),
                OreDictUnifier.get(OrePrefix.gem, Materials.Coke),
                OreDictUnifier.get(OrePrefix.block, Materials.Coal),
                OreDictUnifier.get(OrePrefix.block, Materials.Lignite),
                OreDictUnifier.get(OrePrefix.block, Materials.Coke));

        for(ItemStack fuelStack : displayedFuels) {
            ItemStack modifiedFuelStack = fuelStack.copy();
            int fuelBurnTime = TileEntityFurnace.getItemBurnTime(fuelStack);
            if(fuelBurnTime != 0) {
                modifiedFuelStack.setCount((int)Math.ceil((double) recipeFuelTicks / fuelBurnTime));
                resultStacks.add(modifiedFuelStack);
            }
        }

        return resultStacks;
    }
}
