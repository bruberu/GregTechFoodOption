package gregtechfoodoption.recipe.chain;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtechfoodoption.GTFOMaterialHandler.*;

public class Dianabolchain {
    public static void init() {
        CHEMICAL_RECIPES.recipeBuilder().EUt(128).duration(3600)
                .fluidInputs(Methyltestosterone.getFluid(1000))
                .notConsumable(dust, ChromiumTrioxide)
                .notConsumable(dust, AceticAnhydride)
                .fluidInputs(Acetone.getFluid(1000))
                .fluidOutputs(DianabolSolution.getFluid(1000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().EUt(64).duration(1800)
                .fluidInputs(DianabolSolution.getFluid(1000))
                .fluidOutputs(PurifiedDianabolSolution.getFluid(500))
                .buildAndRegister();

        AUTOCLAVE_RECIPES.recipeBuilder().EUt(96).duration(3600)
                .fluidInputs(PurifiedDianabolSolution.getFluid(1000))
                .output(dust, Dianabol, 10)
                .buildAndRegister();

    }
}

