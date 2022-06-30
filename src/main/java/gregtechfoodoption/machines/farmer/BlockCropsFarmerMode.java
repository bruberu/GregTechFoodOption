package gregtechfoodoption.machines.farmer;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCropsFarmerMode extends CustomCropFarmerMode {

    public BlockCropsFarmerMode(Block crop, ItemStack seed) {
        super(crop, seed);
        if (!(crop instanceof BlockCrops))
            throw new IllegalArgumentException("Crop " + crop.getRegistryName() + " is not a BlockCrops!");
    }

    public BlockCropsFarmerMode(Block crop, Item seed) {
        super(crop, seed);
        if (!(crop instanceof BlockCrops))
            throw new IllegalArgumentException("Crop " + crop.getRegistryName() + " is not a BlockCrops!");
    }

    @Override
    public boolean canOperate(IBlockState state, MetaTileEntityFarmer farmer, BlockPos pos, World world) {
        return state.getBlock().isAssociatedBlock(crop) && ((BlockCrops) crop).isMaxAge(state);
    }


}
