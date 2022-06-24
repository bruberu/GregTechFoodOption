package gregtechfoodoption.machines.farmer;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import java.util.ArrayList;
import java.util.List;

public class FarmerModeRegistry {
    private static final List<FarmerMode> farmerModes = new ArrayList<>();

    public static FarmerMode findSuitableFarmerMode(IBlockState state, MetaTileEntityFarmer farmer) {
        for (FarmerMode mode : farmerModes) {
            if (mode.canOperate(state, farmer)) {
                return mode;
            }
        }
        return null;
    }

    public static void registerFarmerMode(FarmerMode mode) {
        farmerModes.add(mode);
    }

    // For initializing
    public static FarmerMode getAnyMode() {
        return farmerModes.isEmpty() ? null : farmerModes.get(0);
    }

    public static void registerDefaultModes() {
        registerFarmerMode(new CustomCropFarmerMode(Blocks.WHEAT));
        registerFarmerMode(new CustomCropFarmerMode(Blocks.POTATOES));
        registerFarmerMode(new CustomCropFarmerMode(Blocks.CARROTS));
        registerFarmerMode(new GTFOCropFarmerMode());
        registerFarmerMode(new GTFORootCropFarmerMode());
    }
}
