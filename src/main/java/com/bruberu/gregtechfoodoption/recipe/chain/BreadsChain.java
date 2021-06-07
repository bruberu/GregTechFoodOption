package com.bruberu.gregtechfoodoption.recipe.chain;

import com.bruberu.gregtechfoodoption.GTFOConfig;
import com.bruberu.gregtechfoodoption.item.GTFOMetaItem;
import com.bruberu.gregtechfoodoption.recipe.GTFORecipeMaps;
import com.bruberu.gregtechfoodoption.recipe.builder.BakingOvenRecipeBuilder;
import com.bruberu.gregtechfoodoption.recipe.builder.ElectricBakingOvenRecipeBuilder;
import com.bruberu.gregtechfoodoption.recipe.multiblock.ElectricBakingOvenRecipeMap;
import gregtech.api.items.ToolDictNames;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import javafx.util.Pair;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

import static gregtech.api.recipes.RecipeMaps.FORMING_PRESS_RECIPES;
import static gregtech.api.recipes.RecipeMaps.MIXER_RECIPES;
import static gregtech.api.unification.material.Materials.Water;

public class BreadsChain {
    public static void init() {
        if (GTFOConfig.gtfoChainsConfig.deleteBreadRecipe)
            ModHandler.removeRecipes(Items.BREAD);

        ModHandler.addShapedRecipe("wooden_form_bread", GTFOMetaItem.WOODEN_FORM_BREAD.getStackForm(),
                " k ", " M ", "   ",
                'M', MetaItems.WOODEN_FORM_EMPTY.getStackForm());
        ModHandler.addShapedRecipe("wooden_form_baguette", GTFOMetaItem.WOODEN_FORM_BAGUETTE.getStackForm(),
                "  k", " M ", "   ",
                'M', MetaItems.WOODEN_FORM_EMPTY.getStackForm());

        ModHandler.addShapedRecipe("dough_2", GTFOMetaItem.DOUGH.getStackForm(2),
                "FFF", "FBS", "   ",
                'F', OreDictUnifier.get(OrePrefix.dust, Materials.Wheat),
                'S', OreDictUnifier.get(OrePrefix.dustTiny, Materials.Salt),
                'B', Items.WATER_BUCKET);
        MIXER_RECIPES.recipeBuilder().EUt(8).duration(150)
                .input(OrePrefix.dust, Materials.Wheat, 4)
                .input(OrePrefix.dustTiny, Materials.Salt)
                .fluidInputs(Water.getFluid(1000))
                .outputs(GTFOMetaItem.DOUGH.getStackForm(2))
                .notConsumable(new IntCircuitIngredient(0))
                .buildAndRegister();

        ModHandler.addShapedRecipe("dough_4", GTFOMetaItem.DOUGH.getStackForm(4),
                "FFF", "FBS", "O  ",
                'F', OreDictUnifier.get(OrePrefix.dust, Materials.Wheat),
                'S', OreDictUnifier.get(OrePrefix.dustTiny, Materials.Salt),
                'B', Items.WATER_BUCKET,
                'O', OreDictUnifier.get(OrePrefix.dustTiny, Materials.SodaAsh));
        MIXER_RECIPES.recipeBuilder().EUt(8).duration(150)
                .input(OrePrefix.dust, Materials.Wheat, 4)
                .input(OrePrefix.dustTiny, Materials.Salt)
                .input(OrePrefix.dustTiny, Materials.SodaAsh)
                .fluidInputs(Water.getFluid(1000))
                .outputs(GTFOMetaItem.DOUGH.getStackForm(4))
                .notConsumable(new IntCircuitIngredient(1))
                .buildAndRegister();

        ModHandler.addShapedRecipe("bread_dough", GTFOMetaItem.UNCOOKED_BREAD.getStackForm(),
                " R ", "DDD", " M ",
                'R', OreDictUnifier.get(String.valueOf(ToolDictNames.craftingToolRollingPin)),
                'D', GTFOMetaItem.DOUGH,
                'M', GTFOMetaItem.WOODEN_FORM_BREAD);
        FORMING_PRESS_RECIPES.recipeBuilder().EUt(20).duration(100)
                .inputs(GTFOMetaItem.DOUGH.getStackForm(3))
                .notConsumable(GTFOMetaItem.WOODEN_FORM_BREAD.getStackForm())
                .outputs(GTFOMetaItem.UNCOOKED_BREAD.getStackForm())
                .buildAndRegister();

        ModHandler.addShapedRecipe("baguette_dough", GTFOMetaItem.UNCOOKED_BAGUETTE.getStackForm(),
                " R ", "D D", " M ",
                'R', OreDictUnifier.get(String.valueOf(ToolDictNames.craftingToolRollingPin)),
                'D', GTFOMetaItem.DOUGH,
                'M', GTFOMetaItem.WOODEN_FORM_BAGUETTE);
        FORMING_PRESS_RECIPES.recipeBuilder().EUt(20).duration(100)
                .inputs(GTFOMetaItem.DOUGH.getStackForm(2))
                .notConsumable(GTFOMetaItem.WOODEN_FORM_BAGUETTE.getStackForm())
                .outputs(GTFOMetaItem.UNCOOKED_BAGUETTE.getStackForm())
                .buildAndRegister();

        BakingOvenRecipeBuilder.start().fuelAmount(400).duration(150).temperature(490)
                .input(GTFOMetaItem.UNCOOKED_BAGUETTE.getStackForm())
                .output(GTFOMetaItem.BAGUETTE.getStackForm())
                .buildAndRegister();


        BakingOvenRecipeBuilder.start().fuelAmount(400).duration(300).temperature(445)
                .input(GTFOMetaItem.UNCOOKED_BREAD.getStackForm())
                .output(new ItemStack(Items.BREAD))
                .buildAndRegister();



        BakingOvenRecipeBuilder.start().fuelAmount(500).duration(900)
                .input(new ItemStack(Items.POTATO))
                .output(new ItemStack(Items.BAKED_POTATO))
                .buildAndRegister();

        HashMap<Integer, Integer> potatoTemps = new HashMap<>();
        potatoTemps.put(435, 900);
        potatoTemps.put(450, 600);
        potatoTemps.put(475, 400);
        potatoTemps.forEach((temp, duration) -> GTFORecipeMaps.ELECTRIC_BAKING_OVEN_RECIPES.recipeBuilder().setTemp(temp).duration(duration)
                .input(Items.POTATO)
                .output(Items.BAKED_POTATO)
                .buildAndRegister());


    }
}
