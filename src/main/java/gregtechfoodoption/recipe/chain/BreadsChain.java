package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;
import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.GTFOMaterialHandler;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.integration.applecore.GTFOAppleCoreCompat;
import gregtechfoodoption.utils.GTFOUtils;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtechfoodoption.GTFOMaterialHandler.TomatoSauce;
import static gregtechfoodoption.item.GTFOMetaItem.*;
import static gregtechfoodoption.recipe.GTFORecipeMaps.*;

public class BreadsChain {
    public static void init() {
        if (GTFOConfig.gtfoChainsConfig.deleteBreadRecipe) {
            ModHandler.removeRecipes(new ItemStack(Items.BREAD));
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
                'F', new UnificationEntry(dust, Materials.Wheat),
                'S', new UnificationEntry(OrePrefix.dustTiny, Materials.Salt),
                'B', Items.WATER_BUCKET);
        MIXER_RECIPES.recipeBuilder().EUt(8).duration(150)
                .input(dust, Materials.Wheat, 4)
                .input(OrePrefix.dustTiny, Materials.Salt)
                .fluidInputs(Water.getFluid(1000))
                .outputs(DOUGH.getStackForm(2))
                .notConsumable(IntCircuitIngredient.getIntegratedCircuit(2))
                .buildAndRegister();

        ModHandler.addShapedRecipe("dough_4", DOUGH.getStackForm(4),
                "FFF", "FBS", "O  ",
                'F', new UnificationEntry(dust, Materials.Wheat),
                'S', new UnificationEntry(OrePrefix.dustTiny, Materials.Salt),
                'B', Items.WATER_BUCKET,
                'O', new UnificationEntry(OrePrefix.dustTiny, Materials.SodaAsh));
        MIXER_RECIPES.recipeBuilder().EUt(8).duration(150)
                .input(dust, Materials.Wheat, 4)
                .input(OrePrefix.dustTiny, Materials.Salt)
                .input(OrePrefix.dustTiny, Materials.SodaAsh)
                .fluidInputs(Water.getFluid(1000))
                .outputs(DOUGH.getStackForm(4))
                .notConsumable(IntCircuitIngredient.getIntegratedCircuit(1))
                .buildAndRegister();

        ModHandler.addShapedRecipe("bread_dough", UNCOOKED_BREAD.getStackForm(),
                "D D", " M ",
                'D', DOUGH,
                'M', WOODEN_FORM_BREAD);
        FORMING_PRESS_RECIPES.recipeBuilder().EUt(20).duration(100)
                .inputs(DOUGH.getStackForm(3))
                .notConsumable(WOODEN_FORM_BREAD.getStackForm())
                .outputs(UNCOOKED_BREAD.getStackForm(3))
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
                .outputs(UNCOOKED_BUN.getStackForm(3))
                .buildAndRegister();

        GTFOUtils.addBakingOvenRecipes(
                UNCOOKED_BUN.getStackForm(),
                BUN.getStackForm(),
                150,
                490,
                2
        );

        GTFOUtils.addBakingOvenRecipes(
                UNCOOKED_BAGUETTE.getStackForm(),
                BAGUETTE.getStackForm(),
                150,
                490,
                2
        );

        GTFOUtils.addBakingOvenRecipes(
                UNCOOKED_BREAD.getStackForm(),
                new ItemStack(Items.BREAD),
                150,
                490,
                2
        );

        ModHandler.removeRecipes(new ItemStack(Items.CAKE));
        ModHandler.addShapelessRecipe("sugary_dough", SUGARY_DOUGH.getStackForm(2), new UnificationEntry(dust, Sugar), DOUGH.getStackForm());
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

        GTFOUtils.addBakingOvenRecipes(
                CAKE_BOTTOM.getStackForm(),
                BAKED_CAKE_BOTTOM.getStackForm(),
                500,
                445,
                3
        );

        ModHandler.addShapedRecipe("gtfo_cake", new ItemStack(Items.CAKE),
                "SES", "EBE", "MMM",
                'S', new UnificationEntry(dust, Sugar),
                'E', new ItemStack(Items.EGG),
                'B', BAKED_CAKE_BOTTOM.getStackForm(),
                'M', new ItemStack(Items.MILK_BUCKET));
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(7).duration(100)
                .input(dust, Sugar)
                .input(Items.EGG)
                .inputs(BAKED_CAKE_BOTTOM.getStackForm())
                .fluidInputs(Milk.getFluid(3000))
                .outputs(new ItemStack(Items.CAKE))
                .buildAndRegister();

        GTFOAppleCoreCompat.addToSparedItems(Items.COOKIE, 3, 0.2f);
        ModHandler.removeRecipes(new ItemStack(Items.COOKIE));
        ModHandler.removeRecipes(new ItemStack(Items.COOKIE, 8));
        ModHandler.addShapelessRecipe("gtfo_cookie", new ItemStack(Items.COOKIE, 4), SUGARY_DOUGH, SUGARY_DOUGH, new UnificationEntry(dust, Cocoa));

        ModHandler.removeRecipes(new ItemStack(Items.PUMPKIN_PIE));
        GTFOAppleCoreCompat.addToSparedItems(Items.PUMPKIN_PIE);
        ModHandler.addShapedRecipe("gtfo_pie_crust", PIE_CRUST.getStackForm(),
                "RD",
                'R', OreDictUnifier.get(GTFOValues.craftingToolRollingPin),
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
                'R', OreDictUnifier.get(GTFOValues.craftingToolRollingPin),
                'D', DOUGH.getStackForm());
        FORGE_HAMMER_RECIPES.recipeBuilder().EUt(60).duration(40)
                .inputs(DOUGH.getStackForm())
                .outputs(FLAT_DOUGH.getStackForm())
                .buildAndRegister();

        ModHandler.addShapedRecipe("gtfo_slice_bread",  PRESLICED_BREAD.getStackForm(), "Bk", 'B', Items.BREAD);
        CUTTER_RECIPES.recipeBuilder().EUt(18).duration(30)
                .inputs(new ItemStack(Items.BREAD))
                .outputs(PRESLICED_BREAD.getStackForm())
                .buildAndRegister();
        ModHandler.addShapelessRecipe("gtfo_slice_baguette", PRESLICED_BAGUETTE.getStackForm(), 'k', BAGUETTE);
        CUTTER_RECIPES.recipeBuilder().EUt(18).duration(30)
                .inputs(BAGUETTE.getStackForm())
                .outputs(PRESLICED_BAGUETTE.getStackForm())
                .buildAndRegister();
        ModHandler.addShapelessRecipe("gtfo_slice_bun", PRESLICED_BUN.getStackForm(), 'k', BUN);
        CUTTER_RECIPES.recipeBuilder().EUt(18).duration(30)
                .inputs(BUN.getStackForm())
                .outputs(PRESLICED_BUN.getStackForm())
                .buildAndRegister();

        ModHandler.addShapedRecipe("gtfo_vertical_slice_bread",  TOAST.getStackForm(4), "B", "k", 'B', Items.BREAD);
        SLICER_RECIPES.recipeBuilder().EUt(18).duration(30)
                .input(Items.BREAD)
                .notConsumable(SLICER_BLADE_FLAT.getStackForm())
                .outputs(BREAD_SLICE.getStackForm(8))
                .buildAndRegister();
        GTFOUtils.addBakingOvenRecipes(
                BREAD_SLICE.getStackForm(),
                TOAST.getStackForm(),
                50,
                445,
                2
        );

        // Get ready for a *lot* of sandwiches!

        ModHandler.addShapelessRecipe("gtfo_burger_veggie", BURGER_VEGGIE.getStackForm(), PRESLICED_BUN.getStackForm(), CUCUMBER_SLICE.getStackForm(), CUCUMBER_SLICE.getStackForm(), TOMATO_SLICE.getStackForm(), TOMATO_SLICE.getStackForm(), ONION_SLICE.getStackForm(), ONION_SLICE.getStackForm());
        ModHandler.addShapelessRecipe("gtfo_burger_cheese", BURGER_CHEESE.getStackForm(), PRESLICED_BUN.getStackForm(), CHEDDAR_SLICE.getStackForm(), CHEDDAR_SLICE.getStackForm(), CHEDDAR_SLICE.getStackForm(), CHEDDAR_SLICE.getStackForm());
        ModHandler.addShapelessRecipe("gtfo_burger_meat", BURGER_MEAT.getStackForm(), PRESLICED_BUN.getStackForm(), GTFOMaterialHandler.MeatIngot.getItemStack());
        ModHandler.addShapelessRecipe("gtfo_burger_chum", BURGER_CHUM.getStackForm(), PRESLICED_BUN.getStackForm(), CHUM.getStackForm(), CHUM.getStackForm());
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
                .inputs(GTFOMaterialHandler.MeatIngot.getItemStack())
                .outputs(BURGER_MEAT.getStackForm(2))
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(24).duration(50)
                .inputs(PRESLICED_BUN.getStackForm(1))
                .inputs(CHUM.getStackForm(2))
                .outputs(BURGER_CHUM.getStackForm(1))
                .buildAndRegister();


        ModHandler.addShapelessRecipe("gtfo_sandwich_veggie", SANDWICH_VEGGIE.getStackForm(), PRESLICED_BREAD, CUCUMBER_SLICE, CUCUMBER_SLICE, TOMATO_SLICE, TOMATO_SLICE, ONION_SLICE, ONION_SLICE);
        ModHandler.addShapelessRecipe("gtfo_sandwich_cheese", SANDWICH_CHEESE.getStackForm(), PRESLICED_BREAD, CHEDDAR_SLICE, CHEDDAR_SLICE, CHEDDAR_SLICE, CHEDDAR_SLICE);
        ModHandler.addShapelessRecipe("gtfo_sandwich_bacon", SANDWICH_BACON.getStackForm(), PRESLICED_BREAD, BACON, BACON, BACON, BACON, BACON, BACON, BACON, BACON);
        ModHandler.addShapelessRecipe("gtfo_sandwich_steak_from_meat", SANDWICH_STEAK.getStackForm(), PRESLICED_BREAD, GTFOMaterialHandler.MeatIngot.getItemStack(), CHEDDAR_SLICE, CHEDDAR_SLICE);
        ModHandler.addShapelessRecipe("gtfo_sandwich_steak_from_oredict", SANDWICH_STEAK.getStackForm(), PRESLICED_BREAD, OreDictUnifier.get("cookedMeat"), CHEDDAR_SLICE, CHEDDAR_SLICE);
        ModHandler.addShapelessRecipe("gtfo_sandwich_toast", SANDWICH_TOAST.getStackForm(), TOAST, BREAD_SLICE, BREAD_SLICE);
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(24).duration(120)
                .inputs(PRESLICED_BREAD.getStackForm(), TOMATO_SLICE.getStackForm(), CUCUMBER_SLICE.getStackForm(), ONION_SLICE.getStackForm())
                .outputs(SANDWICH_VEGGIE.getStackForm())
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(24).duration(120)
                .inputs(PRESLICED_BREAD.getStackForm(), CHEDDAR_SLICE.getStackForm(2))
                .notConsumable(IntCircuitIngredient.getIntegratedCircuit(1))
                .outputs(SANDWICH_CHEESE.getStackForm())
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(24).duration(120)
                .inputs(PRESLICED_BREAD.getStackForm(), BACON.getStackForm(4))
                .outputs(SANDWICH_BACON.getStackForm())
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(24).duration(120)
                .inputs(PRESLICED_BREAD.getStackForm(3), CHEDDAR_SLICE.getStackForm(3))
                .inputs(GTFOMaterialHandler.MeatIngot.getItemStack())
                .outputs(SANDWICH_STEAK.getStackForm(3))
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(24).duration(120)
                .inputs(PRESLICED_BREAD.getStackForm(3), CHEDDAR_SLICE.getStackForm(3))
                .input("cookedMeat", 1)
                .outputs(SANDWICH_STEAK.getStackForm(3))
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(24).duration(120)
                .inputs(PRESLICED_BREAD.getStackForm(3), CHEDDAR_SLICE.getStackForm(3))
                .input("cookedMeat", 1)
                .outputs(SANDWICH_STEAK.getStackForm(3))
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(24).duration(120)
                .inputs(PRESLICED_BREAD.getStackForm(2), TOAST.getStackForm(1))
                .outputs(SANDWICH_TOAST.getStackForm())
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(75).duration(180)
                .inputs(PRESLICED_BAGUETTE.getStackForm(), TOMATO_SLICE.getStackForm(3), CUCUMBER_SLICE.getStackForm(3), ONION_SLICE.getStackForm(3))
                .outputs(SANDWICH_LARGE_VEGGIE.getStackForm())
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(75).duration(180)
                .inputs(PRESLICED_BAGUETTE.getStackForm(), CHEDDAR_SLICE.getStackForm(5))
                .notConsumable(IntCircuitIngredient.getIntegratedCircuit(1))
                .outputs(SANDWICH_LARGE_CHEESE.getStackForm())
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(75).duration(180)
                .inputs(PRESLICED_BAGUETTE.getStackForm(2), BACON.getStackForm(10))
                .outputs(SANDWICH_LARGE_BACON.getStackForm(2))
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(75).duration(180)
                .inputs(PRESLICED_BAGUETTE.getStackForm(3), CHEDDAR_SLICE.getStackForm(3))
                .inputs(GTFOMaterialHandler.MeatIngot.getItemStack(3))
                .outputs(SANDWICH_LARGE_STEAK.getStackForm(3))
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(75).duration(180)
                .inputs(PRESLICED_BAGUETTE.getStackForm(3), CHEDDAR_SLICE.getStackForm(3))
                .input("cookedMeat", 3)
                .outputs(SANDWICH_LARGE_STEAK.getStackForm(3))
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
                .input(dust, Meat, 10)
                .fluidInputs(TomatoSauce.getFluid(450))
                .outputs(PIZZA_MINCE_MEAT_RAW.getStackForm())
                .buildAndRegister();
        GTFOUtils.addBakingOvenRecipes(
                PIZZA_VEGGIE_RAW.getStackForm(),
                PIZZA_VEGGIE.getStackForm(),
                1200,
                645,
                10
        );
        GTFOUtils.addBakingOvenRecipes(
                PIZZA_CHEESE_RAW.getStackForm(),
                PIZZA_CHEESE.getStackForm(),
                1400,
                645,
                12
        );
        GTFOUtils.addBakingOvenRecipes(
                PIZZA_MINCE_MEAT_RAW.getStackForm(),
                PIZZA_MINCE_MEAT.getStackForm(),
                1600,
                645,
                16
        );
    }
}
