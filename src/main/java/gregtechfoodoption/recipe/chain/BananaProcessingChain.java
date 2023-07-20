package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtechfoodoption.item.GTFOMetaItem;
import net.minecraftforge.fml.common.Loader;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtechfoodoption.GTFOMaterialHandler.*;
import static gregtechfoodoption.GTFOValues.MODID_GCYS;

public class BananaProcessingChain {
    public static void init() {
        if (!Loader.isModLoaded(MODID_GCYS)) {
            ELECTROLYZER_RECIPES.recipeBuilder().EUt(30).duration(56)
                    .input(dust, Salt, 2)
                    .circuitMeta(1)
                    .output(dust, Sodium)
                    .fluidOutputs(Chlorine.getFluid(1000))
                    .buildAndRegister();
            CHEMICAL_RECIPES.recipeBuilder().EUt(120).duration(400) // NaCl + 3H2O -> NaClO3 + 6H
                    .input(dust, Salt, 2)
                    .circuitMeta(2)
                    .fluidInputs(Water.getFluid(3000))
                    .outputs(SodiumChlorate.get(5))
                    .fluidOutputs(Hydrogen.getFluid(6000))
                    .buildAndRegister();

            GTRecipeHandler.removeRecipesByInputs(ELECTROLYZER_RECIPES, Water.getFluid(1000));
            ELECTROLYZER_RECIPES.recipeBuilder().EUt(30).duration(1500) // NaCl + 3H2O -> NaClO3 + 6H
                    .notConsumable(new IntCircuitIngredient(1))
                    .fluidInputs(Water.getFluid(1000))
                    .fluidOutputs(Hydrogen.getFluid(2000), Oxygen.getFluid(1000))
                    .buildAndRegister();
        }
        ELECTROLYZER_RECIPES.recipeBuilder().EUt(60).duration(100)
                .inputs(SodiumChlorate.get(5))
                .notConsumable(new IntCircuitIngredient(1))
                .output(dust, Sodium)
                .fluidOutputs(Chlorine.getFluid(1000), Oxygen.getFluid(3000))
                .buildAndRegister();
        ELECTROLYZER_RECIPES.recipeBuilder().EUt(120).duration(100) // NaClO3 + H2O -> NaClO4 + 2H
                .inputs(SodiumChlorate.get(5))
                .fluidInputs(Water.getFluid(1000))
                .notConsumable(new IntCircuitIngredient(2))
                .outputs(SodiumPerchlorate.getItemStack(6))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(120).duration(100) // NaClO4 + HCl -> NaCl + HClO4
                .inputs(SodiumPerchlorate.getItemStack(6))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .output(dust, Salt, 2)
                .fluidOutputs(PerchloricAcid.getFluid(1000))
                .buildAndRegister();

/*
        CHEMICAL_RECIPES.recipeBuilder().EUt(1920).duration(200)
                .notConsumable(dust, Platinum)
                .fluidInputs(DilutedHydrochloricAcid.getFluid(2000), Oxygen.getFluid(4000)) // H3ClO + 4O -> H2O + HClO4
                .fluidOutputs(PerchloricAcid.getFluid(1000), Water.getFluid(1000))
                .buildAndRegister();
*/

        CHEMICAL_RECIPES.recipeBuilder().EUt(64).duration(300)
                .fluidInputs(PerchloricAcid.getFluid(1000), Ammonia.getFluid(1000))
                .outputs(AmmoniumPerchlorate.getItemStack(10))
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder().EUt(32).duration(60)
                .inputs(GTFOMetaItem.BANANA.getStackForm())
                .outputs(GTFOMetaItem.BANANA_PEEL.getStackForm(), GTFOMetaItem.PEELED_BANANA.getStackForm())
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().EUt(120).duration(671).blastFurnaceTemp(800)
                .inputs(GTFOMetaItem.BANANA_PEEL.getStackForm())
                .outputs(BurntBananaPeel.getItemStack())
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().EUt(24).duration(200)
                .inputs(BurntBananaPeel.getItemStack())
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(AlkalineExtract.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(120).duration(200)
                .inputs(AmmoniumPerchlorate.getItemStack(10))
                .fluidInputs(AlkalineExtract.getFluid(1000))
                .outputs(PotassiumPerchlorate.getItemStack(6))
                .fluidOutputs(Sludge.getFluid(1000))
                .buildAndRegister();

        BLAST_RECIPES.recipeBuilder().EUt(120).duration(319).blastFurnaceTemp(1400)
                .inputs(PotassiumPerchlorate.getItemStack(6))
                .output(dust, RockSalt, 2)
                .fluidOutputs(Oxygen.getFluid(4000))
                .buildAndRegister();

    }
}
