package com.bruberu.gregtechfoodoption.recipe.chain;

import com.bruberu.gregtechfoodoption.GTFOConfig;
import com.bruberu.gregtechfoodoption.integration.applecore.GTFOAppleCoreCompat;
import com.bruberu.gregtechfoodoption.item.GTFOMetaItem;
import com.bruberu.gregtechfoodoption.recipe.GTFORecipeMaps;
import com.bruberu.gregtechfoodoption.recipe.builder.BakingOvenRecipeBuilder;
import gregtech.api.items.ToolDictNames;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.HashMap;

import static com.bruberu.gregtechfoodoption.item.GTFOMetaItem.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class BreadsChain {
    public static void init() {
        if (GTFOConfig.gtfoChainsConfig.deleteBreadRecipe) {
            ModHandler.removeRecipes(Items.BREAD);
            GTFOAppleCoreCompat.addToSparedItems(Items.BREAD, (int)((float) GTFOConfig.gtfoFoodConfig.baguetteHunger * 3)/2, GTFOConfig.gtfoFoodConfig.baguetteSaturation);
        }
        ModHandler.addShapedRecipe("wooden_form_bread", WOODEN_FORM_BREAD.getStackForm(),
                " k ", " M ", "   ",
                'M', MetaItems.WOODEN_FORM_EMPTY.getStackForm());
        ModHandler.addShapedRecipe("wooden_form_baguette", WOODEN_FORM_BAGUETTE.getStackForm(),
                "  k", " M ", "   ",
                'M', MetaItems.WOODEN_FORM_EMPTY.getStackForm());

        ModHandler.addShapedRecipe("dough_2", DOUGH.getStackForm(2),
                "FFF", "FBS", "   ",
                'F', OreDictUnifier.get(dust, Materials.Wheat),
                'S', OreDictUnifier.get(OrePrefix.dustTiny, Materials.Salt),
                'B', Items.WATER_BUCKET);
        MIXER_RECIPES.recipeBuilder().EUt(8).duration(150)
                .input(dust, Materials.Wheat, 4)
                .input(OrePrefix.dustTiny, Materials.Salt)
                .fluidInputs(Water.getFluid(1000))
                .outputs(DOUGH.getStackForm(2))
                .notConsumable(new IntCircuitIngredient(0))
                .buildAndRegister();

        ModHandler.addShapedRecipe("dough_4", DOUGH.getStackForm(4),
                "FFF", "FBS", "O  ",
                'F', OreDictUnifier.get(dust, Materials.Wheat),
                'S', OreDictUnifier.get(OrePrefix.dustTiny, Materials.Salt),
                'B', Items.WATER_BUCKET,
                'O', OreDictUnifier.get(OrePrefix.dustTiny, Materials.SodaAsh));
        MIXER_RECIPES.recipeBuilder().EUt(8).duration(150)
                .input(dust, Materials.Wheat, 4)
                .input(OrePrefix.dustTiny, Materials.Salt)
                .input(OrePrefix.dustTiny, Materials.SodaAsh)
                .fluidInputs(Water.getFluid(1000))
                .outputs(DOUGH.getStackForm(4))
                .notConsumable(new IntCircuitIngredient(1))
                .buildAndRegister();

        ModHandler.addShapedRecipe("bread_dough", UNCOOKED_BREAD.getStackForm(),
                "DDD", " M ",
                'D', DOUGH,
                'M', WOODEN_FORM_BREAD);
        FORMING_PRESS_RECIPES.recipeBuilder().EUt(20).duration(100)
                .inputs(DOUGH.getStackForm(3))
                .notConsumable(WOODEN_FORM_BREAD.getStackForm())
                .outputs(UNCOOKED_BREAD.getStackForm())
                .buildAndRegister();

        ModHandler.addShapedRecipe("baguette_dough", UNCOOKED_BAGUETTE.getStackForm(),
                "D D", " M ",
                'D', DOUGH,
                'M', WOODEN_FORM_BAGUETTE);
        FORMING_PRESS_RECIPES.recipeBuilder().EUt(20).duration(100)
                .inputs(DOUGH.getStackForm(2))
                .notConsumable(WOODEN_FORM_BAGUETTE.getStackForm())
                .outputs(UNCOOKED_BAGUETTE.getStackForm())
                .buildAndRegister();

        BakingOvenRecipeBuilder.start().fuelAmount(400).duration(150).temperature(490)
                .input(UNCOOKED_BAGUETTE.getStackForm())
                .output(BAGUETTE.getStackForm())
                .buildAndRegister();

        BakingOvenRecipeBuilder.start().fuelAmount(400).duration(300).temperature(445)
                .input(UNCOOKED_BREAD.getStackForm())
                .output(new ItemStack(Items.BREAD))
                .buildAndRegister();

        ModHandler.removeRecipes(Items.CAKE);
        ModHandler.addShapelessRecipe("sugary_dough", SUGARY_DOUGH.getStackForm(2), OreDictUnifier.get(dust, Sugar), DOUGH.getStackForm());
        MIXER_RECIPES.recipeBuilder().EUt(7).duration(32)
                .input(dust, Sugar)
                .inputs(DOUGH.getStackForm())
                .outputs(SUGARY_DOUGH.getStackForm(2))
                .buildAndRegister();
        ModHandler.addShapedRecipe("cake_bottom", CAKE_BOTTOM.getStackForm(),
                "D D", "DMD",
                'D', SUGARY_DOUGH.getStackForm(),
                'M', MetaItems.SHAPE_MOLD_CYLINDER.getStackForm());
        FORMING_PRESS_RECIPES.recipeBuilder().EUt(30).duration(100)
                .inputs(SUGARY_DOUGH.getStackForm(4))
                .inputs(MetaItems.SHAPE_MOLD_CYLINDER.getStackForm())
                .outputs(CAKE_BOTTOM.getStackForm())
                .buildAndRegister();
        BakingOvenRecipeBuilder.start().fuelAmount(800).duration(500).temperature(445)
                .input(CAKE_BOTTOM.getStackForm())
                .output(BAKED_CAKE_BOTTOM.getStackForm())
                .buildAndRegister();
        ModHandler.addShapedRecipe("gtfo_cake", new ItemStack(Items.CAKE),
                "SES", "EBE", "MMM",
                'S', OreDictUnifier.get(dust, Sugar),
                'E', new ItemStack(Items.EGG),
                'B', BAKED_CAKE_BOTTOM.getStackForm(),
                'M', new ItemStack(Items.MILK_BUCKET));
        ASSEMBLER_RECIPES.recipeBuilder().EUt(7).duration(100)
                .input(dust, Sugar)
                .input(Items.EGG)
                .inputs(BAKED_CAKE_BOTTOM.getStackForm())
                .fluidInputs(Milk.getFluid(3000))
                .outputs(new ItemStack(Items.CAKE))
                .buildAndRegister();

        GTFOAppleCoreCompat.addToSparedItems(Items.COOKIE, 3, 0.2f);
        ModHandler.removeRecipes(Items.COOKIE);
        ModHandler.addShapelessRecipe("gtfo_cookie", new ItemStack(Items.COOKIE), SUGARY_DOUGH, SUGARY_DOUGH, OreDictUnifier.get(dust, Cocoa));

        ModHandler.removeRecipes(Items.PUMPKIN_PIE);
        GTFOAppleCoreCompat.addToSparedItems(Items.PUMPKIN_PIE);
        ModHandler.addShapedRecipe("gtfo_pie_crust", PIE_CRUST.getStackForm(),
                "RD",
                'R', OreDictUnifier.get(String.valueOf(ToolDictNames.craftingToolRollingPin)),
                'D', SUGARY_DOUGH.getStackForm());
        ModHandler.addShapedRecipe("gtfo_pumpkin_pie", new ItemStack(Items.PUMPKIN_PIE),
                "SSS",
                "PP ",
                "C  ",
                'S', new ItemStack(Items.SUGAR),
                'P', new ItemStack(Blocks.PUMPKIN),
                'C', PIE_CRUST.getStackForm());
    }
}
