package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.block.GTFOCrop;
import gregtechfoodoption.block.GTFOTrees;
import gregtechfoodoption.item.GTFOMetaItem;
import gregtechfoodoption.recipe.GTFORecipeMaps;
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

        ItemStack[] crops = new ItemStack[]{
                LEMON.getStackForm(),
                LIME.getStackForm(),
                TOMATO.getStackForm(),
                CUCUMBER.getStackForm(),
                OLIVE.getStackForm(),
                ONION.getStackForm(),
                BANANA.getStackForm(),
                ORANGE.getStackForm(),
                //GRAPES.getStackForm(),  Done in AlcoholChain
                MANGO.getStackForm(),
                APRICOT.getStackForm(),
                PEA_POD.getStackForm(),
                SOYBEAN.getStackForm(),
                BEANS.getStackForm(),
                COFFEE_CHERRY.getStackForm(),
                CORN_COB.getStackForm(),
                RICE.getStackForm(),
                HORSERADISH.getStackForm(),
                OREGANO.getStackForm(),
                GARLIC_BULB.getStackForm(),
                BASIL.getStackForm(),
                AUBERGINE.getStackForm(),
                ARTICHOKE_HEART.getStackForm(),
                BLACK_PEPPERCORN.getStackForm()
        };

        for (ItemStack seed : crops) {
            if (GTFOConfig.gtfoMiscConfig.centrifugeSeeds) {
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
        for (GTFOCrop crop : GTFOCrop.CROP_BLOCKS) {
            RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder().duration(32).EUt(2)
                    .inputs(crop.getSeedStack())
                    .circuitMeta(3)
                    .fluidOutputs(SeedOil.getFluid(8))
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
        ModHandler.addShapedRecipe("gtfo_seed_oregano_ungenerify", OREGANO_SEED.getStackForm(),
                "   ", "   ", "  S",
                'S', GTFOMetaItem.UNKNOWN_SEED);
        ModHandler.addShapedRecipe("gtfo_seed_horseradish_ungenerify", HORSERADISH_SEED.getStackForm(2),
                "SS ", "   ", "   ",
                'S', GTFOMetaItem.UNKNOWN_SEED);
        ModHandler.addShapedRecipe("gtfo_seed_garlic_ungenerify", GARLIC_CLOVE.getStackForm(2),
                "S S", "   ", "   ",
                'S', GTFOMetaItem.UNKNOWN_SEED);
        ModHandler.addShapedRecipe("gtfo_seed_basil_ungenerify", BASIL_SEED.getStackForm(2),
                " SS", "   ", "   ",
                'S', GTFOMetaItem.UNKNOWN_SEED);
        ModHandler.addShapedRecipe("gtfo_seed_aubergine_ungenerify", AUBERGINE_SEED.getStackForm(2),
                "S  ", "S  ", "   ",
                'S', GTFOMetaItem.UNKNOWN_SEED);
        ModHandler.addShapedRecipe("gtfo_seed_corn_ungenerify", CORN_EAR.getStackForm(2),
                " S ", "S  ", "   ",
                'S', GTFOMetaItem.UNKNOWN_SEED);
        ModHandler.addShapedRecipe("gtfo_seed_artichoke_ungenerify", ARTICHOKE_SEED.getStackForm(2),
                "  S", "S  ", "   ",
                'S', GTFOMetaItem.UNKNOWN_SEED);
        ModHandler.addShapedRecipe("gtfo_seed_black_pepper_ungenerify", BLACK_PEPPERCORN.getStackForm(2),
                "S  ", " S ", "   ",
                'S', GTFOMetaItem.UNKNOWN_SEED);
        ModHandler.addShapedRecipe("gtfo_seed_rice_ungenerify", RICE.getStackForm(2),
                " S ", " S ", "   ",
                'S', GTFOMetaItem.UNKNOWN_SEED);
        ModHandler.addShapedRecipe("gtfo_seed_white_grapes_ungenerify", WHITE_GRAPE_SEED.getStackForm(2),
                "  S", " S ", "   ",
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
        ModHandler.addShapelessRecipe("gtfo_seed_aubergine_extraction", AUBERGINE_SEED.getStackForm(),
                AUBERGINE);
        ModHandler.addShapelessRecipe("gtfo_seed_artichoke_extraction", ARTICHOKE_SEED.getStackForm(),
                ARTICHOKE_HEART);
        ModHandler.addShapelessRecipe("gtfo_clove_garlic_extraction", GARLIC_CLOVE.getStackForm(3),
                GARLIC_BULB.getStackForm());
        GTFORecipeMaps.SLICER_RECIPES.recipeBuilder().EUt(8).duration(40)
                .inputs(GARLIC_BULB.getStackForm())
                .notConsumable(SLICER_BLADE_OCTAGONAL.getStackForm())
                .outputs(GARLIC_CLOVE.getStackForm(8))
                .buildAndRegister();

        RecipeMaps.CUTTER_RECIPES.recipeBuilder().EUt(8).duration(100)
                .inputs(SOYBEAN.getStackForm())
                .outputs(SOYBEAN_SEED.getStackForm(3))
                .chancedOutput(SOYBEAN_SEED.getStackForm(2), 5000, 100)
                .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder().EUt(2).duration(64)
                .input(GTFOMetaItem.SOYBEAN_SEED)
                .circuitMeta(1)
                .fluidOutputs(RawSoybeanOil.getFluid(15))
                .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder().EUt(64).duration(64)
                .input(GTFOMetaItem.SOYBEAN_SEED)
                .circuitMeta(2)
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


        ModHandler.addShapelessRecipe("gtfo_black_pepper", BlackPepper.getItemStack(),
                BLACK_PEPPERCORN, 'm');
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().EUt(8).duration(80)
                .inputs(BLACK_PEPPERCORN.getStackForm())
                .outputs(BlackPepper.getItemStack(2))
                .buildAndRegister();
        ModHandler.addShapelessRecipe("gtfo_nutmeg", Nutmeg.getItemStack(),
                NUTMEG_SEED, 'm');
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().EUt(8).duration(80)
                .inputs(NUTMEG_SEED.getStackForm())
                .outputs(Nutmeg.getItemStack(2))
                .buildAndRegister();

        GTFORecipeMaps.GREENHOUSE_RECIPES.recipeBuilder().EUt(15).duration(500)
                .inputs(NUTMEG_SEED.getStackForm())
                .fluidInputs(Water.getFluid(1000))
                .chancedOutput(GTFOTrees.NUTMEG_TREE.getSaplingStack(), 5000, 0)
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
