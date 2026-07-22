package gregtechfoodoption.worldgen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkGeneratorFlat;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class GTFOWorldGenerator implements IWorldGenerator {

    public static final GTFOWorldGenerator INSTANCE = new GTFOWorldGenerator();

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator iChunkGenerator,
                         IChunkProvider iChunkProvider) {
        if (!(iChunkGenerator instanceof ChunkGeneratorFlat)) {
            for (GTFOFeature feature : GTFOFeature.FEATURES) {
                feature.setWorld(world);
                feature.getWorldGenInstance().generateInChunk(world, random, chunkX, chunkZ);
            }
        }
    }
}
