package gregtechfoodoption.entity;

import gregtech.api.GTValues;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityStrongSnowball extends EntitySnowball {

    public EntityStrongSnowball(World worldIn) {
        super(worldIn);
    }
    public EntityStrongSnowball(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    protected void onImpact(RayTraceResult result) {
        if (result.entityHit instanceof EntitySnowman || result.entityHit instanceof EntityPlayer)
            return;

        if (result.entityHit != null) {
            int i = 2 + GTValues.RNG.nextInt(2);
            if (result.entityHit instanceof EntityBlaze) {
                i += 3;
            }
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)i);
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }

    }
}
