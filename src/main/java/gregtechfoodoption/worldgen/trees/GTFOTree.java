package gregtechfoodoption.worldgen.trees;

import net.minecraft.block.state.BlockStateBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.NoiseGeneratorSimplex;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class GTFOTree {
    protected GTFOTreeGen TREE_GROW_INSTANCE;
    protected GTFOTreeGen WORLD_GEN_INSTANCE;

    public final BlockStateBase logState;
    public final BlockStateBase leavesState;

    private NoiseGeneratorSimplex generatorSimplex;
    private int seed;
    public final List<BiomeCondition> biomeConditions = new ArrayList<>();
    public static final List<GTFOTree> TREES = new ArrayList<>();

    public GTFOTree(BlockStateBase logState, BlockStateBase leavesState, int seed) {
        this.logState = logState;
        this.leavesState = leavesState;
        this.seed = seed;
        TREES.add(this);
    }

    public void setWorld(World world) {
        generatorSimplex = new NoiseGeneratorSimplex(new Random(world.getSeed() + seed));
    }

    public double getRandomStrength(int chunkX, int chunkZ) {
        return generatorSimplex.getValue(chunkX * 0.03, chunkZ * 0.03);
    }

    public abstract boolean grow(World world, BlockPos pos, Random random);

    public GTFOTreeGen getTreeGrowInstance() {
        return TREE_GROW_INSTANCE;
    }

    public GTFOTreeGen getWorldGenInstance() {
        return WORLD_GEN_INSTANCE;
    }

    public GTFOTree addCondition(BiomeCondition condition) {
        biomeConditions.add(condition);
        return this;
    }
}
