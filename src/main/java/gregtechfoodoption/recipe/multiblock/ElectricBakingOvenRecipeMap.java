package gregtechfoodoption.recipe.multiblock;

import gregtechfoodoption.recipe.builder.ElectricBakingOvenRecipeBuilder;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.util.GTUtility;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.List;

public class ElectricBakingOvenRecipeMap<R> extends RecipeMap<ElectricBakingOvenRecipeBuilder> {

    public static ElectricBakingOvenRecipeMap INSTANCE;

    public ElectricBakingOvenRecipeMap(String unlocalizedName, R defaultRecipe) {
        super(unlocalizedName, 1, 1, 1, 1, 0, 0, 0, 0, (ElectricBakingOvenRecipeBuilder) defaultRecipe, false);
        INSTANCE = this;
    }

    @Nullable
    public Recipe findRecipe(int temperature, List<ItemStack> inputs) {
        if (this.getRecipeList().isEmpty())
            return null;
        if (GTUtility.amountOfNonEmptyStacks(inputs) < this.getMinInputs()) {
            return null;
        }
            for (Recipe recipe : this.getRecipeList()) {
                Object recipeTemp = recipe.getRecipePropertyStorage().getRawRecipePropertyValue("temperature");
                if (matches(recipe, inputs)) {
                    assert recipeTemp instanceof Integer;
                    return temperature == (Integer) recipeTemp ? recipe : null;
                }
            }
            return null;
    }

    public boolean matches(Recipe recipe, List<ItemStack> inputs) {
        if (recipe.getInputs().isEmpty()) {
            return false;
        }


        Pair<Boolean, Integer[]> items = this.matchesItems(recipe.getInputs(), inputs);
        if (!(Boolean) items.getKey()) {
            return false;
        }

        return true;
    }

    private Pair<Boolean, Integer[]> matchesItems(List<CountableIngredient> inputs1, List<ItemStack> inputs2) {
        Integer[] itemAmountInSlot = new Integer[inputs2.size()];

        for (int i = 0; i < itemAmountInSlot.length; i++) {
            ItemStack itemInSlot = inputs2.get(i);
            itemAmountInSlot[i] = itemInSlot.isEmpty() ? 0 : itemInSlot.getCount();
        }

        for (CountableIngredient ingredient : inputs1) {
            int ingredientAmount = ingredient.getCount();
            boolean isNotConsumed = false;
            if (ingredientAmount == 0) {
                ingredientAmount = 1;
                isNotConsumed = true;
            }
            for (int i = 0; i < inputs1.size(); i++) {
                ItemStack inputStack = inputs2.get(i);
                if (inputStack.isEmpty() || !ingredient.getIngredient().apply(inputStack))
                    continue;
                int itemAmountToConsume = Math.min(itemAmountInSlot[i], ingredientAmount);
                ingredientAmount -= itemAmountToConsume;
                if (!isNotConsumed) itemAmountInSlot[i] -= itemAmountToConsume;
                if (ingredientAmount == 0) break;
            }
            if (ingredientAmount > 0)
                return Pair.of(false, itemAmountInSlot);
        }

        return Pair.of(true, itemAmountInSlot);
    }

}
