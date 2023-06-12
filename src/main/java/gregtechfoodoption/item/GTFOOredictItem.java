package gregtechfoodoption.item;

import gregtech.api.GregTechAPI;
import gregtech.api.items.metaitem.MetaOreDictItem;
import net.minecraft.creativetab.CreativeTabs;

import javax.annotation.Nonnull;

public class GTFOOredictItem extends MetaOreDictItem {

    public GTFOOredictItem(short metaItemOffset) {
        super(metaItemOffset);
    }

    @Nonnull
    @Override
    public CreativeTabs[] getCreativeTabs() {
        return new gregtech.api.util.BaseCreativeTab[]{GregTechAPI.TAB_GREGTECH_MATERIALS};
    }
}

