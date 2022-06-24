package gregtechfoodoption.machines.farmer;

import gregtech.api.util.StreamUtils;
import gregtechfoodoption.block.GTFORootCrop;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

public class GTFORootCropFarmerMode extends DefaultCropFarmerMode {

    @Override
    public boolean canOperate(IBlockState state, MetaTileEntityFarmer farmer) {
        return state.getBlock() instanceof GTFORootCrop &&
                (((GTFORootCrop) state.getBlock()).isMaxAge(state) ||
                        hasNoSeeds(farmer, (GTFORootCrop) state.getBlock()) ? ((GTFORootCrop) state.getBlock()).seedHarvestable(state) : ((GTFORootCrop) state.getBlock()).cropHarvestable(state));
    }

    private boolean hasNoSeeds(MetaTileEntityFarmer farmer, GTFORootCrop crop) {
        ItemStack seed = crop.getSeedStack();
        return StreamUtils.streamFrom(farmer.getImportItems()).anyMatch(stack -> stack.isItemEqual(seed));
    }
}
