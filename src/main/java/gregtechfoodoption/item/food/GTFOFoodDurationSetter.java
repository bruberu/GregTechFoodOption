package gregtechfoodoption.item.food;

import gregtechfoodoption.item.GTFOFoodStats;
import gregtechfoodoption.utils.GTFOUtils;
import net.minecraft.item.ItemStack;

public class GTFOFoodDurationSetter {
    public static int duration(ItemStack stack) {
        GTFOFoodStats stats = GTFOUtils.getGTFOFoodStats(stack);
        if (stats != null) {
            return stats.getEatingDuration();
        }
        return 32;
    }
}
