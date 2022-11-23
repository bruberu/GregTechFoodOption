package gregtechfoodoption.integration.applecore;

import gregtech.api.GTValues;
import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.GTFOValues;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import squeek.applecore.api.food.FoodEvent;
import squeek.applecore.api.food.FoodValues;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.*;

public class GTFOAppleCoreCompat {
    private static final ArrayList<Item> sparedItems = new ArrayList<>();
    private static final HashMap<Item, FoodValues> sparedItemsFoodValues = new HashMap<>();
    private static final TreeMap<Float, ResourceLocation> foodReductionMap = new TreeMap() {{
        put(1.25f, new ResourceLocation(GTValues.MODID, "low_voltage/lv_root"));
        put(1.5f, new ResourceLocation(GTValues.MODID, "low_voltage/23_lv_assembler"));
        put(2f, new ResourceLocation(GTValues.MODID, "high_voltage/41_vacuum_freezer"));
        put(3f, new ResourceLocation(GTValues.MODID, "extreme_voltage/47_nichrome_coil"));
        put(5f, new ResourceLocation(GTValues.MODID, "insane_voltage/57_tungstensteel_coil"));
    }};
    public static final Map<UUID, Float> clientDivisorsMap = new HashMap<>();

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void setFoodValuesForEvent(FoodEvent.GetPlayerFoodValues event) {
        event.foodValues = getGTFOFoodValues(event.unmodifiedFoodValues, event.food, event.player);
    }

    public FoodValues getGTFOFoodValues(FoodValues originalValues, ItemStack food, EntityPlayer player) {
        Item sparedFood = food.getItem();
        if (sparedItems.contains(sparedFood)) {
            if (sparedItemsFoodValues.containsKey(sparedFood))
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
        return actualFood.getItem().getRegistryName().getNamespace().equals("gregtechfoodoption") ? 1 : getForeignFoodDivisor(player);
    }

    private float getForeignFoodDivisor(EntityPlayer player) {
        if (GTFOConfig.gtfoAppleCoreConfig.useDefaultForeignFoodStatsReduction) {
            return clientDivisorsMap.get(player.getUniqueID());
        } else
            return GTFOConfig.gtfoAppleCoreConfig.constantFoodStatsDivisor;
    }

    public static float advancementLookup(EntityPlayer player) {
        Map.Entry<Float, ResourceLocation> highestAdvancement = foodReductionMap.lastEntry();
        EntityPlayerMP serverPlayer = (EntityPlayerMP) player;
        while (true) {
            if (resourceLocationToAdvancement(highestAdvancement.getValue(), serverPlayer.getEntityWorld()) != null && serverPlayer.getAdvancements().getProgress(resourceLocationToAdvancement(highestAdvancement.getValue(), serverPlayer.world)).isDone())
                return highestAdvancement.getKey();
            highestAdvancement = foodReductionMap.lowerEntry(highestAdvancement.getKey());
            if (highestAdvancement == null) // When the map runs out
                return 1f;
        }
    }

    public static float getDivisorOnAdvancement(Advancement advancement) {
        for (Map.Entry<Float, ResourceLocation> entry : foodReductionMap.entrySet()) {
            if (entry.getValue().equals(advancement.getId()))
                return entry.getKey();
        }
        return 1;
    }

    private static Advancement resourceLocationToAdvancement(ResourceLocation location, World world) {
        AdvancementManager advManager = ObfuscationReflectionHelper.getPrivateValue(World.class, world, "field_191951_C");
        return advManager.getAdvancement(location);
    }

    @ZenMethod
    public static void addToSparedItems(Item item) {
        sparedItems.add(item);
    }

    @ZenMethod
    public static void addToSparedItems(Item item, int hunger, float saturation) {
        sparedItems.add(item);
        if (GTFOConfig.gtfoAppleCoreConfig.appleCoreCompat)
            sparedItemsFoodValues.put(item, new FoodValues(hunger, saturation));
    }

    @Optional.Method(modid = GTFOValues.MODID_AP)
    public static void sendEatenEvent(EntityPlayer player, ItemStack itemStack, int hunger, float sat) {
        MinecraftForge.EVENT_BUS.post(new FoodEvent.FoodEaten(player, itemStack, new FoodValues(hunger, sat), hunger, sat));
    }
}
