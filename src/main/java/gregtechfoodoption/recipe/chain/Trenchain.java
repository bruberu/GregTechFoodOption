package gregtechfoodoption.recipe.chain;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtechfoodoption.GTFOMaterialHandler.*;

public class Trenchain {
    public static void init() {
        CHEMICAL_RECIPES.recipeBuilder().EUt(24).duration(1200)
                .fluidInputs(Testosterone.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(2000))
                .notConsumable(dust, ChromiumTrioxide)
                .notConsumable(dust, Sulfur)
                .fluidOutputs(TestosteroneEnoneIntermediate.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(32).duration(1800)
                .fluidInputs(TestosteroneEnoneIntermediate.getFluid(1000))
                .fluidInputs(hydrogenperoxide.getFluid(500))
                .notConsumable(dust, PotassiumHydroxide)
                .fluidOutputs(Dienedione.getFluid(500))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(16).duration(900)
                .fluidInputs(Dienedione.getFluid(850))
                .input(dust, SodiumHydroxide)
                .fluidOutputs(DienoloneIntermediate.getFluid(800))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(32).duration(2400)
                .fluidInputs(DienoloneIntermediate.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(3000))
                .notConsumable(dust, Palladium)
                .notConsumable(dust, Carbon)
                .fluidOutputs(Trenbolone.getFluid(750))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().EUt(24).duration(1500)
                .fluidInputs(Trenbolone.getFluid(1500))
                .fluidOutputs(PurifiedTrenbolone.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(48).duration(3000)
                .fluidInputs(PurifiedTrenbolone.getFluid(1000))
                .fluidInputs(AceticAnhydride.getFluid(1000))
                .notConsumable(SulfuricAcid.getFluid(1000))
                .fluidOutputs(TrenboloneAcetate.getFluid(100))
                .fluidOutputs(AceticAcid.getFluid(200))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(48).duration(3000)
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(1000))
                .input(dust, Potassium)
                .output(dust, PotassiumHydroxide)
                .buildAndRegister();
    }
}

