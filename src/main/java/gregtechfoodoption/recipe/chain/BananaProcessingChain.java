package gregtechfoodoption.recipe.chain;

import gregtechfoodoption.item.GTFOMetaItem;

import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtechfoodoption.GTFOMaterialHandler.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;

public class BananaProcessingChain {
    public static void init() {

        CHEMICAL_RECIPES.recipeBuilder().EUt(120).duration(200)
                .notConsumable(dust, Platinum)
                .fluidInputs(DilutedHydrochloricAcid.getFluid(1000), Oxygen.getFluid(4000)) // H3ClO + 4O -> H2O + HClO4
                .fluidOutputs(PerchloricAcid.getFluid(1000), Water.getFluid(1000))
                .buildAndRegister();

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
