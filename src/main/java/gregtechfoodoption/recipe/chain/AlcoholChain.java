package gregtechfoodoption.recipe.chain;

import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import gregtechfoodoption.GTFOConfig;
import net.minecraft.init.Items;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.Biomass;
import static gregtech.api.unification.material.Materials.Water;
import static gregtechfoodoption.GTFOMaterialHandler.*;
import static gregtechfoodoption.item.GTFOMetaItem.*;

public class AlcoholChain {
    public static void init() {
        FERMENTING_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .input(Items.POTATO)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(PotatoJuice.getFluid(1000))
                .buildAndRegister();
        FERMENTING_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .input(Items.WHEAT)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(WheatyJuice.getFluid(1000))
                .buildAndRegister();


        FERMENTING_RECIPES.recipeBuilder().EUt(8).duration(3000)
                .fluidInputs(PotatoJuice.getFluid(2000))
                .fluidOutputs(Vodka.getFluid(2000))
                .buildAndRegister();

        FERMENTING_RECIPES.recipeBuilder().EUt(8).duration(3000)
                .fluidInputs(WheatyJuice.getFluid(2000))
                .fluidOutputs(PoorQualityBeer.getFluid(2000))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().EUt(12).duration(30)
                .fluidInputs(Vodka.getFluid(1000), LemonExtract.getFluid(100))
                .fluidOutputs(Leninade.getFluid(1100))
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder().EUt(12).duration(30)
                .input(Items.GLASS_BOTTLE)
                .fluidInputs(Vodka.getFluid(100))
                .outputs(VODKA.getStackForm())
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder().EUt(12).duration(30)
                .input(Items.GLASS_BOTTLE)
                .fluidInputs(Leninade.getFluid(100))
                .outputs(LENINADE.getStackForm())
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder().EUt(12).duration(30)
                .input(Items.GLASS_BOTTLE)
                .fluidInputs(PoorQualityBeer.getFluid(100))
                .outputs(BEER.getStackForm())
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().EUt(800).duration(1000)
                .fluidInputs(Materials.Milk.getFluid(1000))
                .input(OrePrefix.dustTiny, Materials.Naquadah)
                .fluidOutputs(Nilk.getFluid(1000))
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder().EUt(12).duration(30)
                .input(Items.GLASS_BOTTLE)
                .fluidInputs(Nilk.getFluid(200))
                .outputs(NILK.getStackForm())
                .buildAndRegister();

        FERMENTING_RECIPES.recipeBuilder().EUt(4).duration(500)
                .inputs(WHITE_GRAPES.getStackForm(10))
                .fluidInputs(Water.getFluid(4000))
                .fluidOutputs(MaceratedWhiteGrapes.getFluid(4000))
                .buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().EUt(24).duration(100)
                .fluidInputs(MaceratedWhiteGrapes.getFluid(1000))
                .fluidOutputs(PressedWhiteWort.getFluid(1000))
                .buildAndRegister();
        if (!GTFOConfig.gtfoChainsConfig.makeChainsHarder) {
            FERMENTING_RECIPES.recipeBuilder().EUt(4).duration(8000)
                    .fluidInputs(MaceratedWhiteGrapes.getFluid(8000))
                    .fluidOutputs(WhiteWine.getFluid(2000))
                    .buildAndRegister();
        }
        CENTRIFUGE_RECIPES.recipeBuilder().EUt(16).duration(200)
                .fluidInputs(PressedWhiteWort.getFluid(1000))
                .fluidOutputs(ClarifiedWhiteWort.getFluid(800), Biomass.getFluid(200))
                .buildAndRegister();
        FERMENTING_RECIPES.recipeBuilder().EUt(2).duration(4000)
                .fluidInputs(ClarifiedWhiteWort.getFluid(8000))
                .fluidOutputs(WhiteWine.getFluid(8000))
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder().EUt(12).duration(30)
                .input(Items.GLASS_BOTTLE)
                .fluidInputs(WhiteWine.getFluid(200))
                .outputs(WHITE_WINE.getStackForm())
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().EUt(16).duration(300)
                .inputs(GRAPES.getStackForm(8))
                .outputs(MetaItems.PLANT_BALL.getStackForm(1))
                .fluidOutputs(RedGrapesMust.getFluid(4000))
                .buildAndRegister();
        FERMENTING_RECIPES.recipeBuilder().EUt(8).duration(8000)
                .fluidInputs(RedGrapesMust.getFluid(8000))
                .fluidOutputs(FermentedRedGrapesMust.getFluid(8000))
                .buildAndRegister();
        COMPRESSOR_RECIPES.recipeBuilder().EUt(24).duration(500)
                .fluidInputs(FermentedRedGrapesMust.getFluid(1000))
                .outputs(MetaItems.BIO_CHAFF.getStackForm(1))
                .fluidOutputs(AlcoholicRedGrapeJuice.getFluid(1000))
                .buildAndRegister();
        FERMENTING_RECIPES.recipeBuilder().EUt(2).duration(8000)
                .fluidInputs(AlcoholicRedGrapeJuice.getFluid(8000))
                .fluidOutputs(RedWine.getFluid(8000))
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder().EUt(12).duration(30)
                .input(Items.GLASS_BOTTLE)
                .fluidInputs(RedWine.getFluid(200))
                .outputs(RED_WINE.getStackForm())
                .buildAndRegister();

    }
}