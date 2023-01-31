package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.material.Materials;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustSmall;
import static gregtechfoodoption.GTFOMaterialHandler.*;
import static gregtechfoodoption.item.GTFOMetaItem.*;

public class CapletChain {
    public static void init() {
        FLUID_HEATER_RECIPES.recipeBuilder().EUt(2048).duration(280)
                .fluidInputs(Materials.Water.getFluid(1000))
                .circuitMeta(2)
                .fluidOutputs(HeatedWater.getFluid(1000))
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder().EUt(480).duration(200)
                .fluidInputs(HeatedWater.getFluid(1000))
                .inputs(GELATIN.getStackForm(8))
                .fluidOutputs(GelatinSolution.getFluid(1500))
                .buildAndRegister();
        EXTRACTOR_RECIPES.recipeBuilder().EUt(120).duration(100)
                .fluidInputs(GelatinSolution.getFluid(1000))
                .fluidOutputs(GelatinSolutionNoBubbles.getFluid(900))
                .buildAndRegister();
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(120).duration(100)
                .fluidInputs(GelatinSolutionNoBubbles.getFluid(1000))
                .notConsumable(SKEWER.getStackForm(16))
                .notConsumable(IntCircuitIngredient.getIntegratedCircuit(1))
                .outputs(CAPLET_BODY.getStackForm(16))
                .buildAndRegister();
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(120).duration(100)
                .fluidInputs(GelatinSolutionNoBubbles.getFluid(1000))
                .notConsumable(SKEWER.getStackForm(24))
                .notConsumable(IntCircuitIngredient.getIntegratedCircuit(2))
                .outputs(CAPLET_CAP.getStackForm(24))
                .buildAndRegister();
        FORMING_PRESS_RECIPES.recipeBuilder().EUt(120).duration(100)
                .inputs(CAPLET_BODY.getStackForm(4))
                .inputs(CAPLET_CAP.getStackForm(4))
                .outputs(GEL_CAPLET.getStackForm(4))
                .buildAndRegister();
        FORMING_PRESS_RECIPES.recipeBuilder().EUt(30).duration(20)
                .inputs(CAPLET_BODY.getStackForm())
                .inputs(ParacetamolSmall.getItemStack())
                .inputs(CAPLET_CAP.getStackForm())
                .outputs(PARACETAMOL_CAPLET.getStackForm())
                .buildAndRegister();
        ModHandler.addShapelessRecipe("paracetamol_small", ParacetamolSmall.getItemStack(4), Paracetamol.getItemStack());
        ModHandler.addShapelessRecipe("handful_paracetamol", HANDFUL_PARACETAMOL.getStackForm(), PARACETAMOL_CAPLET.getStackForm(), PARACETAMOL_CAPLET.getStackForm(), PARACETAMOL_CAPLET.getStackForm(), PARACETAMOL_CAPLET.getStackForm(), PARACETAMOL_CAPLET.getStackForm(), PARACETAMOL_CAPLET.getStackForm(), PARACETAMOL_CAPLET.getStackForm(), PARACETAMOL_CAPLET.getStackForm());

        // Plutonium 241
        FORMING_PRESS_RECIPES.recipeBuilder().EUt(30).duration(20)
                .inputs(CAPLET_BODY.getStackForm())
                .input(dustSmall, Plutonium241)
                .inputs(CAPLET_CAP.getStackForm())
                .outputs(PLUTONIUM_241_CAPLET.getStackForm())
                .buildAndRegister();

    }
}