package com.bruberu.gregtechfoodoption.recipe.chain;

import com.bruberu.gregtechfoodoption.block.GTFOMetaBlocks;
import com.bruberu.gregtechfoodoption.block.GTFOMetalCasing;
import com.bruberu.gregtechfoodoption.block.GTFOOtherCasing;
import gregicadditions.GAValues;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;

import static gregicadditions.GAValues.*;

import static gregicadditions.GAEnums.GAOrePrefix.*;
import static gregicadditions.GAMaterials.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static com.bruberu.gregtechfoodoption.item.GTFOMetaItem.*;
import static com.bruberu.gregtechfoodoption.machines.GTFOTileEntities.*;
import static com.bruberu.gregtechfoodoption.GTFOMaterialHandler.*;
import static com.bruberu.gregtechfoodoption.recipe.GTFORecipeMaps.CULINARY_GENERATOR_RECIPES;

import static com.bruberu.gregtechfoodoption.block.GTFOMetaBlocks.GTFO_OTHER_CASING;
import static com.bruberu.gregtechfoodoption.client.GTFOClientHandler.BIOCHEMICAL;

public class CulinaryGeneratorChain {
    public static void init() {

        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(AceticAcid.getFluid(1000), Ammonia.getFluid(1000), Chlorine.getFluid(2000))
                .notConsumable(AceticAnhydride.getFluid(1))
                .fluidOutputs(Glycine.getFluid(1000), HydrochloricAcid.getFluid(2000))
                .EUt(480)
                .duration(1000)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Glycine.getFluid(1000), Methanol.getFluid(1000), HydrogenSulfide.getFluid(1000), Bromine.getFluid(2000))
                .outputs(Cysteine.getItemStack(14))
                .fluidOutputs(HydrobromicAcid.getFluid(2000), Water.getFluid(1000))
                .EUt(480)
                .duration(600)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(Cysteine.getItemStack(14))
                .notConsumable(Dimethylaminopyridine.getItemStack())
                .outputs(Cysteamine.getItemStack(11))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(480)
                .duration(600)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(SodiumHydride.getItemStack(8))
                .fluidInputs(BoricAcid.getFluid(1000), Ethanol.getFluid(3000))
                .notConsumable(SulfuricAcid.getFluid(1))
                .outputs(SodiumBorohydride.getItemStack(6),SodiumEthoxide.getItemStack(27))
                .fluidOutputs(Water.getFluid(3000))
                .EUt(480)
                .duration(600)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .inputs(SodiumBorohydride.getItemStack(6))
                .fluidInputs(Propene.getFluid(1000), CarbonMonoxde.getFluid(1000), HydrofluoricAcid.getFluid(3000), Water.getFluid(1000))
                .output(dust, SodiumHydroxide, 3)
                .fluidOutputs(Isobutyraldehyde.getFluid(1000), BoronFluoride.getFluid(1000), Hydrogen.getFluid(6000))
                .EUt(480)
                .duration(600)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Isobutyraldehyde.getFluid(1000), Formaldehyde.getFluid(1000), HydrogenCyanide.getFluid(1000), Water.getFluid(1000))
                .notConsumable(AceticAcid.getFluid(1))
                .fluidOutputs(Pantolactone.getFluid(1000), Ammonia.getFluid(1000))
                .EUt(480)
                .duration(600)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .inputs(BariumHydroxide.getItemStack(5))
                .fluidInputs(Pantolactone.getFluid(1000), AcryloNitrile.getFluid(1000), CarbonDioxide.getFluid(1000), Water.getFluid(1000))
                .notConsumable(CamphorSulfate.getItemStack())
                .notConsumable(Ammonia.getFluid(1))
                .outputs(BariumCarbonate.getItemStack(4))
                .fluidOutputs(PantothenicAcid.getFluid(1000))
                .EUt(480)
                .duration(600)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, ChromiumTrioxide, 8)
                .input(dust, SodiumHydroxide, 3)
                .inputs(BetaPinene.getItemStack(78))
                .fluidInputs(AceticAcid.getFluid(3000), SulfuricAcid.getFluid(3000))
                .outputs(CamphorSulfate.getItemStack(93), ChromiumIIIOxide.getItemStack(5))
                .fluidOutputs(SodiumAcetate.getFluid(3000), Water.getFluid(6000))
                .EUt(480)
                .duration(600)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(AdenosineTriphosphate.getItemStack(1), Cysteamine.getItemStack(11))
                .fluidInputs(PantothenicAcid.getFluid(1000))
                .outputs(CoenzymeA.getItemStack(1))
                .fluidOutputs(Water.getFluid(4000))
                .EUt(480)
                .duration(600)
                .buildAndRegister();
        VACUUM_RECIPES.recipeBuilder()
                .input("treeLeaves", 64)
                .fluidInputs(Nitrogen.getFluid(5000))
                .outputs(FrozenLeaves.getItemStack(1))
                .fluidOutputs(LiquidNitrogen.getFluid(5000))
                .fluidOutputs()
                .EUt(480)
                .duration(40)
                .buildAndRegister();
        MACERATOR_RECIPES.recipeBuilder()
                .inputs(FrozenLeaves.getItemStack(1))
                .outputs(PulverizedFrozenLeaves.getItemStack(1))
                .EUt(8)
                .duration(30)
                .buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder()
                .inputs(PulverizedFrozenLeaves.getItemStack(1))
                .fluidInputs(ChlorophyllExtractionSolution.getFluid(1000))
                .outputs(ImpureChlorophyll.getItemStack(1))
                .EUt(128)
                .duration(200)
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder()
                .inputs(ImpureChlorophyll.getItemStack(4))
                .fluidInputs(ChlorophyllExtractionSolution.getFluid(1000))
                .outputs(Chlorophyll.getItemStack(1))
                .EUt(256)
                .duration(100)
                .buildAndRegister();
        LARGE_MIXER_RECIPES.recipeBuilder()
                .input(dust, Magnesia, 2)
                .input(dust, SodiumSulfate, 7)
                .input(dust, Salt, 2)
                .fluidInputs(Kerosene.getFluid(1000), Diethylether.getFluid(1000), Water.getFluid(1000))
                .fluidOutputs(ChlorophyllExtractionSolution.getFluid(5000))
                .EUt(32)
                .duration(400)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, SodiumHydroxide, 9)
                .fluidInputs(PhosphoricAcid.getFluid(1000))
                .outputs(TrisodiumPhosphate.getItemStack(8))
                .fluidOutputs(Water.getFluid(3000))
                .EUt(32)
                .duration(400)
                .buildAndRegister();
        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(BacillusSubtilis.getItemStack(1), Glucose.getItemStack(24))
                .fluidInputs(Ammonia.getFluid(10))
                .outputs(Ribose.getItemStack(20))
                .EUt(120)
                .duration(3200)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(Ribose.getItemStack(24), TrisodiumPhosphate.getItemStack(8))
                .notConsumable(BenzoylChloride.getFluid(1))
                .outputs(RibosePhosphate.getItemStack(26))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(480)
                .duration(600)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Formamide.getFluid(5000))
                .notConsumable(UVA_HALIDE_LAMP.getStackForm())
                .notConsumable(PhosphorylChloride.getFluid(1))
                .outputs(Adenine.getItemStack(15))
                .fluidOutputs(Water.getFluid(5000))
                .EUt(480)
                .duration(600)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(Adenine.getItemStack(15), RibosePhosphate.getItemStack(26))
                .outputs(AdenosineMonophosphate.getItemStack(1))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(8192)
                .duration(100)
                .buildAndRegister();
        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(AdenosineMonophosphate.getItemStack(1), TrisodiumPhosphate.getItemStack(8))
                .fluidInputs(HydrochloricAcid.getFluid(3000))
                .notConsumable(Chlorophyll.getItemStack())
                .outputs(AdenosineDiphosphate.getItemStack(1))
                .output(dust, Salt, 6)
                .fluidOutputs(Water.getFluid(2000))
                .EUt(8192)
                .duration(100)
                .buildAndRegister();
        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(AdenosineDiphosphate.getItemStack(1), TrisodiumPhosphate.getItemStack(8))
                .fluidInputs(HydrochloricAcid.getFluid(3000))
                .notConsumable(Chlorophyll.getItemStack())
                .outputs(AdenosineTriphosphate.getItemStack(1))
                .output(dust, Salt, 6)
                .fluidOutputs(Water.getFluid(2000))
                .EUt(8192)
                .duration(100)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Pyridine.getFluid(1000), SodiumCyanide.getFluid(1000), HydrobromicAcid.getFluid(1000))
                .outputs(Nicotinonitrile.getItemStack(12), SodiumBromide.getItemStack(2))
                .fluidOutputs(Hydrogen.getFluid(2000))
                .EUt(480)
                .duration(600)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(Nicotinonitrile.getItemStack(12))
                .fluidInputs(Water.getFluid(2000))
                .outputs(Niacin.getItemStack(14))
                .fluidOutputs(Ammonia.getFluid(1000))
                .EUt(64)
                .duration(200)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(Niacin.getItemStack(14))
                .fluidInputs(Ammonia.getFluid(1000))
                .outputs(Nicotinamide.getItemStack(15))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(64)
                .duration(200)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(Nicotinamide.getItemStack(15), RibosePhosphate.getItemStack(26))
                .outputs(NicotinamideMononucleotide.getItemStack(1))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(480)
                .duration(600)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(NicotinamideMononucleotide.getItemStack(1), AdenosineMonophosphate.getItemStack(1))
                .outputs(NicotinamideAdenineDinucleotide.getItemStack(1))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(480)
                .duration(600)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Nitrotoluene.getFluid(1000), Formaldehyde.getFluid(1000), Hydrogen.getFluid(8000))
                .notConsumable(Dimethylformamide.getFluid(1))
                .fluidOutputs(Xylidine.getFluid(1000), Water.getFluid(3000))
                .EUt(480)
                .duration(600)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .inputs(Ribose.getItemStack(20), SodiumAluminiumHydride.getItemStack(6), SodiumHydride.getItemStack(4))
                .fluidInputs(Xylidine.getFluid(1000), Aniline.getFluid(1000), HydrochloricAcid.getFluid(2000))
                .outputs(AzoanilineRibosylxylidine.getItemStack(51), AluminiumHydride.getItemStack(4))
                .output(dust, Salt, 4)
                .fluidOutputs(Water.getFluid(3000))
                .EUt(480)
                .duration(600)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, SodiumHydroxide, 6)
                .fluidInputs(SodiumAcetate.getFluid(1000), HydrogenCyanide.getFluid(1000), Chlorine.getFluid(2000), HydrochloricAcid.getFluid(2000))
                .outputs(MalonicAcid.getItemStack(11))
                .output(dust, Salt, 8)
                .EUt(480)
                .duration(600)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .inputs(AzoanilineRibosylxylidine.getItemStack(51), MalonicAcid.getItemStack(11), Urea.getItemStack(8))
                .outputs(Riboflavin.getItemStack(47))
                .fluidOutputs(Aniline.getFluid(1000), Water.getFluid(3000))
                .EUt(480)
                .duration(600)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(Riboflavin.getItemStack(47), TrisodiumPhosphate.getItemStack(8))
                .fluidInputs(HydrochloricAcid.getFluid(3000))
                .outputs(FlavinMononucleotide.getItemStack(1))
                .output(dust, Salt, 6)
                .fluidOutputs(Water.getFluid(2000))
                .EUt(480)
                .duration(600)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(FlavinMononucleotide.getItemStack(1), AdenosineMonophosphate.getItemStack(1))
                .outputs(FlavinAdenineDinucleotide.getItemStack(1))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(480)
                .duration(600)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(NickelOxideHydroxide.getItemStack(4))
                .fluidInputs(Ammonia.getFluid(1000), Formaldehyde.getFluid(6000))
                .outputs(NickelNitrilotriacetate.getItemStack(20))
                .fluidOutputs(Hydrogen.getFluid(6000), Water.getFluid(2000))
                .EUt(480)
                .duration(600)
                .buildAndRegister();
        BIO_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(RapidlyReplicatingAnimalCells.getFluid(8000))
                .notConsumable(NickelNitrilotriacetate.getItemStack())
                .notConsumable(ULTRASONIC_HOMOGENIZER.getStackForm())
                .notConsumable(Pyridine.getFluid(1))
                .outputs(Mitochondria.getItemStack(1))
                .EUt(8192)
                .duration(200)
                .buildAndRegister();
        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(Mitochondria.getItemStack(8))
                .fluidInputs(RapidlyReplicatingAnimalCells.getFluid(1000))
                .fluidOutputs(MitochondriaIncreasedCells.getFluid(1000))
                .EUt(8192)
                .duration(200)
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(foil, PEDOT, 4)
                .inputs(STERILIZED_PETRI_DISH.getStackForm(1))
                .fluidInputs(MitochondriaIncreasedCells.getFluid(250))
                .outputs(AEROBICALLY_RESPIRING_PETRI_DISH.getStackForm(1))
                .EUt(8192)
                .duration(1200)
                .buildAndRegister();
        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(AdenosineMonophosphate.getItemStack(1))
                .notConsumable(AEROBICALLY_RESPIRING_PETRI_DISH.getStackForm())
                .outputs(CyclicAdenosineMonophosphate.getItemStack(1))
                .EUt(480)
                .duration(600)
                .buildAndRegister();
        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(AdenosineTriphosphate.getItemStack(1), ReducedNicotinamideAdenineDinucleotide.getItemStack(2))
                .input(dust, Salt, 6)
                .fluidInputs(PyruvicAcid.getFluid(1000), CarbonDioxide.getFluid(1000))
                .outputs(AdenosineDiphosphate.getItemStack(1), NicotinamideAdenineDinucleotide.getItemStack(2), TrisodiumPhosphate.getItemStack(8))
                .fluidOutputs(OxloaceticAcid.getFluid(1000), HydrochloricAcid.getFluid(3000))
                .EUt(8)
                .duration(100)
                .buildAndRegister();
        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(CoenzymeA.getItemStack(1), NicotinamideAdenineDinucleotide.getItemStack(2))
                .fluidInputs(PyruvicAcid.getFluid(1000))
                .notConsumable(AEROBICALLY_RESPIRING_PETRI_DISH.getStackForm())
                .outputs(ReducedNicotinamideAdenineDinucleotide.getItemStack(2), AcetylCoenzymeA.getItemStack(1))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(8)
                .duration(100)
                .buildAndRegister();


        CULINARY_GENERATOR_RECIPES.recipeBuilder()
                .inputs(NicotinamideAdenineDinucleotide.getItemStack(2), TrisodiumPhosphate.getItemStack(16))
                .fluidInputs(HydrochloricAcid.getFluid(3000))
                .outputs(ReducedNicotinamideAdenineDinucleotide.getItemStack(2))
                .output(dust, Salt, 6)
                .fluidOutputs(PyruvicAcid.getFluid(2000), Water.getFluid(2000))
                .unitGlucose(1)
                .unitTriglyceride(0)
                .EUt(-32)
                .duration(3200)
                .buildAndRegister();
        CULINARY_GENERATOR_RECIPES.recipeBuilder()
                .inputs(CoenzymeA.getItemStack(12), FlavinAdenineDinucleotide.getItemStack(12), NicotinamideAdenineDinucleotide.getItemStack(12))
                .fluidInputs(Water.getFluid(12000))
                .notConsumable(CyclicAdenosineMonophosphate.getItemStack())
                .outputs(ReducedFlavinAdenineDinucleotide.getItemStack(12), ReducedNicotinamideAdenineDinucleotide.getItemStack(12), AcetylCoenzymeA.getItemStack(12))
                .fluidOutputs(Glycerol.getFluid(1000))
                .unitTriglyceride(1)
                .EUt(-32)
                .duration(10)
                .buildAndRegister();
        CULINARY_GENERATOR_RECIPES.recipeBuilder()
                .inputs(FlavinAdenineDinucleotide.getItemStack(1), NicotinamideAdenineDinucleotide.getItemStack(6), AcetylCoenzymeA.getItemStack(1))
                .fluidInputs(Water.getFluid(2000))
                .notConsumable(dust, MagnesiumChloride)
                .notConsumable(OxloaceticAcid.getFluid(1))
                .outputs(ReducedFlavinAdenineDinucleotide.getItemStack(1), ReducedNicotinamideAdenineDinucleotide.getItemStack(6), CoenzymeA.getItemStack(1))
                .fluidOutputs(CarbonDioxide.getFluid(2000))
                .EUt(-32)
                .duration(1600)
                .buildAndRegister();
        CULINARY_GENERATOR_RECIPES.recipeBuilder()
                .inputs(ReducedNicotinamideAdenineDinucleotide.getItemStack(1))
                .fluidInputs(Oxygen.getFluid(500))
                .outputs(NicotinamideAdenineDinucleotide.getItemStack(1))
                .fluidOutputs(Water.getFluid(500))
                .EUt(-32)
                .duration(4000)
                .buildAndRegister();
        CULINARY_GENERATOR_RECIPES.recipeBuilder()
                .inputs(ReducedFlavinAdenineDinucleotide.getItemStack(1))
                .fluidInputs(Oxygen.getFluid(1000))
                .outputs(FlavinAdenineDinucleotide.getItemStack(1))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(-32)
                .duration(2400)
                .buildAndRegister();
    }
}
