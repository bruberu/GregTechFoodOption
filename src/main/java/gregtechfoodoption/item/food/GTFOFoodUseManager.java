package gregtechfoodoption.item.food;

import gregtech.api.items.metaitem.FoodUseManager;
import gregtechfoodoption.item.GTFOFoodStats;
import net.minecraft.item.ItemStack;

public class GTFOFoodUseManager extends FoodUseManager {
    public GTFOFoodUseManager(GTFOFoodStats foodStats) {
        super(foodStats);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemStack) {
        return ((GTFOFoodStats) this.getFoodStats()).getEatingDuration() * 5 / 4; // You need to extend this so that the animation works correctly :P
    }
}
