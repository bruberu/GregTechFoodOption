package gregtechfoodoption.worldgen;

import gregtech.loaders.dungeon.ChestGenHooks;
import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.item.GTFOMetaItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.loot.LootTableList;

public class GTFODungeonLootLoader {
    public static void init() {
        if (GTFOConfig.gtfoMiscConfig.addDungeonFoods) {
            addItem(GTFOMetaItem.SANDWICH_STEAK.getStackForm(), 2, 8, 20);
            addItem(GTFOMetaItem.SANDWICH_CHEESE.getStackForm(), 2, 8, 20);
            addItem(GTFOMetaItem.SANDWICH_BACON.getStackForm(), 2, 8, 20);
            addItem(GTFOMetaItem.PIZZA_CHEESE.getStackForm(), 2, 4, 20);
            addItem(GTFOMetaItem.PIZZA_VEGGIE.getStackForm(), 2, 4, 20);
            addItem(GTFOMetaItem.PIZZA_MINCE_MEAT.getStackForm(), 2, 4, 20);
            addItem(GTFOMetaItem.FRENCH_FRIES.getStackForm(), 2, 10, 30);
            addItem(GTFOMetaItem.SYALS.getStackForm(), 2, 4, 10);
            addItem(GTFOMetaItem.BAG_OF_CHIPS.getStackForm(), 2, 10, 40);
            addItem(GTFOMetaItem.FISH_AND_CHIPS.getStackForm(), 2, 10, 20);
            addItem(GTFOMetaItem.KETTLE_FRIED_CHIPS.getStackForm(), 2, 10, 30);
            addItem(GTFOMetaItem.REDUCED_FAT_CHIPS.getStackForm(), 2, 10, 10);
            addItem(GTFOMetaItem.BURGER_CHEESE.getStackForm(), 2, 10, 30);
            addItem(GTFOMetaItem.POTATO_ON_A_STICK.getStackForm(), 8, 16, 40);
            addItem(GTFOMetaItem.CHUM_ON_A_STICK.getStackForm(), 8, 16, 10);
            addItem(GTFOMetaItem.ETIRPS.getStackForm(), 8, 16, 40);
            addItem(GTFOMetaItem.BEANS_ON_TOAST.getStackForm(), 5, 10, 30);
            addItem(GTFOMetaItem.SANDWICH_TOAST.getStackForm(), 5, 10, 10);
            addItem(GTFOMetaItem.MUSHY_PEAS.getStackForm(), 5, 10, 20);
            addItem(GTFOMetaItem.BAKED_BEANS.getStackForm(), 5, 10, 20);
            addItem(GTFOMetaItem.MOZZARELLA_BALL.getStackForm(), 8, 32, 20);
            addItem(GTFOMetaItem.HOT_MUSHROOM_STEW.getStackForm(), 1, 1, 16);
            addItem(GTFOMetaItem.HOT_RABBIT_STEW.getStackForm(), 1, 1, 16);
            addItem(GTFOMetaItem.HOT_BEETROOT_SOUP.getStackForm(), 1, 1, 16);
            addItem(GTFOMetaItem.GEL_CAPLET.getStackForm(), 1, 1, 4);
            addItem(GTFOMetaItem.KEBAB_SOLTANI.getStackForm(), 2, 8, 16);
            addItem(GTFOMetaItem.KEBAB_BARG.getStackForm(), 2, 8, 16);
            addItem(GTFOMetaItem.KEBAB_CARROT.getStackForm(), 2, 8, 16);
            addItem(GTFOMetaItem.KEBAB_KUBIDEH.getStackForm(), 2, 8, 16);
            addItem(GTFOMetaItem.KEBAB_FAT.getStackForm(), 2, 8, 16);
            addItem(GTFOMetaItem.KEBAB_CHUM.getStackForm(), 2, 8, 8);
            addItem(GTFOMetaItem.NILK.getStackForm(), 2, 8, 10);
            addItem(GTFOMetaItem.VODKA.getStackForm(), 2, 8, 10);
            addItem(GTFOMetaItem.ICE_CREAM_CHOCOLATE.getStackForm(), 10, 32, 10);
            addItem(GTFOMetaItem.ICE_CREAM_CHIP.getStackForm(), 10, 32, 5);
            addItem(GTFOMetaItem.ICE_CREAM_BACON.getStackForm(), 10, 32, 5);
            addItem(GTFOMetaItem.ICE_CREAM_BANANA.getStackForm(), 10, 32, 5);
            addItem(GTFOMetaItem.ICE_CREAM_MELON.getStackForm(), 10, 32, 5);
            addItem(GTFOMetaItem.ICE_CREAM_PLAIN.getStackForm(), 10, 32, 15);
            addItem(GTFOMetaItem.ICE_CREAM_VANILLA.getStackForm(), 10, 32, 15);
            addItem(GTFOMetaItem.ICE_CREAM_BEAR.getStackForm(), 10, 32, 5);
            addItem(GTFOMetaItem.ICE_CREAM_LEMON.getStackForm(), 10, 32, 5);

            addItemRare(GTFOMetaItem.MINERAL_WATER.getStackForm(), 1, 1, 1);
            addItemRare(GTFOMetaItem.HEART_SMOGUS.getStackForm(), 1, 1, 1);
        }
    }

    public static void addItem(ItemStack foodStack, int min, int max, int weight) {
        ChestGenHooks.addItem(LootTableList.CHESTS_ABANDONED_MINESHAFT, foodStack, min, max, weight);
        ChestGenHooks.addItem(LootTableList.CHESTS_JUNGLE_TEMPLE, foodStack, min, max, weight);
        ChestGenHooks.addItem(LootTableList.CHESTS_DESERT_PYRAMID, foodStack, min, max, weight);
        ChestGenHooks.addItem(LootTableList.CHESTS_SIMPLE_DUNGEON, foodStack, min, max, weight);
        ChestGenHooks.addItem(LootTableList.CHESTS_STRONGHOLD_CORRIDOR, foodStack, min, max, weight);
        ChestGenHooks.addItem(LootTableList.CHESTS_STRONGHOLD_CROSSING, foodStack, min, max, weight);
        ChestGenHooks.addItem(LootTableList.CHESTS_WOODLAND_MANSION, foodStack, min, max, weight);

        if (GTFOConfig.gtfoMiscConfig.addLacedDungeonFoods) {
            ItemStack copy = foodStack.copy();
            NBTTagCompound comp = new NBTTagCompound();
            NBTTagCompound foodStats = new NBTTagCompound();
            foodStats.setBoolean("5dkcap/2/4/", true); // Cyanide :)
            comp.setTag("foodStats", foodStats);
            copy.setTagCompound(comp);

            ChestGenHooks.addItem(LootTableList.CHESTS_ABANDONED_MINESHAFT, copy, min, max, weight / 3);
            ChestGenHooks.addItem(LootTableList.CHESTS_JUNGLE_TEMPLE, foodStack, min, max, weight);
            ChestGenHooks.addItem(LootTableList.CHESTS_DESERT_PYRAMID, foodStack, min, max, weight / 2);
            ChestGenHooks.addItem(LootTableList.CHESTS_SIMPLE_DUNGEON, foodStack, min, max, weight / 2);
            ChestGenHooks.addItem(LootTableList.CHESTS_STRONGHOLD_CORRIDOR, foodStack, min, max, weight / 3);
            ChestGenHooks.addItem(LootTableList.CHESTS_STRONGHOLD_CROSSING, foodStack, min, max, weight / 2);
            ChestGenHooks.addItem(LootTableList.CHESTS_WOODLAND_MANSION, foodStack, min, max, weight);
        }
    }

    public static void addItemRare(ItemStack foodStack, int min, int max, int weight) {
        ChestGenHooks.addItem(LootTableList.CHESTS_JUNGLE_TEMPLE, foodStack, min, max, weight);
        ChestGenHooks.addItem(LootTableList.CHESTS_WOODLAND_MANSION, foodStack, min, max, weight);

        if (GTFOConfig.gtfoMiscConfig.addLacedDungeonFoods) {
            ItemStack copy = foodStack.copy();
            NBTTagCompound comp = new NBTTagCompound();
            NBTTagCompound foodStats = new NBTTagCompound();
            foodStats.setBoolean("5dkcap/2/4/", true); // Cyanide :)
            comp.setTag("foodStats", foodStats);
            copy.setTagCompound(comp);

            ChestGenHooks.addItem(LootTableList.CHESTS_JUNGLE_TEMPLE, foodStack, min, max, weight);
            ChestGenHooks.addItem(LootTableList.CHESTS_WOODLAND_MANSION, foodStack, min, max, weight);
            ChestGenHooks.addItem(LootTableList.CHESTS_SIMPLE_DUNGEON, foodStack, min, max, weight * 4);

        }
    }
}
