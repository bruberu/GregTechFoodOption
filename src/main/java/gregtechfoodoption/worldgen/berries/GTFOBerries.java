package gregtechfoodoption.worldgen.berries;

import gregtechfoodoption.block.GTFOBerryBush;
import gregtechfoodoption.block.GTFOCrops;
import gregtechfoodoption.worldgen.condition.BiomeCondition;
import gregtechfoodoption.worldgen.condition.TemperatureRainfallCondition;

public class GTFOBerries {
    public static GTFOBerry BUSH_BLUEBERRY = new GTFOBerry(1000, GTFOCrops.BUSH_BLUEBERRY)
            .addCondition(new TemperatureRainfallCondition(3, ));
    public static GTFOBerry BUSH_BLACKBERRY = new GTFOBerry(1001, GTFOCrops.BUSH_BLACKBERRY);
    public static GTFOBerry BUSH_RASPBERRY = new GTFOBerry(1002, GTFOCrops.BUSH_RASPBERRY);
    public static GTFOBerry BUSH_STRAWBERRY = new GTFOBerry(1003, GTFOCrops.BUSH_STRAWBERRY);
    public static GTFOBerry BUSH_RED_CURRANT = new GTFOBerry(1004, GTFOCrops.BUSH_RED_CURRANT);
    public static GTFOBerry BUSH_BLACK_CURRANT = new GTFOBerry(1005, GTFOCrops.BUSH_BLACK_CURRANT);
    public static GTFOBerry BUSH_WHITE_CURRANT = new GTFOBerry(1006, GTFOCrops.BUSH_WHITE_CURRANT);
    public static GTFOBerry BUSH_LINGONBERRY = new GTFOBerry(1007, GTFOCrops.BUSH_LINGONBERRY);
    public static GTFOBerry BUSH_ELDERBERRY = new GTFOBerry(1008, GTFOCrops.BUSH_ELDERBERRY);
    public static GTFOBerry BUSH_CRANBERRY = new GTFOBerry(1009, GTFOCrops.BUSH_CRANBERRY);

}
