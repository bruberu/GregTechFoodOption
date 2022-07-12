package gregtechfoodoption.worldgen.trees;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class GTFOTreeGen extends WorldGenerator {
    public final GTFOTree tree;

    protected GTFOTreeGen(boolean notify, GTFOTree tree) {
        super(notify);
        this.tree = tree;
    }

    @Override
    public boolean generate(@Nonnull World world, @Nonnull Random random, @Nonnull BlockPos pos) {
        return generateImpl(world, random, new BlockPos.MutableBlockPos(pos));
    }

    public boolean generateImpl(@Nonnull World world, @Nonnull Random random, BlockPos.MutableBlockPos pos) {
        return tree.grow(world, pos, random);//grow(world, pos, random);
    }

    public boolean generateInChunk(@Nonnull World world, @Nonnull Random random, int chunkX, int chunkZ) {
        Chunk chunk = world.getChunk(chunkX, chunkZ);
        int seaLevel = chunk.getWorld().getSeaLevel();
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(chunk.getPos().getBlock(8, seaLevel, 8));
        int treeCount = getAmountInChunk(tree.biomeConditions, chunkX, chunkZ, world, pos);
        if (treeCount > 0) {
            for (int j = 0; j < treeCount; j++) {
                // Set up position for tree spawn
                pos.setPos(chunk.x * 16 + random.nextInt(16), 255, chunk.z * 16 + random.nextInt(16));
                while (world.isAirBlock(pos) && pos.getY() != 0) {
                    pos.setY(pos.getY() - 1);
                }
                pos.setY(pos.getY() + 1);
                SaplingGrowTreeEvent event = new SaplingGrowTreeEvent(world, random, pos);
                MinecraftForge.TERRAIN_GEN_BUS.post(event);
                if (event.getResult() == Event.Result.DENY || !this.generateImpl(chunk.getWorld(), random, pos)) {
                    treeCount -= 1;
                }
            }
            return true;
        }
        return false;
    }

    public int getAmountInChunk(List<BiomeCondition> conditions, int chunkX, int chunkZ, World world, BlockPos pos) {
        Biome biome = world.getBiome(pos);
        Optional<BiomeCondition> relevantCondition = conditions.stream().filter(biomeCondition -> biomeCondition.getBiome().equals(biome)).findFirst();
        double treeStrength = tree.getRandomStrength(chunkX, chunkZ);
        if (relevantCondition.isPresent() && relevantCondition.get().getPerlinCutoff() < treeStrength) {
            return (int) Math.ceil((relevantCondition.get().getMaxTrees() - relevantCondition.get().getPerlinCutoff()) / (1 - relevantCondition.get().getPerlinCutoff()));
        }
        return 0;
    }
}