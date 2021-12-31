package gregtechfoodoption.item;

import gregtechfoodoption.tools.GTFOMetaTool;
import gregtech.api.items.metaitem.MetaItem;

import java.util.List;

public class GTFOMetaItems {
    public static List<MetaItem<?>> ITEMS = MetaItem.getMetaItems();
    public static final GTFOMetaItem META_ITEM = new GTFOMetaItem();



    public static void init() {
        META_ITEM.setRegistryName("gtfo_meta_item");
        GTFOOredictItem oreDictItem = new GTFOOredictItem((short) 0);
        oreDictItem.setRegistryName("gtfo_oredict_item");
        GTFOMetaTool tool = new GTFOMetaTool();
        tool.setRegistryName("gtfo_meta_tool");
    }

    public static void registerOreDict() {
        for (MetaItem<?> item : ITEMS) {
            if (item instanceof GTFOMetaItem) {
                ((GTFOMetaItem) item).registerOreDict();
            }

            if (item instanceof GTFOMetaTool) {
                ((GTFOMetaTool) item).registerRecipes();
            }
        }
    }
}
