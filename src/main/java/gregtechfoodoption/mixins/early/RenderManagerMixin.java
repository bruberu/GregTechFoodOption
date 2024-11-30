package gregtechfoodoption.mixins.early;

import gregtechfoodoption.potion.AntiSchizoPotion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderManager.class, priority = 500, remap = false)
public class RenderManagerMixin {
    @Inject(method = "renderEntity", at = @At("HEAD"), cancellable = true)
    public void removePlayerRender(Entity entityIn, double x, double y, double z, float yaw, float partialTicks, boolean mojangBrainFart, CallbackInfo ci) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        if (player != null && player.isPotionActive(AntiSchizoPotion.INSTANCE) && entityIn instanceof EntityPlayer && !entityIn.isEntityEqual(player)) {
            ci.cancel();
        }
    }
}
