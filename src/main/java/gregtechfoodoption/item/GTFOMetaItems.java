package gregtechfoodoption.item;

import gregtechfoodoption.tools.GTFOToolItems;

public class GTFOMetaItems {
    public static GTFOMetaItem META_ITEM;
    public static GTFOOredictItem SHAPED_ITEM = new GTFOOredictItem((short) 0);


    public static void init() {
        META_ITEM = new GTFOMetaItem();
        META_ITEM.setRegistryName("gtfo_meta_item");
        SHAPED_ITEM.setRegistryName("gtfo_oredict_item");
        GTFOToolItems.init();
    }
}
