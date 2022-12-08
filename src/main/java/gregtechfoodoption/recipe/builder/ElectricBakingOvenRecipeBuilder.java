package gregtechfoodoption.recipe.builder;

import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.recipeproperties.RecipeProperty;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.GTLog;
import gregtechfoodoption.machines.multiblock.MetaTileEntityElectricBakingOven;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Nonnull;

public class ElectricBakingOvenRecipeBuilder extends RecipeBuilder<ElectricBakingOvenRecipeBuilder> {

    private int temp;

    public ElectricBakingOvenRecipeBuilder() {
    }

    public ElectricBakingOvenRecipeBuilder(RecipeBuilder<ElectricBakingOvenRecipeBuilder> recipeBuilder) {
        super(recipeBuilder);
    }

    @Override
    public ElectricBakingOvenRecipeBuilder copy() {
        return new ElectricBakingOvenRecipeBuilder(this);
    }

    public ElectricBakingOvenRecipeBuilder setTemp(int temperature) {
        this.temp = temperature;
        this.applyProperty(TemperatureProperty.getInstance(), temperature);
        return this;
    }

    @Override
    public boolean applyProperty(@Nonnull String key, Object value) {
        if (key.equals(TemperatureProperty.KEY)) {
            this.setTemp(((Number) value).intValue());
            return true;
        }
        return super.applyProperty(key, value);
    }

    @Override
    protected EnumValidationResult finalizeAndValidate() {
        if (this.temp <= 300) {
            GTLog.logger.error("Temperature cannot be less or equal to 300", new IllegalArgumentException());
            this.recipeStatus = EnumValidationResult.INVALID;
        }
        this.EUt(MetaTileEntityElectricBakingOven.temperatureEnergyCost(this.temp, 1));
        return this.recipeStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append(TemperatureProperty.getInstance().getKey(), temp)
                .toString();
    }

    public static class TemperatureProperty extends RecipeProperty<Integer> {

        public static final String KEY = "temperature";

        private static TemperatureProperty INSTANCE;


        protected TemperatureProperty() {
            super(KEY, Integer.class);
        }

        public static TemperatureProperty getInstance() {
            if (INSTANCE == null) {
                INSTANCE = new TemperatureProperty();
            }

            return INSTANCE;
        }

        @Override
        public void drawInfo(Minecraft minecraft, int x, int y, int color, Object value) {
            minecraft.fontRenderer.drawString(I18n.format("gregtechfoodoption.recipe.baking_oven_temperature",
                    value), x, y, color);
        }
    }
}
