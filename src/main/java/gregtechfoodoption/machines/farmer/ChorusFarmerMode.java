package gregtechfoodoption.machines.farmer;

import gregtechfoodoption.utils.GTFOUtils;
import it.unimi.dsi.fastutil.PriorityQueue;
import it.unimi.dsi.fastutil.objects.ObjectArrayPriorityQueue;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChorusFlower;
import net.minecraft.block.BlockChorusPlant;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.*;


public class ChorusFarmerMode implements FarmerMode {
    @Override
    public boolean canOperate(IBlockState blockState, MetaTileEntityFarmer farmer, BlockPos pos, World world) {
        if (blockState.getBlock() != Blocks.CHORUS_PLANT && blockState.getBlock() != Blocks.CHORUS_FLOWER)
            return false;
        for (Iterator<BlockPos> it = getChorusIterator(pos, world); it.hasNext(); ) {
            IBlockState state = world.getBlockState(it.next());
            if (state.getBlock() == Blocks.CHORUS_FLOWER && state.getValue(BlockChorusFlower.AGE) != 5) {
                return false; // Let all flowers grow
            }
        }
        return true;
    }

    private Iterator<BlockPos> getChorusIterator(BlockPos pos, World world) {
        return new Iterator<>() {
            final PriorityQueue<BlockPos> plantQueue = new ObjectArrayPriorityQueue<>(new BlockPos[]{pos});
            final Set<BlockPos> visited = new ObjectOpenHashSet<>();

            final World myWorld = world;

            @Override
            public boolean hasNext() {
                return !plantQueue.isEmpty();
            }

            @Override
            public BlockPos next() {
                BlockPos blockPos = plantQueue.dequeue();
                visited.add(blockPos);
                IBlockState state = myWorld.getBlockState(blockPos);
                if (state.getPropertyKeys().contains(BlockChorusPlant.UP)) {
                    state = Blocks.CHORUS_PLANT.getActualState(state, myWorld, blockPos);
                    if (state.getValue(BlockChorusPlant.UP)) {
                        add(blockPos.up());
                    }
                    if (state.getValue(BlockChorusPlant.EAST)) {
                        add(blockPos.east());
                    }
                    if (state.getValue(BlockChorusPlant.WEST)) {
                        add(blockPos.west());
                    }
                    if (state.getValue(BlockChorusPlant.NORTH)) {
                        add(blockPos.north());
                    }
                    if (state.getValue(BlockChorusPlant.SOUTH)) {
                        add(blockPos.south());
                    }
                }
                return blockPos;
            }

            private void add(BlockPos pos) {
                if (!visited.contains(pos)) {
                    plantQueue.enqueue(pos);
                }
            }
        };
    }

    @Override
    public List<ItemStack> getDrops(IBlockState blockState, World world, BlockPos.MutableBlockPos pos, MetaTileEntityFarmer farmer) {
        NonNullList<ItemStack> drops = NonNullList.create();
        for (Iterator<BlockPos> it = getChorusIterator(pos, world); it.hasNext(); ) {
            IBlockState state = world.getBlockState(it.next());
            if (state.getBlock() == Blocks.CHORUS_FLOWER) {
                drops.add(new ItemStack(Blocks.CHORUS_FLOWER, 1)); // Because the normal function isn't very nice.
            } else {
                state.getBlock().getDrops(drops, world, pos, state, 0);
            }
        }
        return drops;
    }

    @Override
    public boolean canPlaceItem(ItemStack stack) {
        return stack.isItemEqual(new ItemStack(Blocks.CHORUS_FLOWER));
    }

    @Override
    public void harvest(IBlockState state, World world, BlockPos.MutableBlockPos pos, MetaTileEntityFarmer farmer) {
        List<BlockPos> blocks = new ArrayList<>();
        for (Iterator<BlockPos> it = getChorusIterator(pos, world); it.hasNext(); ) {
            blocks.add(it.next());
        }
        Collections.sort(blocks);
        for (int i = blocks.size() - 1; i >= 0; i--) {
            world.playEvent(2001, blocks.get(i), Block.getStateId(state));
            world.setBlockToAir(blocks.get(i));
        }
    }
}
