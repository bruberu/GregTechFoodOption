package gregtechfoodoption.recipe.chain;

public class PopcornChain {
    public static void init() {/*
        PYROLYSE_RECIPES.recipeBuilder().EUt(120).duration(60)
                .circuitMeta(0)
                .inputs(GradedPopcornKernel.getItemStack())
                .fluidInputs(PopcornFlavoringMixture.getFluid(100))
                .outputs(FLAVORED_POPCORN_FLAKE.getStackForm())
                .fluidOutputs(PopcornFlavoringMixture.getFluid(10))
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(1790).duration(40)
                .inputs(BarePopcornKernel.getItemStack())
                .notConsumable(SENSOR_EV.getStackForm())
                .chancedOutput(GradedPopcornKernel.getItemStack(), 9000, 300)
                .buildAndRegister();
        CLUSTER_MILL_RECIPES.recipeBuilder().EUt(50).duration(10)
                .inputs(PopcornKernel.getItemStack())
                .outputs(BarePopcornKernel.getItemStack())
                .buildAndRegister();
        DISASSEMBLER_RECIPES.recipeBuilder().EUt(300).duration(200)
                .inputs(DRIED_POPCORN_EAR.getStackForm())
                .outputs(PopcornKernel.getItemStack(40))
                .outputs(POPCORN_COB.getStackForm())
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(150).duration(300)
                .inputs(POPCORN_EAR.getStackForm())
                .outputs(DRIED_POPCORN_EAR.getStackForm())
                .buildAndRegister();
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(600).duration(300)
                .inputs(POPCORN_EAR.getStackForm(6))
                .outputs(DRIED_POPCORN_EAR.getStackForm(6))
                .buildAndRegister();
        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(2400).duration(300)
                .inputs(POPCORN_EAR.getStackForm(32))
                .outputs(DRIED_POPCORN_EAR.getStackForm(32))
                .buildAndRegister();

        LARGE_MIXER_RECIPES.recipeBuilder().EUt(480).duration(100)
                .input(dust, Salt, 2)
                .inputs(BetaCarotene.getItemStack(2))
                .fluidInputs(SeedOil.getFluid(250))
                .fluidInputs(Diacetyl.getFluid(1000))
                .fluidInputs(Acetoin.getFluid(750))
                .fluidInputs(IiAcetylpyridine.getFluid(250))
                .fluidOutputs(PopcornFlavoringMixture.getFluid(2250))
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(2400)
                .inputs(CLEAN_CULTURE.getStackForm())
                .fluidInputs(Water.getFluid(1000))
                .fluidInputs(FungalGrowthMedium.getFluid(1000))
                .outputs(PHYCOMYCES_BLAKESLEEANUS_CULTURE.getStackForm())
                .buildAndRegister();
        BIO_REACTOR_RECIPES.recipeBuilder().EUt(30720).duration(200)
                .inputs(PHYCOMYCES_BLAKESLEEANUS_CULTURE.getStackForm())
                .fluidInputs(FungalGrowthMedium.getFluid(1000))
                .outputs(PhycomycesBlakesleeanus.getItemStack())
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .buildAndRegister();
        BIO_REACTOR_RECIPES.recipeBuilder().EUt(1920).duration(50)
                .inputs(PhycomycesBlakesleeanus.getItemStack())
                .fluidInputs(FungalGrowthMedium.getFluid(250))
                .outputs(PhycomycesBlakesleeanus.getItemStack(2))
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().EUt(480).duration(100)
                .inputs(Glucose.getItemStack(12))
                .fluidInputs(WaterAgarMix.getFluid(1000))
                .fluidInputs(PeptoneMixture.getFluid(500))
                .fluidOutputs(FungalGrowthMedium.getFluid(1500))
                .buildAndRegister();

        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(1920).duration(150)
                .fluidInputs(Milk.getFluid(1000))
                .fluidOutputs(PeptoneMixture.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_PLANT_RECIPES.recipeBuilder().EUt(50210).duration(300)
                .inputs(IsopropylmagnesiumChloride.getItemStack(12))
                .fluidInputs(IiBromopyridine.getFluid(1000))
                .fluidInputs(Tetrahydrofuran.getFluid(1000))
                .fluidInputs(AcetylChloride.getFluid(1000))
                .outputs(MgClBromide.getItemStack(3))
                .fluidOutputs(IiAcetylpyridine.getFluid(1000))
                .fluidOutputs(Butyraldehyde.getFluid(1000))
                .fluidOutputs(IsopropylChloride.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(90).duration(50)
                .input(dust, Magnesium, 1)
                .fluidInputs(IsopropylChloride.getFluid(1000))
                .outputs(IsopropylmagnesiumChloride.getItemStack(12))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(1620).duration(100)
                .fluidInputs(AceticAnhydride.getFluid(1000))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(AceticAcid.getFluid(1000))
                .fluidOutputs(AcetylChloride.getFluid(1000))
                .buildAndRegister();

        LARGE_CHEMICAL_RECIPES.recipeBuilder().EUt(24000).duration(200)
                .inputs(IiAminopyridine.getItemStack(13))
                .inputs(SodiumNitrite.getItemStack(4))
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(HydrobromicAcid.getFluid(1000))
                .output(dust, Salt, 2)
                .fluidOutputs(IiBromopyridine.getFluid(1000))
                .fluidOutputs(Nitrogen.getFluid(2000))
                .fluidOutputs(Water.getFluid(2000))
                .buildAndRegister();
        LARGE_CHEMICAL_RECIPES.recipeBuilder().EUt(360).duration(240)
                .inputs(SodiumAzanide.getItemStack(4))
                .fluidInputs(Pyridine.getFluid(1000))
                .notConsumable(Ammonia.getFluid(1000))
                .output(dust, SodiumHydroxide, 3)
                .outputs(IiAminopyridine.getItemStack(13))
                .buildAndRegister();

        HelperMethods.removeRecipesByInputs(RecipeMaps.CHEMICAL_RECIPES, Formaldehyde.getFluid(2000), Hydrogen.getFluid(4000), Acetylene.getFluid(1000));


        CHEMICAL_DEHYDRATOR_RECIPES.recipeBuilder().EUt(840).duration(100)
                .fluidInputs(IIvButanediol.getFluid(1000))
                .notConsumable(SulfuricAcid.getFluid(1000))
                .fluidOutputs(Tetrahydrofuran.getFluid(1000))
                .fluidOutputs(Water.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(1920).duration(30)
                .notConsumable(dust, Palladium)
                .fluidInputs(Hydrogen.getFluid(4000))
                .fluidInputs(IIvButynediol.getFluid(1000))
                .fluidOutputs(IIvButanediol.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(3600).duration(35)
                .notConsumable(new IntCircuitIngredient(0))
                .fluidInputs(Formaldehyde.getFluid(2000))
                .fluidInputs(Acetylene.getFluid(1000))
                .fluidOutputs(IIvButynediol.getFluid(1000))
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(240).duration(100)
                .notConsumable(new IntCircuitIngredient(0))
                .notConsumable(DehydrogenationCatalyst.getItemStack())
                .fluidInputs(IiIiiButanediol.getFluid(1000))
                .fluidInputs(Butene.getFluid(2000))
                .fluidOutputs(Diacetyl.getFluid(1000))
                .fluidOutputs(Butane.getFluid(2000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(240).duration(70)
                .notConsumable(new IntCircuitIngredient(1))
                .notConsumable(DehydrogenationCatalyst.getItemStack())
                .fluidInputs(IiIiiButanediol.getFluid(1000))
                .fluidInputs(Butene.getFluid(1000))
                .fluidOutputs(Acetoin.getFluid(1000))
                .fluidOutputs(Butane.getFluid(1000))
                .buildAndRegister();
        CHEMICAL_RECIPES.recipeBuilder().EUt(350).duration(25)
                .fluidInputs(Butene.getFluid(1000))
                .fluidInputs(HypochlorousAcid.getFluid(1000))
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(HydrochloricAcid.getFluid(1000))
                .fluidOutputs(IiIiiButanediol.getFluid(1000))
                .buildAndRegister();

        BIO_REACTOR_RECIPES.recipeBuilder().EUt(1920).duration(400)
                .inputs(PhycomycesBlakesleeanus.getItemStack())
                .notConsumable(GREEN_HALIDE_LAMP.getStackForm())
                .outputs(BetaCarotene.getItemStack(96))
                .buildAndRegister();*/
    }
}
