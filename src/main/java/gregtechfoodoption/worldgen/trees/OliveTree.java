package gregtechfoodoption.worldgen.trees;

import gregtech.api.util.function.TriConsumer;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.block.GTFOTree;
import gregtechfoodoption.utils.GTFOUtils;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

import static gregtechfoodoption.item.GTFOMetaItem.OLIVE;

public class OliveTree extends GTFOTree {
    public static int LEAVES_COLOR = 0x828E5A;

    public OliveTree() {
        super("olive", 6);
        this.addCondition(new BiomeCondition(Biomes.BIRCH_FOREST, 5, 0.65));
        this.addCondition(new BiomeCondition(Biomes.FOREST, 2, 0.80));
        this.addCondition(new BiomeCondition(Biomes.PLAINS, 1, 0.88));
    }

    @Override
    protected void generateTrunk(World world, BlockPos.MutableBlockPos pos, int maxHeight, Random random, TriConsumer<World, BlockPos, IBlockState> notifier) {
        BlockPos.MutableBlockPos upN = GTFOUtils.copy(pos);
        BlockPos.MutableBlockPos upNSplit = GTFOUtils.copy(upN);
        EnumFacing splitDirection = EnumFacing.byHorizontalIndex(random.nextInt(4));
        int splittingHeight = maxHeight - 1 - random.nextInt(3);
        for (int height = 0; height < maxHeight; ++height) {
            IBlockState state = world.getBlockState(upN);

            if (state.getBlock().isAir(state, world, upN) || state.getBlock().isLeaves(state, world, upN)) {
                notifier.accept(world, upN, logState.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y));
            }

            if (height == splittingHeight) {
                upNSplit.move(splitDirection);
            }
            if (height >= splittingHeight) {
                notifier.accept(world, upNSplit, logState.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y));
                if (random.nextInt(2) == 0)
                    upNSplit.move(splitDirection);
            }

            upN.move(EnumFacing.UP);
            upNSplit.move(EnumFacing.UP);
        }
    }

    @Override
    protected void generateLeaves(World world, BlockPos.MutableBlockPos pos, int trunkHeight, Random random, TriConsumer<World, BlockPos, IBlockState> notifier) {
        BlockPos.MutableBlockPos currentYPos = GTFOUtils.copy(pos);
        currentYPos.move(EnumFacing.UP, trunkHeight);
        for (int i = 25; i > 0; i -= (random.nextInt(8) + 13)) {
            int layerSize = (int) Math.ceil(Math.sqrt(i));
            Iterable<BlockPos> iterator = BlockPos.getAllInBox(
                    currentYPos.offset(EnumFacing.NORTH, layerSize).offset(EnumFacing.WEST, layerSize),
                    currentYPos.offset(EnumFacing.SOUTH, layerSize).offset(EnumFacing.EAST, layerSize));
            int finalI = i;
            iterator.forEach(leavesPos -> {
                if (Math.abs(leavesPos.getX() - currentYPos.getX()) + Math.abs(leavesPos.getZ() - currentYPos.getZ()) <= Math.sqrt(finalI))
                    notifier.accept(world, leavesPos, getNaturalLeavesState());
            });
            currentYPos.move(EnumFacing.UP);
        }
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
    protected int getMooreRadiusAtHeight(int height, int trunkHeight) {
        if (height < trunkHeight - 3)
            return 0;
        if (height < trunkHeight)
            return 4 - (trunkHeight - height);
        return 0;
    }

    @Override
    public ItemStack getAppleDrop(int chance) {
        if (GTFOValues.rand.nextInt(chance / 15) == 0) {
            return OLIVE.getStackForm(GTFOValues.rand.nextInt(4) + 1);
        }
        return ItemStack.EMPTY;
    }


    @Override
    public ItemStack getApple() {
        return OLIVE.getStackForm();
    }
}
