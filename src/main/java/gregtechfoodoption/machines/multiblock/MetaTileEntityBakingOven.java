package gregtechfoodoption.machines.multiblock;

import gregtechfoodoption.client.GTFOClientHandler;
import gregtechfoodoption.client.GTFOGuiTextures;
import gregtechfoodoption.recipe.GTFORecipeMaps;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtechfoodoption.block.GTFOBlockCasing;
import gregtechfoodoption.block.GTFOMetaBlocks;
import gregtech.api.capability.impl.ItemHandlerProxy;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.RecipeMapPrimitiveMultiblockController;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static gregtech.api.unification.material.Materials.Iron;
import static gregtech.api.util.InventoryUtils.simulateItemStackMerge;

public class MetaTileEntityBakingOven extends RecipeMapPrimitiveMultiblockController {

    public MetaTileEntityBakingOven(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTFORecipeMaps.BAKING_OVEN_RECIPES);
    }

    @Override
    public int getLightValueForPart(IMultiblockPart sourcePart) {
        return sourcePart == null && recipeMapWorkable.isActive() ? 15 : 0;
    }

    protected IBlockState getCasingState() {
        return GTFOMetaBlocks.GTFO_CASING.getState(GTFOBlockCasing.CasingType.ADOBE_BRICKS);
    }

    protected IBlockState getFrameState() {
        return MetaBlocks.FRAMES.get(Iron).getDefaultState();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GTFOClientHandler.ADOBE_BRICKS;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        this.getFrontOverlay().renderOrientedState(renderState, translation, pipeline, this.getFrontFacing(), this.recipeMapWorkable.isActive(), this.recipeMapWorkable.isWorkingEnabled());
    }

    @Override
    protected void initializeInventory() {
        super.initializeInventory();
        ItemStackHandler emptyHandler = new ItemStackHandler(0);
        this.itemInventory = new ItemHandlerProxy(emptyHandler, emptyHandler);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXXX", "XXXX", "XXXX", "#XX#")
                .aisle("XXXX", "XFFX", "X##X", "#XX#")
                .aisle("XXXX", "YFFX", "X##X", "#XX#")
                .where('X', states(getCasingState()))
                .where('F', states(getFrameState()))
                .where('#', air())
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
                        .setBackgroundTexture(GuiTextures.SLOT, GTFOGuiTextures.FOOD_OVERLAY))
                .widget(new SlotWidget(importItems, 1, 53, 33, true, true)
                        .setBackgroundTexture(GuiTextures.SLOT, GuiTextures.FURNACE_OVERLAY_1))
                .progressBar(recipeMapWorkable::getProgressPercent, 78, 24, 20, 15, GuiTextures.PROGRESS_BAR_MACERATE, ProgressWidget.MoveType.HORIZONTAL)
                .widget(new SlotWidget(exportItems, 0, 105, 24, true, false)
                        .setBackgroundTexture(GuiTextures.SLOT, GTFOGuiTextures.FOOD_OVERLAY))
                .bindPlayerInventory(entityPlayer.inventory)
                .build(getHolder(), entityPlayer);
    }

    public static List<ItemStack> getDisplayFuelsForRecipe(int recipeFuelTicks) {
        ArrayList<ItemStack> resultStacks = new ArrayList<>();

        List<ItemStack> displayedFuels = Arrays.asList(new ItemStack(Items.STICK),
                OreDictUnifier.get(OrePrefix.plank, Materials.Wood),
                OreDictUnifier.get(OrePrefix.gem, Materials.Coal),
                OreDictUnifier.get(OrePrefix.gem, Materials.Coke),
                OreDictUnifier.get(OrePrefix.block, Materials.Coal),
                OreDictUnifier.get(OrePrefix.block, Materials.Coke));

        for (ItemStack fuelStack : displayedFuels) {
            ItemStack modifiedFuelStack = fuelStack.copy();
            int fuelBurnTime = TileEntityFurnace.getItemBurnTime(fuelStack);
            if (fuelBurnTime != 0) {
                modifiedFuelStack.setCount((int) Math.ceil((double) recipeFuelTicks / fuelBurnTime));
                resultStacks.add(modifiedFuelStack);
            }
        }

        return resultStacks;
    }
}
