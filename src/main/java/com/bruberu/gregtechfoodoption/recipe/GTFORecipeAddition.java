package com.bruberu.gregtechfoodoption.recipe;

import com.bruberu.gregtechfoodoption.recipe.chain.CoffeeChain;
import com.bruberu.gregtechfoodoption.recipe.chain.PopcornChain;
import com.bruberu.gregtechfoodoption.recipe.chain.SmogusChain;
import net.minecraftforge.fml.common.Loader;


public class GTFORecipeAddition {
    public static void init()
    {
        PopcornChain.init();

    }

    public static void compatInit()
    {
        if(Loader.isModLoaded("nuclearcraft"))
            SmogusChain.init();
        if(Loader.isModLoaded("actuallyadditions"))
            CoffeeChain.init();
    }
}
