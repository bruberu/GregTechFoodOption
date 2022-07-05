package gregtechfoodoption.machines.farmer;

import gregtechfoodoption.utils.GTFOUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;

import java.util.List;

public class HeightCropFarmerMode extends CustomCropFarmerMode {

    public HeightCropFarmerMode(Block crop, Item seed) {
        super(crop, seed);
    }

    @Override
    public boolean canOperate(IBlockState state, MetaTileEntityFarmer farmer, BlockPos pos, World world) {
        return world.getBlockState(pos.up()).getBlock().isAssociatedBlock(crop);
    }

    @Override
    public List<ItemStack> getDrops(IBlockState state, World world, MutableBlockPos pos, MetaTileEntityFarmer farmer) {
        NonNullList<ItemStack> drops = NonNullList.create();
        MutableBlockPos reedTester = GTFOUtils.copy(pos.up());
        for (; world.getBlockState(reedTester).getBlock().isAssociatedBlock(crop); reedTester.move(EnumFacing.UP)) {
            crop.getDrops(drops, world, pos, state, 0);
        }
        return drops;
    }

    @Override
    public void harvest(IBlockState state, World world, MutableBlockPos pos, MetaTileEntityFarmer farmer) {
        MutableBlockPos reedTester = GTFOUtils.copy(pos.up());
        while (world.getBlockState(reedTester).getBlock().isAssociatedBlock(crop)) {
            reedTester.move(EnumFacing.UP);
        }
        reedTester.move(EnumFacing.DOWN);
        for (; !reedTester.equals(pos); reedTester.move(EnumFacing.DOWN)) {
            world.playEvent(2001, reedTester, Block.getStateId(state));
            world.setBlockToAir(reedTester);
        }

    }
}
