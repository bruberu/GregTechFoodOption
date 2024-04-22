package gregtechfoodoption.item;

import gregtech.api.items.metaitem.FoodUseManager;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IFoodBehavior;
import gregtech.api.items.metaitem.stats.IItemComponent;
import gregtech.api.items.metaitem.stats.IItemContainerItemProvider;
import gregtech.api.items.toolitem.IGTTool;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.RandomPotionEffect;
import gregtech.common.items.MetaItems;
import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.block.GTFOCrops;
import gregtechfoodoption.item.food.GTFOFoodUseManager;
import gregtechfoodoption.potion.*;
import gregtechfoodoption.utils.GTFOLog;
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
import java.lang.reflect.Field;

import static gregtech.api.unification.material.Materials.*;
import static gregtechfoodoption.GTFOMaterialHandler.BareCornKernel;
import static net.minecraft.potion.Potion.getPotionById;


@Optional.Interface(modid = GTFOValues.MODID_AP, iface = "squeek.applecore.api.food.IEdible")
public class GTFOMetaItem extends MetaItem<GTFOMetaItem.GTFOMetaValueItem> implements IEdible {
    //foods
    public static MetaItem<?>.MetaValueItem POPCORN_BAG;
    public static MetaItem<?>.MetaValueItem PAPER_BAG;
    public static MetaItem<?>.MetaValueItem FLAVORED_POPCORN_FLAKE;
    public static MetaItem<?>.MetaValueItem CORN_COB;
    public static MetaItem<?>.MetaValueItem DRIED_CORN_EAR;
    public static MetaItem<?>.MetaValueItem CORN_EAR;
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
    public static MetaItem<?>.MetaValueItem PARACETAMOL_CAPLET;

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
    public static MetaItem<?>.MetaValueItem CAPLET_CAP;
    public static MetaItem<?>.MetaValueItem CAPLET_BODY;
    public static MetaItem<?>.MetaValueItem GEL_CAPLET;

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
    public static MetaItem<?>.MetaValueItem PLUTONIUM_241_CAPLET;
    public static MetaItem<?>.MetaValueItem IV_BAG;

    public static MetaItem<?>.MetaValueItem GARLIC_BULB;
    public static MetaItem<?>.MetaValueItem GARLIC_CLOVE;
    public static MetaItem<?>.MetaValueItem OREGANO;
    public static MetaItem<?>.MetaValueItem OREGANO_SEED;
    public static MetaItem<?>.MetaValueItem BASIL;
    public static MetaItem<?>.MetaValueItem BASIL_SEED;
    public static MetaItem<?>.MetaValueItem HORSERADISH;
    public static MetaItem<?>.MetaValueItem HORSERADISH_SEED;
    public static MetaItem<?>.MetaValueItem AUBERGINE;
    public static MetaItem<?>.MetaValueItem AUBERGINE_SEED;

    public static MetaItem<?>.MetaValueItem PASTA_DOUGH;
    public static MetaItem<?>.MetaValueItem EGG_PASTA_DOUGH;
    public static MetaItem<?>.MetaValueItem PREMIXED_PASTA_DOUGH;

    public static MetaItem<?>.MetaValueItem BLANK_PASTA_DIE;
    public static MetaItem<?>.MetaValueItem TAGLIATELLE_PASTA_DIE;
    public static MetaItem<?>.MetaValueItem SPAGHETTI_PASTA_DIE;
    public static MetaItem<?>.MetaValueItem LASAGNA_PASTA_DIE;
    public static MetaItem<?>.MetaValueItem DITALINI_PASTA_DIE;
    public static MetaItem<?>.MetaValueItem RIGATONI_PASTA_DIE;

    public static MetaItem<?>.MetaValueItem RAW_TAGLIATELLE;
    public static MetaItem<?>.MetaValueItem RAW_SPAGHETTI;
    public static MetaItem<?>.MetaValueItem RAW_LASAGNA;
    public static MetaItem<?>.MetaValueItem RAW_DITALINI;
    public static MetaItem<?>.MetaValueItem RAW_RIGATONI;
    public static MetaItem<?>.MetaValueItem DRIED_TAGLIATELLE;
    public static MetaItem<?>.MetaValueItem DRIED_SPAGHETTI;
    public static MetaItem<?>.MetaValueItem DRIED_LASAGNA;
    public static MetaItem<?>.MetaValueItem DRIED_DITALINI;
    public static MetaItem<?>.MetaValueItem DRIED_RIGATONI;
    public static MetaItem<?>.MetaValueItem TORTELLINI;
    public static MetaItem<?>.MetaValueItem TAGLIATELLE;
    public static MetaItem<?>.MetaValueItem SPAGHETTI;
    public static MetaItem<?>.MetaValueItem DITALINI;
    public static MetaItem<?>.MetaValueItem RIGATONI;
    public static MetaItem<?>.MetaValueItem ARTICHOKE_HEART;
    public static MetaItem<?>.MetaValueItem ARTICHOKE_SEED;
    public static MetaItem<?>.MetaValueItem BLACK_PEPPERCORN;
    public static MetaItem<?>.MetaValueItem RICE;
    public static MetaItem<?>.MetaValueItem NUTMEG_SEED;

    public static MetaItem<?>.MetaValueItem BRUSCHETTA;
    public static MetaItem<?>.MetaValueItem CAPONATA;
    public static MetaItem<?>.MetaValueItem CARBONARA;
    public static MetaItem<?>.MetaValueItem CARCIOFI_ALLA_ROMANA;
    public static MetaItem<?>.MetaValueItem FETTUCCINE_ALFREDO;
    public static MetaItem<?>.MetaValueItem PARMIGIANA;
    public static MetaItem<?>.MetaValueItem PASTA_ALLA_NORMA;
    public static MetaItem<?>.MetaValueItem PASTA_AL_POMODORO;
    public static MetaItem<?>.MetaValueItem PASTA_E_FAGIOLI;
    public static MetaItem<?>.MetaValueItem POLENTA;
    public static MetaItem<?>.MetaValueItem RAFANATA;
    public static MetaItem<?>.MetaValueItem RISOTTO;
    public static MetaItem<?>.MetaValueItem SPAGHETTI_ALLASSASSINA;
    public static MetaItem<?>.MetaValueItem TAGLIATELLE_AL_RAGU;
    public static MetaItem<?>.MetaValueItem TORTELLINI_IN_BRODO;
    public static MetaItem<?>.MetaValueItem VITELLO_TONNATO;
    public static MetaItem<?>.MetaValueItem LASAGNA_CHUM;
    public static MetaItem<?>.MetaValueItem LASAGNA_NAPOLETANA;
    public static MetaItem<?>.MetaValueItem LASAGNA_PESTO;
    public static MetaItem<?>.MetaValueItem PASTA_ALLAMOGUS;

    public static MetaItem<?>.MetaValueItem CERAMIC_BOWL;
    public static MetaItem<?>.MetaValueItem DIRTY_CERAMIC_BOWL;
    public static MetaItem<?>.MetaValueItem PLATE;
    public static MetaItem<?>.MetaValueItem DIRTY_PLATE;
    public static MetaItem<?>.MetaValueItem BAKING_TRAY;

    public static MetaItem<?>.MetaValueItem LASAGNA_CHUM_RAW;
    public static MetaItem<?>.MetaValueItem LASAGNA_NAPOLETANA_RAW;
    public static MetaItem<?>.MetaValueItem LASAGNA_PESTO_RAW;
    public static MetaItem<?>.MetaValueItem LASAGNA_CHUM_COOKED;
    public static MetaItem<?>.MetaValueItem LASAGNA_NAPOLETANA_COOKED;
    public static MetaItem<?>.MetaValueItem LASAGNA_PESTO_COOKED;

    public static MetaItem<?>.MetaValueItem PORCHETTA;
    public static MetaItem<?>.MetaValueItem PORCHETTA_SLICE;

    public static MetaItem<?>.MetaValueItem AGED_PARMIGIANO_ROLL;
    public static MetaItem<?>.MetaValueItem BRINED_PARMIGIANO_ROLL;
    public static MetaItem<?>.MetaValueItem BRINED_PARMIGIANO;
    public static MetaItem<?>.MetaValueItem CURDLING_PARMIGIANO;
    public static MetaItem<?>.MetaValueItem CHEESE_ROLL_FORM;
    public static MetaItem<?>.MetaValueItem EGGPLANT_SLICE;
    public static MetaItem<?>.MetaValueItem SEASONED_PORK;

    public static MetaItem<?>.MetaValueItem RED_WINE;

    public static MetaItem<?>.MetaValueItem WHITE_WINE;
    public static MetaItem<?>.MetaValueItem WHITE_GRAPES;
    public static MetaItem<?>.MetaValueItem WHITE_GRAPE_SEED;

    public static MetaItem<?>.MetaValueItem UNFIRED_PLATE;
    public static MetaItem<?>.MetaValueItem UNFIRED_BOWL;

    public static MetaItem<?>.MetaValueItem EMERGENCY_RATIONS;

    public static MetaItem<?>.MetaValueItem BLUEBERRY;
    public static MetaItem<?>.MetaValueItem BLACKBERRY;
    public static MetaItem<?>.MetaValueItem RASPBERRY;
    public static MetaItem<?>.MetaValueItem STRAWBERRY;
    public static MetaItem<?>.MetaValueItem RED_CURRANT;
    public static MetaItem<?>.MetaValueItem BLACK_CURRANT;
    public static MetaItem<?>.MetaValueItem WHITE_CURRANT;
    public static MetaItem<?>.MetaValueItem LINGONBERRY;
    public static MetaItem<?>.MetaValueItem ELDERBERRY;
    public static MetaItem<?>.MetaValueItem CRANBERRY;

    public static MetaItem<?>.MetaValueItem BERRY_MEDLEY;
    public static MetaItem<?>.MetaValueItem ETIRPS_CRANBERRY;


    public static MetaItem<?>.MetaValueItem UNCOOKED_PELMENI;
    public static MetaItem<?>.MetaValueItem UNCOOKED_SEASONED_PELMENI;
    public static MetaItem<?>.MetaValueItem PELMENI;
    public static MetaItem<?>.MetaValueItem SEASONED_PELMENI;

    public static MetaItem<?>.MetaValueItem COCONUT;

    public static MetaItem<?>.MetaValueItem KITCHEN_RECIPE;

    public GTFOMetaItem() {
        super((short) 0);
    }

    @Override
    protected GTFOMetaValueItem constructMetaValueItem(short metaValue, String unlocalizedName) {
        return new GTFOMetaValueItem(metaValue, unlocalizedName);
    }

    @Override
    public void registerSubItems() {
        IItemContainerItemProvider selfContainerItemProvider = itemStack -> itemStack;

        PAPER_BAG = addItem(1, "component.bag").blacklistKitchen();
        FLAVORED_POPCORN_FLAKE = addItem(2, "component.corn.flavored_flake");
        CORN_COB = addItem(3, "component.corn.cob");
        DRIED_CORN_EAR = addItem(4, "component.corn.dried_ear");
        CORN_EAR = addItem(5, "component.corn.ear");
        CORN_EAR.addComponents(new GTFOCropSeedBehaviour(GTFOCrops.CROP_CORN, BareCornKernel.getItemStack(), CORN_EAR.getStackForm()));
        //PHYCOMYCES_BLAKESLEEANUS_CULTURE = addItem(6, "culture.phycomyces");

        THERMOS = addItem(7, "component.thermos.new").blacklistKitchen();
        USED_THERMOS = addItem(8, "component.thermos.used").blacklistKitchen();
        LEACHED_THERMOS_CASING = addItem(9, "component.thermos.casing_leached").blacklistKitchen();
        THERMOS_CAP = addItem(10, "component.thermos.cap").blacklistKitchen();
        THERMOS_CASING = addItem(11, "component.thermos.casing").blacklistKitchen();

        CORED_APPLE = addItem(13, "component.cored_apple");
        PLASTIC_BOTTLE = addItem(15, "component.plastic_bottle").blacklistKitchen();
        //LACTOBACILLUS_PENTOSIS_CULTURE = addItem(20, "culture.lactobacillus");
        //BACILLUS_SUBTILIS_CULTURE = addItem(21, "culture.bacillus");

        SLICER_BLADE_FLAT = addItem(23, "config.slicer_blade.flat").blacklistKitchen();
        SLICER_BLADE_STRIPES = addItem(24, "config.slicer_blade.stripes").blacklistKitchen();

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

        MUD_BRICK = addItem(43, "brick.adobe").blacklistKitchen().addComponents(new GTFOFoodStats(0, 0, false, true).setEatingDuration(100)
                .setPotionEffects(new RandomPotionEffect(MobEffects.POISON, 400, 0, 100 - 50)));
        ADOBE_BRICK = addItem(44, "brick.adobe_fired").blacklistKitchen()
                .addComponents(new GTFOFoodStats(0, 0, false, true).setEatingDuration(400));

        WOODEN_FORM_BREAD = addItem(45, "wooden_form.bread").blacklistKitchen().addComponents(selfContainerItemProvider).setMaxStackSize(1);
        WOODEN_FORM_BAGUETTE = addItem(46, "wooden_form.baguette").blacklistKitchen().addComponents(selfContainerItemProvider).setMaxStackSize(1);
        UNCOOKED_BREAD = addItem(47, "component.bread");
        UNCOOKED_BAGUETTE = addItem(48, "component.baguette");
        DOUGH = addItem(50, "component.dough")
                .addOreDict("foodDough");

        CARROT_STRUCTURAL_MESH = addItem(52, "component.structural_mesh.carrot").blacklistKitchen();
        APPLE_STRUCTURAL_MESH = addItem(53, "component.structural_mesh.apple").blacklistKitchen();
        SUGARY_DOUGH = addItem(55, "component.sugary_dough");
        PIE_CRUST = addItem(58, "component.pie_crust");

        PIZZA_CHEESE_RAW = addItem(59, "component.pizza.cheese");
        PIZZA_VEGGIE_RAW = addItem(60, "component.pizza.veggie");
        PIZZA_MINCE_MEAT_RAW = addItem(61, "component.pizza.mince_meat");

        OLIVE = addItem(73, "crop.olive")
                .addOreDict("cropOlive")
                .addComponents(new GTFOFoodStats(2, 0.5f).setEatingDuration(64)
                        .nutrients(0, 0, 0, 0, 1f));
        OLIVE_SLICE = addItem(74, "component.olive_slice")
                .addComponents(new GTFOFoodStats(1, 1f)
                        .nutrients(0, 0, 0, 0, 1f));
        MUSHROOM_SLICE = addItem(75, "component.mushroom_slice")
                .addComponents(new GTFOFoodStats(1, 1f)
                        .nutrients(0, 0, 0, 0.5f, 1f));

        TOMATO = addItem(76, "crop.tomato")
                .addOreDict("cropTomato")
                .addComponents(new GTFOFoodStats(3, 0.5f)
                        .setEatingDuration(72)
                        .nutrients(0, 1f, 0, 0, 0));
        ONION = addItem(77, "crop.onion")
                .addOreDict("cropOnion")
                .addComponents(new GTFOFoodStats(3, 0.33f)
                        .setEatingDuration(128)
                        .nutrients(0, 0, 0, 0, 1f));
        CUCUMBER = addItem(78, "crop.cucumber")
                .addOreDict("cropCucumber")
                .addComponents(new GTFOFoodStats(2, 0.5f)
                        .setEatingDuration(64)
                        .nutrients(0, 0, 0, 0, 1f));
        CUCUMBER.addComponents(new GTFOCropSeedBehaviour(GTFOCrops.CROP_CUCUMBER, CUCUMBER.getStackForm(), CUCUMBER.getStackForm()));

        WOODEN_FORM_BUN = addItem(82, "wooden_form.bun").blacklistKitchen().addComponents(selfContainerItemProvider).setMaxStackSize(1);
        UNCOOKED_BUN = addItem(83, "component.bun");

        PRESLICED_BUN = addItem(84, "component.buns")
                .addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.baguetteHunger / 3, GTFOConfig.gtfoFoodConfig.baguetteSaturation)
                        .setEatingDuration(20)
                        .nutrients(0, 0, 0.5f, 0, 0));
        PRESLICED_BREAD = addItem(85, "component.breads")
                .addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.baguetteHunger * 2 / 3, GTFOConfig.gtfoFoodConfig.baguetteSaturation)
                        .setEatingDuration(20)
                        .nutrients(0, 0, 0.5f, 0, 0));
        PRESLICED_BAGUETTE = addItem(86, "component.baguettes")
                .addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.baguetteHunger * 2 / 3, GTFOConfig.gtfoFoodConfig.baguetteSaturation)
                        .setEatingDuration(20)
                        .nutrients(0, 0, 0.5f, 0, 0));

        BEEF_SLICE = addItem(91, "component.beef_slice");
        CHEDDAR_CURD_MOLD = addItem(92, "component.cheddar_curd_mold").blacklistKitchen();
        AGED_CHEDDAR_MOLD = addItem(93, "component.aged_cheddar_mold").blacklistKitchen();
        MOZZARELLA_SLICE = addItem(94, "component.mozzarella_slice")
                .addOreDict("foodCheese");
        RICOTTA = addItem(95, "component.ricotta_piece")
                .addOreDict("foodCheese");
        CHEDDAR_BLOCK = addItem(96, "component.cheddar_block");

        FLAT_DOUGH = addItem(99, "component.flat_dough");

        UNCOOKED_BACON = addItem(108, "component.bacon");

        SKEWER = addItem(135, "component.skewer").blacklistKitchen();

        GORGONZOLA_WHEEL = addItem(109, "component.gorgonzola_wheel").blacklistKitchen();
        SALTED_GORGONZOLA_WHEEL = addItem(110, "component.salted_gorgonzola_wheel").blacklistKitchen();
        SLIGHTLY_AGED_GORGONZOLA_WHEEL = addItem(111, "component.slightly_aged_gorgonzola_wheel").blacklistKitchen();
        PUNCTURED_GORGONZOLA_WHEEL = addItem(112, "component.punctured_gorgonzola_wheel").blacklistKitchen();
        FULLY_CURED_GORGONZOLA_WHEEL = addItem(113, "component.fully_cured_gorgonzola_wheel").blacklistKitchen();
        //PENICILLIUM_ROQUEFORTI_CULTURE = addItem(115, "culture.penicillium");
        SLICER_BLADE_OCTAGONAL = addItem(116, "config.slicer_blade.octagonal").blacklistKitchen();

        BANANA_PEEL = addItem(127, "component.banana_peel");

        SCRAP_MEAT = addItem(134, "component.scrap_meat");

        GELATIN = addItem(196, "component.gelatin").blacklistKitchen();
        ROASTED_COCOA_BEANS = addItem(197, "component.roasted_beans");
        COCOA_NIBS = addItem(198, "component.cocoa_nibs");
        EMPTY_CUP = addItem(199, "component.empty_cup").blacklistKitchen();
        PAPER_CONE = addItem(200, "component.paper_cone").blacklistKitchen();
        COFFEE_CHERRY = addItem(201, "crop.coffee");
        UNFIRED_CUP = addItem(205, "component.unfired_cup").blacklistKitchen();

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

        GARLIC_BULB = addItem(308, "component.garlic_bulb");
        AUBERGINE = addItem(309, "crop.aubergine");
        HORSERADISH = addItem(233, "component.horseradish");
        BASIL = addItem(234, "component.basil");
        OREGANO = addItem(235, "component.oregano");

        CAPLET_CAP = addItem(227, "component.caplet_cap").blacklistKitchen();
        CAPLET_BODY = addItem(228, "component.caplet_body").blacklistKitchen();
        IV_BAG = addItem(232, "component.iv_bag").blacklistKitchen();

        PASTA_DOUGH = addItem(243, "component.dough.pasta");
        EGG_PASTA_DOUGH = addItem(244, "component.dough.egg_pasta");
        PREMIXED_PASTA_DOUGH = addItem(245, "component.dough.premixed_pasta");

        RAW_TAGLIATELLE = addItem(252, "component.pasta.raw.tagliatelle");
        RAW_SPAGHETTI = addItem(253, "component.pasta.raw.spaghetti");
        RAW_LASAGNA = addItem(254, "component.pasta.raw.lasagna");
        RAW_DITALINI = addItem(255, "component.pasta.raw.ditalini");
        RAW_RIGATONI = addItem(256, "component.pasta.raw.rigatoni");
        DRIED_TAGLIATELLE = addItem(257, "component.pasta.dried.tagliatelle");
        DRIED_SPAGHETTI = addItem(258, "component.pasta.dried.spaghetti");
        DRIED_LASAGNA = addItem(259, "component.pasta.dried.lasagna");
        DRIED_DITALINI = addItem(260, "component.pasta.dried.ditalini");
        DRIED_RIGATONI = addItem(261, "component.pasta.dried.rigatoni");
        TAGLIATELLE = addItem(262, "component.pasta.tagliatelle");
        SPAGHETTI = addItem(263, "component.pasta.spaghetti");
        DITALINI = addItem(264, "component.pasta.ditalini");
        RIGATONI = addItem(265, "component.pasta.rigatoni");

        TORTELLINI = addItem(266, "component.pasta.tortellini");

        PLATE = addItem(291, "component.plate").blacklistKitchen();
        DIRTY_PLATE = addItem(292, "component.dirty_plate").blacklistKitchen();
        CERAMIC_BOWL = addItem(293, "component.ceramic_bowl").blacklistKitchen();
        DIRTY_CERAMIC_BOWL = addItem(294, "component.dirty_bowl").blacklistKitchen();
        BAKING_TRAY = addItem(302, "component.baking_tray").blacklistKitchen();

        LASAGNA_CHUM_RAW = addItem(296, "component.pasta.lasagna.raw.chum");
        LASAGNA_NAPOLETANA_RAW = addItem(297, "component.pasta.lasagna.raw.napoletana");
        LASAGNA_PESTO_RAW = addItem(298, "component.pasta.lasagna.raw.pesto");
        LASAGNA_CHUM_COOKED = addItem(299, "component.pasta.lasagna.cooked.chum");
        LASAGNA_NAPOLETANA_COOKED = addItem(300, "component.pasta.lasagna.cooked.napoletana");
        LASAGNA_PESTO_COOKED = addItem(301, "component.pasta.lasagna.cooked.pesto");

        PORCHETTA = addItem(310, "component.porchetta")
                .addComponents(new GTFOFoodStats(7, 0.7f).setEatingDuration(50)
                        .nutrients(0, 0,  0, 0.5f, 0.1f));

        AGED_PARMIGIANO_ROLL = addItem(312, "component.aged_parmigiano_roll").blacklistKitchen();
        BRINED_PARMIGIANO = addItem(313, "component.brined_parmigiano").blacklistKitchen();
        BRINED_PARMIGIANO_ROLL = addItem(314, "component.brined_parmigiano_roll").blacklistKitchen();
        CURDLING_PARMIGIANO = addItem(315, "component.curdling_parmigiano").blacklistKitchen();
        CHEESE_ROLL_FORM = addItem(316, "component.cheese_form").blacklistKitchen();

        SEASONED_PORK = addItem(318, "component.seasoned_pork");

        UNFIRED_PLATE = addItem(322, "component.unfired_plate").blacklistKitchen();
        UNFIRED_BOWL = addItem(323, "component.unfired_bowl").blacklistKitchen();

        UNCOOKED_PELMENI = addItem(338, "component.pelmeni_uncooked");
        UNCOOKED_SEASONED_PELMENI = addItem(339, "component.pelmeni_uncooked_seasoned");

        COCONUT = addItem(342, "component.coconut");

        POPCORN_BAG = addItem(0, "food.popcorn_bag").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.popcornHunger, GTFOConfig.gtfoFoodConfig.popcornSaturation, false, true, PAPER_BAG.getStackForm(1),
                new RandomPotionEffect(getPotionById(10), 300, 1, 0))
                .nutrients(0, 0, 0.5f, 0, 0));
        MINERAL_WATER = addItem(12, "food.mineral_water").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.mineralWaterHunger, GTFOConfig.gtfoFoodConfig.mineralWaterSaturation, true, true, USED_THERMOS.getStackForm(1),
                new RandomPotionEffect(CreativityPotion.INSTANCE, 5000, 0, 0)));
        APPLE_HARD_CANDY = addItem(14, "food.apple_hard_candy").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.hardCandyHunger, GTFOConfig.gtfoFoodConfig.hardCandySaturation, true, false, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.REGENERATION, 1200, 1, 50))
                .setEatingDuration(24)
                .nutrients(0, 0.5f, 0, 0, 0));
        SPARKLING_WATER = addItem(16, "food.sparkling_water").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.sparklingWaterHunger, GTFOConfig.gtfoFoodConfig.sparklingWaterSaturation, true, false, PLASTIC_BOTTLE.getStackForm(),
                new RandomPotionEffect(MobEffects.SPEED, 600, 1, 0)));
        LEMON = addItem(17, "food.lemon").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.lemonHunger, GTFOConfig.gtfoFoodConfig.lemonSaturation)
                        .nutrients(0, 1f, 0, 0, 0))
                .addOreDict("cropLemon").addOreDict("listAllfruit");
        LIME = addItem(18, "food.lime").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.limeHunger, GTFOConfig.gtfoFoodConfig.limeSaturation)
                        .nutrients(0, 1f, 0, 0, 0))
                .addOreDict("cropLime").addOreDict("listAllfruit");
        ETIRPS = addItem(19, "food.etirps").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.etirpsHunger, GTFOConfig.gtfoFoodConfig.etirpsSaturation, true, true, PLASTIC_BOTTLE.getStackForm(),
                new RandomPotionEffect(MobEffects.SPEED, 1200, 2, 0))
                .nutrients(0, 0.5f, 0, 0, 0));

        MetaItems.BOTTLE_PURPLE_DRINK.addComponents(new GTFOFoodStats(3, 0.2F, true, true, new ItemStack(Items.GLASS_BOTTLE),
                new RandomPotionEffect(MobEffects.HASTE, 800, 1, 10),
                new RandomPotionEffect(MobEffects.WITHER, 800, 5, 10))
                .nutrients(0, 0.5f, 0, 0, 0));

        BACON = addItem(22, "food.bacon").addComponents(new GTFOFoodStats(2, 0.8f, false, true)
                .setEatingDuration(24)
                .nutrients(0, 0, 0, 1f, 0));

        FRENCH_FRIES = addItem(37, "food.french_fries").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.friesHunger, GTFOConfig.gtfoFoodConfig.friesSaturation, false, false, USED_PAPER_BAG.getStackForm(),
                        new RandomPotionEffect(MobEffects.STRENGTH, 1200, 1, 0))
                        .setEatingDuration(20)
                        .nutrients(0, 0, 1f, 0, 0))
                .addOreDict("foodFries");
        SYALS = addItem(38, "food.syals").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.chipHunger / 2, GTFOConfig.gtfoFoodConfig.chipSaturation / 2, false, false, () -> OreDictUnifier.get(OrePrefix.foil, Tin),
                new RandomPotionEffect(MobEffects.LEVITATION, 300, 1, 0))
                .nutrients(0, 0, 0.5f, 0, 0));
        BAG_OF_CHIPS = addItem(39, "food.bag_of_chips").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.chipHunger, GTFOConfig.gtfoFoodConfig.chipSaturation, false, false, () -> OreDictUnifier.get(OrePrefix.foil, Steel),
                new RandomPotionEffect(MobEffects.HASTE, 600, 1, 0))
                .nutrients(0, 0, 0.5f, 0, 0));
        KETTLE_FRIED_CHIPS = addItem(40, "food.kettle_chips").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.chipHunger + 1, GTFOConfig.gtfoFoodConfig.chipSaturation, false, false, () -> OreDictUnifier.get(OrePrefix.foil, Aluminium),
                new RandomPotionEffect(MobEffects.HASTE, 900, 1, 0))
                .nutrients(0, 0, 1f, 0, 0));
        REDUCED_FAT_CHIPS = addItem(41, "food.reduced_fat_chips").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.chipHunger, GTFOConfig.gtfoFoodConfig.chipSaturation + 1, false, false, () -> OreDictUnifier.get(OrePrefix.foil, StainlessSteel),
                new RandomPotionEffect(MobEffects.HASTE, 1200, 1, 0),
                new RandomPotionEffect(MobEffects.HASTE, 1200, 2, 50))
                .setEatingDuration(20)
                .nutrients(0, 0, 1.5f, 0, 0));
        POTATO_ON_A_STICK = addItem(42, "food.potato_on_a_stick").addComponents(new GTFOFoodStats(3, 0.8f, false, false, new ItemStack(Items.STICK))
                        .setEatingDuration(12)
                        .nutrients(0, 0, 1f, 0, 0))
                .setMaxStackSize(16);

        BAGUETTE = addItem(51, "food.baguette").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.baguetteHunger, GTFOConfig.gtfoFoodConfig.baguetteSaturation, false, false, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.HASTE, 1200, 0, 50))
                .setEatingDuration(40)
                .nutrients(0, 0, 1f, 0, 0));
        TUNGSTENSTEEL_APPLE = addItem(54, "food.tungstensteel_apple").addComponents(new GTFOFoodStats(3, 1f, false, false, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.SPEED, 1200, 2, 0),
                new RandomPotionEffect(MobEffects.RESISTANCE, 1200, 3, 0),
                new RandomPotionEffect(MobEffects.NIGHT_VISION, 3600, 2, 40),
                new RandomPotionEffect(MobEffects.INSTANT_DAMAGE, 1, 1, 0))
                .setEatingDuration(80)
                .nutrients(0, 1f, 0, 0, 0));
        CAKE_BOTTOM = addItem(56, "food.cake_bottom").addComponents(new GTFOFoodStats(2, 0.5f, false, false, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.POISON, 200, 1, 80))
                .setEatingDuration(60)
                .nutrients(0, 0, 0.5f, 0, 0));
        BAKED_CAKE_BOTTOM = addItem(57, "food.cake_bottom_baked").addComponents(new GTFOFoodStats(3, 0.5f)
                .nutrients(0, 0, 1f, 0, 0));

        PIZZA_CHEESE = addItem(62, "food.pizza.cheese").addComponents(new GTFOFoodStats(10, 0.8f, false, false, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.HASTE, 2000, 2, 0))
                .setEatingDuration(50)
                .nutrients(1f, 0, 1f, 0, 1f));
        PIZZA_VEGGIE = addItem(63, "food.pizza.veggie").addComponents(new GTFOFoodStats(10, 0.7f, false, false, ItemStack.EMPTY,
                new RandomPotionEffect(StepAssistPotion.INSTANCE, 2000, 1, 0))
                .setEatingDuration(50)
                .nutrients(0, 0, 1f, 0, 2f));
        PIZZA_MINCE_MEAT = addItem(64, "food.pizza.mince_meat").addComponents(new GTFOFoodStats(11, 0.8f, false, false, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.STRENGTH, 2000, 2, 0))
                .setEatingDuration(50)
                .nutrients(0, 0, 1f, 1f, 1f));

        SANDWICH_VEGGIE = addItem(65, "food.sandwich.veggie").addComponents(new GTFOFoodStats(6, 0.6f)
                .setEatingDuration(40)
                .nutrients(0, 0, 1f, 0f, 1f));
        SANDWICH_CHEESE = addItem(66, "food.sandwich.cheese").addComponents(new GTFOFoodStats(6, 0.6f)
                .setEatingDuration(40)
                .nutrients(1f, 0, 1f, 0f, 0f));
        SANDWICH_BACON = addItem(67, "food.sandwich.bacon").addComponents(new GTFOFoodStats(6, 0.7f)
                .setEatingDuration(40)
                .nutrients(0f, 0, 1f, 1f, 0f));
        SANDWICH_STEAK = addItem(68, "food.sandwich.steak").addComponents(new GTFOFoodStats(7, 0.7f)
                .setEatingDuration(40)
                .nutrients(0f, 0, 1f, 1f, 0f));

        SANDWICH_LARGE_VEGGIE = addItem(69, "food.sandwich.veggie.large").addComponents(new GTFOFoodStats(9, 0.6f)
                .setEatingDuration(60)
                .nutrients(0, 0, 1f, 0f, 2f));
        SANDWICH_LARGE_CHEESE = addItem(70, "food.sandwich.cheese.large").addComponents(new GTFOFoodStats(11, 0.6f)
                .setEatingDuration(60)
                .nutrients(2f, 0, 1f, 0f, 0f));
        SANDWICH_LARGE_BACON = addItem(71, "food.sandwich.bacon.large").addComponents(new GTFOFoodStats(10, 0.7f, false, false, ItemStack.EMPTY,
                new RandomPotionEffect(StepAssistPotion.INSTANCE, 600, 0, 0))
                .setEatingDuration(60)
                .nutrients(0f, 0, 1f, 2f, 0f));
        SANDWICH_LARGE_STEAK = addItem(72, "food.sandwich.steak.large").addComponents(new GTFOFoodStats(13, 0.7f)
                .setEatingDuration(60)
                .nutrients(0f, 0, 1f, 2f, 0f));

        BUN = addItem(87, "food.bun").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.baguetteHunger / 3, GTFOConfig.gtfoFoodConfig.baguetteSaturation)
                .setEatingDuration(25)
                .nutrients(0, 0, 1f, 0, 0));

        BURGER_VEGGIE = addItem(88, "food.burger.veggie").addComponents(new GTFOFoodStats(4, 0.6f)
                .nutrients(0, 0, 1f, 0f, 1f));
        BURGER_CHEESE = addItem(89, "food.burger.cheese").addComponents(new GTFOFoodStats(4, 0.6f)
                .nutrients(1f, 0, 1f, 0f, 0f));
        BURGER_MEAT = addItem(90, "food.burger.meat").addComponents(new GTFOFoodStats(4, 0.7f)
                .nutrients(0f, 0, 1f, 1f, 0f));

        CHEDDAR_SLICE = addItem(97, "food.cheddar_slice").addComponents(new GTFOFoodStats(2, 0.2f)
                        .setEatingDuration(20)
                        .nutrients(2f, 0, 0f, 0f, 0f))
                .addOreDict("foodCheese");
        MOZZARELLA_BALL = addItem(98, "food.mozzarella_ball").addComponents(new GTFOFoodStats(3, 0.6f)
                        .nutrients(2f, 0, 0f, 0f, 0f))
                .addOreDict("foodCheese");
        GORGONZOLA_TRIANGULAR_SLICE = addItem(114, "food.gorgonzola_slice").addComponents(new GTFOFoodStats(3, 0.5f)
                        .nutrients(2f, 0, 0f, 0f, 0f))
                .addOreDict("foodCheese");

        ROTTEN_FISH = addItem(117, "food.fish_rotten").addComponents(new GTFOFoodStats(1, 0f, false, true, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.POISON, 500, 1, 0))
                .setEatingDuration(100)
                .nutrients(0f, 0, 0f, 0.5f, 0f));
        ROTTEN_MEAT = addItem(118, "food.meat_rotten").addComponents(new GTFOFoodStats(1, 0f, false, true, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.POISON, 500, 1, 0))
                .setEatingDuration(100)
                .nutrients(0f, 0, 0f, 0.5f, 0f));
        CHUM = addItem(119, "food.chum").addComponents(new GTFOFoodStats(3, 0f, false, true, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.NAUSEA, 500, 10, 99))
                .nutrients(0f, 0, 0f, 0.5f, 0f));
        CHUM_ON_A_STICK = addItem(120, "food.chum_on_a_stick").addComponents(new GTFOFoodStats(3, 0f, false, true, new ItemStack(Items.STICK),
                        new RandomPotionEffect(MobEffects.NAUSEA, 500, 10, 99))
                        .setEatingDuration(16)
                        .nutrients(0f, 0, 0f, 0.75f, 0f))
                .setMaxStackSize(16);
        BURGER_CHUM = addItem(121, "food.burger.chum").addComponents(new GTFOFoodStats(4, 1f, false, false, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.NAUSEA, 500, 10, 99))
                .nutrients(0f, 0, 1f, 0.5f, 0f));

        BANANA = addItem(122, "food.banana").addComponents(new GTFOFoodStats(2, 1f)
                        .setEatingDuration(60)
                        .nutrients(0f, 1f, 0f, 0f, 0f))
                .addOreDict("cropBanana").addOreDict("listAllfruit");
        ORANGE = addItem(123, "food.orange").addComponents(new GTFOFoodStats(2, 1f)
                        .setEatingDuration(50)
                        .nutrients(0f, 1f, 0f, 0f, 0f))
                .addOreDict("cropOrange").addOreDict("listAllfruit");
        GRAPES = addItem(124, "food.grapes").addComponents(new GTFOFoodStats(1, 1f)
                        .nutrients(0f, 1f, 0f, 0f, 0f))
                .addOreDict("cropGrapes").addOreDict("listAllfruit");
        MANGO = addItem(125, "food.mango").addComponents(new GTFOFoodStats(2, 1f)
                        .nutrients(0f, 1f, 0f, 0f, 0f))
                .addOreDict("cropMango").addOreDict("listAllfruit");
        APRICOT = addItem(126, "food.apricot").addComponents(new GTFOFoodStats(2, 1f)
                        .nutrients(0f, 1f, 0f, 0f, 0f))
                .addOreDict("cropApricot").addOreDict("listAllfruit");

        PEELED_BANANA = addItem(128, "food.peeled_banana").addComponents(new GTFOFoodStats(2, 1f)
                .setEatingDuration(12).nutrients(0f, 1f, 0f, 0f, 0f));

        VODKA = addItem(129, "food.vodka").addComponents(new GTFOFoodStats(2, 0f, true, false, new ItemStack(Items.GLASS_BOTTLE),
                new RandomPotionEffect(MobEffects.NAUSEA, 500, 1, 60))
                .nutrients(0f, 0f, 0.5f, 0f, 0f));
        LENINADE = addItem(130, "food.leninade").addComponents(new GTFOFoodStats(3, 1f, true, false, new ItemStack(Items.GLASS_BOTTLE),
                new RandomPotionEffect(MobEffects.NAUSEA, 500, 2, 70),
                new RandomPotionEffect(MobEffects.SPEED, 500, 2, 0))
                .nutrients(0f, 1f, 0.5f, 0f, 0f));

        HOT_MUSHROOM_STEW = addItem(131, "food.mushroom_stew.hot").setMaxStackSize(1).addComponents(new GTFOFoodStats(8, 1f, false, false, new ItemStack(Items.BOWL))
                .setEatingDuration(60)
                .nutrients(0.5f, 0f, 0.5f, 0.5f, 1f));
        HOT_BEETROOT_SOUP = addItem(132, "food.beetroot_soup.hot").setMaxStackSize(1).addComponents(new GTFOFoodStats(7, 1f, false, false, new ItemStack(Items.BOWL))
                .setEatingDuration(60)
                .nutrients(0f, 0f, 0.5f, 0f, 1.5f));
        HOT_RABBIT_STEW = addItem(133, "food.rabbit_stew.hot").setMaxStackSize(1).addComponents(new GTFOFoodStats(9, 0.9f, false, false, new ItemStack(Items.BOWL))
                .setEatingDuration(60)
                .nutrients(0f, 0f, 1f, 1.5f, 1f));

        KEBAB_KUBIDEH_COOKED = addItem(137, "food.kebab.kubide").addComponents(GTFOUtils.getKebabFood(6, 0.8f)
                .nutrients(0f, 0f, 0.5f, 1f, 0.75f));
        KEBAB_BARG_COOKED = addItem(139, "food.kebab.barg").addComponents(GTFOUtils.getKebabFood(6, 0.5f)
                .nutrients(0f, 0.25f, 0.5f, 1f, 0.5f));
        KEBAB_SOLTANI = addItem(140, "food.kebab.soltani").addComponents(GTFOUtils.getKebabFood(16, 1.1f)
                .nutrients(0f, 1f, 1f, 1.5f, 1f));
        KEBAB_ONION_COOKED = addItem(142, "food.kebab.onion").addComponents(GTFOUtils.getKebabFood(5, 0.3f)
                .nutrients(0f, 0f, 0f, 0f, 1.25f));
        KEBAB_TOMATO_COOKED = addItem(144, "food.kebab.tomato").addComponents(GTFOUtils.getKebabFood(5, 0.3f)
                .nutrients(0f, 1.25f, 0f, 0f, 0f));
        KEBAB_CHUM_COOKED = addItem(146, "food.kebab.chum").addComponents(new GTFOFoodStats(6, 0.3f, false, true, SKEWER.getStackForm(1),
                new RandomPotionEffect(MobEffects.NAUSEA, 100, 10, 100 - 10))
                .setEatingDuration(12)
                .nutrients(0f, 0f, 0.5f, 0.5f, 0f));
        KEBAB_CHUM_BUCKET = addItem(147, "food.kebab.chum.bucket").addComponents(new GTFOFoodStats(16, 2f, false, true, new ItemStack(Items.BUCKET),
                new RandomPotionEffect(MobEffects.NAUSEA, 500, 10, 100 - 50),
                new RandomPotionEffect(MobEffects.UNLUCK, 500, 11, 100 - 50),
                new RandomPotionEffect(MobEffects.SPEED, 500, 3, 100 - 50),
                new RandomPotionEffect(MobEffects.HEALTH_BOOST, 500, 3, 100 - 50))
                .setEatingDuration(60)
                .nutrients(0f, 1f, 1.5f, 1.5f, 1f));
        KEBAB_CARROT_COOKED = addItem(154, "food.kebab.carrot").addComponents(GTFOUtils.getKebabFood(4, 0.5f)
                .nutrients(0f, 0f, 0f, 0f, 1.25f));
        KEBAB_FAT_COOKED = addItem(151, "food.kebab.fat").addComponents(GTFOUtils.getKebabFood(4, 0.3f)
                .nutrients(0f, 0f, 0f, 0.5f, 0f));

        TOMATO_SLICE = addItem(79, "component.tomato_slice").addComponents(new GTFOFoodStats(1, 0.0f)
                .nutrients(0f, 0.75f, 0f, 0f, 0f));
        ONION_SLICE = addItem(80, "component.onion_slice").addComponents(new GTFOFoodStats(1, 0.0f)
                .nutrients(0f, 0f, 0f, 0f, 1f));
        CUCUMBER_SLICE = addItem(81, "component.cucumber_slice").addComponents(new GTFOFoodStats(1, 0.0f)
                .nutrients(0f, 0f, 0f, 0f, 0.75f));
        CARROT_SLICE = addItem(148, "component.carrot_slice").addComponents(new GTFOFoodStats(1, 0.0f)
                .nutrients(0f, 0f, 0f, 0f, 0.75f));
        APPLE_SLICE = addItem(152, "component.apple_slice").addComponents(new GTFOFoodStats(1, 0.1f)
                .nutrients(0f, 1f, 0f, 0f, 0f));
        EGGPLANT_SLICE = addItem(317, "component.eggplant_slice").addComponents(new GTFOFoodStats(1, 0.0f)
                .nutrients(0f, 0f, 0f, 0f, 0.75f));

        APPLE_JUICE = addItem(153, "food.juice.apple").addComponents(new GTFOFoodStats(3, 0.2f, true, true, new ItemStack(Items.GLASS_BOTTLE),
                new RandomPotionEffect(MobEffects.SPEED, 500, 1, 100 - 45))
                .nutrients(0f, 1f, 0f, 0f, 0f));
        ORANGE_JUICE = addItem(155, "food.juice.orange").addComponents(new GTFOFoodStats(3, 0.2f, true, true, new ItemStack(Items.GLASS_BOTTLE),
                new RandomPotionEffect(MobEffects.SPEED, 500, 1, 100 - 45))
                .nutrients(0f, 1f, 0f, 0f, 0f));
        KEBAB_MEAT_COOKED = addItem(157, "food.kebab.meat").addComponents(GTFOUtils.getKebabFood(3, 0.6f)
                .nutrients(0f, 0f, 0.25f, 1f, 0f));

        ICE_CREAM_PLAIN = addItem(165, "food.ice_cream.plain").addComponents(new GTFOFoodStats(4, 0.25f, false, true)
                .nutrients(1f, 0f, 0f, 0f, 0f));
        ICE_CREAM_CHUM = addItem(166, "food.ice_cream.chum").addComponents(new GTFOFoodStats(5, 0.33f, false, true)
                .nutrients(1f, 0f, 0f, 1f, 0f));
        ICE_CREAM_BANANA = addItem(167, "food.ice_cream.banana").addComponents(new GTFOFoodStats(6, 0.33f, false, true)
                .nutrients(1f, 1f, 0f, 0f, 0f));
        ICE_CREAM_BACON = addItem(168, "food.ice_cream.bacon").addComponents(new GTFOFoodStats(6, 0.33f, false, true)
                .nutrients(1f, 0f, 0f, 1f, 0f));
        ICE_CREAM_VANILLA = addItem(169, "food.ice_cream.vanilla").addComponents(new GTFOFoodStats(9, 0.25f, false, true, ItemStack.EMPTY,
                new RandomPotionEffect(SnowGolemSpawnerPotion.INSTANCE, 300, 0, 100 - 50))
                .nutrients(1f, 0f, 0f, 0f, 0.25f));
        ICE_CREAM_BEAR = addItem(170, "food.ice_cream.bear").addComponents(new GTFOFoodStats(7, 0.33f, false, true)
                .nutrients(1f, 0f, 0f, 1f, 0f));
        ICE_CREAM_MELON = addItem(171, "food.ice_cream.melon").addComponents(new GTFOFoodStats(5, 0.33f, false, true)
                .nutrients(1f, 1f, 0f, 0f, 0f));
        ICE_CREAM_CHOCOLATE = addItem(172, "food.ice_cream.chocolate").addComponents(new GTFOFoodStats(9, 0.25f, false, true)
                .nutrients(1f, 0f, 0f, 0.25f, 0.25f));
        ICE_CREAM_LEMON = addItem(173, "food.ice_cream.lemon").addComponents(new GTFOFoodStats(6, 0.33f, false, true)
                .nutrients(1f, 1f, 0f, 0f, 0f));
        ICE_CREAM_CHIP = addItem(174, "food.ice_cream.chip").addComponents(new GTFOFoodStats(8, 0.33f, false, true)
                .nutrients(1f, 0f, 0.5f, 0f, 0f));
        ICE_CREAM_RAINBOW = addItem(225, "food.ice_cream.rainbow").addComponents(new GTFOFoodStats(6, 0.33f, false, true, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.NIGHT_VISION, 1000, 0, 100 - 50))
                .nutrients(1.25f, 0.25f, 0f, 0f, 0.25f));

        MILK_CHOCOLATE = addItem(190, "food.chocolate").addComponents(new GTFOFoodStats(4, 1.25f)
                .nutrients(1f, 0f, 0f, 0f, 0.5f));
        GRAHAM_CRACKER = addItem(191, "food.graham_cracker").addComponents(new GTFOFoodStats(1, 1f)
                .nutrients(0f, 0f, 1.5f, 0.25f, 0f));
        SMORE_SMINGOT = addItem(192, "food.smore.one").addComponents(new GTFOFoodStats(8, 1.5f)
                .nutrients(0.5f, 0f, 1f, 0.5f, 0f));
        MORESMORE_DOUBLESMINGOT = addItem(193, "food.smore.two").addComponents(new GTFOFoodStats(20, 3.8f)
                .nutrients(0.5f, 0f, 1f, 0.5f, 0f));
        FOURSMORE_QUADSMINGOT = addItem(194, "food.smore.four").addComponents(new GTFOFoodStats(44, 8.61363636364f)
                .nutrients(0.5f, 0f, 1f, 0.5f, 0f));
        MARSHMALLOW = addItem(195, "food.marshmallow").addComponents(new GTFOFoodStats(1, 1f)
                .nutrients(0f, 0f, 0.5f, 0.5f, 0f));

        COFFEE_CUP = addItem(203, "food.coffee.normal").addComponents(new GTFOFoodStats(8, 0.4f, true, false, EMPTY_CUP.getStackForm(),
                new RandomPotionEffect(MobEffects.REGENERATION, 60, 1, 0),
                new RandomPotionEffect(MobEffects.SPEED, 1800, 2, 0))
                .nutrients(0f, 0.5f, 0.5f, 0.5f, 0f));
        ENERGIZING_COFFEE_CUP = addItem(204, "food.coffee.energized").addComponents(new GTFOFoodStats(8, 0.6f, true, false, EMPTY_CUP.getStackForm(),
                new RandomPotionEffect(MobEffects.REGENERATION, 200, 3, 0),
                new RandomPotionEffect(MobEffects.STRENGTH, 200, 1, 0),
                new RandomPotionEffect(MobEffects.RESISTANCE, 200, 1, 0),
                new RandomPotionEffect(MobEffects.SPEED, 1000, 3, 0))
                .nutrients(0f, 0.5f, 1f, 0.5f, 0f));

        MUSHY_PEAS = addItem(209, "food.mushy_peas").addComponents(new GTFOFoodStats(3, 1)
                .nutrients(0f, 0f, 0f, 0f, 1f));
        BREAD_SLICE = addItem(210, "food.bread_slice").addComponents(new GTFOFoodStats(1, 0.5f)
                .nutrients(0f, 0f, 1f, 0f, 0f));
        TOAST = addItem(211, "food.toast").addComponents(new GTFOFoodStats(2, 0.5f, false, true)
                .nutrients(0f, 0f, 1.5f, 0f, 0f));
        SANDWICH_TOAST = addItem(212, "food.sandwich.toast").addComponents(new GTFOFoodStats(6, 0.5f)
                .nutrients(0f, 0f, 1.5f, 0f, 0f));
        FISH_AND_CHIPS = addItem(213, "food.fish_and_chips").addComponents(new GTFOFoodStats(7, 0.6f)
                .nutrients(0f, 0f, 1f, 1f, 0.25f));
        FULL_BREAKFAST = addItem(214, "food.full_breakfast").addComponents(new GTFOFoodStats(10, 1.2f)
                .nutrients(0f, 1f, 1f, 1.5f, 1f));
        SHEPHERDS_PIE = addItem(215, "food.shepherds_pie").addComponents(new GTFOFoodStats(9, 1f, false, false, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.ABSORPTION, 1000, 0, 100 - 50))
                .nutrients(0f, 0f, 1f, 1f, 1f));
        SAUSAGE_ROLL = addItem(216, "food.sausage_roll").addComponents(new GTFOFoodStats(7, 0.7f)
                .nutrients(0f, 0f, 0.75f, 1f, 0f));
        BAKED_BEANS = addItem(217, "food.baked_beans").addComponents(new GTFOFoodStats(4, 1f)
                .nutrients(0f, 0.5f, 0f, 0.5f, 0f));
        BEANS_ON_TOAST = addItem(218, "food.beans_on_toast").addComponents(new GTFOFoodStats(7, 0.8f, false, false, ItemStack.EMPTY,
                new RandomPotionEffect(MobEffects.SATURATION, 10, 0, 100 - 20))
                .nutrients(0f, 0.25f, 0.75f, 0.5f, 0f));
        FRIED_FISH = addItem(219, "food.fried_fish").addComponents(new GTFOFoodStats(4, 0.3f)
                .nutrients(0f, 0f, 0.5f, 1f, 0f));
        BEER = addItem(220, "food.beer").addComponents(new GTFOFoodStats(2, 0.5f, true, true, new ItemStack(Items.GLASS_BOTTLE),
                new RandomPotionEffect(MobEffects.NAUSEA, 500, 0, 100 - 40))
                .nutrients(0f, 0f, 0.5f, 0f, 0f));
        SAUSAGE = addItem(222, "food.sausage").addComponents(new GTFOFoodStats(4, 0.7f)
                .nutrients(0f, 0f, 0.25f, 1f, 0f));

        NILK = addItem(226, "food.nilk").addComponents(new GTFOFoodStats(6, 4, true, true, new ItemStack(Items.GLASS_BOTTLE),
                new RandomPotionEffect(MobEffects.NAUSEA, 1000, 0, 100 - 80),
                new RandomPotionEffect(MobEffects.REGENERATION, 200, 2, 100 - 60))
                .nutrients(3f, 0f, 0f, 1f, 0f));

        GEL_CAPLET = addItem(229, "food.gel_caplet").blacklistKitchen().addComponents(new GTFOFoodStats(0, 1f, false, true, ItemStack.EMPTY));
        PARACETAMOL_CAPLET = addItem(230, "food.paracetamol_caplet").addComponents(new GTFOFoodStats(0, 1f, false, true, ItemStack.EMPTY, new RandomPotionEffect(MobEffects.REGENERATION, 400, 0, 0)).setEatingDuration(1));
        PLUTONIUM_241_CAPLET = addItem(231, "food.plutonium_241_caplet").addComponents(new GTFOFoodStats(0, 1f, false, true, ItemStack.EMPTY, new RandomPotionEffect(MobEffects.POISON, 7000, 0, 0)).setEatingDuration(1));

        BRUSCHETTA = addItem(272, "food.bruschetta").addComponents(new GTFOFoodStats(6, 0.5f)
                .setPotionEffects(new RandomPotionEffect(PotionAmplifierPotion.INSTANCE, 200, 0, 100 - 75))
                .nutrients(0f, 1f, 1f, 0.5f, 1f)); // I mean, you can technically hold this without a plate
        CAPONATA = addItem(273, "food.caponata").addComponents(new GTFOFoodStats(6, 0.9f).setReturnStack(DIRTY_CERAMIC_BOWL.getStackForm())
                .setPotionEffects(new RandomPotionEffect(PotionLengthenerPotion.INSTANCE, 200, 0, 100 - 75))
                .nutrients(0f, 0f, 0f, 0f, 2f));
        CARBONARA = addItem(274, "food.carbonara").addComponents(new GTFOFoodStats(9, 0.8f).setReturnStack(DIRTY_PLATE.getStackForm())
                .setPotionEffects(new RandomPotionEffect(MobEffects.HEALTH_BOOST, 1000, 0, 100 - 75))
                .nutrients(0.5f, 0f, 1f, 1f, 0f));
        CARCIOFI_ALLA_ROMANA = addItem(275, "food.carciofi_alla_romana").addComponents(new GTFOFoodStats(8, 1.3f).setReturnStack(DIRTY_PLATE.getStackForm())
                .setPotionEffects(new RandomPotionEffect(MobEffects.STRENGTH, 1500, 1, 100 - 95))
                .nutrients(0f, 0f, 0f, 0.5f, 1.5f));
        FETTUCCINE_ALFREDO = addItem(276, "food.fettuccine_alfredo").addComponents(new GTFOFoodStats(8, 0.4f).setReturnStack(DIRTY_PLATE.getStackForm())
                .setPotionEffects(new RandomPotionEffect(MobEffects.RESISTANCE, 1500, 1, 100 - 80))
                .setEatingDuration(20)
                .nutrients(0.75f, 0f, 1f, 0.5f, 0f));
        PARMIGIANA = addItem(277, "food.parmigiana").addComponents(new GTFOFoodStats(8, 1.1f).setReturnStack(DIRTY_PLATE.getStackForm())
                .setPotionEffects(new RandomPotionEffect(MobEffects.REGENERATION, 500, 0, 100 - 75))
                .nutrients(1f, 0.5f, 0f, 0.25f, 1f));
        PASTA_E_FAGIOLI = addItem(278, "food.pasta_e_fagioli").addComponents(new GTFOFoodStats(4, 2.5f).setReturnStack(DIRTY_CERAMIC_BOWL.getStackForm())
                .setPotionEffects(new RandomPotionEffect(MobEffects.HASTE, 3000, 1, 100 - 75))
                .nutrients(0f, 0f, 1f, 1.5f, 1.5f));
        PASTA_ALLA_NORMA = addItem(279, "food.pasta_alla_norma").addComponents(new GTFOFoodStats(12, 0.7f).setReturnStack(DIRTY_PLATE.getStackForm())
                .setEatingDuration(128)
                .nutrients(0f, 1f, 1f, 0f, 1.25f));
        PASTA_AL_POMODORO = addItem(280, "food.pasta_al_pomodoro").addComponents(new GTFOFoodStats(5, 0.5f).setReturnStack(DIRTY_PLATE.getStackForm())
                .setPotionEffects(new RandomPotionEffect(PotionAmplifierPotion.INSTANCE, 160, 1, 100 - 50)).setEatingDuration(16)
                .nutrients(0f, 1.25f, 1f, 0f, 0.75f));
        POLENTA = addItem(281, "food.polenta").addComponents(new GTFOFoodStats(6, 0.4f).setReturnStack(DIRTY_CERAMIC_BOWL.getStackForm())
                .setPotionEffects(new RandomPotionEffect(MobEffects.SATURATION, 20, 0, 100 - 50))
                .nutrients(0f, 0.75f, 0.75f, 0.75f, 0f));
        RAFANATA = addItem(282, "food.rafanata").addComponents(new GTFOFoodStats(7, 1f).setReturnStack(DIRTY_PLATE.getStackForm())
                .setPotionEffects(new RandomPotionEffect(MobEffects.JUMP_BOOST, 500, 0, 100 - 80))
                .nutrients(0f, 0f, 1f, 0.5f, 0.75f));
        RISOTTO = addItem(283, "food.risotto").addComponents(new GTFOFoodStats(10, 0.8f).setReturnStack(DIRTY_CERAMIC_BOWL.getStackForm())
                .setPotionEffects(new RandomPotionEffect(MobEffects.SPEED, 8000, 1, 100 - 100))
                .nutrients(1f, 0.25f, 1f, 0.75f, 0.75f));
        SPAGHETTI_ALLASSASSINA = addItem(284, "food.spaghetti_all'assassina").addComponents(new GTFOFoodStats(6, 0.8f).setReturnStack(DIRTY_PLATE.getStackForm())
                .setPotionEffects(new RandomPotionEffect(MobEffects.STRENGTH, 60, 10, 100 - 60))
                .nutrients(0f, 0.75f, 1f, 0f, 0f));
        TAGLIATELLE_AL_RAGU = addItem(285, "food.tagliatelle_al_ragu").addComponents(new GTFOFoodStats(14, 0.7f).setReturnStack(DIRTY_PLATE.getStackForm())
                .nutrients(0.75f, 0.5f, 0f, 1.25f, 1f));
        TORTELLINI_IN_BRODO = addItem(286, "food.tortellini_in_brodo").addComponents(new GTFOFoodStats(10, 0.5f).setReturnStack(DIRTY_CERAMIC_BOWL.getStackForm())
                .nutrients(0f, 0f, 0f, 1.75f, 1.75f));
        VITELLO_TONNATO = addItem(287, "food.vitello_tonnato").addComponents(new GTFOFoodStats(10, 1f).setReturnStack(DIRTY_PLATE.getStackForm())
                .setPotionEffects(new RandomPotionEffect(PotionLengthenerPotion.INSTANCE, 400, 1, 100 - 90), new RandomPotionEffect(PotionAmplifierPotion.INSTANCE, 400, 0, 100 - 90))
                .nutrients(0f, 0f, 0f, 2.5f, 1.5f));
        LASAGNA_CHUM = addItem(288, "food.lasagna.chum").addComponents(new GTFOFoodStats(9, 0.7f).setReturnStack(DIRTY_PLATE.getStackForm())
                .setPotionEffects(new RandomPotionEffect(MobEffects.LUCK, 3000, 0, 100 - 80)).setEatingDuration(64)
                .nutrients(0.5f, 0.5f, 1f, 0.5f, 0f));
        LASAGNA_NAPOLETANA = addItem(289, "food.lasagna.napoletana").addComponents(new GTFOFoodStats(11, 0.7f).setReturnStack(DIRTY_PLATE.getStackForm())
                .setPotionEffects(new RandomPotionEffect(MobEffects.NIGHT_VISION, 3000, 0, 100 - 90)).setEatingDuration(64)
                .nutrients(0.5f, 0.75f, 1f, 1f, 0.25f));
        LASAGNA_PESTO = addItem(290, "food.lasagna.pesto").addComponents(new GTFOFoodStats(15, 0.7f).setReturnStack(DIRTY_PLATE.getStackForm())
                .setPotionEffects(new RandomPotionEffect(MobEffects.FIRE_RESISTANCE, 3000, 0, 100 - 100)).setEatingDuration(64)
                .nutrients(1f, 0f, 1f, 1f, 0.25f));
        PASTA_ALLAMOGUS = addItem(295, "food.pasta_all'amogus").addComponents(new GTFOFoodStats(5, 0.1f).setReturnStack(DIRTY_PLATE.getStackForm())
                .setPotionEffects(new RandomPotionEffect(VentingPotion.INSTANCE, 400, 0, 100 - 50))
                .nutrients(0f, 0.75f, 1f, 0f, 0.5f));

        PORCHETTA_SLICE = addItem(311, "food.porchetta_slice").addComponents(new GTFOFoodStats(2, 0.7f).setEatingDuration(5)
                .nutrients(0f, 0f, 0f, 1f, 0.25f));

        WHITE_WINE = addItem(319, "food.white_wine").addComponents(new GTFOFoodStats(6, 0.7f, true, true, new ItemStack(Items.GLASS_BOTTLE),
                new RandomPotionEffect(MobEffects.NAUSEA, 600, 0, 100 - 60),
                new RandomPotionEffect(MobEffects.ABSORPTION, 400, 1, 100 - 40))
                .setEatingDuration(96));
        RED_WINE = addItem(324, "food.red_wine").addComponents(new GTFOFoodStats(4, 0.7f, true, true, new ItemStack(Items.GLASS_BOTTLE),
                new RandomPotionEffect(MobEffects.NAUSEA, 600, 0, 100 - 60),
                new RandomPotionEffect(MobEffects.RESISTANCE, 400, 0, 100 - 40))
                .setEatingDuration(96));

        EMERGENCY_RATIONS = addItem(325, "food.emergency_rations").addComponents(new GTFOFoodStats(5, 1.0f, false, true)
                .setPotionEffects(new RandomPotionEffect(MobEffects.NAUSEA, 400, 0, 100 - 10)).setEatingDuration(60)
                .nutrients(0f, 0.5f, 0f, 1f, 0.5f));

        BLUEBERRY = addItem(326, "food.berry.blueberry").addComponents(new GTFOFoodStats(1, 0.5f).nutrients(0f, 1f, 0f, 0f, 0f))
                .addOreDict("cropBlueberry").addOreDict("listAllfruit").addOreDict("listAllberry").addOreDict("listAllberrysweet");
        BLUEBERRY.addComponents(new GTFOBerrySeedBehaviour(GTFOCrops.BUSH_BLUEBERRY, BLUEBERRY.getStackForm(), BLUEBERRY.getStackForm()));
        BLACKBERRY = addItem(327, "food.berry.blackberry").addComponents(new GTFOFoodStats(1, 0.5f).nutrients(0f, 1f, 0f, 0f, 0f))
                .addOreDict("cropBlackberry").addOreDict("listAllfruit").addOreDict("listAllberry").addOreDict("listAllberrytart");
        BLACKBERRY.addComponents(new GTFOBerrySeedBehaviour(GTFOCrops.BUSH_BLACKBERRY, BLACKBERRY.getStackForm(), BLACKBERRY.getStackForm()));
        RASPBERRY = addItem(328, "food.berry.raspberry").addComponents(new GTFOFoodStats(1, 0.5f).nutrients(0f, 1f, 0f, 0f, 0f))
                .addOreDict("cropRaspberry").addOreDict("listAllfruit").addOreDict("listAllberry").addOreDict("listAllberrysweet");
        RASPBERRY.addComponents(new GTFOBerrySeedBehaviour(GTFOCrops.BUSH_RASPBERRY, RASPBERRY.getStackForm(), RASPBERRY.getStackForm()));
        STRAWBERRY = addItem(329, "food.berry.strawberry").addComponents(new GTFOFoodStats(1, 0.5f).nutrients(0f, 1f, 0f, 0f, 0f))
                .addOreDict("cropStrawberry").addOreDict("listAllfruit").addOreDict("listAllberry").addOreDict("listAllberrysweet");
        STRAWBERRY.addComponents(new GTFOBerrySeedBehaviour(GTFOCrops.BUSH_STRAWBERRY, STRAWBERRY.getStackForm(), STRAWBERRY.getStackForm()));
        RED_CURRANT = addItem(330, "food.berry.red_currant").addComponents(new GTFOFoodStats(1, 0.5f).nutrients(0f, 1f, 0f, 0f, 0f))
                .addOreDict("cropRedCurrant").addOreDict("listAllfruit").addOreDict("listAllberry").addOreDict("listAllberrytart");
        RED_CURRANT.addComponents(new GTFOBerrySeedBehaviour(GTFOCrops.BUSH_RED_CURRANT, RED_CURRANT.getStackForm(), RED_CURRANT.getStackForm()));
        BLACK_CURRANT = addItem(331, "food.berry.black_currant").addComponents(new GTFOFoodStats(1, 0.5f).nutrients(0f, 1f, 0f, 0f, 0f))
                .addOreDict("cropBlackCurrant").addOreDict("listAllfruit").addOreDict("listAllberry").addOreDict("listAllberrytart");
        BLACK_CURRANT.addComponents(new GTFOBerrySeedBehaviour(GTFOCrops.BUSH_BLACK_CURRANT, BLACK_CURRANT.getStackForm(), BLACK_CURRANT.getStackForm()));
        WHITE_CURRANT = addItem(332, "food.berry.white_currant").addComponents(new GTFOFoodStats(1, 0.5f).nutrients(0f, 1f, 0f, 0f, 0f))
                .addOreDict("cropWhiteCurrant").addOreDict("listAllfruit").addOreDict("listAllberry").addOreDict("listAllberrytart");
        WHITE_CURRANT.addComponents(new GTFOBerrySeedBehaviour(GTFOCrops.BUSH_WHITE_CURRANT, WHITE_CURRANT.getStackForm(), WHITE_CURRANT.getStackForm()));
        LINGONBERRY = addItem(333, "food.berry.lingonberry").addComponents(new GTFOFoodStats(1, 0.5f).nutrients(0f, 1f, 0f, 0f, 0f))
                .addOreDict("cropLingonberry").addOreDict("listAllfruit").addOreDict("listAllberry").addOreDict("listAllberrytart");
        LINGONBERRY.addComponents(new GTFOBerrySeedBehaviour(GTFOCrops.BUSH_LINGONBERRY, LINGONBERRY.getStackForm(), LINGONBERRY.getStackForm()));
        ELDERBERRY = addItem(334, "food.berry.elderberry").addComponents(new GTFOFoodStats(1, 0.5f).nutrients(0f, 1f, 0f, 0f, 0f).setPotionEffects(
                        new RandomPotionEffect(MobEffects.NAUSEA, 400, 0, 100 - 4),
                        new RandomPotionEffect(MobEffects.POISON, 200, 0, 100 - 1)))
                .addOreDict("cropElderberry").addOreDict("listAllfruit").addOreDict("listAllberry");
        ELDERBERRY.addComponents(new GTFOBerrySeedBehaviour(GTFOCrops.BUSH_ELDERBERRY, ELDERBERRY.getStackForm(), ELDERBERRY.getStackForm()));
        CRANBERRY = addItem(335, "food.berry.cranberry").addComponents(new GTFOFoodStats(1, 0.5f).nutrients(0f, 1f, 0f, 0f, 0f))
                .addOreDict("cropCranberry").addOreDict("listAllfruit").addOreDict("listAllberry").addOreDict("listAllberrysweet");
        CRANBERRY.addComponents(new GTFOBerrySeedBehaviour(GTFOCrops.BUSH_CRANBERRY, CRANBERRY.getStackForm(), CRANBERRY.getStackForm()));

        BERRY_MEDLEY = addItem(336, "food.berry_medley").addComponents(new GTFOFoodStats(5, 0.5f, false, false, new ItemStack(Items.BOWL)).nutrients(0f, 1f, 0f, 0f, 0f));
        ETIRPS_CRANBERRY = addItem(337, "food.etirps_cranberry").addComponents(new GTFOFoodStats(GTFOConfig.gtfoFoodConfig.etirpsHunger + 3, GTFOConfig.gtfoFoodConfig.etirpsSaturation + 0.3f, true, true, PLASTIC_BOTTLE.getStackForm(),
                new RandomPotionEffect(MobEffects.SPEED, 1200, 2, 0),
                new RandomPotionEffect(MobEffects.REGENERATION, 200, 1, 100 - 80))
                .nutrients(0f, 0.5f, 0f, 0f, 0f));

        PELMENI = addItem(340, "food.pelmeni").addComponents(new GTFOFoodStats(5, 0.5f)
                .nutrients(0f, 0f, 1f, 1f, 0.125f));
        SEASONED_PELMENI = addItem(341, "food.pelmeni_seasoned").addComponents(new GTFOFoodStats(7, 1f).setEatingDuration(24)
                .nutrients(0.5f, 0f, 1f, 1f, 1f));

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
        OREGANO_SEED = addItem(303, "seed.oregano");
        OREGANO_SEED.addComponents(new GTFOCropSeedBehaviour(GTFOCrops.CROP_OREGANO, OREGANO_SEED.getStackForm(), OREGANO.getStackForm()));
        BASIL_SEED = addItem(304, "seed.basil");
        BASIL_SEED.addComponents(new GTFOCropSeedBehaviour(GTFOCrops.CROP_BASIL, BASIL_SEED.getStackForm(), BASIL.getStackForm()));
        AUBERGINE_SEED = addItem(305, "seed.aubergine");
        AUBERGINE_SEED.addComponents(new GTFOCropSeedBehaviour(GTFOCrops.CROP_AUBERGINE, AUBERGINE_SEED.getStackForm(), AUBERGINE.getStackForm()));
        HORSERADISH_SEED = addItem(306, "seed.horseradish");
        HORSERADISH_SEED.addComponents(new GTFOCropSeedBehaviour(GTFOCrops.CROP_HORSERADISH, HORSERADISH_SEED.getStackForm(), HORSERADISH.getStackForm()));
        GARLIC_CLOVE = addItem(307, "seed.garlic");
        GARLIC_CLOVE.addComponents(new GTFOCropSeedBehaviour(GTFOCrops.CROP_GARLIC, GARLIC_CLOVE.getStackForm(), GARLIC_BULB.getStackForm()));
        HORSERADISH_SEED.addComponents(new GTFOCropSeedBehaviour(GTFOCrops.CROP_HORSERADISH, HORSERADISH_SEED.getStackForm(), HORSERADISH.getStackForm()));
        ARTICHOKE_HEART = addItem(267, "component.artichoke");
        ARTICHOKE_SEED = addItem(268, "seed.artichoke");
        ARTICHOKE_SEED.addComponents(new GTFOCropSeedBehaviour(GTFOCrops.CROP_ARTICHOKE, ARTICHOKE_SEED.getStackForm(), ARTICHOKE_HEART.getStackForm()));
        BLACK_PEPPERCORN = addItem(269, "component.black_pepper");
        BLACK_PEPPERCORN.addComponents(new GTFOCropSeedBehaviour(GTFOCrops.CROP_BLACK_PEPPER, BLACK_PEPPERCORN.getStackForm(), BLACK_PEPPERCORN.getStackForm()));
        RICE = addItem(270, "component.rice");
        RICE.addComponents(new GTFOCropSeedBehaviour(GTFOCrops.CROP_RICE, RICE.getStackForm(), RICE.getStackForm()));
        NUTMEG_SEED = addItem(271, "component.nutmeg");
        WHITE_GRAPES = addItem(320, "food.white_grapes").addComponents(new GTFOFoodStats(1, 1f).nutrients(0f, 1f, 0f, 0f, 0f));
        WHITE_GRAPE_SEED = addItem(321, "seed.white_grape");
        WHITE_GRAPE_SEED.addComponents(new GTFOCropSeedBehaviour(GTFOCrops.CROP_WHITE_GRAPE, WHITE_GRAPE_SEED.getStackForm(), WHITE_GRAPES.getStackForm()));


        // 175-189 left blank for organic circuits
        SPRINKLER_COVER = addItem(224, "cover.sprinkler").blacklistKitchen();

        BLANK_PASTA_DIE = addItem(246, "shape.pasta.blank").blacklistKitchen();
        TAGLIATELLE_PASTA_DIE = addItem(247, "shape.pasta.tagliatelle").blacklistKitchen();
        SPAGHETTI_PASTA_DIE = addItem(248, "shape.pasta.spaghetti").blacklistKitchen();
        DITALINI_PASTA_DIE = addItem(249, "shape.pasta.ditalini").blacklistKitchen();
        RIGATONI_PASTA_DIE = addItem(250, "shape.pasta.rigatoni").blacklistKitchen();
        LASAGNA_PASTA_DIE = addItem(251, "shape.pasta.lasagna").blacklistKitchen();

        KITCHEN_RECIPE = addItem(343, "utility.kitchen_recipe").blacklistKitchen().addComponents(new GTFOKitchenRecipeBehaviour());

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
                        .setEatingDuration(32 + 10 * i).nutrients(0.5f, 0f, 1f, 0.5f, 0f));
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


    protected String formatModelPath(GTFOMetaItem.GTFOMetaValueItem metaValueItem) {
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

    public class GTFOMetaValueItem extends MetaItem<?>.MetaValueItem {

        private boolean kitchenBlacklisted;
        protected GTFOMetaValueItem(int metaValue, String unlocalizedName) {
            super(metaValue, unlocalizedName);
        }

        protected GTFOMetaValueItem blacklistKitchen() {
            kitchenBlacklisted = true;
            return this;
        }

        public boolean isKitchenBlacklisted() {
            return kitchenBlacklisted;
        }

        protected void addItemComponentsInternal(IItemComponent... stats) {
            super.addItemComponentsInternal(stats);
            for (IItemComponent stat : stats) {
                if (stat instanceof GTFOFoodStats) {
                    try {
                        Class clazz = Class.forName("gregtech.api.items.metaitem.MetaItem$MetaValueItem");
                        Field field = clazz.getDeclaredField("useManager");
                        field.setAccessible(true);
                        field.set(this, new GTFOFoodUseManager((GTFOFoodStats) stat));
                    } catch (Exception e) {
                        GTFOLog.logger.error("Failed to add GTFOFoodStats to GTFOMetaValueItem", e);
                        e.printStackTrace();
                    }
                }
            }
        }

    }

}
