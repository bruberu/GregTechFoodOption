package bruberu.gregtechfoodoption.integration;


import bruberu.gregtechfoodoption.item.GTFOOredictItem;
import bruberu.gregtechfoodoption.material.GTFOFluidMaterial;
import gregtech.api.unification.material.IMaterialHandler;
import gregtech.api.unification.material.MaterialIconSet;
import gregtech.api.unification.ore.OrePrefix;

@IMaterialHandler.RegisterMaterialHandler
public class GTFOAAMaterialHandler implements IMaterialHandler{
    public static final GTFOFluidMaterial Coffee = new GTFOFluidMaterial("coffee", 0x36312e);
    public static final GTFOFluidMaterial EnergizedCoffee = new GTFOFluidMaterial("energized_coffee", 0x695934);

    public static final GTFOOredictItem.OreDictItem COFFEE_GROUNDS = new GTFOOredictItem.OreDictItem(1017, "coffee_grounds",  0x1a1612, MaterialIconSet.DULL, OrePrefix.dust);
    public static final GTFOOredictItem.OreDictItem SMALL_ROASTED_COFFEE = new GTFOOredictItem.OreDictItem(1018, "roasted_coffee_small", 0x1a1612, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem LARGE_ROASTED_COFFEE = new GTFOOredictItem.OreDictItem(1019, "roasted_coffee_large", 0x1a1612, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem SMALL_GRADED_COFFEE = new GTFOOredictItem.OreDictItem(1020, "graded_coffee_small", 0x635c55, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem LARGE_GRADED_COFFEE = new GTFOOredictItem.OreDictItem(1021, "graded_coffee_large", 0x635c55, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem SMALL_HULLED_COFFEE = new GTFOOredictItem.OreDictItem(1022, "hulled_coffee_small", 0x7d4b16, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem LARGE_HULLED_COFFEE = new GTFOOredictItem.OreDictItem(1023, "hulled_coffee_large", 0x7d4b16, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem SMALL_DRIED_COFFEE = new GTFOOredictItem.OreDictItem(1024, "dried_coffee_small", 0x8c6842, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem LARGE_DRIED_COFFEE = new GTFOOredictItem.OreDictItem(1025, "dried_coffee_large", 0x8c6842, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem SMALL_WET_COFFEE = new GTFOOredictItem.OreDictItem(1026, "wet_coffee_small", 0x756452, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem LARGE_WET_COFFEE = new GTFOOredictItem.OreDictItem(1027, "wet_coffee_large", 0x756452, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem SMALL_BASIC_COFFEE = new GTFOOredictItem.OreDictItem(1028, "basic_coffee_small", 0x3b220d, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem LARGE_BASIC_COFFEE = new GTFOOredictItem.OreDictItem(1029, "basic_coffee_large", 0x3b220d, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem UNSORTED_BASIC_COFFEE = new GTFOOredictItem.OreDictItem(1030, "basic_coffee_unsorted", 0x422003, MaterialIconSet.GEM_VERTICAL, OrePrefix.gemChipped);

    @Override
    public void onMaterialsInit() {

    }
}
