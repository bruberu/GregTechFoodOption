package gregtechfoodoption.recipe.chain;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.Benzene;
import static gregtech.api.unification.material.Materials.Ethanol;
import static gregtechfoodoption.GTFOMaterialHandler.*;

public class Testosteronechain {
    public static void init() {
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(300)
                .fluidInputs(Cholesterol.getFluid(1000))
                .fluidInputs(Ozone.getFluid(1000))
                .fluidOutputs(Pregnenoloneintermediate.getFluid(1000))
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(300)
                .fluidInputs(Pregnenoloneintermediate.getFluid(1000))
                .fluidInputs(hydrogenperoxide.getFluid(1000))
                .fluidOutputs(Pregnenolone.getFluid(1000))
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(300)
                .fluidInputs(Benzene.getFluid(1000))
                .fluidInputs(hydrogenperoxide.getFluid(1000))

    }
}

