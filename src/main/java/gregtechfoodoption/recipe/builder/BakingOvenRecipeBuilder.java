package gregtechfoodoption.recipe.builder;

import gregtech.api.recipes.builders.PrimitiveRecipeBuilder;
import stanhebben.zenscript.annotations.ZenMethod;

public class BakingOvenRecipeBuilder extends PrimitiveRecipeBuilder {
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
