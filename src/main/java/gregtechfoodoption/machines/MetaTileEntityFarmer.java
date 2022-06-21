package gregtechfoodoption.machines;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.TieredMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.util.GregFakePlayer;
import gregtech.api.util.InventoryUtils;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtechfoodoption.client.GTFOClientHandler;
import gregtechfoodoption.item.GTFOCropSeedBehaviour;
import gregtechfoodoption.utils.GTFOUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

import static gregtech.api.capability.GregtechDataCodes.IS_WORKING;
import static gregtech.api.capability.GregtechDataCodes.UPDATE_FRONT_FACING;

public class MetaTileEntityFarmer extends TieredMetaTileEntity {

    private final int actionsPerSecond;
    private AxisAlignedBB workingArea;
    private BlockPos operationPosition;
    private static final int LENGTH = 9;
    private boolean isWorking;

    private static final int BASE_EU_CONSUMPTION = 8;

    public MetaTileEntityFarmer(ResourceLocation metaTileEntityId, int tier, int actionsPerSecond) {
        super(metaTileEntityId, tier);
        this.actionsPerSecond = actionsPerSecond;
        this.initializeInventory();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityFarmer(metaTileEntityId, getTier(), actionsPerSecond);
    }

    @Override
    public void update() {
        super.update();
        if (this.getOffsetTimer() % 20 != 0 || this.getWorld().isRemote)
            return;
        boolean isWorkingNow = energyContainer.getEnergyStored() >= getEnergyConsumedPerTick();
        if (isWorkingNow) {
            energyContainer.removeEnergy(getEnergyConsumedPerTick());
            for (int i = 0; i < actionsPerSecond; i++) {
                // Phase 1: move crop pointer and collect crops if there exists enough inventory

                // Phase 2: place down seed if possible
                if (InventoryUtils.getNumberOfEmptySlotsInInventory(getImportItems()) != getImportItems().getSlots()) {
                    int unemptySlot = GTFOUtils.getFirstUnemptyItemSlot(getImportItems());
                    ItemStack seedItem = getImportItems().extractItem(unemptySlot, 1, true);
                    GregFakePlayer placer = new GregFakePlayer(getWorld());
                    placer.setHeldItem(EnumHand.MAIN_HAND, seedItem);
                    EnumActionResult result = seedItem.onItemUse(placer, getWorld(), operationPosition.down(), EnumHand.MAIN_HAND, EnumFacing.UP, 0, 0, 0);
                    if (result == EnumActionResult.SUCCESS)
                        getImportItems().extractItem(unemptySlot, 1, false);
                }
                updateOperationPosition();
            }
        }
        if (isWorkingNow != isWorking) {
            this.isWorking = isWorkingNow;
            writeCustomData(IS_WORKING, buffer -> buffer.writeBoolean(isWorkingNow));
        }
    }

    protected int getEnergyConsumedPerTick() {
        return BASE_EU_CONSUMPTION * (1 << (getTier() - 1));
    }
    private void updateOperationPosition() {
        operationPosition = operationPosition.offset(this.getFrontFacing().rotateYCCW());
        if (!workingArea.contains(new Vec3d(operationPosition.getX(), operationPosition.getY(), operationPosition.getZ()))) {
            operationPosition = operationPosition.offset(this.getFrontFacing().rotateY(), LENGTH).offset(this.getFrontFacing());
            if (!workingArea.contains(new Vec3d(operationPosition.getX(), operationPosition.getY(), operationPosition.getZ()))) {
                operationPosition = this.getPos().offset(this.getFrontFacing()).offset(this.getFrontFacing().rotateY(), LENGTH / 2);
            }
        }
    }

    private void setupWorkingArea() {
        workingArea = new AxisAlignedBB(this.getPos().offset(this.getFrontFacing().getOpposite()).offset(this.getFrontFacing().rotateY(), LENGTH / 2),
                this.getPos().offset(this.getFrontFacing().getOpposite(), LENGTH).offset(this.getFrontFacing().rotateYCCW(), LENGTH / 2))
                .grow(.1);
        operationPosition = this.getPos().offset(this.getFrontFacing()).offset(this.getFrontFacing().rotateY(), LENGTH / 2);
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
    }

    @Override
    public boolean onWrenchClick(EntityPlayer playerIn, EnumHand hand, EnumFacing wrenchSide, CuboidRayTraceResult hitResult) {
        return super.onWrenchClick(playerIn, hand, wrenchSide, hitResult);
    }

    protected IItemHandlerModifiable createImportItemHandler() {
        return new ItemStackHandler(9) {
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if ((stack.getItem() instanceof IPlantable))
                    return true;
                if (stack.getItem() instanceof MetaItem &&
                        ((MetaItem<?>) stack.getItem()).getBehaviours(stack)
                                .stream().anyMatch(behavior -> behavior instanceof GTFOCropSeedBehaviour))
                    return true;
                return false;
            }
        };
    }

    protected IItemHandlerModifiable createExportItemHandler() {
        return new ItemStackHandler(9) {
            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                return stack;
            }
        };
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
    public void onAttached(Object... data) {
        this.setupWorkingArea();
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        OrientedOverlayRenderer renderer = GTFOClientHandler.MOB_AGE_SORTER_OVERLAY;
        renderer.renderOrientedState(renderState, translation, pipeline, Cuboid6.full, this.getFrontFacing(), isWorking, true);
    }
}
