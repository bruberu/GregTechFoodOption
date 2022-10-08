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

import static gregtechfoodoption.item.GTFOMetaItem.ORANGE;

public class OrangeTree extends GTFOTree {
    public static int LEAVES_COLOR = 0x76c92c;

    public OrangeTree() {
        super("orange", 1);
        this.addCondition(new BiomeCondition(Biomes.SAVANNA_PLATEAU, 6, 0.25));
        this.addCondition(new BiomeCondition(Biomes.SAVANNA, 3, 0.45));
    }

    @Override
    public int getMinTrunkHeight(Random random) {
        return 2 + random.nextInt(3);
    }

    @Override
    protected void generateTrunk(World world, BlockPos.MutableBlockPos pos, int maxHeight, Random random, TriConsumer<World, BlockPos, IBlockState> notifier) {
        BlockPos.MutableBlockPos upN = GTFOUtils.copy(pos);
        for (int height = 0; height < maxHeight; ++height) {
            notifier.accept(world, upN, logState.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.Y));
            if (height > 0) {
                EnumFacing randomFacing = EnumFacing.byHorizontalIndex(random.nextInt(4));
                notifier.accept(world, upN.offset(randomFacing), logState.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.fromFacingAxis(randomFacing.getAxis())));
            }
            upN.move(EnumFacing.UP);
        }
    }

    @Override
    protected void generateLeaves(World world, BlockPos.MutableBlockPos pos, int trunkHeight, Random random, TriConsumer<World, BlockPos, IBlockState> notifier) {
        BlockPos.MutableBlockPos currentYPos = GTFOUtils.copy(pos);
        currentYPos.move(EnumFacing.UP);
        for (int i = 0; i < trunkHeight + 1; i++) {
            Iterable<BlockPos> iterator = BlockPos.getAllInBox(
                    currentYPos.offset(EnumFacing.NORTH, 2).offset(EnumFacing.WEST, 2),
                    currentYPos.offset(EnumFacing.SOUTH, 2).offset(EnumFacing.EAST, 2));
            int finalI = i;
            iterator.forEach(leavesPos -> {
                if (Math.abs(leavesPos.getX() - currentYPos.getX()) + Math.abs(leavesPos.getZ() - currentYPos.getZ()) < 3 &&
                        (finalI < trunkHeight || ((Math.abs(leavesPos.getX() - currentYPos.getX()) + Math.abs(leavesPos.getZ() - currentYPos.getZ()) == 1 || random.nextInt(2) == 0))))
                    notifier.accept(world, leavesPos, getNaturalLeavesState());
            });
            currentYPos.move(EnumFacing.UP);
        }
        notifier.accept(world, currentYPos, getNaturalLeavesState());
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
        if (height == 0)
            return 0;
        if (height < trunkHeight + 1)
            return 1;
        return 0;
    }

    @Override
    public ItemStack getAppleDrop(int chance) {
        if (GTFOValues.rand.nextInt(chance / 10) == 0) {
            return ORANGE.getStackForm(GTFOValues.rand.nextInt(2) + 1);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack getApple() {
        return ORANGE.getStackForm();
    }

}