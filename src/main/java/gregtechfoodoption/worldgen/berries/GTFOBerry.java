package gregtechfoodoption.worldgen.berries;

import gregtech.api.util.function.TriConsumer;
import gregtechfoodoption.block.GTFOBerryBush;
import gregtechfoodoption.worldgen.GTFOFeature;
import gregtechfoodoption.worldgen.trees.GTFOTreeGen;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class GTFOBerry extends GTFOFeature {
    public final GTFOBerryBush berryBush;

    public GTFOBerry(int seed, GTFOBerryBush berryBush) {
        super(seed);
        this.berryBush = berryBush;
        this.WORLD_GEN_INSTANCE = new GTFOBerryGen(this);
    }

    @Override
    public boolean generate(World world, BlockPos.MutableBlockPos pos, Random random, TriConsumer<World, BlockPos, IBlockState> notifier) {
        if (canGrowAt(world, pos)) {
            notifier.accept(world, pos, berryBush.withAge(2));
            for (int i = 0; i < random.nextInt(3); i++) {
                BlockPos other = pos.add(random.nextInt(-2, 3), random.nextInt(-2, 3), 0);
                if (canGrowAt(world, other)) {
                    notifier.accept(world, other, berryBush.withAge(2));
                }
            }
            return true;
        }

        return false;
    }

    protected boolean canGrowInto(Block blockType) {
        Material material = blockType.getDefaultState().getMaterial();
        return material == Material.AIR || blockType == Blocks.VINE || material == Material.SNOW;
    }

    protected boolean canGrowAt(World world, BlockPos pos) {
        if (pos.getY() >= 1 && pos.getY() < world.getHeight()) {
            IBlockState soilState = world.getBlockState(pos.down());
            IBlockState currentState = world.getBlockState(pos);
            return canGrowInto(currentState.getBlock()) && soilState.getBlock().canSustainPlant(soilState, world, pos.down(), EnumFacing.UP, berryBush);
        }
        return false;
    }
}
