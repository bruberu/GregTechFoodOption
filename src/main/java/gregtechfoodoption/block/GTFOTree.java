package gregtechfoodoption.block;

import gregtechfoodoption.block.tree.GTFOBlockLeaves;
import gregtechfoodoption.worldgen.trees.BiomeCondition;
import gregtechfoodoption.worldgen.trees.GTFOTreeGen;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.NoiseGeneratorSimplex;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class GTFOTree {
    public final String name;
    protected GTFOTreeGen TREE_GROW_INSTANCE;
    protected GTFOTreeGen WORLD_GEN_INSTANCE;

    public IBlockState logState;
    public IBlockState leavesState;

    private NoiseGeneratorSimplex generatorSimplex;
    private int seed;
    public final List<BiomeCondition> biomeConditions = new ArrayList<>();
    public static final List<GTFOTree> TREES = new ArrayList<>();

    public GTFOTree(String name, int seed) {
        this.name = name;
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

    public void setupBlocks() {
        GTFOBlockLeaves leaves = GTFOMetaBlocks.GTFO_LEAVES.get(seed / 4);
        this.leavesState = leaves.getStateFromMeta(seed % 4 << 2);
    }

    public abstract int getBlockColor(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex);
    public abstract int getItemColor(ItemStack stack, int tintIndex);

}
