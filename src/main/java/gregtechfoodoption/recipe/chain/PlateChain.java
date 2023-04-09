package gregtechfoodoption.recipe.chain;

import gregtech.api.unification.material.Materials;
import gregtechfoodoption.GTFOMaterialHandler;
import gregtechfoodoption.item.GTFOMetaItem;
import net.minecraft.init.Items;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.Glass;
import static gregtech.api.unification.ore.OrePrefix.dust;

public class PlateChain {
    public static void init() {
        MIXER_RECIPES.recipeBuilder().EUt(8).duration(40)
                .input(Items.CLAY_BALL)
                .input(dust, Materials.Stone)
                .input(dust, Materials.Apatite, 2)
                .outputs(GTFOMaterialHandler.BoneChinaClay.getItemStack(4))
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder().EUt(8).duration(80)
                .inputs(GTFOMaterialHandler.BoneChinaClay.getItemStack())
                .outputs(GTFOMetaItem.UNFIRED_PLATE.getStackForm())
                .buildAndRegister();
        LATHE_RECIPES.recipeBuilder().EUt(8).duration(80)
                .inputs(GTFOMaterialHandler.BoneChinaClay.getItemStack())
                .outputs(GTFOMetaItem.UNFIRED_BOWL.getStackForm())
                .buildAndRegister();

        ALLOY_SMELTER_RECIPES.recipeBuilder().EUt(8).duration(200)
                .input(dust, Glass)
                .inputs(GTFOMetaItem.UNFIRED_PLATE.getStackForm())
                .outputs(GTFOMetaItem.PLATE.getStackForm())
                .buildAndRegister();
        ALLOY_SMELTER_RECIPES.recipeBuilder().EUt(8).duration(200)
                .input(dust, Glass)
                .inputs(GTFOMetaItem.UNFIRED_BOWL.getStackForm())
                .outputs(GTFOMetaItem.CERAMIC_BOWL.getStackForm())
                .buildAndRegister();
    }

}
