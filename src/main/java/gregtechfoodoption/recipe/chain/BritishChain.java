package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.common.items.MetaItems;
import gregtechfoodoption.item.GTFOMetaItem;
import gregtechfoodoption.recipe.GTFORecipeMaps;
import gregtechfoodoption.utils.GTFOUtils;
import net.minecraft.item.ItemStack;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustTiny;
import static gregtechfoodoption.GTFOMaterialHandler.*;
import static gregtechfoodoption.item.GTFOMetaItem.*;

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

        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(8).duration(200)
                .input(dust, Wheat, 6)
                .input(dustTiny, Salt, 1)
                .fluidInputs(PoorQualityBeer.getFluid(200))
                .fluidOutputs(BeerBatter.getFluid(200))
                .buildAndRegister();

        for (ItemStack fish : GTFOUtils.getFish())
            RecipeMaps.BREWING_RECIPES.recipeBuilder().EUt(16).duration(400)
                    .fluidInputs(BeerBatter.getFluid(40))
                    .inputs(fish)
                    .outputs(FRIED_FISH.getStackForm())
                    .buildAndRegister();

        ModHandler.addShapelessRecipe("fish_and_chips", FISH_AND_CHIPS.getStackForm(1), FRIED_FISH.getStackForm(), FRENCH_FRIES.getStackForm());
        GTFORecipeMaps.CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(8).duration(10)
                .inputs(FRIED_FISH.getStackForm(), FRENCH_FRIES.getStackForm())
                .outputs(FISH_AND_CHIPS.getStackForm())
                .buildAndRegister();

        GTFORecipeMaps.CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(64).duration(200)
                .inputs(BACON.getStackForm(), BAKED_BEANS.getStackForm(), MUSHROOM_SLICE.getStackForm(8), TOAST.getStackForm(), TOMATO_SLICE.getStackForm(2))
                .outputs(FULL_BREAKFAST.getStackForm())
                .buildAndRegister();

        RecipeMaps.MIXER_RECIPES.recipeBuilder().EUt(8).duration(80)
                .inputs(BEANS.getStackForm())
                .fluidInputs(TomatoSauce.getFluid(100))
                .outputs(BEANS_WITH_SAUCE.getStackForm())
                .buildAndRegister();
        GTFOUtils.addBakingOvenRecipes(BEANS_WITH_SAUCE.getStackForm(), BAKED_BEANS.getStackForm(),
                1000, 450, 3);
        ModHandler.addShapelessRecipe("beans_on_toast", BEANS_ON_TOAST.getStackForm(1), BAKED_BEANS.getStackForm(), TOAST.getStackForm());

        GTFORecipeMaps.CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(64).duration(200)
                .inputs(MashedPotato.getItemStack(), CookedMinceMeat.getItemStack())
                .outputs(SHEPHERDS_PIE.getStackForm())
                .buildAndRegister();

        GTFORecipeMaps.CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(24).duration(400)
                .inputs(DOUGH.getStackForm())
                .fluidInputs(Butter.getFluid(100))
                .outputs(LaminatedDough.getItemStack())
                .buildAndRegister();

        GTFORecipeMaps.CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(8).duration(100)
                .input(dust, Meat)
                .inputs(LaminatedDough.getItemStack())
                .outputs(UNCOOKED_SAUSAGE_ROLL.getStackForm())
                .buildAndRegister();
        GTFOUtils.addBakingOvenRecipes(UNCOOKED_SAUSAGE_ROLL.getStackForm(), SAUSAGE_ROLL.getStackForm(), 250, 460, 2);
        RecipeMaps.EXTRUDER_RECIPES.recipeBuilder().EUt(16).duration(100)
                .inputs(MeatIngot.getItemStack())
                .notConsumable(MetaItems.SHAPE_EXTRUDER_ROD)
                .outputs(SAUSAGE.getStackForm())
                .buildAndRegister();
    }
}
