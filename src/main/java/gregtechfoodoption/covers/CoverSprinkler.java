package gregtechfoodoption.covers;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;
import gregtech.api.GregTechAPI;
import gregtech.api.capability.GregtechDataCodes;
import gregtech.api.cover.CoverBehavior;
import gregtech.api.cover.CoverWithUI;
import gregtech.api.cover.ICoverable;
import gregtech.api.gui.ModularUI;
import gregtech.api.unification.material.Materials;
import gregtech.api.util.XSTR;
import gregtech.client.particle.GTParticleManager;
import gregtechfoodoption.client.particle.GTFOSprinkle;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import static gregtechfoodoption.GTFOValues.UPDATE_OPERATION_POS;
import static gregtechfoodoption.GTFOValues.UPDATE_SPRINKLE_COLOR;
import static net.minecraft.block.BlockFarmland.MOISTURE;

public class CoverSprinkler extends CoverBehavior implements CoverWithUI, ITickable {
    private int tier;

    private BlockPos.MutableBlockPos operationPosition;
    private int sprinkleColor;
    private final SprinklerRandom random;
    private AxisAlignedBB workingArea;
    private boolean wasWorking = false;
    private static final int LENGTH = 9;

    public CoverSprinkler(ICoverable coverHolder, EnumFacing attachedSide, int tier) {
        super(coverHolder, attachedSide);
        this.tier = tier;
        this.random = new SprinklerRandom(8 - tier);
    }

    @Override
    public boolean canAttach() {
        return attachedSide == EnumFacing.DOWN && coverHolder.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, attachedSide) != null;
    }

    @Override
    public void renderCover(CCRenderState ccRenderState, Matrix4 matrix4, IVertexOperation[] iVertexOperations, Cuboid6 cuboid6, BlockRenderLayer blockRenderLayer) {
        if (wasWorking) {
            GTParticleManager.INSTANCE.addEffect(new GTFOSprinkle(this.coverHolder.getWorld(), operationPosition.getX() + 0.5, operationPosition.getY() + 1, operationPosition.getZ() + 0.5, sprinkleColor));
            updateOperationPosition();
        }
    }

    @Override
    public ModularUI createUI(EntityPlayer entityPlayer) {
        return null;
    }

    @Override
    public void update() {
        IFluidHandler fluidHandler = this.coverHolder.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, attachedSide);
        boolean isWorkingNow = fluidHandler != null && fluidHandler.drain(Materials.Water.getFluid(1), true) != null;
        if (fluidHandler != null && fluidHandler.getTankProperties().length > 0) {
            FluidStack fluid = fluidHandler.getTankProperties()[0].getContents();
            FluidStack drop = fluid.copy();
            drop.amount = 1;
            if (fluidHandler.drain(drop, true) != null) {
                isWorkingNow = true;
                int color = GregTechAPI.MaterialRegistry.get(drop.getFluid().getName()).getMaterialRGB();
                if (color != sprinkleColor) {
                    sprinkleColor = color;
                    syncSprinkleColor();
                }
            }
        }
        this.setWorking(isWorkingNow);
        if (isWorkingNow && this.coverHolder.getOffsetTimer() % 20 == 0)
            return;
        if (operationPosition == null || workingArea == null)
            setupWorkingArea();
        updateOperationPosition();
        syncOperationPosition();
        IBlockState cropState = this.coverHolder.getWorld().getBlockState(operationPosition);
        if (cropState.getBlock() instanceof IGrowable) {
            ((IGrowable) cropState.getBlock()).grow(this.coverHolder.getWorld(), random, operationPosition, cropState);
        }

        IBlockState farmlandState = this.coverHolder.getWorld().getBlockState(operationPosition.down());
        if (farmlandState.getBlock() instanceof BlockFarmland) {
            this.coverHolder.getWorld().setBlockState(operationPosition.down(), farmlandState.withProperty(MOISTURE, Math.min(7, farmlandState.getValue(MOISTURE) + 2)));
        }


    }

    private void updateOperationPosition() {
        operationPosition.move(EnumFacing.EAST);
        if (!isOperationPositionInsideWorkingArea()) {
            operationPosition.move(EnumFacing.WEST, LENGTH).move(EnumFacing.SOUTH);
            if (!isOperationPositionInsideWorkingArea()) {
                setDefaultOperationPosition();
                if (!isOperationPositionInsideWorkingArea() && !coverHolder.getWorld().isRemote) { // This is needed for persistent working areas
                    setupWorkingArea();
                }
            }
        }
    }

    private boolean isOperationPositionInsideWorkingArea() {
        return workingArea.contains(new Vec3d(operationPosition.getX(), operationPosition.getY(), operationPosition.getZ()));
    }

    private void setupWorkingArea() {
        workingArea = new AxisAlignedBB(this.getPos().offset(EnumFacing.SOUTH, LENGTH / 2).offset(EnumFacing.EAST, LENGTH / 2),
                this.getPos().offset(EnumFacing.NORTH, LENGTH / 2).offset(EnumFacing.WEST, LENGTH / 2))
                .grow(.1);
        if (operationPosition == null || !isOperationPositionInsideWorkingArea()) { // The second part is needed due to weirdness in how the facing is set.
            setDefaultOperationPosition();
        }
    }

    private void setDefaultOperationPosition() {
        operationPosition = new BlockPos.MutableBlockPos(this.getPos().offset(EnumFacing.NORTH, LENGTH / 2).offset(EnumFacing.WEST, LENGTH / 2));
        //writeCustomData(UPDATE_OPERATION_POS, buf -> buf.writeBlockPos(operationPosition));
    }

    public BlockPos getPos() {
        return this.coverHolder.getPos().down(3);
    }

    private void syncOperationPosition() {
        writeUpdateData(UPDATE_OPERATION_POS, packetBuffer -> {
            packetBuffer.writeBlockPos(operationPosition);
        });
    }

    private void syncSprinkleColor() {
        writeUpdateData(UPDATE_SPRINKLE_COLOR, packetBuffer -> {
            packetBuffer.writeInt(sprinkleColor);
        });
    }

    @Override
    public void readUpdateData(int id, PacketBuffer packetBuffer) {
        super.readUpdateData(id, packetBuffer);
        if (id == UPDATE_OPERATION_POS) {
            this.operationPosition = new BlockPos.MutableBlockPos(packetBuffer.readBlockPos());
            this.coverHolder.scheduleRenderUpdate();
        } else if (id == UPDATE_SPRINKLE_COLOR) {
            this.sprinkleColor = packetBuffer.readInt();
        } else if (id == GregtechDataCodes.IS_WORKING) {
            wasWorking = packetBuffer.readBoolean();
        }
    }

    public void setWorking(boolean b) {
        if (wasWorking != b) {
            wasWorking = b;
            writeUpdateData(GregtechDataCodes.IS_WORKING, packetBuffer -> {
                packetBuffer.writeBoolean(wasWorking);
            });
        }
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        setupWorkingArea();
        buf.writeBlockPos(operationPosition);
        buf.writeBoolean(wasWorking);
        buf.writeInt(sprinkleColor);
    }

    @Override
    public void readInitialSyncData(PacketBuffer buf) {
        super.readInitialSyncData(buf);
        this.operationPosition = new BlockPos.MutableBlockPos(buf.readBlockPos());
        this.wasWorking = buf.readBoolean();
        this.sprinkleColor = buf.readInt();
        setupWorkingArea();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setLong("operationPos", this.operationPosition.toLong());
        tagCompound.setBoolean("wasWorking", wasWorking);
        tagCompound.setInteger("sprinkleColor", sprinkleColor);
        return tagCompound;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        this.operationPosition = new BlockPos.MutableBlockPos(BlockPos.fromLong(tagCompound.getLong("operationPos")));
        this.wasWorking = tagCompound.getBoolean("wasWorking");
        this.sprinkleColor = tagCompound.getInteger("sprinkleColor");
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
