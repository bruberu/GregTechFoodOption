package gregtechfoodoption.machines.farmer;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;
import codechicken.lib.vec.Vector3;
import gregtech.api.capability.impl.NotifiableItemStackHandler;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.TieredMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.util.GTTransferUtils;
import gregtech.api.util.GregFakePlayer;
import gregtech.api.util.InventoryUtils;
import gregtech.client.particle.GTParticleManager;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtechfoodoption.client.GTFOClientHandler;
import gregtechfoodoption.client.particle.GTFOFarmingLaserBeamParticle;
import gregtechfoodoption.utils.GTFOUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Collections;
import java.util.List;

import static gregtech.api.capability.GregtechDataCodes.IS_WORKING;
import static gregtech.api.capability.GregtechDataCodes.UPDATE_FRONT_FACING;
import static gregtechfoodoption.GTFOValues.UPDATE_LASER_POS;

public class MetaTileEntityFarmer extends TieredMetaTileEntity {

    private final int ticksPerAction;
    private AxisAlignedBB workingArea;
    private MutableBlockPos operationPosition;
    public static final int LENGTH = 9;
    private boolean isWorking;
    private FarmerMode cachedMode;
    public FakePlayer fakePlayer;
    private boolean isFull = false;
    private boolean hasResponded = false;
    private boolean hasFailedToInsertCrop = false;

    private static final int BASE_EU_CONSUMPTION = 8;


    public MetaTileEntityFarmer(ResourceLocation metaTileEntityId, int tier, int ticksPerAction) {
        super(metaTileEntityId, tier);
        this.ticksPerAction = ticksPerAction;
        this.initializeInventory();
        cachedMode = FarmerModeRegistry.getAnyMode();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityFarmer(metaTileEntityId, getTier(), ticksPerAction);
    }

    @Override
    public void update() {
        super.update();
        boolean isWorkingNow = energyContainer.getEnergyStored() >= getEnergyConsumedPerTick();
        energyContainer.removeEnergy(getEnergyConsumedPerTick());
        if (operationPosition == null || workingArea == null)
            setupWorkingArea();
        if (this.getWorld().isRemote) {
            if (isWorking) {
                if (getOffsetTimer() % ticksPerAction == 0) {
                    GTParticleManager.INSTANCE.addEffect(new GTFOFarmingLaserBeamParticle(getWorld(),
                            new Vector3(new Vec3d(getPos()).add(GTFOUtils.getScaledFacingVec(getFrontFacing(), .4)).add(.5, .7, .5)),
                            new Vector3(new Vec3d(operationPosition)).add(.5, .0, .5),
                            ticksPerAction * 3));
                    updateOperationPosition();
                }
            }
            return;
        }
        if (isWorkingNow != isWorking) {
            writeCustomData(IS_WORKING, buffer -> buffer.writeBoolean(isWorkingNow));
            this.isWorking = isWorkingNow;
        }
        if (this.getOffsetTimer() % ticksPerAction != 0)
            return;
        if (isWorkingNow) {
            boolean didSomething = false;

            if (this.fakePlayer == null)
                this.fakePlayer = GregFakePlayer.get((WorldServer)this.getWorld());

            // Phase 1: move crop pointer and collect crops if there exists enough inventory
            IBlockState blockState = getWorld().getBlockState(operationPosition);
            if (!getWorld().isAirBlock(operationPosition) && !isFull) {
                boolean canHarvestBlock = true;
                if (!cachedMode.canOperate(blockState, this, GTFOUtils.copy(operationPosition), getWorld())) {
                    FarmerMode mode = FarmerModeRegistry.findSuitableFarmerMode(blockState, this, GTFOUtils.copy(operationPosition), getWorld());
                    if (mode != null) {
                        cachedMode = mode;
                    } else {
                        canHarvestBlock = false;
                    }
                }
                if (canHarvestBlock) {
                    List<ItemStack> drops = cachedMode.getDrops(blockState, getWorld(), GTFOUtils.copy(operationPosition), this);
                    if (GTTransferUtils.addItemsToItemHandler(getExportItems(), true, drops)) {
                        GTTransferUtils.addItemsToItemHandler(getExportItems(), false, drops);
                        cachedMode.harvest(blockState, getWorld(), GTFOUtils.copy(operationPosition), this);
                        didSomething = true;
                        hasResponded = true;
                    } else if (GTFOUtils.isFull(getExportItems())) {
                        isFull = true;
                        hasFailedToInsertCrop = true;
                        notifiedItemOutputList.clear();
                    } else {
                        hasFailedToInsertCrop = true;
                    }
                }
            } else if (isFull) {
                if (notifiedItemOutputList.contains(getExportItems()) && !GTFOUtils.isFull(getExportItems())) {
                    isFull = false;
                }
            }

            // Phase 2: place down seed if possible
            if (getWorld().isAirBlock(operationPosition) && InventoryUtils.getNumberOfEmptySlotsInInventory(getImportItems()) != getImportItems().getSlots()) {
                int unemptySlot = GTFOUtils.getFirstUnemptyItemSlot(getImportItems());
                ItemStack seedItem = getImportItems().extractItem(unemptySlot, 1, true);
                boolean canPlaceSeed = true;
                if (!cachedMode.canPlaceItem(seedItem)) {
                    FarmerMode mode = FarmerModeRegistry.findSuitableFarmerMode(seedItem);
                    if (mode != null) {
                        cachedMode = mode;
                    } else {
                        canPlaceSeed = false;
                        // Move this unusable stack to the output
                        ItemStack junkStack = getImportItems().extractItem(unemptySlot, getImportItems().getStackInSlot(unemptySlot).getCount(), true);
                        if (GTTransferUtils.addItemsToItemHandler(getExportItems(), true, Collections.singletonList(junkStack))) {
                            GTTransferUtils.addItemsToItemHandler(getExportItems(), false,
                                    Collections.singletonList(getImportItems().extractItem(unemptySlot, getImportItems().getStackInSlot(unemptySlot).getCount(), false)));
                        }
                    }
                }
                if (canPlaceSeed && cachedMode.canPlaceAt(GTFOUtils.copy(operationPosition), new MutableBlockPos(this.getPos()), this.getFrontFacing())) {
                    EnumActionResult result = cachedMode.place(seedItem, getWorld(), GTFOUtils.copy(operationPosition), this);
                    if (result == EnumActionResult.SUCCESS) {
                        getImportItems().extractItem(unemptySlot, 1, false);
                        didSomething = true;
                    }
                }
            }
            if (didSomething)
                getWorld().playSound(null, getPos().getX() + 0.5, getPos().getY() + 0.5, getPos().getZ() + 0.5,
                        GTFOClientHandler.FARMER_LASER, SoundCategory.BLOCKS, 1.0f, 1.0f);
            updateOperationPosition();
        }
    }

    protected int getEnergyConsumedPerTick() {
        return BASE_EU_CONSUMPTION * (1 << ((getTier() - 1) * 2));
    }

    private void updateOperationPosition() {
        operationPosition.move(this.getFrontFacing().rotateYCCW());
        if (!isOperationPositionInsideWorkingArea()) {
            operationPosition.move(this.getFrontFacing().rotateY(), LENGTH).move(this.getFrontFacing());
            if (!isOperationPositionInsideWorkingArea()) {
                if (!hasResponded && hasFailedToInsertCrop) {
                    isFull = true; // If it has gone through the entire field and not harvested anything, it's likely that it's full.
                    notifiedItemOutputList.clear();
                }
                hasResponded = false;
                hasFailedToInsertCrop = false;
                operationPosition = new MutableBlockPos(this.getPos().offset(this.getFrontFacing()).offset(this.getFrontFacing().rotateY(), LENGTH / 2));
                if (!isOperationPositionInsideWorkingArea() && !getWorld().isRemote) { // This is needed for persistent working areas
                    setupWorkingArea();
                }
            }
        }
        writeCustomData(UPDATE_LASER_POS, buf -> buf.writeBlockPos(operationPosition));
    }

    private void setupWorkingArea() {
        workingArea = new AxisAlignedBB(this.getPos().offset(this.getFrontFacing()).offset(this.getFrontFacing().rotateY(), LENGTH / 2),
                this.getPos().offset(this.getFrontFacing(), LENGTH).offset(this.getFrontFacing().rotateYCCW(), LENGTH / 2))
                .grow(.1);
        if (operationPosition == null || !isOperationPositionInsideWorkingArea()) { // The second part is needed due to weirdness in how the facing is set.
            operationPosition = new MutableBlockPos(this.getPos().offset(this.getFrontFacing()).offset(this.getFrontFacing().rotateY(), LENGTH / 2));
            writeCustomData(UPDATE_LASER_POS, buf -> buf.writeBlockPos(operationPosition));
        }
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == UPDATE_FRONT_FACING) {
            setupWorkingArea();
        }
        if (dataId == IS_WORKING) {
            this.isWorking = buf.readBoolean();
            getHolder().scheduleRenderUpdate();
        }
        if (dataId == UPDATE_LASER_POS) {
            this.operationPosition = new MutableBlockPos(buf.readBlockPos());
            getHolder().scheduleRenderUpdate();
        }
    }

    @Override
    public boolean onWrenchClick(EntityPlayer playerIn, EnumHand hand, EnumFacing wrenchSide, CuboidRayTraceResult hitResult) {
        return super.onWrenchClick(playerIn, hand, wrenchSide, hitResult);
    }

    protected IItemHandlerModifiable createImportItemHandler() {
        return new ItemStackHandler(9);
    }

    protected IItemHandlerModifiable createExportItemHandler() {
        return new NotifiableItemStackHandler(9, this, true);
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 176, 166)
                .label(10, 5, this.getMetaFullName());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int index = i * 3 + j;
                builder.widget(new SlotWidget(this.importItems, index, 28 + j * 18, 18 + i * 18, true, true).setBackgroundTexture(GuiTextures.SLOT));
                builder.widget(new SlotWidget(this.exportItems, index, 94 + j * 18, 18 + i * 18, true, true).setBackgroundTexture(GuiTextures.SLOT));
            }
        }

        builder.bindPlayerInventory(entityPlayer.inventory, GuiTextures.SLOT, 7, 84);
        return builder.build(this.getHolder(), entityPlayer);
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        OrientedOverlayRenderer renderer = GTFOClientHandler.FARMER_OVERLAY;
        renderer.renderOrientedState(renderState, translation, pipeline, Cuboid6.full, this.getFrontFacing(), isWorking, true);

    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.isWorking = buf.readBoolean();
        this.operationPosition = new MutableBlockPos(buf.readBlockPos());
        setupWorkingArea();
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        setupWorkingArea();
        buf.writeBoolean(isWorking);
        buf.writeBlockPos(operationPosition);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setLong("operationPosition", operationPosition.toLong());
        return data;
    }
    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        operationPosition = new MutableBlockPos(BlockPos.fromLong(data.getLong("operationPosition")));
    }

    private boolean isOperationPositionInsideWorkingArea() {
        return workingArea.contains(new Vec3d(operationPosition.getX(), operationPosition.getY(), operationPosition.getZ()));
    }
}
