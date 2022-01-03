package gregtechfoodoption;

import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.ore.OrePrefix;
import gregtechfoodoption.item.GTFOOredictItem;
import gregtechfoodoption.material.GTFOFluidMaterial;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.GENERATE_FRAME;
import static gregtechfoodoption.utils.RecipeUtils.average;

public class GTFOMaterialHandler {

    public static final GTFOFluidMaterial IsopropylChloride = new GTFOFluidMaterial("isopropyl_chloride", 0xa8a89d, "(CH3)2CHCl");

    public static final GTFOFluidMaterial LemonExtract = new GTFOFluidMaterial("lemon_extract", 0xfce80a);
    public static final GTFOFluidMaterial LimeExtract = new GTFOFluidMaterial("lime_extract", 0x85f218);
    public static final GTFOFluidMaterial UnheatedCaneSyrup = new GTFOFluidMaterial("unheated_cane_syrup", 0xf0efe4);
    public static final GTFOFluidMaterial CaneSyrup = new GTFOFluidMaterial("cane_syrup", 0xf2f1dc);
    public static final GTFOFluidMaterial PurpleDrink = new GTFOFluidMaterial("purple_drink", 0xb405ff);

    public static final GTFOFluidMaterial FryingOil = new GTFOFluidMaterial("frying_oil", 0xffe3a1);
    public static final GTFOFluidMaterial HotFryingOil = new GTFOFluidMaterial("hot_frying_oil", 0xffd166);
    public static final GTFOFluidMaterial StarchFilledWater = new GTFOFluidMaterial("starch_filled_water", 0xd1cbbe);

    public static final GTFOFluidMaterial MushroomSoup = new GTFOFluidMaterial("mushroom_soup", 0xedcaaf);
    public static final GTFOFluidMaterial BeetrootSoup = new GTFOFluidMaterial("beetroot_soup", 0xc25132);

    public static final GTFOFluidMaterial ItalianBuffaloMilk = new GTFOFluidMaterial("italian_buffalo_milk", 0xfcfbf5);
    public static final GTFOFluidMaterial CrudeRennetSolution = new GTFOFluidMaterial("crude_rennet_solution", 0xb0631a);
    public static final GTFOFluidMaterial Whey = new GTFOFluidMaterial("whey", 0xf5ef9a);
    public static final GTFOFluidMaterial ActivatedBuffaloMilk = new GTFOFluidMaterial("activated_buffalo_milk", 0xfff8cc);
    public static final GTFOFluidMaterial WheySaltWaterMix = new GTFOFluidMaterial("whey_salt_water_mix", 0xecfc7e);
    public static final GTFOFluidMaterial HeatedRicottaStarter = new GTFOFluidMaterial("heated_ricotta_starter", 0xdef72f);
    public static final GTFOFluidMaterial AcidicMilkSolution = new GTFOFluidMaterial("acidic_milk_solution", 0xb2c71c);
    public static final GTFOFluidMaterial CoagulatingRicottaSolution = new GTFOFluidMaterial("coagulating_ricotta_solution", 0xeff5c9);

    public static final GTFOFluidMaterial TomatoSauce = new GTFOFluidMaterial("tomato_sauce", 0xfc2217);
    public static final GTFOFluidMaterial OliveOil = new GTFOFluidMaterial("olive_oil", 0xd1db5a);

    public static final GTFOFluidMaterial Sludge = new GTFOFluidMaterial("sludge", 0x24140b);

    public static final GTFOFluidMaterial AlkalineExtract = new GTFOFluidMaterial("alkaline_extract", 0x121110);

    public static final GTFOFluidMaterial PotatoJuice = new GTFOFluidMaterial("potato_juice", 0x786b48);
    public static final GTFOFluidMaterial Vodka = new GTFOFluidMaterial("vodka", 0x7d6933);
    public static final GTFOFluidMaterial Leninade = new GTFOFluidMaterial("leninade", 0x82661d);

    public static final GTFOFluidMaterial PerchloricAcid = new GTFOFluidMaterial("perchloric_acid", average(6, Chlorine.getMaterialRGB(), Oxygen.getMaterialRGB() * 4, Hydrogen.getMaterialRGB()), "HClO4");
    public static final GTFOFluidMaterial ChloroauricAcid = new GTFOFluidMaterial("chloroauric_acid", average(3, Chlorine.getMaterialRGB(), Gold.getMaterialRGB() * 3), "HAuCl4");
    public static final GTFOFluidMaterial MoistAir = new GTFOFluidMaterial("moist_air", 0x82c8ff);
    public static final GTFOFluidMaterial ColdMoistAir = new GTFOFluidMaterial("cold_moist_air", 0x72a2ff);

    public static final GTFOOredictItem.OreDictItem GradedPopcornKernel = new GTFOOredictItem.OreDictItem(1000, "popcorn_kernel_graded", 0xffea70, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem BarePopcornKernel = new GTFOOredictItem.OreDictItem(1001, "popcorn_kernel_bare", 0xfecb60, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem PopcornKernel = new GTFOOredictItem.OreDictItem(1002, "popcorn_kernel", 0xfecb60, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem PhycomycesBlakesleeanus = new GTFOOredictItem.OreDictItem(1003, "phycomyces_blakesleeanus", 0x454442, MaterialIconSet.ROUGH, OrePrefix.dust);
    public static final GTFOOredictItem.OreDictItem BetaCarotene = new GTFOOredictItem.OreDictItem(1004, "beta_carotene", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "C40H56");
    public static final GTFOOredictItem.OreDictItem IsopropylmagnesiumChloride = new GTFOOredictItem.OreDictItem(1005, "isopropylmagnesium_chloride", 0x211210, MaterialIconSet.SHINY, OrePrefix.dust, "C3H7ClMg");
    public static final GTFOOredictItem.OreDictItem IiAminopyridine = new GTFOOredictItem.OreDictItem(1006, "2-aminopyridine", 0xf0ede9, MaterialIconSet.LAPIS, OrePrefix.dust, "C5H6N2");

    public static final GTFOOredictItem.OreDictItem PotatoStarch = new GTFOOredictItem.OreDictItem(1101, "potato_starch", 0xdedcb1, MaterialIconSet.ROUGH, OrePrefix.dust);

    public static final GTFOOredictItem.OreDictItem LargeMozzarellaCurd = new GTFOOredictItem.OreDictItem(1102, "large_mozzarella_curd", 0xf5f5f5, MaterialIconSet.SHINY, OrePrefix.nugget);
    public static final GTFOOredictItem.OreDictItem SmallMozzarellaCurd = new GTFOOredictItem.OreDictItem(1103, "small_mozzarella_curd", 0xf5f5f5, MaterialIconSet.SHINY, OrePrefix.nugget);
    public static final GTFOOredictItem.OreDictItem DriedMozzarellaCurd = new GTFOOredictItem.OreDictItem(1104, "dried_mozzarella_curd", 0xf5f4e4, MaterialIconSet.SHINY, OrePrefix.nugget);
    public static final GTFOOredictItem.OreDictItem SolidifiedMozzarellaCurd = new GTFOOredictItem.OreDictItem(1105, "solidified_mozzarella_curd", 0xedebca, MaterialIconSet.SHINY, OrePrefix.nugget);
    public static final GTFOOredictItem.OreDictItem CoagulatedMilkCurd = new GTFOOredictItem.OreDictItem(1106, "coagulated_milk_curd", 0xede3cc, MaterialIconSet.SHINY, OrePrefix.nugget);
    public static final GTFOOredictItem.OreDictItem CutCurd = new GTFOOredictItem.OreDictItem(1107, "cut_curd", 0xede3cc, MaterialIconSet.SHINY, OrePrefix.round);
    public static final GTFOOredictItem.OreDictItem CookedCurd = new GTFOOredictItem.OreDictItem(1108, "cooked_curd", 0xffe8b3, MaterialIconSet.SHINY, OrePrefix.round);
    public static final GTFOOredictItem.OreDictItem SaltedCurd = new GTFOOredictItem.OreDictItem(1109, "salted_curd", 0xf7d68b, MaterialIconSet.SHINY, OrePrefix.round);
    public static final GTFOOredictItem.OreDictItem GorgonzolaCurd = new GTFOOredictItem.OreDictItem(1110, "gorgonzola_curd", 0xe5e5f5, MaterialIconSet.SHINY, OrePrefix.nugget);
    public static final GTFOOredictItem.OreDictItem PenicilliumRoqueforti = new GTFOOredictItem.OreDictItem(1111, "penicillium_roqueforti", 0x2a7b5a, MaterialIconSet.ROUGH, OrePrefix.dust, "Bacteria");

    public static final GTFOOredictItem.OreDictItem BurntBananaPeel = new GTFOOredictItem.OreDictItem(1112, "burnt_banana_peel", 0x121110, MaterialIconSet.ROUGH, OrePrefix.dust);
    public static final GTFOOredictItem.OreDictItem AmmoniumPerchlorate = new GTFOOredictItem.OreDictItem(1113, "ammonium_perchlorate", average(7, Ammonia.getMaterialRGB(), Chlorine.getMaterialRGB(), Oxygen.getMaterialRGB() * 4, Hydrogen.getMaterialRGB()), MaterialIconSet.DULL, OrePrefix.dust, "NH4ClO4");
    public static final GTFOOredictItem.OreDictItem PotassiumPerchlorate = new GTFOOredictItem.OreDictItem(1114, "potassium_perchlorate", average(2, AmmoniumPerchlorate.getMaterialRGB(), Potassium.getMaterialRGB()), MaterialIconSet.ROUGH, OrePrefix.dust, "KClO4");

    public static void onMaterialsInit() {
        Materials.Iron.addFlags(GENERATE_FRAME);
        Materials.BismuthBronze.addFlags(GENERATE_FRAME);
    }
}
