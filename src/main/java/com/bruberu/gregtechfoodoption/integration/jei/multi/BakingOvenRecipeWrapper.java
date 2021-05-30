package com.bruberu.gregtechfoodoption.integration.jei.multi;

import com.bruberu.gregtechfoodoption.machines.multiblock.MetaTileEntityBakingOven;
import com.bruberu.gregtechfoodoption.recipe.multiblock.BakingOvenRecipe;
import com.google.common.collect.ImmutableList;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import scala.tools.cmd.Meta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BakingOvenRecipeWrapper implements IRecipeWrapper {
    private final BakingOvenRecipe recipe;
    private final List<List<ItemStack>> matchingInputs = new ArrayList<>();
    private final List<List<ItemStack>> outputs = new ArrayList<>();



    public BakingOvenRecipeWrapper(BakingOvenRecipe recipe) {
        this.recipe = recipe;
        CountableIngredient ingredient = recipe.getInput();

        List<ItemStack> ingredientValues = Arrays.stream(ingredient.getIngredient().getMatchingStacks())
                .map(ItemStack::copy)
                .sorted(OreDictUnifier.getItemStackComparator())
                .collect(Collectors.toList());
        ingredientValues.forEach(stack -> stack.setCount(ingredient.getCount()));

        this.matchingInputs.add(ingredientValues);

        this.matchingInputs.add(MetaTileEntityBakingOven.getDisplayFuelsForRecipe(recipe.getFuelAmount()));

        this.outputs.add(ImmutableList.of(recipe.getOutput()));
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputLists(ItemStack.class, this.matchingInputs);
        ingredients.setOutputLists(ItemStack.class, this.outputs);
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        minecraft.fontRenderer.drawString(I18n.format("gregtech.recipe.duration", this.recipe.getDuration() / 20f), 0, 55, 0x111111);
    }

}
