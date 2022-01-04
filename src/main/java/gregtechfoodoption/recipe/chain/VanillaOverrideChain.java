package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.GTFOMaterialHandler;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.integration.applecore.GTFOAppleCoreCompat;
import gregtechfoodoption.item.GTFOMetaItem;
import gregtechfoodoption.utils.RecipeUtils;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static gregtech.api.unification.material.Materials.*;
import static gregtechfoodoption.GTFOMaterialHandler.ChloroauricAcid;

public class VanillaOverrideChain {
    public static void init() {
        RecipeUtils.removeAllSmelting(RecipeUtils.wildcardize(new ItemStack(Items.POTATO)));
        GTFOAppleCoreCompat.addToSparedItems(Items.BAKED_POTATO, 2, (float) 0.5);

        RecipeUtils.addBakingOvenRecipes(new ItemStack(Items.POTATO), new ItemStack(Items.BAKED_POTATO), 900, 435, 1);

        if(GTFOConfig.gtfoVanillaOverridesConfig.useBakingOvenForMeats) {
            RecipeUtils.removeAllSmelting(RecipeUtils.wildcardize(new ItemStack(Items.BEEF)));
            GTFOAppleCoreCompat.addToSparedItems(Items.COOKED_BEEF, 7, (float) 0.6);
            RecipeUtils.addBakingOvenRecipes(new ItemStack(Items.BEEF), new ItemStack(Items.COOKED_BEEF), 2000, 500, 4);


            RecipeUtils.removeAllSmelting(RecipeUtils.wildcardize(new ItemStack(Items.PORKCHOP)));
            GTFOAppleCoreCompat.addToSparedItems(Items.COOKED_PORKCHOP, 7, (float) 0.6);
            RecipeUtils.addBakingOvenRecipes(new ItemStack(Items.PORKCHOP), new ItemStack(Items.COOKED_PORKCHOP), 1800, 490, 3);

            RecipeUtils.removeAllSmelting(RecipeUtils.wildcardize(new ItemStack(Items.MUTTON)));
            GTFOAppleCoreCompat.addToSparedItems(Items.COOKED_MUTTON, 6, (float) 0.9);
            RecipeUtils.addBakingOvenRecipes(new ItemStack(Items.MUTTON), new ItemStack(Items.COOKED_MUTTON), 1600, 500, 4);


            RecipeUtils.removeAllSmelting(RecipeUtils.wildcardize(new ItemStack(Items.CHICKEN)));
            GTFOAppleCoreCompat.addToSparedItems(Items.COOKED_CHICKEN, 8, (float) 0.75);
            RecipeUtils.addBakingOvenRecipes(new ItemStack(Items.CHICKEN), new ItemStack(Items.COOKED_CHICKEN), 3000, 520, 6);

            RecipeUtils.removeAllSmelting(RecipeUtils.wildcardize(new ItemStack(Items.RABBIT)));
            GTFOAppleCoreCompat.addToSparedItems(Items.COOKED_RABBIT, 8, (float) 0.75);
            RecipeUtils.addBakingOvenRecipes(new ItemStack(Items.RABBIT), new ItemStack(Items.COOKED_RABBIT), 2000, 500, 5);

            RecipeUtils.removeAllSmelting(new ItemStack(Items.FISH,1, 0));
            RecipeUtils.removeAllSmelting(new ItemStack(Items.FISH,1, 1));

            GTFOAppleCoreCompat.addToSparedItems(Items.COOKED_FISH, 5, (float) 0.8);
            RecipeUtils.addBakingOvenRecipes(new ItemStack(Items.FISH), new ItemStack(Items.COOKED_FISH), 800, 485, 2);

            GTFOAppleCoreCompat.addToSparedItems(new ItemStack(Items.COOKED_FISH, 1, 1).getItem(), 5, (float) 0.8);
            RecipeUtils.addBakingOvenRecipes(new ItemStack(Items.FISH, 1, 1), new ItemStack(Items.COOKED_FISH, 1, 1), 800, 475, 2);

        }

        if(GTFOConfig.gtfoVanillaOverridesConfig.useRollingPinForPaper) {
            ModHandler.removeRecipes(new ItemStack(Items.PAPER, 2));
            ModHandler.addShapedRecipe("gtfo_paper", new ItemStack(Items.PAPER, 2),
                    " R ", "CCC",
                    'R', OreDictUnifier.get(GTFOValues.craftingToolRollingPin),
                    'C', OreDictUnifier.get(OrePrefix.dust, Materials.Paper));
        }

        GTFOAppleCoreCompat.addToSparedItems(Items.RABBIT_STEW, 11, (float) 1.2);
        ModHandler.removeRecipes(new ItemStack(Items.MUSHROOM_STEW));
        GTFOAppleCoreCompat.addToSparedItems(Items.MUSHROOM_STEW, 5, (float) 0.9);
        ModHandler.removeRecipes(new ItemStack(Items.BEETROOT_SOUP));
        GTFOAppleCoreCompat.addToSparedItems(Items.BEETROOT_SOUP, 3, (float) 0.9);

        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(8).duration(100)
                .input(Blocks.BROWN_MUSHROOM)
                .input(Blocks.RED_MUSHROOM)
                .input(OrePrefix.dust, Wheat)
                .fluidInputs(Milk.getFluid(25))
                .fluidOutputs(GTFOMaterialHandler.MushroomSoup.getFluid(50))
                .buildAndRegister();

        RecipeMaps.CANNER_RECIPES.recipeBuilder().EUt(8).duration(10)
                .fluidInputs(GTFOMaterialHandler.MushroomSoup.getFluid(250))
                .input(Items.BOWL)
                .output(Items.MUSHROOM_STEW)
                .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(8).duration(100)
                .input(Items.BEETROOT, 4)
                .input(OrePrefix.dust, Wheat)
                .fluidInputs(Water.getFluid(100))
                .fluidOutputs(GTFOMaterialHandler.BeetrootSoup.getFluid(125))
                .buildAndRegister();

        RecipeMaps.CANNER_RECIPES.recipeBuilder().EUt(8).duration(10)
                .fluidInputs(GTFOMaterialHandler.BeetrootSoup.getFluid(250))
                .input(Items.BOWL)
                .output(Items.BEETROOT_SOUP)
                .buildAndRegister();

        ModHandler.removeRecipes(new ItemStack(Items.GOLDEN_CARROT));
        ModHandler.removeRecipes(new ItemStack(Items.GOLDEN_APPLE));

        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, OreDictUnifier.get(OrePrefix.nugget, Gold, 8), new ItemStack(Items.CARROT));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, OreDictUnifier.get(OrePrefix.ingot, Gold, 8), new ItemStack(Items.APPLE));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, OreDictUnifier.get(OrePrefix.block, Gold, 8), new ItemStack(Items.APPLE));

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder().EUt(1920).duration(200)
                .input(Items.CARROT)
                .outputs(GTFOMetaItem.CARROT_STRUCTURAL_MESH.getStackForm())
                .buildAndRegister();
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder().EUt(1920).duration(200)
                .input(Items.APPLE)
                .outputs(GTFOMetaItem.APPLE_STRUCTURAL_MESH.getStackForm())
                .buildAndRegister();

        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(1920).duration(1500)
                .inputs(GTFOMetaItem.CARROT_STRUCTURAL_MESH.getStackForm())
                .fluidInputs(ChloroauricAcid.getFluid(3000))
                .output(Items.GOLDEN_CARROT)
                .buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(100000).duration(8000)
                .inputs(GTFOMetaItem.APPLE_STRUCTURAL_MESH.getStackForm())
                .fluidInputs(ChloroauricAcid.getFluid(6000))
                .output(Items.GOLDEN_APPLE)
                .buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(100000000).duration(100000)
                .inputs(GTFOMetaItem.APPLE_STRUCTURAL_MESH.getStackForm())
                .fluidInputs(ChloroauricAcid.getFluid(54000))
                .outputs(new ItemStack(Items.GOLDEN_APPLE, 1, 1))
                .buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(1000000).duration(10000)
                .inputs(GTFOMetaItem.APPLE_STRUCTURAL_MESH.getStackForm())
                .fluidInputs(TungstenSteel.getFluid(12000))
                .outputs(GTFOMetaItem.TUNGSTENSTEEL_APPLE.getStackForm())
                .buildAndRegister();
    }
}
