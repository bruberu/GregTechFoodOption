package gregtechfoodoption.machines.farmer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;

public class CustomCropFarmerMode extends DefaultCropFarmerMode {

    private final BlockCrops crop;

    public CustomCropFarmerMode(Block crop) {
        if (crop instanceof BlockCrops)
            this.crop = (BlockCrops) crop;
        else
            throw new IllegalArgumentException(crop.getRegistryName() + " is not a BlockCrops!");
    }

    @Override
    public boolean canOperate(IBlockState state, MetaTileEntityFarmer farmer) {
        return state.getBlock().isAssociatedBlock(crop) && crop.isMaxAge(state);
    }
}
