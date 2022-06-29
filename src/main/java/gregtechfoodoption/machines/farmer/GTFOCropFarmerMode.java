package gregtechfoodoption.machines.farmer;

import gregtech.api.items.metaitem.MetaItem;
import gregtechfoodoption.block.GTFOCrop;
import gregtechfoodoption.item.GTFOCropSeedBehaviour;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

public class GTFOCropFarmerMode implements FarmerMode {

    @Override
    public boolean canOperate(IBlockState state, MetaTileEntityFarmer farmer) {
        return state.getBlock() instanceof GTFOCrop && ((GTFOCrop) state.getBlock()).isMaxAge(state);
    }

    @Override
    public boolean canPlaceItem(ItemStack stack) {
        return stack.getItem() instanceof MetaItem<?> && ((MetaItem<?>) stack.getItem()).getItem(stack).getBehaviours().stream().anyMatch(behaviour -> behaviour instanceof GTFOCropSeedBehaviour);
    }
}
