package gregtechfoodoption.potion;

import gregtechfoodoption.utils.GTFOUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import org.jetbrains.annotations.Nullable;

public class AntiSchizoPotion extends GTFOPotion{
    public AntiSchizoPotion() {
        super("antischizo", true, 0xf5f5f5, -1);
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
    public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, EntityLivingBase entityLivingBaseIn, int amplifier, double health) {
        super.affectEntity(source, indirectSource, entityLivingBaseIn, amplifier, health);
    }

    @Override
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
        GTFOUtils.drawRect(0, 0, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight, 0xff000000);
    }

    @Override
    public boolean shouldRenderHUD(PotionEffect effect) {
        return super.shouldRenderHUD(effect);
    }
}
