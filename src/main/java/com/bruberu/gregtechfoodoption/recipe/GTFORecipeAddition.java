package com.bruberu.gregtechfoodoption.recipe;

import com.bruberu.gregtechfoodoption.GTFOConfig;
import com.bruberu.gregtechfoodoption.recipe.chain.*;
import net.minecraftforge.fml.common.Loader;


public class GTFORecipeAddition {
    public static void init()
    {
        CoreChain.init();
        GTFOMachineRecipes.init();
        if(GTFOConfig.popcornChain)
            PopcornChain.init();
        if(GTFOConfig.mineralWaterChain)
            MineralWaterChain.init();
        if(GTFOConfig.purpleDrinkChain)
            PurpleDrinkChain.init();
        if(GTFOConfig.potatoProcessingChain)
            PotatoProcessingChain.init();
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
