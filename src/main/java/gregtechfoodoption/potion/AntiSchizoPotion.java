package gregtechfoodoption.potion;

import net.minecraft.entity.EntityLivingBase;

public class AntiSchizoPotion extends GTFOPotion {
    public static AntiSchizoPotion INSTANCE = null;

    public AntiSchizoPotion() {
        super("antischizo", false, 0xf5f5f5, -1);
        INSTANCE = this;
    }

    @Override
    protected boolean canRender() {
        return false;
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier) {
        super.performEffect(entityLivingBaseIn, amplifier);
    }
}
