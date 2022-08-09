package gregtechfoodoption.worldgen.trees;

import net.minecraft.world.biome.Biome;

public class BiomeCondition {
    private final Biome biome;
    private final int maxAmount;
    private final double perlinCutoff;

    /*
    From my testing, here are the chances compared to the perlinCutoff:

    0.15 - 1/3
    0.85 - 1/100
     */
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
