package gregtechfoodoption.worldgen.trees.condition;

import net.minecraft.world.biome.Biome;

public class TemperatureRainfallCondition extends TreeCondition {
    private double optimalTemp;
    private double optimalRain;
    private double range;
    private double commonality;

    public TemperatureRainfallCondition(int maxAmount, double commonality, double optimalTemp, double optimalRain, double range) {
        super(maxAmount);
        this.optimalTemp = optimalTemp;
        this.optimalRain = optimalRain;
        this.range = range;
        this.commonality = commonality;
    }

    private double getHabitation(Biome biome) {
        return range - Math.sqrt(Math.pow(biome.getDefaultTemperature() - optimalTemp, 2) + Math.pow(biome.getRainfall() - optimalRain, 2));
    }

    @Override
    public boolean isSatisfied(Biome biome) {
        return getHabitation(biome) > 0;
    }

    @Override
    public double getPerlinCutoff(Biome biome) {
        return 1 - (getHabitation(biome) * commonality);
    }
}
