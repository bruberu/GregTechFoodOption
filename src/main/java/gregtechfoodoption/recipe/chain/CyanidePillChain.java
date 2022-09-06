package gregtechfoodoption.recipe.chain;

import gregicality.science.api.unification.materials.GCYSMaterials;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtechfoodoption.GTFOMaterialHandler;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.items.materialitem.MetaPrefixItem;

import gregtech.common.items.MetaItems;

import static gregicality.science.api.unification.materials.GCYSMaterials.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtechfoodoption.GTFOMaterialHandler.*;
import static gregtechfoodoption.item.GTFOMetaItem.*;

public class CyanidePillChain {
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
        CANNER_RECIPES.recipeBuilder().EUt(120).duration(100)
                .inputs(GEL_CAPLET.getStackForm())
                .input(OrePrefix.dustSmall, SodiumCyanide)
                .outputs(CYANIDE_CAPLET.getStackForm())
                .buildAndRegister();

    }
}