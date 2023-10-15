package gregtechfoodoption.worldgen;

import gregtech.common.ConfigHolder;
import gregtechfoodoption.worldgen.condition.FeatureCondition;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.WorldGenerator;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public abstract class GTFOFeatureGen extends WorldGenerator {
    public final GTFOFeature feature;

    protected GTFOFeatureGen(boolean notify, GTFOFeature feature) {
        super(notify);
        this.feature = feature;
    }

    @Override
    public boolean generate(@Nonnull World world, @Nonnull Random random, @Nonnull BlockPos pos) {
        return generateImpl(world, random, new BlockPos.MutableBlockPos(pos));
    }

    public void setBlockSafely(World worldIn, BlockPos pos, IBlockState state) {
        if (worldIn.getBlockState(pos).getBlock().canBeReplacedByLeaves(worldIn.getBlockState(pos), worldIn, pos)) { // I assume here, for my sanity, that all wood blocks are already accounted for.
            setBlockAndNotifyAdequately(worldIn, pos, state);
        }
    }

    public int getAmountInChunk(List<FeatureCondition> conditions, int chunkX, int chunkZ, World world, BlockPos pos) {
        Biome biome = world.getBiome(pos);
        Optional<FeatureCondition> relevantCondition = conditions.stream().filter(biomeCondition -> biomeCondition.isSatisfied(biome)).findFirst();
        double treeStrength = feature.getRandomStrength(chunkX, chunkZ);
        if (!ConfigHolder.misc.debug) {
            if (relevantCondition.isPresent() && relevantCondition.get().getPerlinCutoff(biome) < treeStrength) {
                double perlinCutoff = relevantCondition.get().getPerlinCutoff(biome);
                double maxTrees = relevantCondition.get().getMaxFeatures();
                return (int) Math.ceil(maxTrees - perlinCutoff * maxTrees);
            }
        } else {
            if (relevantCondition.isPresent()) {
                if (relevantCondition.get().getPerlinCutoff(biome) < treeStrength) {
                    feature.updatePlacePercentage(true);
                    double perlinCutoff = relevantCondition.get().getPerlinCutoff(biome);
                    double maxTrees = relevantCondition.get().getMaxFeatures();
                    return (int) Math.ceil(maxTrees - perlinCutoff * maxTrees);
                } else {
                    feature.updatePlacePercentage(false);
                }
            }
        }
        return 0;
    }

    public boolean generateInChunk(@Nonnull World world, @Nonnull Random random, int chunkX, int chunkZ) {
        Chunk chunk = world.getChunk(chunkX, chunkZ);
        int seaLevel = chunk.getWorld().getSeaLevel();
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(chunk.getPos().getBlock(8, seaLevel, 8));
        int featureCount = getAmountInChunk(feature.featureConditions, chunkX, chunkZ, world, pos);
        if (featureCount > 0) {
            for (int j = 0; j < featureCount; j++) {
                // Set up position for tree spawn, offset by 8 to prevent cascading
                pos.setPos(chunk.x * 16 + random.nextInt(16) + 8, 255, chunk.z * 16 + random.nextInt(16) + 8);
                while (world.isAirBlock(pos) && pos.getY() != 0) {
                    pos.setY(pos.getY() - 1);
                }
                pos.setY(pos.getY() + 1);
                if (!this.generateImpl(chunk.getWorld(), random, pos)) {
                    featureCount -= 1;
                }
            }
            return true;
        }
        return false;
    }

    public abstract boolean generateImpl(World world, Random random, BlockPos.MutableBlockPos pos);
}
