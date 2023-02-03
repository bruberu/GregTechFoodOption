package gregtechfoodoption.potion;

import gregtechfoodoption.GTFOConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class CreativityPotion extends GTFOPotion {
    public static final String TAG_NAME = "gregtechfoodoption - creativity";
    public static CreativityPotion INSTANCE = null;
    public CreativityPotion() {
        super("creativity", false, 0x51f5d1, 0);
        INSTANCE = this;
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public void performEffect(EntityLivingBase entity, int amplifier) {
        if(entity instanceof EntityPlayer) {
            entity.fallDistance = 0;
        }
    }

    @Override
    protected boolean canRender() {
        return GTFOConfig.gtfoPotionConfig.creativity;
    }
}
