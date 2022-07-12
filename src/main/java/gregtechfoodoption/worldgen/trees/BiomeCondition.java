package gregtechfoodoption.worldgen.trees;

import net.minecraft.world.biome.Biome;

public class BiomeCondition {
    private final Biome biome;
    private final int maxAmount;
    private final double perlinCutoff;

    public BiomeCondition(Biome biome, int maxAmount, double perlinCutoff) {
        this.biome = biome;
        this.maxAmount = maxAmount;
        this.perlinCutoff = perlinCutoff;
    }

    public Biome getBiome() {
        return biome;
    }

    public double getPerlinCutoff() {
        return perlinCutoff;
    }

    public double getMaxTrees() {
        return maxAmount;
    }
}
