package gregtechfoodoption.machines.farmer;

import gregtech.api.util.GregFakePlayer;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CocoaFarmerMode implements FarmerMode {
    public static final int COCOA_MAX_AGE = 2;

    @Override
    public boolean canOperate(IBlockState state, MetaTileEntityFarmer farmer, BlockPos pos, World world) {
        return state.getBlock() instanceof BlockCocoa && (state.getBlock().getMetaFromState(state) & 15) >> 2 == COCOA_MAX_AGE;
    }

    @Override
    public boolean canPlaceItem(ItemStack stack) {
        return stack.isItemEqual(new ItemStack(Items.DYE, 1, EnumDyeColor.BROWN.getDyeDamage()));
    }

    @Override
    public EnumActionResult place(ItemStack stack, World world, BlockPos.MutableBlockPos pos, MetaTileEntityFarmer farmer) {
        GregFakePlayer placer = farmer.fakePlayer;
        placer.setHeldItem(EnumHand.MAIN_HAND, stack);
        for (EnumFacing facing : EnumFacing.HORIZONTALS) {
            if (stack.onItemUse(placer, world, pos.offset(facing), EnumHand.MAIN_HAND, facing.getOpposite(), 0, 0, 0) == EnumActionResult.SUCCESS)
                return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }
}
