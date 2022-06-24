package gregtechfoodoption.machines.farmer;

import gregtechfoodoption.block.GTFOCrop;
import net.minecraft.block.state.IBlockState;

public class GTFOCropFarmerMode extends DefaultCropFarmerMode {

    @Override
    public boolean canOperate(IBlockState state, MetaTileEntityFarmer farmer) {
        return state.getBlock() instanceof GTFOCrop && ((GTFOCrop) state.getBlock()).isMaxAge(state);
    }
}
