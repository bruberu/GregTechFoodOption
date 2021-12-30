package com.bruberu.gregtechfoodoption.recipe.builder;

import com.google.common.collect.ImmutableMap;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.recipeproperties.RecipeProperty;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.GTLog;
import gregtech.api.util.ValidationResult;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CulinaryGeneratorRecipeBuilder extends RecipeBuilder<CulinaryGeneratorRecipeBuilder> {

    private int unitGlucose;
    private int unitTriglyceride;

    public CulinaryGeneratorRecipeBuilder() {
    }

    public CulinaryGeneratorRecipeBuilder(RecipeBuilder<CulinaryGeneratorRecipeBuilder> recipeBuilder) {
        super(recipeBuilder);
    }

    public CulinaryGeneratorRecipeBuilder(RecipeBuilder<CulinaryGeneratorRecipeBuilder> recipeBuilder, int unitGlucose, int unitTriglyceride) {
        super(recipeBuilder);
        this.unitGlucose = unitGlucose;
        this.unitTriglyceride = unitTriglyceride;
    }

    @Override
    public CulinaryGeneratorRecipeBuilder copy() {
        return new CulinaryGeneratorRecipeBuilder(this, this.unitGlucose, this.unitTriglyceride);
    }

    @Override
    public boolean applyProperty(String key, Object value) {
        switch (key) {
            case "unit_glucose":
                this.unitGlucose(((Number) value).intValue());
                return true;
            case "unit_triglyceride":
                this.unitTriglyceride(((Number) value).intValue());
                return true;
        }
        return false;
    }

    public CulinaryGeneratorRecipeBuilder unitGlucose(int unitGlucose) {
        if (unitGlucose < 0) {
            GTLog.logger.error("Glucose units cannot be less than 0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.unitGlucose = unitGlucose;
        return this;
    }
    public CulinaryGeneratorRecipeBuilder unitTriglyceride(int unitTriglyceride) {
        if (unitTriglyceride < 0) {
            GTLog.logger.error("Triglyceride units cannot be less than 0", new IllegalArgumentException());
            recipeStatus = EnumValidationResult.INVALID;
        }
        this.unitTriglyceride = unitTriglyceride;
        return this;
    }

    @Override
    public ValidationResult<Recipe> build() {
        return ValidationResult.newResult(finalizeAndValidate(),
                new Recipe(inputs, outputs, chancedOutputs, fluidInputs, fluidOutputs,
                        duration, EUt, hidden));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(CulinaryGeneratorRecipeBuilder.UnitGlucoseProperty.getInstance().getKey(), unitGlucose)
                .append(CulinaryGeneratorRecipeBuilder.UnitTriglycerideProperty.getInstance().getKey(), unitTriglyceride)
                .toString();
    }

    private static class UnitGlucoseProperty extends RecipeProperty<Integer> {

        private static final String KEY = "temperature";

        private static CulinaryGeneratorRecipeBuilder.UnitGlucoseProperty INSTANCE;


        protected UnitGlucoseProperty() {
            super(KEY, Integer.class);
        }

        public static CulinaryGeneratorRecipeBuilder.UnitGlucoseProperty getInstance() {
            if (INSTANCE == null) {
                INSTANCE = new CulinaryGeneratorRecipeBuilder.UnitGlucoseProperty();
            }

            return INSTANCE;
        }

        @Override
        public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
            minecraft.fontRenderer.drawString(I18n.format("gregtechfoodoption.recipe.culinary_generator_unit_glucose",
                    value), x, y, color);
        }
    }
    private static class UnitTriglycerideProperty extends RecipeProperty<Integer> {

        private static final String KEY = "temperature";

        private static CulinaryGeneratorRecipeBuilder.UnitTriglycerideProperty INSTANCE;


        protected UnitTriglycerideProperty() {
            super(KEY, Integer.class);
        }

        public static CulinaryGeneratorRecipeBuilder.UnitTriglycerideProperty getInstance() {
            if (INSTANCE == null) {
                INSTANCE = new CulinaryGeneratorRecipeBuilder.UnitTriglycerideProperty();
            }

            return INSTANCE;
        }

        @Override
        public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
            minecraft.fontRenderer.drawString(I18n.format("gregtechfoodoption.recipe.culinary_generator_unit_triglyceride",
                    value), x, y, color);
        }
    }
}
