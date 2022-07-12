package gregtechfoodoption.worldgen;

import gregtechfoodoption.worldgen.trees.GTFOTreeGen;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GTFOWorldGenerator implements IWorldGenerator {

    public static final GTFOWorldGenerator INSTANCE = new GTFOWorldGenerator();

    public final List<GTFOTreeGen> treeGens = new ArrayList<>();

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator iChunkGenerator, IChunkProvider iChunkProvider) {
        for (GTFOTreeGen treeGen : treeGens) {
            treeGen.generateInChunk(world, random, chunkX, chunkZ);
        }
    }
}
