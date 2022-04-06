package gregtechfoodoption.recipe.builder;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.recipeproperties.PrimitiveProperty;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.ValidationResult;
import stanhebben.zenscript.annotations.ZenMethod;

public class BakingOvenRecipeBuilder extends RecipeBuilder<BakingOvenRecipeBuilder> {
    private int temperature = -1;

    public BakingOvenRecipeBuilder() {
    }

    private BakingOvenRecipeBuilder(BakingOvenRecipeBuilder bakingOvenRecipeBuilder) {
        super(bakingOvenRecipeBuilder);
        this.temperature = bakingOvenRecipeBuilder.getTemperature();
    }

    @ZenMethod
    public static BakingOvenRecipeBuilder start() {
        return new BakingOvenRecipeBuilder();
    }

    @Override
    public BakingOvenRecipeBuilder copy() {
        return new BakingOvenRecipeBuilder(this);
    }

    @Override
    public ValidationResult<Recipe> build() {
        this.EUt(1);
        Recipe recipe = new Recipe(this.inputs, this.outputs, this.chancedOutputs, this.fluidInputs, this.fluidOutputs, this.duration, this.EUt, this.hidden, false);
        return !recipe.setProperty(PrimitiveProperty.getInstance(), true) ? ValidationResult.newResult(EnumValidationResult.INVALID, recipe) : ValidationResult.newResult(this.finalizeAndValidate(), recipe);
    }

    @ZenMethod
    public BakingOvenRecipeBuilder temperature(int temperature) {
        this.temperature = temperature;
        return this;
    }

    @ZenMethod
    public int getTemperature() {
        return temperature;
    }
}
