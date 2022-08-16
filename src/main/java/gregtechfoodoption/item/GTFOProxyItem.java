package gregtechfoodoption.item;

import gregtech.api.items.metaitem.MetaOreDictItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

import java.util.function.Supplier;

import static gregtechfoodoption.item.GTFOMetaItems.SHAPED_ITEM;

public class GTFOProxyItem {
    private final Supplier<ItemStack> itemPreparer;
    private ItemStack preferredItem;
    private final boolean isOverridden;

    public GTFOProxyItem(Supplier<MetaOreDictItem.OreDictValueItem> gtfoItem, int gtfoItemID, String modid, Supplier<ItemStack> otherItem) {
        this.isOverridden = Loader.isModLoaded(modid);
        if (isOverridden) {
            itemPreparer = otherItem;
        } else {
            itemPreparer = () -> SHAPED_ITEM.getItem((short) gtfoItemID).getStackForm();
            gtfoItem.get();
        }
    }

    public ItemStack get() {
        if (preferredItem == null)
            preferredItem = itemPreparer.get();
        return preferredItem.copy();
    }

    public ItemStack get(int count) {
        ItemStack copy = this.get();
        copy.setCount(count);
        return copy;
    }
}
