package gregtechfoodoption.integration;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.ore.OrePrefix;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.item.GTFOOredictItem;

import static gregtech.api.unification.material.Materials.SodaAsh;
import static gregtechfoodoption.GTFOMaterialHandler.fluidBuilder;
import static gregtechfoodoption.item.GTFOMetaItems.SHAPED_ITEM;
import static gregtechfoodoption.utils.GTFOUtils.averageRGB;

public class GTFONCMaterialHandler {

    // 21970 - 21989

    public static final Material SweetenedDilutedCaneSyrupMixture = fluidBuilder(21970, "sweetened_diluted_cane_syrup_mixture")
            .color(0xdedcc8)
            .build();
    public static final Material MarshmallowSyrupMixture = fluidBuilder(21971, "marshmallow_syrup_mixture")
            .color(0xe6e0dc)
            .build();
    public static final Material MarshmallowFoam = fluidBuilder(21972, "marshmallow_foam")
            .color(0xe6e0dc)
            .build();
    public static final Material SodiumCarbonateSolution = fluidBuilder(21973, "sodium_carbonate_solution")
            .color(averageRGB(2, 0xaaaaaa, SodaAsh.getMaterialRGB()))
            .build()
            .setFormula("Na2CO3");

    public static final GTFOOredictItem.OreDictValueItem COCOA_HULL = SHAPED_ITEM.addOreDictItem(1035, "cocoa_hull", 0x362c25, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem COCOA_NIB = SHAPED_ITEM.addOreDictItem(1036, "cocoa_nib", 0x635943, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem CHOCOLATE_LIQUOR = SHAPED_ITEM.addOreDictItem(1037, "chocolate_liquor", 0x635943, GTFOValues.Organic, OrePrefix.crushed);
    public static final GTFOOredictItem.OreDictValueItem CHOCOLATE_LIQUOR_REFINED = SHAPED_ITEM.addOreDictItem(1038, "chocolate_liquor_refined", 0x605528, GTFOValues.Organic, OrePrefix.crushed);
    public static final GTFOOredictItem.OreDictValueItem CHOCOLATE_LIQUOR_DUTCHED = SHAPED_ITEM.addOreDictItem(1039, "chocolate_liquor_dutched", 0x695143, GTFOValues.Organic, OrePrefix.crushed);
    public static final GTFOOredictItem.OreDictValueItem PRESS_CAKE_DUTCHED = SHAPED_ITEM.addOreDictItem(1040, "press_cake_dutched", 0x997e6e, MaterialIconSet.DULL, OrePrefix.plateDense);
    public static final GTFOOredictItem.OreDictValueItem PRESS_CAKE = SHAPED_ITEM.addOreDictItem(1041, "press_cake", 0x948881, MaterialIconSet.DULL, OrePrefix.plateDense);
    public static final GTFOOredictItem.OreDictValueItem PRESS_CAKE_GRADED = SHAPED_ITEM.addOreDictItem(1042, "press_cake_graded", 0xa67f68, MaterialIconSet.DULL, OrePrefix.plateDense);
    public static final GTFOOredictItem.OreDictValueItem HOT_MILK_CHOCOLATE = SHAPED_ITEM.addOreDictItem(1043, "milk_chocolate", 0x636c61, MaterialIconSet.DULL, OrePrefix.ingotHot);
    public static final GTFOOredictItem.OreDictValueItem MATTER_MARSHMALLOW = SHAPED_ITEM.addOreDictItem(1044, "matter_marshmallow", 0xe6e0dc, GTFOValues.Organic, OrePrefix.crushed);
    public static final GTFOOredictItem.OreDictValueItem MATTER_GRAHAM = SHAPED_ITEM.addOreDictItem(1045, "matter_graham", 0xf0c25d, GTFOValues.Organic, OrePrefix.crushed);
    public static final GTFOOredictItem.OreDictValueItem MATTER_GRAHAM_HOT = SHAPED_ITEM.addOreDictItem(1046, "matter_graham_hot", 0xffe1a1, GTFOValues.Organic, OrePrefix.crushed);
    public static final GTFOOredictItem.OreDictValueItem CHUNK_GRAHAM_HOT = SHAPED_ITEM.addOreDictItem(1047, "matter_graham_hot", 0xffe1a1, MaterialIconSet.DULL, OrePrefix.plateDense);
    public static final GTFOOredictItem.OreDictValueItem WAFER_GRAHAM_HOT = SHAPED_ITEM.addOreDictItem(1048, "matter_graham_hot", 0xffe1a1, MaterialIconSet.DULL, OrePrefix.plate);
    public static final GTFOOredictItem.OreDictValueItem CRACKER_GRAHAM_UNGRADED = SHAPED_ITEM.addOreDictItem(1049, "cracker_graham_ungraded", 0xf0c25d, MaterialIconSet.DULL, OrePrefix.plate);
    public static final GTFOOredictItem.OreDictValueItem CHOCOLATE_LIQUOR_PRESSED = SHAPED_ITEM.addOreDictItem(1050, "chocolate_liquor_pressed", 0xa6795a, GTFOValues.Organic, OrePrefix.crushed);
    public static final GTFOOredictItem.OreDictValueItem CHOCOLATE_LIQUOR_DUTCHED_PRESSED = SHAPED_ITEM.addOreDictItem(1051, "chocolate_liquor_dutched_pressed", 0xab7550, GTFOValues.Organic, OrePrefix.crushed);


    public static void onMaterialsInit() {
    }
}
