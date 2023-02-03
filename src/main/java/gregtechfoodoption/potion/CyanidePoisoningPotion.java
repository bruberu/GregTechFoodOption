package gregtechfoodoption.potion;

import gregtechfoodoption.utils.GTFODamageSources;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;

public class CyanidePoisoningPotion extends GTFOPotion {
    public static final String TAG_NAME = "gregtechfoodoption - cyanidepoisoning";
    public static CyanidePoisoningPotion INSTANCE = null;
    public CyanidePoisoningPotion() {
        super("cyanidepoisoning", true, 0xffffff, 0);
        INSTANCE = this;
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public void performEffect(EntityLivingBase entity, int amplifier) {
        int phase = entity.getActivePotionEffect(this).getDuration();

        if (phase == 400) {
            if (entity.world.isRemote)
                entity.sendMessage(new TextComponentTranslation("gregtechfoodoption.cyanide.1"));
            entity.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 1000, 9));
        } else if (phase == 300) {
            if (entity.world.isRemote)
                entity.sendMessage(new TextComponentTranslation("gregtechfoodoption.cyanide.2"));
        } else if (phase == 160) {
            entity.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 1000, 0));
        } else if (phase < 200 && phase % 5 == 0) {
            entity.attackEntityFrom(GTFODamageSources.CYANIDE, (float) Math.pow((double) (200 - phase) / 80, 2));
            entity.hurtResistantTime = 0; // No saving you :)
        } else if (phase < 100) {
            if (entity.world.isRemote && entity.isEntityAlive())
                entity.sendMessage(new TextComponentTranslation("gregtechfoodoption.cyanide.3"));
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
}
