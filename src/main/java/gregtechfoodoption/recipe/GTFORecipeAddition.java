package gregtechfoodoption.recipe;

import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.block.GTFOTree;
import gregtechfoodoption.recipe.chain.*;
import net.minecraftforge.fml.common.Loader;

public class GTFORecipeAddition {
    public static void init()
    {
        CoreChain.init();
        GTFOTree.TREES.forEach(GTFOTree::initRecipes);
        GreenhouseChain.init();
        MobExtractionChain.init();
        SeedsChain.init();
        CheeseChain.init();
        BananaProcessingChain.init();
        MicrowaveChain.init();
        GTFOMachineRecipes.init();
        KebabChain.init();
        IceCreamChain.init();
        VanillinChain.init();
        if(GTFOConfig.gtfoChainsConfig.potatoProcessingChain)
            PotatoProcessingChain.init();
        if(GTFOConfig.gtfoChainsConfig.alcoholChain)
            AlcoholChain.init();
        if (Loader.isModLoaded(GTFOValues.MODID_GCYS)) {
            if(GTFOConfig.gtfoChainsConfig.popcornChain)
                PopcornChain.init();
            if(GTFOConfig.gtfoChainsConfig.mineralWaterChain)
                MineralWaterChain.init();
            if(GTFOConfig.gtfoChainsConfig.purpleDrinkChain)
                PurpleDrinkChain.init();
        }
    }

    public static void lowInit() {
    }

    public static void compatInit() {
        if(GTFOConfig.gtfoChainsConfig.breadsChain)
            BreadsChain.init();
        if(GTFOConfig.gtfoVanillaOverridesConfig.vanillaOverrideChain)
            VanillaOverrideChain.init();
        CoffeeChain.init();
        SmogusChain.init();
    }
}
