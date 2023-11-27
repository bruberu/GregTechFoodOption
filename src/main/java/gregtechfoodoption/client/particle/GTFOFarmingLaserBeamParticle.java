package gregtechfoodoption.client.particle;

import codechicken.lib.vec.Vector3;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.client.particle.GTLaserBeamParticle;
import gregtech.client.utils.EffectRenderContext;
import gregtechfoodoption.machines.farmer.MetaTileEntityFarmer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class GTFOFarmingLaserBeamParticle extends GTLaserBeamParticle {

    private int particleAge;
    private final int particleMaxAge;
    public GTFOFarmingLaserBeamParticle(MetaTileEntity mte, Vector3 startPos, Vector3 endPos, int maxAge) {
        super(mte, startPos, endPos);
        this.particleAge = 0;
        this.particleMaxAge = maxAge;
        this.setBody(new ResourceLocation("gregtech", "textures/fx/laser/laser.png"));
        this.setHead(new ResourceLocation("gregtech", "textures/fx/laser/laser_start.png"));
        this.setEmit(1f);
    }

    @Override
    public float getAlpha() {
        return (float) particleAge / particleMaxAge;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (particleAge != particleMaxAge) {
            this.setAlpha(1f - ((float) particleAge / particleMaxAge));
            particleAge++;
        } else {
            this.setExpired();
        }
    }

    @Override
    public void renderParticle(@NotNull BufferBuilder buffer, @NotNull EffectRenderContext context) {
        this.setAlpha(1f - ((float) particleAge / particleMaxAge));
        super.renderParticle(buffer, context);
    }
}
