package gregtechfoodoption.integration.enderio;

import crazypants.enderio.api.farm.IFarmer;
import crazypants.enderio.api.farm.IHarvestResult;
import crazypants.enderio.base.farming.farmers.CustomSeedFarmer;
import gregtechfoodoption.block.GTFORootCrop;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GTFORootCropFarmer extends CustomSeedFarmer {
    public GTFORootCropFarmer(@Nonnull Block plantedBlock, @Nonnull ItemStack seeds) {
        super(plantedBlock, seeds);
    }

    @Override
    public boolean canPlant(@Nonnull ItemStack stack) {
        return stack.isItemEqual(seeds);
    }

    @Override
    public boolean canHarvest(@Nonnull IFarmer farm, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        return state.getBlock() instanceof GTFORootCrop;
    }

    @Nullable
    @Override
    public IHarvestResult harvestBlock(@Nonnull IFarmer farm, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        GTFORootCrop crop = (GTFORootCrop) state.getBlock();
        // If crop is already harvestable for seeds, just harvest it
        // If crop can be harvested for produce AND there exists an available seed to replant it, then harvest it too
        if (crop.seedHarvestable(state) || (crop.cropHarvestable(state) && farm.hasSeed(crop.getSeedStack(), pos))) {
            return super.harvestBlock(farm, pos, state);
        }

        return null;
    }
}
