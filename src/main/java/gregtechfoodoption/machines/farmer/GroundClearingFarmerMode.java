package gregtechfoodoption.machines.farmer;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GroundClearingFarmerMode implements FarmerMode {
    protected final Block crop;

    public GroundClearingFarmerMode(Block crop) {
        this.crop = crop;
    }

    @Override
    public boolean canOperate(IBlockState state, MetaTileEntityFarmer farmer, BlockPos pos, World world) {
        return crop.isAssociatedBlock(state.getBlock());
    }

    @Override
    public boolean canPlaceItem(ItemStack stack) {
        return false;
    }
}
