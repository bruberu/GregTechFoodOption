package gregtechfoodoption.potion;

import gregtechfoodoption.utils.GTFODamageSources;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.Collections;
import java.util.List;

public class asphyxiationpotion extends GTFOPotion {
    public static final String TAG_NAME = "gregtechfoodoption - asphyxiation";
    public static asphyxiationpotion INSTANCE = null;
    public asphyxiationpotion() {
        super("asphyxiation", true, 0xffffff, 0);
        INSTANCE = this;
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public void performEffect(EntityLivingBase entity, int amplifier) {
        int phase = entity.getActivePotionEffect(this).getDuration();

        if (phase == 80) {
            if (entity.world.isRemote)
                entity.sendMessage(new TextComponentTranslation("gregtechfoodoption.asphyxiation.1"));
            entity.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 1000, 9));
            entity.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 1000, 9));
            entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 1000, 9));
        } else if (phase == 20) {
            entity.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 1000, 0));
        } else if (phase < 80 && phase % 5 == 0) {
            entity.attackEntityFrom(GTFODamageSources.ASPHYXIATION, (float) Math.pow((double) (200 - phase) / 80, 2));
            entity.hurtResistantTime = 0;
            //the dmg calc is subject to change
        }
    }

    @Override
    protected boolean canRender() {
        return false;
    }

    @Override
    public boolean shouldRender(PotionEffect effect) {
        return false;
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList(); // Nothing :)
    }
}
