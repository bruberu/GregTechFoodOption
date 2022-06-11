package gregtechfoodoption.machines;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.TieredMetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.util.GregFakePlayer;
import gregtech.api.util.InventoryUtils;
import gregtechfoodoption.item.GTFOCropSeedBehaviour;
import gregtechfoodoption.utils.GTFOUtils;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

import static gregtech.api.capability.GregtechDataCodes.UPDATE_FRONT_FACING;

public class MetaTileEntityFarmer extends TieredMetaTileEntity {

    private final int actionsPerSecond;
    private AxisAlignedBB workingArea;
    private BlockPos operationPosition;
    private static final int LENGTH = 9;

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
        if (this.getOffsetTimer() % 20 != 0)
            return;
        for (int i = 0; i < actionsPerSecond; i++) {
            // Phase 1: move crop pointer and collect crops if there exists enough inventory

            // Phase 2: place down seed if possible
            if (InventoryUtils.getNumberOfEmptySlotsInInventory(getImportItems()) != 0) {
                ItemStack seedItem = getImportItems().extractItem(GTFOUtils.getFirstUnemptyItemSlot(getImportItems()), 1, true);
                GregFakePlayer placer = new GregFakePlayer(getWorld());
                placer.setHeldItem(EnumHand.MAIN_HAND, seedItem);
                seedItem.onItemUse(placer, getWorld(), operationPosition, EnumHand.MAIN_HAND, EnumFacing.DOWN, 0, 0, 0);
            }
            updateOperationPosition();
        }
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
        workingArea = new AxisAlignedBB(this.getPos().offset(this.getFrontFacing()).offset(this.getFrontFacing().rotateY(), LENGTH / 2),
                this.getPos().offset(this.getFrontFacing(), LENGTH).offset(this.getFrontFacing().rotateYCCW(), LENGTH / 2));
        operationPosition = this.getPos().offset(this.getFrontFacing()).offset(this.getFrontFacing().rotateY(), LENGTH / 2);
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == UPDATE_FRONT_FACING) {
            setupWorkingArea();
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
                if ((stack.getItem() instanceof IGrowable))
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
}
