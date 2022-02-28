package gregtechfoodoption.recipe;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.IntCircuitRecipeBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.sound.GTSounds;
import gregtechfoodoption.client.GTFOGuiTextures;
import gregtechfoodoption.recipe.builder.BakingOvenRecipeBuilder;
import gregtechfoodoption.recipe.builder.ElectricBakingOvenRecipeBuilder;
import gregtechfoodoption.recipe.maps.ElectricBakingOvenRecipeMap;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenProperty;

@ZenClass("mods.gregtechfoodoption.recipe.GTFORecipeMaps")

public class GTFORecipeMaps {
    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> SLICER_RECIPES = new RecipeMap<>("slicer",
            1, 2, 1, 2, 0, 1, 0, 1, new SimpleRecipeBuilder(), false)
            .setSound(GTSounds.CUT)
            .setSlotOverlay(false, false, false, GTFOGuiTextures.SLICER_INPUT_OVERLAY)
            .setSlotOverlay(false, false, true, GTFOGuiTextures.SLICER_CUTTER_OVERLAY)
            .setSlotOverlay(true, false, false, GTFOGuiTextures.SLICER_OUTPUT_OVERLAY)
            .setSlotOverlay(true, false, true, GuiTextures.DUST_OVERLAY)
            .setProgressBar(GTFOGuiTextures.PROGRESS_BAR_SLICER, ProgressWidget.MoveType.HORIZONTAL);

    @ZenProperty
    public static final RecipeMap<IntCircuitRecipeBuilder> CUISINE_ASSEMBLER_RECIPES = new RecipeMap<>("cuisine_assembler",
            0, 6, 0, 2, 0, 3, 0, 1, new IntCircuitRecipeBuilder(), false)
            .setSound(GTSounds.ASSEMBLER)
            .setSlotOverlay(false, false, true, GuiTextures.INT_CIRCUIT_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> MICROWAVE_RECIPES = new RecipeMap<>("microwave",
            1, 1, 1, 1, 0, 0, 0, 0, new SimpleRecipeBuilder(), false)
            .setSound(GTSounds.ARC)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL);

    @ZenProperty
    public static final ElectricBakingOvenRecipeMap<ElectricBakingOvenRecipeBuilder> ELECTRIC_BAKING_OVEN_RECIPES = (ElectricBakingOvenRecipeMap<ElectricBakingOvenRecipeBuilder>) new ElectricBakingOvenRecipeMap<>("electric_baking_oven", new ElectricBakingOvenRecipeBuilder())
            .setSound(GTSounds.FURNACE)
            .setSlotOverlay(false, false, true, GuiTextures.FURNACE_OVERLAY_1)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressWidget.MoveType.HORIZONTAL);

    @ZenProperty
    public static final RecipeMap<BakingOvenRecipeBuilder> BAKING_OVEN_RECIPES = new RecipeMap<>("baking_oven", 1, 2, 0, 1, 0, 0, 0, 0, new BakingOvenRecipeBuilder(), false)
            .setSound(GTSounds.FURNACE)
            .onRecipeBuild(recipeBuilder -> {
                if (((BakingOvenRecipeBuilder) recipeBuilder).getTemperature() != -1) {
                    ELECTRIC_BAKING_OVEN_RECIPES.recipeBuilder().setTemp(((BakingOvenRecipeBuilder) recipeBuilder).getTemperature())
                            .duration(recipeBuilder.getDuration() / 4)
                            .inputs(recipeBuilder.getInputs().get(0)) // We don't need charcoal.
                            .outputs(recipeBuilder.getOutputs())
                            .buildAndRegister();
                }
            });
}
