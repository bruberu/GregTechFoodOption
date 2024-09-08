package gregtechfoodoption.recipe;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMapBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.core.sound.GTSoundEvents;
import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.client.GTFOGuiTextures;
import gregtechfoodoption.recipe.builder.BakingOvenRecipeBuilder;
import gregtechfoodoption.recipe.builder.ElectricBakingOvenRecipeBuilder;
import gregtechfoodoption.recipe.builder.MobProximityRecipeBuilder;
import net.minecraft.util.ResourceLocation;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenProperty;

@ZenClass("mods.gregtechfoodoption.recipe.GTFORecipeMaps")

public class GTFORecipeMaps {
    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> SLICER_RECIPES = new RecipeMapBuilder<>("slicer", new SimpleRecipeBuilder())
            .itemInputs(2)
            .itemOutputs(2)
            .fluidInputs(1)
            .fluidOutputs(1)
            .itemSlotOverlay(GTFOGuiTextures.SLICER_INPUT_OVERLAY, false, false)
            .itemSlotOverlay(GTFOGuiTextures.SLICER_CUTTER_OVERLAY, false, true)
            .itemSlotOverlay(GTFOGuiTextures.SLICER_OUTPUT_OVERLAY, true, false)
            .itemSlotOverlay(GuiTextures.DUST_OVERLAY, true, true)
            .progressBar(GTFOGuiTextures.PROGRESS_BAR_SLICER, ProgressWidget.MoveType.HORIZONTAL)
            .build()
            .setSound(GTSoundEvents.CUT);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> CUISINE_ASSEMBLER_RECIPES = new RecipeMapBuilder<>("cuisine_assembler", new SimpleRecipeBuilder())
            .itemInputs(6)
            .itemOutputs(2)
            .fluidInputs(3)
            .fluidOutputs(1)
            .itemSlotOverlay(GuiTextures.INT_CIRCUIT_OVERLAY, false, true)
            .progressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
            .build()
            .setSound(GTSoundEvents.ASSEMBLER);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> MICROWAVE_RECIPES = new RecipeMapBuilder<>("microwave", new SimpleRecipeBuilder())
            .itemInputs(1)
            .itemOutputs(1)
            .fluidInputs(0)
            .fluidOutputs(0)
            .progressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
            .build()
            .setSound(GTSoundEvents.ARC);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> MULTICOOKER_RECIPES = new RecipeMapBuilder<>("multicooker", new SimpleRecipeBuilder())
            .itemInputs(6)
            .itemOutputs(3)
            .fluidInputs(3)
            .fluidOutputs(2)
            .itemSlotOverlay(GuiTextures.HEATING_OVERLAY_1, false, true)
            .progressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL)
            .build()
            .setSound(GTSoundEvents.MIXER);

    @ZenProperty
    public static final RecipeMap<ElectricBakingOvenRecipeBuilder> ELECTRIC_BAKING_OVEN_RECIPES = new RecipeMapBuilder<>("electric_baking_oven", new ElectricBakingOvenRecipeBuilder())
            .itemInputs(1)
            .itemOutputs(1)
            .itemSlotOverlay(GuiTextures.FURNACE_OVERLAY_1, false, true)
            .progressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, ProgressWidget.MoveType.HORIZONTAL)
            .build()
            .setSound(GTSoundEvents.FURNACE);

    @ZenProperty
    public static final RecipeMap<BakingOvenRecipeBuilder> BAKING_OVEN_RECIPES = new RecipeMapBuilder<>("baking_oven", new BakingOvenRecipeBuilder())
            .itemInputs(2)
            .itemOutputs(1)
            .build()
            .setSound(GTSoundEvents.FURNACE)
            .onRecipeBuild(new ResourceLocation(GTFOValues.MODID, "baking_oven"), recipeBuilder -> {
                if (recipeBuilder.getTemperature() != -1) {
                    ELECTRIC_BAKING_OVEN_RECIPES.recipeBuilder().setTemp(recipeBuilder.getTemperature())
                            .duration(recipeBuilder.getDuration() / 4)
                            .inputs(recipeBuilder.getInputs().get(0)) // We don't need charcoal.
                            .outputs(recipeBuilder.getOutputs())
                            .buildAndRegister();
                }
                if (GTFOConfig.gtfoMiscConfig.bakingOvenReplacement) {
                    ModHandler.addSmeltingRecipe(recipeBuilder.getInputs().get(0).getInputStacks()[0], recipeBuilder.getOutputs().get(0), 0);
                }
            });

    @ZenProperty
    public static final RecipeMap<MobProximityRecipeBuilder> MOB_EXTRACTOR_RECIPES = new RecipeMapBuilder<>("mob_extractor", new MobProximityRecipeBuilder())
            .itemInputs(1)
            .itemOutputs(1)
            .fluidOutputs(1)
            .itemSlotOverlay(GuiTextures.INT_CIRCUIT_OVERLAY, false, true)
            .progressBar(GuiTextures.PROGRESS_BAR_EXTRACT, ProgressWidget.MoveType.HORIZONTAL)
            .build()
            .setSound(GTSoundEvents.COMPRESSOR);

    @ZenProperty
    public static final RecipeMap<SimpleRecipeBuilder> GREENHOUSE_RECIPES = new RecipeMapBuilder<>("greenhouse", new SimpleRecipeBuilder())
            .itemInputs(4)
            .itemOutputs(4)
            .fluidInputs(1)
            .fluidOutputs(1)
            .build()
            .setSound(GTSoundEvents.COOLING);
}
