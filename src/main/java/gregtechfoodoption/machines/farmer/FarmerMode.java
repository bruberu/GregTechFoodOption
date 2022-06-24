package gregtechfoodoption.machines.farmer;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public interface FarmerMode {
    boolean canOperate(IBlockState state, MetaTileEntityFarmer farmer);
    void harvest(IBlockState state, World world, BlockPos pos, MetaTileEntityFarmer farmer);
    List<ItemStack> getDrops(IBlockState state, World world, BlockPos pos, MetaTileEntityFarmer farmer);
}
