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
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;

import java.util.List;

public interface FarmerMode {
    boolean canOperate(IBlockState state, MetaTileEntityFarmer farmer, BlockPos pos, World world);

    default void harvest(IBlockState state, World world, MutableBlockPos pos, MetaTileEntityFarmer farmer) {
        world.playEvent(2001, pos, Block.getStateId(state));
        world.setBlockToAir(pos);
    }

    default List<ItemStack> getDrops(IBlockState state, World world, MutableBlockPos pos, MetaTileEntityFarmer farmer) {
        NonNullList<ItemStack> drops = NonNullList.create();
        state.getBlock().getDrops(drops, world, pos, state,0);
        return drops;
    }

    // If the farming mode can work with this ItemStack in any situation.
    boolean canPlaceItem(ItemStack stack);

    // If the farming mode can actually place something in this position.
    default boolean canPlaceAt(MutableBlockPos operationPos, MutableBlockPos farmerPos, EnumFacing facing, World world) {
        return true;
    }

    default EnumActionResult place(ItemStack stack, World world, MutableBlockPos pos, MetaTileEntityFarmer farmer) {
        GregFakePlayer placer = farmer.fakePlayer;
        placer.setHeldItem(EnumHand.MAIN_HAND, stack);
        return stack.getItem().onItemUse(placer, world, pos.down(), EnumHand.MAIN_HAND, EnumFacing.UP, 0, 0, 0);
    }
}
