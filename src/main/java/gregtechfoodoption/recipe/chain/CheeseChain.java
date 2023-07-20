package gregtechfoodoption.recipe.chain;

import gregtech.common.items.MetaItems;
import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.utils.GTFOUtils;
import net.minecraft.init.Items;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtechfoodoption.GTFOMaterialHandler.*;
import static gregtechfoodoption.item.GTFOMetaItem.*;
import static gregtechfoodoption.recipe.GTFORecipeMaps.SLICER_RECIPES;

public class CheeseChain {
    public static void init() {
        SLICER_RECIPES.recipeBuilder().EUt(24).duration(80)
                .input(Items.BEEF)
                .outputs(BEEF_SLICE.getStackForm(9))
                .notConsumable(SLICER_BLADE_STRIPES.getStackForm())
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(300)
                .inputs(BEEF_SLICE.getStackForm(4))
                .fluidInputs(SaltWater.getFluid(1000), AceticAcid.getFluid(100))
                .fluidOutputs(CrudeRennetSolution.getFluid(500))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(200)
                .fluidInputs(CrudeRennetSolution.getFluid(1), Milk.getFluid(3000))
                .outputs(CoagulatedMilkCurd.getItemStack())
                .fluidOutputs(Whey.getFluid(600))
                .buildAndRegister();
        SLICER_RECIPES.recipeBuilder().EUt(16).duration(40)
                .inputs(CoagulatedMilkCurd.getItemStack())
                .notConsumable(SLICER_BLADE_STRIPES.getStackForm())
                .outputs(CutCurd.getItemStack(64))
                .buildAndRegister();
        if (!GTFOConfig.gtfoChainsConfig.makeChainsHarder) {
            CANNER_RECIPES.recipeBuilder().EUt(4).duration(200)
                    .inputs(CutCurd.getItemStack(64), MetaItems.SHAPE_MOLD_BLOCK.getStackForm())
                    .outputs(CHEDDAR_CURD_MOLD.getStackForm())
                    .buildAndRegister();
        }
        CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(6).duration(2400)
                .inputs(CutCurd.getItemStack(64))
                .fluidInputs(Steam.getFluid(1000))
                .outputs(CookedCurd.getItemStack(64))
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder().EUt(20).duration(100)
                .input(dust, Salt)
                .inputs(CookedCurd.getItemStack(64))
                .outputs(SaltedCurd.getItemStack(64))
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder().EUt(4).duration(200)
                .inputs(SaltedCurd.getItemStack(32), MetaItems.SHAPE_MOLD_BLOCK.getStackForm())
                .outputs(CHEDDAR_CURD_MOLD.getStackForm())
                .buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().EUt(16).duration(6000)
                .inputs(CHEDDAR_CURD_MOLD.getStackForm())
                .outputs(AGED_CHEDDAR_MOLD.getStackForm())
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder().EUt(8).duration(40)
                .inputs(AGED_CHEDDAR_MOLD.getStackForm())
                .outputs(CHEDDAR_BLOCK.getStackForm(), MetaItems.SHAPE_MOLD_BLOCK.getStackForm())
                .buildAndRegister();
        SLICER_RECIPES.recipeBuilder().EUt(16).duration(80)
                .inputs(CHEDDAR_BLOCK.getStackForm())
                .notConsumable(SLICER_BLADE_FLAT.getStackForm())
                .outputs(CHEDDAR_SLICE.getStackForm(9))
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder().EUt(8).duration(40)
                .fluidInputs(ItalianBuffaloMilk.getFluid(730), Whey.getFluid(270))
                .fluidOutputs(ActivatedBuffaloMilk.getFluid(1000))
                .buildAndRegister();
        if (!GTFOConfig.gtfoChainsConfig.makeChainsHarder) {
            MIXER_RECIPES.recipeBuilder().EUt(8).duration(1200)
                    .fluidInputs(Milk.getFluid(6000), CrudeRennetSolution.getFluid(1))
                    .circuitMeta(2)
                    .outputs(LargeMozzarellaCurd.getItemStack())
                    .buildAndRegister();
        }
        MIXER_RECIPES.recipeBuilder().EUt(8).duration(120)
                .fluidInputs(ActivatedBuffaloMilk.getFluid(3000), CrudeRennetSolution.getFluid(1))
                .circuitMeta(1)
                .outputs(LargeMozzarellaCurd.getItemStack())
                .buildAndRegister();
        SLICER_RECIPES.recipeBuilder().EUt(12).duration(1000)
                .inputs(LargeMozzarellaCurd.getItemStack())
                .notConsumable(SLICER_BLADE_STRIPES.getStackForm())
                .outputs(SmallMozzarellaCurd.getItemStack(9))
                .buildAndRegister();
        THERMAL_CENTRIFUGE_RECIPES.recipeBuilder().EUt(30).duration(1000)
                .inputs(SmallMozzarellaCurd.getItemStack())
                .outputs(DriedMozzarellaCurd.getItemStack())
                .buildAndRegister();
        GTFOUtils.chemicalDehydratorProxy().recipeBuilder().EUt(16).duration(200)
                .inputs(DriedMozzarellaCurd.getItemStack())
                .outputs(SolidifiedMozzarellaCurd.getItemStack())
                .fluidOutputs(Whey.getFluid(30))
                .buildAndRegister();
        BENDER_RECIPES.recipeBuilder().EUt(16).duration(400)
                .inputs(SolidifiedMozzarellaCurd.getItemStack(5))
                .circuitMeta(1)
                .outputs(MOZZARELLA_BALL.getStackForm())
                .buildAndRegister();
        EXTRUDER_RECIPES.recipeBuilder().EUt(16).duration(400)
                .inputs(MOZZARELLA_BALL.getStackForm())
                .notConsumable(MetaItems.SHAPE_EXTRUDER_PLATE.getStackForm())
                .outputs(MOZZARELLA_SLICE.getStackForm(9))
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder().EUt(12).duration(40)
                .fluidInputs(Whey.getFluid(1000), SaltWater.getFluid(50))
                .fluidOutputs(WheySaltWaterMix.getFluid(1050))
                .buildAndRegister();
        FLUID_HEATER_RECIPES.recipeBuilder().EUt(30).duration(100)
                .fluidInputs(WheySaltWaterMix.getFluid(1000))
                .circuitMeta(1)
                .fluidOutputs(HeatedRicottaStarter.getFluid(1000))
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder().EUt(12).duration(40)
                .fluidInputs(Milk.getFluid(144), LemonExtract.getFluid(10))
                .fluidOutputs(AcidicMilkSolution.getFluid(144))
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder().EUt(12).duration(40)
                .fluidInputs(Milk.getFluid(144), CitricAcid.getFluid(1))
                .fluidOutputs(AcidicMilkSolution.getFluid(144))
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder().EUt(20).duration(60)
                .fluidInputs(HeatedRicottaStarter.getFluid(1000), AcidicMilkSolution.getFluid(144))
                .fluidOutputs(CoagulatingRicottaSolution.getFluid(1144))
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder().EUt(30).duration(160)
                .fluidInputs(CoagulatingRicottaSolution.getFluid(1144))
                .outputs(RICOTTA.getStackForm(2))
                .fluidOutputs(Whey.getFluid(856))
                .buildAndRegister();
        /*
        BIO_REACTOR_RECIPES.recipeBuilder().EUt(500).duration(360)
                .inputs(CLEAN_CULTURE.getStackForm())
                .input(dust, Calcite)
                .fluidInputs(FungalGrowthMedium.getFluid(1000))
                .outputs(PENICILLIUM_ROQUEFORTI_CULTURE.getStackForm())
                .buildAndRegister();
        BIO_REACTOR_RECIPES.recipeBuilder().EUt(500).duration(200)
                .inputs(PENICILLIUM_ROQUEFORTI_CULTURE.getStackForm())
                .fluidInputs(FungalGrowthMedium.getFluid(1000))
                .outputs(PenicilliumRoqueforti.getItemStack())
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .buildAndRegister();
        BIO_REACTOR_RECIPES.recipeBuilder().EUt(500).duration(50)
                .inputs(PenicilliumRoqueforti.getItemStack())
                .fluidInputs(FungalGrowthMedium.getFluid(250))
                .outputs(PenicilliumRoqueforti.getItemStack(2))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder().EUt(110).duration(120)
                .inputs(Yeast.getItemStack())
                .inputs(PenicilliumRoqueforti.getItemStack())
                .fluidInputs(CrudeRennetSolution.getFluid(250))
                .fluidOutputs(FungalRennetSolution.getFluid(250))
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder().EUt(16).duration(120)
                .fluidInputs(ActivatedBuffaloMilk.getFluid(3000), FungalRennetSolution.getFluid(3))
                .outputs(GorgonzolaCurd.getItemStack(12))
                .buildAndRegister();
        FORMING_PRESS_RECIPES.recipeBuilder().EUt(20).duration(75)
                .inputs(GorgonzolaCurd.getItemStack(20))
                .notConsumable(SHAPE_MOLD_CYLINDER)
                .outputs(GORGONZOLA_WHEEL.getStackForm())
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder().EUt(18).duration(65)
                .inputs(GORGONZOLA_WHEEL.getStackForm())
                .input(dust, Salt)
                .outputs(SALTED_GORGONZOLA_WHEEL.getStackForm())
                .buildAndRegister();
        RecipeUtils.chemicalDehydratorProxy().recipeBuilder().duration(460).EUt(24)
                .inputs(SALTED_GORGONZOLA_WHEEL.getStackForm())
                .outputs(SLIGHTLY_AGED_GORGONZOLA_WHEEL.getStackForm())
                .fluidOutputs(Whey.getFluid(35))
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().duration(90).EUt(32)
                .inputs(SLIGHTLY_AGED_GORGONZOLA_WHEEL.getStackForm())
                .notConsumable(stick, StainlessSteel)
                .outputs(PUNCTURED_GORGONZOLA_WHEEL.getStackForm())
                .buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().duration(3200).EUt(24)
                .inputs(PUNCTURED_GORGONZOLA_WHEEL.getStackForm())
                .outputs(FULLY_CURED_GORGONZOLA_WHEEL.getStackForm())
                .fluidInputs(ColdMoistAir.getFluid(500))
                .buildAndRegister();
        SLICER_RECIPES.recipeBuilder().duration(160).EUt(28)
                .inputs(FULLY_CURED_GORGONZOLA_WHEEL.getStackForm())
                .notConsumable(SLICER_BLADE_OCTAGONAL)
                .outputs(GORGONZOLA_TRIANGULAR_SLICE.getStackForm(16))
                .buildAndRegister();
         */

        LATHE_RECIPES.recipeBuilder().EUt(72).duration(360)
                .input(block, StainlessSteel)
                .outputs(CHEESE_ROLL_FORM.getStackForm())
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder().EUt(24).duration(100)
                .fluidInputs(Milk.getFluid(1000))
                .fluidOutputs(UnpasteurizedSkimmedMilk.getFluid(800))
                .buildAndRegister();
        if (!GTFOConfig.gtfoChainsConfig.makeChainsHarder) {
            MIXER_RECIPES.recipeBuilder().EUt(16).duration(500)
                    .input(dustTiny, Copper)
                    .fluidInputs(UnpasteurizedSkimmedMilk.getFluid(1000))
                    .fluidOutputs(ParmigianoReggianoStarter.getFluid(1000))
                    .buildAndRegister();
        }
        MIXER_RECIPES.recipeBuilder().EUt(16).duration(40)
                .input(dustTiny, Copper)
                .fluidInputs(UnpasteurizedSkimmedMilk.getFluid(500), Whey.getFluid(500))
                .fluidOutputs(ParmigianoReggianoStarter.getFluid(1000))
                .buildAndRegister();
        if (!GTFOConfig.gtfoChainsConfig.makeChainsHarder) {
            FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(16).duration(150)
                    .fluidInputs(ParmigianoReggianoStarter.getFluid(1000))
                    .inputs(CHEESE_ROLL_FORM.getStackForm())
                    .outputs(BRINED_PARMIGIANO_ROLL.getStackForm())
                    .buildAndRegister();
        }
        FLUID_HEATER_RECIPES.recipeBuilder().EUt(48).duration(300)
                .fluidInputs(ParmigianoReggianoStarter.getFluid(1000))
                .circuitMeta(1)
                .fluidOutputs(CurdlingParmigianoReggiano.getFluid(2000))
                .buildAndRegister();
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(16).duration(150)
                .fluidInputs(CurdlingParmigianoReggiano.getFluid(1000))
                .inputs(CHEESE_ROLL_FORM.getStackForm())
                .outputs(CURDLING_PARMIGIANO.getStackForm())
                .buildAndRegister();
        CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(8).duration(800)
                .inputs(CURDLING_PARMIGIANO.getStackForm())
                .fluidInputs(SaltWater.getFluid(100))
                .outputs(BRINED_PARMIGIANO.getStackForm())
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder().EUt(8).duration(40)
                .inputs(BRINED_PARMIGIANO.getStackForm())
                .outputs(BRINED_PARMIGIANO_ROLL.getStackForm(), CHEESE_ROLL_FORM.getStackForm())
                .buildAndRegister();
        FERMENTING_RECIPES.recipeBuilder().EUt(2).duration(36000)
                .inputs(BRINED_PARMIGIANO_ROLL.getStackForm(64))
                .fluidInputs(Air.getFluid(10000))
                .outputs(AGED_PARMIGIANO_ROLL.getStackForm(64))
                .fluidOutputs(Air.getFluid(9000))
                .buildAndRegister();
        MACERATOR_RECIPES.recipeBuilder().EUt(8).duration(120)
                .inputs(AGED_PARMIGIANO_ROLL.getStackForm())
                .outputs(ShreddedParmesan.getItemStack(8))
                .buildAndRegister();
    }
}
