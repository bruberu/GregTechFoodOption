package gregtechfoodoption.item;

import gregtech.api.gui.ModularUI;
import gregtech.api.items.gui.ItemUIFactory;
import gregtech.api.items.gui.PlayerInventoryHolder;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import net.minecraft.entity.player.EntityPlayer;

public class GTFOKitchenRecipeBehaviour implements ItemUIFactory, IItemBehaviour {
    @Override
    public ModularUI createUI(PlayerInventoryHolder playerInventoryHolder, EntityPlayer entityPlayer) {
        return null;
    }
}
