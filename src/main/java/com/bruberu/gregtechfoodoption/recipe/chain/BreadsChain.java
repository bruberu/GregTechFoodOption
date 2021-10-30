package com.bruberu.gregtechfoodoption.recipe.chain;

import com.bruberu.gregtechfoodoption.GTFOConfig;
import com.bruberu.gregtechfoodoption.integration.applecore.GTFOAppleCoreCompat;
import com.bruberu.gregtechfoodoption.recipe.builder.BakingOvenRecipeBuilder;
import gregicadditions.GAMaterials;
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

import static com.bruberu.gregtechfoodoption.GTFOMaterialHandler.TomatoSauce;
import static com.bruberu.gregtechfoodoption.item.GTFOMetaItem.*;
import static com.bruberu.gregtechfoodoption.recipe.GTFORecipeMaps.CUISINE_ASSEMBLER_RECIPES;
import static com.bruberu.gregtechfoodoption.recipe.GTFORecipeMaps.SLICER_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class BreadsChain {
    public static void init() {
        if (GTFOConfig.gtfoChainsConfig.deleteBreadRecipe) {
            ModHandler.removeRecipes(Items.BREAD);
            GTFOAppleCoreCompat.addToSparedItems(Items.BREAD, (int) ((float) GTFOConfig.gtfoFoodConfig.baguetteHunger * 3) / 2, GTFOConfig.gtfoFoodConfig.baguetteSaturation);
        }
        ModHandler.addShapedRecipe("wooden_form_bread", WOODEN_FORM_BREAD.getStackForm(),
                " k ", " M ", "   ",
                'M', MetaItems.WOODEN_FORM_EMPTY.getStackForm());
        ModHandler.addShapedRecipe("wooden_form_baguette", WOODEN_FORM_BAGUETTE.getStackForm(),
                "  k", " M ", "   ",
                'M', MetaItems.WOODEN_FORM_EMPTY.getStackForm());
        ModHandler.addShapedRecipe("wooden_form_bun", WOODEN_FORM_BUN.getStackForm(),
                "   ", "kM ", "   ",
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
                "D D", " M ",
                'D', DOUGH,
                'M', WOODEN_FORM_BREAD);
        FORMING_PRESS_RECIPES.recipeBuilder().EUt(20).duration(100)
                .inputs(DOUGH.getStackForm(3))
                .notConsumable(WOODEN_FORM_BREAD.getStackForm())
                .outputs(UNCOOKED_BREAD.getStackForm())
                .buildAndRegister();

        ModHandler.addShapedRecipe("baguette_dough", UNCOOKED_BAGUETTE.getStackForm(),
                "DDD", " M ",
                'D', DOUGH,
                'M', WOODEN_FORM_BAGUETTE);
        FORMING_PRESS_RECIPES.recipeBuilder().EUt(20).duration(100)
                .inputs(DOUGH.getStackForm(2))
                .notConsumable(WOODEN_FORM_BAGUETTE.getStackForm())
                .outputs(UNCOOKED_BAGUETTE.getStackForm())
                .buildAndRegister();

        ModHandler.addShapedRecipe("bun_dough", UNCOOKED_BUN.getStackForm(),
                " D ", " M ",
                'D', DOUGH,
                'M', WOODEN_FORM_BUN);
        FORMING_PRESS_RECIPES.recipeBuilder().EUt(20).duration(100)
                .inputs(DOUGH.getStackForm(2))
                .notConsumable(WOODEN_FORM_BUN.getStackForm())
                .outputs(UNCOOKED_BUN.getStackForm())
                .buildAndRegister();

        BakingOvenRecipeBuilder.start().fuelAmount(400).duration(150).temperature(490)
                .input(UNCOOKED_BUN.getStackForm())
                .output(BUN.getStackForm())
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

        ModHandler.addShapedRecipe("gtfo_flat_dough", FLAT_DOUGH.getStackForm(),
                "RD ",
                'R', OreDictUnifier.get(String.valueOf(ToolDictNames.craftingToolRollingPin)),
                'D', DOUGH.getStackForm());
        FORGE_HAMMER_RECIPES.recipeBuilder().EUt(60).duration(40)
                .inputs(DOUGH.getStackForm())
                .outputs(FLAT_DOUGH.getStackForm())
                .buildAndRegister();

        ModHandler.addShapelessRecipe("gtfo_slice_bread", PRESLICED_BREAD.getStackForm(), 'k', Items.BREAD);
        SLICER_RECIPES.recipeBuilder().EUt(18).duration(30)
                .inputs(new ItemStack(Items.BREAD))
                .notConsumable(SLICER_BLADE_FLAT.getStackForm())
                .outputs(PRESLICED_BREAD.getStackForm())
                .buildAndRegister();
        ModHandler.addShapelessRecipe("gtfo_slice_baguette", PRESLICED_BAGUETTE.getStackForm(), 'k', BAGUETTE);
        SLICER_RECIPES.recipeBuilder().EUt(18).duration(30)
                .inputs(BAGUETTE.getStackForm())
                .notConsumable(SLICER_BLADE_FLAT.getStackForm())
                .outputs(PRESLICED_BAGUETTE.getStackForm())
                .buildAndRegister();
        ModHandler.addShapelessRecipe("gtfo_slice_bun", PRESLICED_BUN.getStackForm(), 'k', BUN);
        SLICER_RECIPES.recipeBuilder().EUt(18).duration(30)
                .inputs(BUN.getStackForm())
                .notConsumable(SLICER_BLADE_FLAT.getStackForm())
                .outputs(PRESLICED_BUN.getStackForm())
                .buildAndRegister();

        // Get ready for a *lot* of sandwiches!

        ModHandler.addShapelessRecipe("gtfo_burger_veggie", BURGER_VEGGIE.getStackForm(), PRESLICED_BUN.getStackForm(), CUCUMBER_SLICE.getStackForm(), CUCUMBER_SLICE.getStackForm(), TOMATO_SLICE.getStackForm(), TOMATO_SLICE.getStackForm(), ONION_SLICE.getStackForm(), ONION_SLICE.getStackForm());
        ModHandler.addShapelessRecipe("gtfo_burger_cheese", BURGER_CHEESE.getStackForm(), PRESLICED_BUN.getStackForm(), CHEDDAR_SLICE.getStackForm(), CHEDDAR_SLICE.getStackForm(), CHEDDAR_SLICE.getStackForm(), CHEDDAR_SLICE.getStackForm());
        ModHandler.addShapelessRecipe("gtfo_burger_meat", BURGER_MEAT.getStackForm(), PRESLICED_BUN.getStackForm(), "cookedMeat");
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(24).duration(50)
                .inputs(PRESLICED_BUN.getStackForm(), ONION_SLICE.getStackForm(), CUCUMBER_SLICE.getStackForm(), TOMATO_SLICE.getStackForm())
                .outputs(BURGER_VEGGIE.getStackForm())
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(24).duration(50)
                .inputs(PRESLICED_BUN.getStackForm(), CHEDDAR_SLICE.getStackForm(3))
                .outputs(BURGER_CHEESE.getStackForm())
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(24).duration(50)
                .inputs(PRESLICED_BUN.getStackForm(2))
                .input("cookedMeat", 1)
                .outputs(BURGER_MEAT.getStackForm(2))
                .buildAndRegister();

        ModHandler.addShapelessRecipe("gtfo_sandwich_veggie", SANDWICH_VEGGIE.getStackForm(), PRESLICED_BREAD, CUCUMBER_SLICE, CUCUMBER_SLICE, TOMATO_SLICE, TOMATO_SLICE, ONION_SLICE, ONION_SLICE);
        ModHandler.addShapelessRecipe("gtfo_sandwich_cheese", SANDWICH_CHEESE.getStackForm(), PRESLICED_BREAD, CHEDDAR_SLICE, CHEDDAR_SLICE, CHEDDAR_SLICE, CHEDDAR_SLICE);
        ModHandler.addShapelessRecipe("gtfo_sandwich_bacon", SANDWICH_BACON.getStackForm(), PRESLICED_BREAD, BACON, BACON, BACON, BACON, BACON, BACON, BACON, BACON);
        ModHandler.addShapelessRecipe("gtfo_sandwich_steak", SANDWICH_STEAK.getStackForm(), PRESLICED_BREAD, Items.COOKED_BEEF, CHEDDAR_SLICE, CHEDDAR_SLICE);
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(120)
                .inputs(PRESLICED_BREAD.getStackForm(), TOMATO_SLICE.getStackForm(), CUCUMBER_SLICE.getStackForm(), ONION_SLICE.getStackForm())
                .outputs(SANDWICH_VEGGIE.getStackForm())
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(120)
                .inputs(PRESLICED_BREAD.getStackForm(), CHEDDAR_SLICE.getStackForm(2))
                .notConsumable(MetaItems.INTEGRATED_CIRCUIT)
                .outputs(SANDWICH_CHEESE.getStackForm())
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(120)
                .inputs(PRESLICED_BREAD.getStackForm(), BACON.getStackForm(4))
                .outputs(SANDWICH_BACON.getStackForm())
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(120)
                .inputs(PRESLICED_BREAD.getStackForm(), CHEDDAR_SLICE.getStackForm())
                .input(Items.COOKED_BEEF)
                .outputs(SANDWICH_STEAK.getStackForm())
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(75).duration(180)
                .inputs(PRESLICED_BAGUETTE.getStackForm(), TOMATO_SLICE.getStackForm(3), CUCUMBER_SLICE.getStackForm(3), ONION_SLICE.getStackForm(3))
                .outputs(SANDWICH_LARGE_VEGGIE.getStackForm())
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(75).duration(180)
                .inputs(PRESLICED_BAGUETTE.getStackForm(), CHEDDAR_SLICE.getStackForm(5))
                .notConsumable(MetaItems.INTEGRATED_CIRCUIT)
                .outputs(SANDWICH_LARGE_CHEESE.getStackForm())
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(75).duration(180)
                .inputs(PRESLICED_BAGUETTE.getStackForm(), BACON.getStackForm(10))
                .outputs(SANDWICH_LARGE_BACON.getStackForm())
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(75).duration(180)
                .inputs(PRESLICED_BAGUETTE.getStackForm(), CHEDDAR_SLICE.getStackForm(3))
                .input(Items.COOKED_BEEF, 2)
                .outputs(SANDWICH_LARGE_STEAK.getStackForm())
                .buildAndRegister();

        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(180).duration(400)
                .inputs(FLAT_DOUGH.getStackForm(), MOZZARELLA_SLICE.getStackForm(3), MUSHROOM_SLICE.getStackForm(8), OLIVE_SLICE.getStackForm(8))
                .fluidInputs(TomatoSauce.getFluid(300))
                .outputs(PIZZA_VEGGIE_RAW.getStackForm())
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(180).duration(400)
                .inputs(FLAT_DOUGH.getStackForm(), MOZZARELLA_SLICE.getStackForm(8))
                .notConsumable(MetaItems.INTEGRATED_CIRCUIT)
                .fluidInputs(TomatoSauce.getFluid(600))
                .outputs(PIZZA_CHEESE_RAW.getStackForm())
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(180).duration(400)
                .inputs(FLAT_DOUGH.getStackForm(), MOZZARELLA_SLICE.getStackForm(4))
                .input(dust, GAMaterials.Meat, 10)
                .fluidInputs(TomatoSauce.getFluid(450))
                .outputs(PIZZA_MINCE_MEAT_RAW.getStackForm())
                .buildAndRegister();
        BakingOvenRecipeBuilder.start().temperature(495).fuelAmount(3200).duration(1200)
                .input(PIZZA_VEGGIE_RAW.getStackForm())
                .output(PIZZA_VEGGIE.getStackForm())
                .buildAndRegister();
        BakingOvenRecipeBuilder.start().temperature(495).fuelAmount(4000).duration(1400)
                .input(PIZZA_CHEESE_RAW.getStackForm())
                .output(PIZZA_CHEESE.getStackForm())
                .buildAndRegister();
        BakingOvenRecipeBuilder.start().temperature(495).fuelAmount(4800).duration(1600)
                .input(PIZZA_MINCE_MEAT_RAW.getStackForm())
                .output(PIZZA_MINCE_MEAT.getStackForm())
                .buildAndRegister();
    }
}
