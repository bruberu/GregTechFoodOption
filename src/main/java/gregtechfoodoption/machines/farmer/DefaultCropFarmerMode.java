package gregtechfoodoption.machines.farmer;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public abstract class DefaultCropFarmerMode implements FarmerMode {
    @Override
    public void harvest(IBlockState state, World world, BlockPos pos, MetaTileEntityFarmer farmer) {
        world.playEvent(2001, pos, Block.getStateId(state));
        world.setBlockToAir(pos);
    }

    @Override
    public List<ItemStack> getDrops(IBlockState state, World world, BlockPos pos, MetaTileEntityFarmer farmer) {
        NonNullList<ItemStack> drops = NonNullList.create();
        state.getBlock().getDrops(drops, world, pos, state,0);
        return drops;
    }
}
