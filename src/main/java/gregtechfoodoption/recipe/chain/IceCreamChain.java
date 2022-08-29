package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.RecipeMaps;
import gregtechfoodoption.item.GTFOMetaItem;

import static gregtech.api.unification.material.Materials.Milk;
import static gregtech.api.unification.material.Materials.Sugar;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.common.items.MetaItems.SHAPE_MOLD_BALL;
import static gregtechfoodoption.GTFOMaterialHandler.*;

public class IceCreamChain {
    public static void init() {
        RecipeMaps.FLUID_HEATER_RECIPES.recipeBuilder().EUt(16).duration(800)
                .fluidInputs(Milk.getFluid(4000))
                .circuitMeta(1)
                .fluidOutputs(PasteurizedMilk.getFluid(4000))
                .buildAndRegister();
        RecipeMaps.FLUID_HEATER_RECIPES.recipeBuilder().EUt(256).duration(40)
                .fluidInputs(Milk.getFluid(4000))
                .circuitMeta(2)
                .fluidOutputs(PasteurizedMilk.getFluid(4000))
                .buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().EUt(24).duration(100)
                .fluidInputs(PasteurizedMilk.getFluid(1000))
                .fluidOutputs(SkimmedMilk.getFluid(800), Cream.getFluid(200))
                .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(24).duration(160)
                .fluidInputs(Milk.getFluid(500), SoyLecithin.getFluid(10))
                .input(dust, Sugar)
                .fluidOutputs(MilkColloid.getFluid(500))
                .buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(24).duration(200)
                .fluidInputs(MilkColloid.getFluid(500), Cream.getFluid(500))
                .fluidOutputs(IceCreamMixture.getFluid(1000))
                .buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(7).duration(70)
                .fluidInputs(IceCreamMixture.getFluid(144))
                .notConsumable(SHAPE_MOLD_BALL)
                .outputs(GTFOMetaItem.ICE_CREAM_PLAIN.getStackForm())
                .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(24).duration(120)
                .inputs(GTFOMetaItem.ICE_CREAM_PLAIN.getStackForm(4), GTFOMetaItem.CHUM.getStackForm())
                .outputs(GTFOMetaItem.ICE_CREAM_CHUM.getStackForm(4))
                .buildAndRegister();
    }
}
