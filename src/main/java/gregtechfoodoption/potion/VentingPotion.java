package gregtechfoodoption.potion;

import gregtechfoodoption.client.GTFOClientHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;

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

            for(int i = 0; i < 16; ++i) {
                double newX = entityLiving.posX + (entityLiving.getRNG().nextDouble() - 0.5) * 16.0;
                double newY = MathHelper.clamp(entityLiving.posY + (double)(entityLiving.getRNG().nextInt(16) - 8), 0.0, entityLiving.getEntityWorld().getActualHeight() - 1);
                double newZ = entityLiving.posZ + (entityLiving.getRNG().nextDouble() - 0.5) * 16.0;
                if (entityLiving.isRiding()) {
                    entityLiving.dismountRidingEntity();
                }

                if (entityLiving.attemptTeleport(newX, newY, newZ)) {
                    entityLiving.getEntityWorld().playSound(null, playerX, playerY, playerZ, GTFOClientHandler.AMOGUS_VENT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    entityLiving.playSound(GTFOClientHandler.AMOGUS_VENT, 1.0F, 1.0F);
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
