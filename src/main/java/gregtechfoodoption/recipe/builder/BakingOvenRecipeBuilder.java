package gregtechfoodoption.recipe.builder;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.properties.impl.PrimitiveProperty;
import gregtech.api.util.ValidationResult;
import stanhebben.zenscript.annotations.ZenMethod;

import javax.annotation.Nonnull;

public class BakingOvenRecipeBuilder extends RecipeBuilder<BakingOvenRecipeBuilder> {
    public BakingOvenRecipeBuilder() {
    }

    private BakingOvenRecipeBuilder(BakingOvenRecipeBuilder bakingOvenRecipeBuilder) {
        super(bakingOvenRecipeBuilder);
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
        this.applyProperty(PrimitiveProperty.getInstance(), true);
        return super.build();
    }

    @ZenMethod
    public BakingOvenRecipeBuilder temperature(int temperature) {
        this.applyProperty(ElectricBakingOvenRecipeBuilder.TemperatureProperty.getInstance(), temperature);
        return this;
    }

    @ZenMethod
    public int getTemperature() {
        return this.recipePropertyStorage == null ? -1 : this.recipePropertyStorage.get(ElectricBakingOvenRecipeBuilder.TemperatureProperty.getInstance(), -1);
    }
}
