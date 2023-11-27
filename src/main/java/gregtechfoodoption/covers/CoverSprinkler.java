package gregtechfoodoption.covers;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;
import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.cover.CoverBase;
import gregtech.api.cover.CoverDefinition;
import gregtech.api.cover.CoverWithUI;
import gregtech.api.cover.CoverableView;
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
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import static gregtechfoodoption.GTFOValues.UPDATE_SPRINKLER_DATA;
import static net.minecraft.block.BlockFarmland.MOISTURE;

public class CoverSprinkler extends CoverBase implements CoverWithUI, ITickable {
    private int tier;

    private BlockPos.MutableBlockPos operationPosition;
    private int sprinkleColor;
    private AxisAlignedBB workingArea;
    private boolean wasWorking = false;
    private static final int LENGTH = 9;
    private boolean showsSprinkles = true;

    public CoverSprinkler(CoverDefinition definition, CoverableView coverHolder, EnumFacing attachedSide, int tier) {
        super(definition, coverHolder, attachedSide);
        this.tier = tier;
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void renderCover(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline, Cuboid6 plateBox, BlockRenderLayer blockRenderLayer) {
        if (wasWorking && showsSprinkles) {
            float chance = Minecraft.getMinecraft().gameSettings.particleSetting == 0 ? 1f : Minecraft.getMinecraft().gameSettings.particleSetting == 1 ? 0.5f : 0.1f;
            if (Math.random() < chance) {
                Minecraft.getMinecraft().effectRenderer.addEffect(new GTFOSprinkle(this.getCoverableView().getWorld(), this.getCoverableView().getPos().getX() + 0.5, this.getCoverableView().getPos().getY() - 0.1, this.getCoverableView().getPos().getZ() + 0.5, operationPosition.getX() + 0.5, operationPosition.getY() + 1, operationPosition.getZ() + 0.5, sprinkleColor));
            }
            updateOperationPosition();
        }
        GTFOClientHandler.SPRINKLER_OVERLAY.renderSided(this.getAttachedSide(), plateBox, renderState, pipeline, translation);
    }

    @Override
    public ModularUI createUI(EntityPlayer entityPlayer) {
        return null;
    }

    @Override
    public void update() {
        IFluidHandler fluidHandler = this.getCoverableView().getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, getAttachedSide());
        if (fluidHandler == null)
            return;
        FluidStack fluid = fluidHandler.drain(1, true);
        boolean isWorkingNow = fluid != null;
        int percentageChance = 0;
        if (isWorkingNow) {
            Material mat = GregTechAPI.materialManager.getMaterial(fluid.getFluid().getName());
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
        if (operationPosition == null || workingArea == null)
            setupWorkingArea();
        updateOperationPosition();
        IBlockState cropState = this.getCoverableView().getWorld().getBlockState(operationPosition);
        if (cropState.getBlock() instanceof IGrowable && GTValues.RNG.nextInt(100) < percentageChance && ((IGrowable) cropState.getBlock()).canGrow(this.getCoverableView().getWorld(), operationPosition, cropState, false)) {
            ((IGrowable) cropState.getBlock()).grow(this.getCoverableView().getWorld(), GTValues.RNG, operationPosition, cropState);
        }

        IBlockState farmlandState = this.getCoverableView().getWorld().getBlockState(operationPosition.down());
        if (farmlandState.getBlock() instanceof BlockFarmland) {
            this.getCoverableView().getWorld().setBlockState(operationPosition.down(), farmlandState.withProperty(MOISTURE, Math.min(7, farmlandState.getValue(MOISTURE) + 2)));
        }
        syncAllData();
    }

    @Override
    public boolean canAttach(@NotNull CoverableView coverHolder, @NotNull EnumFacing attachedSide) {
        return getAttachedSide() == EnumFacing.DOWN && getCoverableView().getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, getAttachedSide()) != null;
    }

    private void updateOperationPosition() {
        operationPosition.move(EnumFacing.EAST);
        if (!isOperationPositionInsideWorkingArea()) {
            operationPosition.move(EnumFacing.WEST, LENGTH).move(EnumFacing.SOUTH);
            if (!isOperationPositionInsideWorkingArea()) {
                setDefaultOperationPosition();
                if (!isOperationPositionInsideWorkingArea() && !getCoverableView().getWorld().isRemote) { // This is needed for persistent working areas
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
        return this.getCoverableView().getPos().down(3);
    }

    private void syncAllData() {
        writeCustomData(UPDATE_SPRINKLER_DATA, packetBuffer -> {
            packetBuffer.writeBlockPos(operationPosition);
            packetBuffer.writeInt(sprinkleColor);
            packetBuffer.writeBoolean(wasWorking);
            packetBuffer.writeBoolean(showsSprinkles);
        });
    }

    @Override
    public void readCustomData(int id, PacketBuffer packetBuffer) {
        super.readCustomData(id, packetBuffer);
        if (id == UPDATE_SPRINKLER_DATA && this.isRemote()) {
            this.operationPosition = new BlockPos.MutableBlockPos(packetBuffer.readBlockPos());
            this.sprinkleColor = packetBuffer.readInt();
            this.wasWorking = packetBuffer.readBoolean();
            this.showsSprinkles = packetBuffer.readBoolean();
            this.getCoverableView().scheduleRenderUpdate();
        }
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        setupWorkingArea();
        buf.writeBlockPos(operationPosition);
        buf.writeBoolean(wasWorking);
        buf.writeInt(sprinkleColor);
        buf.writeBoolean(showsSprinkles);
    }

    @Override
    public void readInitialSyncData(PacketBuffer buf) {
        super.readInitialSyncData(buf);
        this.operationPosition = new BlockPos.MutableBlockPos(buf.readBlockPos());
        this.wasWorking = buf.readBoolean();
        this.sprinkleColor = buf.readInt();
        this.showsSprinkles = buf.readBoolean();
        setupWorkingArea();
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setLong("operationPos", this.operationPosition.toLong());
        tagCompound.setBoolean("wasWorking", wasWorking);
        tagCompound.setInteger("sprinkleColor", sprinkleColor);
        tagCompound.setBoolean("showsSprinkles", showsSprinkles);
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        this.operationPosition = new BlockPos.MutableBlockPos(BlockPos.fromLong(tagCompound.getLong("operationPos")));
        this.wasWorking = tagCompound.getBoolean("wasWorking");
        this.sprinkleColor = tagCompound.getInteger("sprinkleColor");
        this.showsSprinkles = tagCompound.getBoolean("showsSprinkles");
    }

    @Override
    public EnumActionResult onSoftMalletClick(EntityPlayer playerIn, EnumHand hand, CuboidRayTraceResult hitResult) {
        this.showsSprinkles = !this.showsSprinkles;
        if (!this.isRemote()) {
            playerIn.sendMessage(new TextComponentTranslation(showsSprinkles ? "gregtechfoodoption.sprinkler.particles.on" : "gregtechfoodoption.sprinkler.particles.off"));
        }
        return EnumActionResult.SUCCESS;
    }


}
