package gregtechfoodoption.worldgen.trees;

import gregtech.api.util.function.TriConsumer;
import gregtechfoodoption.block.GTFOTree;
import gregtechfoodoption.utils.GTFOUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nonnull;
import java.util.Random;

public class BananaTree extends GTFOTree {

    public static int LEAVES_COLOR = 0x396A2E;

    public BananaTree() {
        super("banana", 0);
        this.addCondition(new BiomeCondition(Biomes.JUNGLE, 5, 0.25));
        this.addCondition(new BiomeCondition(Biomes.JUNGLE_EDGE, 5, 0.2));
        this.addCondition(new BiomeCondition(Biomes.JUNGLE_HILLS, 5, 0.25));
        this.addCondition(new BiomeCondition(Biomes.MUTATED_JUNGLE, 5, 0.2));
        this.addCondition(new BiomeCondition(Biomes.MUTATED_JUNGLE_EDGE, 5, 0.15));
    }

    @Override
    public boolean grow(@Nonnull World world, BlockPos.MutableBlockPos pos, Random random, TriConsumer<World, BlockPos, IBlockState> notifier) {
        int minHeight = getMinTrunkHeight(random);

        // Check if tree fits in world
        if (pos.getY() >= 1 && pos.getY() + minHeight + 1 <= world.getHeight()) {
            if (isSuitableLocation(world, pos, minHeight)) {
                IBlockState state = world.getBlockState(pos.down());
                if (state.getBlock().canSustainPlant(state, world, pos.down(), EnumFacing.UP, this.getPlantableSapling()) && pos.getY() < world.getHeight() - minHeight - 1) {
                    state.getBlock().onPlantGrow(state, world, pos.down(), pos);
                    generateTrunk(world, pos, minHeight, notifier);
                    generateLeaves(world, pos, minHeight, random, notifier);
                    return true;
                }
            }
        }
        return false;
    }

    protected void generateLeaves(World world, BlockPos.MutableBlockPos pos, int height, Random random, TriConsumer<World, BlockPos, IBlockState> notifier) {
        // Generate top
        notifier.accept(world, pos.up(height - 2), this.leavesState);
        notifier.accept(world, pos.up(height - 1), this.leavesState);
        notifier.accept(world, pos.up(height).offset(EnumFacing.byHorizontalIndex(random.nextInt(4))), this.leavesState);

        // Generate sideways leaves
        for (int i = 0; i < 4; i++) {
            int leafOffset = random.nextInt(2);
            BlockPos.MutableBlockPos posCopy = GTFOUtils.copy(pos.up(height - 4 + leafOffset));

            for (int j = 0; j < 3; j++) {
                posCopy.move(EnumFacing.byHorizontalIndex(i));
                notifier.accept(world, posCopy, this.leavesState);
                if (j == 0)
                    posCopy.move(EnumFacing.UP);
            }
        }

        // Generate ring at height - 3 for extra fullness
        for (int i = 0; i < 4; i++) {
            notifier.accept(world, pos.up(height - 3).offset(EnumFacing.byHorizontalIndex(i)), this.leavesState);
            notifier.accept(world, pos.up(height - 3).offset(EnumFacing.byHorizontalIndex(i)).offset(EnumFacing.byHorizontalIndex(i).rotateY()), this.leavesState);
        }
    }

    protected void generateTrunk(World world, BlockPos.MutableBlockPos pos, int minHeight, TriConsumer<World, BlockPos, IBlockState> notifier) {
        BlockPos.MutableBlockPos upN = GTFOUtils.copy(pos);
        for (int height = 0; height < minHeight - 2; ++height) {
            upN.move(EnumFacing.UP);
            IBlockState state = world.getBlockState(upN);

            if (state.getBlock().isAir(state, world, upN) || state.getBlock().isLeaves(state, world, upN)) {
                notifier.accept(world, pos.up(height), logState.withProperty(BlockLog.LOG_AXIS, height == minHeight - 3 ? BlockLog.EnumAxis.NONE : BlockLog.EnumAxis.Y));
            }
        }
    }

    @Override
    protected int getMooreRadiusAtHeight(int height, int totalHeight) {
        if (height < totalHeight - 4)
            return 0;
        if (height == totalHeight - 4)
            return 1;
        if (height < totalHeight - 1)
            return 3;
        if (height < totalHeight)
            return 0;
        return 1;
    }

    @Override
    public int getBlockColor(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
        return LEAVES_COLOR;
    }

    @Override
    public int getItemColor(ItemStack stack, int tintIndex) {
        return LEAVES_COLOR;
    }

    public int getGrowHeight(World world, BlockPos pos) {
        BlockPos below = pos.down();
        IBlockState baseState = world.getBlockState(below);
        Block baseBlock = baseState.getBlock();
        if (baseBlock.isAir(baseState, world, below) || !baseBlock.canSustainPlant(baseState, world, below, EnumFacing.UP, (IPlantable) saplingState.getBlock()) || (!world.isAirBlock(pos.up()) && world.getBlockState(pos.up()).getBlock() != saplingState.getBlock()))
            return 0;
        int height = 1;
        pos = pos.up();
        while (world.isAirBlock(pos) && height < 7) {
            pos = pos.up();
            height++;
        }
        return height;
    }
}
