package gregtechfoodoption.recipe;

import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.block.GTFOTree;
import gregtechfoodoption.recipe.chain.*;
import gregtechfoodoption.tools.GTFOToolItems;
import net.minecraftforge.fml.common.Loader;

public class GTFORecipeAddition {
    public static void init() {
        CoreChain.init();
        GTFOTree.TREES.forEach(GTFOTree::initRecipes);
        GreenhouseChain.init();
        MobExtractionChain.init();
        SeedsChain.init();
        CheeseChain.init();
        BananaProcessingChain.init();
        MicrowaveChain.init();
        GTFOMachineRecipes.init();
        GTFOToolItems.registerCustomRecipes();
        KebabChain.init();
        IceCreamChain.init();
        VanillinChain.init();
        DyeChain.init();
        BritishChain.init();
        PotatoProcessingChain.init();
        AlcoholChain.init();
        if (Loader.isModLoaded(GTFOValues.MODID_GCYS)) {
            PopcornChain.init();
            MineralWaterChain.init();
            PurpleDrinkChain.init();
        }
    }

    public static void lowInit() {
    }

    public static void compatInit() {
        BreadsChain.init();
        if (GTFOConfig.gtfoVanillaOverridesConfig.vanillaOverrideChain)
            VanillaOverrideChain.init();
        CoffeeChain.init();
        SmogusChain.init();
    }
}
