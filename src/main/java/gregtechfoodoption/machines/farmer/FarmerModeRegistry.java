package gregtechfoodoption.machines.farmer;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class FarmerModeRegistry {
    private static final List<FarmerMode> farmerModes = new ArrayList<>();

    public static FarmerMode findSuitableFarmerMode(IBlockState state, MetaTileEntityFarmer farmer, MutableBlockPos pos, World world) {
        for (FarmerMode mode : farmerModes) {
            if (mode.canOperate(state, farmer, pos, world)) {
                return mode;
            }
        }
        return null;
    }

    public static FarmerMode findSuitableFarmerMode(ItemStack stack) {
        for (FarmerMode mode : farmerModes) {
            if (mode.canPlaceItem(stack)) {
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
        registerFarmerMode(new BlockCropsFarmerMode(Blocks.WHEAT, Items.WHEAT_SEEDS));
        registerFarmerMode(new BlockCropsFarmerMode(Blocks.POTATOES, Items.POTATO));
        registerFarmerMode(new BlockCropsFarmerMode(Blocks.CARROTS, Items.CARROT));
        registerFarmerMode(new BlockCropsFarmerMode(Blocks.BEETROOTS, Items.BEETROOT_SEEDS));
        registerFarmerMode(new NetherWartFarmerMode());
        registerFarmerMode(new StemFarmerMode(Blocks.MELON_BLOCK, Items.MELON_SEEDS));
        registerFarmerMode(new StemFarmerMode(Blocks.PUMPKIN, Items.PUMPKIN_SEEDS));
        registerFarmerMode(new HeightCropFarmerMode(Blocks.REEDS, Items.REEDS));
        registerFarmerMode(new HeightCropFarmerMode(Blocks.CACTUS, Item.getItemFromBlock(Blocks.CACTUS)));
        registerFarmerMode(new GTFOCropFarmerMode());
        registerFarmerMode(new GTFORootCropFarmerMode());
    }
}
