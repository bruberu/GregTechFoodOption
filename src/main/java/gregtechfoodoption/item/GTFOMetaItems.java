package gregtechfoodoption.item;

import gregtech.api.items.metaitem.MetaItem;
import gregtechfoodoption.tools.GTFOToolItems;

import java.util.List;

public class GTFOMetaItems {
    public static List<MetaItem<?>> ITEMS = MetaItem.getMetaItems();
    public static GTFOMetaItem META_ITEM;
    public static GTFOOredictItem SHAPED_ITEM = new GTFOOredictItem((short) 0);


    public static void init() {
        META_ITEM = new GTFOMetaItem();
        META_ITEM.setRegistryName("gtfo_meta_item");
        SHAPED_ITEM.setRegistryName("gtfo_oredict_item");
        GTFOToolItems.init();
    }
}
