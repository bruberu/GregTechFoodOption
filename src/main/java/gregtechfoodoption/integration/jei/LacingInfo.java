package gregtechfoodoption.integration.jei;

import gregtech.api.items.metaitem.MetaItem;
import gregtechfoodoption.item.food.GTFOFoodUseManager;
import gregtechfoodoption.potion.LacingEntry;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LacingInfo implements IRecipeWrapper {
    private final LacingEntry entry;

    public LacingInfo(LacingEntry entry) {
        this.entry = entry;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        List<List<ItemStack>> inputs = new ArrayList<>();
        inputs.add(Collections.singletonList(entry.getLacingItem()));
        inputs.add(JEIGTFOPlugin.FOOD_ITEMS);
        ingredients.setInputLists(VanillaTypes.ITEM, inputs);
    }

    public ItemStack getLacingItem() {
        return entry.getLacingItem();
    }

    public String getPotionName() {
        return entry.getAppliedEffect().getEffectName();
    }
    public int getPotionDuration() {
        return entry.getAppliedEffect().getDuration();
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        minecraft.fontRenderer.drawString(I18n.format("gregtechfoodoption.lacing.info.1") + " " + I18n.format(this.getPotionName()), 2, 30, 0x111111);
        minecraft.fontRenderer.drawString(I18n.format("gregtechfoodoption.lacing.info.2", this.getPotionDuration()), 2, 40, 0x111111);
    }
}
