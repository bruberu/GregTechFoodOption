package gregtechfoodoption.machines.multiblock;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IControllable;
import gregtech.api.capability.impl.MultiblockRecipeLogic;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.Widget;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.gui.widgets.*;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.*;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.recipes.Recipe;
import gregtech.api.util.RelativeDirection;
import gregtech.api.util.TextComponentUtil;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.core.sound.GTSoundEvents;
import gregtechfoodoption.block.GTFOMetalCasing;
import gregtechfoodoption.client.GTFOClientHandler;
import gregtechfoodoption.client.GTFOGuiTextures;
import gregtechfoodoption.recipe.GTFORecipeMaps;
import gregtechfoodoption.recipe.builder.ElectricBakingOvenRecipeBuilder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static gregtech.api.unification.material.Materials.Steel;
import static gregtechfoodoption.block.GTFOMetaBlocks.GTFO_METAL_CASING;

public class MetaTileEntityElectricBakingOven extends RecipeMapMultiblockController implements IProgressBarMultiblock {

    private int temp;
    private int targetTemp;
    private boolean canAchieveTargetTemp;
    private boolean hasEnoughEnergy;
    public int size;
    public boolean adaptable = true;
    private boolean canRunRecipe = false;

    public MetaTileEntityElectricBakingOven(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId, GTFORecipeMaps.ELECTRIC_BAKING_OVEN_RECIPES);
        this.recipeMapWorkable = new ElectricBakingOvenLogic(this);

        temp = 300;
        targetTemp = 300;
    }

    private static final MultiblockAbility<?>[] ALLOWED_ABILITIES = {
            MultiblockAbility.IMPORT_ITEMS, MultiblockAbility.EXPORT_ITEMS,
            MultiblockAbility.INPUT_ENERGY, MultiblockAbility.MAINTENANCE_HATCH
    };

    @Override
    protected void updateFormedValid() {
        if (getWorld().isRemote) {
            return;
        }

        super.updateFormedValid();

        if (temp > 300) {
            hasEnoughEnergy = drainEnergy();
            if (adaptable && !canRunRecipe)
                targetTemp = 300;
        } else
            hasEnoughEnergy = true;

        if (getOffsetTimer() % 20 == 0 && !recipeMapWorkable.isActive())
            stepTowardsTargetTemp();
        else if (targetTemp == temp) {
            canAchieveTargetTemp = true;
        }

        if (!isActive())
            canRunRecipe = false;
    }

    private void stepTowardsTargetTemp() {
        canAchieveTargetTemp = true;
        if (temp > 300 && (!this.recipeMapWorkable.isWorking() || targetTemp < temp)) {
            setTemp(temp - 5);
            if (temp == 300)
                markDirty();
            return;
        }
        if (temp == targetTemp) return;
        if (temperatureEnergyCost(this.temp + 5, this.size) <= this.getEnergyContainer().getInputVoltage() * this.getEnergyContainer().getInputAmperage() && hasEnoughEnergy) {
            setTemp(temp + 5);
            if (temp == 305)
                markDirty();
        } else {
            canAchieveTargetTemp = false;
        }
    }

    private boolean drainEnergy() {
        if (energyContainer.getEnergyStored() >= temperatureEnergyCost(this.temp, this.size)) {
            energyContainer.removeEnergy(temperatureEnergyCost(this.temp, this.size));
            return true;
        }
        setTemp(temp - 5);
        return false;
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);

        tooltip.add(I18n.format("gregtechfoodoption.machine.electric_baking_oven.tooltip.1"));
        tooltip.add(I18n.format("gregtechfoodoption.machine.electric_baking_oven.tooltip.2"));
        tooltip.add(I18n.format("gregtechfoodoption.machine.electric_baking_oven.tooltip.3"));
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        if (!this.isStructureFormed()) {
            ITextComponent tooltip = new TextComponentTranslation("gregtech.multiblock.invalid_structure.tooltip");
            tooltip.setStyle((new Style()).setColor(TextFormatting.GRAY));
            textList.add((new TextComponentTranslation("gregtech.multiblock.invalid_structure")).setStyle((new Style()).setColor(TextFormatting.RED).setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, tooltip))));
        } else {
            if (!this.recipeMapWorkable.isWorkingEnabled()) {
                textList.add(TextComponentUtil.translationWithColor(TextFormatting.GRAY, "gregtech.multiblock.work_paused"));
            } else if (this.recipeMapWorkable.isActive()) {
                textList.add(TextComponentUtil.translationWithColor(TextFormatting.GRAY, "gregtech.multiblock.running"));
                int currentProgress = (int) (this.recipeMapWorkable.getProgressPercent() * 100.0D);
                if (this.recipeMapWorkable.getParallelLimit() != 1) {
                    textList.add(new TextComponentTranslation("gregtech.multiblock.parallel", this.recipeMapWorkable.getParallelLimit()));
                }

                textList.add(new TextComponentTranslation("gregtech.multiblock.progress", currentProgress));
            } else {
                textList.add(TextComponentUtil.translationWithColor(TextFormatting.GRAY, "gregtech.multiblock.idling"));
            }

            if (this.recipeMapWorkable.isHasNotEnoughEnergy()) {
                textList.add((new TextComponentTranslation("gregtech.multiblock.not_enough_energy")).setStyle((new Style()).setColor(TextFormatting.RED)));
            }

            textList.add(new TextComponentTranslation("gregtechfoodoption.multiblock.electric_baking_oven.tooltip.4", temperatureEnergyCost(temp, size)));

            textList.add(new TextComponentTranslation("gregtechfoodoption.multiblock.electric_baking_oven.tooltip.5", targetTemp));


            if (!canAchieveTargetTemp && hasEnoughEnergy)
                textList.add(new TextComponentTranslation("gregtechfoodoption.multiblock.electric_baking_oven.tooltip.2")
                        .setStyle(new Style().setColor(TextFormatting.RED)));
            if (!hasEnoughEnergy)
                textList.add(new TextComponentTranslation("gregtech.multiblock.not_enough_energy")
                        .setStyle(new Style().setColor(TextFormatting.RED)));

        }
    }

    public void decrementTemperatureTarget(Widget.ClickData data) {
        targetTemp -= data.isShiftClick ? 25 : 5;
    }

    public void incrementTemperatureTarget(Widget.ClickData data) {
        targetTemp += data.isShiftClick ? 25 : 5;
    }

    protected IBlockState getCasingState() {
        return GTFO_METAL_CASING.getState(GTFOMetalCasing.CasingType.BISMUTH_BRONZE_CASING);
    }

    protected IBlockState getFrameState() {
        return MetaBlocks.FRAMES.get(Steel).getBlock(Steel);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return GTFOClientHandler.BISMUTH_BRONZE_CASING;
    }

    @Override
    protected ICubeRenderer getFrontOverlay() {
        return GTFOClientHandler.BAKING_OVEN_OVERLAY;
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start(RelativeDirection.FRONT, RelativeDirection.UP, RelativeDirection.RIGHT)
                .aisle("XXXX", "YXXX", "XXXX", "####")
                .aisle("XXXX", "GFFX", "GIOX", "XXXX").setRepeatable(2, 14)
                .aisle("XXXX", "XXXX", "XXXX", "####")
                .where('X', states(getCasingState()).setMinGlobalLimited(10).or(autoAbilities()))
                .where('F', states(getFrameState()))
                .where('G', states(MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.TEMPERED_GLASS)))
                .where('#', any())
                .where('O', air())
                .where('I', isIndicatorPredicate())
                .where('Y', selfPredicate())
                .build();

    }

    // This function is highly useful for detecting the length of this multiblock.
    public static TraceabilityPredicate isIndicatorPredicate() {
        return new TraceabilityPredicate((blockWorldState) -> {
            if (air().test(blockWorldState)) {
                blockWorldState.getMatchContext().increment("bakingOvenLength", 1);
                return true;
            } else
                return false;
        });
    }

    @Override
    public void invalidateStructure() {
        setTemp(300);
        super.invalidateStructure();
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        this.size = context.getOrDefault("bakingOvenLength", 1) - 1;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityElectricBakingOven(metaTileEntityId);
    }

    public static int temperatureEnergyCost(int temp, int multiSize) {
        return temp <= 300 ? 0 : (int) Math.exp(((double) temp - 100 + (multiSize * 5)) / 100);
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
        data.setInteger("size", this.size);
        data.setInteger("targetTemp", this.targetTemp);
        data.setBoolean("canAchieveTargetTemp", this.canAchieveTargetTemp);
        data.setBoolean("hasEnoughEnergy", this.hasEnoughEnergy);
        data.setBoolean("adaptable", this.adaptable);
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
        this.size = data.getInteger("size");
        this.canAchieveTargetTemp = data.getBoolean("canAchieveTargetTemp");
        this.hasEnoughEnergy = data.getBoolean("hasEnoughEnergy");
        this.adaptable = data.getBoolean("adaptable");
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeInt(this.temp);
        buf.writeInt(this.targetTemp);
        buf.writeInt(this.size);
        buf.writeBoolean(this.canAchieveTargetTemp);
        buf.writeBoolean(this.hasEnoughEnergy);
        buf.writeBoolean(this.adaptable);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.temp = buf.readInt();
        this.targetTemp = buf.readInt();
        this.size = buf.readInt();
        this.canAchieveTargetTemp = buf.readBoolean();
        this.hasEnoughEnergy = buf.readBoolean();
        this.adaptable = buf.readBoolean();
    }


    @Override
    public boolean checkRecipe(Recipe recipe, boolean consumeIfSuccess) {
        int recipeTemp = recipe.getProperty(ElectricBakingOvenRecipeBuilder.TemperatureProperty.getInstance(), 300);
        if (adaptable) {
            targetTemp = recipeTemp;
            canRunRecipe = true;
        }
        return recipeTemp == temp;
    }

    @Override
    public int getActualLightValue() {
        return temp > 305 ? 15 : 0;
    }

    @Override
    public double getFillPercentage(int index) {
        return Math.min(1, (temp - 300.0) / 400.0);
    }

    @Override
    public TextureArea getProgressBarTexture(int index) {
        return GTFOGuiTextures.PROGRESS_BAR_SINGLE_HEAT;
    }

    @Override
    public void addBarHoverText(List<ITextComponent> hoverList, int index) {
        hoverList.add(new TextComponentTranslation("gregtechfoodoption.multiblock.electric_baking_oven.tooltip.1", temp));
    }

    private class ElectricBakingOvenLogic extends MultiblockRecipeLogic {
        public ElectricBakingOvenLogic(RecipeMapMultiblockController tileEntity) {
            super(tileEntity);
        }

        @Override
        public int getParallelLimit() {
            return ((MetaTileEntityElectricBakingOven) this.getMetaTileEntity()).size;
        }

        @Override
        protected int[] calculateOverclock(Recipe recipe) {
            return new int[]{0, recipe.getDuration()};
        }

        @Override
        public int getRecipeEUt() {
            return temperatureEnergyCost(temp, size);
        }

        @Override
        public boolean isWorking() {
            return temp > 300 && !this.hasNotEnoughEnergy && this.workingEnabled;
        }

        @Override
        public @NotNull ParallelLogicType getParallelLogicType() {
            return ParallelLogicType.MULTIPLY;
        }

        @Override
        protected void updateRecipeProgress() {
            super.updateRecipeProgress();
            canRunRecipe = true;
        }
    }

    @Override
    public SoundEvent getBreakdownSound() {
        return GTSoundEvents.BREAKDOWN_ELECTRICAL;
    }

    public boolean canCreateSound() {
        return temp > 300 && this.recipeMapWorkable.isWorkingEnabled();
    }

    @Override
    protected @NotNull TextureArea getLogo() {
        return GTFOGuiTextures.GTFO_LOGO_WORKING;
    }

    @Override
    protected @NotNull TextureArea getWarningLogo() {
        return GTFOGuiTextures.GTFO_LOGO_WARNING;
    }

    @Override
    protected @NotNull TextureArea getErrorLogo() {
        return GTFOGuiTextures.GTFO_LOGO_ERROR;
    }

    public int getAdaptableMode() {
        return adaptable ? 0 : 1;
    }

    public void setAdaptableMode(int mode) {
        adaptable = mode == 0;
    }

    @Override
    protected @NotNull Widget getFlexButton(int x, int y, int width, int height) {
        return (new ImageCycleButtonWidget(x, y, width, height, GTFOGuiTextures.BUTTON_ADAPTABILITY, 2, this::getAdaptableMode, this::setAdaptableMode)).setTooltipHoverString((mode) -> {
            String tooltip = "";
            switch (mode) {
                case 0:
                    tooltip = "gregtechfoodoption.multiblock.electric_baking_oven.adaptable_on";
                    break;
                case 1:
                    tooltip = "gregtechfoodoption.multiblock.electric_baking_oven.adaptable_off";
                    break;
            }

            return tooltip;
        });
    }

    protected @NotNull Widget getTemperatureButton(int x, int y, int width, int height) {
        WidgetGroup group = new WidgetGroup(x, y, width, height);
        group.addWidget((new ClickButtonWidget(0, 0, 9, 18, "", this::decrementTemperatureTarget)).setButtonTexture(GuiTextures.BUTTON_THROTTLE_MINUS).setTooltipText("gregtechfoodoption.multiblock.electric_baking_oven.temp_decrement"));
        group.addWidget((new ClickButtonWidget(9, 0, 9, 18, "", this::incrementTemperatureTarget)).setButtonTexture(GuiTextures.BUTTON_THROTTLE_PLUS).setTooltipText("gregtechfoodoption.multiblock.electric_baking_oven.temp_increment"));
        return group;
    }

    protected ModularUI.Builder createUITemplate(EntityPlayer entityPlayer) {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 198, 208);

        // Display
        builder.image(4, 4, 190, 109, GuiTextures.DISPLAY);
        builder.label(9, 9, getMetaFullName(), 0xFFFFFF);
        builder.widget(new AdvancedTextWidget(9, 20, this::addDisplayText, 0xFFFFFF)
                .setMaxWidthLimit(181)
                .setClickHandler(this::handleDisplayClick));

        ProgressWidget progressBar = (new ProgressWidget(() ->
                this.getFillPercentage(0), 4, 115, 190, 7,
                this.getProgressBarTexture(0), ProgressWidget.MoveType.HORIZONTAL))
                .setHoverTextConsumer((list) -> this.addBarHoverText(list, 0));
        builder.widget(progressBar);

        // Power Button
        // todo in the future, refactor so that this class is instanceof IControllable.
        IControllable controllable = getCapability(GregtechTileCapabilities.CAPABILITY_CONTROLLABLE, null);
        if (controllable != null) {
            builder.widget(new ImageCycleButtonWidget(173, 183, 18, 18, GuiTextures.BUTTON_POWER,
                    controllable::isWorkingEnabled, controllable::setWorkingEnabled));
            builder.widget(new ImageWidget(173, 201, 18, 6, GuiTextures.BUTTON_POWER_DETAIL));
        }

        // Temperature control button
        builder.widget(getTemperatureButton(173, 143, 18, 18));

        // Flex Button
        builder.widget(getFlexButton(173, 124, 18, 18));

        // Lack of voiding button
        builder.widget(new ImageWidget(173, 161, 18, 18, GuiTextures.BUTTON_VOID_NONE)
                .setTooltip("gregtech.gui.multiblock_voiding_not_supported"));

        // Logo / Indicator Widget
        builder.widget(new IndicatorImageWidget(174, 93, 17, 17, getLogo())
                .setWarningStatus(getWarningLogo(), this::addWarningText)
                .setErrorStatus(getErrorLogo(), this::addErrorText));

        builder.bindPlayerInventory(entityPlayer.inventory, 125);
        return builder;
    }


    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        this.getFrontOverlay().renderOrientedState(renderState, translation, pipeline, this.getFrontFacing(), temp > 300, this.recipeMapWorkable.isWorkingEnabled());
    }
}
