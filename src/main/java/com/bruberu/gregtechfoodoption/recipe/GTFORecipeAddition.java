package com.bruberu.gregtechfoodoption.recipe;

import com.bruberu.gregtechfoodoption.GTFOConfig;
import com.bruberu.gregtechfoodoption.recipe.chain.*;


public class GTFORecipeAddition {
    public static void init()
    {
        CoreChain.init();
        GTFOMachineRecipes.init();
        if(GTFOConfig.gtfoChainsConfig.popcornChain)
            PopcornChain.init();
        if(GTFOConfig.gtfoChainsConfig.mineralWaterChain)
            MineralWaterChain.init();
        if(GTFOConfig.gtfoChainsConfig.purpleDrinkChain)
            PurpleDrinkChain.init();
        if(GTFOConfig.gtfoChainsConfig.potatoProcessingChain)
            PotatoProcessingChain.init();
        if(GTFOConfig.gtfoChainsConfig.breadsChain)
            BreadsChain.init();
    }

    public static void compatInit()
    {
        if(GTFOConfig.gtfoncConfig.nuclearCompat) {
            if (GTFOConfig.gtfoncConfig.smoreChain)
                SmogusChain.init();
        }
        if(GTFOConfig.gtfoaaConfig.actuallyCompat)
            if(GTFOConfig.gtfoaaConfig.coffeeChain)
                CoffeeChain.init();
    }
}
