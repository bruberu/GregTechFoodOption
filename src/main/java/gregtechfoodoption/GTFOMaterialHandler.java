package gregtechfoodoption;

import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.ore.OrePrefix;
import gregtechfoodoption.item.GTFOOredictItem;
import gregtechfoodoption.material.GTFOFluidMaterial;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.GENERATE_FRAME;
import static gregtechfoodoption.GTFOValues.Organic;
import static gregtechfoodoption.item.GTFOMetaItems.SHAPED_ITEM;
import static gregtechfoodoption.utils.GTFOUtils.averageRGB;

public class GTFOMaterialHandler {

    public static final GTFOFluidMaterial IsopropylChloride = new GTFOFluidMaterial("isopropyl_chloride", 0xa8a89d, "(CH3)2CHCl");

    public static final GTFOFluidMaterial LemonExtract = new GTFOFluidMaterial("lemon_extract", 0xfce80a);
    public static final GTFOFluidMaterial LimeExtract = new GTFOFluidMaterial("lime_extract", 0x85f218);
    public static final GTFOFluidMaterial OrangeExtract = new GTFOFluidMaterial("orange_extract", 0xFF6100);
    public static final GTFOFluidMaterial AppleExtract = new GTFOFluidMaterial("apple_extract", 0xE9BA58);
    public static final GTFOFluidMaterial AppleCider = new GTFOFluidMaterial("apple_cider", averageRGB(2, 0xE9BA58, FermentedBiomass.getMaterialRGB()));
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

    public static final GTFOFluidMaterial PerchloricAcid = new GTFOFluidMaterial("perchloric_acid", averageRGB(3, Chlorine.getMaterialRGB(), Oxygen.getMaterialRGB(), Hydrogen.getMaterialRGB()), "HClO4");
    public static final GTFOFluidMaterial ChloroauricAcid = new GTFOFluidMaterial("chloroauric_acid", averageRGB(3, Chlorine.getMaterialRGB(), Gold.getMaterialRGB()), "HAuCl4");
    public static final GTFOFluidMaterial MoistAir = new GTFOFluidMaterial("moist_air", 0x82c8ff);
    public static final GTFOFluidMaterial ColdMoistAir = new GTFOFluidMaterial("cold_moist_air", 0x72a2ff);

    public static final GTFOFluidMaterial Albumen = new GTFOFluidMaterial("albumen", 0xfffef7);
    public static final GTFOFluidMaterial Yolk = new GTFOFluidMaterial("yolk", 0xffdf00);
    public static final GTFOFluidMaterial Butter = new GTFOFluidMaterial("butter", 0xffef82);

    public static final GTFOFluidMaterial RabbitStew = new GTFOFluidMaterial("rabbit_stew", 0xe0c0a0);

    public static final GTFOFluidMaterial Stearin = new GTFOFluidMaterial("stearin", 0xffcc66, "C57H110O6", 373);
    public static final GTFOFluidMaterial StearicAcid = new GTFOFluidMaterial("stearic_acid", 0xfff7e6, "C17H35CO2H"); // used as a food additive synthesized from Fat (Basically turning it into 3 parts)
    public static final GTFOFluidMaterial SodiumStearate = new GTFOFluidMaterial("sodium_stearate", averageRGB(2, 0xfff7e6, SodiumHydroxide.getMaterialRGB()), "C17H35COO−Na+", 373);
    public static final GTFOFluidMaterial CitricAcid = new GTFOFluidMaterial("citric_acid", 0xCCBD61, "HOC(CH2CO2H)2"); //good for processing food

    public static final GTFOOredictItem.OreDictValueItem PopcornKernel = SHAPED_ITEM.addOreDictItem(1002, "popcorn_kernel", 0xfecb60, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);

    public static final GTFOOredictItem.OreDictValueItem Zest = SHAPED_ITEM.addOreDictItem(1092, "zest", 0xd8ff4a, MaterialIconSet.SAND, OrePrefix.dust);
    public static final GTFOOredictItem.OreDictValueItem PotatoStarch = SHAPED_ITEM.addOreDictItem(1101, "potato_starch", 0xdedcb1, MaterialIconSet.ROUGH, OrePrefix.dust);

    public static final GTFOOredictItem.OreDictValueItem LargeMozzarellaCurd = SHAPED_ITEM.addOreDictItem(1102, "large_mozzarella_curd", 0xf5f5f5, MaterialIconSet.SHINY, OrePrefix.nugget);
    public static final GTFOOredictItem.OreDictValueItem SmallMozzarellaCurd = SHAPED_ITEM.addOreDictItem(1103, "small_mozzarella_curd", 0xf5f5f5, MaterialIconSet.SHINY, OrePrefix.nugget);
    public static final GTFOOredictItem.OreDictValueItem DriedMozzarellaCurd = SHAPED_ITEM.addOreDictItem(1104, "dried_mozzarella_curd", 0xf5f4e4, MaterialIconSet.SHINY, OrePrefix.nugget);
    public static final GTFOOredictItem.OreDictValueItem SolidifiedMozzarellaCurd = SHAPED_ITEM.addOreDictItem(1105, "solidified_mozzarella_curd", 0xedebca, MaterialIconSet.SHINY, OrePrefix.nugget);
    public static final GTFOOredictItem.OreDictValueItem CoagulatedMilkCurd = SHAPED_ITEM.addOreDictItem(1106, "coagulated_milk_curd", 0xede3cc, MaterialIconSet.SHINY, OrePrefix.nugget);
    public static final GTFOOredictItem.OreDictValueItem CutCurd = SHAPED_ITEM.addOreDictItem(1107, "cut_curd", 0xede3cc, MaterialIconSet.SHINY, OrePrefix.round);
    public static final GTFOOredictItem.OreDictValueItem CookedCurd = SHAPED_ITEM.addOreDictItem(1108, "cooked_curd", 0xffe8b3, MaterialIconSet.SHINY, OrePrefix.round);
    public static final GTFOOredictItem.OreDictValueItem SaltedCurd = SHAPED_ITEM.addOreDictItem(1109, "salted_curd", 0xf7d68b, MaterialIconSet.SHINY, OrePrefix.round);
    public static final GTFOOredictItem.OreDictValueItem GorgonzolaCurd = SHAPED_ITEM.addOreDictItem(1110, "gorgonzola_curd", 0xe5e5f5, MaterialIconSet.SHINY, OrePrefix.nugget);
    public static final GTFOOredictItem.OreDictValueItem PenicilliumRoqueforti = SHAPED_ITEM.addOreDictItem(1111, "penicillium_roqueforti", 0x2a7b5a, MaterialIconSet.ROUGH, OrePrefix.dust, "Bacteria");

    public static final GTFOOredictItem.OreDictValueItem BurntBananaPeel = SHAPED_ITEM.addOreDictItem(1112, "burnt_banana_peel", 0x121110, MaterialIconSet.ROUGH, OrePrefix.dust);
    public static final GTFOOredictItem.OreDictValueItem AmmoniumPerchlorate = SHAPED_ITEM.addOreDictItem(1113, "ammonium_perchlorate", averageRGB(4, Ammonia.getMaterialRGB(), Chlorine.getMaterialRGB(), Oxygen.getMaterialRGB(), Hydrogen.getMaterialRGB()), MaterialIconSet.DULL, OrePrefix.dust, "NH4ClO4");
    public static final GTFOOredictItem.OreDictValueItem PotassiumPerchlorate = SHAPED_ITEM.addOreDictItem(1114, "potassium_perchlorate", averageRGB(2, AmmoniumPerchlorate.getMaterialRGB(), Potassium.getMaterialRGB()), MaterialIconSet.ROUGH, OrePrefix.dust, "KClO4");

    public static final GTFOOredictItem.OreDictValueItem MashedPotato = SHAPED_ITEM.addOreDictItem(1115, "mashed_potato", 0xf5d89f, MaterialIconSet.FINE, OrePrefix.dust);

    public static final GTFOOredictItem.OreDictValueItem ToughMeat = SHAPED_ITEM.addOreDictItem(1116, "tough_meat", 0xa63028, MaterialIconSet.ROUGH, OrePrefix.dust);

    public static final GTFOOredictItem.OreDictValueItem KubideMeat = SHAPED_ITEM.addOreDictItem(1117, "kubide_meat", 0x9B0600, Organic, OrePrefix.dust);
    public static final GTFOOredictItem.OreDictValueItem BargMeat = SHAPED_ITEM.addOreDictItem(1118, "barg_meat", 0x7F0000, Organic, OrePrefix.dust);
    public static final GTFOOredictItem.OreDictValueItem Fat = SHAPED_ITEM.addOreDictItem(1119, "fat", 0xFFF200, Organic, OrePrefix.ingot, "C57H110O6"); // yea Fat is much more complicated but i just stick to this formula...

    public static final GTFOOredictItem.OreDictValueItem MeatIngot = SHAPED_ITEM.addOreDictItem(1120, "cooked_meat", 0xa63028, MaterialIconSet.ROUGH, OrePrefix.ingot); // yea Fat is much more complicated but i just stick to this formula...
    //public static final GTFOOredictItem.OreDictValueItem SlimeIngot = SHAPED_ITEM.addOreDictItem(1120, "slime_ingot", 0x84C873, Organic, OrePrefix.ingot);

    public static void onMaterialsInit() {
        Materials.Iron.addFlags(GENERATE_FRAME);
        Materials.BismuthBronze.addFlags(GENERATE_FRAME);
    }
}
