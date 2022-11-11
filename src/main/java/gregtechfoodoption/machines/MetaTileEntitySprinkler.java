package gregtechfoodoption.machines;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;
import gregtech.api.capability.IControllable;
import gregtech.api.gui.ModularUI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.TieredMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.util.XSTR;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtechfoodoption.client.GTFOClientHandler;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import static gregtech.api.capability.GregtechDataCodes.*;
import static gregtechfoodoption.GTFOValues.UPDATE_OPERATION_POS;
import static net.minecraft.block.BlockFarmland.MOISTURE;

public class MetaTileEntitySprinkler extends TieredMetaTileEntity implements IControllable {
    private BlockPos.MutableBlockPos operationPosition;
    private final SprinklerRandom random;
    private AxisAlignedBB workingArea;
    private static final int LENGTH = 9;
    private boolean isWorking;
    private static final int BASE_EU_CONSUMPTION = 1;
    private boolean isWorkingEnabled;

    public MetaTileEntitySprinkler(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, tier);
        random = new SprinklerRandom(8 - tier);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntitySprinkler(this.metaTileEntityId, this.getTier());
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        return null;
    }

    protected int getEnergyConsumedPerTick() {
        return BASE_EU_CONSUMPTION * (1 << ((getTier() - 1) * 2));
    }

    @Override
    public void update() {
        super.update();

        boolean isWorkingNow = energyContainer.getEnergyStored() >= getEnergyConsumedPerTick();
        if (!getWorld().isRemote && isWorkingNow != isWorking) {
            writeCustomData(IS_WORKING, buffer -> buffer.writeBoolean(isWorkingNow));
            this.isWorking = isWorkingNow;
        }
        if (!isWorkingNow || this.getHolder().getOffsetTimer() % 20 == 0)
            return;
        updateOperationPosition();
        if (this.getWorld().isRemote) {
            getWorld().spawnParticle(EnumParticleTypes.DRIP_WATER, operationPosition.getX(), operationPosition.getY(), operationPosition.getZ(), 0, -1, 0);
            return;
        }
        IBlockState cropState = this.getWorld().getBlockState(operationPosition);
        if (cropState.getBlock() instanceof IGrowable) {
            ((IGrowable) cropState.getBlock()).grow(this.getWorld(), random, operationPosition, cropState);
        }

        IBlockState farmlandState = this.getWorld().getBlockState(operationPosition.down());
        if (farmlandState.getBlock() instanceof BlockFarmland) {
            this.getWorld().setBlockState(operationPosition.down(), farmlandState.withProperty(MOISTURE, Math.min(7, farmlandState.getValue(MOISTURE) + 2)));
        }
    }

    private void updateOperationPosition() {
        operationPosition.move(this.getFrontFacing().rotateYCCW());
        if (!isOperationPositionInsideWorkingArea()) {
            operationPosition.move(this.getFrontFacing().rotateY(), LENGTH).move(this.getFrontFacing());
            if (!isOperationPositionInsideWorkingArea()) {
                setDefaultOperationPosition();
                if (!isOperationPositionInsideWorkingArea() && !getWorld().isRemote) { // This is needed for persistent working areas
                    setupWorkingArea();
                }
            }
        }
    }

    private boolean isOperationPositionInsideWorkingArea() {
        return workingArea.contains(new Vec3d(operationPosition.getX(), operationPosition.getY(), operationPosition.getZ()));
    }

    private void setupWorkingArea() {
        workingArea = new AxisAlignedBB(this.getPos().offset(this.getFrontFacing()).offset(this.getFrontFacing().rotateY(), LENGTH / 2),
                this.getPos().offset(this.getFrontFacing(), LENGTH).offset(this.getFrontFacing().rotateYCCW(), LENGTH / 2))
                .grow(.1);
        if (operationPosition == null || !isOperationPositionInsideWorkingArea()) { // The second part is needed due to weirdness in how the facing is set.
            setDefaultOperationPosition();
            writeCustomData(UPDATE_OPERATION_POS, buf -> buf.writeBlockPos(operationPosition));
        }
    }

    private void setDefaultOperationPosition() {
        operationPosition = new BlockPos.MutableBlockPos(this.getPos().offset(this.getFrontFacing()).offset(this.getFrontFacing().rotateY(), LENGTH / 2));
        writeCustomData(UPDATE_OPERATION_POS, buf -> buf.writeBlockPos(operationPosition));
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        OrientedOverlayRenderer renderer = GTFOClientHandler.SPRINKLER_OVERLAY;
        renderer.renderOrientedState(renderState, translation, pipeline, Cuboid6.full, this.getFrontFacing(), isWorking, true);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.isWorking = buf.readBoolean();
        this.operationPosition = new BlockPos.MutableBlockPos(buf.readBlockPos());
        this.isWorkingEnabled = buf.readBoolean();
        setupWorkingArea();
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        setupWorkingArea();
        buf.writeBoolean(isWorking);
        buf.writeBlockPos(operationPosition);
        buf.writeBoolean(isWorkingEnabled);
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
        operationPosition = new BlockPos.MutableBlockPos(BlockPos.fromLong(data.getLong("operationPosition")));
    }

    @Override
    public void setWorkingEnabled(boolean b) {
        this.isWorkingEnabled = b;
        this.writeCustomData(WORKING_ENABLED, buf -> buf.writeBoolean(b));
    }

    @Override
    public boolean isWorkingEnabled() {
        return isWorkingEnabled;
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == UPDATE_FRONT_FACING) {
            setupWorkingArea();
            setDefaultOperationPosition();
        }
        if (dataId == IS_WORKING) {
            this.isWorking = buf.readBoolean();
            getHolder().scheduleRenderUpdate();
        }
    }

    public static class SprinklerRandom extends XSTR {
        private int actualBound;
        public SprinklerRandom(int actualBound) {
            this.actualBound = actualBound;
        }

        @Override
        public int nextInt(int bound) {
            return super.nextInt(actualBound);
        }
    }
}
