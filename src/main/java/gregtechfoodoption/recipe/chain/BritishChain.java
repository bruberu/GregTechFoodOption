package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.RecipeMaps;
import gregtechfoodoption.item.GTFOMetaItem;

import static gregtech.api.unification.material.Materials.SodiumBicarbonate;
import static gregtech.api.unification.material.Materials.Water;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtechfoodoption.GTFOMaterialHandler.BakingSodaSolution;

public class BritishChain {
    public static void init() {
        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(8).duration(60)
                .input(dust, SodiumBicarbonate)
                .fluidInputs(Water.getFluid(1000))
                .fluidOutputs(BakingSodaSolution.getFluid(1000))
                .buildAndRegister();

        RecipeMaps.BREWING_RECIPES.recipeBuilder().EUt(4).duration(1280)
                .fluidInputs(BakingSodaSolution.getFluid(500))
                .inputs(GTFOMetaItem.PEAS.getStackForm())
                .outputs(GTFOMetaItem.MUSHY_PEAS.getStackForm())
                .buildAndRegister();


    }
}
