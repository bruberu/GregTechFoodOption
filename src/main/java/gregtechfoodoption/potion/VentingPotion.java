package gregtechfoodoption.potion;

import gregtechfoodoption.client.GTFOClientHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class VentingPotion extends GTFOPotion {
    public static final String TAG_NAME = "gregtechfoodoption - venting";
    public static VentingPotion INSTANCE = null;

    public VentingPotion() {
        super("venting", false, 0xFF052B, 7);
        INSTANCE = this;
    }

    @Override
    public void performEffect(EntityLivingBase entityLiving, int amplifier) {
        // Chorus fruit moment
        if (Math.random() < 0.025 * (amplifier + 1)) {
            double playerX = entityLiving.posX;
            double playerY = entityLiving.posY;
            double playerZ = entityLiving.posZ;

            for (int i = 0; i < 16; ++i) {
                Vec3d look = entityLiving.getLookVec();

                double newX = entityLiving.posX + (entityLiving.getRNG().nextDouble() - 0.5 + look.x) * 16.0;
                double newY = MathHelper.clamp(entityLiving.posY + (entityLiving.getRNG().nextInt(8) - 4 + 4 * look.y), 0.0, entityLiving.getEntityWorld().getActualHeight() - 1);
                double newZ = entityLiving.posZ + (entityLiving.getRNG().nextDouble() - 0.5 + look.z) * 16.0;
                if (entityLiving.isRiding()) {
                    entityLiving.dismountRidingEntity();
                }

                if (entityLiving.attemptTeleport(newX, newY, newZ)) {
                    entityLiving.getEntityWorld().playSound(null, playerX, playerY, playerZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    entityLiving.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
                    break;
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
