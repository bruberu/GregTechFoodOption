package gregtechfoodoption.recipe.chain;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtechfoodoption.GTFOMaterialHandler.*;

public class Testosteronechain {
    public static void init() {
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(300)
                .fluidInputs(Cholesterol.getFluid(1000))
                .fluidInputs(Ozone.getFluid(1000))
                .fluidOutputs(Pregnenoloneintermediate.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(300)
                .fluidInputs(Pregnenoloneintermediate.getFluid(1000))
                .fluidInputs(hydrogenperoxide.getFluid(1000))
                .fluidOutputs(Pregnenolone.getFluid(1000))
                .buildAndRegister();
        FLUID_HEATER_RECIPES.recipeBuilder().EUt(8).duration(300)
                .fluidInputs(PhthalicAcid.getFluid(1000))
                .fluidOutputs(heatedPhthalicAcid.getFluid(1000))
                .buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().EUt(8).duration(300)
                .fluidInputs(heatedPhthalicAcid.getFluid(1000))
                .output(dust, PhthalicAnhydride, 10)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(300)
                .fluidInputs(Benzene.getFluid(1000))
                .input(dust, PhthalicAnhydride)
                .notConsumable(dust, Aluminium)
                .fluidOutputs(anthraquinone.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(300)
                .fluidInputs(anthraquinone.getFluid(1000))
                .fluidInputs(Hydrogen.getFluid(2000))
                .fluidOutputs(anthrahydroquinone.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(300)
                .fluidInputs(anthrahydroquinone.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(2000))
                .fluidOutputs(anthraquinone.getFluid(1000))
                .fluidOutputs(hydrogenperoxide.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(300)
                .fluidInputs(Water.getFluid(1000))
                .input(dust, ChromiumTrioxide)
                .fluidOutputs(Chromicacid.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(300)
                .fluidInputs(Chromicacid.getFluid(1000))
                .fluidInputs(Pregnenolone.getFluid(1000))
                .fluidOutputs(Progesterone.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(300)
                .input(dust, ChromiumTrioxide)
                .fluidInputs(Progesterone.getFluid(1000))
                .fluidOutputs(Androstenedioneintermediate.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(300)
                .fluidInputs(Androstenedioneintermediate.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(Androstenedione.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(300)
                .input(dust, SodiumBorohydride)
                .fluidInputs(Androstenedione.getFluid(1000))
                .fluidOutputs(Testosterone.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(300)
                .input(dust, AceticAnhydride)
                .fluidInputs(Androstenedione.getFluid(1000))
                .fluidOutputs(Testosterone.getFluid(1000))
                .buildAndRegister();


    }
}

