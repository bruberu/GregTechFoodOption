package gregtechfoodoption.machines.multiblock.kitchen;

import net.minecraft.item.ItemStack;

public class ItemStackInfo {
    public ItemStack itemStack;
    public boolean nonConsumable;

    public ItemStackInfo(ItemStack itemStack, boolean nonConsumable) {
        this.itemStack = itemStack;
        this.nonConsumable = nonConsumable;
    }
}