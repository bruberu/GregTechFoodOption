package gregtechfoodoption.machines.farmer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CustomCropFarmerMode implements FarmerMode {

    private final BlockCrops crop;
    private final ItemStack seed;

    public CustomCropFarmerMode(Block crop, Item seed) {
        this(crop, seed.getDefaultInstance());
    }

    public CustomCropFarmerMode(Block crop, ItemStack seed) {
        if (crop instanceof BlockCrops)
            this.crop = (BlockCrops) crop;
        else
            throw new IllegalArgumentException(crop.getRegistryName() + " is not a BlockCrops!");
        this.seed = seed;
    }

    @Override
    public boolean canOperate(IBlockState state, MetaTileEntityFarmer farmer) {
        return state.getBlock().isAssociatedBlock(crop) && crop.isMaxAge(state);
    }

    @Override
    public boolean canPlaceItem(ItemStack stack) {
        return stack.isItemEqual(seed);
    }
}
