package gregtechfoodoption.worldgen.trees;

import gregtech.common.blocks.MetaBlocks;
import gregtechfoodoption.block.GTFOTree;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Random;

public class BananaTree extends GTFOTree {

    public static int LEAVES_COLOR = 0x51993c;
    public BananaTree() {
        super("banana", 0);
    }

    @Override
    public boolean grow(@Nonnull World world, BlockPos pos, Random random) {
        return false;
    }

    @Override
    public int getBlockColor(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
        return LEAVES_COLOR;
    }

    @Override
    public int getItemColor(ItemStack stack, int tintIndex) {
        return LEAVES_COLOR;
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
