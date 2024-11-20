package gregtechfoodoption.recipe.chain;

import gregtech.api.unification.ore.OrePrefix;

import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.unification.material.Materials.PolyvinylChloride;
import static gregtech.api.unification.material.Materials.StainlessSteel;
import static gregtechfoodoption.item.GTFOMetaItem.IV_BAG;

public class IVBagChain {
    public static void init() {

        //IV Bag
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(100)
                .input(OrePrefix.foil, PolyvinylChloride)
                .input(OrePrefix.pipeSmallFluid, PolyvinylChloride)
                .input(OrePrefix.bolt, StainlessSteel)
                .outputs(IV_BAG.getStackForm(1))
                .buildAndRegister();

    }
}
