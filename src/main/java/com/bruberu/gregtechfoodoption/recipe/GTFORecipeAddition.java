package com.bruberu.gregtechfoodoption.recipe;

import com.bruberu.gregtechfoodoption.GTFOConfig;
import com.bruberu.gregtechfoodoption.recipe.chain.*;


public class GTFORecipeAddition {
    public static void init()
    {
        CoreChain.init();
        SeedsChain.init();
        CheeseChain.init();
        BananaProcessingChain.init();
        CulinaryGeneratorChain.init();
        GTFOMachineRecipes.init();
        if(GTFOConfig.gtfoChainsConfig.popcornChain)
            PopcornChain.init();
        if(GTFOConfig.gtfoChainsConfig.mineralWaterChain)
            MineralWaterChain.init();
        if(GTFOConfig.gtfoChainsConfig.purpleDrinkChain)
            PurpleDrinkChain.init();
        if(GTFOConfig.gtfoChainsConfig.potatoProcessingChain)
            PotatoProcessingChain.init();
        if(GTFOConfig.gtfoChainsConfig.alcoholChain)
            AlcoholChain.init();
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
