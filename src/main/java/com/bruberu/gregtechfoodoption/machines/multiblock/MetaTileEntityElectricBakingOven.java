package com.bruberu.gregtechfoodoption.machines.multiblock;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.bruberu.gregtechfoodoption.block.GTFOMetalCasing;
import com.bruberu.gregtechfoodoption.client.GTFOClientHandler;
import com.bruberu.gregtechfoodoption.recipe.GTFORecipeMaps;
import com.bruberu.gregtechfoodoption.recipe.builder.ElectricBakingOvenRecipeBuilder;
import com.bruberu.gregtechfoodoption.recipe.multiblock.ElectricBakingOvenRecipeMap;
import com.bruberu.gregtechfoodoption.utils.GTFOLog;
import gregicadditions.capabilities.GregicAdditionsCapabilities;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.machines.multi.simple.LargeSimpleRecipeMapMultiblockController;
import gregtech.api.gui.Widget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.api.multiblock.BlockPattern;
import gregtech.api.multiblock.BlockWorldState;
import gregtech.api.multiblock.FactoryBlockPattern;
import gregtech.api.multiblock.PatternMatchContext;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.Recipe;
import gregtech.api.render.ICubeRenderer;
import gregtech.api.util.GTUtility;
import gregtech.api.util.InventoryUtils;
import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Predicate;

import static com.bruberu.gregtechfoodoption.block.GTFOMetaBlocks.GTFO_METAL_CASING;
import static com.bruberu.gregtechfoodoption.client.GTFOClientHandler.BISMUTH_BRONZE_CASING;
import static gregtech.api.gui.widgets.AdvancedTextWidget.withButton;
import static gregtech.api.unification.material.Materials.Steel;

public class MetaTileEntityElectricBakingOven extends LargeSimpleRecipeMapMultiblockController {

    private int temp;
    private int targetTemp;
    private boolean canAchieveTargetTemp;
    private boolean hasEnoughEnergy;
    public int size;

    public MetaTileEntityElectricBakingOven(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTFORecipeMaps.ELECTRIC_BAKING_OVEN_RECIPES, 0, 100, 100, 1, false, true, false);
        this.recipeMapWorkable = new ElectricBakingOvenLogic(this);

        temp = 300;
        targetTemp = 300;
    }

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS,
            MultiblockAbility.INPUT_ENERGY, GregicAdditionsCapabilities.MAINTENANCE_HATCH
    };

    @Override
    protected void updateFormedValid() {
        if (getWorld().isRemote) {
            return;
        }

        super.updateFormedValid();

        if (temp > 300)
            hasEnoughEnergy = drainEnergy();
        else {
            hasEnoughEnergy = true;

        }

        if (getOffsetTimer() % 20 == 0 && targetTemp != temp && !recipeMapWorkable.isActive())
            stepTowardsTargetTemp();
        else if (targetTemp == temp) {
            canAchieveTargetTemp = true;

        }

    }

    private void stepTowardsTargetTemp() {
        canAchieveTargetTemp = true;
        if (targetTemp < temp) {
            setTemp(temp - 5);
            if (temp == 300)
                markDirty();
            return;
        }
        if (temperatureEnergyCost(this.temp + 5) <= this.getEnergyContainer().getInputVoltage() * this.getEnergyContainer().getInputAmperage() && hasEnoughEnergy) {
            setTemp(temp + 5);
            if (temp == 305)
                markDirty();
        } else {
            canAchieveTargetTemp = false;
        }
    }

    private boolean drainEnergy() {
        if (energyContainer.getEnergyStored() >= temperatureEnergyCost(this.temp)) {
            energyContainer.removeEnergy(temperatureEnergyCost(this.temp));
            return true;
        }
        setTemp(temp - 5);
        return false;
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);


    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        if (this.isStructureFormed()) {
            textList.add(new TextComponentTranslation("gregtechfoodoption.multiblock.electric_baking_oven.tooltip.1", temp));
            textList.add(new TextComponentTranslation("gregtechfoodoption.multiblock.electric_baking_oven.tooltip.4", temperatureEnergyCost(temp)));

            ITextComponent buttonText = new TextComponentTranslation("gregtechfoodoption.multiblock.electric_baking_oven.tooltip.3");
            buttonText.appendText(" ");
            buttonText.appendSibling(withButton(new TextComponentString("[-]"), "sub"));
            buttonText.appendText(" ");
            buttonText.appendSibling(withButton(new TextComponentString("[+]"), "add"));
            textList.add(buttonText);

            textList.add(new TextComponentTranslation("gregtechfoodoption.multiblock.electric_baking_oven.tooltip.5", targetTemp));


            if (!canAchieveTargetTemp && hasEnoughEnergy)
                textList.add(new TextComponentTranslation("gregtechfoodoption.multiblock.electric_baking_oven.tooltip.2")
                        .setStyle(new Style().setColor(TextFormatting.RED)));
            if (!hasEnoughEnergy)
                textList.add(new TextComponentTranslation("gregtech.multiblock.not_enough_energy")
                        .setStyle(new Style().setColor(TextFormatting.RED)));

        }
    }


    @Override
    protected void handleDisplayClick(String componentData, Widget.ClickData clickData) {
        super.handleDisplayClick(componentData, clickData);
        int modifier = componentData.equals("add") ? 1 : -1;
        targetTemp += 5 * modifier;
        if (targetTemp < 300)
            targetTemp = 300;
    }

    protected IBlockState getCasingState() {
        return GTFO_METAL_CASING.getState(GTFOMetalCasing.CasingType.BISMUTH_BRONZE_CASING);
    }

    protected IBlockState getFrameState() {
        return MetaBlocks.FRAMES.get(Steel).getDefaultState();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return BISMUTH_BRONZE_CASING;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        GTFOClientHandler.BAKING_OVEN_OVERLAY.render(renderState, translation, pipeline, getFrontFacing(), temp > 300);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(BlockPattern.RelativeDirection.FRONT, BlockPattern.RelativeDirection.UP, BlockPattern.RelativeDirection.RIGHT)
                .aisle("XXXX", "YXXX", "XXXX", "####")
                .aisle("XXXX", "GFFX", "GIOX", "XXXX").setRepeatable(2, 14)
                .aisle("XXXX", "XXXX", "XXXX", "####")
                .where('X', statePredicate(getCasingState()).or(abilityPartPredicate(ALLOWED_ABILITIES)))
                .where('F', statePredicate(getFrameState()))
                .where('G', statePredicate(GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.REINFORCED_GLASS)))
                .where('#', (tile) -> true)
                .where('O', isAirPredicate())
                .where('I', isIndicatorPredicate())
                .where('Y', selfPredicate())
                .build();

    }

    // This function is highly useful for detecting the length of this multiblock.
    public static Predicate<BlockWorldState> isIndicatorPredicate() {
        return (blockWorldState) -> {
            if(isAirPredicate().test(blockWorldState)) {
                blockWorldState.getMatchContext().increment("bakingOvenLength", 1);
                return true;
            }
            else
                return false;
        };
    }


    @Override
    public void invalidateStructure() {
        setTemp(300);
        super.invalidateStructure();
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        this.size = context.getOrDefault("bakingOvenLength", 1) - 2;
        System.out.println(size);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(MetaTileEntityHolder holder) {
        return new MetaTileEntityElectricBakingOven(metaTileEntityId);
    }

    public int temperatureEnergyCost(int temp) {
        return temp <= 300 ? 0 : (int) Math.exp(((double) temp - 100 + (size * 5)) / 100);
    }

    // Is the inverse of the previous function.
    public static int temperatureForEnergy(int EUt) {
        if (EUt <= 8)
            return 300;
        return (int) (Math.log(EUt) * 100) + 100;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setInteger("temp", this.temp);
        data.setInteger("targetTemp", this.targetTemp);
        data.setBoolean("canAchieveTargetTemp", this.canAchieveTargetTemp);
        data.setBoolean("hasEnoughEnergy", this.hasEnoughEnergy);
        return data;
    }

    public void setTemp(int temp) {
        this.temp = temp;
        if (!getWorld().isRemote) {
            writeCustomData(600, buf -> buf.writeInt(temp));
            markDirty();
        }
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == 600) {
            this.temp = buf.readInt();
            scheduleRenderUpdate();
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.temp = data.getInteger("temp");
        this.targetTemp = data.getInteger("targetTemp");
        this.canAchieveTargetTemp = data.getBoolean("canAchieveTargetTemp");
        this.hasEnoughEnergy = data.getBoolean("hasEnoughEnergy");
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeInt(this.temp);
        buf.writeInt(this.targetTemp);
        buf.writeBoolean(this.canAchieveTargetTemp);
        buf.writeBoolean(this.hasEnoughEnergy);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.temp = buf.readInt();
        this.targetTemp = buf.readInt();
        this.canAchieveTargetTemp = buf.readBoolean();
        this.hasEnoughEnergy = buf.readBoolean();
    }


    @Override
    public boolean checkRecipe(Recipe recipe, boolean consumeIfSuccess) {
        return recipe.getIntegerProperty("temperature") == temp;
    }

    @Override
    public int getLightValueForPart(IMultiblockPart sourcePart) {
        return sourcePart == null && temp > 300 ? 15 : 0;
    }

    private class ElectricBakingOvenLogic extends LargeSimpleMultiblockRecipeLogic {
        public ElectricBakingOvenLogic(RecipeMapMultiblockController tileEntity) {
            super(tileEntity, 0, 100, 100, 1);
        }

        protected int lastTemp = 300;

        @Override
        protected void trySearchNewRecipe() {
            Recipe currentRecipe = null;
            IItemHandlerModifiable importInventory = this.getInputInventory();

            if (!(getMetaTileEntity() instanceof MetaTileEntityElectricBakingOven)) {
                return; // This is done like this so that tileTemperature can have an increased scope.
            }
            int tileTemperature = ((MetaTileEntityElectricBakingOven) getMetaTileEntity()).temp;

            boolean dirty = this.checkRecipeInputsDirty(importInventory, tileTemperature);
            if (!dirty && !this.forceRecipeRecheck) {
                if (this.previousRecipe != null && this.previousRecipe.matches(false, importInventory, importFluids)) {
                    currentRecipe = this.previousRecipe;
                }
            } else {
                this.forceRecipeRecheck = false;
                currentRecipe = this.findRecipe(importInventory, temp);
                if (currentRecipe != null) {
                    this.previousRecipe = currentRecipe;
                }
            }

            if (currentRecipe != null && this.setupAndConsumeRecipeInputs(currentRecipe)) {
                this.setupRecipe(currentRecipe);
            }
        }


        protected boolean checkRecipeInputsDirty(IItemHandler inputs, int temperature) {

            boolean shouldRecheckRecipe = false;

            if (this.lastItemInputs == null || this.lastItemInputs.length != inputs.getSlots()) {
                this.lastItemInputs = new ItemStack[inputs.getSlots()];
                Arrays.fill(this.lastItemInputs, ItemStack.EMPTY);
            }

            int i;
            for(i = 0; i < this.lastItemInputs.length; ++i) {
                ItemStack currentStack = inputs.getStackInSlot(i);
                ItemStack lastStack = this.lastItemInputs[i];
                if (!areItemStacksEqual(currentStack, lastStack)) {
                    this.lastItemInputs[i] = currentStack.isEmpty() ? ItemStack.EMPTY : currentStack.copy();
                    shouldRecheckRecipe = true;
                } else if (currentStack.getCount() != lastStack.getCount()) {
                    lastStack.setCount(currentStack.getCount());
                    shouldRecheckRecipe = true;
                }
            }

            if (temperature != lastTemp) {
                lastTemp = temp;
                shouldRecheckRecipe = true;
            }

            return shouldRecheckRecipe;

        }

        protected Recipe findRecipe(IItemHandlerModifiable inputs, int temp) {
            List<ItemStack> itemStackList = new ArrayList<>();
            for(int i = 0; i < inputs.getSlots(); i++)
                itemStackList.add(inputs.getStackInSlot(i));

            assert this.recipeMap instanceof ElectricBakingOvenRecipeMap;
            Recipe recipe = ((ElectricBakingOvenRecipeMap)this.recipeMap).findRecipe(temp, itemStackList);
            if (recipe != null)
                return createRecipe(inputs, temp, recipe);
            return null;
        }

        private Recipe createRecipe(IItemHandlerModifiable inputs, int temp, Recipe matchingRecipe) {
            int duration;
            int minMultiplier = Integer.MAX_VALUE;

            Set<ItemStack> countIngredients = new HashSet<>();
            if (matchingRecipe.getInputs().size() != 0) {
                this.findIngredients(countIngredients, inputs);
                minMultiplier = Math.min(size + 1, this.getMinRatioItem(countIngredients, matchingRecipe, size + 1));
            }

            if (minMultiplier == Integer.MAX_VALUE) {
                GTFOLog.logger.error("Cannot calculate ratio of items for processing array");
                return null;
            }

            duration = matchingRecipe.getDuration();

            List<CountableIngredient> newRecipeInputs = new ArrayList<>();
            List<ItemStack> outputI = new ArrayList<>();
            this.multiplyInputsAndOutputs(newRecipeInputs, outputI, matchingRecipe, minMultiplier);

            // determine if there is enough room in the output to fit all of this
            boolean canFitOutputs = InventoryUtils.simulateItemStackMerge(outputI, this.getOutputInventory());
            // if there isn't, we can't process this recipe.
            if (!canFitOutputs)
                return null;


            ElectricBakingOvenRecipeBuilder newRecipe = ((ElectricBakingOvenRecipeBuilder) recipeMap.recipeBuilder())
                    .inputsIngredients(newRecipeInputs)
                    .outputs(outputI)
                    .setTemp(temp)
                    .duration(duration);

            return newRecipe.build().getResult();
        }

        protected void multiplyInputsAndOutputs(List<CountableIngredient> newRecipeInputs, List<ItemStack> outputI, Recipe r, int multiplier) {
            for (CountableIngredient ci : r.getInputs()) {
                CountableIngredient newIngredient = new CountableIngredient(ci.getIngredient(), ci.getCount() * multiplier);
                newRecipeInputs.add(newIngredient);
            }
            for (ItemStack s : r.getOutputs()) {
                int num = s.getCount() * multiplier;
                ItemStack itemCopy = s.copy();
                itemCopy.setCount(num);
                outputI.add(itemCopy);
            }
        }

        protected boolean checkRecipeInputsDirty(IItemHandler inputs, int temperature, int index) {
            boolean shouldRecheckRecipe = false;
            if (this.lastItemInputsMatrix == null || this.lastItemInputsMatrix.length != this.getInputBuses().size()) {
                this.lastItemInputsMatrix = new ItemStack[this.getInputBuses().size()][];
            }

            if (this.lastItemInputsMatrix[index] == null || this.lastItemInputsMatrix[index].length != inputs.getSlots()) {
                this.lastItemInputsMatrix[index] = new ItemStack[inputs.getSlots()];
                Arrays.fill(this.lastItemInputsMatrix[index], ItemStack.EMPTY);
            }

            int i;
            for (i = 0; i < this.lastItemInputsMatrix[index].length; ++i) {
                ItemStack currentStack = inputs.getStackInSlot(i);
                ItemStack lastStack = this.lastItemInputsMatrix[index][i];
                if (!areItemStacksEqual(currentStack, lastStack)) {
                    this.lastItemInputsMatrix[index][i] = currentStack.isEmpty() ? ItemStack.EMPTY : currentStack.copy();
                    shouldRecheckRecipe = true;
                } else if (currentStack.getCount() != lastStack.getCount()) {
                    lastStack.setCount(currentStack.getCount());
                    shouldRecheckRecipe = true;
                }
            }

            if (temperature != lastTemp) {
                lastTemp = temp;
                shouldRecheckRecipe = true;
            }

            return shouldRecheckRecipe;

        }
        protected void setupRecipe(Recipe recipe) {
            this.progressTime = 1;
            this.maxProgressTime = recipe.getDuration();
            this.itemOutputs = GTUtility.copyStackList(recipe.getOutputs());
            this.fluidOutputs = new ArrayList<>();
            if (this.wasActiveAndNeedsUpdate) {
                this.wasActiveAndNeedsUpdate = false;
            } else {
                this.setActive(true);
            }
        }
    }
}
