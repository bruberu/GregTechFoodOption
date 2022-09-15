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

public class DyeChain {
    public static void init() {
        ARC_FURNACE_RECIPES.recipeBuilder().EUt(30).duration(100)
                .input(dust, Arsenic)
                .fluidInputs(Oxygen.getFluid(3000))
                .outputs(ArsenicTrioxide.get(5))
                .buildAndRegister();

        // $stoik As2O3 + Na2CO3 -> 2NaAsO2 + CO2
        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(160)
                .inputs(ArsenicTrioxide.get(5))
                .fluidInputs(SodiumCarbonateSolution.getFluid(1000))
                .fluidOutputs(SodiumArseniteSolution.getFluid(2000), CarbonDioxide.getFluid(1000))
                .buildAndRegister();

        // $stoik NaAsO2 + CuSO4 + NaOH -> CuAsHO3 + Na2SO4
        CHEMICAL_RECIPES.recipeBuilder().EUt(24).duration(80)
                .input(dust, SodiumHydroxide, 3)
                .fluidInputs(SodiumArseniteSolution.getFluid(1000), GTFOMaterialHandler.BlueVitriol.getFluid(1000))
                .outputs(CupricHydrogenArsenite.getItemStack(6))
                .fluidOutputs(CarbonDioxide.getFluid(1000), Water.getFluid(1000))
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(24).duration(60)
                .inputs(CupricHydrogenArsenite.getItemStack(), MetaBlocks.TRANSPARENT_CASING.getItemVariant(BlockGlassCasing.CasingType.TEMPERED_GLASS))
                .outputs(GTFOMetaBlocks.GTFO_GLASS_CASING.getItemVariant(GTFOGlassCasing.CasingType.GREENHOUSE_GLASS))
                .buildAndRegister();
    }
}
