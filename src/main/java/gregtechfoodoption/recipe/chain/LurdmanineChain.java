package gregtechfoodoption.recipe.chain;

import gregtech.api.unification.ore.OrePrefix;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustSmall;
import static gregtechfoodoption.GTFOMaterialHandler.*;

public class LurdmanineChain {
    public static void init() {
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .input(OrePrefix.dust, Aluminium)
                .fluidInputs(Ethanol.getFluid(2000))
                .fluidOutputs(DiethylEther.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(Benzene.getFluid(1000))
                .fluidInputs(Bromine.getFluid(1000))
                .notConsumable(OrePrefix.dust, Aluminium)
                .fluidOutputs(Bromobenzene.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .input(OrePrefix.dust, Magnesium)
                .fluidInputs(Bromobenzene.getFluid(144))
                .fluidInputs(DiethylEther.getFluid(144))
                .output(dust, PhenylmagnesiumBromide, 5)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .input(dustSmall, PhenylmagnesiumBromide)
                .fluidInputs(CarbonDioxide.getFluid(144))
                .output(dust, Benzophenone, 5)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .input(dust, Calcite, 5)
                .fluidInputs(Water.getFluid(2000))
                .fluidOutputs(Acetylene.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(Acetylene.getFluid(144))
                .fluidInputs(Formaldehyde.getFluid(144))
                .fluidOutputs(I_IVButynediol.getFluid(288))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .notConsumable(OrePrefix.dust, Silver)
                .fluidInputs(Methanol.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(Formaldehyde.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(I_IVButynediol.getFluid(144))
                .fluidInputs(Water.getFluid(144))
                .fluidOutputs(I_IVButanediol.getFluid(288))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(I_IVButanediol.getFluid(144))
                .notConsumable(OrePrefix.wireGtSingle, Copper)
                .fluidOutputs(GammaButyrolactone.getFluid(144))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(Ammonia.getFluid(144))
                .fluidInputs(Methanol.getFluid(144))
                .notConsumable(dust, SiliconDioxide)
                .fluidOutputs(Methylamine.getFluid(144))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(SaltWater.getFluid(12000))
                .fluidInputs(Chlorine.getFluid(12000))
                .notConsumable(SulfuricAcid.getFluid(1000))
                .fluidOutputs(Bromine.getFluid(1000))
                .fluidOutputs(Chlorine.getFluid(12000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(SaltWater.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(12000))
                .notConsumable(SulfuricAcid.getFluid(1000))
                .fluidOutputs(Bromine.getFluid(1000))
                .fluidOutputs(Chlorine.getFluid(12000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(I_IVButanediol.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidOutputs(GammaButyrolactone.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(GammaButyrolactone.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(AmmoniumGammaButyrolactone.getFluid(1000))
                .buildAndRegister();
        VACUUM_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(AmmoniumGammaButyrolactone.getFluid(1000))
                .fluidOutputs(CooledAmmoniumGammaButyrolactone.getFluid(1000))
                .buildAndRegister();
        DISTILLATION_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(CooledAmmoniumGammaButyrolactone.getFluid(1000))
                .fluidOutputs(IIPyrrolidone.getFluid(100))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(IIPyrrolidone.getFluid(100))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(AqueousIIPyrrolidone.getFluid(100))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(AqueousIIPyrrolidone.getFluid(100))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(AcidicAqueousIIPyrrolidone.getFluid(100))
                .buildAndRegister();
        VACUUM_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(AcidicAqueousIIPyrrolidone.getFluid(1000))
                .fluidOutputs(CooledAcidicAqueousIIPyrrolidone.getFluid(1000))
                .buildAndRegister();
        DISTILLERY_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(CooledAcidicAqueousIIPyrrolidone.getFluid(100))
                .fluidOutputs(IVAminoButyricAcid.getFluid(100))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(IVAminoButyricAcid.getFluid(100))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(AcidicIVAminoButyricAcidSolution.getFluid(100))
                .buildAndRegister();
        DISTILLERY_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(AcidicIVAminoButyricAcidSolution.getFluid(100))
                .fluidOutputs(IVPiperidone.getFluid(100))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(IVAminoButyricAcid.getFluid(100))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(AcidicIVAminoButyricAcidSolution.getFluid(100))
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .inputs(SodiumCyanide.getItemStack(5))
                .fluidInputs(BenzylChloride.getFluid(1000))
                .fluidOutputs(Phenylacetonitrile.getFluid(100))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(Benzene.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidOutputs(BenzylChloride.getFluid(2000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(Phenylacetonitrile.getFluid(1000))
                .fluidInputs(DiethylEther.getFluid(1000))
                .fluidOutputs(DissolvedPhenylacetonitrile.getFluid(2000))
                .buildAndRegister();
        DISTILLERY_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(DissolvedPhenylacetonitrile.getFluid(1000))
                .fluidOutputs(Phenethylamine.getFluid(100))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(Phenethylamine.getFluid(1000))
                .fluidInputs(IVPiperidone.getFluid(1000))
                .fluidInputs(Ethanol.getFluid(1000))
                .fluidOutputs(CrudeNPhenethylIVPiperidone.getFluid(1000))
                .buildAndRegister();
        DISTILLERY_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(CrudeNPhenethylIVPiperidone.getFluid(1000))
                .fluidOutputs(NPhenethylIVPiperidone.getFluid(100))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(Phenethylamine.getFluid(1000))
                .fluidInputs(IVPiperidone.getFluid(1000))
                .fluidInputs(Ethanol.getFluid(1000))
                .fluidOutputs(NPhenethylIVPiperidoneIntermediate.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(NPhenethylIVPiperidoneIntermediate.getFluid(1000))
                .input(dust, SodiumBorohydride)
                .fluidInputs(Ethanol.getFluid(1000))
                .fluidOutputs(NPhenethylIVPiperidone.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .input(dust, Sodium, 16)
                .input(dust, Borax)
                .fluidInputs(Hydrogen.getFluid(16000))
                .output(dust, SodiumBorohydride, 4)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(Butene.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .output(dust, MaleicAnhydride, 5)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .input(dust, MaleicAnhydride)
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(SuccinicAcid.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .input(dust, Benzophenone, 5)
                .fluidInputs(NChlorosuccinimide.getFluid(1000))
                .notConsumable(Chloroform.getFluid(1000))
                .fluidOutputs(III_IVDichlorobenzophenone.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(SuccinicAcid.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(AmmoniumSuccinate.getFluid(1000))
                .buildAndRegister();
        FLUID_HEATER_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(AmmoniumSuccinate.getFluid(1000))
                .fluidOutputs(Succinimide.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(Succinimide.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidOutputs(ChlorinatedSuccinimide.getFluid(1000))
                .fluidOutputs(DilutedHydrochloricAcid.getFluid(1000))
                .buildAndRegister();
        DISTILLERY_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(ChlorinatedSuccinimide.getFluid(1000))
                .fluidOutputs(NChlorosuccinimide.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(III_IVDichlorobenzophenone.getFluid(1000))
                .fluidInputs(NPhenethylIVPiperidone.getFluid(1000))
                .notConsumable(dust, Aluminium)
                .fluidOutputs(LurdmaninePrecursorBase.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(LurdmaninePrecursorBase.getFluid(1000))
                .fluidInputs(NMethylIIPyrrolidinone.getFluid(1000))
                .notConsumable(dust, Aluminium)
                .fluidOutputs(LurdmaninePrecursorDimer.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(GammaButyrolactone.getFluid(1000))
                .fluidInputs(Methylamine.getFluid(1000))
                .fluidOutputs(CrudeNMethylIIPyrrolidinone.getFluid(1000))
                .buildAndRegister();
        FLUID_HEATER_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(CrudeNMethylIIPyrrolidinone.getFluid(1000))
                .fluidOutputs(HotNMethylIIPyrrolidinone.getFluid(1000))
                .buildAndRegister();
        VACUUM_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(HotNMethylIIPyrrolidinone.getFluid(1000))
                .fluidOutputs(CooledNMethylIIPyrrolidinone.getFluid(100))
                .buildAndRegister();
        DISTILLATION_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(CooledNMethylIIPyrrolidinone.getFluid(1000))
                .fluidOutputs(NMethylIIPyrrolidinone.getFluid(100))
                .fluidOutputs(Methylamine.getFluid(400))
                .fluidOutputs(Ammonia.getFluid(400))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(LurdmaninePrecursorDimer.getFluid(1000))
                .fluidInputs(Chloroform.getFluid(100))
                .fluidOutputs(CrudeLurdmanine.getFluid(1000))
                .buildAndRegister();
        VACUUM_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(CrudeLurdmanine.getFluid(1000))
                .fluidOutputs(UnrefinedLurdmanine.getFluid(100))
                .buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(UnrefinedLurdmanine.getFluid(1000))
                .output(dust, RecrystalizedLurdmanine, 10)
                .buildAndRegister();


    }
}