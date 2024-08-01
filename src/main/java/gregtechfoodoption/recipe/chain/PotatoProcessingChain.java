package gregtechfoodoption.recipe.chain;

import gregtech.api.unification.material.Materials;
import gregtechfoodoption.GTFOMaterialHandler;
import gregtech.api.recipes.ModHandler;
import gregtechfoodoption.utils.GTFOUtils;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static gregtechfoodoption.item.GTFOMetaItem.*;
import static gregtechfoodoption.recipe.GTFORecipeMaps.SLICER_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.foil;

public class PotatoProcessingChain {
    public static void init() {
        SLICER_RECIPES.recipeBuilder().EUt(8).duration(40)
                .input(Items.POTATO)
                .fluidInputs(Water.getFluid(500))
                .notConsumable(SLICER_BLADE_FLAT.getStackForm())
                .outputs(PEELED_POTATO.getStackForm())
                //.fluidOutputs(GTFOMaterialHandler.StarchFilledWater.getFluid(500))
                .buildAndRegister();
        SLICER_RECIPES.recipeBuilder().EUt(20).duration(80)
                .inputs(PEELED_POTATO.getStackForm())
                .fluidInputs(Water.getFluid(200))
                .notConsumable(SLICER_BLADE_FLAT.getStackForm())
                .outputs(POTATO_SLICE.getStackForm(10))
                //.fluidOutputs(GTFOMaterialHandler.StarchFilledWater.getFluid(200))
                .buildAndRegister();
        GTFOUtils.chemicalDehydratorProxy().recipeBuilder().EUt(16).duration(20)
                .fluidInputs(GTFOMaterialHandler.StarchFilledWater.getFluid(1000))
                .outputs(GTFOMaterialHandler.PotatoStarch.getItemStack())
                .fluidOutputs(Water.getFluid(900))
                .buildAndRegister();
        SLICER_RECIPES.recipeBuilder().EUt(20).duration(80)
                .inputs(PEELED_POTATO.getStackForm())
                .notConsumable(SLICER_BLADE_STRIPES.getStackForm())
                .outputs(POTATO_STRIP.getStackForm(10))
                .buildAndRegister();
        GTFOUtils.chemicalDehydratorProxy().recipeBuilder().EUt(30).duration(400)
                .inputs(POTATO_SLICE.getStackForm(40))
                .fluidInputs(GTFOMaterialHandler.FryingOil.getFluid(500))
                .outputs(FRIED_POTATO_SLICE.getStackForm(38))
                .fluidOutputs(GTFOMaterialHandler.FryingOil.getFluid(450))
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder().EUt(16).duration(100)
                .input(foil, Tin)
                .inputs(FRIED_POTATO_SLICE.getStackForm(20))
                .outputs(PARTIALLY_FILLED_CHIP_BAG.getStackForm())
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder().EUt(16).duration(120)
                .inputs(PARTIALLY_FILLED_CHIP_BAG.getStackForm())
                .fluidInputs(Nitrogen.getFluid(500))
                .outputs(SYALS.getStackForm())
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder().EUt(16).duration(100)
                .inputs(FRIED_POTATO_SLICE.getStackForm(40))
                .input(foil, Steel)
                .outputs(BAG_OF_CHIPS.getStackForm())
                .EUt(16)
                .duration(100)
                .buildAndRegister();
        CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(30).duration(400)
                .inputs(POTATO_SLICE.getStackForm(40))
                .fluidInputs(GTFOMaterialHandler.FryingOil.getFluid(500))
                .outputs(BATCH_FRIED_POTATO_SLICE.getStackForm(38))
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder().EUt(50).duration(200)
                .inputs(BATCH_FRIED_POTATO_SLICE.getStackForm(5))
                .outputs(OILY_POTATO_SLICE.getStackForm(5))
                .fluidOutputs(GTFOMaterialHandler.FryingOil.getFluid(20))
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder().EUt(16).duration(100)
                .inputs(OILY_POTATO_SLICE.getStackForm(40))
                .input(foil, Aluminium)
                .outputs(KETTLE_FRIED_CHIPS.getStackForm())
                .EUt(16)
                .duration(100)
                .buildAndRegister();
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().EUt(160).duration(500)
                .inputs(BATCH_FRIED_POTATO_SLICE.getStackForm(5))
                .outputs(HOT_POTATO_SLICE.getStackForm(5))
                .buildAndRegister();
        GTFOUtils.chemicalDehydratorProxy().recipeBuilder().EUt(30).duration(400)
                .inputs(HOT_POTATO_SLICE.getStackForm(10))
                .outputs(REDUCED_FAT_POTATO_SLICE.getStackForm(10))
                .fluidOutputs(GTFOMaterialHandler.FryingOil.getFluid(60))
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder().EUt(16).duration(100)
                .inputs(REDUCED_FAT_POTATO_SLICE.getStackForm(40))
                .input(foil, StainlessSteel)
                .outputs(REDUCED_FAT_CHIPS.getStackForm())
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder().EUt(64).duration(100)
                .fluidInputs(Naquadah.getFluid(10))
                .inputs(REDUCED_FAT_POTATO_SLICE.getStackForm(40))
                .input(foil, RhodiumPlatedPalladium)
                .outputs(NAQUADAH_CHIPS.getStackForm())
                .buildAndRegister();
        CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(60).duration(1000)
                .inputs(POTATO_STRIP.getStackForm(40))
                .fluidInputs(GTFOMaterialHandler.FryingOil.getFluid(700))
                .outputs(BLANCHED_POTATO_STRIP.getStackForm(40))
                .buildAndRegister();
        CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(60).duration(1000)
                .inputs(BLANCHED_POTATO_STRIP.getStackForm(40))
                .fluidInputs(GTFOMaterialHandler.HotFryingOil.getFluid(500))
                .outputs(FRIED_POTATO_STRIP.getStackForm(40))
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder().EUt(10).duration(160)
                .inputs(FRIED_POTATO_STRIP.getStackForm(64))
                .inputs(PAPER_BAG.getStackForm())
                .outputs(FRENCH_FRIES.getStackForm())
                .buildAndRegister();
        GTFOUtils.chemicalDehydratorProxy().recipeBuilder().EUt(30).duration(180)
                .inputs(USED_PAPER_BAG.getStackForm())
                .output(dust, Paper, 3)
                .chancedOutput(GTFOMaterialHandler.PotatoStarch.getItemStack(), 2000, 150)
                .fluidOutputs(SeedOil.getFluid(300))
                .buildAndRegister();
        ModHandler.addShapelessRecipe("potato_on_a_stick", POTATO_ON_A_STICK.getStackForm(), new ItemStack(Items.BAKED_POTATO), new ItemStack(Items.STICK));
        ASSEMBLER_RECIPES.recipeBuilder().EUt(4).duration(5)
                .input(Items.STICK)
                .input(Items.BAKED_POTATO)
                .outputs(POTATO_ON_A_STICK.getStackForm())
                .buildAndRegister();
    }
}
