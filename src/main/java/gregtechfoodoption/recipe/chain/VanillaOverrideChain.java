package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;
import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.GTFOMaterialHandler;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.integration.applecore.GTFOAppleCoreCompat;
import gregtechfoodoption.item.GTFOMetaItem;
import gregtechfoodoption.utils.GTFOUtils;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static gregtech.api.unification.material.Materials.*;
import static gregtechfoodoption.GTFOMaterialHandler.ChloroauricAcid;
import static gregtechfoodoption.GTFOMaterialHandler.MashedPotato;

public class VanillaOverrideChain {
    public static void init() {
        GTFOUtils.removeAllSmelting(GTFOUtils.wildcardize(new ItemStack(Items.POTATO)));
        GTFOAppleCoreCompat.addToSparedItems(Items.BAKED_POTATO, 2, (float) 0.5);

        GTFOUtils.addBakingOvenRecipes(new ItemStack(Items.POTATO), new ItemStack(Items.BAKED_POTATO), 450, 435, 1);

        if(GTFOConfig.gtfoVanillaOverridesConfig.useBakingOvenForMeats) {
            GTFOUtils.removeAllSmelting(GTFOUtils.wildcardize(new ItemStack(Items.BEEF)));
            GTFOAppleCoreCompat.addToSparedItems(Items.COOKED_BEEF, 6, (float) 0.6);
            GTFOUtils.addBakingOvenRecipes(new ItemStack(Items.BEEF), new ItemStack(Items.COOKED_BEEF), 1000, 500, 2);


            GTFOUtils.removeAllSmelting(GTFOUtils.wildcardize(new ItemStack(Items.PORKCHOP)));
            GTFOAppleCoreCompat.addToSparedItems(Items.COOKED_PORKCHOP, 6, (float) 0.6);
            GTFOUtils.addBakingOvenRecipes(new ItemStack(Items.PORKCHOP), new ItemStack(Items.COOKED_PORKCHOP), 900, 490, 1);

            GTFOUtils.removeAllSmelting(GTFOUtils.wildcardize(new ItemStack(Items.MUTTON)));
            GTFOAppleCoreCompat.addToSparedItems(Items.COOKED_MUTTON, 5, (float) 0.9);
            GTFOUtils.addBakingOvenRecipes(new ItemStack(Items.MUTTON), new ItemStack(Items.COOKED_MUTTON), 800, 500, 2);


            GTFOUtils.removeAllSmelting(GTFOUtils.wildcardize(new ItemStack(Items.CHICKEN)));
            GTFOAppleCoreCompat.addToSparedItems(Items.COOKED_CHICKEN, 7, (float) 0.75);
            GTFOUtils.addBakingOvenRecipes(new ItemStack(Items.CHICKEN), new ItemStack(Items.COOKED_CHICKEN), 1500, 520, 2);

            GTFOUtils.removeAllSmelting(GTFOUtils.wildcardize(new ItemStack(Items.RABBIT)));
            GTFOAppleCoreCompat.addToSparedItems(Items.COOKED_RABBIT, 7, (float) 0.75);
            GTFOUtils.addBakingOvenRecipes(new ItemStack(Items.RABBIT), new ItemStack(Items.COOKED_RABBIT), 1000, 500, 2);

            GTFOUtils.removeAllSmelting(new ItemStack(Items.FISH,1, 0));
            GTFOUtils.removeAllSmelting(new ItemStack(Items.FISH,1, 1));

            GTFOAppleCoreCompat.addToSparedItems(Items.COOKED_FISH, 5, (float) 0.8);
            GTFOUtils.addBakingOvenRecipes(new ItemStack(Items.FISH), new ItemStack(Items.COOKED_FISH), 400, 485, 2);

            GTFOAppleCoreCompat.addToSparedItems(new ItemStack(Items.COOKED_FISH, 1, 1).getItem(), 5, (float) 0.8);
            GTFOUtils.addBakingOvenRecipes(new ItemStack(Items.FISH, 1, 1), new ItemStack(Items.COOKED_FISH, 1, 1), 400, 475, 2);

        }

        if(GTFOConfig.gtfoVanillaOverridesConfig.useRollingPinForPaper) {
            ModHandler.removeRecipeByOutput(new ItemStack(Items.PAPER, 2));
            ModHandler.addShapedRecipe("gtfo_paper", new ItemStack(Items.PAPER, 2),
                    " R ", "CCC",
                    'R', OreDictUnifier.get(GTFOValues.craftingToolRollingPin),
                    'C', new UnificationEntry(OrePrefix.dust, Materials.Paper));
            ModHandler.removeRecipeByOutput(new ItemStack(Blocks.STICKY_PISTON, 1));
            ModHandler.addShapedRecipe("gtfo_sticky_piston", new ItemStack(Blocks.STICKY_PISTON, 1),
                    "R", "S", "P",
                    'R', OreDictUnifier.get(GTFOValues.craftingToolRollingPin),
                    'S', MetaItems.STICKY_RESIN,
                    'P', Blocks.PISTON);
            ModHandler.addShapedRecipe("gtfo_sticky_piston_slime", new ItemStack(Blocks.STICKY_PISTON, 1),
                    "R", "S", "P",
                    'R', OreDictUnifier.get(GTFOValues.craftingToolRollingPin),
                    'S', Items.SLIME_BALL,
                    'P', Blocks.PISTON);
        }

        ModHandler.removeRecipeByOutput(new ItemStack(Items.RABBIT_STEW));
        GTFOAppleCoreCompat.addToSparedItems(Items.RABBIT_STEW, 7, (float) 1.2);
        ModHandler.removeRecipeByOutput(new ItemStack(Items.MUSHROOM_STEW));
        GTFOAppleCoreCompat.addToSparedItems(Items.MUSHROOM_STEW, 5, (float) 0.9);
        ModHandler.removeRecipeByOutput(new ItemStack(Items.BEETROOT_SOUP));
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
                .input(Items.BEETROOT, 2)
                .input(OrePrefix.dust, Wheat)
                .fluidInputs(Water.getFluid(100))
                .fluidOutputs(GTFOMaterialHandler.BeetrootSoup.getFluid(125))
                .buildAndRegister();

        RecipeMaps.CANNER_RECIPES.recipeBuilder().EUt(8).duration(10)
                .fluidInputs(GTFOMaterialHandler.BeetrootSoup.getFluid(250))
                .input(Items.BOWL)
                .output(Items.BEETROOT_SOUP)
                .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(8).duration(100)
                .input(Blocks.BROWN_MUSHROOM)
                .input(Items.CARROT)
                .input(Items.COOKED_RABBIT)
                .inputs(MashedPotato.getItemStack())
                .input(OrePrefix.dust, Wheat)
                .fluidInputs(Water.getFluid(50))
                .fluidOutputs(GTFOMaterialHandler.RabbitStew.getFluid(125))
                .buildAndRegister();

        RecipeMaps.CANNER_RECIPES.recipeBuilder().EUt(8).duration(10)
                .fluidInputs(GTFOMaterialHandler.RabbitStew.getFluid(250))
                .input(Items.BOWL)
                .output(Items.RABBIT_STEW)
                .buildAndRegister();

        ModHandler.removeRecipeByOutput(new ItemStack(Items.GOLDEN_CARROT));
        ModHandler.removeRecipeByOutput(new ItemStack(Items.GOLDEN_APPLE));

        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, OreDictUnifier.get(OrePrefix.nugget, Gold, 8), new ItemStack(Items.CARROT));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, OreDictUnifier.get(OrePrefix.ingot, Gold, 8), new ItemStack(Items.APPLE));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, OreDictUnifier.get(OrePrefix.block, Gold, 8), new ItemStack(Items.APPLE));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.LARGE_CHEMICAL_RECIPES, OreDictUnifier.get(OrePrefix.nugget, Gold, 8), new ItemStack(Items.CARROT));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.LARGE_CHEMICAL_RECIPES, OreDictUnifier.get(OrePrefix.ingot, Gold, 8), new ItemStack(Items.APPLE));
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.LARGE_CHEMICAL_RECIPES, OreDictUnifier.get(OrePrefix.block, Gold, 8), new ItemStack(Items.APPLE));


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
