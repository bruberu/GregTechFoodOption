package gregtechfoodoption.machines;

import gregtech.api.gui.ModularUI;
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
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.Queue;

public class MetaTileEntityFarmer extends TieredMetaTileEntity {

    private final Queue<BlockPos> operationQueue;
    private final ItemStackHandler seedInput = new ItemStackHandler(18) {
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
    private final ItemStackHandler cropOutput = new ItemStackHandler(18) {
        @Nonnull
        @Override
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            return stack;
        }
    };
    private final int ticksPerAction;

    public MetaTileEntityFarmer(ResourceLocation metaTileEntityId, int tier, int ticksPerAction) {
        super(metaTileEntityId, tier);
        this.ticksPerAction = ticksPerAction;
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityFarmer(metaTileEntityId, getTier(), ticksPerAction);
    }

    @Override
    public void update() {
        super.update();
        if (this.getOffsetTimer() % ticksPerAction != 0)
            return;
        // Phase 1: place down seed if possible
        if(InventoryUtils.getNumberOfEmptySlotsInInventory(seedInput) != 0) {
            ItemStack seedItem = seedInput.extractItem(GTFOUtils.getFirstUnemptyItemSlot(seedInput), 1, true);
            GregFakePlayer placer = new GregFakePlayer(getWorld());
            placer.setHeldItem(EnumHand.MAIN_HAND, seedItem);
            placer.
        }
        // Phase 2: move crop pointer and collect crops
    }

    @Override
    public IItemHandlerModifiable getImportItems() {
        return seedInput;
    }

    @Override
    public IItemHandlerModifiable getExportItems() {
        return cropOutput;
    }

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        return null;
    }
}
