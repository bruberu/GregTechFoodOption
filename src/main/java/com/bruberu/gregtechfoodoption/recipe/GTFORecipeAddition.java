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
        if(Loader.isModLoaded("nuclearcraft"))
            if(GTFOConfig.GTFONCConfig.smoreChain)
                SmogusChain.init();
        if(Loader.isModLoaded("actuallyadditions"))
            if(GTFOConfig.GTFOAAConfig.coffeeChain)
                CoffeeChain.init();
    }
}
