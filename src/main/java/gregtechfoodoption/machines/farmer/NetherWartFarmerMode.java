package gregtechfoodoption.machines.farmer;

import net.minecraft.block.BlockNetherWart;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class NetherWartFarmerMode implements FarmerMode {
    public static final int NETHER_WART_MAX_AGE = 3;
    @Override
    public boolean canOperate(IBlockState state, MetaTileEntityFarmer farmer) {
        return state.getBlock() instanceof BlockNetherWart && state.getBlock().getMetaFromState(state) == NETHER_WART_MAX_AGE;
    }

    @Override
    public boolean canPlaceItem(ItemStack stack) {
        return stack.getItem().equals(Items.NETHER_WART);
    }
}
