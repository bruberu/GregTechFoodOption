package gregtechfoodoption.machines.farmer;

import gregtech.api.util.GregFakePlayer;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public interface FarmerMode {
    boolean canOperate(IBlockState state, MetaTileEntityFarmer farmer);

    default void harvest(IBlockState state, World world, BlockPos pos, MetaTileEntityFarmer farmer) {
        world.playEvent(2001, pos, Block.getStateId(state));
        world.setBlockToAir(pos);
    }

    default List<ItemStack> getDrops(IBlockState state, World world, BlockPos pos, MetaTileEntityFarmer farmer) {
        NonNullList<ItemStack> drops = NonNullList.create();
        state.getBlock().getDrops(drops, world, pos, state,0);
        return drops;
    }

    // If the farming mode can work with this ItemStack in any situation.
    boolean canPlaceItem(ItemStack stack);

    // If the farming mode can actually place something in this position.
    default boolean canPlaceAt(BlockPos operationPos, BlockPos farmerPos) {
        return true;
    }

    default EnumActionResult place(ItemStack stack, World world, BlockPos pos, MetaTileEntityFarmer farmer) {
        GregFakePlayer placer = new GregFakePlayer(world);
        placer.setHeldItem(EnumHand.MAIN_HAND, stack);
        return stack.onItemUse(placer, world, pos.down(), EnumHand.MAIN_HAND, EnumFacing.UP, 0, 0, 0);
    }
}
