package gregtechfoodoption.integration;

import gregtech.api.unification.material.info.MaterialIconSet;
import gregtechfoodoption.item.GTFOOredictItem;
import gregtechfoodoption.material.GTFOFluidMaterial;
import gregtech.api.unification.ore.OrePrefix;

import static gregtech.api.unification.material.Materials.*;
import static gregtechfoodoption.item.GTFOMetaItems.SHAPED_ITEM;
import static gregtechfoodoption.utils.RecipeUtils.average;

public class GTFONCMaterialHandler {

    public static final GTFOFluidMaterial SweetenedDilutedCaneSyrupMixture = new GTFOFluidMaterial("sweetened_diluted_cane_syrup_mixture", 0xdedcc8);
    public static final GTFOFluidMaterial MarshmallowSyrupMixture = new GTFOFluidMaterial("marshmallow_syrup_mixture", 0xe6e0dc);
    public static final GTFOFluidMaterial MarshmallowFoam = new GTFOFluidMaterial("marshmallow_foam", 0xe6e0dc);
    public static final GTFOFluidMaterial SodiumCarbonateSolution = new GTFOFluidMaterial("sodium_carbonate_solution", average(2, 0xaaaaaa, SodaAsh.getMaterialRGB()));


    public static final GTFOOredictItem.OreDictValueItem COCOA_HULL = SHAPED_ITEM.addOreDictItem( 1035, "cocoa_hull", 0x362c25, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem COCOA_NIB = SHAPED_ITEM.addOreDictItem(1036, "cocoa_nib", 0x635943, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem CHOCOLATE_LIQUOR = SHAPED_ITEM.addOreDictItem(1037, "chocolate_liquor", 0x635943, MaterialIconSet.DULL, OrePrefix.crushedCentrifuged);
    public static final GTFOOredictItem.OreDictValueItem CHOCOLATE_LIQUOR_REFINED = SHAPED_ITEM.addOreDictItem(1038, "chocolate_liquor_refined", 0x605528, MaterialIconSet.DULL, OrePrefix.crushedCentrifuged);
    public static final GTFOOredictItem.OreDictValueItem CHOCOLATE_LIQUOR_DUTCHED = SHAPED_ITEM.addOreDictItem(1039, "chocolate_liquor_dutched", 0x695143, MaterialIconSet.DULL, OrePrefix.crushedCentrifuged);
    public static final GTFOOredictItem.OreDictValueItem PRESS_CAKE_DUTCHED = SHAPED_ITEM.addOreDictItem(1040, "press_cake_dutched", 0x997e6e, MaterialIconSet.DULL, OrePrefix.plateDense);
    public static final GTFOOredictItem.OreDictValueItem PRESS_CAKE = SHAPED_ITEM.addOreDictItem(1041, "press_cake", 0x948881, MaterialIconSet.DULL, OrePrefix.plateDense);
    public static final GTFOOredictItem.OreDictValueItem PRESS_CAKE_GRADED = SHAPED_ITEM.addOreDictItem(1042, "press_cake_graded", 0xa67f68, MaterialIconSet.DULL, OrePrefix.plateDense);
    public static final GTFOOredictItem.OreDictValueItem HOT_MILK_CHOCOLATE = SHAPED_ITEM.addOreDictItem(1043, "milk_chocolate", 0x636c61, MaterialIconSet.DULL, OrePrefix.ingotHot);
    public static final GTFOOredictItem.OreDictValueItem MATTER_MARSHMALLOW = SHAPED_ITEM.addOreDictItem(1044, "matter_marshmallow", 0xe6e0dc, MaterialIconSet.DULL, OrePrefix.crushedCentrifuged);
    public static final GTFOOredictItem.OreDictValueItem MATTER_GRAHAM = SHAPED_ITEM.addOreDictItem(1045, "matter_graham", 0xf0c25d, MaterialIconSet.DULL, OrePrefix.crushedCentrifuged);
    public static final GTFOOredictItem.OreDictValueItem MATTER_GRAHAM_HOT = SHAPED_ITEM.addOreDictItem(1046, "matter_graham_hot", 0xffe1a1, MaterialIconSet.DULL, OrePrefix.crushedCentrifuged);
    public static final GTFOOredictItem.OreDictValueItem CHUNK_GRAHAM_HOT = SHAPED_ITEM.addOreDictItem(1047, "matter_graham_hot", 0xffe1a1, MaterialIconSet.DULL, OrePrefix.plateDense);
    public static final GTFOOredictItem.OreDictValueItem WAFER_GRAHAM_HOT = SHAPED_ITEM.addOreDictItem(1048, "matter_graham_hot", 0xffe1a1, MaterialIconSet.DULL, OrePrefix.plate);
    public static final GTFOOredictItem.OreDictValueItem CRACKER_GRAHAM_UNGRADED = SHAPED_ITEM.addOreDictItem(1049, "cracker_graham_ungraded", 0xf0c25d, MaterialIconSet.DULL, OrePrefix.plate);
    public static final GTFOOredictItem.OreDictValueItem CHOCOLATE_LIQUOR_PRESSED = SHAPED_ITEM.addOreDictItem(1050, "chocolate_liquor_pressed", 0xa6795a, MaterialIconSet.DULL, OrePrefix.crushedCentrifuged);
    public static final GTFOOredictItem.OreDictValueItem CHOCOLATE_LIQUOR_DUTCHED_PRESSED = SHAPED_ITEM.addOreDictItem(1051, "chocolate_liquor_dutched_pressed", 0xab7550, MaterialIconSet.DULL, OrePrefix.crushedCentrifuged);


    public static void onMaterialsInit() {
    }
}
