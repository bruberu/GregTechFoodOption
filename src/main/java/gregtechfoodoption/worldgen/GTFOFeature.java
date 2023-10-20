package gregtechfoodoption.worldgen;

import gregtech.api.util.function.TriConsumer;
import gregtechfoodoption.utils.GTFOLog;
import gregtechfoodoption.worldgen.condition.FeatureCondition;
import gregtechfoodoption.worldgen.trees.GTFOTree;
import gregtechfoodoption.worldgen.trees.GTFOTreeGen;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.NoiseGeneratorSimplex;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class GTFOFeature {

    private NoiseGeneratorSimplex generatorSimplex;
    private final int seed;
    public final List<FeatureCondition> featureConditions = new ArrayList<>();
    public static final List<GTFOFeature> FEATURES = new ArrayList<>();
    protected GTFOFeatureGen FEATURE_GROW_INSTANCE;
    protected GTFOFeatureGen WORLD_GEN_INSTANCE;

    private int totalChunksChecked;
    private int totalChunksPlaced;
    public GTFOFeature(int seed) {
        this.seed = seed;
        FEATURES.add(this);
    }

    public void setWorld(World world) {
        generatorSimplex = new NoiseGeneratorSimplex(new Random(world.getSeed() + seed));
    }

    public double getRandomStrength(int chunkX, int chunkZ) {
        return generatorSimplex.getValue(chunkX * getPerlinScale(), chunkZ * getPerlinScale());
    }

    public double getPerlinScale() {
        return 0.04;
    }

    // For testing purposes only.
    public void updatePlacePercentage(boolean didSucceed) {
        totalChunksChecked++;
        if (didSucceed) {
            totalChunksPlaced++;
        }
        if (totalChunksChecked % 1000 == 0) {
            GTFOLog.logger.info("Feature " + this + " has been placed successfully in chunks " + ((double) totalChunksPlaced / (totalChunksChecked / 100)) + " percent of the time out of " + totalChunksChecked + " chunks checked");
        }
    }

    public abstract boolean generate(World world, BlockPos.MutableBlockPos pos, Random random, TriConsumer<World, BlockPos, IBlockState> notifier);

    public GTFOFeatureGen getWorldGenInstance() {
        return WORLD_GEN_INSTANCE;
    }

    public GTFOFeature addCondition(FeatureCondition condition) {
        featureConditions.add(condition);
        return this;
    }
}
