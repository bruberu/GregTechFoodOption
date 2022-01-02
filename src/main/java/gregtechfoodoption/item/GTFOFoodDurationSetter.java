package gregtechfoodoption.item;

import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.stats.IItemComponent;
import net.minecraft.item.ItemStack;

public class GTFOFoodDurationSetter {
    public static int duration(ItemStack stack) {
        if (stack.getItem() instanceof MetaItem<?>) {
            if (GTFOMetaItems.META_ITEM.getItem(stack) != null) {
                for (IItemComponent stats : GTFOMetaItems.META_ITEM.getItem(stack).getAllStats()) {
                    if (stats instanceof GTFOFoodStats) {
                        return ((GTFOFoodStats) stats).getEatingDuration();
                    }
                }
            }
        }
        return 32;
    }
}
