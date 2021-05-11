package com.bruberu.gregtechfoodoption.recipe;

import com.bruberu.gregtechfoodoption.GTFOConfig;
import com.bruberu.gregtechfoodoption.recipe.chain.CoffeeChain;
import com.bruberu.gregtechfoodoption.recipe.chain.MineralWaterChain;
import com.bruberu.gregtechfoodoption.recipe.chain.PopcornChain;
import com.bruberu.gregtechfoodoption.recipe.chain.SmogusChain;
import net.minecraftforge.fml.common.Loader;


public class GTFORecipeAddition {
    public static void init()
    {
        if(GTFOConfig.popcornChain)
            PopcornChain.init();
        if(GTFOConfig.mineralWaterChain)
            MineralWaterChain.init();
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
