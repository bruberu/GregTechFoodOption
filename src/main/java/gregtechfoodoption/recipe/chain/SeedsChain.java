package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.item.GTFOMetaItem;
import net.minecraft.item.ItemStack;

import static gregtech.api.unification.material.Materials.*;
import static gregtechfoodoption.GTFOMaterialHandler.*;
import static gregtechfoodoption.item.GTFOMetaItem.*;

public class SeedsChain {
    public static void init() {
        /*
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().EUt(6).duration(20)
                .inputs(MetaItems.PLANT_BALL.getStackForm())
                .chancedOutput(GTFOMaterialHandler.PopcornKernel.getItemStack(), 2000, 250)
                .buildAndRegister();*/

        ItemStack[] seeds = new ItemStack[]{
                LEMON.getStackForm(),
                LIME.getStackForm(),
                TOMATO.getStackForm(),
                CUCUMBER.getStackForm(),
                OLIVE.getStackForm(),
                ONION.getStackForm(),
                BANANA.getStackForm(),
                ORANGE.getStackForm(),
                GRAPES.getStackForm(),
                MANGO.getStackForm(),
                APRICOT.getStackForm(),
                PEA_POD.getStackForm(),
                SOYBEAN.getStackForm(),
                BEANS.getStackForm(),
                COFFEE_CHERRY.getStackForm(),
        };

        for (ItemStack seed : seeds) {
            if(GTFOConfig.gtfoMiscConfig.centrifugeSeeds) {
                RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().EUt(5).duration(144)
                        .inputs(seed)
                        .fluidOutputs(Materials.Methane.getFluid(34))
                        .buildAndRegister();
            }
            RecipeMaps.BREWING_RECIPES.recipeBuilder().EUt(3).duration(800)
                    .inputs(seed)
                    .fluidInputs(Water.getFluid(100))
                    .fluidOutputs(Biomass.getFluid(100))
                    .buildAndRegister();
            ItemStack eight = seed.copy();
            eight.setCount(8);
            RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().EUt(2).duration(300)
                    .inputs(eight)
                    .outputs(MetaItems.PLANT_BALL.getStackForm())
                    .buildAndRegister();
        }
        ModHandler.addShapedRecipe("gtfo_seed_soy_ungenerify", GTFOMetaItem.SOYBEAN_SEED.getStackForm(),
                "S  ", "   ", "   ",
                'S', GTFOMetaItem.UNKNOWN_SEED);
        ModHandler.addShapedRecipe("gtfo_seed_tomato_ungenerify", GTFOMetaItem.TOMATO_SEED.getStackForm(),
                " S ", "   ", "   ",
                'S', GTFOMetaItem.UNKNOWN_SEED);
        ModHandler.addShapedRecipe("gtfo_seed_cucumber_ungenerify", GTFOMetaItem.CUCUMBER_SEED.getStackForm(),
                "   ", "S  ", "   ",
                'S', GTFOMetaItem.UNKNOWN_SEED);
        ModHandler.addShapedRecipe("gtfo_seed_onion_ungenerify", GTFOMetaItem.ONION_SEED.getStackForm(),
                "  S", "   ", "   ",
                'S', GTFOMetaItem.UNKNOWN_SEED);
        ModHandler.addShapedRecipe("gtfo_seed_grapes_ungenerify", GTFOMetaItem.GRAPE_SEED.getStackForm(),
                "   ", " S ", "   ",
                'S', GTFOMetaItem.UNKNOWN_SEED);
        ModHandler.addShapedRecipe("gtfo_seed_coffee_ungenerify", GTFOMetaItem.COFFEE_SEED.getStackForm(),
                "   ", "   ", "S  ",
                'S', GTFOMetaItem.UNKNOWN_SEED);
        ModHandler.addShapedRecipe("gtfo_seed_pea_ungenerify", GTFOMetaItem.PEAS.getStackForm(),
                "   ", "  S", "   ",
                'S', GTFOMetaItem.UNKNOWN_SEED);
        ModHandler.addShapedRecipe("gtfo_seed_bean_ungenerify", GTFOMetaItem.BEANS.getStackForm(),
                "   ", "   ", " S ",
                'S', GTFOMetaItem.UNKNOWN_SEED);


        ModHandler.addShapelessRecipe("gtfo_seed_soy_extraction", GTFOMetaItem.SOYBEAN_SEED.getStackForm(),
                GTFOMetaItem.SOYBEAN);
        ModHandler.addShapelessRecipe("gtfo_seed_tomato_extraction", GTFOMetaItem.TOMATO_SEED.getStackForm(),
                GTFOMetaItem.TOMATO);
        ModHandler.addShapelessRecipe("gtfo_seed_cucumber_extraction", GTFOMetaItem.CUCUMBER_SEED.getStackForm(),
                GTFOMetaItem.CUCUMBER);
        ModHandler.addShapelessRecipe("gtfo_seed_grapes_extraction", GTFOMetaItem.GRAPE_SEED.getStackForm(),
                GTFOMetaItem.GRAPES);
        ModHandler.addShapelessRecipe("gtfo_seed_coffee_extraction", GTFOMetaItem.COFFEE_SEED.getStackForm(),
                GTFOMetaItem.COFFEE_CHERRY);

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder().EUt(2).duration(64)
                .input(GTFOMetaItem.SOYBEAN_SEED)
                .notConsumable(IntCircuitIngredient.getIntegratedCircuit(1))
                .fluidOutputs(RawSoybeanOil.getFluid(15))
                .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder().EUt(64).duration(64)
                .input(GTFOMetaItem.SOYBEAN_SEED)
                .notConsumable(IntCircuitIngredient.getIntegratedCircuit(2))
                .output(OrePrefix.dustSmall, Wood)
                .fluidOutputs(RawSoybeanOil.getFluid(28))
                .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(24).duration(60)
                .fluidInputs(RawSoybeanOil.getFluid(1000), Water.getFluid(500))
                .fluidOutputs(HydratedSoybeanOil.getFluid(1000))
                .buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().EUt(16).duration(120)
                .fluidInputs(HydratedSoybeanOil.getFluid(1000))
                .fluidOutputs(SoyLecithin.getFluid(50), SoybeanOil.getFluid(1000))
                .buildAndRegister();

        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().EUt(24).duration(40)
                .fluidInputs(RawSoybeanOil.getFluid(1000))
                .fluidOutputs(SeedOil.getFluid(600))
                .buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().EUt(24).duration(40)
                .fluidInputs(SoybeanOil.getFluid(1000))
                .fluidOutputs(SeedOil.getFluid(1000))
                .buildAndRegister();
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().EUt(24).duration(40)
                .fluidInputs(OliveOil.getFluid(1000))
                .fluidOutputs(SeedOil.getFluid(500))
                .buildAndRegister();

        ModHandler.addShapelessRecipe("gtfo_seed_pea_extraction", PEAS.getStackForm(),
                PEA_POD);
        RecipeMaps.CANNER_RECIPES.recipeBuilder().EUt(8).duration(20)
                .inputs(PEA_POD.getStackForm())
                .outputs(PEAS.getStackForm(8))
                .buildAndRegister();


/*
        RecipeUtils.addGreenHouseRecipes(LEMON.getStackForm(), LEMON);
        RecipeUtils.addGreenHouseRecipes(LIME.getStackForm(), LIME);
        RecipeUtils.addGreenHouseRecipes(TOMATO.getStackForm(), TOMATO);
        RecipeUtils.addGreenHouseRecipes(CUCUMBER.getStackForm(), CUCUMBER);
        RecipeUtils.addGreenHouseRecipes(ONION.getStackForm(), ONION);
        RecipeUtils.addGreenHouseRecipes(GRAPES.getStackForm(), GRAPES);

        RecipeUtils.addGreenHouseRecipes(PopcornKernel.getItemStack(), POPCORN_EAR);
*/

    }
}
