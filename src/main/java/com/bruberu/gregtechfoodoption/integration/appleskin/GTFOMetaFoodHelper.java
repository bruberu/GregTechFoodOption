package com.bruberu.gregtechfoodoption.integration.appleskin;


import gregtech.api.items.materialitem.MaterialMetaItem;
import gregtech.api.items.metaitem.FoodUseManager;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IFoodBehavior;
import gregtech.api.items.metaitem.stats.IItemUseManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import squeek.appleskin.helpers.FoodHelper;

//Code yoinked from @idcppl (Zook) of Greg's Harvestcraft
public class GTFOMetaFoodHelper extends FoodHelper {
    public static boolean isFood(ItemStack stackInHand) {
        if(stackInHand.getItem() instanceof MetaItem) {
            MetaItem<?> metaItem = (MetaItem<?>) stackInHand.getItem();
            if(metaItem instanceof MaterialMetaItem && ((MaterialMetaItem) metaItem).getMaterial(stackInHand) != null) {
                return false;
            } else {
                MetaItem<?>.MetaValueItem metaValueItem = metaItem.getItem(stackInHand);
                IItemUseManager useManager = metaValueItem.getUseManager();
                if (useManager != null) {
                    return useManager instanceof FoodUseManager;
                }
            }
        }
        return false;
    }

    public static FoodHelper.BasicFoodValues getDefaultFoodValues(ItemStack stackInHand) {
        MetaItem<?> metaItem = (MetaItem<?>) stackInHand.getItem();
        MetaItem<?>.MetaValueItem metaValueItem = metaItem.getItem(stackInHand);
        IItemUseManager useManager = metaValueItem.getUseManager();
        FoodUseManager foodUseManager = (FoodUseManager) useManager;
        IFoodBehavior foodStats = foodUseManager.getFoodStats();
        int hunger = foodStats.getFoodLevel(stackInHand, null);
        float saturationModifier = foodStats.getSaturation(stackInHand, null);

        return new FoodHelper.BasicFoodValues(hunger, saturationModifier);
    }

    public static FoodHelper.BasicFoodValues getModifiedFoodValues(ItemStack itemStack, EntityPlayer player) {
        return getDefaultFoodValues(itemStack);
    }
}
