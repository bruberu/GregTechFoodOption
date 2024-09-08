package gregtechfoodoption.item;

import gregtech.api.GregTechAPI;
import gregtech.api.creativetab.BaseCreativeTab;
import gregtech.api.items.metaitem.MetaOreDictItem;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.creativetab.GTCreativeTabs;
import it.unimi.dsi.fastutil.ints.Int2BooleanArrayMap;
import it.unimi.dsi.fastutil.ints.Int2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import it.unimi.dsi.fastutil.shorts.Short2BooleanArrayMap;
import it.unimi.dsi.fastutil.shorts.Short2BooleanMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.HashMap;

public class GTFOOredictItem extends MetaOreDictItem {
    private Int2BooleanMap FOOD_RELATED_ITEMS = new Int2BooleanArrayMap();

    public GTFOOredictItem(short metaItemOffset) {
        super(metaItemOffset);
    }

    public OreDictValueItem addFoodOreDictItem(int id, String materialName, int rgb, MaterialIconSet materialIconSet, OrePrefix orePrefix) {
        OreDictValueItem item = super.addOreDictItem(id, materialName, rgb, materialIconSet, orePrefix);
        setFoodRelated(id);
        return item;
    }

    public OreDictValueItem addFoodOreDictItem(int id, String materialName, int rgb, MaterialIconSet materialIconSet, OrePrefix prefix, String chemicalFormula) {
        OreDictValueItem item = super.addOreDictItem(id, materialName, rgb, materialIconSet, prefix, chemicalFormula);
        setFoodRelated(id);
        return item;
    }

    @Nonnull
    @Override
    public CreativeTabs[] getCreativeTabs() {
        return new BaseCreativeTab[]{GTCreativeTabs.TAB_GREGTECH_MATERIALS};
    }

    public void setFoodRelated(int item) {
        FOOD_RELATED_ITEMS.put(item, true);
    }

    public boolean isFoodRelated(ItemStack item) {
        return FOOD_RELATED_ITEMS.containsKey(item.getMetadata());
    }
}

