package gregtechfoodoption.worldgen.trees;

import gregtech.api.util.function.TriConsumer;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.block.GTFOTree;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

import static gregtechfoodoption.item.GTFOMetaItem.ORANGE;

public class OrangeTree extends GTFOTree {
    public static int LEAVES_COLOR = 0x76c92c;

    public OrangeTree() {
        super("orange", 1);
    }

    @Override
    public boolean grow(World world, BlockPos.MutableBlockPos pos, Random random, TriConsumer<World, BlockPos, IBlockState> notifier) {

        return false;
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
        return 0;
    }

    @Override
    public ItemStack getApple() {
        if (GTFOValues.rand.nextInt(20) == 0) {
            return ORANGE.getStackForm();
        }
        return ItemStack.EMPTY;
    }
}