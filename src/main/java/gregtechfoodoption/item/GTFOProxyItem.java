package gregtechfoodoption.item;

import gregtech.api.GregTechAPI;
import gregtech.api.items.metaitem.MetaOreDictItem;
import net.minecraft.item.ItemStack;

import java.util.function.Supplier;

import static gregtechfoodoption.item.GTFOMetaItems.SHAPED_ITEM;

public class GTFOProxyItem {
    private final Supplier<ItemStack> itemPreparer;
    private ItemStack preferredItem;
    private final boolean isOverriden;

    public GTFOProxyItem(Supplier<MetaOreDictItem.OreDictValueItem> gtfoItem, int gtfoItemID, String name, Supplier<ItemStack> otherItem) {
        isOverriden = GregTechAPI.materialManager.getMaterial(name) != null;
        if (isOverriden) {
            itemPreparer = otherItem;
        } else {
            itemPreparer = () -> SHAPED_ITEM.getItem((short) gtfoItemID).getStackForm();
            gtfoItem.get(); // Initialize it, but don't use it yet.
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

    public boolean isOverriden() {
        return isOverriden;
    }
}
