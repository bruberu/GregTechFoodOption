package gregtechfoodoption.utils;

import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;

public class GTFODamageSources {
    public static FakePlayer EXTERMINATOR;
    public static final DamageSource EXTRACTION = new DamageSource("extraction").setDamageBypassesArmor();
    public static final DamageSource CYANIDE = new DamageSource("cyanide").setDamageBypassesArmor();


    public static DamageSource getExterminationDamage(World worldIn) {
        return new EntityDamageSource("extermination", EXTERMINATOR).setDamageBypassesArmor();
    }

}
