package gregtechfoodoption;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.fluids.FluidBuilder;
import gregtech.api.fluids.attribute.FluidAttribute;
import gregtech.api.fluids.attribute.FluidAttributes;
import gregtech.api.items.metaitem.MetaOreDictItem;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.FluidTooltipUtil;
import gregtech.api.util.LocalizationUtils;
import gregtechfoodoption.item.GTFOOredictItem;
import gregtechfoodoption.item.GTFOProxyItem;
import gregtechfoodoption.materials.CleanerProperty;
import gregtechfoodoption.materials.FertilizerProperty;
import gregtechfoodoption.materials.LacingProperty;
import it.unimi.dsi.fastutil.ints.Int2BooleanArrayMap;
import it.unimi.dsi.fastutil.ints.Int2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.material.info.MaterialFlags.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtechfoodoption.GTFOValues.Organic;
import static gregtechfoodoption.item.GTFOMetaItems.SHAPED_ITEM;
import static gregtechfoodoption.utils.GTFOUtils.averageRGB;

public class GTFOMaterialHandler {
    public static final PropertyKey<FertilizerProperty> FERTILIZER = new PropertyKey<>("gtfo_fertilizer", FertilizerProperty.class);
    public static final PropertyKey<LacingProperty> LACING = new PropertyKey<>("gtfo_lacing", LacingProperty.class);
    public static final PropertyKey<CleanerProperty> CLEANER = new PropertyKey<>("gtfo_cleaner", CleanerProperty.class);
    private static final Int2BooleanMap FOOD_RELATED_MATERIALS = new Int2BooleanArrayMap();

    // 21500 - 21999

    public static final Material IsopropylChloride = fluidBuilder(21500, "isopropyl_chloride")
            .components(Carbon, 3, Hydrogen, 7, Chlorine, 1)
            .build()
            .setFormula("(CH3)2CHCl", true);
    public static final Material LemonExtract = fluidBuilderFood(21501, "lemon_extract")
            .color(0xfce80a)
            .build();
    public static final Material LimeExtract = fluidBuilderFood(21502, "lime_extract")
            .color(0x85f218)
            .build();
    public static final Material OrangeExtract = fluidBuilderFood(21503, "orange_extract")
            .color(0xff6100)
            .build();
    public static final Material AppleExtract = fluidBuilderFood(21504, "apple_extract")
            .color(0xe9ba58)
            .build();

    public static final Material AppleCider = fluidBuilderFood(21505, "apple_cider")
            .color(averageRGB(2, 0xE9BA58, FermentedBiomass.getMaterialRGB()))
            .build();
    public static final Material UnheatedCaneSyrup = fluidBuilderFood(21506, "unheated_cane_syrup")
            .color(0xf0efe4)
            .build();
    public static final Material CaneSyrup = fluidBuilderFood(21507, "cane_syrup")
            .color(0xf2f1dc)
            .build();
    public static final Material PurpleDrink = fluidBuilderFood(21508, "purple_drink")
            .color(0xb405ff)
            .build();

    public static final Material FryingOil = fluidBuilderFood(21509, "frying_oil")
            .color(0xffe3a1)
            .build();
    public static final Material HotFryingOil = fluidBuilderFood(21510, "hot_frying_oil", 483)
            .color(0xffd166)
            .build();
    public static final Material StarchFilledWater = fluidBuilder(21511, "starch_filled_water")
            .color(0xd1cbbe)
            .build();

    public static final Material MushroomSoup = fluidBuilderFood(21512, "mushroom_soup", 343)
            .color(0xedcaaf)
            .build();
    public static final Material BeetrootSoup = fluidBuilderFood(21513, "beetroot_soup", 343)
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
    public static final Material HeatedRicottaStarter = fluidBuilder(21519, "heated_ricotta_starter", 348)
            .color(0xdef72f)
            .build();
    public static final Material AcidicMilkSolution = fluidBuilder(21520, "acidic_milk_solution")
            .color(0xb2c71c)
            .build();
    public static final Material CoagulatingRicottaSolution = fluidBuilder(21521, "coagulating_ricotta_solution")
            .color(0xeff5c9)
            .build();

    public static final Material TomatoSauce = fluidBuilderFood(21522, "tomato_sauce")
            .color(0xfc2217)
            .build();
    public static final Material OliveOil = fluidBuilderFood(21523, "olive_oil")
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
    public static final Material Leninade = fluidBuilderFood(21528, "leninade")
            .color(0x82661d)
            .build();

    public static final Material PerchloricAcid = fluidBuilder(21529, "perchloric_acid", FluidAttributes.ACID)
            .components(Hydrogen, 1, Chlorine, 1, Oxygen, 4)
            .build();
    public static final Material ChloroauricAcid = GregTechAPI.materialManager.getMaterial("chloroauric_acid") != null ? GregTechAPI.materialManager.getMaterial("chloroauric_acid") :
            fluidBuilder(21530, "chloroauric_acid", FluidAttributes.ACID)
                    .components(Hydrogen, 1, Gold, 1, Chlorine, 4)
                    .build();

    public static final Material MoistAir = gasBuilder(21531, "moist_air", 273)
            .color(0x82c8ff)
            .build();
    public static final Material ColdMoistAir = gasBuilder(21532, "cold_moist_air", 243)
            .color(0x72a2ff)
            .build();

    public static final Material Albumen = fluidBuilderFood(21533, "albumen")
            .color(0xfffef7)
            .build();
    public static final Material Yolk = fluidBuilderFood(21534, "yolk")
            .color(0xffdf00)
            .build();
    public static final Material Butter = fluidBuilderFood(21535, "butter")
            .color(0xffef82)
            .build();

    public static final Material RabbitStew = fluidBuilderFood(21536, "rabbit_stew", 343)
            .color(0xe0c0a0)
            .build();

    public static final Material Stearin = fluidBuilder(21537, "stearin")
            .color(0xffcc66)
            .flags(DISABLE_DECOMPOSITION)
            .components(Carbon, 57, Hydrogen, 110, Oxygen, 6)
            .build();
/*    public static final Material StearicAcid = fluidBuilder(21538, "stearic_acid", FluidTypes.ACID).color(0xfff7e6)
            .components(Carbon, 18, Hydrogen, 36, Oxygen, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("C17H35CO2H", true); // used as a food additive synthesized from Fat (Basically turning it into 3 parts)*/
    public static final Material SodiumStearate = fluidBuilder(21539, "sodium_stearate")
            .components(Carbon, 18, Hydrogen, 35, Oxygen, 2, Sodium, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("C17H35COONa", true);
    public static final Material CitricAcid = fluidBuilder(21540, "citric_acid", FluidAttributes.ACID)
            .color(0xccbd61)
            .components(Carbon, 5, Hydrogen, 7, Oxygen, 5)
            .build()
            .setFormula("HOC(CH2CO2H)2", true); //good for processing food
    public static final Material HydrogenCyanide = GregTechAPI.materialManager.getMaterial("hydrogen_cyanide") != null ? GregTechAPI.materialManager.getMaterial("hydrogen_cyanide") : fluidBuilder(21541, "hydrogen_cyanide")
            .color(0x6e6a5e)
            .components(Hydrogen, 1, Carbon, 1, Nitrogen, 1)
            .build();

    public static final Material Cream = fluidBuilderFood(21542, "cream")
            .color(0xced2d9)
            .build();
    public static final Material SkimmedMilk = fluidBuilderFood(21543, "skimmed_milk")
            .color(0xf7ffe3)
            .build();
    public static final Material SoyLecithin = fluidBuilder(21544, "soy_lecithin")
            .color(0xa6963a)
            .build();
    public static final Material RawSoybeanOil = fluidBuilder(21545, "raw_soybean_oil")
            .color(0xad5418)
            .build();
    public static final Material HydratedSoybeanOil = fluidBuilder(21546, "hydrated_soybean_oil")
            .color(0xc99c7d)
            .build();
    public static final Material SoybeanOil = fluidBuilder(21547, "soybean_oil")
            .color(0xe8e4a9)
            .build();
    public static final Material PasteurizedMilk = fluidBuilderFood(21548, "pasteurized_milk")
            .color(0xfefdf3)
            .build();
    public static final Material MilkColloid = fluidBuilderFood(21549, "milk_colloid")
            .color(0xe0d7bf)
            .build();
    public static final Material IceCreamMixture = fluidBuilderFood(21550, "ice_cream_mixture")
            .color(0xdebd80)
            .build();

    public static final Material Guaiacol = fluidBuilder(21551, "guaiacol")
            .color(0xa63a00)
            .components(Carbon, 7, Hydrogen, 8, Oxygen, 2)
            .build();
    public static final Material Acetaldehyde = fluidBuilder(21552, "acetaldehyde")
            .color(0xf3f2f1)
            .components(Carbon, 2, Hydrogen, 4, Oxygen, 1)
            .build();
    public static final Material Glyoxal = fluidBuilder(21553, "glyoxal")
            .color(0xc9c7ab)
            .components(Carbon, 2, Hydrogen, 2, Oxygen, 2)
            .build();
    public static final Material GlyoxylicAcid = fluidBuilder(21554, "glyoxylic_acid", FluidAttributes.ACID)
            .color(0xd9d5a0)
            .components(Carbon, 2, Hydrogen, 2, Oxygen, 3)
            .build();

    public static final Material MelonExtract = fluidBuilderFood(21555, "melon_extract")
            .color(0xfc7996)
            .build();

    public static final Material MoltenUnsweetenedChocolate = fluidBuilderFood(21556, "molten_unsweetened_chocolate", 370)
            .color(0x7b3f00)
            .build();
    public static final Material CocoaButter = fluidBuilderFood(21557, "cocoa_butter")
            .color(0xe5dbce)
            .build();
    public static final Material MoltenDarkChocolate = fluidBuilderFood(21558, "molten_dark_chocolate", 360)
            .color(0x490206)
            .build();
    public static final Material MoltenMilkChocolate = fluidBuilderFood(21559, "molten_milk_chocolate", 350)
            .color(0x84563c)
            .build();

    public static final Material SodiumArseniteSolution = fluidBuilder(21560, "sodium_arsenite_solution")
            .color(averageRGB(2, SodaAsh.getMaterialRGB(), Arsenic.getMaterialRGB()))
            .components(Sodium, 1, Arsenic, 1, Oxygen, 2)
            .flags(DISABLE_DECOMPOSITION)
            .build();
    public static final Material RubberSap = fluidBuilder(21561, "rubber_sap")
            .color(0xf7f6dc)
            .build();
    public static final Material RainbowSap = fluidBuilderCustom(21562, "rainbow_sap")
            .color(0xffffff)
            .build();
    public static final Material BlueVitriol = GregTechAPI.materialManager.getMaterial("blue_vitriol") != null ? GregTechAPI.materialManager.getMaterial("blue_vitriol") :
            fluidBuilder(21563, "blue_vitriol")
                    .color(0x4242DE)
                    .components(Copper, 1, Sulfur, 1, Oxygen, 4)
                    .build();
    public static final Material BakingSodaSolution = fluidBuilder(21564, "baking_soda_solution")
            .color(SodiumBicarbonate.getMaterialRGB())
            .components(SodiumBicarbonate, 1, Water, 1)
            .flags(DECOMPOSITION_BY_CENTRIFUGING)
            .build();
    public static final Material BeerBatter = fluidBuilderFood(21565, "beer_batter")
            .color(0xe4cfc0)
            .build();
    public static final Material WheatyJuice = fluidBuilderFood(21566, "wheaty_juice")
            .color(0xa87b58)
            .build();
    public static final Material PoorQualityBeer = fluidBuilderFood(21567, "poor_quality_beer")
            .color(0xa87b58)
            .build();
    public static final Material SodiumSulfate = new Material.Builder(21568, gtfoId("sodium_sulfate"))
            .dust()
            .components(Sodium, 2, Sulfur, 1, Oxygen, 4)
            .build();

    public static final Material Blood = fluidBuilder(21569, "blood", 310)
            .color(0x691a15)
            .build();

    public static final Material FertilizerSolution = fluidBuilder(21570, "fertilizer_solution")
            .color(0x947760)
            .build();

    public static final Material Nilk = fluidBuilderFood(21571, "nilk")
            .color(0x252626)
            .build();

    public static final Material HighFructoseCornSyrupSolution = fluidBuilder(21572, "hfcs_solution")
            .color(0xe3bc20)
            .build();
    public static final Material XPhenothiazineIiPropylChloride = fluidBuilder(21573, "x_phenothiazine_ii_propyl_chloride")
            .components(Carbon, 15, Hydrogen, 14, Nitrogen, 1, Sulfur, 1, Chlorine, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build();
    public static final Material AppleSyrup = fluidBuilderFood(21574, "apple_syrup")
            .color(0xf2e1ac)
            .build();
    public static final Material AppleCandySyrup = fluidBuilderFood(21575, "apple_candy_syrup")
            .color(0xe7f5ae)
            .build();
    public static final Material Etirps = fluidBuilderFood(21576, "etirps")
            .color(0xb0ff73)
            .build();
    public static final Material LemonLimeSodaSyrup = fluidBuilderFood(21577, "lemon_lime_soda_syrup")
            .color(0x76ff0d)
            .build();
    public static final Material CarbonatedWater = fluidBuilderFood(21578, "carbonated_water")
            .color(0xf5ffff)
            .build();
    public static final Material CoughSyrup = fluidBuilder(21579, "cough_syrup")
            .color(0x5c1b5e)
            .build();
    public static final Material LemonLimeSolution = fluidBuilderFood(21580, "lemon_lime_solution")
            .color(0xbddb5a)
            .build();
    public static final Material LemonLimeSludge = fluidBuilderFood(21581, "lemon_lime_sludge")
            .color(0x779906)
            .build();
    public static final Material Aniline = fluidBuilder(21582, "aniline")
            .color(0x4c911d)
            .components(Carbon, 6, Hydrogen, 7, Nitrogen, 1)
            .flags(DISABLE_DECOMPOSITION)
            .build()
            .setFormula("C6H5NH2", true);

    public static final Material HeatedWater = fluidBuilderFood(21583, "heated_water", 343)
            .color(0x024B86)
            .build();
    public static final Material GelatinSolution = fluidBuilderFood(21584, "gelatin_solution")
            .color(0xD3D3D3)
            .build();
    public static final Material AceticAnhydride = fluidBuilder(21585, "acetic_anhydride")
            .components(Carbon, 4, Hydrogen, 6, Oxygen, 3)
            .build();
    public static final Material Nitrophenols = fluidBuilder(21586, "nitrophenols")
            .color(0xFFFFFF)
            .build().setFormula("(C6H5NO3)(C6H5NO3)", true);
    public static final Material Egg = fluidBuilderFood(21587, "egg")
            .color(0xFFFF0F)
            .build();
    public static final Material UnpasteurizedSkimmedMilk = fluidBuilderFood(21588, "unpasteurized_skimmed_milk")
            .color(0xfcfcf0)
            .build();
    public static final Material ParmigianoReggianoStarter = fluidBuilder(21589, "parmigiano_reggiano_starter")
            .color(0xf0eac0)
            .build();
    public static final Material CurdlingParmigianoReggiano = fluidBuilder(21590, "curdling_parmigiano_reggiano")
            .color(0xfff4ab)
            .build();
    public static final Material Agrodolce = fluidBuilderFood(21591, "agrodolce")
            .color(0xba1430)
            .build();
    public static final Material Polenta = fluidBuilderFood(21592, "polenta")
            .color(0xBBA844)
            .build();
    public static final Material Pesto = fluidBuilderFood(21593, "pesto")
            .color(0x309027)
            .build();
    public static final Material BechamelSauce = fluidBuilderFood(21594, "bechamel_sauce")
            .color(0xD1B7AC)
            .build();
    public static final Material ChickenBroth = fluidBuilderFood(21595, "chicken_broth")
            .color(0xA4600D)
            .build();
    public static final Material VitelloTonnatoSauce = fluidBuilderFood(21596, "vitello_tonnato_sauce")
            .color(0xC6BABE)
            .build();
    public static final Material WhiteWine = fluidBuilder(21597, "white_wine")
            .color(0xD7C259)
            .build();
    public static final Material MaceratedWhiteGrapes = fluidBuilder(21598, "macerated_white_grapes")
            .color(0x8C9D41)
            .build();
    public static final Material PressedWhiteWort = fluidBuilder(21599, "pressed_white_wort")
            .color(0xDEF37F)
            .build();

    public static final Material ClarifiedWhiteWort = fluidBuilder(21600, "clarified_white_wort")
            .color(0xD8E4A4)
            .build();
    public static final Material VitelloTonnatoFlavorant = fluidBuilderFood(21601, "vitello_tonnato_flavorant")
            .color(0xC2ACB0)
            .build();
    public static final Material RafanataMixture = fluidBuilderFood(21602, "rafanata_mixture")
            .color(0xDCB239)
            .build();
    public static final Material CarbonaraSauce = fluidBuilderFood(21603, "carbonara_sauce")
            .color(0xCDAF44)
            .build();
    public static final Material PastaEFagioliBase = fluidBuilderFood(21604, "pasta_e_fagioli_base", 343)
            .color(0xD4592F)
            .build();
    public static final Material MixedPastaEFagioli = fluidBuilderFood(21605, "mixed_pasta_e_fagioli", 343)
            .color(0xE48628)
            .build();

    public static final Material RedGrapesMust = fluidBuilder(21606, "red_grapes_must")
            .color(0xD32552)
            .build();
    public static final Material FermentedRedGrapesMust = fluidBuilder(21607, "fermented_red_grapes_must")
            .color(0xA83351)
            .build();
    public static final Material AlcoholicRedGrapeJuice = fluidBuilder(21608, "alcoholic_red_grape_juice")
            .color(0xA4002A)
            .build();
    public static final Material RedWine = fluidBuilder(21609, "red_wine")
            .color(0x641126)
            .build();
    public static final Material BologneseSauce = fluidBuilderFood(21610, "bolognese_sauce")
            .color(0x782A14)
            .build();
    public static final Material TomatoBologneseSauce = fluidBuilderFood(21611, "tomato_bolognese_sauce")
            .color(0xCA190F)
            .build();

    public static final Material CranberryExtract = fluidBuilderFood(21612, "cranberry_extract")
            .color(0x8C0D22)
            .build();
    public static final Material EtirpsCranberry = fluidBuilderFood(21613, "etirps_cranberry")
            .color(averageRGB(2, CranberryExtract.getMaterialRGB(), 0xbbddbb))
            .build();
    public static final Material CranberrySludge = fluidBuilderFood(21614, "cranberry_sludge")
            .color(averageRGB(2, CranberryExtract.getMaterialRGB(), 0x222222))
            .build();
    public static final Material CranberrySodaSyrup = fluidBuilderFood(21615, "cranberry_soda_syrup")
            .color(averageRGB(2, CranberryExtract.getMaterialRGB(), 0x333333))
            .build();

    public static final Material LingonberryJam = fluidBuilderFood(21616, "lingonberry_jam")
            .color(0x61262D)
            .build();
    public static final Material ElderberryJam = fluidBuilderFood(21617, "elderberry_jam")
            .color(0x5F414F)
            .build();

    public static final Material SourCream = fluidBuilderFood(21618, "sour_cream")
            .color(0xe3de9d)
            .build();
    public static final Material LacticAcidBacteria = fluidBuilder(21619, "lactic_acid_bacteria")
            .color(0x371040)
            .build();

    public static final Material FungalRennetSolution = fluidBuilder(21620, "fungal_rennet_solution")
            .color(0x2a7b5a)
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
            .flags(DECOMPOSITION_BY_CENTRIFUGING)
            .components(Water, 1, SodaAsh, 1)
            .build();

    public static final Material Coffee = fluidBuilderFood(21990, "coffee", 368)
            .color(0x36312e)
            .build();
    public static final Material EnergizedCoffee = fluidBuilderFood(21991, "energized_coffee", 368)
            .color(0x695934)
            .build();


    public static final GTFOOredictItem.OreDictValueItem COFFEE_GROUNDS = SHAPED_ITEM.addOreDictItem(1017, "coffee_grounds", 0x1a1612, MaterialIconSet.DULL, OrePrefix.dust);
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

    public static final MetaOreDictItem.OreDictValueItem HotAppleHardCandy = SHAPED_ITEM.addFoodOreDictItem(1084, "hot_apple_hard_candy", 0x78e32b, MaterialIconSet.DULL, OrePrefix.plate);
    /*
        public static final GTFOOredictItem.OreDictItem BacillusSubtilis = new GTFOOredictItem.OreDictItem(1085, "bacillus_subtilis", 0xe0386b, MaterialIconSet.ROUGH, OrePrefix.dust, "Bacteria");
        public static final GTFOOredictItem.OreDictItem LactobacillusPentosis = new GTFOOredictItem.OreDictItem(1086, "lactobacillus_pentosis", 0x87316f, MaterialIconSet.ROUGH, OrePrefix.dust, "Bacteria");
        public static final GTFOOredictItem.OreDictItem FructoseConversionPlate = new GTFOOredictItem.OreDictItem(1087, "fructose_conversion_plate", 0xe0e0dc, MaterialIconSet.SHINY, OrePrefix.plate);
        public static final GTFOOredictItem.OreDictItem XyloseIsomerase = new GTFOOredictItem.OreDictItem(1088, "xylose_isomerase", 0x9718ea, MaterialIconSet.SAND, OrePrefix.dust);
        public static final GTFOOredictItem.OreDictItem AlphaAmylase = new GTFOOredictItem.OreDictItem(1089, "alpha_amylase", 0x69D992, MaterialIconSet.SAND, OrePrefix.dust);
        public static final GTFOOredictItem.OreDictItem GammaAmylase = new GTFOOredictItem.OreDictItem(1090, "gamma_amylase", 0x4FCE67, MaterialIconSet.SAND, OrePrefix.dust);
        public static final GTFOOredictItem.OreDictItem CornStarch = new GTFOOredictItem.OreDictItem(1091, "corn_starch", 0xfff5f5, MaterialIconSet.ROUGH, OrePrefix.dust);
    */
    public static final GTFOOredictItem.OreDictValueItem CrushedHardCandy = SHAPED_ITEM.addFoodOreDictItem(1093, "crushed_hard_candy", 0x52a302, MaterialIconSet.DULL, OrePrefix.crushed);
    public static final GTFOOredictItem.OreDictValueItem Promethazine = SHAPED_ITEM.addOreDictItem(1094, "promethazine", 0xf8fade, MaterialIconSet.SAND, OrePrefix.dust, "C17H20N2S");
    public static final GTFOOredictItem.OreDictValueItem Codeine = SHAPED_ITEM.addOreDictItem(1095, "codeine", 0xfadef2, MaterialIconSet.SAND, OrePrefix.dust, "C18H21NO3");
    public static final GTFOOredictItem.OreDictValueItem Phenothiazine = SHAPED_ITEM.addOreDictItem(1096, "phenothiazine", 0x67735c, MaterialIconSet.SAND, OrePrefix.dust, "C12H9NS");
    public static final GTFOOredictItem.OreDictValueItem Diphenylamine = SHAPED_ITEM.addOreDictItem(1097, "diphenylamine", 0xe3932b, MaterialIconSet.SAND, OrePrefix.dust, "C12H11N");
    public static final GTFOOredictItem.OreDictValueItem CrushedPoppy = SHAPED_ITEM.addOreDictItem(1098, "crushed_poppy", 0x940801, MaterialIconSet.ROUGH, OrePrefix.dust, "You monster.");
    public static final GTFOOredictItem.OreDictValueItem HardCandyPlate = SHAPED_ITEM.addFoodOreDictItem(1099, "hard_candy", 0x78e32b, MaterialIconSet.ROUGH, OrePrefix.plate);
    public static final GTFOOredictItem.OreDictValueItem HardCandyResin = SHAPED_ITEM.addFoodOreDictItem(1100, "hard_candy", 0x78e32b, MaterialIconSet.ROUGH, OrePrefix.plateDense);

    public static final GTFOOredictItem.OreDictValueItem Zest = SHAPED_ITEM.addFoodOreDictItem(1092, "zest", 0xd8ff4a, MaterialIconSet.SAND, dust);
    public static final GTFOOredictItem.OreDictValueItem PotatoStarch = SHAPED_ITEM.addFoodOreDictItem(1101, "potato_starch", 0xdedcb1, MaterialIconSet.ROUGH, dust);

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

    public static final GTFOOredictItem.OreDictValueItem MashedPotato = SHAPED_ITEM.addFoodOreDictItem(1115, "mashed_potato", 0xf5d89f, MaterialIconSet.FINE, dust);

    public static final GTFOOredictItem.OreDictValueItem ToughMeat = SHAPED_ITEM.addFoodOreDictItem(1116, "tough_meat", 0xa63028, MaterialIconSet.ROUGH, dust);

    public static final GTFOOredictItem.OreDictValueItem KubideMeat = SHAPED_ITEM.addOreDictItem(1117, "kubide_meat", 0x9B0600, Organic, dust);
    public static final GTFOOredictItem.OreDictValueItem BargMeat = SHAPED_ITEM.addFoodOreDictItem(1118, "barg_meat", 0x7F0000, Organic, dust);
    public static final GTFOOredictItem.OreDictValueItem Fat = SHAPED_ITEM.addFoodOreDictItem(1119, "fat", 0xFFF200, Organic, ingot, "C57H110O6"); // yea Fat is much more complicated, but I just stick to this formula...

    public static final GTFOOredictItem.OreDictValueItem MeatIngot = SHAPED_ITEM.addFoodOreDictItem(1120, "cooked_meat", 0xa63028, MaterialIconSet.ROUGH, ingot);
    //public static final GTFOOredictItem.OreDictValueItem SlimeIngot = SHAPED_ITEM.addOreDictItem(1120, "slime_ingot", 0x84C873, Organic, OrePrefix.ingot);

    public static final GTFOOredictItem.OreDictValueItem SodiumPerchlorate = SHAPED_ITEM.addOreDictItem(1121, "sodium_perchlorate", averageRGB(3, Sodium.getMaterialRGB(), Oxygen.getMaterialRGB(), 0xFFFFFF), MaterialIconSet.ROUGH, dust, "NaClO4");
    public static final GTFOProxyItem SodiumChlorate = new GTFOProxyItem(() -> SHAPED_ITEM.addOreDictItem(1122, "sodium_chlorate", averageRGB(2, Sodium.getMaterialRGB(), Oxygen.getMaterialRGB()), MaterialIconSet.ROUGH, dust, "NaClO3"), 1122, "sodium_chlorate", () -> OreDictUnifier.get(dust, GregTechAPI.materialManager.getMaterial("sodium_chlorate")));

    public static final GTFOOredictItem.OreDictValueItem VanillylmandelicAcid = SHAPED_ITEM.addOreDictItem(1123, "vanillylmandelic_acid", 0xf2efbd, MaterialIconSet.ROUGH, dust, "C9H10O5");
    public static final GTFOOredictItem.OreDictValueItem VanilglycolicAcid = SHAPED_ITEM.addOreDictItem(1124, "vanilglycolic_acid", 0xebe7a4, MaterialIconSet.DULL, dust, "C9H8O5");
    public static final GTFOOredictItem.OreDictValueItem Vanillin = SHAPED_ITEM.addOreDictItem(1125, "vanillin", 0xfbfbfb, MaterialIconSet.SHINY, OrePrefix.dust, "C8H8O3");

    public static final GTFOProxyItem ArsenicTrioxide = new GTFOProxyItem(() -> SHAPED_ITEM.addOreDictItem(1126, "arsenic_trioxide", averageRGB(2, Arsenic.getMaterialRGB(), Oxygen.getMaterialRGB()), MaterialIconSet.ROUGH, dust, "As2O3"), 1126, "arsenic_trioxide", () -> OreDictUnifier.get(dust, GregTechAPI.materialManager.getMaterial("arsenic_trioxide")));
    public static final GTFOOredictItem.OreDictValueItem CupricHydrogenArsenite = SHAPED_ITEM.addOreDictItem(1127, "cupric_hydrogen_arsenite", 0x0fff00, MaterialIconSet.SHINY, OrePrefix.dust, "CuHAsO3");
    public static final GTFOOredictItem.OreDictValueItem LaminatedDough = SHAPED_ITEM.addFoodOreDictItem(1128, "laminated_dough", 0xc6b4bb, MaterialIconSet.DULL, plate);
    public static final MetaOreDictItem.OreDictValueItem CookedMinceMeat = SHAPED_ITEM.addFoodOreDictItem(1129, "cooked_mince_meat", 0x462b25, MaterialIconSet.ROUGH, dust);
    public static final MetaOreDictItem.OreDictValueItem Aminophenol = SHAPED_ITEM.addOreDictItem(1130, "aminophenol", 0xFFFFFF, MaterialIconSet.SHINY, dust, "C6H7NO");
    public static final MetaOreDictItem.OreDictValueItem IVNitrophenol = SHAPED_ITEM.addOreDictItem(1131, "ivnitrophenol", 0xFFFFE0, MaterialIconSet.SHINY, dust, "C6H5NO3");
    public static final MetaOreDictItem.OreDictValueItem IINitrophenol = SHAPED_ITEM.addOreDictItem(1132, "iinitrophenol", 0xFFFF00, MaterialIconSet.SHINY, dust, "C6H5NO3");
    public static final MetaOreDictItem.OreDictValueItem ShreddedParmesan = SHAPED_ITEM.addFoodOreDictItem(1133, "shredded_parmesan", 0xdbd6b4, MaterialIconSet.DULL, dust);
    public static final MetaOreDictItem.OreDictValueItem BlackPepper = SHAPED_ITEM.addFoodOreDictItem(1134, "black_pepper", 0x02030f, MaterialIconSet.DULL, dust);
    public static final MetaOreDictItem.OreDictValueItem Nutmeg = SHAPED_ITEM.addFoodOreDictItem(1135, "nutmeg", 0x391A0C, MaterialIconSet.DULL, dust);
    public static final MetaOreDictItem.OreDictValueItem GratedHorseradishRoot = SHAPED_ITEM.addFoodOreDictItem(1136, "grated_horseradish_root", 0xE5D2C1, MaterialIconSet.DULL, dust);
    public static final MetaOreDictItem.OreDictValueItem BoneChinaClay = SHAPED_ITEM.addFoodOreDictItem(1137, "bone_china_clay", 0xEEC1C1, MaterialIconSet.DULL, dust);

    public static final GTFOOredictItem.OreDictValueItem BareCornKernel = SHAPED_ITEM.addFoodOreDictItem(1001, "corn_kernel_bare", 0xfecb60, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem CornKernel = SHAPED_ITEM.addFoodOreDictItem(1000, "corn_kernel", 0xffea70, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictValueItem SodiumCyanide = SHAPED_ITEM.addOreDictItem(1138, "sodium_cyanide", averageRGB(2, HydrogenCyanide.getMaterialRGB(), Sodium.getMaterialRGB()), MaterialIconSet.DULL, OrePrefix.dust, "NaCN");
    public static final GTFOOredictItem.OreDictValueItem LithiumOxide = SHAPED_ITEM.addOreDictItem(1139, "lithium_oxide", averageRGB(2, Lithium.getMaterialRGB(), 0), MaterialIconSet.DULL, OrePrefix.dust, "Li2O");
    public static final GTFOProxyItem LithiumCarbonate = new GTFOProxyItem(() -> SHAPED_ITEM.addOreDictItem(1140, "lithium_carbonate", averageRGB(2, Lithium.getMaterialRGB(), CarbonDioxide.getMaterialRGB()), MaterialIconSet.DULL, OrePrefix.dust, "Li2CO3"),
            1140, "lithium_carbonate", () -> OreDictUnifier.get(dust, GregTechAPI.materialManager.getMaterial("lithium_carbonate")));

    public static final GTFOOredictItem.OreDictValueItem BoneAsh = SHAPED_ITEM.addOreDictItem(1141, "bone_ash", 0xFEFDFE, MaterialIconSet.DULL, OrePrefix.dust);
    public static final MetaOreDictItem.OreDictValueItem UnfiredPorcelainTile = SHAPED_ITEM.addOreDictItem(1142, "unfired_porcelain_tile", 0xC2C2C4, MaterialIconSet.ROUGH, plate);
    public static final MetaOreDictItem.OreDictValueItem BiscuitPorcelainTile = SHAPED_ITEM.addOreDictItem(1143, "bisque_porcelain_tile", 0xFEFEE8, MaterialIconSet.ROUGH, plate);
    public static final MetaOreDictItem.OreDictValueItem GlazedPorcelainTile = SHAPED_ITEM.addOreDictItem(1144, "glazed_porcelain_tile", 0xEEEEEE, MaterialIconSet.SHINY, plate);
    public static final MetaOreDictItem.OreDictValueItem BlackGlazedPorcelainTile = SHAPED_ITEM.addOreDictItem(1145, "black_glazed_porcelain_tile", 0x111111, MaterialIconSet.SHINY, plate);

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

    public static final Material Paracetamol = new Material.Builder(21900, gtfoId("paracetamol")).dust()
            .color(0x0045A0).iconSet(MaterialIconSet.SHINY)
            .components(Carbon, 8, Hydrogen, 9, Nitrogen, 1, Oxygen, 2)
            .build();

    public static void onMaterialsInit() {
        Materials.Iron.addFlags(GENERATE_FRAME);
        Materials.BismuthBronze.addFlags(GENERATE_FRAME);
        Materials.Aluminium.addFlags(GENERATE_DENSE);
        Materials.StainlessSteel.addFlags(GENERATE_DENSE, GENERATE_SPRING_SMALL);
        Materials.Titanium.addFlags(GENERATE_DENSE);
        Materials.Aluminium.addFlags(GENERATE_DENSE);

        Water.setProperty(FERTILIZER, new FertilizerProperty(5));
        Blood.setProperty(FERTILIZER, new FertilizerProperty(30));
        FertilizerSolution.setProperty(FERTILIZER, new FertilizerProperty(15));

        DistilledWater.setProperty(CLEANER, new CleanerProperty(2));
        SodiumStearate.setProperty(CLEANER, new CleanerProperty(16));
    }

    public static Material.Builder fluidBuilder(int id, String name) {
        return new Material.Builder(id, gtfoId("gtfo_" + name)).liquid();
    }

    public static Material.Builder fluidBuilderFood(int id, String name) {
        FOOD_RELATED_MATERIALS.put(id, true);
        return new Material.Builder(id, gtfoId("gtfo_" + name)).liquid();
    }

    public static Material.Builder fluidBuilderFood(int id, String name, int temp) {
        FOOD_RELATED_MATERIALS.put(id, true);
        return new Material.Builder(id, gtfoId("gtfo_" + name)).liquid(new FluidBuilder().temperature(temp));
    }
    public static Material.Builder fluidBuilderCustom(int id, String name) {
        return new Material.Builder(id, gtfoId("gtfo_" + name)).liquid(new FluidBuilder().customStill());
    }
    public static Material.Builder gasBuilder(int id, String name, int temp) {
        return new Material.Builder(id, gtfoId("gtfo_" + name)).gas(new FluidBuilder().temperature(temp));
    }
    public static Material.Builder fluidBuilder(int id, String name, FluidAttribute attribute) {
        return new Material.Builder(id, gtfoId("gtfo_" + name)).liquid(new FluidBuilder().attribute(attribute));
    }

    public static Material.Builder fluidBuilder(int id, String name, int temp) {
        return new Material.Builder(id, gtfoId("gtfo_" + name)).liquid(new FluidBuilder().temperature(temp));
    }

    public static void registerPropertyTooltips() {
        for (Material material : GregTechAPI.materialManager.getRegisteredMaterials()) {
            FertilizerProperty fertilizerProperty = material.getProperty(FERTILIZER);
            if (fertilizerProperty != null)
                FluidTooltipUtil.registerTooltip(material.getFluid(), () -> Collections.singletonList(LocalizationUtils.format("gregtechfoodoption.fluid.fertilizer", fertilizerProperty.getBoostPercentage())));
            LacingProperty lacingProperty = material.getProperty(LACING);
            if (lacingProperty != null)
                FluidTooltipUtil.registerTooltip(material.getFluid(), () -> Collections.singletonList(LocalizationUtils.format("gregtechfoodoption.fluid.lacing")));
            CleanerProperty cleanerProperty = material.getProperty(CLEANER);
            if (cleanerProperty != null)
                FluidTooltipUtil.registerTooltip(material.getFluid(), () -> Collections.singletonList(LocalizationUtils.format("gregtechfoodoption.fluid.cleaning", cleanerProperty.getCleaningPower())));
        }
    }

    public static ResourceLocation gtfoId(String path) {
        return new ResourceLocation(GTValues.MODID, path);
    }

    public static boolean isFoodRelated(Material material) {
        return FOOD_RELATED_MATERIALS.containsKey(material.getId());
    }
}
