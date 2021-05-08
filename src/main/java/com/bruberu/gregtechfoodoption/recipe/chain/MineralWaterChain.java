package com.bruberu.gregtechfoodoption.recipe.chain;

import com.bruberu.gregtechfoodoption.GTFOConfig;
import com.bruberu.gregtechfoodoption.GTFOMaterialHandler;
import com.bruberu.gregtechfoodoption.item.GTFOMetaItem;
import gregicadditions.GAMaterials;
import gregicadditions.GAValues;
import gregtech.api.recipes.RecipeMaps.*;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;

import static gregicadditions.GAValues.*;

import static gregicadditions.GAEnums.GAOrePrefix.*;
import static gregicadditions.GAMaterials.*;
import static gregicadditions.GAValues.*;
import static gregicadditions.item.GAMetaItems.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static com.bruberu.gregtechfoodoption.item.GTFOMetaItem.*;
import static com.bruberu.gregtechfoodoption.GTFOMaterialHandler.*;


public class MineralWaterChain {
    public static void init()
    {
        FLUID_CANNER_RECIPES.recipeBuilder()
                .fluidInputs(Water.getFluid(1000))
                .inputs(THERMOS.getStackForm())
                .outputs(MINERAL_WATER.getStackForm())
                .EUt(2)
                .duration(40)
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(THERMOS_CASING.getStackForm(), THERMOS_CAP.getStackForm())
                .outputs(THERMOS.getStackForm())
                .EUt(4)
                .duration(40)
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(foil, Zylon, 2)
                .input(plateCurved, TungstenSteel, 2)
                .input(ring, TungstenSteel)
                .outputs(THERMOS_CAP.getStackForm())
                .EUt(30720)
                .duration(100)
                .buildAndRegister();
        DISASSEMBLER_RECIPES.recipeBuilder()
                .inputs(USED_THERMOS.getStackForm())
                .outputs(LEACHED_THERMOS_CASING.getStackForm(), THERMOS_CAP.getStackForm())
                .EUt(120)
                .duration(160)
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(PolymoscoviumPentahalide.getItemStack(), LEACHED_THERMOS_CASING.getStackForm())
                .fluidInputs(Polytetrafluoroethylene.getFluid(96))
                .chancedOutput(THERMOS_CASING.getStackForm(), 5000, 0)
                .EUt((V[GAValues.UXV]/16) * 15)
                .duration(1000)
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(NonToxicPolymoscoviumPentahalideCurvedSheet.getItemStack(2))
                .outputs(THERMOS_CASING.getStackForm())
                .EUt(V[GAValues.UIV] * 15/16)
                .duration(400)
                .buildAndRegister();
        BENDER_RECIPES.recipeBuilder()
                .inputs(NonToxicPolymoscoviumPentahalideSheet.getItemStack())
                .circuitMeta(1)
                .outputs(NonToxicPolymoscoviumPentahalideCurvedSheet.getItemStack())
                .EUt(V[GAValues.UMV] * 15/16)
                .duration(874)
                .buildAndRegister();
        FORMING_PRESS_RECIPES.recipeBuilder()
                .input(plate, Polytetrafluoroethylene, 2)
                .inputs(PolymoscoviumPentahalideSheet.getItemStack())
                .EUt((V[GAValues.UXV]/16) * 15)
                .outputs(NonToxicPolymoscoviumPentahalideSheet.getItemStack())
                .duration(400)
                .buildAndRegister();
        STELLAR_FORGE_RECIPES.recipeBuilder()
                .inputs(PolymoscoviumPentahalide.getItemStack())
                .fluidInputs(Gluons.getFluid(50))
                .outputs(PolymoscoviumPentahalideSheet.getItemStack())
                .EUt(V[GAValues.UHV] * 15/16)
                .duration(300)
                .buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder()
                .inputs(COSMIC_MESH.getStackForm())
                .fluidInputs(HighlyPolymerizedMoscoviumPentahalides.getFluid(216))
                .outputs(PolymoscoviumPentahalide.getItemStack())
                .EUt(V[GAValues.UIV] * 15/16)
                .duration(1500)
                .buildAndRegister();
        FLUID_EXTRACTION_RECIPES.recipeBuilder()
                .inputs(OligoPolymerizedMoscoviumPentahalides.getItemStack())
                .fluidOutputs(HighlyPolymerizedMoscoviumPentahalides.getFluid(144))
                .EUt(V[GAValues.ZPM] * 15/16)
                .duration(300)
                .buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder()
                .inputs(WellMixedMoscoviumPentahalides.getItemStack())
                .fluidInputs(Helium.getFluid(1000))
                .outputs(OligoPolymerizedMoscoviumPentahalides.getItemStack())
                .EUt(V[GAValues.UEV] * 15/16)
                .duration(1100)
                .buildAndRegister();
        LARGE_MIXER_RECIPES.recipeBuilder()
                .inputs(MoscoviumPentafluoride.getItemStack(), MoscoviumPentachloride.getItemStack(), MoscoviumPentabromide.getItemStack(), MoscoviumPentaiodide.getItemStack(), MoscoviumPentaastatide.getItemStack(), MoscoviumPentatennesside.getItemStack())
                .outputs(WellMixedMoscoviumPentahalides.getItemStack(6))
                .EUt(1920)
                .duration(2100)
                .buildAndRegister();


        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(MoscoviumTrifluoride.getItemStack(4))
                .fluidInputs(ChlorineTrifluoride.getFluid(1000))
                .outputs(MoscoviumPentafluoride.getItemStack(6))
                .fluidOutputs(ChlorineFluoride.getFluid(1000))
                .EUt(V[ZPM] * 15/16)
                .duration(178)
                .buildAndRegister();
        ELECTROLYZER_RECIPES.recipeBuilder()
                .fluidInputs(ChlorineFluoride.getFluid(1000))
                .fluidOutputs(Chlorine.getFluid(1000), Fluorine.getFluid(1000))
                .EUt(120)
                .duration(200)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Chlorine.getFluid(1000), Fluorine.getFluid(3000))
                .fluidOutputs(ChlorineTrifluoride.getFluid(1000))
                .EUt(1920)
                .duration(80)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(MoscoviumIiiOxide.getItemStack(5))
                .fluidInputs(HydrofluoricAcid.getFluid(6000))
                .outputs(MoscoviumTrifluoride.getItemStack(8))
                .fluidOutputs(Water.getFluid(3000))
                .EUt(7680)
                .duration(380)
                .buildAndRegister();
        BLAST_RECIPES.recipeBuilder()
                .input(dust, Moscovium, 2)
                .fluidInputs(Water.getFluid(3000))
                .blastFurnaceTemp(750)
                .outputs(MoscoviumIiiOxide.getItemStack(5))
                .fluidOutputs(Hydrogen.getFluid(6000))
                .EUt(V[UV] * 15/16)
                .duration(460)
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .input(dust, Sodium, 2)
                .inputs(TitaniumCarbide.getItemStack(6), MoscoviumHafniumOctadecachloride.getItemStack(31))
                .outputs(SaltyTitaniumDichlorideMixture.getItemStack(13), DecayedMoscovium.getItemStack(8), HighlyIrradiatedHafniumCarbide.getItemStack(3), MoscoviumPentachloride.getItemStack(12))
                .EUt(V[GAValues.UEV] * 15/16)
                .duration(860)
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder()
                .inputs(HighlyIrradiatedHafniumCarbide.getItemStack(3))
                .outputs(HafniumCarbide.getItemStack())
                .output(dust, TungstenCarbide, 2)
                .EUt(7680)
                .duration(660)
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder()
                .inputs(DecayedMoscovium.getItemStack(8))
                .output(dust, Moscovium, 6)
                .output(dust, Nihonium, 2)
                .EUt(7680)
                .duration(1020)
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder()
                .inputs(SaltyTitaniumDichlorideMixture.getItemStack(13))
                .output(dust, Salt, 4)
                .outputs(TitaniumDichloride.getItemStack(9))
                .EUt(7680)
                .duration(150)
                .buildAndRegister();
        BLAST_RECIPES.recipeBuilder()
                .inputs(TitaniumDichloride.getItemStack(3))
                .input(dust, Magnesium)
                .output(ingotHot, Titanium)
                .output(dust, MagnesiumChloride, 3)
                .blastFurnaceTemp(2141)
                .EUt(480)
                .duration(480)
                .buildAndRegister();
        BLAST_RECIPES.recipeBuilder()
                .inputs(TitaniumIiOxide.getItemStack(4))
                .input(dust, Carbon, 3)
                .blastFurnaceTemp(2100)
                .outputs(TitaniumCarbide.getItemStack(4))
                .fluidOutputs(CarbonDioxide.getFluid(1000))
                .EUt(V[UV] * 15/16)
                .duration(4600)
                .buildAndRegister();
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .inputs(TitaniumIiiOxide.getItemStack(5))
                .outputs(TitaniumIiOxide.getItemStack(4))
                .fluidOutputs(Oxygen.getFluid(1000))
                .EUt(V[ZPM] * 15/16)
                .duration(4000)
                .buildAndRegister();
        ARC_FURNACE_RECIPES.recipeBuilder()
                .input(dust, Titanium, 2)
                .fluidInputs(Oxygen.getFluid(3000))
                .outputs(TitaniumIiiOxide.getItemStack(5))
                .EUt(120)
                .duration(200)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .inputs(HafniumChloride.getItemStack(15), MoscoviumTrichloride.getItemStack(8))
                .input(dust, Moscovium, 8)
                .outputs(MoscoviumHafniumOctadecachloride.getItemStack(31))
                .EUt(V[UV] * 15/16)
                .duration(448)
                .buildAndRegister();
        ARC_FURNACE_RECIPES.recipeBuilder()
                .input(dust, Hafnium)
                .fluidInputs(Oxygen.getFluid(2000))
                .outputs(HafniumOxide.getItemStack(3))
                .EUt(120)
                .duration(200)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(MoscoviumIiiOxide.getItemStack(5))
                .fluidInputs(HydrochloricAcid.getFluid(6000))
                .outputs(MoscoviumTrichloride.getItemStack(8))
                .fluidOutputs(Water.getFluid(3000))
                .EUt(7680)
                .duration(380)
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .fluidInputs(TetrabromomoscoviumTrichloroxenonComplex.getFluid(1000), Bromine.getFluid(4000))
                .notConsumable(HIGH_FREQUENCY_LASER.getStackForm())
                .outputs(MoscoviumPentabromide.getItemStack(6))
                .fluidOutputs(BromineMonochloride.getFluid(3000), Xenon.getFluid(1000))
                .EUt(V[GAValues.UIV] * 15/16)
                .duration(1440)
                .buildAndRegister();
        ELECTROLYZER_RECIPES.recipeBuilder()
                .fluidInputs(BromineMonochloride.getFluid(1000))
                .fluidOutputs(Chlorine.getFluid(1000), Bromine.getFluid(1000))
                .EUt(120)
                .duration(157)
                .buildAndRegister();
        CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .inputs(PotassiumBromide.getItemStack(2))
                .fluidInputs(TribromochloromoscoviumTrichloroxenonComplex.getFluid(1000))
                .notConsumable(DimethylSulfoxide.getFluid(1))
                .output(dust, RockSalt, 2)
                .fluidOutputs(TetrabromomoscoviumTrichloroxenonComplex.getFluid(1000))
                .EUt(V[GAValues.UIV] * 15/16)
                .duration(580)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Dimethylsulfide.getFluid(1000), NitrogenDioxide.getFluid(1000))
                .fluidOutputs(DimethylSulfoxide.getFluid(1000), NitricOxide.getFluid(1000))
                .EUt(7680)
                .duration(100)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(MoscoviumTribromide.getItemStack(4))
                .fluidInputs(XenonTetrachloride.getFluid(1000))
                .fluidOutputs(TribromochloromoscoviumTrichloroxenonComplex.getFluid(1000))
                .EUt(V[UV] * 15/16)
                .duration(595)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(MoscoviumIiiOxide.getItemStack(5))
                .fluidInputs(HydrobromicAcid.getFluid(6000))
                .outputs(MoscoviumTribromide.getItemStack(8))
                .fluidOutputs(Water.getFluid(3000))
                .EUt(7680)
                .duration(373)
                .buildAndRegister();
        DECAY_CHAMBERS_RECIPES.recipeBuilder()
                .fluidInputs(IodineCxxixTrichloride.getFluid(4000))
                .fluidOutputs(XenonTetrachloride.getFluid(3000))
                .outputs(IodineCxxix.getItemStack())
                .EUt(7680)
                .duration(25000)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(IodineCxxix.getItemStack())
                .fluidInputs(Chlorine.getFluid(3000))
                .fluidOutputs(IodineCxxixTrichloride.getFluid(1000))
                .EUt(7680)
                .duration(431)
                .buildAndRegister();
        LARGE_CENTRIFUGE_RECIPES.recipeBuilder()
                .inputs(FissionedUraniumCcxxxv.getItemStack())
                .chancedOutput(OreDictUnifier.get(dust, Technetium), 613, 0)
                .chancedOutput(OreDictUnifier.get(dust, Tin), 10, 0)
                .chancedOutput(OreDictUnifier.get(dust, Zirconium), 545, 0)
                .chancedOutput(OreDictUnifier.get(dust, Caesium), 691, 0)
                .chancedOutput(OreDictUnifier.get(dust, Palladium), 124, 0)
                .chancedOutput(IodineCxxix.getItemStack(), 84, 0)
                .EUt(V[ZPM] * 3/4)
                .duration(900)
                .buildAndRegister();
        NUCLEAR_REACTOR_RECIPES.recipeBuilder()
                .input(dust, Uranium235)
                .input(dustSmall, Neutronium)
                .outputs(FissionedUraniumCcxxxv.getItemStack())
                .EUt(30720)
                .duration(700)
                .baseHeatProduction(12)
                .buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(MoscoviumIodide.getItemStack(5))
                .outputs(MoscoviumPentaiodide.getItemStack(3))
                .EUt(V[GAValues.UMV] * 13/16)
                .duration(500)
                .buildAndRegister();
        BLAST_RECIPES.recipeBuilder()
                .inputs(MethylMoscoviumDiiodide.getItemStack(7))
                .outputs(MoscoviumIodide.getItemStack(2))
                .blastFurnaceTemp(650)
                .fluidOutputs(Iodomethane.getFluid(1000))
                .EUt(480)
                .duration(120)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Iodomethane.getFluid(2000), Trimethylmoscovide.getFluid(1000))
                .outputs(MethylMoscoviumDiiodide.getItemStack(7))
                .fluidOutputs(Propane.getFluid(1000), Methane.getFluid(1000))
                .EUt(V[UHV] * 15/16)
                .duration(260)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(MoscoviumIiiOxide.getItemStack(5), Methyllithium.getItemStack(5))
                .outputs(LithiumMoscovide.getItemStack(2))
                .fluidOutputs(Trimethylmoscovide.getFluid(1000), Oxygen.getFluid(3000))
                .EUt(7680)
                .duration(100)
                .buildAndRegister();
        ELECTROLYZER_RECIPES.recipeBuilder()
                .inputs(LithiumMoscovide.getItemStack(2))
                .outputs(OreDictUnifier.get(dust, Moscovium), OreDictUnifier.get(dust, Lithium))
                .EUt(480)
                .duration(212)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Lithium, 2)
                .fluidInputs(Bromomethane.getFluid(1000))
                .notConsumable(Diethylether.getFluid(1000))
                .outputs(Methyllithium.getItemStack(5), LithiumBromide.getItemStack(2))
                .EUt(480)
                .duration(63)
                .buildAndRegister();
        ELECTROLYZER_RECIPES.recipeBuilder()
                .inputs(LithiumBromide.getItemStack(2))
                .output(dust, Lithium)
                .fluidOutputs(Bromine.getFluid(1000))
                .EUt(120)
                .duration(194)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Sulfur)
                .fluidInputs(Bromine.getFluid(6000), Methanol.getFluid(6000))
                .fluidOutputs(SulfuricAcid.getFluid(1000), Bromomethane.getFluid(6000), Water.getFluid(2000))
                .EUt(7680)
                .duration(770)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Phosphorus)
                .input(dust, Iodine, 3)
                .fluidInputs(Methanol.getFluid(3000))
                .fluidOutputs(PhosphoricAcid.getFluid(1000), Iodomethane.getFluid(3000))
                .EUt(30720)
                .duration(559)
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder()
                .inputs(ShockedThalliumAstatideDust.getItemStack(4))
                .outputs(MoscoviumPentaastatide.getItemStack(3), ThalliumAstatide.getItemStack())
                .EUt(V[UV] * 15/16)
                .duration(280)
                .buildAndRegister();
        MACERATOR_RECIPES.recipeBuilder()
                .inputs(ShockedThalliumAstatideConductor.getItemStack())
                .outputs(ShockedThalliumAstatideDust.getItemStack())
                .EUt(120)
                .duration(136)
                .buildAndRegister();
        POLARIZER_RECIPES.recipeBuilder()
                .inputs(ThalliumAstatideConductor.getItemStack(3))
                .outputs(ShockedThalliumAstatideConductor.getItemStack(2))
                .EUt((V[GAValues.UXV] / 16) * 15)
                .duration(220)
                .buildAndRegister();
        VACUUM_RECIPES.recipeBuilder()
                .inputs(HeatedDopedThalliumAstatide.getItemStack())
                .outputs(ThalliumAstatideConductor.getItemStack())
                .EUt(30720)
                .duration(640)
                .buildAndRegister();
        ARC_FURNACE_RECIPES.recipeBuilder()
                .inputs(DopedThalliumAstatidePlate.getItemStack())
                .outputs(HeatedDopedThalliumAstatide.getItemStack())
                .EUt(30720)
                .duration(259)
                .buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(DopedThalliumAstatide.getItemStack())
                .outputs(DopedThalliumAstatidePlate.getItemStack())
                .EUt(30720)
                .duration(80)
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder()
                .inputs(ThalliumAstatide.getItemStack(), MoscoviumAstatide.getItemStack())
                .outputs(DopedThalliumAstatide.getItemStack(2))
                .EUt(1920)
                .duration(85)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(ThalliumSulfate.getItemStack(7))
                .fluidInputs(HydroastaticAcid.getFluid(2000))
                .outputs(ThalliumAstatide.getItemStack(4))
                .fluidOutputs(SulfuricAcid.getFluid(1000))
                .EUt(480)
                .duration(40)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Thallium, 2)
                .fluidInputs(SulfuricAcid.getFluid(1000), Oxygen.getFluid(1000))
                .outputs(ThalliumSulfate.getItemStack(7))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(7550)
                .duration(40)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Astatine, 1)
                .fluidInputs(Hydrogen.getFluid(1000))
                .fluidOutputs(HydroastaticAcid.getFluid(1000))
                .EUt(960)
                .duration(50)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(MoscoviumIOxide.getItemStack(3))
                .fluidInputs(HydroastaticAcid.getFluid(2000))
                .outputs(MoscoviumAstatide.getItemStack(2))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(30720)
                .duration(140)
                .buildAndRegister();
        ARC_FURNACE_RECIPES.recipeBuilder()
                .input(dust, Moscovium, 2)
                .fluidInputs(Oxygen.getFluid(1000))
                .outputs(MoscoviumIOxide.getItemStack(3))
                .EUt(7680)
                .duration(400)
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder()
                .inputs(SupercooledMoscoviumTennesside.getItemStack(2), SupercooledTennessine.getItemStack(4))
                .fluidInputs(EntangledGluons.getFluid(1000))
                .notConsumable(HIGH_FREQUENCY_LASER.getStackForm())
                .outputs(MoscoviumPentatennesside.getItemStack(6))
                .EUt((V[GAValues.UXV] / 16) * 15)
                .duration(220)
                .buildAndRegister();
        STELLAR_FORGE_RECIPES.recipeBuilder()
                .fluidInputs(Gluons.getFluid(1000))
                .notConsumable(OreDictUnifier.get(plate, QCDMatter))
                .fluidOutputs(EntangledGluons.getFluid(1000))
                .EUt(V[UHV] * 15/16)
                .duration(43)
                .buildAndRegister();
        VACUUM_RECIPES.recipeBuilder()
                .inputs(MoscoviumTennesside.getItemStack())
                .fluidInputs(SupercooledCryotheum.getFluid(3000))
                .outputs(SupercooledMoscoviumTennesside.getItemStack())
                .EUt(V[UHV] * 15/16)
                .duration(71)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .inputs(MoscoviumIOxide.getItemStack(3), HydrotennessiticAcid.getItemStack(2))
                .outputs(MoscoviumTennesside.getItemStack(2))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(V[UV] * 15/16)
                .duration(77)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .input(dust, Tennessine)
                .fluidInputs(Hydrogen.getFluid(1000))
                .EUt(V[UHV])
                .duration(200)
                .outputs(HydrotennessiticAcid.getItemStack(2))
                .buildAndRegister();
        ELECTROLYZER_RECIPES.recipeBuilder()
                .inputs(SupercooledHydrotennessiticAcid.getItemStack(2))
                .outputs(SupercooledTennessine.getItemStack())
                .fluidOutputs(LiquidHydrogen.getFluid(1000))
                .EUt(30)
                .duration(460000)
                .buildAndRegister();
        VACUUM_RECIPES.recipeBuilder()
                .inputs(HydrotennessiticAcid.getItemStack())
                .fluidInputs(SupercooledCryotheum.getFluid(3000))
                .outputs(SupercooledHydrotennessiticAcid.getItemStack())
                .EUt(V[UHV])
                .duration(68)
                .buildAndRegister();
    }
}
