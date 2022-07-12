package gregtechfoodoption.worldgen.trees;

import gregtech.common.blocks.MetaBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Random;

public class BananaTree extends GTFOTree {
    public BananaTree(BlockStateBase logState, BlockStateBase leavesState, int seed) {
        super(logState, leavesState, seed);
    }

    @Override
    public boolean grow(@Nonnull World world, BlockPos pos, Random random) {
        return false;
    }

    public int getGrowHeight(World world, BlockPos pos) {
        BlockPos below = pos.down();
        IBlockState baseState = world.getBlockState(below);
        Block baseBlock = baseState.getBlock();
        if (baseBlock.isAir(baseState, world, below) ||
                !baseBlock.canSustainPlant(baseState, world, below, EnumFacing.UP, MetaBlocks.RUBBER_SAPLING) || (
                !world.isAirBlock(pos.up()) && world.getBlockState(pos.up()).getBlock() != MetaBlocks.RUBBER_SAPLING))
            return 0;
        int height = 1;
        pos = pos.up();
        while (world.isAirBlock(pos) && height < 7) {
            pos = pos.up();
            height++;
        }
        return height;
    }
}
