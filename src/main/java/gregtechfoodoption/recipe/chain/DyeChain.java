package gregtechfoodoption.recipe.chain;

import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtechfoodoption.GTFOMaterialHandler;
import gregtechfoodoption.block.GTFOGlassCasing;
import gregtechfoodoption.block.GTFOMetaBlocks;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtechfoodoption.GTFOMaterialHandler.*;
import static gregtechfoodoption.GTFOMaterialHandler.ArsenicTrioxide;

// It's alive...
public class DyeChain {
    public static void init() {
        ARC_FURNACE_RECIPES.recipeBuilder().EUt(30).duration(100)
                .input(dust, Arsenic, 2)
                .fluidInputs(Oxygen.getFluid(3000))
                .outputs(ArsenicTrioxide.get(5))
                .buildAndRegister();

        // $stoik As2O3 + Na2CO3 + H2O-> 2NaAsO2(1/2H2O) + CO2
        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(160)
                .inputs(ArsenicTrioxide.get(5))
                .fluidInputs(SodiumCarbonateSolution.getFluid(1000))
                .fluidOutputs(SodiumArseniteSolution.getFluid(2000), CarbonDioxide.getFluid(1000))
                .buildAndRegister();

        // $stoik NaAsO2 (+ 1/2H2O) + CuSO4 + NaOH -> CuAsHO3 + Na2SO4 (+ 1/2H2O)
        CHEMICAL_RECIPES.recipeBuilder().EUt(24).duration(80)
                .input(dust, SodiumHydroxide, 3)
                .fluidInputs(SodiumArseniteSolution.getFluid(1000), GTFOMaterialHandler.BlueVitriol.getFluid(1000))
                .outputs(CupricHydrogenArsenite.getItemStack(6))
                .output(dust, SodiumSulfate, 7)
                .fluidOutputs(Water.getFluid(500))
                .buildAndRegister();

        // $stoik Cu + H2SO4 -> CuSO4 + H2
        CHEMICAL_RECIPES.recipeBuilder().EUt(16).duration(160)
                .input(dust, Copper)
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(GTFOMaterialHandler.BlueVitriol.getFluid(1000), Hydrogen.getFluid(2000))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(24).duration(60)
                .inputs(CupricHydrogenArsenite.getItemStack(), MetaBlocks.TRANSPARENT_CASING.getItemVariant(BlockGlassCasing.CasingType.TEMPERED_GLASS))
                .outputs(GTFOMetaBlocks.GTFO_GLASS_CASING.getItemVariant(GTFOGlassCasing.CasingType.GREENHOUSE_GLASS))
                .buildAndRegister();
    }
}
