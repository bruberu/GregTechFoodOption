package gregtechfoodoption.client.particle;

import codechicken.lib.vec.Vector3;
import gregtech.client.particle.GTLaserBeamParticle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class GTFOFarmingLaserBeamParticle extends GTLaserBeamParticle {

    public GTFOFarmingLaserBeamParticle(World worldIn, Vector3 startPos, Vector3 endPos, int maxAge) {
        super(worldIn, startPos, endPos);
        this.particleAge = 0;
        this.particleMaxAge = maxAge;
        this.setBody(new ResourceLocation("gregtech", "textures/fx/laser/laser.png"));
        this.setHead(new ResourceLocation("gregtech", "textures/fx/laser/laser_start.png"));
    }

    @Override
    public float getAlpha() {
        return (float) particleAge / particleMaxAge;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!isExpired) {
            this.setAlphaF(1f - ((float) particleAge / particleMaxAge));
        }
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        this.setAlpha(1f - ((float) particleAge / particleMaxAge));
        super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
    }
}
