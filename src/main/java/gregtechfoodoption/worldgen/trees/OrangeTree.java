package gregtechfoodoption.worldgen.trees;

import net.minecraft.block.state.BlockStateBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class OrangeTree extends GTFOTree {
    public OrangeTree(BlockStateBase logState, BlockStateBase leavesState, int seed) {
        super(logState, leavesState, seed);
    }

    @Override
    public boolean grow(World world, BlockPos pos, Random random) {

        return false;
    }
}
