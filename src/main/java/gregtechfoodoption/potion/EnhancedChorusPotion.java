package gregtechfoodoption.potion;

import gregtechfoodoption.client.GTFOClientHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class EnhancedChorusPotion extends GTFOPotion {
    public static final String TAG_NAME = "gregtechfoodoption - enhanced chorus";
    public static EnhancedChorusPotion INSTANCE = null;

    public EnhancedChorusPotion() {
        super("enhancedchorus", false, 0xFF052B, 9);
        INSTANCE = this;
    }

    @Override
    public void performEffect(EntityLivingBase entityLiving, int amplifier) {
        // Chorus fruit moment

        if (entityLiving instanceof EntityPlayer player) {
            // Chorus fruit moment
            if (entityLiving.ticksExisted % 20 == 0 && player.isSneaking()) {
                double playerX = entityLiving.posX;
                double playerY = entityLiving.posY;
                double playerZ = entityLiving.posZ;

                for (int i = 0; i < 16; ++i) {
                    Vec3d look = entityLiving.getLookVec();

                    double newX = entityLiving.posX + look.x * 8 + Math.random() * 2;
                    double newY = MathHelper.clamp(entityLiving.posY + 8 * look.y + Math.random(), 0.0, entityLiving.getEntityWorld().getActualHeight() - 1);
                    double newZ = entityLiving.posZ + look.z * 8 + Math.random() * 2;
                    if (entityLiving.isRiding()) {
                        entityLiving.dismountRidingEntity();
                    }

                    if (entityLiving.attemptTeleport(newX, newY, newZ)) {
                        entityLiving.getEntityWorld().playSound(null, playerX, playerY, playerZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                        entityLiving.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
                        player.fallDistance = 0;
                        break;
                    }
                }
            }
        }
    }

    @Override
    protected boolean canRender() {
        return true;
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }
}
