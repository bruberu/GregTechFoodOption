package com.bruberu.gregtechfoodoption.recipe.chain;

import gregtech.api.unification.OreDictUnifier;
import gregtech.common.items.MetaItems;
import net.minecraft.init.Items;

import static com.bruberu.gregtechfoodoption.GTFOMaterialHandler.*;
import static com.bruberu.gregtechfoodoption.item.GTFOMetaItem.*;
import static com.bruberu.gregtechfoodoption.item.GTFOMetaItem.RICOTTA;
import static com.bruberu.gregtechfoodoption.recipe.GTFORecipeMaps.SLICER_RECIPES;
import static gregicadditions.recipes.GARecipeMaps.CHEMICAL_DEHYDRATOR_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class CheeseChain {
    public static void init() {
        SLICER_RECIPES.recipeBuilder().EUt(24).duration(80)
                .input(Items.BEEF)
                .outputs(BEEF_SLICE.getStackForm(9))
                .notConsumable(SLICER_BLADE_STRIPES.getStackForm())
                .buildAndRegister();
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(8).duration(300)
                .inputs(BEEF_SLICE.getStackForm(4))
                .fluidInputs(SaltWater.getFluid(1000), AceticAcid.getFluid(100))
                .fluidOutputs(CrudeRennetSolution.getFluid(500))
                .buildAndRegister();
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(30).duration(200)
                .fluidInputs(CrudeRennetSolution.getFluid(1), Milk.getFluid(3000))
                .outputs(CoagulatedMilkCurd.getItemStack())
                .fluidOutputs(Whey.getFluid(600))
                .buildAndRegister();
        SLICER_RECIPES.recipeBuilder().EUt(16).duration(40)
                .inputs(CoagulatedMilkCurd.getItemStack())
                .notConsumable(SLICER_BLADE_STRIPES.getStackForm())
                .outputs(CutCurd.getItemStack(64))
                .buildAndRegister();
        CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(6).duration(2400)
                .inputs(CutCurd.getItemStack(64))
                .fluidInputs(Steam.getFluid(1000))
                .outputs(CookedCurd.getItemStack(60))
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder().EUt(20).duration(100)
                .inputs(CookedCurd.getItemStack(64), OreDictUnifier.get(dust, Salt))
                .outputs(SaltedCurd.getItemStack(64))
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder().EUt(4).duration(200)
                .inputs(SaltedCurd.getItemStack(64), MetaItems.SHAPE_MOLD_BLOCK.getStackForm())
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
        MIXER_RECIPES.recipeBuilder().EUt(8).duration(120)
                .fluidInputs(ActivatedBuffaloMilk.getFluid(3000), CrudeRennetSolution.getFluid(1))
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
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(16).duration(200)
                .inputs(DriedMozzarellaCurd.getItemStack())
                .outputs(SolidifiedMozzarellaCurd.getItemStack())
                .fluidOutputs(Whey.getFluid(30))
                .buildAndRegister();
        BENDER_RECIPES.recipeBuilder().EUt(16).duration(400)
                .inputs(SolidifiedMozzarellaCurd.getItemStack(5))
                .circuitMeta(0)
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
                .circuitMeta(0)
                .fluidOutputs(HeatedRicottaStarter.getFluid(1000))
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder().EUt(12).duration(40)
                .fluidInputs(Milk.getFluid(144), LemonExtract.getFluid(1))
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



    }
}
