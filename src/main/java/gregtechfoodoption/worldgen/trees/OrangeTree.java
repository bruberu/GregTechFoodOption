package gregtechfoodoption.worldgen.trees;

import gregtechfoodoption.block.GTFOTree;
import net.minecraft.block.state.BlockStateBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class OrangeTree extends GTFOTree {
    public static int LEAVES_COLOR = 0x76c92c;

    public OrangeTree(BlockStateBase logState, BlockStateBase leavesState, int seed) {
        super("orange", 1);
    }

    @Override
    public boolean grow(World world, BlockPos pos, Random random) {

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
}
