package gregtechfoodoption.integration.appleskin;


import gregtech.api.items.metaitem.FoodUseManager;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.api.items.metaitem.stats.IFoodBehavior;
import gregtech.api.items.metaitem.stats.IItemUseManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import squeek.appleskin.helpers.FoodHelper;

//Code yoinked from @idcppl (Zook) of Greg's Harvestcraft
public class GTFOMetaFoodHelper extends FoodHelper {
    public static boolean isFood(ItemStack stackInHand) {
        if (stackInHand.getItem() instanceof MetaItem) {
            MetaItem<?>.MetaValueItem metaValueItem = ((MetaItem) stackInHand.getItem()).getItem(stackInHand);
            if (metaValueItem != null) {
                IItemUseManager useManager = metaValueItem.getUseManager();
                return useManager instanceof FoodUseManager;
            }
        }
        return false;
    }

    public static FoodHelper.BasicFoodValues getDefaultFoodValues(ItemStack stackInHand) {
        MetaItem<?> metaItem = (MetaItem<?>) stackInHand.getItem();
        MetaItem<?>.MetaValueItem metaValueItem = metaItem.getItem(stackInHand);
        IItemUseManager useManager = metaValueItem.getUseManager();
        if (useManager != null) {
            if (useManager instanceof FoodUseManager) {
                FoodUseManager foodUseManager = (FoodUseManager) useManager;
                IFoodBehavior foodStats = foodUseManager.getFoodStats();
                int hunger = foodStats.getFoodLevel(stackInHand, null);
                float saturationModifier = foodStats.getSaturation(stackInHand, null);

                return new BasicFoodValues(hunger, saturationModifier);
            }
        }
        return new BasicFoodValues(0, 0f);
    }

    public static FoodHelper.BasicFoodValues getModifiedFoodValues(ItemStack itemStack, EntityPlayer player) {
        return getDefaultFoodValues(itemStack);
    }
}
