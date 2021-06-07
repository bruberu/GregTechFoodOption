package com.bruberu.gregtechfoodoption.integration.jei.multi;

import com.bruberu.gregtechfoodoption.GregTechFoodOption;
import com.bruberu.gregtechfoodoption.recipe.multiblock.BakingOvenRecipe;
import gregtech.api.gui.GuiTextures;
import gregtech.integration.jei.recipe.primitive.PrimitiveRecipeCategory;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;

public class BakingOvenRecipeCategory extends PrimitiveRecipeCategory<BakingOvenRecipe, BakingOvenRecipeWrapper> {

    protected final IDrawable slot;
    protected final IDrawable progressBar;

    public BakingOvenRecipeCategory(IGuiHelper guiHelper) {
        super("baking_oven",
                "gregtechfoodoption.machine.baking_oven.name",
                guiHelper.createBlankDrawable(140, 60), guiHelper);

        this.slot = guiHelper.createDrawable(GuiTextures.SLOT.imageLocation, 0, 0, 18, 18, 18, 18);
        this.progressBar = guiHelper.createDrawable(GuiTextures.PROGRESS_BAR_MACERATE.imageLocation, 0, 0, 20, 15, 20, 30);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, BakingOvenRecipeWrapper recipeWrapper,
                          IIngredients ingredients) {
        IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();
        itemStackGroup.init(0, true, 41, 4);
        itemStackGroup.init(1, true, 41, 22);

        itemStackGroup.init(2, false, 93, 13);
        itemStackGroup.set(ingredients);
    }

    @Override
    public IRecipeWrapper getRecipeWrapper(BakingOvenRecipe recipe) {
        return new BakingOvenRecipeWrapper(recipe);
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        this.slot.draw(minecraft, 41, 4);
        this.slot.draw(minecraft, 41, 22);
        this.slot.draw(minecraft, 93, 13);
        this.progressBar.draw(minecraft, 66, 14);
    }

    @Override
    public String getUid() {
        return GregTechFoodOption.MODID + ":" + "baking_oven";
    }
}
