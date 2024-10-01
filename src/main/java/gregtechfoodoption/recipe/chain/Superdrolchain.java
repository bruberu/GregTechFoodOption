package gregtechfoodoption.recipe.chain;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtechfoodoption.GTFOMaterialHandler.*;

public class Superdrolchain {
    public static void init() {
        CHEMICAL_RECIPES.recipeBuilder().EUt(64).duration(2400)
                .fluidInputs(Testosterone.getFluid(1000))
                .input(dust, MethylIodide)
                .fluidInputs(PotassiumCarbonate.getFluid(1000))
                .fluidInputs(Dimethylformamide.getFluid(1000))
                .fluidOutputs(Methyltestosterone.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(128).duration(3600)
                .fluidInputs(Methyltestosterone.getFluid(1000))
                .notConsumable(dust, ChromiumTrioxide)
                .notConsumable(SulfuricAcid.getFluid(1000))
                .fluidInputs(Acetone.getFluid(1000))
                .fluidOutputs(SuperdrolSolution.getFluid(1000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().EUt(64).duration(1800)
                .fluidInputs(SuperdrolSolution.getFluid(1000))
                .fluidOutputs(PurifiedSuperdrolSolution.getFluid(500))
                .buildAndRegister();

        AUTOCLAVE_RECIPES.recipeBuilder().EUt(96).duration(3600)
                .fluidInputs(PurifiedSuperdrolSolution.getFluid(1000))
                .output(dust, Superdrol, 10)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(128).duration(3600)
                .fluidInputs(Methanol.getFluid(1000))
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .notConsumable(dust, Silver)
                .fluidOutputs(FormicAcid.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(128).duration(3600)
                .fluidInputs(Dimethylamine.getFluid(1000))
                .fluidInputs(FormicAcid.getFluid(1000))
                .input(dust, Zinc, 1000)
                .fluidOutputs(Dimethylformamide.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(64).duration(1800)
                .input(dust, PotassiumHydroxide)
                .fluidInputs(CarbonDioxide.getFluid(1000))
                .fluidOutputs(PotassiumCarbonate.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(128).duration(3600)
                .fluidInputs(Methanol.getFluid(1000))
                .fluidInputs(Iodine.getFluid(1000))
                .notConsumable(dust, SulfurDioxide, 1000)
                .output(dust, MethylIodide)
                .buildAndRegister();

    }
}

