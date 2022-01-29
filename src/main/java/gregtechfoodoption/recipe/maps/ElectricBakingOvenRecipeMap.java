package gregtechfoodoption.recipe.maps;

import gregtech.api.recipes.RecipeMap;
import gregtechfoodoption.recipe.builder.ElectricBakingOvenRecipeBuilder;

public class ElectricBakingOvenRecipeMap<R> extends RecipeMap<ElectricBakingOvenRecipeBuilder> {

    public static ElectricBakingOvenRecipeMap INSTANCE;

    public ElectricBakingOvenRecipeMap(String unlocalizedName, R defaultRecipe) {
        super(unlocalizedName, 1, 1, 1, 1, 0, 0, 0, 0, (ElectricBakingOvenRecipeBuilder) defaultRecipe, false);
        INSTANCE = this;
    }
}
