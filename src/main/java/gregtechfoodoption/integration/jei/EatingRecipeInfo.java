package gregtechfoodoption.integration.jei;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class EatingRecipeInfo implements IRecipeWrapper {
    public ItemStack foodInput;
    public ItemStack substrateOutput;

    public EatingRecipeInfo(ItemStack foodInput, ItemStack substrateOutput) {
        this.foodInput = foodInput;
        this.substrateOutput = substrateOutput;
    }
    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.ITEM, foodInput);
        ingredients.setOutput(VanillaTypes.ITEM, substrateOutput);
    }
}
