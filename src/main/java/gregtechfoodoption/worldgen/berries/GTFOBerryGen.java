package gregtechfoodoption.worldgen.berries;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.worldgen.GTFOFeatureGen;

public class GTFOBerryGen extends GTFOFeatureGen {

    public GTFOBerryGen(GTFOBerry berry) {
        super(true, berry);
    }

    @Override
    public boolean configOption() {
        return GTFOConfig.gtfoWorldgenConfig.enableGTFOBerries;
    }

    @Override
    public boolean generateImpl(World world, Random random, BlockPos.MutableBlockPos pos) {
        return feature.generate(world, pos, random, this::setBlockSafely);
    }
}
