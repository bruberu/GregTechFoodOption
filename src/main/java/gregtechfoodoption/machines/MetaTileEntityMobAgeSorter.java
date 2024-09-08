package gregtechfoodoption.machines;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;
import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import gregtech.api.GTValues;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.ToggleButtonWidget;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.TieredMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtechfoodoption.client.GTFOClientHandler;
import gregtechfoodoption.client.GTFOGuiTextures;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

import static gregtech.api.capability.GregtechDataCodes.IS_WORKING;

public class MetaTileEntityMobAgeSorter extends TieredMetaTileEntity {
    private static final int BASE_EU_CONSUMPTION = 8;
    private boolean isWorking;
    private AxisAlignedBB areaBoundingBox;
    private final int suckingRange;
    private boolean movesAdults;
    private BlockPos areaCenterPos;

    public MetaTileEntityMobAgeSorter(ResourceLocation metaTileEntityId, int tier, int suckingRange) {
        super(metaTileEntityId, tier);
        this.suckingRange = suckingRange;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity holder) {
        return new MetaTileEntityMobAgeSorter(metaTileEntityId, getTier(), suckingRange);
    }

    @Override
    public void update() {
        super.update();

        if (getWorld().isRemote) {
            return;
        }

        boolean isWorkingNow = energyContainer.getEnergyStored() >= getEnergyConsumedPerTick() && isBlockRedstonePowered();
        if (isWorkingNow) {
            energyContainer.removeEnergy(getEnergyConsumedPerTick());
            BlockPos selfPos = getPos();
            if (areaCenterPos == null || areaBoundingBox == null) {
                this.areaCenterPos = selfPos.offset(this.getFrontFacing(), suckingRange);
                this.areaBoundingBox = new AxisAlignedBB(areaCenterPos).grow(suckingRange - 1, 1.0, suckingRange - 1);
            }
            List<EntityLivingBase> animals = this.getWorld().getEntitiesWithinAABB(EntityLivingBase.class, areaBoundingBox);
            animals.removeIf(animal -> animal.isChild() != movesAdults);

            if (!animals.isEmpty()) {
                BlockPos pos = this.getPos().offset(this.getFrontFacing().getOpposite());
                animals.get(0).setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            }
        }

        if (isWorkingNow != isWorking) {
            this.isWorking = isWorkingNow;
            writeCustomData(IS_WORKING, buffer -> buffer.writeBoolean(isWorkingNow));
        }
    }

    protected int getEnergyConsumedPerTick() {
        return BASE_EU_CONSUMPTION * (1 << ((getTier() - 1) * 2));
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeBoolean(isWorking);
        buf.writeBoolean(movesAdults);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.isWorking = buf.readBoolean();
        this.movesAdults = buf.readBoolean();
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == IS_WORKING) {
            this.isWorking = buf.readBoolean();
            getHolder().scheduleRenderUpdate();
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setBoolean("MovesChildren", movesAdults);
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        movesAdults = data.getBoolean("MovesChildren");
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        ModularUI.Builder builder = ModularUI.builder(GuiTextures.BACKGROUND, 150, 50)
                .label(10, 6, getMetaFullName());
        builder.widget(new ToggleButtonWidget(10, 20, 20, 20, this::getAgeFilter, data -> invertFilter())
                .setButtonTexture(GTFOGuiTextures.BUTTON_MOB_SORTER_MODE).setTooltipText("gregtechfoodoption.gui.mob_age_sorter_mode"));
        return builder.build(getHolder(), entityPlayer);
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        OrientedOverlayRenderer renderer = GTFOClientHandler.MOB_AGE_SORTER_OVERLAY;
        renderer.renderOrientedState(renderState, translation, pipeline, Cuboid6.full, this.getFrontFacing(), isWorking, true);
    }

    private void invertFilter() {
        movesAdults = !movesAdults;
    }

    private boolean getAgeFilter() {
        return movesAdults;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, boolean advanced) {
        tooltip.add(I18n.format("gregtechfoodoption.machine.mob_age_sorter.range", suckingRange, suckingRange));
        tooltip.add(I18n.format("gregtech.universal.tooltip.max_voltage_in", energyContainer.getInputVoltage(), GTValues.VNF[getTier()]));
        tooltip.add(I18n.format("gregtech.universal.tooltip.energy_storage_capacity", energyContainer.getEnergyCapacity()));
        tooltip.add(I18n.format("gregtech.universal.tooltip.requires_redstone"));
        tooltip.add(I18n.format("gregtech.universal.tooltip.uses_per_tick", getEnergyConsumedPerTick()));
    }

    @Override
    public ModularPanel buildUI(PosGuiData data, PanelSyncManager syncManager) {
        return null;
    }
}
