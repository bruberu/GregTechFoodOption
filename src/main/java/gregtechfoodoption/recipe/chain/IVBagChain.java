package gregtechfoodoption.recipe.chain;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtechfoodoption.item.GTFOMetaItem.*;

import gregtech.api.unification.ore.OrePrefix;

public class IVBagChain {

    public static void init() {
        // IV Bag
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(100)
                .input(OrePrefix.foil, PolyvinylChloride)
                .input(OrePrefix.pipeSmallItem, PolyvinylChloride)
                .input(OrePrefix.bolt, StainlessSteel)
                .outputs(IV_BAG.getStackForm(1))
                .buildAndRegister();
    }
}
