package gregtechfoodoption.recipe.chain;

import gregtechfoodoption.GTFOMaterialHandler;
import gregtechfoodoption.utils.GTFOUtils;

import static gregtech.api.recipes.RecipeMaps.LATHE_RECIPES;
import static gregtech.api.recipes.RecipeMaps.MIXER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtechfoodoption.GTFOMaterialHandler.KubideMeat;
import static gregtechfoodoption.item.GTFOMetaItem.*;

// Kebab Chain caus why not?!
public class KebabChain {
    public static void init(){
        LATHE_RECIPES.recipeBuilder().EUt(40).duration(25)
                .input(stickLong,Steel,3)
                .outputs(SKEWER.getStackForm(2))
                .buildAndRegister();

        LATHE_RECIPES.recipeBuilder().EUt(40).duration(120)
                .input(stick,Steel,3)
                .outputs(SKEWER.getStackForm(1))
                .buildAndRegister();

        LATHE_RECIPES.recipeBuilder().EUt(40).duration(25)
                .input(stickLong,StainlessSteel,3)
                .outputs(SKEWER.getStackForm(6))
                .buildAndRegister();

        LATHE_RECIPES.recipeBuilder().EUt(40).duration(120)
                .input(stick,StainlessSteel,3)
                .outputs(SKEWER.getStackForm(3))
                .buildAndRegister();

        LATHE_RECIPES.recipeBuilder().EUt(200).duration(6)
                .input(stickLong,Titanium,3)
                .outputs(SKEWER.getStackForm(24))
                .buildAndRegister();

        LATHE_RECIPES.recipeBuilder().EUt(200).duration(25)
                .input(stick,Titanium,3)
                .outputs(SKEWER.getStackForm(12))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().EUt(16).duration(90)
                .inputs(GTFOMaterialHandler.Fat.getItemStack(1),ONION_SLICE.getStackForm(4),TOMATO_SLICE.getStackForm(4))
                .input(dust, Meat,4)
                .outputs(KubideMeat.getItemStack(5))
                .buildAndRegister();

        GTFOUtils.addBakingOvenRecipes(KEBAB_KUBIDEH.getStackForm(),KEBAB_KUBIDEH_COOKED.getStackForm(),1000,275,4);
    }
}
