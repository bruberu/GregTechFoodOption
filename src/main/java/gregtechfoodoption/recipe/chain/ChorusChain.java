package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.ore.OrePrefix;
import gregtechfoodoption.GTFOMaterialHandler;
import gregtechfoodoption.item.GTFOMetaItem;
import gregtechfoodoption.recipe.GTFORecipeMaps;
import net.minecraft.init.Items;

import static gregtech.api.GTValues.*;
import static gregtech.api.recipes.RecipeMaps.CANNER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtechfoodoption.item.GTFOMetaItem.*;

public class ChorusChain {
    public static void init() {
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder().EUt(16).duration(200)
                .input(Items.CHORUS_FRUIT)
                .fluidOutputs(GTFOMaterialHandler.ChorusJuice.getFluid(50))
                .buildAndRegister();
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder().EUt(16).duration(100)
                .input(Items.CHORUS_FRUIT_POPPED)
                .fluidOutputs(GTFOMaterialHandler.ChorusJuice.getFluid(100))
                .buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(7).duration(200)
                .input(OrePrefix.dust, EnderPearl)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(GTFOMaterialHandler.EnderPearlSolution.getFluid(1000))
                .buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().EUt(30).duration(500)
                .fluidInputs(GTFOMaterialHandler.EnderPearlSolution.getFluid(1000))
                .fluidOutputs(GTFOMaterialHandler.EnderSugarSolution.getFluid(1000))
                .chancedOutput(OrePrefix.dust, Potassium, 3, 3000, 1000)
                .chancedFluidOutput(Nitrogen.getFluid(3000), 1000, 1500)
                .buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(VA[HV]).duration(400)
                .fluidInputs(GTFOMaterialHandler.EnderSugarSolution.getFluid(1000))
                .fluidInputs(GTFOMaterialHandler.FermentedChorusJuice.getFluid(1000))
                .fluidOutputs(GTFOMaterialHandler.VibrantExtract.getFluid(1000))
                .buildAndRegister();

        RecipeMaps.FERMENTING_RECIPES.recipeBuilder().EUt(2).duration(1000)
                .fluidInputs(GTFOMaterialHandler.LacticAcidBacteria.getFluid(1000))
                .input(Items.CHORUS_FRUIT)
                .outputs(GTFOMetaItem.FERMENTED_CHORUS.getStackForm())
                .buildAndRegister();
        RecipeMaps.FERMENTING_RECIPES.recipeBuilder().EUt(2).duration(800)
                .fluidInputs(GTFOMaterialHandler.LacticAcidBacteria.getFluid(1000))
                .input(Items.CHORUS_FRUIT_POPPED)
                .outputs(GTFOMetaItem.FERMENTED_CHORUS.getStackForm())
                .buildAndRegister();

        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder().EUt(16).duration(200)
                .inputs(GTFOMetaItem.FERMENTED_CHORUS.getStackForm())
                .fluidOutputs(GTFOMaterialHandler.FermentedChorusJuice.getFluid(50))
                .buildAndRegister();
        GTFORecipeMaps.CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(300)
                .inputs(PIE_CRUST.getStackForm())
                .fluidInputs(GTFOMaterialHandler.FermentedChorusJuice.getFluid(1000))
                .outputs(FERMENTED_CHORUS_PIE.getStackForm())
                .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(60).duration(20)
                .fluidInputs(GTFOMaterialHandler.FermentedChorusJuice.getFluid(1000))
                .input(Items.SUGAR, 9)
                .fluidInputs(GTFOMaterialHandler.CarbonatedWater.getFluid(1000))
                .fluidOutputs(GTFOMaterialHandler.Antaf.getFluid(2000))
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder()
                .fluidInputs(GTFOMaterialHandler.Antaf.getFluid(500))
                .inputs(PLASTIC_BOTTLE.getStackForm())
                .outputs(ANTAF.getStackForm())
                .EUt(30)
                .duration(20)
                .buildAndRegister();
    }
}
