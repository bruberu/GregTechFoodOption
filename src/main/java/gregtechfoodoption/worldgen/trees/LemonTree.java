package gregtechfoodoption.worldgen.trees;

import gregtech.api.util.function.TriConsumer;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.block.GTFOTree;
import gregtechfoodoption.utils.GTFOUtils;
import gregtechfoodoption.worldgen.trees.condition.BiomeCondition;
import gregtechfoodoption.worldgen.trees.condition.TemperatureRainfallCondition;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

import static gregtechfoodoption.item.GTFOMetaItem.LEMON;

public class LemonTree extends GTFOTree {
    public static int LEAVES_COLOR = 0x87A92C;
    public LemonTree() {
        super("lemon", 4);
        this.addCondition(new BiomeCondition(Biomes.JUNGLE_EDGE, 3, 0.4));
        this.addCondition(new BiomeCondition(Biomes.FOREST, 1, 0.65));
        this.addCondition(new TemperatureRainfallCondition(5, 0.4, 0.7, 0.7, 0.3));

    }

    @Override
    protected void generateLeaves(World world, BlockPos.MutableBlockPos pos, int trunkHeight, Random random, TriConsumer<World, BlockPos, IBlockState> notifier) {
        BlockPos.MutableBlockPos currentYPos = GTFOUtils.copy(pos);
        currentYPos.move(EnumFacing.UP, trunkHeight - 4);
        for (int i = 1; i < 12; i += (random.nextInt(3) + 2)) {
            int layerSize = (int) Math.ceil(Math.sqrt(i));
            Iterable<BlockPos> iterator = BlockPos.getAllInBox(
                    currentYPos.offset(EnumFacing.NORTH, layerSize).offset(EnumFacing.WEST, layerSize),
                    currentYPos.offset(EnumFacing.SOUTH, layerSize).offset(EnumFacing.EAST, layerSize));
            int finalI = i;
            iterator.forEach(leavesPos -> {
                if (Math.abs(leavesPos.getX() - currentYPos.getX()) + Math.abs(leavesPos.getZ() - currentYPos.getZ()) <= Math.sqrt(finalI) && random.nextInt(16 - finalI) != 0)
                    notifier.accept(world, leavesPos, getNaturalLeavesState());
            });
            currentYPos.move(EnumFacing.UP);
        }
        notifier.accept(world, GTFOUtils.copy(pos).move(EnumFacing.UP, trunkHeight), getNaturalLeavesState()); // In case the top isn't covered.
    }

    @Override
    public int getBlockColor(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
        return LEAVES_COLOR;
    }

    @Override
    public int getItemColor(ItemStack stack, int tintIndex) {
        return LEAVES_COLOR;
    }

    @Override
    public ItemStack getAppleDrop(int chance) {
        if (GTFOValues.rand.nextInt(chance / 10) == 0) {
            return LEMON.getStackForm(GTFOValues.rand.nextInt(2) + 1);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack getApple() {
        return LEMON.getStackForm();
    }
    @Override
    public int getMinTrunkHeight(Random random) {
        return 6 + random.nextInt(3);
    }

}
