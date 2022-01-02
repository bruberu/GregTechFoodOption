package gregtechfoodoption.recipe.chain;

import gregtech.api.unification.material.Materials;
import net.minecraft.init.Items;

import static gregtechfoodoption.GTFOMaterialHandler.*;
import static gregtechfoodoption.item.GTFOMetaItem.LENINADE;
import static gregtechfoodoption.item.GTFOMetaItem.VODKA;
import static gregtech.api.recipes.RecipeMaps.*;

public class AlcoholChain {
    public static void init() {
        FERMENTING_RECIPES.recipeBuilder().EUt(8).duration(1000)
                .input(Items.POTATO)
                .fluidInputs(Materials.Water.getFluid(1000))
                .fluidOutputs(PotatoJuice.getFluid(1000))
                .buildAndRegister();

        FERMENTING_RECIPES.recipeBuilder().EUt(8).duration(3000)
                .fluidInputs(PotatoJuice.getFluid(2000))
                .fluidOutputs(Vodka.getFluid(2000))
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
    }
}