package gregtechfoodoption;

import gregicality.science.api.unification.materials.GCYSMaterials;
import gregtech.api.GregTechAPI;
import gregtech.api.fluids.fluidType.FluidType;
import gregtech.api.fluids.fluidType.FluidTypes;
import gregtech.api.items.metaitem.MetaOreDictItem;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.ore.OrePrefix;
import gregtechfoodoption.item.GTFOOredictItem;
import gregtechfoodoption.item.GTFOProxyItem;
import net.minecraftforge.fml.common.Loader;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.GENERATE_DENSE;
import static gregtech.api.unification.material.info.MaterialFlags.GENERATE_FRAME;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtechfoodoption.GTFOValues.MODID_GCYS;
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

    public static final Material PerchloricAcid = fluidBuilder(21529, "perchloric_acid", FluidTypes.ACID)
            .components(Hydrogen, 1, Chlorine, 1, Oxygen, 4)
            .build();
    public static final Material ChloroauricAcid = GregTechAPI.MaterialRegistry.get("chloroauric_acid") != null ? GregTechAPI.MaterialRegistry.get("chloroauric_acid") :
            fluidBuilder(21530, "chloroauric_acid", FluidTypes.ACID)
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
    public static final Material StearicAcid = fluidBuilder(21538, "stearic_acid", FluidTypes.ACID).color(0xfff7e6)
            .components(Carbon, 18, Hydrogen, 36, Oxygen, 2)
            .build()
            .setFormula("C17H35CO2H", true); // used as a food additive synthesized from Fat (Basically turning it into 3 parts)
    public static final Material SodiumStearate = fluidBuilder(21539, "sodium_stearate")
            .components(Carbon, 18, Hydrogen, 35, Oxygen, 2, Sodium, 1)
            .build()
            .setFormula("C17H35COONa", true);
    public static final Material CitricAcid = fluidBuilder(21540, "citric_acid", FluidTypes.ACID)
            .color(0xccbd61)
            .components(Carbon, 5, Hydrogen, 7, Oxygen, 5)
            .build()
            .setFormula("HOC(CH2CO2H)2", true); //good for processing food
    public static final Material HydrogenCyanide = Loader.isModLoaded(MODID_GCYS) ? GCYSMaterials.HydrogenCyanide : fluidBuilder(21541, "hydrogen_cyanide")
            .color(0x6e6a5e)
            .components(Hydrogen, 1, Carbon, 1, Nitrogen, 1)
            .build();

    public static final Material Cream = fluidBuilder(21542, "cream").color(0xced2d9)
            .build();
    public static final Material SkimmedMilk = fluidBuilder(21543, "skimmed_milk").color(0xf7ffe3)
            .build();
    public static final Material SoyLecithin = fluidBuilder(21544, "soy_lecithin").color(0xa6963a)
            .build();
    public static final Material RawSoybeanOil = fluidBuilder(21545, "raw_soybean_oil").color(0xad5418)
            .build();
    public static final Material HydratedSoybeanOil = fluidBuilder(21546, "hydrated_soybean_oil").color(0xc99c7d)
            .build();
    public static final Material SoybeanOil = fluidBuilder(21547, "soybean_oil").color(0xe8e4a9)
            .build();
    public static final Material PasteurizedMilk = fluidBuilder(21548, "pasteurized_milk").color(0xfefdf3)
            .build();
    public static final Material MilkColloid = fluidBuilder(21549, "milk_colloid").color(0xe0d7bf)
            .build();
    public static final Material IceCreamMixture = fluidBuilder(21550, "ice_cream_mixture").color(0xdebd80)
            .build();

    public static final Material Guaiacol = fluidBuilder(21551, "guaiacol").color(0xa63a00)
            .components(Carbon, 7, Hydrogen, 8, Oxygen, 2)
            .build();
    public static final Material Acetaldehyde = fluidBuilder(21552, "acetaldehyde").color(0xf3f2f1)
            .components(Carbon, 2, Hydrogen, 4, Oxygen, 1)
            .build();
    public static final Material Glyoxal = fluidBuilder(21553, "glyoxal").color(0xc9c7ab)
            .components(Carbon, 2, Hydrogen, 2, Oxygen, 2)
            .build();
    public static final Material GlyoxylicAcid = fluidBuilder(21554, "glyoxylic_acid", FluidTypes.ACID).color(0xd9d5a0)
            .components(Carbon, 2, Hydrogen, 2, Oxygen, 3)
            .build();

    public static final Material MelonExtract = fluidBuilder(21555, "melon_extract").color(0xfc7996)
            .build();

    public static final Material MoltenUnsweetenedChocolate = fluidBuilder(21556, "molten_unsweetened_chocolate").color(0x7b3f00).fluidTemp(303)
            .build();

    public static final Material CocoaButter = fluidBuilder(21557, "cocoa_butter").color(0xe5dbce).fluidTemp(309)
            .build();

    public static final Material MoltenDarkChocolate = fluidBuilder(21558, "molten_dark_chocolate").color(0x490206).fluidTemp(326)
            .build();

    public static final Material MoltenMilkChocolate = fluidBuilder(21559, "molten_milk_chocolate").color(0x84563c).fluidTemp(316)
            .build();

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

    public static final GTFOOredictItem.OreDictValueItem PopcornKernel = SHAPED_ITEM.addOreDictItem(1002, "popcorn_kernel", 0xfecb60, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);

    public static final GTFOOredictItem.OreDictValueItem Zest = SHAPED_ITEM.addOreDictItem(1092, "zest", 0xd8ff4a, MaterialIconSet.SAND, dust);
    public static final GTFOOredictItem.OreDictValueItem PotatoStarch = SHAPED_ITEM.addOreDictItem(1101, "potato_starch", 0xdedcb1, MaterialIconSet.ROUGH, dust);

    public static final GTFOOredictItem.OreDictValueItem LargeMozzarellaCurd = SHAPED_ITEM.addOreDictItem(1102, "large_mozzarella_curd", 0xf5f5f5, MaterialIconSet.SHINY, OrePrefix.nugget);
    public static final GTFOOredictItem.OreDictValueItem SmallMozzarellaCurd = SHAPED_ITEM.addOreDictItem(1103, "small_mozzarella_curd", 0xf5f5f5, MaterialIconSet.SHINY, OrePrefix.nugget);
    public static final GTFOOredictItem.OreDictValueItem DriedMozzarellaCurd = SHAPED_ITEM.addOreDictItem(1104, "dried_mozzarella_curd", 0xf5f4e4, MaterialIconSet.SHINY, OrePrefix.nugget);
    public static final GTFOOredictItem.OreDictValueItem SolidifiedMozzarellaCurd = SHAPED_ITEM.addOreDictItem(1105, "solidified_mozzarella_curd", 0xedebca, MaterialIconSet.SHINY, OrePrefix.nugget);
    public static final GTFOOredictItem.OreDictValueItem CoagulatedMilkCurd = SHAPED_ITEM.addOreDictItem(1106, "coagulated_milk_curd", 0xede3cc, MaterialIconSet.SHINY, OrePrefix.nugget);
    public static final GTFOOredictItem.OreDictValueItem CutCurd = SHAPED_ITEM.addOreDictItem(1107, "cut_curd", 0xede3cc, MaterialIconSet.SHINY, OrePrefix.round);
    public static final GTFOOredictItem.OreDictValueItem CookedCurd = SHAPED_ITEM.addOreDictItem(1108, "cooked_curd", 0xffe8b3, MaterialIconSet.SHINY, OrePrefix.round);
    public static final GTFOOredictItem.OreDictValueItem SaltedCurd = SHAPED_ITEM.addOreDictItem(1109, "salted_curd", 0xf7d68b, MaterialIconSet.SHINY, OrePrefix.round);
    public static final GTFOOredictItem.OreDictValueItem GorgonzolaCurd = SHAPED_ITEM.addOreDictItem(1110, "gorgonzola_curd", 0xe5e5f5, MaterialIconSet.SHINY, OrePrefix.nugget);
    public static final GTFOOredictItem.OreDictValueItem PenicilliumRoqueforti = SHAPED_ITEM.addOreDictItem(1111, "penicillium_roqueforti", 0x2a7b5a, MaterialIconSet.ROUGH, dust, "Bacteria");

    public static final GTFOOredictItem.OreDictValueItem BurntBananaPeel = SHAPED_ITEM.addOreDictItem(1112, "burnt_banana_peel", 0x121110, MaterialIconSet.ROUGH, dust);
    public static final GTFOOredictItem.OreDictValueItem AmmoniumPerchlorate = SHAPED_ITEM.addOreDictItem(1113, "ammonium_perchlorate", averageRGB(4, Ammonia.getMaterialRGB(), Chlorine.getMaterialRGB(), Oxygen.getMaterialRGB(), Hydrogen.getMaterialRGB()), MaterialIconSet.DULL, dust, "NH4ClO4");
    public static final GTFOOredictItem.OreDictValueItem PotassiumPerchlorate = SHAPED_ITEM.addOreDictItem(1114, "potassium_perchlorate", averageRGB(2, AmmoniumPerchlorate.getMaterialRGB(), Potassium.getMaterialRGB()), MaterialIconSet.ROUGH, dust, "KClO4");

    public static final GTFOOredictItem.OreDictValueItem MashedPotato = SHAPED_ITEM.addOreDictItem(1115, "mashed_potato", 0xf5d89f, MaterialIconSet.FINE, dust);

    public static final GTFOOredictItem.OreDictValueItem ToughMeat = SHAPED_ITEM.addOreDictItem(1116, "tough_meat", 0xa63028, MaterialIconSet.ROUGH, dust);

    public static final GTFOOredictItem.OreDictValueItem KubideMeat = SHAPED_ITEM.addOreDictItem(1117, "kubide_meat", 0x9B0600, Organic, dust);
    public static final GTFOOredictItem.OreDictValueItem BargMeat = SHAPED_ITEM.addOreDictItem(1118, "barg_meat", 0x7F0000, Organic, dust);
    public static final GTFOOredictItem.OreDictValueItem Fat = SHAPED_ITEM.addOreDictItem(1119, "fat", 0xFFF200, Organic, OrePrefix.ingot, "C57H110O6"); // yea Fat is much more complicated but i just stick to this formula...

    public static final GTFOOredictItem.OreDictValueItem MeatIngot = SHAPED_ITEM.addOreDictItem(1120, "cooked_meat", 0xa63028, MaterialIconSet.ROUGH, OrePrefix.ingot);
    //public static final GTFOOredictItem.OreDictValueItem SlimeIngot = SHAPED_ITEM.addOreDictItem(1120, "slime_ingot", 0x84C873, Organic, OrePrefix.ingot);

    public static final GTFOOredictItem.OreDictValueItem SodiumPerchlorate = SHAPED_ITEM.addOreDictItem(1121, "sodium_perchlorate", averageRGB(3, Sodium.getMaterialRGB(), Oxygen.getMaterialRGB(), 0xFFFFFF), MaterialIconSet.ROUGH, dust, "NaClO4");
    public static final GTFOProxyItem SodiumChlorate = new GTFOProxyItem(() -> SHAPED_ITEM.addOreDictItem(1122, "sodium_chlorate", averageRGB(2, Sodium.getMaterialRGB(), Oxygen.getMaterialRGB()), MaterialIconSet.ROUGH, dust, "NaClO3"), 1122, MODID_GCYS, () -> OreDictUnifier.get(dust, GCYSMaterials.SodiumChlorate));

    public static final MetaOreDictItem.OreDictValueItem VanillylmandelicAcid = SHAPED_ITEM.addOreDictItem(1123, "vanillylmandelic_acid", 0xf2efbd, MaterialIconSet.ROUGH, dust, "C9H10O5");
    public static final MetaOreDictItem.OreDictValueItem VanilglycolicAcid = SHAPED_ITEM.addOreDictItem(1124, "vanilglycolic_acid", 0xebe7a4, MaterialIconSet.DULL, dust, "C9H8O5");
    public static final MetaOreDictItem.OreDictValueItem Vanillin = SHAPED_ITEM.addOreDictItem(1125, "vanillin", 0xfbfbfb, MaterialIconSet.SHINY, OrePrefix.dust, "C8H8O3");

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
        Materials.Iron.addFlags(GENERATE_FRAME);
        Materials.BismuthBronze.addFlags(GENERATE_FRAME);
        Materials.Aluminium.addFlags(GENERATE_DENSE);
        Materials.StainlessSteel.addFlags(GENERATE_DENSE);
        Materials.Titanium.addFlags(GENERATE_DENSE);
        Materials.Aluminium.addFlags(GENERATE_DENSE);
    }

    public static Material.Builder fluidBuilder(int id, String name) {
        return new Material.Builder(id, "gtfo_" + name).fluid();
    }

    public static Material.Builder fluidBuilder(int id, String name, FluidType type) {
        return new Material.Builder(id, "gtfo_" + name).fluid(type);
    }
}
