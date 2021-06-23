package com.bruberu.gregtechfoodoption.recipe;

import gregtech.api.unification.OreDictUnifier;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GTFOOreDictRegistration {
    public static void init() {
        OreDictUnifier.registerOre(new ItemStack(Items.COOKED_BEEF), "cookedMeat");
        OreDictUnifier.registerOre(new ItemStack(Items.COOKED_CHICKEN), "cookedMeat");
        OreDictUnifier.registerOre(new ItemStack(Items.COOKED_MUTTON), "cookedMeat");
        OreDictUnifier.registerOre(new ItemStack(Items.COOKED_PORKCHOP), "cookedMeat");
        OreDictUnifier.registerOre(new ItemStack(Items.COOKED_RABBIT), "cookedMeat");


    }
}
