package com.bruberu.gregtechfoodoption.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import java.util.Iterator;
import java.util.Map;

//A not small amount of code from here was yoinked from other places. I'll give credit wherever I can!

public class RecipeUtils {
    //Thanks to Draco18s for this code! :)
    public static void removeSmelting(ItemStack resultStack) {
        ItemStack recipeResult = null;
        Map<ItemStack,ItemStack> recipes = FurnaceRecipes.instance().getSmeltingList();
        Iterator<ItemStack> iterator = recipes.keySet().iterator();
        while(iterator.hasNext()) {
            ItemStack tmpRecipe = iterator.next();
            recipeResult = recipes.get(tmpRecipe);
            if (ItemStack.areItemStacksEqual(resultStack, recipeResult)) {
                iterator.remove();
            }
        }
    }
}
