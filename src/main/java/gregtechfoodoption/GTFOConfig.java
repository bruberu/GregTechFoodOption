package gregtechfoodoption;

import net.minecraftforge.common.config.Config;

@Config(modid = GregTechFoodOption.MODID)

public class GTFOConfig {

    @Config.Comment("Show tooltips on shift?")
    public static boolean showTooltipsOnShift = false;

    @Config.Comment("Show tooltips always, regardless of what keys are held?")
    public static boolean showTooltipsAlways = true;

    @Config.Comment("Chain Options")
    public static GTFOChainsConfig gtfoChainsConfig = new GTFOChainsConfig();

    @Config.Comment("Vanilla Overrides Options")
    public static GTFOVanillaOverridesConfig gtfoVanillaOverridesConfig = new GTFOVanillaOverridesConfig();

    @Config.Comment("AppleCore compatibility")
    public static GTFOAppleCoreConfig gtfoAppleCoreConfig = new GTFOAppleCoreConfig();

    @Config.Comment("NuclearCraft compatibility")
    public static GTFONCConfig gtfoncConfig = new GTFONCConfig();

    @Config.Comment("ActuallyAdditions compatibility")
    public static GTFOAAConfig gtfoaaConfig = new GTFOAAConfig();

    @Config.Comment("Add Hunger and Saturation values for GTFO foods")
    public static GTFOFoodConfig gtfoFoodConfig = new GTFOFoodConfig();

    @Config.Comment("Effect options for GTFO. NOTE: None of these actually remove the appearance of the effects in-game, they just remove the functionality.")
    public static GTFOPotionConfig gtfoPotionConfig = new GTFOPotionConfig();

    @Config.Comment("Miscellaneous features for GTFO.")
    public static GTFOMiscConfig gtfoMiscConfig = new GTFOMiscConfig();

    public static class GTFOChainsConfig {
/*        @Config.Comment("Add Popcorn Chain?")
        public boolean popcornChain = true;

        @Config.Comment("Add Mineral Water Chain?")
        public boolean mineralWaterChain = true;

        @Config.Comment("Add Purple Drink Chain? (Note: also adds Etirps and Apple Hard Candy as sideproducts.)")
        public boolean purpleDrinkChain = true;*/

        @Config.Comment("Delete vanilla bread recipe?")
        public boolean deleteBreadRecipe = false;
    }

    public static class GTFOVanillaOverridesConfig {
        @Config.Comment("Enable most GTFO Vanilla Overrides features, usually adding small chains to food items while removing normal recipes?")
        public boolean vanillaOverrideChain = false;

        @Config.Comment("Make Baking Oven Recipes for Meats in the Vanilla Overrides chain (and delete normal furnace recipes for them), if the chain itself is enabled?")
        public boolean useBakingOvenForMeats = false;

        @Config.Comment("Replace manual paper recipe with one with a Rolling Pin?")
        public boolean useRollingPinForPaper = false;
    }

    public static class GTFOAppleCoreConfig {
        @Config.Comment("Should AppleCore compatibility be turned on?")
        public boolean appleCoreCompat = false;

        @Config.Comment("Should all foods not from GregTech Food Option have reduced hunger and saturation stats, to incentivize using the foods from GTFO?")
        public boolean reduceForeignFoodStats = false;

        @Config.Comment("Use the default GregTech Food Option food stats reduction (a logistic curve)?")
        public boolean useDefaultForeignFoodStatsReduction = false;

        @Config.Comment("If the above is false, you can set this to divide all vanilla food items by some value.")
        public int constantFoodStatsDivisor = 1;

        public void setAllToFalse() {
            appleCoreCompat = false;
            reduceForeignFoodStats = false;
        }
    }

    public static class GTFONCConfig {
        @Config.Comment("Should NuclearCraft compatibility be turned on? (Note: only works for NuclearCraft:Overhauled)")
        public boolean nuclearCompat = true;

        @Config.Comment("Add NuclearCraft S'more recipes?")
        public boolean smoreChain = true;

        @Config.Comment("Add NuclearCraft S'more extensions?")
        public boolean addSmogus = true;

        public void setAllToFalse() {
            nuclearCompat = false;
            smoreChain = false;
        }
    }

    public static class GTFOAAConfig {
        @Config.Comment("Should ActuallyAdditions compatibility be turned on?")
        public boolean actuallyCompat = true;

        @Config.Comment("Disable AA Coffee Maker's recipe?")
        public boolean disableCoffeeMaker = true;

        @Config.Comment("Add AA Coffee Chain?")
        public boolean coffeeChain = true;

        public void setAllToFalse() {
            actuallyCompat = false;
            disableCoffeeMaker = false;
        }
    }



    public static class GTFOFoodConfig {
        @Config.Comment("Popcorn hunger")
        public int popcornHunger = 5;

        @Config.Comment("Popcorn saturation")
        public float popcornSaturation = 0.4f;

        @Config.Comment("Mineral Water hunger")
        public int mineralWaterHunger = 0;

        @Config.Comment("Mineral Water saturation")
        public float mineralWaterSaturation = 0;

        @Config.Comment("Popcorn hunger")
        public int limeHunger = 1;

        @Config.Comment("Lime saturation")
        public float limeSaturation = 0.5f;

        @Config.Comment("Lemon hunger")
        public int lemonHunger = 1;

        @Config.Comment("Lemon saturation")
        public float lemonSaturation = 0.5f;

        @Config.Comment("Etirps hunger")
        public int etirpsHunger = 0;

        @Config.Comment("Etirps saturation")
        public float etirpsSaturation = 0;

        @Config.Comment("Hard candy hunger")
        public int hardCandyHunger = 1;

        @Config.Comment("Hard candy saturation")
        public float hardCandySaturation = 1;

        @Config.Comment("Sparkling water hunger")
        public int sparklingWaterHunger = 1;

        @Config.Comment("Sparkling water saturation")
        public float sparklingWaterSaturation = 1;

        @Config.Comment("French fries hunger")
        public int friesHunger = 3;

        @Config.Comment("French fries saturation")
        public float friesSaturation = 0;

        @Config.Comment("Chip hunger (also affects the hunger of the other chip items)")
        public int chipHunger = 2;

        @Config.Comment("Chip saturation (also affects the hunger of the other chip items)")
        public float chipSaturation = 0.5f;

        @Config.Comment("Baguette hunger")
        public int baguetteHunger = 2;

        @Config.Comment("Baguette saturation")
        public float baguetteSaturation = 1f;


        @Config.Comment("Return bag after eating popcorn?")
        public boolean popcornReturnsContainer = true;

        @Config.Comment("Return used thermos after drinking mineral water?")
        public boolean mineralWaterReturnsContainer = true;
    }

    public static class GTFOPotionConfig {
        @Config.Comment("Apply effects of Creativity?")
        public boolean creativity = true;

        @Config.Comment("Apply effects of Step Assist?")
        public boolean stepAssist = true;

        @Config.Comment("Apply effects of Snow Golem Spawner?")
        public boolean snowGolemSpawner = true;

    }

    public static class GTFOMiscConfig {
        @Config.Comment("Create methane centrifuging recipes for GTFO seeds?")
        public boolean centrifugeSeeds = true;

        @Config.Comment("Greenhouse replacements for dirt (use block names like minecraft:dirt or blockstates like minecraft:dirt[snowy=true])")
        @Config.RequiresMcRestart
        public String[] greenhouseDirts = new String[0];

        @Config.Comment("Add furnace recipes for each baking oven recipe?")
        public boolean bakingOvenReplacement = false;
    }
}
