package com.bruberu.gregtechfoodoption.recipe;

import com.bruberu.gregtechfoodoption.client.GTFOGuiTextures;
import com.bruberu.gregtechfoodoption.recipe.builder.ElectricBakingOvenRecipeBuilder;
import com.bruberu.gregtechfoodoption.recipe.multiblock.BakingOvenRecipe;
import com.bruberu.gregtechfoodoption.recipe.multiblock.ElectricBakingOvenRecipeMap;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.IntCircuitRecipeBuilder;
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

    public static final RecipeMap<IntCircuitRecipeBuilder> CUISINE_ASSEMBLER_RECIPES = new RecipeMap<>("cuisine_assembler",
            0, 6, 0, 2, 0, 3, 0, 1, new IntCircuitRecipeBuilder())
            .setSlotOverlay(false, false, true, GuiTextures.INT_CIRCUIT_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL);


    @ZenProperty
    public static final ElectricBakingOvenRecipeMap<ElectricBakingOvenRecipeBuilder> ELECTRIC_BAKING_OVEN_RECIPES = (ElectricBakingOvenRecipeMap<ElectricBakingOvenRecipeBuilder>) new ElectricBakingOvenRecipeMap<>("electric_baking_oven", new ElectricBakingOvenRecipeBuilder())
            .setSlotOverlay(false, false, true, GuiTextures.FURNACE_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressWidget.MoveType.HORIZONTAL);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> CULINARY_GENERATOR_RECIPES = new RecipeMap<>("culinary_generator",
            0, 6, 0, 4, 0, 2, 0, 2, new SimpleRecipeBuilder())
            .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressWidget.MoveType.HORIZONTAL);

    @ZenProperty
    public static final List<BakingOvenRecipe> BAKING_OVEN_RECIPES = new CopyOnWriteArrayList<>();

    @ZenMethod
    public static List<BakingOvenRecipe> getBakingOvenRecipes() {
        return BAKING_OVEN_RECIPES;
    }

}
