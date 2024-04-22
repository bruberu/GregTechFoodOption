package gregtechfoodoption.integration.sereneseasons;

import gregtechfoodoption.block.GTFOCrop;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sereneseasons.config.FertilityConfig;
import sereneseasons.core.SereneSeasons;
import sereneseasons.init.ModFertility;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

public class GTFOSSTooltipHandler {
    @SideOnly(Side.CLIENT)
    public static void addTooltip(GTFOCrop crop, List<String> tooltip) {
        try {
            Field seedSeasons = ModFertility.class.getDeclaredField("seedSeasons");
            seedSeasons.setAccessible(true);
            HashMap<String, Integer> map = (HashMap<String, Integer>) seedSeasons.get(FertilityConfig.seasonal_fertility);
            String name = crop.getRegistryName().toString();
            if (map.containsKey(name)) {
                int mask = map.get(name);
                tooltip.add("Fertile Seasons:");
                if ((mask & 1) != 0 && (mask & 2) != 0 && (mask & 4) != 0 && (mask & 8) != 0) {
                    tooltip.add(TextFormatting.LIGHT_PURPLE + " Year-Round");
                } else {
                    if ((mask & 1) != 0) {
                        tooltip.add(TextFormatting.GREEN + " Spring");
                    }

                    if ((mask & 2) != 0) {
                        tooltip.add(TextFormatting.YELLOW + " Summer");
                    }

                    if ((mask & 4) != 0) {
                        tooltip.add(TextFormatting.GOLD + " Autumn");
                    }

                    if ((mask & 8) != 0) {
                        tooltip.add(TextFormatting.AQUA + " Winter");
                    }
                }
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
