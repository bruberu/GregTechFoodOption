package gregtechfoodoption;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.ore.OrePrefix;
import gregtechfoodoption.item.GTFOOredictItem;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.GENERATE_FRAME;
import static gregtechfoodoption.GTFOValues.Organic;
import static gregtechfoodoption.item.GTFOMetaItems.SHAPED_ITEM;
import static gregtechfoodoption.utils.GTFOUtils.averageRGB;

public class GTFOMaterialHandler {

    // 21500 - 21969

    public static final Material IsopropylChloride = fluidBuilder(21500, "isopropyl_chloride")
            .components(Carbon, 3, Hydrogen, 7, Chlorine, 1)
            .build()
            .setFormula("(CH3)2CHCl", true);
    public static final Material LemonExtract = fluidBuilder(21501, "lemon_extract")
            .color(0xfce80a)
            .build();
    public static final Material LimeExtract = fluidBuilder(21502, "lime_extract")
            .color(0x85f218)
            .build();
    public static final Material OrangeExtract = fluidBuilder(21503, "orange_extract")
            .color(0xff6100)
            .build();
    public static final Material AppleExtract = fluidBuilder(21504, "apple_extract")
            .color(0xe9ba58)
            .build();

    public static final Material AppleCider = fluidBuilder(21505, "apple_cider")
            .color(averageRGB(2, 0xE9BA58, FermentedBiomass.getMaterialRGB()))
            .build();
    public static final Material UnheatedCaneSyrup = fluidBuilder(21506, "unheated_cane_syrup")
            .color(0xf0efe4)
            .build();
    public static final Material CaneSyrup = fluidBuilder(21507, "cane_syrup")
            .color(0xf2f1dc)
            .build();
    public static final Material PurpleDrink = fluidBuilder(21508, "purple_drink")
            .color(0xb405ff)
            .build();

    public static final Material FryingOil = fluidBuilder(21509, "frying_oil")
            .color(0xffe3a1)
            .build();
    public static final Material HotFryingOil = fluidBuilder(21510, "hot_frying_oil")
            .color(0xffd166)
            .build();
    public static final Material StarchFilledWater = fluidBuilder(21511, "starch_filled_water")
            .color(0xd1cbbe)
            .build();

    public static final Material MushroomSoup = fluidBuilder(21512, "mushroom_soup")
            .color(0xedcaaf)
            .build();
    public static final Material BeetrootSoup = fluidBuilder(21513, "beetroot_soup")
            .color(0xc25132)
            .build();

    public static final Material ItalianBuffaloMilk = fluidBuilder(21514, "italian_buffalo_milk")
            .color(0xfcfbf5)
            .build();
    public static final Material CrudeRennetSolution = fluidBuilder(21515, "crude_rennet_solution")
            .color(0xb0631a)
            .build();
    public static final Material Whey = fluidBuilder(21516, "whey")
            .color(0xf5ef9a)
            .build();
    public static final Material ActivatedBuffaloMilk = fluidBuilder(21517, "activated_buffalo_milk")
            .color(0xfff8cc)
            .build();
    public static final Material WheySaltWaterMix = fluidBuilder(21518, "whey_salt_water_mix")
            .color(0xecfc7e)
            .build();
    public static final Material HeatedRicottaStarter = fluidBuilder(21519, "heated_ricotta_starter")
            .color(0xdef72f)
            .build();
    public static final Material AcidicMilkSolution = fluidBuilder(21520, "acidic_milk_solution")
            .color(0xb2c71c)
            .build();
    public static final Material CoagulatingRicottaSolution = fluidBuilder(21521, "coagulating_ricotta_solution")
            .color(0xeff5c9)
            .build();

    public static final Material TomatoSauce = fluidBuilder(21522, "tomato_sauce")
            .color(0xfc2217)
            .build();
    public static final Material OliveOil = fluidBuilder(21523, "olive_oil")
            .color(0xd1db5a)
            .build();

    public static final Material Sludge = fluidBuilder(21524, "sludge")
            .color(0x24140b)
            .build();

    public static final Material AlkalineExtract = fluidBuilder(21525, "alkaline_extract")
            .color(0x121110)
            .build();

    public static final Material PotatoJuice = fluidBuilder(21526, "potato_juice")
            .color(0x786b48)
            .build();
    public static final Material Vodka = fluidBuilder(21527, "vodka")
            .color(0x7d6933)
            .build();
    public static final Material Leninade = fluidBuilder(21528, "leninade")
            .color(0x82661d)
            .build();

    public static final Material PerchloricAcid = fluidBuilder(21529, "perchloric_acid")
            .components(Hydrogen, 1, Chlorine, 1, Oxygen, 4)
            .build();
    public static final Material ChloroauricAcid = fluidBuilder(21530, "chloroauric_acid")
            .components(Hydrogen, 1, Gold, 1, Chlorine, 4)
            .build();

    public static final Material MoistAir = fluidBuilder(21531, "moist_air")
            .color(0x82c8ff)
            .build();
    public static final Material ColdMoistAir = fluidBuilder(21532, "cold_moist_air")
            .color(0x72a2ff)
            .build();

    public static final Material Albumen = fluidBuilder(21533, "albumen")
            .color(0xfffef7)
            .build();
    public static final Material Yolk = fluidBuilder(21534, "yolk")
            .color(0xffdf00)
            .build();
    public static final Material Butter = fluidBuilder(21535, "butter")
            .color(0xffef82)
            .build();

    public static final Material RabbitStew = fluidBuilder(21536, "rabbit_stew")
            .color(0xe0c0a0)
            .build();

    public static final Material Stearin = fluidBuilder(21537, "stearin")
            .color(0xffcc66)
            .components(Carbon, 57, Hydrogen, 110, Oxygen, 6)
            .build();
    public static final Material StearicAcid = fluidBuilder(21538, "stearic_acid").color(0xfff7e6).
            components(Carbon, 18, Hydrogen, 36, Oxygen, 2)
            .build()
            .setFormula("C17H35CO2H"); // used as a food additive synthesized from Fat (Basically turning it into 3 parts)
    public static final Material SodiumStearate = fluidBuilder(21539, "sodium_stearate")
            .components(Carbon, 18, Hydrogen, 35, Oxygen, 2, Sodium, 1)
            .build()
            .setFormula("C17H35COO−Na+");
    public static final Material CitricAcid = fluidBuilder(21540, "citric_acid")
            .color(0xccbd61)
            .components(Carbon, 5, Hydrogen, 7, Oxygen, 5)
            .build()
            .setFormula("HOC(CH2CO2H)2"); //good for processing food
    public static final Material HydrogenCyanide = fluidBuilder(21541, "hydrogen_cyanide")
            .color(0x6e6a5e)
            .components(Hydrogen, 1, Carbon, 1, Nitrogen, 1)
            .build();

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

    public static Material.Builder fluidBuilder(int id, String name) {
        return new Material.Builder(id, "gtfo_" + name).fluid();
    }
}
