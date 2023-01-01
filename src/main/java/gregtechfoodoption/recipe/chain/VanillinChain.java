package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.GTRecipeHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtechfoodoption.GTFOMaterialHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class VanillinChain {
    public static void init() {
        GTRecipeHandler.removeRecipesByInputs(RecipeMaps.DISTILLATION_RECIPES, new ItemStack[]{}, new FluidStack[]{Materials.Creosote.getFluid(24)});
        RecipeMaps.DISTILLATION_RECIPES.recipeBuilder().EUt(24).duration(32)
                .fluidInputs(Materials.Creosote.getFluid(24))
                .fluidOutputs(Materials.Lubricant.getFluid(12), GTFOMaterialHandler.Guaiacol.getFluid(1))
                .buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(120) // C2H4 + O -> C2H4O
                .notConsumable(OrePrefix.dust, Materials.Palladium)
                .fluidInputs(Materials.Ethylene.getFluid(1000), Materials.Oxygen.getFluid(1000))
                .fluidOutputs(GTFOMaterialHandler.Acetaldehyde.getFluid(1000))
                .buildAndRegister();
        RecipeMaps.LARGE_CHEMICAL_RECIPES.recipeBuilder().EUt(60).duration(120) // 2C2H4O + 2HNO3 -> 2C2H2O2 + N2O + 3H2O
                .fluidInputs(GTFOMaterialHandler.Acetaldehyde.getFluid(2000), Materials.NitricAcid.getFluid(2000))
                .fluidOutputs(GTFOMaterialHandler.Glyoxal.getFluid(2000), Materials.NitrogenDioxide.getFluid(1000), Materials.Water.getFluid(3000))
                .buildAndRegister();
        RecipeMaps.LARGE_CHEMICAL_RECIPES.recipeBuilder().EUt(240).duration(30) // 2C2H2O2 + 2HNO3 -> 2C2H2O3 + H2O + NO2 + NO
                .fluidInputs(GTFOMaterialHandler.Glyoxal.getFluid(2000), Materials.NitricAcid.getFluid(2000))
                .fluidOutputs(GTFOMaterialHandler.GlyoxylicAcid.getFluid(2000), Materials.Water.getFluid(1000), Materials.NitrogenDioxide.getFluid(1000), Materials.NitrousOxide.getFluid(1000))
                .buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().EUt(120).duration(160) // C7H8O2 + C2H2O3 -> C9H10O5
                .notConsumable(OrePrefix.dust, Materials.SodiumHydroxide)
                .fluidInputs(GTFOMaterialHandler.Guaiacol.getFluid(1000), GTFOMaterialHandler.GlyoxylicAcid.getFluid(1000))
                .outputs(GTFOMaterialHandler.VanillylmandelicAcid.getItemStack(24))
                .buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().EUt(120).duration(160) // C7H8O2 + C2H2O3 -> C9H10O5
                .notConsumable(OrePrefix.dust, Materials.SodiumHydroxide)
                .fluidInputs(GTFOMaterialHandler.Guaiacol.getFluid(1000), GTFOMaterialHandler.GlyoxylicAcid.getFluid(1000))
                .outputs(GTFOMaterialHandler.VanillylmandelicAcid.getItemStack(24))
                .buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().EUt(120).duration(160) // C9H10O5 + O -> C9H8O5 + H2O
                .notConsumable(OrePrefix.dust, Materials.SodiumHydroxide)
                .inputs(GTFOMaterialHandler.VanillylmandelicAcid.getItemStack(24))
                .fluidInputs(Materials.Oxygen.getFluid(1000))
                .outputs(GTFOMaterialHandler.VanilglycolicAcid.getItemStack(22))
                .fluidOutputs(Materials.Water.getFluid(1000))
                .buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().EUt(120).duration(240) // C9H8O5 (H+) -> C8H8O3 + CO2
                .inputs(GTFOMaterialHandler.VanilglycolicAcid.getItemStack(22))
                .fluidInputs(Materials.HydrochloricAcid.getFluid(1000))
                .outputs(GTFOMaterialHandler.Vanillin.getItemStack(19))
                .fluidOutputs(Materials.DilutedHydrochloricAcid.getFluid(1000), Materials.CarbonDioxide.getFluid(1000))
                .buildAndRegister();
    }
}
