package gregtechfoodoption.recipe;

import gregtech.api.GTValues;
import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.recipe.chain.*;


public class GTFORecipeAddition {
    public static void init()
    {
        CoreChain.init();
        SeedsChain.init();
        CheeseChain.init();
        BananaProcessingChain.init();
        GTFOMachineRecipes.init();
        if(GTFOConfig.gtfoChainsConfig.potatoProcessingChain)
            PotatoProcessingChain.init();
        if(GTFOConfig.gtfoChainsConfig.alcoholChain)
            AlcoholChain.init();
        if (GTValues.isModLoaded(GTFOValues.MODID_GCYS)) {
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

        if(GTFOConfig.gtfoncConfig.nuclearCompat) {
            if (GTFOConfig.gtfoncConfig.smoreChain)
                SmogusChain.init();
        }
        if(GTFOConfig.gtfoaaConfig.actuallyCompat)
            if(GTFOConfig.gtfoaaConfig.coffeeChain)
                CoffeeChain.init();
    }
}
