package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.GTFOMaterialHandler;
import gregtechfoodoption.item.GTFOMetaItem;
import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import static gregtech.api.unification.material.Materials.SeedOil;
import static gregtech.api.unification.material.Materials.Wood;

public class SeedsChain {
    public static void init() {
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().EUt(6).duration(20)
                .inputs(MetaItems.PLANT_BALL.getStackForm())
                .chancedOutput(GTFOMaterialHandler.PopcornKernel.getItemStack(), 2000, 250)
                .buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().EUt(6).duration(20)
                .inputs(new ItemStack(Blocks.LEAVES, 1, BlockPlanks.EnumType.JUNGLE.getMetadata()))
                .chancedOutput(GTFOMetaItem.LEMON.getStackForm(), 2000, 250)
                .chancedOutput(GTFOMetaItem.LIME.getStackForm(), 2000, 250)
                .chancedOutput(GTFOMetaItem.ORANGE.getStackForm(), 2000, 250)
                .chancedOutput(GTFOMetaItem.BANANA.getStackForm(), 2000, 250)
                .chancedOutput(GTFOMetaItem.MANGO.getStackForm(), 2000, 250)
                .buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().EUt(6).duration(20)
                .inputs(new ItemStack(Blocks.LEAVES, 1, BlockPlanks.EnumType.OAK.getMetadata()))
                .chancedOutput(GTFOMetaItem.TOMATO.getStackForm(), 2000, 250)
                .chancedOutput(GTFOMetaItem.CUCUMBER.getStackForm(), 2000, 250)
                .chancedOutput(GTFOMetaItem.ONION.getStackForm(), 2000, 250)
                .chancedOutput(GTFOMetaItem.OLIVE.getStackForm(), 2000, 250)
                .chancedOutput(GTFOMetaItem.GRAPES.getStackForm(), 2000, 250)
                .buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().EUt(6).duration(20)
                .inputs(new ItemStack(Blocks.LEAVES2, 1, 0))
                .chancedOutput(GTFOMetaItem.APRICOT.getStackForm(), 2000, 250)
                .buildAndRegister();

        if(GTFOConfig.gtfoMiscConfig.centrifugeSeeds) {
            ItemStack[] seeds = new ItemStack[]{
                    GTFOMetaItem.LEMON.getStackForm(),
                    GTFOMetaItem.LIME.getStackForm(),
                    GTFOMetaItem.TOMATO.getStackForm(),
                    GTFOMetaItem.CUCUMBER.getStackForm(),
                    GTFOMetaItem.OLIVE.getStackForm(),
                    GTFOMetaItem.ONION.getStackForm(),
                    GTFOMetaItem.BANANA.getStackForm(),
                    GTFOMetaItem.ORANGE.getStackForm(),
                    GTFOMetaItem.GRAPES.getStackForm(),
                    GTFOMetaItem.MANGO.getStackForm(),
                    GTFOMetaItem.APRICOT.getStackForm(),
                    GTFOMaterialHandler.PopcornKernel.getItemStack()
            };
            for (ItemStack seed : seeds) {
                RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().EUt(5).duration(144)
                        .inputs(seed)
                        .fluidOutputs(Materials.Methane.getFluid(34))
                        .buildAndRegister();
            }
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

        ModHandler.addShapelessRecipe("gtfo_seed_soy_extraction", GTFOMetaItem.SOYBEAN_SEED.getStackForm(),
                GTFOMetaItem.SOYBEAN);
        ModHandler.addShapelessRecipe("gtfo_seed_tomato_extraction", GTFOMetaItem.TOMATO_SEED.getStackForm(),
                GTFOMetaItem.TOMATO);
        ModHandler.addShapelessRecipe("gtfo_seed_cucumber_extraction", GTFOMetaItem.CUCUMBER_SEED.getStackForm(),
                GTFOMetaItem.CUCUMBER);
        ModHandler.addShapelessRecipe("gtfo_seed_grapes_extraction", GTFOMetaItem.GRAPE_SEED.getStackForm(),
                GTFOMetaItem.GRAPES);

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder().EUt(2).duration(64)
                .input(GTFOMetaItem.SOYBEAN_SEED)
                .fluidOutputs(SeedOil.getFluid(15))
                .buildAndRegister();

        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().EUt(64).duration(64)
                .input(GTFOMetaItem.SOYBEAN_SEED)
                .output(OrePrefix.dustSmall, Wood)
                .fluidOutputs(SeedOil.getFluid(28))
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
