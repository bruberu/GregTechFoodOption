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

import static gregtechfoodoption.item.GTFOMetaItem.NUTMEG_SEED;

public class NutmegTree extends GTFOTree {
    public static int LEAVES_COLOR = 0x6DB626;

    public NutmegTree() {
        super("nutmeg", 8);
        this.addCondition(new BiomeCondition(Biomes.JUNGLE, 3, 0.3));
        this.addCondition(new TemperatureRainfallCondition(3, 0.4, 0.85, 1.0, 0.3));
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
    protected void generateLeaves(World world, BlockPos.MutableBlockPos pos, int trunkHeight, Random random, TriConsumer<World, BlockPos, IBlockState> notifier) {
        BlockPos.MutableBlockPos currentYPos = GTFOUtils.copy(pos);
        currentYPos.move(EnumFacing.UP, trunkHeight - 2);
        for (double i = 3; i > 0; i -= (random.nextDouble() / 2 + 0.5)) {
            int layerSize = (int) Math.ceil(i);
            Iterable<BlockPos> iterator = BlockPos.getAllInBox(
                    currentYPos.offset(EnumFacing.NORTH, layerSize).offset(EnumFacing.WEST, layerSize),
                    currentYPos.offset(EnumFacing.SOUTH, layerSize).offset(EnumFacing.EAST, layerSize));
            double finalI = i;
            iterator.forEach(leavesPos -> {
                if (Math.pow(Math.pow(Math.abs(leavesPos.getX() - currentYPos.getX()), 2) + Math.pow(Math.abs(leavesPos.getZ() - currentYPos.getZ()), 2), 0.5) <= finalI)
                    notifier.accept(world, leavesPos, getNaturalLeavesState());
            });
            currentYPos.move(EnumFacing.UP);
        }
        notifier.accept(world, GTFOUtils.copy(pos).move(EnumFacing.UP, trunkHeight), getNaturalLeavesState()); // In case the top isn't covered.
    }

    @Override
    public ItemStack getAppleDrop(int chance) {
        if (GTFOValues.rand.nextInt(chance / 10) == 0) {
            return NUTMEG_SEED.getStackForm(GTFOValues.rand.nextInt(2) + 1);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack getApple() {
        return NUTMEG_SEED.getStackForm();
    }
}
