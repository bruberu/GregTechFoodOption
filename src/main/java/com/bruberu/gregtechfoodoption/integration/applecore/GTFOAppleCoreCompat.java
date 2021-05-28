package com.bruberu.gregtechfoodoption.integration.applecore;

import com.bruberu.gregtechfoodoption.GTFOConfig;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import squeek.applecore.api.food.FoodEvent;
import squeek.applecore.api.food.FoodValues;

public class GTFOAppleCoreCompat {
    public static final GTFOAppleCoreCompat GLOBAL = new GTFOAppleCoreCompat();



    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void getFoodValues(FoodEvent.GetPlayerFoodValues event) {
        if (GTFOConfig.gtfoAppleCoreConfig.reduceForeignFoodStats) {
            ItemStack actualFood = event.food;

            float modifier = this.getFoodModifier(event.player, actualFood);
            event.foodValues = this.getModifiedFoodValues(event.foodValues, modifier);
        }
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
}
