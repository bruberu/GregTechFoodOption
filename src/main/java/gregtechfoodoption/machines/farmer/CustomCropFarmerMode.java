package gregtechfoodoption.machines.farmer;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CustomCropFarmerMode implements FarmerMode {

    protected final Block crop;
    protected final ItemStack seed;

    public CustomCropFarmerMode(Block crop, Item seed) {
        this(crop, new ItemStack(seed));
    }

    public CustomCropFarmerMode(Block crop, ItemStack seed) {
        this.crop = crop;
        this.seed = seed;
    }

    @Override
    public boolean canOperate(IBlockState state, MetaTileEntityFarmer farmer, BlockPos pos, World world) {
        return state.getBlock().isAssociatedBlock(crop);
    }

    @Override
    public boolean canPlaceItem(ItemStack stack) {
        return stack.isItemEqual(seed);
    }
}
