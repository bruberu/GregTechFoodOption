package gregtechfoodoption.worldgen.trees.condition;

import net.minecraft.world.biome.Biome;

import java.util.Arrays;

public class BiomeCondition extends TreeCondition {
    private Biome[] biomes;
    private double perlinCutoff;

    public BiomeCondition(Biome[] biomes, int maxAmount, double perlinCutoff) {
        super(maxAmount);
        this.biomes = biomes;
        this.perlinCutoff = perlinCutoff;
    }

    public BiomeCondition(Biome biome, int maxAmount, double perlinCutoff) {
        this(new Biome[]{biome}, maxAmount, perlinCutoff);
    }

    @Override
    public boolean isSatisfied(Biome biome) {
        return Arrays.stream(biomes).anyMatch(aBiome -> aBiome == biome);
    }

    @Override
    public double getPerlinCutoff(Biome biome) {
        return perlinCutoff;
    }
}
