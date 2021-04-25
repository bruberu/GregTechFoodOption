package com.bruberu.gregtechfoodoption.item;

import com.bruberu.gregtechfoodoption.material.GTFOFluidMaterial;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.unification.material.MaterialIconSet;
import gregtech.api.unification.material.type.FluidMaterial;
import gregtech.api.unification.ore.OrePrefix;

import java.util.List;

public class GTFOMetaItems {
    private static List<MetaItem<?>> ITEMS = MetaItem.getMetaItems();




    public static void init() {
        GTFOMetaItem item = new GTFOMetaItem();
        item.setRegistryName("gtfo_meta_item");
        GTFOOredictItem oreDictItem = new GTFOOredictItem((short) 0);
        oreDictItem.setRegistryName("gtfo_oredict_item");
    }

    public static void registerOreDict() {
        for (MetaItem<?> item : ITEMS) {
            if (item instanceof GTFOMetaItem) {
                ((GTFOMetaItem) item).registerOreDict();
            }
        }
    }
}
