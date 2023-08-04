package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;

import static gregtechfoodoption.GTFOMaterialHandler.LithiumCarbonate;
import static gregtechfoodoption.GTFOMaterialHandler.LithiumOxide;

public class LithiumChain {
    public static void init() {
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, Materials.Lithium, 2)
                .fluidInputs(Materials.Oxygen.getFluid(1000))
                .outputs(LithiumOxide.getItemStack(3))
                .EUt(60)
                .duration(60)
                .buildAndRegister();

        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder()
                .inputs(LithiumOxide.getItemStack(3))
                .fluidInputs(Materials.CarbonDioxide.getFluid(1000))
                .outputs(LithiumCarbonate.getItemStack(6))
                .EUt(16)
                .duration(140)
                .buildAndRegister();
    }
}
