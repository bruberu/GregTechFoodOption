package gregtechfoodoption.machines.multiblock.kitchen;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.google.common.collect.Lists;
import gregtech.api.capability.GregtechDataCodes;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.capability.impl.ItemHandlerList;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.resources.TextureArea;
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
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockBoilerCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtechfoodoption.block.GTFOBlockCasing;
import gregtechfoodoption.block.GTFOMetaBlocks;
import gregtechfoodoption.client.GTFOGuiTextures;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.List;

public class MetaTileEntityKitchen extends MultiblockWithDisplayBase {
    public static final int MIN_RADIUS = 2;
    private final KitchenLogic kitchenLogic;
    protected IItemHandlerModifiable inputInventory;
    protected IItemHandlerModifiable outputInventory;
    protected IMultipleTankHandler inputFluidInventory;
    protected IMultipleTankHandler outputFluidInventory;
    protected IEnergyContainer energyContainer;
    protected IItemHandlerModifiable recipeHolder = new ItemStackHandler(1);

    private int sDist = 0;
    private int bDist = 0;

    public MetaTileEntityKitchen(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        kitchenLogic = new KitchenLogic(this);
        resetTileAbilities();
    }

    protected void initializeAbilities() {
        this.inputInventory = new ItemHandlerList(getAbilities(MultiblockAbility.IMPORT_ITEMS));
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
                .aisle(centerBuilder.toString(), topBuilder.toString()).setRepeatable(1, bDist)
                .aisle(frontBuilder.toString(), emptyBuilder.toString())
                .where('S', selfPredicate())
                .where('B', basePredicate.or(states(getCasingState())))
                .where('F', states(getFloorState()))
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
            return true;
        });
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
        // maximum size is 15x15 including walls, so check 7 block radius around the controller for blocks
        for (int i = 1; i < 8; i++) {
            if (sDist == 0 && isBlockEdge(world, lPos, left) & isBlockEdge(world, rPos, right))
                sDist = i; // The & is absolutely *essential* here.
            if (bDist == 0 && isBlockEdge(world, bPos, back)) bDist = i;
            if (sDist != 0 && bDist != 0) break;
        }


        if (sDist < MIN_RADIUS || bDist < MIN_RADIUS * 2) {
            invalidateStructure();
            return false;
        }

        this.sDist = sDist;
        this.bDist = bDist;

        writeCustomData(GregtechDataCodes.UPDATE_STRUCTURE_SIZE, buf -> {
            buf.writeInt(this.sDist);
            buf.writeInt(this.bDist);
        });
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
        writeRecipeItemToNBT(data);
        return super.writeToNBT(data);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.sDist = data.getInteger("sDist");
        this.bDist = data.getInteger("bDist");
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
        buf.writeItemStack(this.recipeHolder.getStackInSlot(0));
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.sDist = buf.readInt();
        this.bDist = buf.readInt();
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

    public boolean isBlockFloor(@Nonnull World world, @Nonnull BlockPos.MutableBlockPos pos, @Nonnull EnumFacing direction) {
        return world.getBlockState(pos.move(direction)) == getFloorState();
    }

    @Nonnull
    protected IBlockState getCasingState() {
        return MetaBlocks.BOILER_CASING.getState(BlockBoilerCasing.BoilerCasingType.STEEL_PIPE);
    }

    @Nonnull
    protected IBlockState getFloorState() {
        return GTFOMetaBlocks.GTFO_CASING.getState(GTFOBlockCasing.CasingType.PORCELAIN_TILE);
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
        }
    }

    @Override
    protected @NotNull ICubeRenderer getFrontOverlay() {
        return Textures.BLAST_FURNACE_OVERLAY;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        this.getFrontOverlay().renderOrientedState(renderState, translation, pipeline, getFrontFacing(), isActive(), true);

    }
    @Override
    protected void addDisplayText(List<ITextComponent> textList) {
        MultiblockDisplayText.builder(textList, isStructureFormed())
                .addWorkingStatusLine()
                .addCustom((list) -> {
                    ITextComponent comp = null;
                    switch (this.kitchenLogic.state) {
                        case NO_RECIPE -> comp = new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.no_recipe");
                        case BUSES_FULL -> comp = new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.buses_full");
                        case BAD_MACHINES -> comp = new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.bad_machines");
                        case HATCHES_FULL -> comp = new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.hatches_full");
                        case NO_INGREDIENTS -> comp = new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.no_ingredients");
                        case PROBABLY_FINE -> comp = new TextComponentTranslation("gregtechfoodoption.multiblock.kitchen.probably_fine");
                    }
                    list.add(comp.setStyle((new Style()).setColor(TextFormatting.AQUA)));
                });
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

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing side) {
        return super.getCapability(capability, side);
    }
}