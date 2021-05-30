package com.bruberu.gregtechfoodoption.recipe;

import com.bruberu.gregtechfoodoption.client.GTFOGuiTextures;
import com.bruberu.gregtechfoodoption.recipe.multiblock.BakingOvenRecipe;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenProperty;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@ZenClass("mods.gregtechfoodoption.recipe.GTFORecipeMaps")

public class GTFORecipeMaps {
    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> SLICER_RECIPES = new RecipeMap<>("slicer",
            1, 2, 1, 2, 0, 1, 0, 1, new SimpleRecipeBuilder())
            .setSlotOverlay(false, false, false, GTFOGuiTextures.SLICER_INPUT_OVERLAY)
            .setSlotOverlay(false, false, true, GTFOGuiTextures.SLICER_CUTTER_OVERLAY)
            .setSlotOverlay(true, false, false, GTFOGuiTextures.SLICER_OUTPUT_OVERLAY)
            .setSlotOverlay(true, false, true, GuiTextures.DUST_OVERLAY)
            .setProgressBar(GTFOGuiTextures.PROGRESS_BAR_SLICER, ProgressWidget.MoveType.HORIZONTAL);
    @ZenProperty
    public static final List<BakingOvenRecipe> BAKING_OVEN_RECIPES = new CopyOnWriteArrayList<>();

    @ZenMethod
    public static List<BakingOvenRecipe> getBakingOvenRecipes() {
        return BAKING_OVEN_RECIPES;
    }

}
