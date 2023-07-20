package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.common.items.MetaItems;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustSmall;
import static gregtechfoodoption.GTFOMaterialHandler.*;
import static gregtechfoodoption.item.GTFOMetaItem.*;

public class CapletChain {
    public static void init() {
        FLUID_HEATER_RECIPES.recipeBuilder().EUt(120).duration(540)
                .fluidInputs(Water.getFluid(1000))
                .circuitMeta(2)
                .fluidOutputs(HeatedWater.getFluid(1000))
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder().EUt(480).duration(200)
                .fluidInputs(HeatedWater.getFluid(1000))
                .inputs(GELATIN.getStackForm(8))
                .fluidOutputs(GelatinSolution.getFluid(1000))
                .buildAndRegister();
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(120).duration(100)
                .fluidInputs(GelatinSolution.getFluid(108))
                .notConsumable(MetaItems.SHAPE_MOLD_BALL)
                .outputs(CAPLET_BODY.getStackForm(16))
                .buildAndRegister();
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(120).duration(100)
                .fluidInputs(GelatinSolution.getFluid(72))
                .notConsumable(MetaItems.SHAPE_MOLD_BOTTLE)
                .outputs(CAPLET_CAP.getStackForm(1))
                .buildAndRegister();
        FORMING_PRESS_RECIPES.recipeBuilder().EUt(30).duration(100)
                .inputs(CAPLET_BODY.getStackForm(1))
                .inputs(CAPLET_CAP.getStackForm(1))
                .circuitMeta(1)
                .outputs(GEL_CAPLET.getStackForm(1))
                .buildAndRegister();
        FORMING_PRESS_RECIPES.recipeBuilder().EUt(30).duration(20)
                .inputs(CAPLET_BODY.getStackForm())
                .input(dustSmall, Paracetamol)
                .inputs(CAPLET_CAP.getStackForm())
                .circuitMeta(3)
                .outputs(PARACETAMOL_CAPLET.getStackForm())
                .buildAndRegister();

        // (C6H5NO3)(C6H5NO3)
        CHEMICAL_RECIPES.recipeBuilder().EUt(480).duration(500)
                .fluidInputs(NitrationMixture.getFluid(2000))
                .fluidInputs(Phenol.getFluid(5000))
                .fluidOutputs(Nitrophenols.getFluid(6000))
                .fluidOutputs(DilutedSulfuricAcid.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(480).duration(10)
                .fluidInputs(Nitrophenols.getFluid(1000))
                .outputs(IVNitrophenol.getItemStack(15))
                .outputs(IINitrophenol.getItemStack(15))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(480).duration(80)
                .inputs(IVNitrophenol.getItemStack(15))
                .fluidInputs(Hydrogen.getFluid(6000))
                .notConsumable(dust, Nickel)
                .fluidOutputs(Water.getFluid(2000))
                .outputs(Aminophenol.getItemStack(15))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(480).duration(500)
                .notConsumable(new IntCircuitIngredient(16))
                .fluidInputs(MethylAcetate.getFluid(1000))
                .fluidInputs(CarbonMonoxide.getFluid(1000))
                .fluidOutputs(AceticAnhydride.getFluid(2000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(1800).duration(400)
                .notConsumable(new IntCircuitIngredient(16))
                .fluidInputs(AceticAnhydride.getFluid(1000))
                .inputs(Aminophenol.getItemStack(15))
                .output(dust, Paracetamol, 20)
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();

        // Plutonium 241
        FORMING_PRESS_RECIPES.recipeBuilder().EUt(30).duration(20)
                .inputs(CAPLET_BODY.getStackForm())
                .input(dustSmall, Plutonium241)
                .inputs(CAPLET_CAP.getStackForm())
                .circuitMeta(2)
                .outputs(PLUTONIUM_241_CAPLET.getStackForm())
                .buildAndRegister();

    }
}