package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtechfoodoption.GTFOMaterialHandler;
import gregtechfoodoption.item.GTFOMetaItem;

public class SorbetChain {
    public static void init() {
        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(30).duration(200)
                .input(OrePrefix.dust, Materials.Ice, 4)
                .input(OrePrefix.dustTiny, Materials.Sugar)
                .circuitMeta(1)
                .outputs(GTFOMetaItem.SORBET_PLAIN.getStackForm(4))
                .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(30).duration(200)
                .input(OrePrefix.dust, Materials.Ice, 4)
                .input(OrePrefix.dustTiny, Materials.Sugar)
                .fluidInputs(GTFOMaterialHandler.AppleExtract.getFluid(50))
                .outputs(GTFOMetaItem.SORBET_APPLE.getStackForm(4))
                .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(30).duration(200)
                .input(OrePrefix.dust, Materials.Ice, 4)
                .input(OrePrefix.dustTiny, Materials.Sugar)
                .fluidInputs(GTFOMaterialHandler.ApricotExtract.getFluid(40), GTFOMaterialHandler.LemonExtract.getFluid(10))
                .outputs(GTFOMetaItem.SORBET_APRICOT.getStackForm(4))
                .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(30).duration(200)
                .input(OrePrefix.dust, Materials.Ice, 4)
                .input(OrePrefix.dustTiny, Materials.Sugar)
                .fluidInputs(GTFOMaterialHandler.GrapeExtract.getFluid(50))
                .outputs(GTFOMetaItem.SORBET_GRAPE.getStackForm(4))
                .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(30).duration(200)
                .input(OrePrefix.dust, Materials.Ice, 4)
                .input(OrePrefix.dustTiny, Materials.Sugar)
                .fluidInputs(GTFOMaterialHandler.LimeExtract.getFluid(50))
                .outputs(GTFOMetaItem.SORBET_LIME.getStackForm(4))
                .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(30).duration(200)
                .input(OrePrefix.dust, Materials.Ice, 4)
                .input(OrePrefix.dustTiny, Materials.Sugar)
                .fluidInputs(GTFOMaterialHandler.ChorusJuice.getFluid(40), GTFOMaterialHandler.LemonExtract.getFluid(10))
                .outputs(GTFOMetaItem.SORBET_CHORUS.getStackForm(4))
                .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(30).duration(200)
                .input(OrePrefix.dust, Materials.Ice, 4)
                .input(OrePrefix.dustTiny, Materials.Sugar)
                .fluidInputs(GTFOMaterialHandler.VibrantExtract.getFluid(40), GTFOMaterialHandler.LemonExtract.getFluid(10))
                .outputs(GTFOMetaItem.SORBET_VIBRANT.getStackForm(4))
                .buildAndRegister();


        /*RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(30).duration(200)
                .input(OrePrefix.dust, Materials.Ice, 4)
                .input(OrePrefix.dustTiny, Materials.Sugar)
                .fluidInputs(GTFOMaterialHandler.OrangeExtract.getFluid(50))
                .outputs(GTFOMetaItem.SORBET_ORANGE.getStackForm(4))
                .buildAndRegister();*/
    }
}
