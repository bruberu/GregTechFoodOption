package gregtechfoodoption.worldgen.trees;

import gregtech.api.util.function.TriConsumer;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.block.GTFOTree;
import gregtechfoodoption.utils.GTFOUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

import static gregtechfoodoption.item.GTFOMetaItem.APRICOT;

public class ApricotTree extends GTFOTree {
    public static int LEAVES_COLOR = 0x87A92C;
    public ApricotTree() {
        super("apricot", 3);
        this.addCondition(new BiomeCondition(Biomes.MUTATED_SAVANNA, 4, 0.40));
        this.addCondition(new BiomeCondition(Biomes.SAVANNA, 2, 0.55));
    }

    @Override
    protected void generateLeaves(World world, BlockPos.MutableBlockPos pos, int trunkHeight, Random random, TriConsumer<World, BlockPos, IBlockState> notifier) {
        BlockPos.MutableBlockPos currentYPos = GTFOUtils.copy(pos);
        currentYPos.move(EnumFacing.UP, trunkHeight - 4);
        boolean atBottom = true;
        for (int i = 8; i > 0; i -= (random.nextInt(3) + 3)) {
            int layerSize = (int) Math.ceil(Math.sqrt(i));
            Iterable<BlockPos> iterator = BlockPos.getAllInBox(
                    currentYPos.offset(EnumFacing.NORTH, layerSize).offset(EnumFacing.WEST, layerSize),
                    currentYPos.offset(EnumFacing.SOUTH, layerSize).offset(EnumFacing.EAST, layerSize));
            int finalI = i;
            iterator.forEach(leavesPos -> {
                if (Math.abs(leavesPos.getX() - currentYPos.getX()) + Math.abs(leavesPos.getZ() - currentYPos.getZ()) <= Math.sqrt(finalI))
                    notifier.accept(world, leavesPos, getNaturalLeavesState());
            });
            if (atBottom) {
                i += 7;
                atBottom = false;
            }
            currentYPos.move(EnumFacing.UP);
        }
        notifier.accept(world, GTFOUtils.copy(pos).move(EnumFacing.UP, trunkHeight), getNaturalLeavesState()); // In case the top isn't covered.
    }

    @Override
    public int getMinTrunkHeight(Random random) {
        return super.getMinTrunkHeight(random);
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
        if (GTFOValues.rand.nextInt(chance / 15) == 0) {
            return APRICOT.getStackForm();
        }
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack getApple() {
        return APRICOT.getStackForm();
    }
}
