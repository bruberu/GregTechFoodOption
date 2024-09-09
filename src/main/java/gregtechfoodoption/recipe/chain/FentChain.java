package gregtechfoodoption.recipe.chain;

import com.cleanroommc.groovyscript.api.IIngredient;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.item.GTFOMetaItem;
import net.minecraft.init.Items;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustSmall;
import static gregtechfoodoption.GTFOMaterialHandler.*;
import static gregtechfoodoption.item.GTFOMetaItem.*;

public class FentChain {
    public static void init() {
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .notConsumable(OrePrefix.dust, Silver)
                .fluidInputs(Oxygen.getFluid(1000), Ethylene.getFluid(1000))
                .fluidOutputs(EthyleneOxide.getFluid(144))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .input(OrePrefix.dust, Aluminium)
                .fluidInputs(Ethylene.getFluid(288))
                .fluidOutputs(DiethylEther.getFluid(144))
                .fluidOutputs(Water.getFluid(144))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(Benzene.getFluid(1000))
                .fluidInputs(Bromine.getFluid(1000))
                .notConsumable(OrePrefix.dust, Aluminium)
                .fluidOutputs(BromoBenzene.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .input(OrePrefix.dust, Magnesium)
                .fluidInputs(BromoBenzene.getFluid(144))
                .fluidInputs(DiethylEther.getFluid(144))
                .output(dust, PhenylmagnesiumBromide, 5)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .input(dustSmall, PhenylmagnesiumBromide)
                .fluidInputs(CarbonDioxide.getFluid(144))
                .output(dust, BenzoPhenone, 5)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .input(dust, Calcite, 5)
                .fluidInputs(Water.getFluid(2000))
                .fluidOutputs(Acetylene.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(Acetylene.getFluid(144))
                .fluidInputs(Formaldehyde.getFluid(144))
                .fluidOutputs(onefourButynediol.getFluid(288))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .notConsumable(OrePrefix.dust, Silver)
                .fluidInputs(Methanol.getFluid(288))
                .fluidInputs(Oxygen.getFluid(288))
                .fluidOutputs(Formaldehyde.getFluid(288))
                .fluidOutputs(Water.getFluid(288))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(onefourButynediol.getFluid(144))
                .fluidInputs(Water.getFluid(144))
                .fluidOutputs(onefourButanediol.getFluid(288))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(onefourButanediol.getFluid(144))
                .notConsumable(OrePrefix.wireGtSingle, Copper)
                .fluidOutputs(gammabutyrolacetone.getFluid(144))
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
                .fluidInputs(onefourButanediol.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidOutputs(yButyrolactone.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(yButyrolactone.getFluid(1000))
                .fluidInputs(Ammonia.getFluid(1000))
                .fluidOutputs(yButyrolactoneammonia.getFluid(1000))
                .buildAndRegister();
        VACUUM_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(yButyrolactoneammonia.getFluid(1000))
                .fluidOutputs(cooledyButyrolactoneammonia.getFluid(1000))
                .buildAndRegister();
        DISTILLATION_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(cooledyButyrolactoneammonia.getFluid(1000))
                .fluidOutputs(twoPyrrolidone.getFluid(100))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(twoPyrrolidone.getFluid(100))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(aqueoustwoPyrrolidone.getFluid(100))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(aqueoustwoPyrrolidone.getFluid(100))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(acidicaqueoustwoPyrrolidone.getFluid(100))
                .buildAndRegister();
        VACUUM_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(acidicaqueoustwoPyrrolidone.getFluid(1000))
                .fluidOutputs(cooledacidicaqueoustwoPyrrolidone.getFluid(1000))
                .buildAndRegister();
        DISTILLERY_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(cooledacidicaqueoustwoPyrrolidone.getFluid(100))
                .fluidOutputs(fouraminobutyric.getFluid(100))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(fouraminobutyric.getFluid(100))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(acidicfouraminobutyric.getFluid(100))
                .buildAndRegister();
        DISTILLERY_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(acidicfouraminobutyric.getFluid(100))
                .fluidOutputs(fourpiperidone.getFluid(100))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(fouraminobutyric.getFluid(100))
                .fluidInputs(SulfuricAcid.getFluid(1000))
                .fluidOutputs(acidicfouraminobutyric.getFluid(100))
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .inputs(SodiumCyanide.getItemStack(5))
                .fluidInputs(Benzylchloride.getFluid(1000))
                .fluidOutputs(Phenylacetonitrile.getFluid(100))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(Benzene.getFluid(1000))
                .fluidInputs(Chlorine.getFluid(1000))
                .fluidOutputs(Benzylchloride.getFluid(2000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(Phenylacetonitrile.getFluid(1000))
                .fluidInputs(DiethylEther.getFluid(1000))
                .fluidOutputs(dissolvedPhenylacetonitrile.getFluid(2000))
                .buildAndRegister();
        DISTILLERY_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(dissolvedPhenylacetonitrile.getFluid(1000))
                .fluidOutputs(phenethylamine.getFluid(100))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(phenethylamine.getFluid(1000))
                .fluidInputs(fourpiperidone.getFluid(1000))
                .fluidInputs(Ethanol.getFluid(1000))
                .fluidOutputs(crudeNphenethylfourpiperidone.getFluid(1000))
                .buildAndRegister();
        DISTILLERY_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(crudeNphenethylfourpiperidone.getFluid(1000))
                .fluidOutputs(Nphenethylfourpiperidone.getFluid(100))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(Nphenethylfourpiperidone.getFluid(1000))
                .fluidInputs(fourpiperidone.getFluid(1000))
                .fluidInputs(Ethanol.getFluid(1000))
                .fluidOutputs(crudeNphenethylfourpiperidone.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(Butene.getFluid(1000))
                .fluidInputs(Oxygen.getFluid(1000))
                .output(dust, Maleicanhydride, 5)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .input(dust, Maleicanhydride)
                .fluidInputs(Oxygen.getFluid(1000))
                .fluidOutputs(Succinicacid.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .input(dust, BenzoPhenone, 5)
                .fluidInputs(Nchlorosuccinimide.getFluid(1000))
                .notConsumable(Chloroform.getFluid(1000))
                .fluidOutputs(threefourDichlorobenzophenone.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(Succinicacid.getFluid(1000))
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
                .fluidOutputs(Nchlorosuccinimide.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(Nchlorosuccinimide.getFluid(1000))
                .fluidInputs(Nphenethylfourpiperidone.getFluid(1000))
                .notConsumable(dust, Aluminium)
                .fluidOutputs(ThreefourChlorobenzoylfourfourchlorophenylonephenethylpiperidinfourol.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(ThreefourChlorobenzoylfourfourchlorophenylonephenethylpiperidinfourol.getFluid(1000))
                .fluidInputs(Nmethyltwopyrrolidinone.getFluid(1000)).notConsumable(dust, Aluminium)
                .fluidOutputs(RNthreeaminopropylNonethreebenzylsevenchlorofouroxofourHchromentwoyltwomethylpropylfourmethylbenzamidehydrochloride.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(gammabutyrolacetone.getFluid(1000))
                .fluidInputs(Methylamine.getFluid(1000))
                .fluidOutputs(crudeNmethyltwopyrrolidinone.getFluid(1000))
                .buildAndRegister();
        FLUID_HEATER_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(crudeNmethyltwopyrrolidinone.getFluid(1000))
                .fluidOutputs(hotNmethyltwopyrrolidinone.getFluid(1000))
                .buildAndRegister();
        VACUUM_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(hotNmethyltwopyrrolidinone.getFluid(1000))
                .fluidOutputs(cooledNmethyltwopyrrolidinone.getFluid(100))
                .buildAndRegister();
        DISTILLATION_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(cooledNmethyltwopyrrolidinone.getFluid(1000))
                .fluidOutputs(Nmethyltwopyrrolidinone.getFluid(100))
                .fluidOutputs(Methylamine.getFluid(400))
                .fluidOutputs(Ammonia.getFluid(400))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(RNthreeaminopropylNonethreebenzylsevenchlorofouroxofourHchromentwoyltwomethylpropylfourmethylbenzamidehydrochloride.getFluid(1000))
                .fluidOutputs(Chloroform.getFluid(100))
                .fluidOutputs(Crudefentanyl.getFluid(1000))
                .buildAndRegister();
        VACUUM_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(Crudefentanyl.getFluid(1000))
                .fluidOutputs(UnrefinedFentanyl.getFluid(100))
                .buildAndRegister();
        AUTOCLAVE_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .fluidInputs(UnrefinedFentanyl.getFluid(1000))
                .output(dust, RecrystalizedFentanyl, 10)
                .buildAndRegister();


    }
}