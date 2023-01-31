package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.ore.OrePrefix;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtechfoodoption.item.GTFOMetaItem.*;

public class IVBagChain {
    public static void init() {

        //IV Bag
        ASSEMBLER_RECIPES.recipeBuilder().EUt(30).duration(100)
                .input(OrePrefix.foil, PolyvinylChloride)
                .input(OrePrefix.pipeSmallItem, PolyvinylChloride)
                .input(OrePrefix.bolt, StainlessSteel)
                .outputs(IV_BAG.getStackForm(1))
                .buildAndRegister();

    }
}
