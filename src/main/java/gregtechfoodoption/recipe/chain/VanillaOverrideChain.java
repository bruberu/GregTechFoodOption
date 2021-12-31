package gregtechfoodoption.recipe.chain;

import gregtechfoodoption.integration.applecore.GTFOAppleCoreCompat;
import gregtechfoodoption.utils.RecipeUtils;
import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.GTFOMaterialHandler;
import gregtechfoodoption.item.GTFOMetaItem;
import gregtechfoodoption.recipe.GTFORecipeMaps;
import gregtechfoodoption.recipe.builder.BakingOvenRecipeBuilder;
import gregicadditions.GAMaterials;
import gregicadditions.recipes.helper.HelperMethods;
import gregtech.api.items.ToolDictNames;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.HashMap;

import static gregtech.api.unification.material.Materials.*;

public class VanillaOverrideChain {
    public static void init() {
        RecipeUtils.removeAllSmelting(RecipeUtils.wildcardize(new ItemStack(Items.POTATO)));
        GTFOAppleCoreCompat.addToSparedItems(Items.BAKED_POTATO, 2, (float) 0.5);

        HashMap<Integer, Integer> potatoTemps = new HashMap<>();
        potatoTemps.put(435, 900);
        potatoTemps.put(450, 600);
        potatoTemps.put(475, 400);
        potatoTemps.forEach((temp, duration) -> GTFORecipeMaps.ELECTRIC_BAKING_OVEN_RECIPES.recipeBuilder().setTemp(temp).duration(duration)
                .input(Items.POTATO)
                .output(Items.BAKED_POTATO)
                .buildAndRegister());
        BakingOvenRecipeBuilder.start().fuelAmount(700).duration(900)
                .input(new ItemStack(Items.POTATO))
                .output(new ItemStack(Items.BAKED_POTATO))
                .buildAndRegister();

        if(GTFOConfig.gtfoVanillaOverridesConfig.useBakingOvenForMeats) {
            RecipeUtils.removeAllSmelting(RecipeUtils.wildcardize(new ItemStack(Items.BEEF)));
            GTFOAppleCoreCompat.addToSparedItems(Items.COOKED_BEEF, 7, (float) 0.6);
            BakingOvenRecipeBuilder.start().fuelAmount(3200).duration(2000).temperature(500)
                    .input(Items.BEEF)
                    .output(Items.COOKED_BEEF)
                    .buildAndRegister();

            RecipeUtils.removeAllSmelting(RecipeUtils.wildcardize(new ItemStack(Items.PORKCHOP)));
            GTFOAppleCoreCompat.addToSparedItems(Items.COOKED_PORKCHOP, 7, (float) 0.6);
            BakingOvenRecipeBuilder.start().fuelAmount(2600).duration(1800).temperature(490)
                    .input(Items.PORKCHOP)
                    .output(Items.COOKED_PORKCHOP)
                    .buildAndRegister();

            RecipeUtils.removeAllSmelting(RecipeUtils.wildcardize(new ItemStack(Items.MUTTON)));
            GTFOAppleCoreCompat.addToSparedItems(Items.COOKED_MUTTON, 6, (float) 0.9);
            BakingOvenRecipeBuilder.start().fuelAmount(3200).duration(1600).temperature(500)
                    .input(Items.MUTTON)
                    .output(Items.COOKED_MUTTON)
                    .buildAndRegister();

            RecipeUtils.removeAllSmelting(RecipeUtils.wildcardize(new ItemStack(Items.CHICKEN)));
            GTFOAppleCoreCompat.addToSparedItems(Items.COOKED_CHICKEN, 8, (float) 0.75);
            BakingOvenRecipeBuilder.start().fuelAmount(4800).duration(3000).temperature(520)
                    .input(Items.CHICKEN)
                    .output(Items.COOKED_CHICKEN)
                    .buildAndRegister();

            RecipeUtils.removeAllSmelting(RecipeUtils.wildcardize(new ItemStack(Items.RABBIT)));
            GTFOAppleCoreCompat.addToSparedItems(Items.COOKED_RABBIT, 4, (float) 0.75);
            BakingOvenRecipeBuilder.start().fuelAmount(4800).duration(3000).temperature(520)
                    .input(Items.RABBIT)
                    .output(Items.COOKED_RABBIT)
                    .buildAndRegister();

            RecipeUtils.removeAllSmelting(new ItemStack(Items.FISH,1, 0));
            RecipeUtils.removeAllSmelting(new ItemStack(Items.FISH,1, 1));

            GTFOAppleCoreCompat.addToSparedItems(Items.COOKED_FISH, 5, (float) 0.8);
            BakingOvenRecipeBuilder.start().fuelAmount(1600).duration(800).temperature(485)
                    .input(Items.FISH)
                    .output(Items.COOKED_FISH)
                    .buildAndRegister();

            GTFOAppleCoreCompat.addToSparedItems(new ItemStack(Items.COOKED_FISH, 1, 1).getItem(), 5, (float) 0.8);
            BakingOvenRecipeBuilder.start().fuelAmount(1600).duration(800).temperature(485)
                    .input(new ItemStack(Items.FISH, 1, 1))
                    .output(new ItemStack(Items.COOKED_FISH, 1, 1))
                    .buildAndRegister();

        }

        if(GTFOConfig.gtfoVanillaOverridesConfig.useRollingPinForPaper) {
            ModHandler.removeRecipes(new ItemStack(Items.PAPER, 2));
            ModHandler.addShapedRecipe("gtfo_paper", new ItemStack(Items.PAPER, 2),
                    " R ", "CCC",
                    'R', OreDictUnifier.get(String.valueOf(ToolDictNames.craftingToolRollingPin)),
                    'C', OreDictUnifier.get(OrePrefix.dust, Materials.Paper));
        }

        GTFOAppleCoreCompat.addToSparedItems(Items.RABBIT_STEW, 11, (float) 1.2);
        ModHandler.removeRecipes(Items.MUSHROOM_STEW);
        GTFOAppleCoreCompat.addToSparedItems(Items.MUSHROOM_STEW, 5, (float) 0.9);
        ModHandler.removeRecipes(Items.BEETROOT_SOUP);
        GTFOAppleCoreCompat.addToSparedItems(Items.BEETROOT_SOUP, 3, (float) 0.9);

        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(8).duration(100)
                .input(Blocks.BROWN_MUSHROOM)
                .input(Blocks.RED_MUSHROOM)
                .input(OrePrefix.dust, Wheat)
                .fluidInputs(Milk.getFluid(25))
                .fluidOutputs(GTFOMaterialHandler.MushroomSoup.getFluid(50))
                .buildAndRegister();

        RecipeMaps.FLUID_CANNER_RECIPES.recipeBuilder().EUt(8).duration(10)
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

        RecipeMaps.FLUID_CANNER_RECIPES.recipeBuilder().EUt(8).duration(10)
                .fluidInputs(GTFOMaterialHandler.BeetrootSoup.getFluid(250))
                .input(Items.BOWL)
                .output(Items.BEETROOT_SOUP)
                .buildAndRegister();

        ModHandler.removeRecipes(Items.GOLDEN_CARROT);
        ModHandler.removeRecipes(Items.GOLDEN_APPLE);

        HelperMethods.removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, OreDictUnifier.get(OrePrefix.nugget, Gold, 8), new ItemStack(Items.CARROT));
        HelperMethods.removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, OreDictUnifier.get(OrePrefix.ingot, Gold, 8), new ItemStack(Items.APPLE));
        HelperMethods.removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, OreDictUnifier.get(OrePrefix.block, Gold, 8), new ItemStack(Items.APPLE));

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
                .fluidInputs(GAMaterials.ChloroauricAcid.getFluid(3000))
                .output(Items.GOLDEN_CARROT)
                .buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(100000).duration(8000)
                .inputs(GTFOMetaItem.APPLE_STRUCTURAL_MESH.getStackForm())
                .fluidInputs(GAMaterials.ChloroauricAcid.getFluid(6000))
                .output(Items.GOLDEN_APPLE)
                .buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(100000000).duration(100000)
                .inputs(GTFOMetaItem.APPLE_STRUCTURAL_MESH.getStackForm())
                .fluidInputs(GAMaterials.ChloroauricAcid.getFluid(54000))
                .outputs(new ItemStack(Items.GOLDEN_APPLE, 1, 1))
                .buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(1000000).duration(10000)
                .inputs(GTFOMetaItem.APPLE_STRUCTURAL_MESH.getStackForm())
                .fluidInputs(TungstenSteel.getFluid(12000))
                .outputs(GTFOMetaItem.TUNGSTENSTEEL_APPLE.getStackForm())
                .buildAndRegister();
    }
}
