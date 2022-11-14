package gregtechfoodoption.client.particle;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class GTFOSprinkle extends Particle {
    private double posXFinal, posYFinal, posZFinal;

    public GTFOSprinkle(World worldIn, double posXIn, double posYIn, double posZIn, double posXFinal, double posYFinal, double posZFinal, int color) {
        super(worldIn, posXIn, posYIn, posZIn);
        this.particleRed = (float) (color - (color % (256 * 256))) / (256 * 256 * 256);
        this.particleGreen = (float) (color % (256 * 256) - color % 256) / (256 * 256);
        this.particleBlue = (float) (color % 256) / 256;

        this.posXFinal = posXFinal;
        this.posYFinal = posYFinal;
        this.posZFinal = posZFinal;

        this.setParticleTextureIndex(113);
        //this.setSize(0.01F, 0.01F);
        this.particleGravity = 0.06F;
        this.particleMaxAge = 10;

    }

    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

/*        if (this.bobTimer-- > 0) {
            this.motionX *= 0.02;
            this.motionY *= 0.02;
            this.motionZ *= 0.02;
            this.setParticleTextureIndex(113);
        } else {
            this.setParticleTextureIndex(112);
        }*/

        this.move((posXFinal - posX) / particleMaxAge, (posYFinal - posY) / particleMaxAge, (posZFinal - posZ) / particleMaxAge);

        if (this.particleMaxAge-- <= 0) {
            this.setExpired();
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
    public boolean isAlive() {
        return super.isAlive();
    }
}
