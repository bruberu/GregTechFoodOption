package gregtechfoodoption.worldgen.condition;

import net.minecraft.world.biome.Biome;

public abstract class FeatureCondition {
    private final int maxAmount;


    public FeatureCondition(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public abstract boolean isSatisfied(Biome biome);

    /*
        From my testing, here are the chances compared to the perlinCutoff:

        0.15 - 1/3
        0.85 - 1/100
     */
    public abstract double getPerlinCutoff(Biome biome);

    public double getMaxFeatures() {
        return maxAmount;
    }
}
