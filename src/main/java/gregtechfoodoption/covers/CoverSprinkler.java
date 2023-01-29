package gregtechfoodoption.covers;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;
import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.cover.CoverBehavior;
import gregtech.api.cover.CoverWithUI;
import gregtech.api.cover.ICoverable;
import gregtech.api.gui.ModularUI;
import gregtech.api.unification.material.Material;
import gregtechfoodoption.GTFOMaterialHandler;
import gregtechfoodoption.client.GTFOClientHandler;
import gregtechfoodoption.client.particle.GTFOSprinkle;
import gregtechfoodoption.materials.FertilizerProperty;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static gregtechfoodoption.GTFOValues.UPDATE_SPRINKLER_DATA;
import static net.minecraft.block.BlockFarmland.MOISTURE;

public class CoverSprinkler extends CoverBehavior implements CoverWithUI, ITickable {
    private int tier;

    private BlockPos.MutableBlockPos operationPosition;
    private int sprinkleColor;
    private AxisAlignedBB workingArea;
    private boolean wasWorking = false;
    private static final int LENGTH = 9;

    public CoverSprinkler(ICoverable coverHolder, EnumFacing attachedSide, int tier) {
        super(coverHolder, attachedSide);
        this.tier = tier;
    }

    @Override
    public boolean canAttach() {
        return attachedSide == EnumFacing.DOWN && coverHolder.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, attachedSide) != null;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderCover(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline, Cuboid6 plateBox, BlockRenderLayer blockRenderLayer) {
        if (wasWorking) {
            Minecraft.getMinecraft().effectRenderer.addEffect(new GTFOSprinkle(this.coverHolder.getWorld(), this.coverHolder.getPos().getX() + 0.5, this.coverHolder.getPos().getY() - 0.1, this.coverHolder.getPos().getZ() + 0.5, operationPosition.getX() + 0.5, operationPosition.getY() + 1, operationPosition.getZ() + 0.5, sprinkleColor));
            //this.coverHolder.getWorld().spawnParticle(EnumParticleTypes.DRIP_WATER, operationPosition.getX() + 0.5, operationPosition.getY() + 1, operationPosition.getZ() + 0.5, 0, 0, 0);
            updateOperationPosition();
        }
        GTFOClientHandler.SPRINKLER_OVERLAY.renderSided(attachedSide, plateBox, renderState, pipeline, translation);
    }

    @Override
    public ModularUI createUI(EntityPlayer entityPlayer) {
        return null;
    }

    @Override
    public void update() {
        IFluidHandler fluidHandler = this.coverHolder.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, attachedSide);
        if (fluidHandler == null)
            return;
        FluidStack fluid = fluidHandler.drain(1, true);
        boolean isWorkingNow = fluid != null;
        int percentageChance = 0;
        if (isWorkingNow) {
            Material mat = GregTechAPI.MaterialRegistry.get(fluid.getFluid().getName());
            if (mat != null) {
                int color = mat.getMaterialRGB();
                if (color != sprinkleColor) {
                    sprinkleColor = color;
                }
                FertilizerProperty property = mat.getProperty(GTFOMaterialHandler.FERTILIZER);
                if (property != null) {
                    percentageChance = property.getBoostPercentage();
                }
            } else {
                sprinkleColor = 255;
            }
        }
        this.wasWorking = isWorkingNow;
        if (isWorkingNow && this.coverHolder.getOffsetTimer() % 20 == 0) {
            syncAllData();
            return;
        }
        if (operationPosition == null || workingArea == null)
            setupWorkingArea();
        updateOperationPosition();
        IBlockState cropState = this.coverHolder.getWorld().getBlockState(operationPosition);
        if (cropState.getBlock() instanceof IGrowable && GTValues.RNG.nextInt(100) < percentageChance) {
            ((IGrowable) cropState.getBlock()).grow(this.coverHolder.getWorld(), GTValues.RNG, operationPosition, cropState);
        }

        IBlockState farmlandState = this.coverHolder.getWorld().getBlockState(operationPosition.down());
        if (farmlandState.getBlock() instanceof BlockFarmland) {
            this.coverHolder.getWorld().setBlockState(operationPosition.down(), farmlandState.withProperty(MOISTURE, Math.min(7, farmlandState.getValue(MOISTURE) + 2)));
        }
        this.syncAllData();
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

    private void syncAllData() {
        writeUpdateData(UPDATE_SPRINKLER_DATA, packetBuffer -> {
            packetBuffer.writeBlockPos(operationPosition);
            packetBuffer.writeInt(sprinkleColor);
            packetBuffer.writeBoolean(wasWorking);
        });
    }
    @Override
    public void readUpdateData(int id, PacketBuffer packetBuffer) {
        super.readUpdateData(id, packetBuffer);
        if (id == UPDATE_SPRINKLER_DATA && this.isRemote()) {
            this.operationPosition = new BlockPos.MutableBlockPos(packetBuffer.readBlockPos());
            this.sprinkleColor = packetBuffer.readInt();
            this.wasWorking = packetBuffer.readBoolean();
            this.coverHolder.scheduleRenderUpdate();
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
}
