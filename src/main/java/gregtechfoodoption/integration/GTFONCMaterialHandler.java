package gregtechfoodoption.integration;

import gregtech.api.unification.material.info.MaterialIconSet;
import gregtechfoodoption.item.GTFOOredictItem;
import gregtechfoodoption.material.GTFOFluidMaterial;
import gregtech.api.unification.ore.OrePrefix;

public class GTFONCMaterialHandler {

    public static final GTFOFluidMaterial SweetenedDilutedCaneSyrupMixture = new GTFOFluidMaterial("sweetened_diluted_cane_syrup_mixture", 0xdedcc8);
    public static final GTFOFluidMaterial Albumen = new GTFOFluidMaterial("albumen", 0xfffef7);
    public static final GTFOFluidMaterial Yolk = new GTFOFluidMaterial("yolk", 0xffdf00);
    public static final GTFOFluidMaterial MarshmallowSyrupMixture = new GTFOFluidMaterial("marshmallow_syrup_mixture", 0xe6e0dc);
    public static final GTFOFluidMaterial MarshmallowFoam = new GTFOFluidMaterial("marshmallow_foam", 0xe6e0dc);
    public static final GTFOFluidMaterial Butter = new GTFOFluidMaterial("butter", 0xffef82);
    public static final GTFOFluidMaterial PotassiumCarbonateSolution = new GTFOFluidMaterial("potassium_carbonate_solution", 0xfafafa);


    public static final GTFOOredictItem.OreDictItem COCOA_HULL = new GTFOOredictItem.OreDictItem( 1035, "cocoa_hull", 0x362c25, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem COCOA_NIB = new GTFOOredictItem.OreDictItem(1036, "cocoa_nib", 0x635943, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem CHOCOLATE_LIQUOR = new GTFOOredictItem.OreDictItem(1037, "chocolate_liquor", 0x635943, MaterialIconSet.DULL, OrePrefix.crushedCentrifuged);
    public static final GTFOOredictItem.OreDictItem CHOCOLATE_LIQUOR_REFINED = new GTFOOredictItem.OreDictItem(1038, "chocolate_liquor_refined", 0x605528, MaterialIconSet.DULL, OrePrefix.crushedCentrifuged);
    public static final GTFOOredictItem.OreDictItem CHOCOLATE_LIQUOR_DUTCHED = new GTFOOredictItem.OreDictItem(1039, "chocolate_liquor_dutched", 0x695143, MaterialIconSet.DULL, OrePrefix.crushedCentrifuged);
    public static final GTFOOredictItem.OreDictItem PRESS_CAKE_DUTCHED = new GTFOOredictItem.OreDictItem(1040, "press_cake_dutched", 0x997e6e, MaterialIconSet.DULL, OrePrefix.plateDense);
    public static final GTFOOredictItem.OreDictItem PRESS_CAKE = new GTFOOredictItem.OreDictItem(1041, "press_cake", 0x948881, MaterialIconSet.DULL, OrePrefix.plateDense);
    public static final GTFOOredictItem.OreDictItem PRESS_CAKE_GRADED = new GTFOOredictItem.OreDictItem(1042, "press_cake_graded", 0xa67f68, MaterialIconSet.DULL, OrePrefix.plateDense);
    public static final GTFOOredictItem.OreDictItem HOT_MILK_CHOCOLATE = new GTFOOredictItem.OreDictItem(1043, "milk_chocolate", 0x636c61, MaterialIconSet.DULL, OrePrefix.ingotHot);
    public static final GTFOOredictItem.OreDictItem MATTER_MARSHMALLOW = new GTFOOredictItem.OreDictItem(1044, "matter_marshmallow", 0xe6e0dc, MaterialIconSet.DULL, OrePrefix.crushedCentrifuged);
    public static final GTFOOredictItem.OreDictItem MATTER_GRAHAM = new GTFOOredictItem.OreDictItem(1045, "matter_graham", 0xf0c25d, MaterialIconSet.DULL, OrePrefix.crushedCentrifuged);
    public static final GTFOOredictItem.OreDictItem MATTER_GRAHAM_HOT = new GTFOOredictItem.OreDictItem(1046, "matter_graham_hot", 0xffe1a1, MaterialIconSet.DULL, OrePrefix.crushedCentrifuged);
    public static final GTFOOredictItem.OreDictItem CHUNK_GRAHAM_HOT = new GTFOOredictItem.OreDictItem(1047, "matter_graham_hot", 0xffe1a1, MaterialIconSet.DULL, OrePrefix.plateDense);
    public static final GTFOOredictItem.OreDictItem WAFER_GRAHAM_HOT = new GTFOOredictItem.OreDictItem(1048, "matter_graham_hot", 0xffe1a1, MaterialIconSet.DULL, OrePrefix.plate);
    public static final GTFOOredictItem.OreDictItem CRACKER_GRAHAM_UNGRADED = new GTFOOredictItem.OreDictItem(1049, "cracker_graham_ungraded", 0xf0c25d, MaterialIconSet.DULL, OrePrefix.plate);
    public static final GTFOOredictItem.OreDictItem CHOCOLATE_LIQUOR_PRESSED = new GTFOOredictItem.OreDictItem(1050, "chocolate_liquor_pressed", 0xa6795a, MaterialIconSet.DULL, OrePrefix.crushedCentrifuged);
    public static final GTFOOredictItem.OreDictItem CHOCOLATE_LIQUOR_DUTCHED_PRESSED = new GTFOOredictItem.OreDictItem(1051, "chocolate_liquor_dutched_pressed", 0xab7550, MaterialIconSet.DULL, OrePrefix.crushedCentrifuged);


    public static void onMaterialsInit() {

    }
}
