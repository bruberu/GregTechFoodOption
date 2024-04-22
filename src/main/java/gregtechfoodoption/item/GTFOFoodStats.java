package gregtechfoodoption.item;

import gregtech.api.items.metaitem.stats.IFoodBehavior;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import gregtech.api.util.RandomPotionEffect;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.integration.applecore.GTFOAppleCoreCompat;
import gregtechfoodoption.potion.LacingEntry;
import gregtechfoodoption.utils.GTFOUtils;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.Loader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class GTFOFoodStats implements IFoodBehavior, IItemBehaviour { // These names suck
    protected int foodLevel;
    protected float saturation;
    protected boolean isDrink;
    protected boolean alwaysEdible;
    public RandomPotionEffect[] potionEffects;
    public Supplier<ItemStack> stackSupplier;
    public Object2FloatMap<String> nutrients = new Object2FloatArrayMap<>();
    protected int eatingDuration = 32;


    public GTFOFoodStats(int foodLevel, float saturation, boolean isDrink, boolean alwaysEdible, Supplier<ItemStack> itemStackSupplier, RandomPotionEffect... potionEffects) {
        this.foodLevel = foodLevel;
        this.saturation = saturation;
        this.isDrink = isDrink;
        this.alwaysEdible = alwaysEdible;
        this.stackSupplier = itemStackSupplier;
        this.potionEffects = potionEffects;
    }

    public GTFOFoodStats(int foodLevel, float saturation, boolean isDrink, boolean alwaysEdible, ItemStack stack, RandomPotionEffect... potionEffects) {
        this.foodLevel = foodLevel;
        this.saturation = saturation;
        this.isDrink = isDrink;
        this.alwaysEdible = alwaysEdible;
        this.stackSupplier = () -> stack;
        this.potionEffects = potionEffects;
    }

    public GTFOFoodStats(int foodLevel, float saturation, boolean isDrink, boolean alwaysEdible) {
        this(foodLevel, saturation, isDrink, alwaysEdible, ItemStack.EMPTY);
    }

    public GTFOFoodStats(int foodLevel, float saturation) {
        this(foodLevel, saturation, false, false);
    }

    public EnumAction getFoodAction(ItemStack itemStack) {
        return this.isDrink ? EnumAction.DRINK : EnumAction.EAT;
    }

    public int getFoodLevel(ItemStack itemStack, EntityPlayer player) {
        return this.foodLevel;
    }

    public float getSaturation(ItemStack itemStack, EntityPlayer player) {
        return this.saturation;
    }

    public boolean alwaysEdible(ItemStack itemStack, EntityPlayer player) {
        return this.alwaysEdible;
    }

    public GTFOFoodStats setReturnStack(ItemStack stack) {
        this.stackSupplier = () -> stack;
        return this;
    }

    public ItemStack onFoodEaten(ItemStack itemStack, EntityPlayer player) {
        NBTTagCompound nbtStats = itemStack.getSubCompound("gtfoStats");
        if (nbtStats != null) {
            LacingEntry.LACING_REGISTRY.forEach(lacingEntry -> {
                if (nbtStats.getBoolean(lacingEntry.getNbtKey())) {
                    player.addPotionEffect(lacingEntry.getAppliedEffect());
                }
            });
        }

        if (Loader.isModLoaded(GTFOValues.MODID_AP)) {
            itemStack.grow(1);
            GTFOAppleCoreCompat.sendEatenEvent(player, itemStack, getFoodLevel(itemStack, player), getSaturation(itemStack, player));
            itemStack.shrink(1);
        }
        for (RandomPotionEffect potionEffect : this.potionEffects) {
            if (Math.random() * 100.0D > (double) potionEffect.chance) {
                player.addPotionEffect(new PotionEffect(potionEffect.effect));
            }
        }
        if (player != null && !player.world.isRemote) {
            if (this.stackSupplier != null) {
                ItemStack containerItem = stackSupplier.get().copy();
                if (!player.capabilities.isCreativeMode) {
                    if (itemStack.isEmpty()) {
                        return containerItem;
                    }

                    if (!player.inventory.addItemStackToInventory(containerItem)) {
                        player.dropItem(containerItem, false, false);
                    }
                }
            }
        }

        return itemStack;
    }

    @Override
    public void addInformation(ItemStack itemStack, List<String> list) {
        if (this.potionEffects.length > 0) {
            GTFOUtils.addPotionTooltip(Arrays.asList(potionEffects), list);
        }
        list.add(new TextComponentTranslation("gregtechfoodoption.tooltip.food.lacing").getFormattedText());
        if (this.eatingDuration != 32) {
            list.add(new TextComponentTranslation("gregtechfoodoption.tooltip.food.duration", this.eatingDuration).getFormattedText());
        }
    }

    public GTFOFoodStats setEatingDuration(int duration) {
        this.eatingDuration = duration;
        return this;
    }

    public GTFOFoodStats setPotionEffects(RandomPotionEffect... effects) {
        this.potionEffects = effects;
        return this;
    }

    public int getEatingDuration() {
        return eatingDuration;
    }

    public RandomPotionEffect[] getPotionEffects() {
        return potionEffects;
    }

    public Supplier<ItemStack> getStackSupplier() {
        return stackSupplier;
    }

    public GTFOFoodStats nutrients(float dairy, float fruit, float grain, float protein, float vegetable) {
        if (dairy > 0) {
            this.nutrients.put("dairy", dairy);
        }
        if (fruit > 0) {
            this.nutrients.put("fruit", fruit);
        }
        if (grain > 0) {
            this.nutrients.put("grain", grain);
        }
        if (protein > 0) {
            this.nutrients.put("protein", protein);
        }
        if (vegetable > 0) {
            this.nutrients.put("vegetable", vegetable);
        }
        return this;
    }
}
