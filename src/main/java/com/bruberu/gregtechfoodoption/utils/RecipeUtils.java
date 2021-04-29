package com.bruberu.gregtechfoodoption.utils;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import java.util.Iterator;
import java.util.Map;

import static gregicadditions.GAMaterials.OrganicFertilizer;
import static gregicadditions.recipes.GARecipeMaps.GREEN_HOUSE_RECIPES;
import static gregtech.api.unification.material.Materials.Water;

//A not small amount of code from here was yoinked from other places. I'll give credit wherever I can!

public class RecipeUtils {
    public static void addGreenHouseRecipes(ItemStack seed, Item crop) {
        GREEN_HOUSE_RECIPES.recipeBuilder().duration(1000).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Water.getFluid(2000)).notConsumable(seed).outputs(new ItemStack(crop)).buildAndRegister();
        GREEN_HOUSE_RECIPES.recipeBuilder().duration(900).notConsumable(new IntCircuitIngredient(1)).inputs(new ItemStack(Items.DYE, 1, 15)).fluidInputs(Water.getFluid(2000)).notConsumable(seed).outputs(new ItemStack(crop, 2)).chancedOutput(seed, 100, 50).buildAndRegister();
        GREEN_HOUSE_RECIPES.recipeBuilder().duration(600).notConsumable(new IntCircuitIngredient(2)).input(OrePrefix.dust, OrganicFertilizer).fluidInputs(Water.getFluid(2000)).notConsumable(seed).outputs(new ItemStack(crop, 3)).chancedOutput(seed, 100, 50).buildAndRegister();
    }

}
