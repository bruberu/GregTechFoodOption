package gregtechfoodoption.item;

import gregtech.api.items.metaitem.stats.IFoodBehavior;
import gregtech.api.util.GTUtility;
import gregtech.api.util.RandomPotionEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

import java.util.List;
import java.util.function.Supplier;

public class GTFOFoodStats implements IFoodBehavior {
    public final int foodLevel;
    public final float saturation;
    public final boolean isDrink;
    public final boolean alwaysEdible;
    public final RandomPotionEffect[] potionEffects;
    public final Supplier<ItemStack> stackSupplier;
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

    public ItemStack onFoodEaten(ItemStack itemStack, EntityPlayer player) {
        if (!player.world.isRemote) {
            for (RandomPotionEffect potionEffect : this.potionEffects) {
                if (Math.random() * 100.0D > (double) potionEffect.chance) {
                    player.addPotionEffect(GTUtility.copyPotionEffect(potionEffect.effect));
                }
            }

            if (this.stackSupplier != null) {
                ItemStack containerItem = stackSupplier.get().copy();
                if (player == null || !player.capabilities.isCreativeMode) {
                    if (itemStack.isEmpty()) {
                        return containerItem;
                    }

                    if (player != null && !player.inventory.addItemStackToInventory(containerItem)) {
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
            PotionEffect[] effects = new PotionEffect[this.potionEffects.length];

            for (int i = 0; i < this.potionEffects.length; ++i) {
                effects[i] = this.potionEffects[i].effect;
            }
        }
    }

    public GTFOFoodStats setEatingDuration(int duration) {
        this.eatingDuration = duration;
        return this;
    }

    public int getEatingDuration() {
        return eatingDuration;
    }
}
