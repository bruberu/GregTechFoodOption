package gregtechfoodoption.integration.appleskin;

import net.minecraft.item.ItemStack;
import squeek.appleskin.helpers.FoodHelper;

public class GTFOAppleCoreIsolation {
    public static boolean isFood(ItemStack stackInHand) {
        return GTFOMetaFoodHelper.isFood(stackInHand);
    }

    public static FoodHelper.BasicFoodValues getDefaultFoodValues(ItemStack stackInHand) {
        return GTFOMetaFoodHelper.getDefaultFoodValues(stackInHand);
    }
}
