package gregtechfoodoption.integration;

import gregtech.api.unification.material.info.MaterialIconSet;
import gregtechfoodoption.item.GTFOOredictItem;
import gregtechfoodoption.material.GTFOFluidMaterial;
import gregtech.api.unification.ore.OrePrefix;

import static gregtechfoodoption.item.GTFOMetaItems.SHAPED_ITEM;

public class GTFOAAMaterialHandler {
    public static final GTFOFluidMaterial Coffee = new GTFOFluidMaterial("coffee", 0x36312e);
    public static final GTFOFluidMaterial EnergizedCoffee = new GTFOFluidMaterial("energized_coffee", 0x695934);

    public static final GTFOOredictItem.OreDictValueItem COFFEE_GROUNDS = SHAPED_ITEM.addOreDictItem(1017, "coffee_grounds",  0x1a1612, MaterialIconSet.DULL, OrePrefix.dust);
    public static final GTFOOredictItem.OreDictValueItem SMALL_ROASTED_COFFEE = SHAPED_ITEM.addOreDictItem(1018, "roasted_coffee_small", 0x1a1612, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem LARGE_ROASTED_COFFEE = SHAPED_ITEM.addOreDictItem(1019, "roasted_coffee_large", 0x1a1612, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem SMALL_GRADED_COFFEE = SHAPED_ITEM.addOreDictItem(1020, "graded_coffee_small", 0x635c55, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem LARGE_GRADED_COFFEE = SHAPED_ITEM.addOreDictItem(1021, "graded_coffee_large", 0x635c55, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem SMALL_HULLED_COFFEE = SHAPED_ITEM.addOreDictItem(1022, "hulled_coffee_small", 0x7d4b16, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem LARGE_HULLED_COFFEE = SHAPED_ITEM.addOreDictItem(1023, "hulled_coffee_large", 0x7d4b16, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem SMALL_DRIED_COFFEE = SHAPED_ITEM.addOreDictItem(1024, "dried_coffee_small", 0x8c6842, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem LARGE_DRIED_COFFEE = SHAPED_ITEM.addOreDictItem(1025, "dried_coffee_large", 0x8c6842, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem SMALL_WET_COFFEE = SHAPED_ITEM.addOreDictItem(1026, "wet_coffee_small", 0x756452, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem LARGE_WET_COFFEE = SHAPED_ITEM.addOreDictItem(1027, "wet_coffee_large", 0x756452, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem SMALL_BASIC_COFFEE = SHAPED_ITEM.addOreDictItem(1028, "basic_coffee_small", 0x3b220d, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem LARGE_BASIC_COFFEE = SHAPED_ITEM.addOreDictItem(1029, "basic_coffee_large", 0x3b220d, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem UNSORTED_BASIC_COFFEE = SHAPED_ITEM.addOreDictItem(1030, "basic_coffee_unsorted", 0x422003, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);

    public static void onMaterialsInit() {

    }
}
