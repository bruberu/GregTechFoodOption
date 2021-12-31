package bruberu.gregtechfoodoption.integration.applecore;

import bruberu.gregtechfoodoption.GTFOConfig;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import squeek.applecore.api.food.FoodEvent;
import squeek.applecore.api.food.FoodValues;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.ArrayList;
import java.util.HashMap;

public class GTFOAppleCoreCompat {
    private static ArrayList<Item> sparedItems = new ArrayList<>();
    private static HashMap<Item, FoodValues> sparedItemsFoodValues = new HashMap<>();

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void setFoodValuesForEvent(FoodEvent.GetPlayerFoodValues event) {
        event.foodValues = getGTFOFoodValues(event.unmodifiedFoodValues, event.food, event.player);
    }

    public FoodValues getGTFOFoodValues(FoodValues originalValues, ItemStack food, EntityPlayer player) {
        Item sparedFood = food.getItem();
        if (sparedItems.contains(sparedFood)) {
            if(sparedItemsFoodValues.containsKey(sparedFood))
                return sparedItemsFoodValues.get(sparedFood);
            return originalValues;
        }
        if (GTFOConfig.gtfoAppleCoreConfig.reduceForeignFoodStats) {
            ItemStack actualFood = food;

            float modifier = this.getFoodModifier(player, actualFood);
            return this.getModifiedFoodValues(originalValues, modifier);
        }
        return originalValues;
    }

    private FoodValues getModifiedFoodValues(FoodValues foodValues, float modifier) {
        return new FoodValues((int) Math.max(1, (float) foodValues.hunger / modifier), (float) Math.max(0.1, foodValues.saturationModifier / modifier));
    }

    private float getFoodModifier(EntityPlayer player, ItemStack actualFood) {
        int timePlayed = 0;
        if(player instanceof EntityPlayerMP)
            timePlayed = ((EntityPlayerMP) player).getStatFile().readStat(StatList.PLAY_ONE_MINUTE) / 1200;
        else if (player instanceof EntityPlayerSP)
            timePlayed = ((EntityPlayerSP) player).getStatFileWriter().readStat(StatList.PLAY_ONE_MINUTE) / 1200;

        return actualFood.getItem().getRegistryName().getNamespace().equals("gregtechfoodoption") ? 1 : getForeignFoodDivisor(timePlayed);
    }

    private float getForeignFoodDivisor(int playTimeStat) {
        if(GTFOConfig.gtfoAppleCoreConfig.useDefaultForeignFoodStatsReduction) {
            float logistic = (float) (1 + ((GTFOConfig.gtfoAppleCoreConfig.foodStatsReductionMaximum - 1) /
                                (1 + Math.exp(((float) 5 / GTFOConfig.gtfoAppleCoreConfig.foodStatsReductionMinuteMidpoint) *
                                        (GTFOConfig.gtfoAppleCoreConfig.foodStatsReductionMinuteMidpoint - playTimeStat)))));
            return logistic < 1.05 ? 1 : logistic;
        }
        else
            return GTFOConfig.gtfoAppleCoreConfig.constantFoodStatsDivisor;
    }

    @ZenMethod
    public static void addToSparedItems(Item item) {
        sparedItems.add(item);
    }
    @ZenMethod
    public static void addToSparedItems(Item item, int hunger, float saturation) {
        sparedItems.add(item);
        if(GTFOConfig.gtfoAppleCoreConfig.appleCoreCompat)
            sparedItemsFoodValues.put(item, new FoodValues(hunger, saturation));
    }
}
