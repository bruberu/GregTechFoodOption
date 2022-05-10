package gregtechfoodoption.utils;

import gregtech.api.util.GregFakePlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;

public class GTFODamageSources {
    private static final DamageSource EXTRACTION = new DamageSource("extraction").setDamageBypassesArmor();

    public static DamageSource getExterminationDamage(World worldIn) {
        return new EntityDamageSource("extermination", new GregFakePlayer(worldIn)).setDamageBypassesArmor();
    }

    public static DamageSource getExtractionDamage() {
        return EXTRACTION;
    }

}
