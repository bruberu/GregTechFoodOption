package gregtechfoodoption.worldgen.trees;

import gregtechfoodoption.worldgen.GTFOFeatureGen;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

import javax.annotation.Nonnull;
import java.util.Random;

public class GTFOTreeGen extends GTFOFeatureGen {
    public GTFOTreeGen(boolean notify, GTFOTree tree) {
        super(notify, tree);
    }

    public boolean generateImpl(@Nonnull World world, @Nonnull Random random, BlockPos.MutableBlockPos pos) {
        SaplingGrowTreeEvent event = new SaplingGrowTreeEvent(world, random, pos);
        MinecraftForge.TERRAIN_GEN_BUS.post(event);
        if (event.getResult() == Event.Result.DENY) {
            return false;
        }
        return feature.generate(world, pos, random, this::setBlockSafely);
    }

}
