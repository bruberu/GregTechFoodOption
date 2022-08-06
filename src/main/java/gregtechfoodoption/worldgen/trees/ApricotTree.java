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

import static gregtechfoodoption.item.GTFOMetaItem.APRICOT;

public class ApricotTree extends GTFOTree {
    public ApricotTree() {
        super("apricot", 3);
    }

    @Override
    public boolean grow(World world, BlockPos.MutableBlockPos pos, Random random, TriConsumer<World, BlockPos, IBlockState> notifier) {
        return false;
    }

    @Override
    public int getBlockColor(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
        return 0;
    }

    @Override
    public int getItemColor(ItemStack stack, int tintIndex) {
        return 0;
    }

    @Override
    protected int getMooreRadiusAtHeight(int height, int trunkHeight) {
        return 0;
    }

    @Override
    public ItemStack getApple() {
        if (GTFOValues.rand.nextInt(20) == 0) {
            return APRICOT.getStackForm();
        }
        return ItemStack.EMPTY;
    }
}
