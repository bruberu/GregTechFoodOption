package gregtechfoodoption.recipe.chain;

import gregtechfoodoption.GTFOMaterialHandler;
import gregtechfoodoption.item.GTFOMetaItem;

import static gregtech.api.recipes.RecipeMaps.CENTRIFUGE_RECIPES;
import static gregtech.api.recipes.RecipeMaps.MIXER_RECIPES;
import static gregtech.api.unification.material.Materials.Water;
import static gregtech.api.unification.material.Materials.Wheat;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class PastaChain {
    public static void init() {
        CENTRIFUGE_RECIPES.recipeBuilder().EUt(120).duration(30)
                .input(dust, Wheat, 1)
                .fluidInputs(Water.getFluid(16))
                .outputs(GTFOMetaItem.PREMIXED_PASTA_DOUGH.getStackForm())
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder().EUt(120).duration(600)
                .inputs(GTFOMetaItem.PREMIXED_PASTA_DOUGH.getStackForm())
                .outputs(GTFOMetaItem.PREMIXED_PASTA_DOUGH.getStackForm())
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder().EUt(120).duration(600)
                .inputs(GTFOMetaItem.PREMIXED_PASTA_DOUGH.getStackForm())
                .fluidInputs(GTFOMaterialHandler.Egg.getFluid(400))
                .outputs(GTFOMetaItem.EGG_PASTA_DOUGH.getStackForm())
                .buildAndRegister();


    }
}
