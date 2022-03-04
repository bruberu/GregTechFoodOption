package gregtechfoodoption.integration;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.ore.OrePrefix;
import gregtechfoodoption.item.GTFOOredictItem;

import static gregtechfoodoption.GTFOMaterialHandler.fluidBuilder;
import static gregtechfoodoption.item.GTFOMetaItems.SHAPED_ITEM;

public class GTFOAAMaterialHandler {

    // 21990 - 21999

    public static final Material Coffee = fluidBuilder(21990, "coffee")
            .color(0x36312e)
            .build();
    public static final Material EnergizedCoffee = fluidBuilder(21991, "energized_coffee")
            .color(0x695934)
            .build();

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
