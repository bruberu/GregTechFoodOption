package com.bruberu.gregtechfoodoption.item;

import com.sun.org.apache.bcel.internal.generic.POP;
import gregtech.api.items.materialitem.MaterialMetaItem;
import gregtech.api.items.metaitem.FoodStats;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.util.RandomPotionEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

import java.awt.*;

import static net.minecraft.potion.Potion.getPotionById;


public class GTFOMetaItem extends MaterialMetaItem {
    public GTFOMetaItem()
    {
        super();
    }

    public static MetaItem<?>.MetaValueItem POPCORN_BAG;
    public static MetaItem<?>.MetaValueItem PAPER_BAG;
    public static MetaItem<?>.MetaValueItem FLAVORED_POPCORN_FLAKE;
    public static MetaItem<?>.MetaValueItem POPCORN_COB;
    public static MetaItem<?>.MetaValueItem DRIED_POPCORN_EAR;
    public static MetaItem<?>.MetaValueItem POPCORN_EAR;
    public static MetaItem<?>.MetaValueItem PHYCOMYCES_BLAKESLEEANUS_CULTURE;
    public static MetaItem<?>.MetaValueItem POPCORN_SEED;


    @Override
    public void registerSubItems()
    {
        PAPER_BAG = addItem(1, "component.bag");
        FLAVORED_POPCORN_FLAKE = addItem(2, "component.popcorn.flavored_flake");
        POPCORN_COB = addItem(3, "component.popcorn.cob");
        DRIED_POPCORN_EAR = addItem(4, "component.popcorn.dried_ear");
        POPCORN_EAR = addItem(5, "component.popcorn.ear");
        PHYCOMYCES_BLAKESLEEANUS_CULTURE = addItem(6, "phycomyces.culture");

        POPCORN_BAG = addItem(0, "food.popcorn_bag").addComponents(new FoodStats(5, 2, false, true, PAPER_BAG.getStackForm(), new RandomPotionEffect(getPotionById(10), 15, 1, 100)));
    }

    @Override
    public ItemStack getContainerItem(ItemStack stack) {
        return stack.copy();
    }
}
