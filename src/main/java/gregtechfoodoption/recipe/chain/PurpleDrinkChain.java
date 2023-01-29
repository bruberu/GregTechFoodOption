package gregtechfoodoption.recipe.chain;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtechfoodoption.item.GTFOMetaItem.*;
import static gregtechfoodoption.GTFOMaterialHandler.*;
import static gregtech.common.items.MetaItems.*;
import static gregtech.api.unification.material.Materials.*;
import net.minecraft.init.Items;
import net.minecraft.init.Blocks;
import static gregtech.api.unification.ore.OrePrefix.*;

public class PurpleDrinkChain {
    public static void init() {
        CANNER_RECIPES.recipeBuilder()
                .fluidInputs(PurpleDrink.getFluid(500))
                .input(Items.GLASS_BOTTLE, 1)
                .outputs(BOTTLE_PURPLE_DRINK.getStackForm())
                .EUt(30)
                .duration(20)
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Etirps.getFluid(1000), CoughSyrup.getFluid(500))
                .inputs(CrushedHardCandy.getItemStack())
                .fluidOutputs(PurpleDrink.getFluid(1000))
                .EUt(480)
                .duration(40)
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(CarbonatedWater.getFluid(1000), LemonLimeSodaSyrup.getFluid(1000))
                .fluidOutputs(Etirps.getFluid(2000))
                .EUt(60)
                .duration(20)
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder()
                .fluidInputs(Etirps.getFluid(500))
                .inputs(PLASTIC_BOTTLE.getStackForm())
                .outputs(ETIRPS.getStackForm())
                .EUt(30)
                .duration(20)
                .buildAndRegister();
/*        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(LemonLimeSludge.getFluid(1000), HFCSLv.getFluid(166))
                .fluidOutputs(LemonLimeSodaSyrup.getFluid(1000))
                .EUt(60)
                .duration(40)
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(LemonLimeSludge.getFluid(750), HFCSXlii.getFluid(375))
                .fluidOutputs(LemonLimeSodaSyrup.getFluid(750))
                .EUt(60)
                .duration(40)
                .buildAndRegister();*/
        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(LemonLimeSludge.getFluid(500))
                .input(Items.SUGAR, 9)
                .fluidOutputs(LemonLimeSodaSyrup.getFluid(500))
                .EUt(60)
                .duration(40)
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(LemonLimeSolution.getFluid(1500))
                .fluidOutputs(LemonLimeSludge.getFluid(1000))
                .EUt(30)
                .duration(140)
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(LemonExtract.getFluid(500), LimeExtract.getFluid(500))
                .fluidOutputs(LemonLimeSolution.getFluid(1000))
                .EUt(30)
                .duration(100)
                .buildAndRegister();
/*
        RecipeUtils.addBioReactorRecipes(DistilledWater, LACTOBACILLUS_PENTOSIS_CULTURE, LactobacillusPentosis.getItemStack(), Milk);
        RecipeUtils.addBioReactorRecipes(DistilledWater, BACILLUS_SUBTILIS_CULTURE, BacillusSubtilis.getItemStack(), new ItemStack(Items.WHEAT));
*/
/*        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(HFCSXlii.getFluid(771),HFCSXc.getFluid(229))
                .fluidOutputs(HFCSLv.getFluid(1000))
                .EUt(30)
                .duration(80)
                .buildAndRegister();
        RecipeUtils.chemicalDehydratorProxy().recipeBuilder()
                .fluidInputs(HighFructoseCornSyrupSolution.getFluid(1000))
                .fluidOutputs(HFCSXlii.getFluid(1000))
                .EUt(120)
                .duration(100)
                .buildAndRegister();
        CENTRIFUGE_RECIPES.recipeBuilder()
                .fluidInputs(HighFructoseCornSyrupSolution.getFluid(1000))
                .fluidOutputs(HFCSXc.getFluid(466))
                .EUt(1920)
                .duration(240)
                .buildAndRegister();
        BIO_REACTOR_RECIPES.recipeBuilder()
                .fluidInputs(GlucoseSolution.getFluid(1000))
                .notConsumable(FructoseConversionPlate.getItemStack())
                .fluidOutputs(HighFructoseCornSyrupSolution.getFluid(1000))
                .EUt(240)
                .duration(125)
                .buildAndRegister();
        RecipeUtils.chemicalDehydratorProxy().recipeBuilder()
                .fluidInputs(GlucoseSolution.getFluid(1000))
                .outputs(Glucose.getItemStack(24))
                .EUt(480)
                .duration(100)
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder()
                .fluidInputs(Glue.getFluid(100))
                .inputs(XyloseIsomerase.getItemStack())
                .input(plate, Polytetrafluoroethylene)
                .outputs(FructoseConversionPlate.getItemStack())
                .EUt(40)
                .duration(400)
                .buildAndRegister();
        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(LactobacillusPentosis.getItemStack())
                .input(dustSmall, Sugar)
                .outputs(XyloseIsomerase.getItemStack())
                .EUt(480)
                .duration(180)
                .buildAndRegister();
        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(GammaAmylase.getItemStack())
                .fluidInputs(OligosaccharideSolution.getFluid(1000))
                .fluidOutputs(GlucoseSolution.getFluid(1000))
                .EUt(30)
                .duration(400)
                .buildAndRegister();
        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(BacillusSubtilis.getItemStack())
                .input(dustSmall, Wheat)
                .chancedOutput(GammaAmylase.getItemStack(), 4000, 600)
                .chancedOutput(AlphaAmylase.getItemStack(), 4000, 600)
                .EUt(30)
                .duration(400)
                .buildAndRegister();
        BIO_REACTOR_RECIPES.recipeBuilder()
                .inputs(AlphaAmylase.getItemStack())
                .fluidInputs(CornStarchSolution.getFluid(1000))
                .fluidOutputs(OligosaccharideSolution.getFluid(1000))
                .EUt(120)
                .duration(100)
                .buildAndRegister();*/
/*        MIXER_RECIPES.recipeBuilder()
                .inputs(CornStarch.getItemStack())
                .fluidInputs(DistilledWater.getFluid(1000))
                .fluidOutputs(CornStarchSolution.getFluid(1000))
                .EUt(6)
                .duration(60)
                .buildAndRegister();
        LATHE_RECIPES.recipeBuilder()
                .inputs(PopcornKernel.getItemStack())
                .outputs(CornStarch.getItemStack())
                .output(dustSmall, Wood, 2)
                .EUt(18)
                .duration(200)
                .buildAndRegister();*/
        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Water.getFluid(1000), CarbonDioxide.getFluid(100))
                .fluidOutputs(CarbonatedWater.getFluid(1050))
                .EUt(120)
                .duration(60)
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder()
                .fluidInputs(CarbonatedWater.getFluid(500))
                .inputs(PLASTIC_BOTTLE.getStackForm())
                .outputs(SPARKLING_WATER.getStackForm())
                .EUt(30)
                .duration(100)
                .buildAndRegister();
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .fluidInputs(Polyethylene.getFluid(288))
                .notConsumable(SHAPE_MOLD_BOTTLE.getStackForm())
                .outputs(PLASTIC_BOTTLE.getStackForm())
                .EUt(60)
                .duration(160)
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder()
                .inputs(Promethazine.getItemStack(40), Codeine.getItemStack(42))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(CoughSyrup.getFluid(1000))
                .EUt(60)
                .duration(60)
                .buildAndRegister();
        EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(CrushedPoppy.getItemStack(30))
                .outputs(Codeine.getItemStack(42))
                .EUt(1920)
                .duration(600)
                .buildAndRegister();
        MACERATOR_RECIPES.recipeBuilder()
                .input(Blocks.RED_FLOWER)
                .outputs(CrushedPoppy.getItemStack())
                .EUt(4)
                .duration(20)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Dimethylamine.getFluid(1000), XPhenothiazineIiPropylChloride.getFluid(1000))
                .notConsumable(dust, Copper)
                .outputs(Promethazine.getItemStack(40))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .EUt(8000)
                .duration(120)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(IsopropylChloride.getFluid(1000))
                .inputs(Phenothiazine.getItemStack(23))
                .fluidOutputs(XPhenothiazineIiPropylChloride.getFluid(1000))
                .EUt(240)
                .duration(120)
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder()
                .inputs(Diphenylamine.getItemStack(24))
                .input(dust, Sulfur, 2)
                .notConsumable(dust, Iodine)
                .outputs(Phenothiazine.getItemStack(23))
                .fluidOutputs(HydrogenSulfide.getFluid(1000))
                .EUt(120)
                .duration(120)
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Aniline.getFluid(2000), HydrochloricAcid.getFluid(1000))
                .outputs(Diphenylamine.getItemStack(24))
                .output(dust, AmmoniumChloride, 6)
                .EUt(480)
                .duration(120)
                .buildAndRegister();
        MACERATOR_RECIPES.recipeBuilder()
                .inputs(APPLE_HARD_CANDY.getStackForm())
                .outputs(CrushedHardCandy.getItemStack(2))
                .EUt(480)
                .duration(200)
                .buildAndRegister();
        FORGE_HAMMER_RECIPES.recipeBuilder()
                .inputs(APPLE_HARD_CANDY.getStackForm())
                .outputs(CrushedHardCandy.getItemStack(1))
                .EUt(30)
                .duration(20)
                .buildAndRegister();
        VACUUM_RECIPES.recipeBuilder()
                .inputs(HotAppleHardCandy.getItemStack())
                .outputs(APPLE_HARD_CANDY.getStackForm())
                .EUt(5)
                .duration(200)
                .buildAndRegister();
        BENDER_RECIPES.recipeBuilder()
                .inputs(HardCandyPlate.getItemStack())
                .circuitMeta(0)
                .outputs(HotAppleHardCandy.getItemStack())
                .EUt(24)
                .duration(64)
                .buildAndRegister();
        CUTTER_RECIPES.recipeBuilder()
                .inputs(HardCandyResin.getItemStack())
                .outputs(HardCandyPlate.getItemStack(9))
                .EUt(120)
                .duration(60)
                .buildAndRegister();
        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .fluidInputs(AppleCandySyrup.getFluid(1000))
                .notConsumable(SHAPE_MOLD_PLATE.getStackForm())
                .outputs(HardCandyResin.getItemStack())
                .EUt(120)
                .duration(60)
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(AppleSyrup.getFluid(1000), CaneSyrup.getFluid(1000))
                .input("dyeLime", 5)
                .fluidOutputs(AppleCandySyrup.getFluid(2000))
                .EUt(120)
                .duration(60)
                .buildAndRegister();
        EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(CORED_APPLE.getStackForm(2))
                .fluidOutputs(AppleSyrup.getFluid(1000))
                .EUt(30)
                .duration(60)
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder()
                .input(Items.APPLE)
                .outputs(CORED_APPLE.getStackForm())
                .EUt(60)
                .duration(200)
                .buildAndRegister();
        // 6H + C6H5NO2 -> C6H5NH2 + 2H2O
        CHEMICAL_RECIPES.recipeBuilder()
                .fluidInputs(Hydrogen.getFluid(6000))
                .fluidInputs(Nitrobenzene.getFluid(1000))
                .notConsumable(dust, Zinc)
                .fluidOutputs(Aniline.getFluid(1000))
                .fluidOutputs(Water.getFluid(2000))
                .EUt(30)
                .duration(100)
                .buildAndRegister();
    }
}
