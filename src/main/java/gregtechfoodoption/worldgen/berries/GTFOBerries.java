package gregtechfoodoption.worldgen.berries;

import gregtechfoodoption.block.GTFOCrops;
import gregtechfoodoption.worldgen.GTFOFeature;
import gregtechfoodoption.worldgen.condition.TemperatureRainfallCondition;

public class GTFOBerries {
    public static GTFOFeature BUSH_BLUEBERRY = new GTFOBerry(1000, GTFOCrops.BUSH_BLUEBERRY)
            .addCondition(new TemperatureRainfallCondition(3, 1.2, 0.7, 0.5, 0.5));
    public static GTFOFeature BUSH_BLACKBERRY = new GTFOBerry(1001, GTFOCrops.BUSH_BLACKBERRY)
            .addCondition(new TemperatureRainfallCondition(3, 1.2, 0.5, 0.4, 0.5));
    public static GTFOFeature BUSH_RASPBERRY = new GTFOBerry(1002, GTFOCrops.BUSH_RASPBERRY)
            .addCondition(new TemperatureRainfallCondition(3, 1.2, 0.5, 0.5, 0.4));
    public static GTFOFeature BUSH_STRAWBERRY = new GTFOBerry(1003, GTFOCrops.BUSH_STRAWBERRY)
            .addCondition(new TemperatureRainfallCondition(3, 1.2, 0.7, 0.8, 0.5));
    public static GTFOFeature BUSH_RED_CURRANT = new GTFOBerry(1004, GTFOCrops.BUSH_RED_CURRANT)
            .addCondition(new TemperatureRainfallCondition(3, 0.9, 0.3, 0.75, 0.5));
    public static GTFOFeature BUSH_BLACK_CURRANT = new GTFOBerry(1005, GTFOCrops.BUSH_BLACK_CURRANT)
            .addCondition(new TemperatureRainfallCondition(3, 0.9, 0.3, 0.75, 0.5));
    public static GTFOFeature BUSH_WHITE_CURRANT = new GTFOBerry(1006, GTFOCrops.BUSH_WHITE_CURRANT)
            .addCondition(new TemperatureRainfallCondition(3, 0.9, 0.3, 0.75, 0.5));
    public static GTFOFeature BUSH_LINGONBERRY = new GTFOBerry(1007, GTFOCrops.BUSH_LINGONBERRY)
            .addCondition(new TemperatureRainfallCondition(3, 0.9, 0.25, 0.7, 0.6));
    public static GTFOFeature BUSH_ELDERBERRY = new GTFOBerry(1008, GTFOCrops.BUSH_ELDERBERRY)
            .addCondition(new TemperatureRainfallCondition(3, 0.9, 0.2, 0.4, 0.6));
    public static GTFOFeature BUSH_CRANBERRY = new GTFOBerry(1009, GTFOCrops.BUSH_CRANBERRY)
            .addCondition(new TemperatureRainfallCondition(3, 1.2, 0.2, 0.4, 0.6));

    public static void init() {

    }
}
