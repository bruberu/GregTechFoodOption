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

import javax.annotation.Nonnull;
import java.util.Random;

import static gregtechfoodoption.item.GTFOMetaItem.BANANA;

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
        {
            BlockPos.MutableBlockPos posCopy = GTFOUtils.copy(pos.up(height - 3));
            for (int i = 0; i < 3; i++) {
                posCopy.move(EnumFacing.UP);
                notifier.accept(world, posCopy, getNaturalLeavesState());
                if (i == 1) {
                    posCopy.move(EnumFacing.byHorizontalIndex(random.nextInt(4)));
                    notifier.accept(world, posCopy, getNaturalLeavesState());
                }
            }
        }

        // Generate sideways leaves
        for (int i = 0; i < 4; i++) {
            int leafOffset = random.nextInt(2);
            BlockPos.MutableBlockPos posCopy = GTFOUtils.copy(pos.up(height - 4 + leafOffset));

            for (int j = 0; j < 3; j++) {
                posCopy.move(EnumFacing.byHorizontalIndex(i));
                notifier.accept(world, posCopy, getNaturalLeavesState());
                if (j == 0) {
                    posCopy.move(EnumFacing.UP);
                    notifier.accept(world, posCopy, getNaturalLeavesState());
                }
            }
        }

        // Generate ring at height - 3 for extra fullness
        for (int i = 0; i < 4; i++) {
            notifier.accept(world, pos.up(height - 3).offset(EnumFacing.byHorizontalIndex(i)), getNaturalLeavesState());
            notifier.accept(world, pos.up(height - 3).offset(EnumFacing.byHorizontalIndex(i)).offset(EnumFacing.byHorizontalIndex(i).rotateY()), getNaturalLeavesState());
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
    public ItemStack getApple() {
        if (GTFOValues.rand.nextInt(20) == 0) {
            return BANANA.getStackForm(GTFOValues.rand.nextInt(4) + 3);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public int getBlockColor(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
        return LEAVES_COLOR;
    }

    @Override
    public int getItemColor(ItemStack stack, int tintIndex) {
        return LEAVES_COLOR;
    }

}
