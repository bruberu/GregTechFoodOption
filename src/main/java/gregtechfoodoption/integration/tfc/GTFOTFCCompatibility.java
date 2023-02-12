package gregtechfoodoption.integration.tfc;

import gregtech.api.items.metaitem.MetaItem;
import gregtechfoodoption.GTFOValues;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.event.FMLInterModComms;

import static gregtechfoodoption.item.GTFOMetaItem.*;
import static gregtechfoodoption.item.GTFOMetaItem.APPLE_SLICE;

public class GTFOTFCCompatibility {
    public static void init() {
        new TFCComponentPreparer(POPCORN_BAG)
                .setFoodData(0, 4, 0, 2, 0, 0).register();
        new TFCComponentPreparer(MINERAL_WATER)
                .setFoodData(20, 0, 0, 1, 0, 1).register();
        new TFCComponentPreparer(APPLE_HARD_CANDY)
                .setFoodData(0, 0, 5, 0, 0, 0).register();
        new TFCComponentPreparer(SPARKLING_WATER)
                .setFoodData(10, 0, 0, 0, 0, 0).register();
        new TFCComponentPreparer(LEMON)
                .setFoodData(2, 0, 5, 0, 0, 0).register();
        new TFCComponentPreparer(LIME)
                .setFoodData(2, 0, 5, 0, 0, 0).register();
        new TFCComponentPreparer(ETIRPS)
                .setFoodData(15, 0, 3, 0, 0, 0).register();
        new TFCComponentPreparer(BACON)
                .setFoodData(0, 0, 0, 0, 2, 0).register();
        new TFCComponentPreparer(FRENCH_FRIES).register();
        new TFCComponentPreparer(SYALS).register();
        new TFCComponentPreparer(BAG_OF_CHIPS).register();
        new TFCComponentPreparer(KETTLE_FRIED_CHIPS).register();
        new TFCComponentPreparer(REDUCED_FAT_CHIPS)
                .setFoodData(0, 0, 0, 5, 0, 0).register();
        new TFCComponentPreparer(POTATO_ON_A_STICK)
                .setFoodData(0, 0, 0, 1.5f, 0, 0).register();
        new TFCComponentPreparer(BAGUETTE)
                .setFoodData(0, 1.8f, 0, 0, 0, 0).register();
        new TFCComponentPreparer(TUNGSTENSTEEL_APPLE).register();
        new TFCComponentPreparer(CAKE_BOTTOM).register();
        new TFCComponentPreparer(BAKED_CAKE_BOTTOM).register();
        new TFCComponentPreparer(PIZZA_CHEESE)
                .setFoodData(0, 3, 0, 3, 0, 6).register();
        new TFCComponentPreparer(PIZZA_MINCE_MEAT)
                .setFoodData(0, 3, 0, 3, 5, 5).register();
        new TFCComponentPreparer(PIZZA_VEGGIE)
                .setFoodData(0, 3, 0, 8, 0, 5).register();
        new TFCComponentPreparer(SANDWICH_BACON)
                .setFoodData(0, 2, 0, 0, 5, 0).register();
        new TFCComponentPreparer(SANDWICH_CHEESE)
                .setFoodData(0, 2, 0, 0, 0, 5).register();
        new TFCComponentPreparer(SANDWICH_STEAK)
                .setFoodData(0, 2, 0, 0, 6, 0).register();
        new TFCComponentPreparer(SANDWICH_VEGGIE)
                .setFoodData(0, 2, 0, 5, 0, 0).register();
        new TFCComponentPreparer(SANDWICH_LARGE_BACON)
                .setFoodData(0, 3, 0, 0, 8, 0).register();
        new TFCComponentPreparer(SANDWICH_LARGE_CHEESE)
                .setFoodData(0, 3, 0, 0, 0, 8).register();
        new TFCComponentPreparer(SANDWICH_LARGE_STEAK)
                .setFoodData(0, 3, 0, 0, 10, 0).register();
        new TFCComponentPreparer(SANDWICH_LARGE_VEGGIE)
                .setFoodData(0, 3, 0, 8, 0, 0).register();
        new TFCComponentPreparer(BUN)
                .setFoodData(0, 1, 0, 0, 0, 0).register();
        new TFCComponentPreparer(BURGER_MEAT)
                .setFoodData(0, 1.5f, 0, 0, 4, 0).register();
        new TFCComponentPreparer(BURGER_CHEESE)
                .setFoodData(0, 1.5f, 0, 0, 0, 4).register();
        new TFCComponentPreparer(BURGER_VEGGIE)
                .setFoodData(0, 1.5f, 0, 4, 0, 0).register();
        new TFCComponentPreparer(CHEDDAR_SLICE)
                .setFoodData(0, 0, 0, 0, 0, 2).register();
        new TFCComponentPreparer(MOZZARELLA_BALL)
                .setFoodData(0, 0, 0, 0, 0, 2).register();
        new TFCComponentPreparer(GORGONZOLA_TRIANGULAR_SLICE)
                .setFoodData(0, 0, 0, 0, 0, 2).register();
        new TFCComponentPreparer(ROTTEN_FISH)
                .setFoodData(2, 0, 0, 0, 1, 0).register();
        new TFCComponentPreparer(ROTTEN_MEAT)
                .setFoodData(1, 0, 0, 0, 1, 0).register();
        new TFCComponentPreparer(CHUM)
                .setFoodData(1, 1, 1, 1, 1, 1).register();
        new TFCComponentPreparer(CHUM_ON_A_STICK)
                .setFoodData(1, 1, 1, 1, 1, 1).register();
        new TFCComponentPreparer(BURGER_CHUM)
                .setFoodData(1, 2.5f, 1, 1, 1, 1).register();
        new TFCComponentPreparer(BANANA)
                .setFoodData(0, 0, 3, 0, 0, 0).register();
        new TFCComponentPreparer(ORANGE)
                .setFoodData(0, 0, 3, 0, 0, 0).register();
        new TFCComponentPreparer(GRAPES)
                .setFoodData(0, 0, 3, 0, 0, 0).register();
        new TFCComponentPreparer(MANGO)
                .setFoodData(0, 0, 3, 0, 0, 0).register();
        new TFCComponentPreparer(APRICOT)
                .setFoodData(0, 0, 3, 0, 0, 0).register();
        new TFCComponentPreparer(PEELED_BANANA)
                .setFoodData(0, 0, 3, 0, 0, 0).register();
        new TFCComponentPreparer(VODKA)
                .setFoodData(4, 2, 0, 2, 0, 0).register();
        new TFCComponentPreparer(LENINADE)
                .setFoodData(5, 3, 1, 3, 0, 0).register();
        new TFCComponentPreparer(HOT_MUSHROOM_STEW)
                .setFoodData(2, 2, 0, 5, 0, 0).register();
        new TFCComponentPreparer(HOT_MUSHROOM_STEW)
                .setFoodData(2, 2, 0, 5, 0, 0).register();
        new TFCComponentPreparer(HOT_MUSHROOM_STEW)
                .setFoodData(2, 2, 0, 2, 3, 0).register();
        new TFCComponentPreparer(KEBAB_KUBIDEH_COOKED)
                .setFoodData(0, 0, 0, 4, 6, 0).register();
        new TFCComponentPreparer(KEBAB_BARG_COOKED)
                .setFoodData(0, 0, 2, 2, 6, 0).register();
        new TFCComponentPreparer(KEBAB_SOLTANI)
                .setFoodData(0, 0, 6, 10, 25, 0).register();
        new TFCComponentPreparer(KEBAB_ONION_COOKED)
                .setFoodData(0, 0, 0, 8, 0, 0).register();
        new TFCComponentPreparer(KEBAB_TOMATO_COOKED)
                .setFoodData(0, 0, 0, 8, 0, 0).register();
        new TFCComponentPreparer(KEBAB_CARROT_COOKED)
                .setFoodData(2, 0, 0, 8, 0, 0).register();
        new TFCComponentPreparer(KEBAB_CHUM_COOKED)
                .setFoodData(4, 5, 5, 8, 5, 5).register();
        new TFCComponentPreparer(KEBAB_CHUM_BUCKET)
                .setFoodData(10, 10, 5, 20, 20, 8).register();
        new TFCComponentPreparer(KEBAB_FAT_COOKED).register();
        new TFCComponentPreparer(KEBAB_MEAT_COOKED)
                .setFoodData(0, 0, 0, 0, 8, 0).register();
        new TFCComponentPreparer(APPLE_JUICE)
                .setFoodData(4, 0, 2, 0, 0, 0).register();
        new TFCComponentPreparer(ORANGE_JUICE)
                .setFoodData(4, 0, 2, 0, 0, 0).register();
        new TFCComponentPreparer(TOMATO_SLICE)
                .setFoodData(0, 0, 0, 1, 0, 0).register();
        new TFCComponentPreparer(ONION_SLICE)
                .setFoodData(0, 0, 0, 1, 0, 0).register();
        new TFCComponentPreparer(CUCUMBER_SLICE)
                .setFoodData(0, 0, 0, 1, 0, 0).register();
        new TFCComponentPreparer(CARROT_SLICE)
                .setFoodData(0, 0, 0, 1, 0, 0).register();
        new TFCComponentPreparer(APPLE_SLICE)
                .setFoodData(0, 0, 1, 0, 0, 0).register();
        new TFCComponentPreparer(ICE_CREAM_PLAIN)
                .setFoodData(1, 0, 0, 0, 0, 2)
                .setHeating(100, 50)
                .register();
        new TFCComponentPreparer(ICE_CREAM_CHUM)
                .setFoodData(2, 1, 1, 1, 1, 3)
                .setHeating(105, 55)
                .register();
        new TFCComponentPreparer(ICE_CREAM_BACON)
                .setFoodData(1, 0, 0, 0, 2, 2)
                .setHeating(105, 55)
                .register();
        new TFCComponentPreparer(ICE_CREAM_BANANA)
                .setFoodData(1, 0, 3, 0, 0, 2)
                .setHeating(105, 55)
                .register();
        new TFCComponentPreparer(ICE_CREAM_MELON)
                .setFoodData(1, 0, 1, 0, 0, 2)
                .setHeating(105, 55)
                .register();
        new TFCComponentPreparer(ICE_CREAM_BEAR)
                .setFoodData(1, 0, 0, 0, 3, 2)
                .setHeating(105, 55)
                .register();
        new TFCComponentPreparer(ICE_CREAM_LEMON)
                .setFoodData(1, 0, 2, 0, 0, 2)
                .setHeating(105, 55)
                .register();
        new TFCComponentPreparer(ICE_CREAM_CHOCOLATE)
                .setFoodData(1, 1, 0, 1, 0, 2)
                .setHeating(105, 55)
                .register();
        new TFCComponentPreparer(ICE_CREAM_VANILLA)
                .setFoodData(1, 1, 0, 3, 0, 3)
                .setHeating(105, 55)
                .register();
        new TFCComponentPreparer(ICE_CREAM_CHIP)
                .setFoodData(1, 0, 0, 3, 0, 2)
                .setHeating(105, 55)
                .register();
    }

    private static class TFCComponentPreparer {
        NBTTagCompound comp = new NBTTagCompound();

        public TFCComponentPreparer(MetaItem.MetaValueItem item) {
            comp.setInteger("item", Item.getIdFromItem(item.getMetaItem()));
            comp.setShort("damage", (short) item.getMetaValue());
        }
        
        public TFCComponentPreparer setFoodData(float water, float grain, float fruit, float veg, float meat, float dairy) {
            NBTTagCompound statsCompound = new NBTTagCompound();
            statsCompound.setFloat("water", water);
            statsCompound.setFloat("grain", grain);
            statsCompound.setFloat("fruit", fruit);
            statsCompound.setFloat("veg", veg);
            statsCompound.setFloat("meat", meat);
            statsCompound.setFloat("dairy", dairy);

            comp.setTag("tfcFoodStats", statsCompound);
            return this;
        }

        public TFCComponentPreparer setHeating(int heatCapacity, int meltTemp) {
            NBTTagCompound heatingCompound = new NBTTagCompound();
            heatingCompound.setInteger("heatCapacity", heatCapacity);
            heatingCompound.setInteger("meltTemp", meltTemp);

            comp.setTag("tfcHeatingStats", heatingCompound);
            return this;
        }

        public void register() {
            FMLInterModComms.sendMessage(GTFOValues.MODID_GF, "setTfcFoodStats", comp);
        }
    }
}
