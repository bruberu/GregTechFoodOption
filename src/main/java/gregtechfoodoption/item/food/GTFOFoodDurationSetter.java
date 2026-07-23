package gregtechfoodoption.item.food;

import net.minecraft.item.ItemStack;

import gregtechfoodoption.item.GTFOFoodStats;
import gregtechfoodoption.utils.GTFOUtils;

public class GTFOFoodDurationSetter {

    public static int duration(ItemStack stack) {
        GTFOFoodStats stats = GTFOUtils.getGTFOFoodStats(stack);
        if (stats != null) {
            return stats.getEatingDuration();
        }
        return 32;
    }
}
