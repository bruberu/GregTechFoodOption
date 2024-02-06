package gregtechfoodoption.machines.multiblock.kitchen;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.google.common.collect.Lists;
import gregtech.api.GTValues;
import gregtech.api.capability.GregtechDataCodes;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.Widget;
import gregtech.api.gui.resources.TextureArea;
import gregtech.api.gui.widgets.ClickButtonWidget;
import gregtech.api.gui.widgets.ImageWidget;
import gregtech.api.gui.widgets.WidgetGroup;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntityHolder;
import gregtech.api.metatileentity.SimpleGeneratorMetaTileEntity;
import gregtech.api.metatileentity.WorkableTieredMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockDisplayText;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.pattern.TraceabilityPredicate;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.block.GTFOBlockCasing;
import gregtechfoodoption.block.GTFOMetaBlocks;
import gregtechfoodoption.client.GTFOGuiTextures;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MetaTileEntityKitchen extends MultiblockWithDisplayBase {
    public static final int MIN_RADIUS = 2;
    private final KitchenLogic kitchenLogic;
    protected IItemHandlerModifiable inputInventory;
    protected IItemHandlerModifiable outputInventory;
    protected IMultipleTankHandler inputFluidInventory;
    protected IMultipleTankHandler outputFluidInventory;
    protected IEnergyContainer energyContainer;
    protected IItemHandlerModifiable recipeHolder = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            kitchenLogic.reset();
        }
    };
    protected IItemHandlerModifiable allCircuits = new ItemStackHandler(32) {
        @NotNull
        @Override
        public ItemStack getStackInSlot(int slot) {
            return IntCircuitIngredient.getIntegratedCircuit(slot);
        }
    };

    protected int orderSize = 64;

    private int sDist = 0;
    private int bDist = 0;

    public MetaTileEntityKitchen(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        kitchenLogic = new KitchenLogic(this);
        resetTileAbilities();
    }

    protected void initializeAbilities() {
        List<IItemHandlerModifiable> inputs = new ArrayList<>(getAbilities(MultiblockAbility.IMPORT_ITEMS));
        inputs.add(allCircuits);
        this.inputInventory = new ItemHandlerList(inputs);
        this.inputFluidInventory = new FluidTankList(true, getAbilities(MultiblockAbility.IMPORT_FLUIDS));
        this.outputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.EXPORT_ITEMS));
        this.outputFluidInventory = new FluidTankList(true, getAbilities(MultiblockAbility.EXPORT_FLUIDS));
        this.energyContainer = new EnergyContainerList(getAbilities(MultiblockAbility.INPUT_ENERGY));
    }

    private void resetTileAbilities() {
        this.inputInventory = new ItemStackHandler(0);
        this.inputFluidInventory = new FluidTankList(true);
        this.outputInventory = new ItemStackHandler(0);
        this.outputFluidInventory = new FluidTankList(true);
        this.energyContainer = new EnergyContainerList(Lists.newArrayList());
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        initializeAbilities();
    }

    @Override
    protected void updateFormedValid() {

    }

    @Override
    public void clearMachineInventory(NonNullList<ItemStack> itemBuffer) {
        super.clearMachineInventory(itemBuffer);
        itemBuffer.add(recipeHolder.getStackInSlot(0));
    }

    public boolean drainEnergy(boolean simulate) {
        long energyToDrain = GTValues.VA[getEnergyTier()] / 2;
        long resultEnergy = energyContainer.getEnergyStored() - energyToDrain;
        if (resultEnergy >= 0L && resultEnergy <= energyContainer.getEnergyCapacity()) {
            if (!simulate)
                energyContainer.changeEnergy(-energyToDrain);
            return true;
        }
        return false;
    }

    public int getEnergyTier() {
        if (energyContainer == null) return GTValues.LV;
        return Math.max(GTValues.LV, GTUtility.getFloorTierByVoltage(energyContainer.getInputVoltage()));
    }

    @NotNull
    @Override
    protected BlockPattern createStructurePattern() {
        if (getWorld() != null) updateStructureDimensions();
        // these can sometimes get set to 0 when loading the game, breaking JEI
        if (sDist < MIN_RADIUS) sDist = MIN_RADIUS;
        if (bDist < MIN_RADIUS * 2) bDist = MIN_RADIUS * 2;

        StringBuilder borderBuilder = new StringBuilder();     // BBBBB
        StringBuilder centerBuilder = new StringBuilder();     // BFFFB
        StringBuilder frontBuilder = new StringBuilder();     // BBSBB
        StringBuilder topBuilder = new StringBuilder(); //  III
        StringBuilder emptyBuilder = new StringBuilder();


        for (int i = 0; i < sDist; i++) {
            if (i == 0) {
                centerBuilder.append("B");
                topBuilder.append(" ");
            } else {
                centerBuilder.append("F");
                topBuilder.append("I");
            }
            emptyBuilder.append("  ");
            frontBuilder.append("B");
            borderBuilder.append("B");
        }
        emptyBuilder.append(" ");
        borderBuilder.append(borderBuilder);
        borderBuilder.append("B");
        frontBuilder.append(frontBuilder);
        frontBuilder.insert(sDist, "S");
        centerBuilder.append(new StringBuilder(centerBuilder).reverse());
        centerBuilder.insert(sDist, "F");
        topBuilder.append(new StringBuilder(topBuilder).reverse());
        topBuilder.insert(sDist, "I");


        TraceabilityPredicate basePredicate = autoAbilities().or(abilities(MultiblockAbility.INPUT_ENERGY)
                .setMinGlobalLimited(1).setMaxGlobalLimited(3));

        return FactoryBlockPattern.start()
                .aisle(borderBuilder.toString(), emptyBuilder.toString())
                .aisle(centerBuilder.toString(), topBuilder.toString()).setRepeatable(MIN_RADIUS * 2, bDist)
                .aisle(frontBuilder.toString(), emptyBuilder.toString())
                .where('S', selfPredicate())
                .where('B', basePredicate.or(states(getCasingState())))
                .where('F', states(getFloorState(), getFloorState2()))
                .where('I', innerPredicate())
                .where(' ', any())
                .build();
    }

    public TraceabilityPredicate autoAbilities() {
        TraceabilityPredicate predicate = super.autoAbilities(true, false);

        predicate = predicate.or(abilities(MultiblockAbility.INPUT_ENERGY).setMinGlobalLimited(1)
                .setMaxGlobalLimited(2)
                .setPreviewCount(1));

        predicate = predicate.or(abilities(MultiblockAbility.IMPORT_ITEMS).setPreviewCount(1));
        predicate = predicate.or(abilities(MultiblockAbility.EXPORT_ITEMS).setPreviewCount(1));
        predicate = predicate.or(abilities(MultiblockAbility.IMPORT_FLUIDS).setPreviewCount(1));
        predicate = predicate.or(abilities(MultiblockAbility.EXPORT_FLUIDS).setPreviewCount(1));

        return predicate;
    }


    @Nonnull
    protected TraceabilityPredicate innerPredicate() {
        return new TraceabilityPredicate(blockWorldState -> {
            // all non-MetaTileEntities are allowed inside by default
            TileEntity tileEntity = blockWorldState.getTileEntity();
            if (!(tileEntity instanceof IGregTechTileEntity)) return true;

            MetaTileEntity metaTileEntity = ((IGregTechTileEntity) tileEntity).getMetaTileEntity();

            // incompatible with the kitchen
            if (!(metaTileEntity instanceof WorkableTieredMetaTileEntity) || metaTileEntity instanceof SimpleGeneratorMetaTileEntity)
                return true;

            // slurp
            this.kitchenLogic.giveMetaTileEntity((WorkableTieredMetaTileEntity) metaTileEntity);
            this.kitchenLogic.wasNotified = true;
            return true;
        });
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        super.addInformation(stack, player, tooltip, advanced);

        for (int i = 1; i < 6; i++) {
            tooltip.add(I18n.format("gregtechfoodoption.machine.kitchen.tooltip." + i));
        }
    }



    @Override
    public boolean allowsExtendedFacing() {
        return false;
    }

    @Override
    public boolean allowsFlip() {
        return false;
    }

    protected boolean updateStructureDimensions() {

        World world = getWorld();
        EnumFacing front = getFrontFacing();
        EnumFacing back = front.getOpposite();
        EnumFacing left = front.rotateYCCW();
        EnumFacing right = left.getOpposite();

        BlockPos.MutableBlockPos lPos = new BlockPos.MutableBlockPos(getPos().offset(back)); // Can't have it looking the border to the left and right of the controller.
        BlockPos.MutableBlockPos rPos = new BlockPos.MutableBlockPos(getPos().offset(back));
        BlockPos.MutableBlockPos bPos = new BlockPos.MutableBlockPos(getPos());

        // find the distances from the controller to the plascrete blocks on one horizontal axis and the Y axis
        // repeatable aisles take care of the second horizontal axis
        int sDist = 0;
        int bDist = 0;

        // find the left, right, back, and front distances for the structure pattern
        // maximum size is 15x15 including walls, so check the back
        for (int i = 0; i < 15; i++) {
            if (isBlockEdge(world, bPos, back)) {
                bDist = i;
                break;
            }
        }

        for (int i = 1; i < 9; i++) { // start at 1 for an off-by-one error
            if (isBlockEdge(world, lPos, left) & isBlockEdge(world, rPos, right)) {
                sDist = i; // The & is absolutely *essential* here.
                break;
            }
        }


        if (sDist < MIN_RADIUS || bDist < MIN_RADIUS * 2) {
            invalidateStructure();
            return false;
        }

        this.sDist = sDist;
        this.bDist = bDist;

        if (!this.getWorld().isRemote) {
            writeCustomData(GregtechDataCodes.UPDATE_STRUCTURE_SIZE, buf -> {
                buf.writeInt(this.sDist);
                buf.writeInt(this.bDist);
            });
        }
        return true;
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        resetTileAbilities();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        data.setInteger("sDist", this.sDist);
        data.setInteger("bDist", this.bDist);
        data.setInteger("orderSize", this.orderSize);
        writeRecipeItemToNBT(data);
        return super.writeToNBT(data);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.sDist = data.getInteger("sDist");
        this.bDist = data.getInteger("bDist");
        this.orderSize = data.getInteger("orderSize");
        if (data.hasKey("recipe")) {
            this.recipeHolder.setStackInSlot(0, new ItemStack(data.getCompoundTag("recipe")));
        }
        reinitializeStructurePattern();
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeInt(this.sDist);
        buf.writeInt(this.bDist);
        buf.writeInt(this.orderSize);
        buf.writeItemStack(this.recipeHolder.getStackInSlot(0));
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.sDist = buf.readInt();
        this.bDist = buf.readInt();
        this.orderSize = buf.readInt();
        try {
            this.recipeHolder.setStackInSlot(0, buf.readItemStack());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        reinitializeStructurePattern();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return Textures.SOLID_STEEL_CASING;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityKitchen(metaTileEntityId);
    }

    @Override
    protected ModularUI.Builder createUITemplate(EntityPlayer entityPlayer) {
        ModularUI.Builder builder = super.createUITemplate(entityPlayer);
        builder.slot(recipeHolder, 0, 150, 100, GuiTextures.SLOT);

        return builder;
    }

    public boolean isBlockEdge(@Nonnull World world, @Nonnull BlockPos.MutableBlockPos pos, @Nonnull EnumFacing direction) {
        return world.getBlockState(pos.move(direction)) == getCasingState() || world.getTileEntity(pos) instanceof MetaTileEntityHolder;
    }

    @Nonnull
    protected IBlockState getCasingState() {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE);
    }

    @Nonnull
    protected IBlockState getFloorState() {
        return GTFOMetaBlocks.GTFO_CASING.getState(GTFOBlockCasing.CasingType.PORCELAIN_TILE);
    }

    @Nonnull
    protected IBlockState getFloorState2() {
        return GTFOMetaBlocks.GTFO_CASING.getState(GTFOBlockCasing.CasingType.DARK_PORCELAIN_TILE);
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == GregtechDataCodes.UPDATE_STRUCTURE_SIZE) {
            this.sDist = buf.readInt();
            this.bDist = buf.readInt();
        } else if (dataId == GregtechDataCodes.WORKABLE_ACTIVE) {
            scheduleRenderUpdate();
        } else if (dataId == GregtechDataCodes.WORKING_ENABLED) {
            scheduleRenderUpdate();
        } else if (dataId == GTFOValues.UPDATE_KITCHEN_ORDER) {
            this.orderSize = buf.readInt();
            kitchenLogic.recheckOutputs = true;
        }
    }

    @Override
    protected @NotNull ICubeRenderer getFrontOverlay() {
        return Textures.BLAST_FURNACE_OVERLAY;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        this.getFrontOverlay().renderOrientedState(renderState, translation, pipeline, getFrontFacing(), isActive(), kitchenLogic.isWorkingEnabled());
    }

    public boolean isActive() {
        return this.isStructureFormed() && kitchenLogic.state == KitchenLogic.KitchenLogicState.PROBABLY_FINE && kitchenLogic.isWorkingEnabled();
    }

    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        MultiblockDisplayText.builder(textList, isStructureFormed())
                .setWorkingStatus(this.kitchenLogic.isWorkingEnabled(), this.isActive())
                .addWorkingStatusLine()
                .addEnergyUsageLine(this.energyContainer)
                .addEnergyTierLine(this.getEnergyTier())
                .addCustom((list) -> {
                    if (!this.isActive())
                        return;
                    ITextComponent comp = null;
                    switch (this.kitchenLogic.state) {
                        case PROBABLY_FINE -> comp = new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.probably_fine");
                        case ORDER_COMPLETE -> comp = new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.order_complete");
                    }
                    if (comp != null) {
                        list.add(comp.setStyle((new Style()).setColor(TextFormatting.AQUA)));
                    }
                })
                .addCustom((list) -> {
                    if (!this.isActive())
                        return;
                    list.add(new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.order_size", this.orderSize).setStyle((new Style()).setColor(TextFormatting.GOLD)));
                })
                .addCustom((list) -> {
                    TextFormatting color = TextFormatting.GRAY;
                    if (kitchenLogic.dirtiness > 100) {
                        color = TextFormatting.RED;
                    } else if (kitchenLogic.dirtiness > 50) {
                        color = TextFormatting.YELLOW;
                    } else if (kitchenLogic.dirtiness > 10) {
                        color = TextFormatting.WHITE;
                    }

                    list.add(new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.dirtiness", this.kitchenLogic.dirtiness).setStyle(
                            (new Style()).setColor(color)));
                });
    }

    @Override
    protected void addErrorText(List<ITextComponent> textList) {
        MultiblockDisplayText.builder(textList, this.isStructureFormed(), true)
                .addCustom((list) -> {
                    if (!this.isStructureFormed() || !this.kitchenLogic.isWorkingEnabled())
                        return;
                    ITextComponent comp = null;
                    switch (this.kitchenLogic.state) {
                        case NO_RECIPE -> comp = new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.no_recipe");
                        case BAD_MACHINES -> comp = new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.bad_machines");
                        case MACHINES_NOT_WORKING -> comp = new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.machines_not_working");
                        case NO_INGREDIENTS -> comp = new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.no_ingredients");
                    }
                    if (comp != null) {
                        list.add(comp.setStyle((new Style()).setColor(TextFormatting.AQUA)));
                    }
                });
    }
    protected void addWarningText(List<ITextComponent> textList) {
        MultiblockDisplayText.builder(textList, this.isStructureFormed(), false)
                .addMaintenanceProblemLines(this.getMaintenanceProblems())
                .addLowPowerLine(!drainEnergy(true))
                .addCustom((list) -> {
                    if (!this.isStructureFormed() || !this.kitchenLogic.isWorkingEnabled())
                        return;
                    ITextComponent comp = null;
                    switch (this.kitchenLogic.state) {
                        case BUSES_FULL -> comp = new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.buses_full");
                        case HATCHES_FULL -> comp = new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.hatches_full");
                    }
                    if (comp != null) {
                        list.add(comp.setStyle((new Style()).setColor(TextFormatting.AQUA)));
                    }
                    if (this.kitchenLogic.dirtiness > 100) {
                        list.add(new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.very_dirty").setStyle(new Style().setColor(TextFormatting.RED)));
                    }
                });
    }


    @Override
    protected @NotNull Widget getFlexButton(int x, int y, int width, int height) {
        WidgetGroup group = new WidgetGroup(x, y, width, height);
        group.addWidget((new ClickButtonWidget(0, 0, 9, 18, "", this::decrementOrderSize)).setTooltipText("gregtechfoodoption.multiblock.kitchen.decrement_order").setButtonTexture(GuiTextures.BUTTON_THROTTLE_MINUS));
        group.addWidget((new ClickButtonWidget(9, 0, 9, 18, "", this::incrementOrderSize)).setTooltipText("gregtechfoodoption.multiblock.kitchen.increment_order").setButtonTexture(GuiTextures.BUTTON_THROTTLE_PLUS));
        return group;
    }

    private void incrementOrderSize(Widget.ClickData clickData) {
        this.orderSize++;
        this.orderSize = Math.min(this.orderSize, 64);
        this.writeCustomData(GTFOValues.UPDATE_KITCHEN_ORDER, buf -> buf.writeInt(this.orderSize));
        this.markDirty();
    }


    private void decrementOrderSize(Widget.ClickData clickData) {
        this.orderSize--;
        this.orderSize = Math.max(this.orderSize, 1);
        this.writeCustomData(GTFOValues.UPDATE_KITCHEN_ORDER, buf -> buf.writeInt(this.orderSize));
        this.markDirty();
    }

    public IItemHandlerModifiable getInputInventory() {
        return inputInventory;
    }

    public IItemHandlerModifiable getOutputInventory() {
        return outputInventory;
    }

    public IMultipleTankHandler getInputFluidInventory() {
        return inputFluidInventory;
    }

    public IMultipleTankHandler getOutputFluidInventory() {
        return outputFluidInventory;
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

    public NBTTagCompound getRecipeNBT() {
        if (!recipeHolder.getStackInSlot(0).isEmpty()) {
            return recipeHolder.getStackInSlot(0).getTagCompound();
        }
        return null;
    }

    public void writeRecipeItemToNBT(NBTTagCompound tag) {
        if (!recipeHolder.getStackInSlot(0).isEmpty()) {
            NBTTagCompound item = new NBTTagCompound();
            recipeHolder.getStackInSlot(0).writeToNBT(item);
            tag.setTag("recipe", item);
        }
    }

    public int getOrderSize() {
        return orderSize;
    }
}
