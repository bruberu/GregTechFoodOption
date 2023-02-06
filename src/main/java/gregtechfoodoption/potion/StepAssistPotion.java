package gregtechfoodoption.potion;

import gregtechfoodoption.GTFOConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class StepAssistPotion extends GTFOPotion {
    public static final String TAG_NAME = "gregtechfoodoption - stepassist";
    public static StepAssistPotion INSTANCE = null;
    public StepAssistPotion() {
        super("stepassist", false, 0xdb5800, 3);
        INSTANCE = this;
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public void performEffect(EntityLivingBase entity, int amplifier) {
        if(entity instanceof EntityPlayer) {
            if (entity.isSneaking()) {
                //make sure that, when sneaking, dont fall off!!
                entity.stepHeight = 0.9F;
            }
            else {
                entity.stepHeight = 1.0F + (1F / 16F);//PATH BLOCKS etc are 1/16th downif MY feature turns this on, then do it
            }
        }
    }

    @Override
    protected boolean canRender() {
        return GTFOConfig.gtfoPotionConfig.stepAssist;
    }

}
