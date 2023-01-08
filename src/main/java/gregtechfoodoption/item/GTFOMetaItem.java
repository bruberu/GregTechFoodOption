package gregtechfoodoption.item;

import gregtech.api.items.metaitem.FoodUseManager;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.api.items.metaitem.stats.IFoodBehavior;
import gregtech.api.items.metaitem.stats.IItemContainerItemProvider;
import gregtech.api.items.toolitem.IGTTool;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.RandomPotionEffect;
import gregtech.common.items.MetaItems;
import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.block.GTFOCrops;
import gregtechfoodoption.potion.CreativityPotion;
import gregtechfoodoption.potion.SnowGolemSpawnerPotion;
import gregtechfoodoption.potion.StepAssistPotion;
import gregtechfoodoption.utils.GTFOUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import squeek.applecore.api.food.FoodValues;
import squeek.applecore.api.food.IEdible;

import javax.annotation.Nonnull;

import static gregtech.api.unification.material.Materials.*;
import static net.minecraft.potion.Potion.getPotionById;


@Optional.Interface(modid = GTFOValues.MODID_AP, iface = "squeek.applecore.api.food.IEdible")
public class GTFOMetaItem extends StandardMetaItem implements IEdible {
    //foods
    public static MetaItem<?>.MetaValueItem POPCORN_BAG;
    public static MetaItem<?>.MetaValueItem PAPER_BAG;
    public static MetaItem<?>.MetaValueItem FLAVORED_POPCORN_FLAKE;
    public static MetaItem<?>.MetaValueItem POPCORN_COB;
    public static MetaItem<?>.MetaValueItem DRIED_POPCORN_EAR;
    public static MetaItem<?>.MetaValueItem POPCORN_EAR;
    public static MetaItem<?>.MetaValueItem PHYCOMYCES_BLAKESLEEANUS_CULTURE;
    public static MetaItem<?>.MetaValueItem THERMOS;
    public static MetaItem<?>.MetaValueItem THERMOS_CASING;
    public static MetaItem<?>.MetaValueItem THERMOS_CAP;
    public static MetaItem<?>.MetaValueItem LEACHED_THERMOS_CASING;
    public static MetaItem<?>.MetaValueItem USED_THERMOS;
    public static MetaItem<?>.MetaValueItem MINERAL_WATER;
    public static MetaItem<?>.MetaValueItem CORED_APPLE;
    public static MetaItem<?>.MetaValueItem APPLE_HARD_CANDY;
    public static MetaItem<?>.MetaValueItem PLASTIC_BOTTLE;
    public static MetaItem<?>.MetaValueItem SPARKLING_WATER;
    public static MetaItem<?>.MetaValueItem LEMON;
    public static MetaItem<?>.MetaValueItem LIME;
    public static MetaItem<?>.MetaValueItem ETIRPS;
    public static MetaItem<?>.MetaValueItem LACTOBACILLUS_PENTOSIS_CULTURE;
    public static MetaItem<?>.MetaValueItem BACILLUS_SUBTILIS_CULTURE;
    public static MetaItem<?>.MetaValueItem SLICER_BLADE_FLAT;
    public static MetaItem<?>.MetaValueItem SLICER_BLADE_STRIPES;
    public static MetaItem<?>.MetaValueItem PEELED_POTATO;
    public static MetaItem<?>.MetaValueItem POTATO_STRIP;
    public static MetaItem<?>.MetaValueItem POTATO_SLICE;
    public static MetaItem<?>.MetaValueItem FRIED_POTATO_SLICE;
    public static MetaItem<?>.MetaValueItem BATCH_FRIED_POTATO_SLICE;
    public static MetaItem<?>.MetaValueItem OILY_POTATO_SLICE;
    public static MetaItem<?>.MetaValueItem HOT_POTATO_SLICE;
    public static MetaItem<?>.MetaValueItem REDUCED_FAT_POTATO_SLICE;
    public static MetaItem<?>.MetaValueItem PARTIALLY_FILLED_CHIP_BAG;
    public static MetaItem<?>.MetaValueItem BLANCHED_POTATO_STRIP;
    public static MetaItem<?>.MetaValueItem FRIED_POTATO_STRIP;
    public static MetaItem<?>.MetaValueItem USED_PAPER_BAG;
    public static MetaItem<?>.MetaValueItem FRENCH_FRIES;
    public static MetaItem<?>.MetaValueItem SYALS;
    public static MetaItem<?>.MetaValueItem BAG_OF_CHIPS;
    public static MetaItem<?>.MetaValueItem KETTLE_FRIED_CHIPS;
    public static MetaItem<?>.MetaValueItem REDUCED_FAT_CHIPS;
    public static MetaItem<?>.MetaValueItem POTATO_ON_A_STICK;
    public static MetaItem<?>.MetaValueItem MUD_BRICK;
    public static MetaItem<?>.MetaValueItem ADOBE_BRICK;
    public static MetaItem<?>.MetaValueItem WOODEN_FORM_BREAD;
    public static MetaItem<?>.MetaValueItem WOODEN_FORM_BAGUETTE;
    public static MetaItem<?>.MetaValueItem UNCOOKED_BREAD;
    public static MetaItem<?>.MetaValueItem UNCOOKED_BAGUETTE;
    public static MetaItem<?>.MetaValueItem BAGUETTE;
    public static MetaItem<?>.MetaValueItem DOUGH;
    public static MetaItem<?>.MetaValueItem CARROT_STRUCTURAL_MESH;
    public static MetaItem<?>.MetaValueItem APPLE_STRUCTURAL_MESH;
    public static MetaItem<?>.MetaValueItem TUNGSTENSTEEL_APPLE;
    public static MetaItem<?>.MetaValueItem SUGARY_DOUGH;
    public static MetaItem<?>.MetaValueItem CAKE_BOTTOM;
    public static MetaItem<?>.MetaValueItem BAKED_CAKE_BOTTOM;
    public static MetaItem<?>.MetaValueItem PIE_CRUST;
    public static MetaItem<?>.MetaValueItem SANDWICH_VEGGIE;
    public static MetaItem<?>.MetaValueItem SANDWICH_CHEESE;
    public static MetaItem<?>.MetaValueItem SANDWICH_BACON;
    public static MetaItem<?>.MetaValueItem SANDWICH_STEAK;
    public static MetaItem<?>.MetaValueItem SANDWICH_LARGE_VEGGIE;
    public static MetaItem<?>.MetaValueItem SANDWICH_LARGE_CHEESE;
    public static MetaItem<?>.MetaValueItem SANDWICH_LARGE_BACON;
    public static MetaItem<?>.MetaValueItem SANDWICH_LARGE_STEAK;
    public static MetaItem<?>.MetaValueItem PIZZA_VEGGIE_RAW;
    public static MetaItem<?>.MetaValueItem PIZZA_CHEESE_RAW;
    public static MetaItem<?>.MetaValueItem PIZZA_MINCE_MEAT_RAW;
    public static MetaItem<?>.MetaValueItem PIZZA_VEGGIE;
    public static MetaItem<?>.MetaValueItem PIZZA_CHEESE;
    public static MetaItem<?>.MetaValueItem PIZZA_MINCE_MEAT;
    public static MetaItem<?>.MetaValueItem OLIVE;
    public static MetaItem<?>.MetaValueItem OLIVE_SLICE;
    public static MetaItem<?>.MetaValueItem MUSHROOM_SLICE;
    public static MetaItem<?>.MetaValueItem TOMATO;
    public static MetaItem<?>.MetaValueItem ONION;
    public static MetaItem<?>.MetaValueItem CUCUMBER;
    public static MetaItem<?>.MetaValueItem TOMATO_SLICE;
    public static MetaItem<?>.MetaValueItem ONION_SLICE;
    public static MetaItem<?>.MetaValueItem CUCUMBER_SLICE;
    public static MetaItem<?>.MetaValueItem WOODEN_FORM_BUN;
    public static MetaItem<?>.MetaValueItem UNCOOKED_BUN;
    public static MetaItem<?>.MetaValueItem BUN;
    public static MetaItem<?>.MetaValueItem PRESLICED_BUN;
    public static MetaItem<?>.MetaValueItem PRESLICED_BREAD;
    public static MetaItem<?>.MetaValueItem PRESLICED_BAGUETTE;
    public static MetaItem<?>.MetaValueItem BURGER_VEGGIE;
    public static MetaItem<?>.MetaValueItem BURGER_CHEESE;
    public static MetaItem<?>.MetaValueItem BURGER_MEAT;
    public static MetaItem<?>.MetaValueItem BEEF_SLICE;
    public static MetaItem<?>.MetaValueItem CHEDDAR_CURD_MOLD;
    public static MetaItem<?>.MetaValueItem AGED_CHEDDAR_MOLD;
    public static MetaItem<?>.MetaValueItem MOZZARELLA_SLICE;
    public static MetaItem<?>.MetaValueItem MOZZARELLA_BALL;
    public static MetaItem<?>.MetaValueItem RICOTTA;
    public static MetaItem<?>.MetaValueItem CHEDDAR_BLOCK;
    public static MetaItem<?>.MetaValueItem CHEDDAR_SLICE;
    public static MetaItem<?>.MetaValueItem FLAT_DOUGH;
    public static MetaItem<?>.MetaValueItem BACON;
    public static MetaItem<?>.MetaValueItem UNCOOKED_BACON;
    public static IGTTool ROLLING_PIN;
    public static IGTTool BUTCHERY_KNIFE_HV;
    public static MetaItem<?>.MetaValueItem EIGHT_SMORE;
    public static MetaItem<?>.MetaValueItem SIXTEEN_SMORE;
    public static MetaItem<?>.MetaValueItem THIRTY_TWO_SMORE;
    public static MetaItem<?>.MetaValueItem SIXTY_FOUR_SMORE;
    public static MetaItem<?>.MetaValueItem SMOGUS;
    public static MetaItem<?>.MetaValueItem MORE_SMOGUS;
    public static MetaItem<?>.MetaValueItem FOUR_SMOGUS;
    public static MetaItem<?>.MetaValueItem HEART_SMOGUS;
    public static MetaItem<?>.MetaValueItem GORGONZOLA_WHEEL;
    public static MetaItem<?>.MetaValueItem SALTED_GORGONZOLA_WHEEL;
    public static MetaItem<?>.MetaValueItem SLIGHTLY_AGED_GORGONZOLA_WHEEL;
    public static MetaItem<?>.MetaValueItem PUNCTURED_GORGONZOLA_WHEEL;
    public static MetaItem<?>.MetaValueItem FULLY_CURED_GORGONZOLA_WHEEL;
    public static MetaItem<?>.MetaValueItem GORGONZOLA_TRIANGULAR_SLICE;
    public static MetaItem<?>.MetaValueItem PENICILLIUM_ROQUEFORTI_CULTURE;
    public static MetaItem<?>.MetaValueItem SLICER_BLADE_OCTAGONAL;
    public static MetaItem<?>.MetaValueItem ROTTEN_MEAT;
    public static MetaItem<?>.MetaValueItem ROTTEN_FISH;
    public static MetaItem<?>.MetaValueItem CHUM;
    public static MetaItem<?>.MetaValueItem CHUM_ON_A_STICK;
    public static MetaItem<?>.MetaValueItem BURGER_CHUM;
    public static MetaItem<?>.MetaValueItem BANANA;
    public static MetaItem<?>.MetaValueItem ORANGE;
    public static MetaItem<?>.MetaValueItem GRAPES;
    public static MetaItem<?>.MetaValueItem MANGO;
    public static MetaItem<?>.MetaValueItem APRICOT;
    public static MetaItem<?>.MetaValueItem BANANA_PEEL;
    public static MetaItem<?>.MetaValueItem PEELED_BANANA;
    public static MetaItem<?>.MetaValueItem VODKA;
    public static MetaItem<?>.MetaValueItem LENINADE;
    public static MetaItem<?>.MetaValueItem HOT_MUSHROOM_STEW;
    public static MetaItem<?>.MetaValueItem HOT_BEETROOT_SOUP;
    public static MetaItem<?>.MetaValueItem HOT_RABBIT_STEW;
    public static MetaItem<?>.MetaValueItem SCRAP_MEAT;
    public static MetaItem<?>.MetaValueItem SKEWER;
    public static MetaItem<?>.MetaValueItem KEBAB_KUBIDEH;
    public static MetaItem<?>.MetaValueItem KEBAB_KUBIDEH_COOKED;
    public static MetaItem<?>.MetaValueItem KEBAB_BARG;
    public static MetaItem<?>.MetaValueItem KEBAB_BARG_COOKED;
    public static MetaItem<?>.MetaValueItem KEBAB_SOLTANI;
    //    public static MetaItem<?>.MetaValueItem KEBAB_DANDE;
//    public static MetaItem<?>.MetaValueItem KEBAB_LIVER;
    public static MetaItem<?>.MetaValueItem KEBAB_ONION;
    public static MetaItem<?>.MetaValueItem KEBAB_ONION_COOKED;
    public static MetaItem<?>.MetaValueItem KEBAB_TOMATO;
    public static MetaItem<?>.MetaValueItem KEBAB_TOMATO_COOKED;
    //    public static MetaItem<?>.MetaValueItem KEBAB_PEPPER;
//    public static MetaItem<?>.MetaValueItem KEBAB_MUSHROOM;
//    public static MetaItem<?>.MetaValueItem KEBAB_MIX;
    public static MetaItem<?>.MetaValueItem KEBAB_MEAT;
    public static MetaItem<?>.MetaValueItem KEBAB_MEAT_COOKED;
    //    public static MetaItem<?>.MetaValueItem KEBAB_LAMB;
//    public static MetaItem<?>.MetaValueItem KEBAB_CHICKEN;
    public static MetaItem<?>.MetaValueItem KEBAB_FAT;
    public static MetaItem<?>.MetaValueItem KEBAB_FAT_COOKED;
    //    public static MetaItem<?>.MetaValueItem KEBAB_FISH;
    public static MetaItem<?>.MetaValueItem KEBAB_CHUM;
    public static MetaItem<?>.MetaValueItem KEBAB_CHUM_COOKED;
    public static MetaItem<?>.MetaValueItem KEBAB_CHUM_BUCKET;
    public static MetaItem<?>.MetaValueItem KEBAB_CARROT;
    public static MetaItem<?>.MetaValueItem KEBAB_CARROT_COOKED;
    public static MetaItem<?>.MetaValueItem CARROT_SLICE;
    public static MetaItem<?>.MetaValueItem APPLE_SLICE;
    public static MetaItem<?>.MetaValueItem MILK_CHOCOLATE;
    public static MetaItem<?>.MetaValueItem MARSHMALLOW;
    public static MetaItem<?>.MetaValueItem FOURSMORE_QUADSMINGOT;
    public static MetaItem<?>.MetaValueItem MORESMORE_DOUBLESMINGOT;
    public static MetaItem<?>.MetaValueItem SMORE_SMINGOT;
    public static MetaItem<?>.MetaValueItem GRAHAM_CRACKER;
    public static MetaItem<?>.MetaValueItem COCOA_NIBS;
    public static MetaItem<?>.MetaValueItem COFFEE_CHERRY;
    public static MetaItem<?>.MetaValueItem ENERGIZING_COFFEE_CUP;
    public static MetaItem<?>.MetaValueItem COFFEE_CUP;

    //juice
    public static MetaItem<?>.MetaValueItem ORANGE_JUICE;
    public static MetaItem<?>.MetaValueItem APPLE_JUICE;

    //seed
    public static MetaItem<?>.MetaValueItem UNKNOWN_SEED;
    public static MetaItem<?>.MetaValueItem ONION_SEED;
    public static MetaItem<?>.MetaValueItem SOYBEAN;
    public static MetaItem<?>.MetaValueItem SOYBEAN_SEED;
    public static MetaItem<?>.MetaValueItem TOMATO_SEED;
    public static MetaItem<?>.MetaValueItem GRAPE_SEED;
    public static MetaItem<?>.MetaValueItem CUCUMBER_SEED;
    public static MetaItem<?>.MetaValueItem COFFEE_SEED;

    //ice cream
    public static MetaItem<?>.MetaValueItem ICE_CREAM_PLAIN;
    public static MetaItem<?>.MetaValueItem ICE_CREAM_CHUM;
    public static MetaItem<?>.MetaValueItem ICE_CREAM_VANILLA;
    public static MetaItem<?>.MetaValueItem ICE_CREAM_CHOCOLATE;
    public static MetaItem<?>.MetaValueItem ICE_CREAM_BACON;
    public static MetaItem<?>.MetaValueItem ICE_CREAM_BANANA;
    public static MetaItem<?>.MetaValueItem ICE_CREAM_LEMON;
    public static MetaItem<?>.MetaValueItem ICE_CREAM_MELON;
    public static MetaItem<?>.MetaValueItem ICE_CREAM_BEAR;
    public static MetaItem<?>.MetaValueItem ICE_CREAM_CHIP;
    public static MetaItem<?>.MetaValueItem ICE_CREAM_RAINBOW;

    //misc items
    public static MetaItem<?>.MetaValueItem GELATIN;
    public static MetaItem<?>.MetaValueItem ROASTED_COCOA_BEANS;
    public static MetaItem<?>.MetaValueItem PAPER_CONE;
    public static MetaItem<?>.MetaValueItem EMPTY_CUP;
    public static MetaItem<?>.MetaValueItem UNFIRED_CUP;

    public static MetaItem<?>.MetaValueItem MUSHY_PEAS;
    public static MetaItem<?>.MetaValueItem PEA_POD;
    public static MetaItem<?>.MetaValueItem PEAS;
    public static MetaItem<?>.MetaValueItem SANDWICH_TOAST;
    public static MetaItem<?>.MetaValueItem FISH_AND_CHIPS;
    public static MetaItem<?>.MetaValueItem FULL_BREAKFAST;
    public static MetaItem<?>.MetaValueItem SHEPHERDS_PIE;
    public static MetaItem<?>.MetaValueItem SAUSAGE_ROLL;
    public static MetaItem<?>.MetaValueItem SAUSAGE;
    public static MetaItem<?>.MetaValueItem BEANS;
    public static MetaItem<?>.MetaValueItem BAKED_BEANS;
    public static MetaItem<?>.MetaValueItem BEANS_ON_TOAST;
    public static MetaItem<?>.MetaValueItem TOAST;
    public static MetaItem<?>.MetaValueItem BREAD_SLICE;
    public static MetaItem<?>.MetaValueItem FRIED_FISH;
    public static MetaItem<?>.MetaValueItem BEER;
    public static MetaItem<?>.MetaValueItem BEANS_WITH_SAUCE;
    public static MetaItem<?>.MetaValueItem UNCOOKED_SAUSAGE_ROLL;
    public static MetaItem<?>.MetaValueItem NILK;

    public static MetaItem<?>.MetaValueItem SPRINKLER_COVER;


    public GTFOMetaItem() {
        super();
    }

    @Override
    public void registerSubItems() {
        IItemContainerItemProvider selfContainerItemProvider = itemStack -> itemStack;


        PAPER_BAG = addItem(1, "component.bag");
        FLAVORED_POPCORN_FLAKE = addItem(2, "component.popcorn.flavored_flake");
        POPCORN_COB = addItem(3, "component.popcorn.cob");
        DRIED_POPCORN_EAR = addItem(4, "component.popcorn.dried_ear");
        POPCORN_EAR = addItem(5, "component.popcorn.ear");
        //PHYCOMYCES_BLAKESLEEANUS_CULTURE = addItem(6, "culture.phycomyces");

        THERMOS = addItem(7, "component.thermos.new");
        USED_THERMOS = addItem(8, "component.thermos.used");
        LEACHED_THERMOS_CASING = addItem(9, "component.thermos.casing_leached");
        THERMOS_CAP = addItem(10, "component.thermos.cap");
        THERMOS_CASING = addItem(11, "component.thermos.casing");

        CORED_APPLE = addItem(13, "component.cored_apple");
        PLASTIC_BOTTLE = addItem(15, "component.plastic_bottle");
        //LACTOBACILLUS_PENTOSIS_CULTURE = addItem(20, "culture.lactobacillus");
        //BACILLUS_SUBTILIS_CULTURE = addItem(21, "culture.bacillus");

        SLICER_BLADE_FLAT = addItem(23, "config.slicer_blade.flat");
        SLICER_BLADE_STRIPES = addItem(24, "config.slicer_blade.stripes");

        PEELED_POTATO = addItem(25, "component.potato.peeled");
        POTATO_STRIP = addItem(26, "component.potato.strip");
        POTATO_SLICE = addItem(27, "component.potato.slice");
        FRIED_POTATO_SLICE = addItem(28, "component.potato.fried_slice");
        BATCH_FRIED_POTATO_SLICE = addItem(29, "component.potato.batch_fried_slice");
        OILY_POTATO_SLICE = addItem(30, "component.potato.oily_slice");
        HOT_POTATO_SLICE = addItem(31, "component.potato.hot_slice");
        REDUCED_FAT_POTATO_SLICE = addItem(32, "component.potato.reduced_fat_slice");
        PARTIALLY_FILLED_CHIP_BAG = addItem(33, "component.partially_filled_chip_bag");
        BLANCHED_POTATO_STRIP = addItem(34, "component.potato.blanched_strip");
        FRIED_POTATO_STRIP = addItem(35, "component.potato.fried_strip");
        USED_PAPER_BAG = addItem(36, "component.bag_used");

        MUD_BRICK = addItem(43, "brick.adobe");
        ADOBE_BRICK = addItem(44, "brick.adobe_fired");

        WOODEN_FORM_BREAD = addItem(45, "wooden_form.bread").addComponents(selfContainerItemProvider);
        WOODEN_FORM_BAGUETTE = addItem(46, "wooden_form.baguette").addComponents(selfContainerItemProvider);
        UNCOOKED_BREAD = addItem(47, "component.bread");
        UNCOOKED_BAGUETTE = addItem(48, "component.baguette");
        DOUGH = addItem(50, "component.dough")
                .addOreDict("foodDough");

        CARROT_STRUCTURAL_MESH = addItem(52, "component.structural_mesh.carrot");
        APPLE_STRUCTURAL_MESH = addItem(53, "component.structural_mesh.apple");
        SUGARY_DOUGH = addItem(55, "component.sugary_dough");
        PIE_CRUST = addItem(58, "component.pie_crust");

        PIZZA_CHEESE_RAW = addItem(59, "component.pizza.cheese");
        PIZZA_VEGGIE_RAW = addItem(60, "component.pizza.veggie");
        PIZZA_MINCE_MEAT_RAW = addItem(61, "component.pizza.mince_meat");

        OLIVE = addItem(73, "crop.olive")
                .addOreDict("cropOlive");
        OLIVE_SLICE = addItem(74, "component.olive_slice");
        MUSHROOM_SLICE = addItem(75, "component.mushroom_slice");

        TOMATO = addItem(76, "crop.tomato")
                .addOreDict("cropTomato");
        ONION = addItem(77, "crop.onion")
                .addOreDict("cropOnion");
        CUCUMBER = addItem(78, "crop.cucumber")
                .addOreDict("cropCucumber");
        CUCUMBER.addComponents(new GTFOCropSeedBehaviour(GTFOCrops.CROP_TOMATO, CUCUMBER.getStackForm(), CUCUMBER.getStackForm()));

        WOODEN_FORM_BUN = addItem(82, "wooden_form.bun").addComponents(selfContainerItemProvider);
        UNCOOKED_BUN = addItem(83, "component.bun");

        PRESLICED_BUN = addItem(84, "component.buns");
        PRESLICED_BREAD = addItem(85, "component.breads");
        PRESLICED_BAGUETTE = addItem(86, "component.baguettes");

        BEEF_SLICE = addItem(91, "component.beef_slice");
        CHEDDAR_CURD_MOLD = addItem(92, "component.cheddar_curd_mold");
        AGED_CHEDDAR_MOLD = addItem(93, "component.aged_cheddar_mold");
        MOZZARELLA_SLICE = addItem(94, "component.mozzarella_slice")
                .addOreDict("foodCheese");
        RICOTTA = addItem(95, "component.ricotta_piece")
                .addOreDict("foodCheese");
        CHEDDAR_BLOCK = addItem(96, "component.cheddar_block");

        FLAT_DOUGH = addItem(99, "component.flat_dough");

        UNCOOKED_BACON = addItem(108, "component.bacon");

        SKEWER = addItem(135, "component.skewer");

        //GORGONZOLA_WHEEL = addItem(109, "component.gorgonzola_wheel");
        //SALTED_GORGONZOLA_WHEEL = addItem(110, "component.salted_gorgonzola_wheel");
        //SLIGHTLY_AGED_GORGONZOLA_WHEEL = addItem(111, "component.slightly_aged_gorgonzola_wheel");
        //PUNCTURED_GORGONZOLA_WHEEL = addItem(112, "component.punctured_gorgonzola_wheel");
        //FULLY_CURED_GORGONZOLA_WHEEL = addItem(113, "component.fully_cured_gorgonzola_wheel");
        //PENICILLIUM_ROQUEFORTI_CULTURE = addItem(115, "culture.penicillium");
        SLICER_BLADE_OCTAGONAL = addItem(116, "config.slicer_blade.octagonal");

        BANANA_PEEL = addItem(127, "component.banana_peel");

        SCRAP_MEAT = addItem(134, "component.scrap_meat");

        GELATIN = addItem(196, "component.gelatin");
        ROASTED_COCOA_BEANS = addItem(197, "component.roasted_beans");
        COCOA_NIBS = addItem(198, "component.cocoa_nibs");
        EMPTY_CUP = addItem(199, "component.empty_cup");
        PAPER_CONE = addItem(200, "component.paper_cone");
        COFFEE_CHERRY = addItem(201, "crop.coffee");
        UNFIRED_CUP = addItem(205, "component.unfired_cup");

        KEBAB_KUBIDEH = addItem(136, "component.kebab.kubide");
        KEBAB_BARG = addItem(138, "component.kebab.barg");
        KEBAB_ONION = addItem(141, "component.kebab.onion");
        KEBAB_TOMATO = addItem(143, "component.kebab.tomato");
        KEBAB_CHUM = addItem(145, "component.kebab.chum");
        KEBAB_CARROT = addItem(149, "component.kebab.carrot");
        KEBAB_FAT = addItem(150, "component.kebab.fat");
        KEBAB_MEAT = addItem(156, "component.kebab.meat");

        BEANS_WITH_SAUCE = addItem(221, "component.beans_with_sauce");
        UNCOOKED_SAUSAGE_ROLL = addItem(223, "component.uncooked_sausage_roll");

        POPCORN_BAG = addItem(0, "food.popcorn_bag").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.popcornHunger, GTFOConfig.gtfoFoodConfig.popcornSaturation, false, true, PAPER_BAG.getStackForm(1),
                new RandomPotionEffect(getPotionById(10), 300, 1, 0)));
        MINERAL_WATER = addItem(12, "food.mineral_water").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.mineralWaterHunger, GTFOConfig.gtfoFoodConfig.mineralWaterSaturation, true, true, USED_THERMOS.getStackForm(1),
                new RandomPotionEffect(CreativityPotion.instance, 5000, 0, 0)));
        APPLE_HARD_CANDY = addItem(14, "food.apple_hard_candy").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.hardCandyHunger, GTFOConfig.gtfoFoodConfig.hardCandySaturation, true, false, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.REGENERATION, 1200, 1, 50))
                .setEatingDuration(24));
        SPARKLING_WATER = addItem(16, "food.sparkling_water").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.sparklingWaterHunger, GTFOConfig.gtfoFoodConfig.sparklingWaterSaturation, true, false, PLASTIC_BOTTLE.getStackForm(),
                new RandomPotionEffect(MobEffects.SPEED, 600, 1, 0)));
        LEMON = addItem(17, "food.lemon").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.lemonHunger, GTFOConfig.gtfoFoodConfig.lemonSaturation))
                .addOreDict("cropLemon").addOreDict("listAllfruit");
        LIME = addItem(18, "food.lime").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.limeHunger, GTFOConfig.gtfoFoodConfig.limeSaturation))
                .addOreDict("cropLime").addOreDict("listAllfruit");
        ETIRPS = addItem(19, "food.etirps").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.etirpsHunger, GTFOConfig.gtfoFoodConfig.etirpsSaturation, true, false, PLASTIC_BOTTLE.getStackForm(),
                new RandomPotionEffect(MobEffects.SPEED, 1200, 2, 0)));

        MetaItems.BOTTLE_PURPLE_DRINK.addComponents(new GTFOFoodStats(3, 0.2F, true, true, new ItemStack(Items.GLASS_BOTTLE),
                new RandomPotionEffect(MobEffects.HASTE, 800, 1, 10),
                new RandomPotionEffect(MobEffects.WITHER, 800, 5, 10)));

        BACON = addItem(22, "food.bacon").addComponents(new GTFOFoodStats(2, 0.8f, false, true)
                .setEatingDuration(24));

        FRENCH_FRIES = addItem(37, "food.french_fries").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.friesHunger, GTFOConfig.gtfoFoodConfig.friesSaturation, false, false, USED_PAPER_BAG.getStackForm(),
                        new RandomPotionEffect(MobEffects.STRENGTH, 1200, 1, 0))
                        .setEatingDuration(20))
                .addOreDict("foodFries");
        SYALS = addItem(38, "food.syals").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.chipHunger / 2, GTFOConfig.gtfoFoodConfig.chipSaturation / 2, false, false, () -> OreDictUnifier.get(OrePrefix.foil, Tin),
                new RandomPotionEffect(MobEffects.LEVITATION, 300, 1, 0)));
        BAG_OF_CHIPS = addItem(39, "food.bag_of_chips").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.chipHunger, GTFOConfig.gtfoFoodConfig.chipSaturation, false, false, () -> OreDictUnifier.get(OrePrefix.foil, Steel),
                new RandomPotionEffect(MobEffects.HASTE, 600, 1, 0)));
        KETTLE_FRIED_CHIPS = addItem(40, "food.kettle_chips").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.chipHunger + 1, GTFOConfig.gtfoFoodConfig.chipSaturation, false, false, () -> OreDictUnifier.get(OrePrefix.foil, Aluminium),
                new RandomPotionEffect(MobEffects.HASTE, 900, 1, 0)));
        REDUCED_FAT_CHIPS = addItem(41, "food.reduced_fat_chips").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.chipHunger, GTFOConfig.gtfoFoodConfig.chipSaturation + 1, false, false, () -> OreDictUnifier.get(OrePrefix.foil, StainlessSteel),
                new RandomPotionEffect(MobEffects.HASTE, 1200, 1, 0),
                new RandomPotionEffect(MobEffects.HASTE, 1200, 2, 50))
                .setEatingDuration(20));
        POTATO_ON_A_STICK = addItem(42, "food.potato_on_a_stick").addComponents(new GTFOFoodStats(3, 0.8f, false, false, new ItemStack(Items.STICK))
                        .setEatingDuration(12))
                .setMaxStackSize(16);

        BAGUETTE = addItem(51, "food.baguette").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.baguetteHunger, GTFOConfig.gtfoFoodConfig.baguetteSaturation, false, false, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.HASTE, 1200, 1, 50))
                .setEatingDuration(40));
        TUNGSTENSTEEL_APPLE = addItem(54, "food.tungstensteel_apple").addComponents(new GTFOFoodStats(3, 1f, false, false, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.SPEED, 1200, 2, 0),
                new RandomPotionEffect(MobEffects.RESISTANCE, 1200, 3, 0),
                new RandomPotionEffect(MobEffects.NIGHT_VISION, 3600, 2, 40),
                new RandomPotionEffect(MobEffects.INSTANT_DAMAGE, 1, 1, 0))
                .setEatingDuration(80));
        CAKE_BOTTOM = addItem(56, "food.cake_bottom").addComponents(new GTFOFoodStats(2, 0.5f, false, false, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.POISON, 200, 1, 80))
                .setEatingDuration(60));
        BAKED_CAKE_BOTTOM = addItem(57, "food.cake_bottom_baked").addComponents(new GTFOFoodStats(3, 0.5f));

        PIZZA_CHEESE = addItem(62, "food.pizza.cheese").addComponents(new GTFOFoodStats(5, 0.8f, false, false, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.HASTE, 2000, 2, 0))
                .setEatingDuration(50));
        PIZZA_VEGGIE = addItem(63, "food.pizza.veggie").addComponents(new GTFOFoodStats(5, 0.7f, false, false, ItemStack.EMPTY,
                new RandomPotionEffect(StepAssistPotion.instance, 2000, 1, 0))
                .setEatingDuration(50));
        PIZZA_MINCE_MEAT = addItem(64, "food.pizza.mince_meat").addComponents(new GTFOFoodStats(6, 0.8f, false, false, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.STRENGTH, 2000, 2, 0))
                .setEatingDuration(50));

        SANDWICH_VEGGIE = addItem(65, "food.sandwich.veggie").addComponents(new GTFOFoodStats(4, 0.6f)
                .setEatingDuration(40));
        SANDWICH_CHEESE = addItem(66, "food.sandwich.cheese").addComponents(new GTFOFoodStats(5, 0.6f)
                .setEatingDuration(40));
        SANDWICH_BACON = addItem(67, "food.sandwich.bacon").addComponents(new GTFOFoodStats(5, 0.7f)
                .setEatingDuration(40));
        SANDWICH_STEAK = addItem(68, "food.sandwich.steak").addComponents(new GTFOFoodStats(6, 0.7f)
                .setEatingDuration(40));

        SANDWICH_LARGE_VEGGIE = addItem(69, "food.sandwich.veggie.large").addComponents(new GTFOFoodStats(9, 0.6f)
                .setEatingDuration(60));
        SANDWICH_LARGE_CHEESE = addItem(70, "food.sandwich.cheese.large").addComponents(new GTFOFoodStats(11, 0.6f)
                .setEatingDuration(60));
        SANDWICH_LARGE_BACON = addItem(71, "food.sandwich.bacon.large").addComponents(new GTFOFoodStats(10, 0.7f, false, false, ItemStack.EMPTY,
                new RandomPotionEffect(StepAssistPotion.instance, 600, 0, 0))
                .setEatingDuration(60));
        SANDWICH_LARGE_STEAK = addItem(72, "food.sandwich.steak.large").addComponents(new GTFOFoodStats(13, 0.7f)
                .setEatingDuration(60));

        BUN = addItem(87, "food.bun").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.baguetteHunger / 3, GTFOConfig.gtfoFoodConfig.baguetteSaturation)
                .setEatingDuration(25));

        BURGER_VEGGIE = addItem(88, "food.burger.veggie").addComponents(new GTFOFoodStats(3, 0.6f));
        BURGER_CHEESE = addItem(89, "food.burger.cheese").addComponents(new GTFOFoodStats(4, 0.6f));
        BURGER_MEAT = addItem(90, "food.burger.meat").addComponents(new GTFOFoodStats(4, 0.7f));

        CHEDDAR_SLICE = addItem(97, "food.cheddar_slice").addComponents(new GTFOFoodStats(2, 0.2f))
                .addOreDict("foodCheese");
        MOZZARELLA_BALL = addItem(98, "food.mozzarella_ball").addComponents(new GTFOFoodStats(3, 0.6f))
                .addOreDict("foodCheese");
        GORGONZOLA_TRIANGULAR_SLICE = addItem(114, "food.gorgonzola_slice").addComponents(new GTFOFoodStats(3, 0.5f))
                .addOreDict("foodCheese");

        ROTTEN_FISH = addItem(117, "food.fish_rotten").addComponents(new GTFOFoodStats(1, 0f, false, true, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.POISON, 500, 1, 0))
                .setEatingDuration(100));
        ROTTEN_MEAT = addItem(118, "food.meat_rotten").addComponents(new GTFOFoodStats(1, 0f, false, true, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.POISON, 500, 1, 0))
                .setEatingDuration(100));
        CHUM = addItem(119, "food.chum").addComponents(new GTFOFoodStats(3, 0f, false, true, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.NAUSEA, 500, 10, 99)));
        CHUM_ON_A_STICK = addItem(120, "food.chum_on_a_stick").addComponents(new GTFOFoodStats(3, 0f, false, true, new ItemStack(Items.STICK),
                        new RandomPotionEffect(MobEffects.NAUSEA, 500, 10, 99))
                        .setEatingDuration(16))
                .setMaxStackSize(16);
        BURGER_CHUM = addItem(121, "food.burger.chum").addComponents(new GTFOFoodStats(4, 1f, false, false, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.NAUSEA, 500, 10, 99)));

        BANANA = addItem(122, "food.banana").addComponents(new GTFOFoodStats(2, 1f)
                        .setEatingDuration(60))
                .addOreDict("cropBanana").addOreDict("listAllfruit");
        ORANGE = addItem(123, "food.orange").addComponents(new GTFOFoodStats(2, 1f)
                        .setEatingDuration(50))
                .addOreDict("cropOrange").addOreDict("listAllfruit");
        GRAPES = addItem(124, "food.grapes").addComponents(new GTFOFoodStats(1, 1f)
                        .setEatingDuration(20))
                .addOreDict("cropGrapes").addOreDict("listAllfruit");
        MANGO = addItem(125, "food.mango").addComponents(new GTFOFoodStats(2, 1f))
                .addOreDict("cropMango").addOreDict("listAllfruit");
        APRICOT = addItem(126, "food.apricot").addComponents(new GTFOFoodStats(2, 1f))
                .addOreDict("cropApricot").addOreDict("listAllfruit");

        PEELED_BANANA = addItem(128, "food.peeled_banana").addComponents(new GTFOFoodStats(2, 1f)
                .setEatingDuration(12));

        VODKA = addItem(129, "food.vodka").addComponents(new GTFOFoodStats(2, 0f, true, false, new ItemStack(Items.GLASS_BOTTLE),
                new RandomPotionEffect(MobEffects.NAUSEA, 500, 1, 60)));
        LENINADE = addItem(130, "food.leninade").addComponents(new GTFOFoodStats(3, 1f, true, false, new ItemStack(Items.GLASS_BOTTLE),
                new RandomPotionEffect(MobEffects.NAUSEA, 500, 2, 70),
                new RandomPotionEffect(MobEffects.SPEED, 500, 2, 0)));

        HOT_MUSHROOM_STEW = addItem(131, "food.mushroom_stew.hot").addComponents(new GTFOFoodStats(8, 1f, false, false, new ItemStack(Items.BOWL))
                .setEatingDuration(60));
        HOT_BEETROOT_SOUP = addItem(132, "food.beetroot_soup.hot").addComponents(new GTFOFoodStats(7, 1f, false, false, new ItemStack(Items.BOWL))
                .setEatingDuration(60));
        HOT_RABBIT_STEW = addItem(133, "food.rabbit_stew.hot").addComponents(new GTFOFoodStats(9, 0.9f, false, false, new ItemStack(Items.BOWL))
                .setEatingDuration(60));

        KEBAB_KUBIDEH_COOKED = addItem(137, "food.kebab.kubide").addComponents(GTFOUtils.getKebabFood(6, 0.8f));
        KEBAB_BARG_COOKED = addItem(139, "food.kebab.barg").addComponents(GTFOUtils.getKebabFood(6, 0.5f));
        KEBAB_SOLTANI = addItem(140, "food.kebab.soltani").addComponents(GTFOUtils.getKebabFood(16, 1.1f));
        KEBAB_ONION_COOKED = addItem(142, "food.kebab.onion").addComponents(GTFOUtils.getKebabFood(2, 0.1f));
        KEBAB_TOMATO_COOKED = addItem(144, "food.kebab.tomato").addComponents(GTFOUtils.getKebabFood(2, 0.1f));
        KEBAB_CHUM_COOKED = addItem(146, "food.kebab.chum").addComponents(new GTFOFoodStats(6, 0.3f, false, true, SKEWER.getStackForm(1),
                new RandomPotionEffect(MobEffects.NAUSEA, 100, 10, 100 - 10))
                .setEatingDuration(12));
        KEBAB_CHUM_BUCKET = addItem(147, "food.kebab.chum.bucket").addComponents(new GTFOFoodStats(16, 2f, false, true, new ItemStack(Items.BUCKET),
                new RandomPotionEffect(MobEffects.NAUSEA, 500, 10, 100 - 50),
                new RandomPotionEffect(MobEffects.UNLUCK, 500, 11, 100 - 50),
                new RandomPotionEffect(MobEffects.SPEED, 500, 3, 100 - 50),
                new RandomPotionEffect(MobEffects.HEALTH_BOOST, 500, 3, 100 - 50))
                .setEatingDuration(12));
        KEBAB_CARROT_COOKED = addItem(154, "food.kebab.carrot").addComponents(GTFOUtils.getKebabFood(2, 0.2f));
        KEBAB_FAT_COOKED = addItem(151, "food.kebab.fat").addComponents(GTFOUtils.getKebabFood(3, 0.3f));

        TOMATO_SLICE = addItem(79, "component.tomato_slice").addComponents(new GTFOFoodStats(1, 0.0f));
        ONION_SLICE = addItem(80, "component.onion_slice").addComponents(new GTFOFoodStats(1, 0.0f));
        CUCUMBER_SLICE = addItem(81, "component.cucumber_slice").addComponents(new GTFOFoodStats(1, 0.0f));
        CARROT_SLICE = addItem(148, "component.carrot_slice").addComponents(new GTFOFoodStats(1, 0.0f));
        APPLE_SLICE = addItem(152, "component.apple_slice").addComponents(new GTFOFoodStats(1, 0.1f));

        APPLE_JUICE = addItem(153, "food.juice.apple").addComponents(new GTFOFoodStats(3, 0.2f, true, true, new ItemStack(Items.GLASS_BOTTLE),
                new RandomPotionEffect(MobEffects.SPEED, 500, 1, 100 - 45)));
        ORANGE_JUICE = addItem(155, "food.juice.orange").addComponents(new GTFOFoodStats(3, 0.2f, true, true, new ItemStack(Items.GLASS_BOTTLE),
                new RandomPotionEffect(MobEffects.SPEED, 500, 1, 100 - 45)));
        KEBAB_MEAT_COOKED = addItem(157, "food.kebab.meat").addComponents(GTFOUtils.getKebabFood(3, 0.6f));

        ICE_CREAM_PLAIN = addItem(165, "food.ice_cream.plain").addComponents(new GTFOFoodStats(4, 0.25f, false, true));
        ICE_CREAM_CHUM = addItem(166, "food.ice_cream.chum").addComponents(new GTFOFoodStats(5, 0.33f, false, true));
        ICE_CREAM_BANANA = addItem(167, "food.ice_cream.banana").addComponents(new GTFOFoodStats(6, 0.33f, false, true));
        ICE_CREAM_BACON = addItem(168, "food.ice_cream.bacon").addComponents(new GTFOFoodStats(6, 0.33f, false, true));
        ICE_CREAM_VANILLA = addItem(169, "food.ice_cream.vanilla").addComponents(new GTFOFoodStats(9, 0.25f, false, true, ItemStack.EMPTY,
                new RandomPotionEffect(SnowGolemSpawnerPotion.instance, 300, 0, 100 - 50)));
        ICE_CREAM_BEAR = addItem(170, "food.ice_cream.bear").addComponents(new GTFOFoodStats(7, 0.33f, false, true));
        ICE_CREAM_MELON = addItem(171, "food.ice_cream.melon").addComponents(new GTFOFoodStats(5, 0.33f, false, true));
        ICE_CREAM_CHOCOLATE = addItem(172, "food.ice_cream.chocolate").addComponents(new GTFOFoodStats(9, 0.25f, false, true));
        ICE_CREAM_LEMON = addItem(173, "food.ice_cream.lemon").addComponents(new GTFOFoodStats(6, 0.33f, false, true));
        ICE_CREAM_CHIP = addItem(174, "food.ice_cream.chip").addComponents(new GTFOFoodStats(8, 0.33f, false, true));
        ICE_CREAM_RAINBOW = addItem(225, "food.ice_cream.rainbow").addComponents(new GTFOFoodStats(6, 0.33f, false, true, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.NIGHT_VISION, 1000, 0, 100 - 50)));

        MILK_CHOCOLATE = addItem(190, "food.chocolate").addComponents(new GTFOFoodStats(4, 1.25f));
        GRAHAM_CRACKER = addItem(191, "food.graham_cracker").addComponents(new GTFOFoodStats(1, 1f));
        SMORE_SMINGOT = addItem(192, "food.smore.one").addComponents(new GTFOFoodStats(8, 1.5f));
        MORESMORE_DOUBLESMINGOT = addItem(193, "food.smore.two").addComponents(new GTFOFoodStats(20, 3.8f));
        FOURSMORE_QUADSMINGOT = addItem(194, "food.smore.four").addComponents(new GTFOFoodStats(44, 8.61363636364f));
        MARSHMALLOW = addItem(195, "food.marshmallow").addComponents(new GTFOFoodStats(1, 1f));

        COFFEE_CUP = addItem(203, "food.coffee.normal").addComponents(new GTFOFoodStats(8, 5f, true, false, EMPTY_CUP.getStackForm(), new RandomPotionEffect(MobEffects.REGENERATION, 60, 1, 0), new RandomPotionEffect(MobEffects.SPEED, 1800, 2, 0)));
        ENERGIZING_COFFEE_CUP = addItem(204, "food.coffee.energized").addComponents(new GTFOFoodStats(8, 5f, true, false, EMPTY_CUP.getStackForm(), new RandomPotionEffect(MobEffects.REGENERATION, 200, 3, 0), new RandomPotionEffect(MobEffects.STRENGTH, 200, 2, 0), new RandomPotionEffect(MobEffects.RESISTANCE, 200, 2, 0), new RandomPotionEffect(MobEffects.SPEED, 1000, 4, 0)));

        MUSHY_PEAS = addItem(209, "food.mushy_peas").addComponents(new GTFOFoodStats(3, 1));
        BREAD_SLICE = addItem(210, "food.bread_slice").addComponents(new GTFOFoodStats(1, 0.5f));
        TOAST = addItem(211, "food.toast").addComponents(new GTFOFoodStats(2, 0.5f, false, true));
        SANDWICH_TOAST = addItem(212, "food.sandwich.toast").addComponents(new GTFOFoodStats(6, 0.5f));
        FISH_AND_CHIPS = addItem(213, "food.fish_and_chips").addComponents(new GTFOFoodStats(7, 0.6f));
        FULL_BREAKFAST = addItem(214, "food.full_breakfast").addComponents(new GTFOFoodStats(10, 1.2f));
        SHEPHERDS_PIE = addItem(215, "food.shepherds_pie").addComponents(new GTFOFoodStats(9, 1f, false, false, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.ABSORPTION, 1000, 0, 100 - 50)));
        SAUSAGE_ROLL = addItem(216, "food.sausage_roll").addComponents(new GTFOFoodStats(7, 0.7f));
        BAKED_BEANS = addItem(217, "food.baked_beans").addComponents(new GTFOFoodStats(4, 1f));
        BEANS_ON_TOAST = addItem(218, "food.beans_on_toast").addComponents(new GTFOFoodStats(7, 0.8f, false, false, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.SATURATION, 500, 0, 100 - 20)));
        FRIED_FISH = addItem(219, "food.fried_fish").addComponents(new GTFOFoodStats(4, 0.3f));
        BEER = addItem(220, "food.beer").addComponents(new GTFOFoodStats(2, 0.5f, true, true, new ItemStack(Items.GLASS_BOTTLE),
                new RandomPotionEffect(MobEffects.NAUSEA, 500, 0, 100 - 40)));
        SAUSAGE = addItem(222, "food.sausage").addComponents(new GTFOFoodStats(4, 0.7f));

        NILK = addItem(226, "food.nilk").addComponents(new GTFOFoodStats(6, 4, true, true, new ItemStack(Items.GLASS_BOTTLE),
                new RandomPotionEffect(MobEffects.NAUSEA, 1000, 0, 100 - 80),
                new RandomPotionEffect(MobEffects.REGENERATION, 200, 2, 100 - 60)));


        UNKNOWN_SEED = addItem(158, "seed.unknown");
        ONION_SEED = addItem(159, "seed.onion");
        ONION_SEED.addComponents(new GTFOCropSeedBehaviour(GTFOCrops.CROP_ONION, ONION_SEED.getStackForm(), ONION.getStackForm()));
        SOYBEAN = addItem(160, "component.soybean");
        SOYBEAN_SEED = addItem(161, "seed.soy");
        SOYBEAN_SEED.addComponents(new GTFOCropSeedBehaviour(GTFOCrops.CROP_SOY, SOYBEAN_SEED.getStackForm(), SOYBEAN.getStackForm()));
        TOMATO_SEED = addItem(162, "seed.tomato");
        TOMATO_SEED.addComponents(new GTFOCropSeedBehaviour(GTFOCrops.CROP_TOMATO, TOMATO_SEED.getStackForm(), TOMATO.getStackForm()));
        GRAPE_SEED = addItem(163, "seed.grape");
        GRAPE_SEED.addComponents(new GTFOCropSeedBehaviour(GTFOCrops.CROP_GRAPE, GRAPE_SEED.getStackForm(), GRAPES.getStackForm()));
        CUCUMBER_SEED = addItem(164, "seed.cucumber");
        CUCUMBER_SEED.addComponents(new GTFOCropSeedBehaviour(GTFOCrops.CROP_CUCUMBER, CUCUMBER_SEED.getStackForm(), CUCUMBER.getStackForm()));
        COFFEE_SEED = addItem(202, "seed.coffee");
        COFFEE_SEED.addComponents(new GTFOCropSeedBehaviour(GTFOCrops.CROP_COFFEE, COFFEE_SEED.getStackForm(), COFFEE_CHERRY.getStackForm()));
        PEA_POD = addItem(206, "component.pea_pod");
        PEAS = addItem(207, "seed.pea");
        PEAS.addComponents(new GTFOCropSeedBehaviour(GTFOCrops.CROP_PEA, PEAS.getStackForm(), PEA_POD.getStackForm()));
        BEANS = addItem(208, "seed.bean");
        BEANS.addComponents(new GTFOCropSeedBehaviour(GTFOCrops.CROP_BEAN, BEANS.getStackForm(), BEANS.getStackForm()));


        // 175-189 left blank for organic circuits

        SPRINKLER_COVER = addItem(224, "cover.sprinkler");

        {
            int heal = 44;
            double saturation = 8.6;
            int potionDuration = 1200;

            MetaItem<?>.MetaValueItem[] smores = {EIGHT_SMORE, SIXTEEN_SMORE, THIRTY_TWO_SMORE, SIXTY_FOUR_SMORE, SMOGUS, MORE_SMOGUS, FOUR_SMOGUS, HEART_SMOGUS};
            String[] smoreStrings = {"eight", "sixteen", "thirtytwo", "sixtyfour", "gusone", "gustwo", "gusfour", "heartofthesmogus"};

            for (int i = 0; i < smores.length; i++) {
                heal = (heal * 2) + 4;
                saturation = (saturation * 2) + 1;
                potionDuration = (int) (((float) potionDuration) * 1.25);
                int potionStrength = (int) ((int) (Math.pow(2, (((double) i) + 8))) / Math.pow((((double) i) + 8), 2));

                smores[i] = addItem(100 + i, "food.smore." + smoreStrings[i]).addComponents(new GTFOFoodStats(heal, (float) saturation, false, true, ItemStack.EMPTY,
                        new RandomPotionEffect(getPotionById(1), potionDuration, potionStrength, 2 * i),
                        new RandomPotionEffect(getPotionById(22), potionDuration, potionStrength, 2 * i),
                        new RandomPotionEffect(getPotionById(3), potionDuration, potionStrength, 2 * i),
                        new RandomPotionEffect(getPotionById(23), potionDuration, potionStrength, 2 * i),
                        new RandomPotionEffect(getPotionById(21), potionDuration, potionStrength, 2 * i))
                        .setEatingDuration(32 + 10 * i));
            }

            EIGHT_SMORE = smores[0];
            SIXTEEN_SMORE = smores[1];
            THIRTY_TWO_SMORE = smores[2];
            SIXTY_FOUR_SMORE = smores[3];
            SMOGUS = smores[4];
            MORE_SMOGUS = smores[5];
            FOUR_SMOGUS = smores[6];
            HEART_SMOGUS = smores[7];
        }
    }

    @Override
    public ItemStack getContainerItem(ItemStack stack) {
        return stack.copy();
    }


    protected String formatModelPath(MetaItem<?>.MetaValueItem metaValueItem) {
        return "metaitems/" + metaValueItem.unlocalizedName.replace('.', '/');
    }

    @Override
    public boolean getHasSubtypes() {
        return true;
    }

    @Optional.Method(modid = GTFOValues.MODID_AP)
    public FoodValues getFoodValues(@Nonnull ItemStack itemStack) {
        MetaItem<?>.MetaValueItem item = this.getItem(itemStack);
        if (item != null && item.getUseManager() instanceof FoodUseManager) {
            IFoodBehavior stats = ((FoodUseManager) item.getUseManager()).getFoodStats();
            return new FoodValues(stats.getFoodLevel(itemStack, null), stats.getSaturation(itemStack, null));
        }
        return null;
    }

    @Nonnull
    public CreativeTabs[] getCreativeTabs() {
        return new CreativeTabs[]{GTFOValues.TAB_GTFO, GTFOValues.TAB_GTFO_FOOD};
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> subItems) {
        if (tab == GTFOValues.TAB_GTFO || tab == GTFOValues.TAB_GTFO_FOOD) {
            for (MetaItem.MetaValueItem item : this.getAllItems()) {
                if (item.isVisible() && ((!(item.getUseManager() instanceof FoodUseManager) && tab == GTFOValues.TAB_GTFO) || ((item.getUseManager() instanceof FoodUseManager) && tab == GTFOValues.TAB_GTFO_FOOD) || tab == CreativeTabs.SEARCH)) {
                    ItemStack itemStack = item.getStackForm();
                    item.getSubItemHandler().getSubItems(itemStack, tab, subItems);
                }
            }
        }
    }
}
