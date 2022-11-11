package gregtechfoodoption.client.particle;

import gregtech.client.particle.GTParticle;
import gregtech.client.particle.IGTParticleHandler;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class GTFOSprinkle extends GTParticle {
    private int bobTimer;

    private static final ResourceLocation PARTICLE_TEXTURES = new ResourceLocation("textures/particle/particles.png");

    public static IGTParticleHandler HANDLER = new IGTParticleHandler() {

        public void preDraw(BufferBuilder buffer) {
        }

        public void postDraw(BufferBuilder buffer) {
        }
    };

    public GTFOSprinkle(World worldIn, double posXIn, double posYIn, double posZIn, int color) {
        super(worldIn, posXIn, posYIn, posZIn);
        this.motionX = 0.0;
        this.motionY = 0.0;
        this.motionZ = 0.0;
        this.particleRed = color - (color % 256 * 256);
        this.particleGreen = color % 256 * 256 - color % 256;
        this.particleBlue = color % 256;

        this.setParticleTextureIndex(113);
        this.setSize(0.01F, 0.01F);
        this.particleGravity = 0.06F;
        this.bobTimer = 40;
        this.particleMaxAge = (int)(64.0 / (Math.random() * 0.8 + 0.2));
        this.motionX = 0.0;
        this.motionY = 0.0;
        this.motionZ = 0.0;
    }

    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        this.motionY -= this.particleGravity;
        if (this.bobTimer-- > 0) {
            this.motionX *= 0.02;
            this.motionY *= 0.02;
            this.motionZ *= 0.02;
            this.setParticleTextureIndex(113);
        } else {
            this.setParticleTextureIndex(112);
        }

        this.move(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863;
        this.motionY *= 0.9800000190734863;
        this.motionZ *= 0.9800000190734863;
        if (this.particleMaxAge-- <= 0) {
            this.setExpired();
        }

        if (this.onGround) {
            this.setParticleTextureIndex(114);
            this.motionX *= 0.699999988079071;
            this.motionZ *= 0.699999988079071;
        }

        BlockPos blockpos = new BlockPos(this.posX, this.posY, this.posZ);
        IBlockState iblockstate = this.world.getBlockState(blockpos);
        Material material = iblockstate.getMaterial();
        if (material.isLiquid() || material.isSolid()) {
            double d0 = 0.0;
            if (iblockstate.getBlock() instanceof BlockLiquid) {
                d0 = BlockLiquid.getLiquidHeightPercent(iblockstate.getValue(BlockLiquid.LEVEL));
            }

            double d1 = (double)(MathHelper.floor(this.posY) + 1) - d0;
            if (this.posY < d1) {
                this.setExpired();
            }
        }

    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        TextureManager renderEngine = Minecraft.getMinecraft().getRenderManager().renderEngine;
        renderEngine.bindTexture(PARTICLE_TEXTURES);

        buffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);

        float minU = (float) this.particleTextureIndexX / (float)this.texturesCount;
        float maxU = minU + 1.0F / (float)this.texturesCount;
        float minV = (float) this.particleTextureIndexY / (float)this.texturesCount;
        float maxV = minV + 1.0F / (float)this.texturesCount;
        float scale = 0.1F * this.particleScale;
        if (this.particleTexture != null) {
            minU = this.particleTexture.getMinU();
            maxU = this.particleTexture.getMaxU();
            minV = this.particleTexture.getMinV();
            maxV = this.particleTexture.getMaxV();
        }

        float renderX = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
        float renderY = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
        float renderZ = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);
        int brightnessForRender = this.getBrightnessForRender(partialTicks);
        int j = brightnessForRender >> 16 & '\uffff';
        int k = brightnessForRender & '\uffff';

        buffer.pos(renderX - rotationX * scale - rotationXY * scale, renderY - rotationZ * scale, renderZ - rotationYZ * scale - rotationXZ * scale).tex(maxU, maxV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
        buffer.pos(renderX - rotationX * scale + rotationXY * scale, renderY + rotationZ * scale, renderZ - rotationYZ * scale + rotationXZ * scale).tex(maxU, minV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
        buffer.pos(renderX + rotationX * scale + rotationXY * scale, renderY + rotationZ * scale, renderZ + rotationYZ * scale + rotationXZ * scale).tex(minU, minV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
        buffer.pos(renderX + rotationX * scale - rotationXY * scale, renderY - rotationZ * scale, renderZ + rotationYZ * scale - rotationXZ * scale).tex(minU, maxV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
        Tessellator.getInstance().draw();
    }

    @Override
    public boolean isAlive() {
        return super.isAlive();
    }

    public IGTParticleHandler getGLHandler() {
        return HANDLER;
    }
}
