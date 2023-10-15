package gregtechfoodoption.worldgen.berries;

import gregtechfoodoption.worldgen.GTFOFeatureGen;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class GTFOBerryGen extends GTFOFeatureGen {
    public GTFOBerryGen(GTFOBerry berry) {
        super(true, berry);
    }

    @Override
    public boolean generateImpl(World world, Random random, BlockPos.MutableBlockPos pos) {
        return feature.generate(world, pos, random, this::setBlockSafely);
    }
}
