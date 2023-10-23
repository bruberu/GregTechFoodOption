package gregtechfoodoption.integration.jei;

import gregtech.api.gui.GuiTextures;
import gregtech.common.items.MetaItems;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.Tags;
import gregtechfoodoption.item.GTFOMetaItem;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.resources.I18n;
import org.jetbrains.annotations.Nullable;

public class EatingRecipeCategory implements IRecipeCategory<EatingRecipeInfo> {
    private final IDrawable icon;
    protected final IDrawable slot;
    private final IDrawable arrow;
    private final IGuiHelper guiHelper;
    public EatingRecipeCategory(IGuiHelper guiHelper) {
        this.guiHelper = guiHelper;

        this.icon = guiHelper.createDrawableIngredient(GTFOMetaItem.DIRTY_PLATE.getStackForm());
        this.slot = guiHelper.drawableBuilder(GuiTextures.SLOT.imageLocation, 0, 0, 18, 18).setTextureSize(18, 18).build();
        this.arrow = guiHelper.drawableBuilder(GuiTextures.PROGRESS_BAR_ARROW.imageLocation, 0, 20, 20, 20).setTextureSize(20, 40).build();
    }
    @Override
    public String getUid() {
        return GTFOValues.MODID + ":eating_recipe";
    }

    @Override
    public String getTitle() {
        return I18n.format("eating.output.name");
    }

    @Override
    public String getModName() {
        return Tags.MODNAME;
    }

    @Override
    public IDrawable getBackground() {
        return guiHelper.createBlankDrawable(176, 50);
    }

    @Nullable
    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, EatingRecipeInfo recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();

        itemStackGroup.init(0, true, 54, 16);
        itemStackGroup.set(0, recipeWrapper.foodInput);
        itemStackGroup.init(1, true, 104, 16);
        itemStackGroup.set(1, recipeWrapper.substrateOutput);
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        slot.draw(minecraft, 54, 16);
        slot.draw(minecraft, 104, 16);
        arrow.draw(minecraft, 77, 14);
    }
}
