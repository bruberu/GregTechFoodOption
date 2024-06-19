package gregtechfoodoption.client.particle;

import gregtechfoodoption.covers.CoverSprinkler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

public class GTFOSprinkleMaker extends Particle {
    private CoverSprinkler sprinkler;

    public GTFOSprinkleMaker(World worldIn, double posXIn, double posYIn, double posZIn, CoverSprinkler sprinkler) {
        super(worldIn, posXIn, posYIn, posZIn);
        this.particleMaxAge = Integer.MAX_VALUE;
        this.sprinkler = sprinkler;
    }

    public void onUpdate() {
        if (this.sprinkler == null || !this.sprinkler.getCoverableView().isValid()) {
            this.setExpired();
        }
        if (this.sprinkler.canShowSprinkles()) {
            float chance = Minecraft.getMinecraft().gameSettings.particleSetting == 0 ? 1f : Minecraft.getMinecraft().gameSettings.particleSetting == 1 ? 0.5f : 0.1f;
            if (Math.random() < chance) {
                Minecraft.getMinecraft().effectRenderer.addEffect(
                        new GTFOSprinkle(this.world,
                                this.posX + 0.5,
                                this.posY - 0.1,
                                this.posZ + 0.5,
                                sprinkler.operationPosition.getX() + 0.5,
                                sprinkler.operationPosition.getY() + 1,
                                sprinkler.operationPosition.getZ() + 0.5,
                                this.sprinkler.getSprinkleColor()));
            }
            this.sprinkler.updateOperationPosition();
        }
    }
}
