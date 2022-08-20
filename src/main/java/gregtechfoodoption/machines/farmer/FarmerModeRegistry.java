package gregtechfoodoption.machines.farmer;

import gregtechfoodoption.integration.agricraft.GTFOAgriCraftFarmerMode;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;

import java.util.ArrayList;
import java.util.List;

import static gregtechfoodoption.GTFOValues.MODID_AC;

public class FarmerModeRegistry {
    private static final List<FarmerMode> farmerModes = new ArrayList<>();
    public static boolean canUseAirOptimization = true;

    public static FarmerMode findSuitableFarmerMode(IBlockState state, MetaTileEntityFarmer farmer, MutableBlockPos pos, World world) {
        for (FarmerMode mode : farmerModes) {
            if (mode.canOperate(state, farmer, pos, world)) {
                return mode;
            }
        }
        return null;
    }

    public static FarmerMode findSuitableFarmerMode(ItemStack stack, BlockPos.MutableBlockPos operationPos, BlockPos.MutableBlockPos farmerPos, EnumFacing facing, World world) {
        for (FarmerMode mode : farmerModes) {
            if (mode.canPlaceItem(stack) && mode.canPlaceAt(operationPos, farmerPos, facing, world)) {
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
        registerFarmerMode(new CocoaFarmerMode());
        registerFarmerMode(new NetherWartFarmerMode());
        registerFarmerMode(new StemFarmerMode(Blocks.MELON_BLOCK, Items.MELON_SEEDS));
        registerFarmerMode(new StemFarmerMode(Blocks.PUMPKIN, Items.PUMPKIN_SEEDS));
        registerFarmerMode(new HeightCropFarmerMode(Blocks.REEDS, Items.REEDS));
        registerFarmerMode(new HeightCropFarmerMode(Blocks.CACTUS, Item.getItemFromBlock(Blocks.CACTUS)));
        registerFarmerMode(new GroundClearingFarmerMode(Blocks.DEADBUSH));
        registerFarmerMode(new GroundClearingFarmerMode(Blocks.TALLGRASS));
        registerFarmerMode(new GroundClearingFarmerMode(Blocks.RED_FLOWER));
        registerFarmerMode(new GroundClearingFarmerMode(Blocks.YELLOW_FLOWER));
        registerFarmerMode(new GroundClearingFarmerMode(Blocks.RED_MUSHROOM));
        registerFarmerMode(new GroundClearingFarmerMode(Blocks.BROWN_MUSHROOM));
        registerFarmerMode(new GroundClearingFarmerMode(Blocks.SNOW_LAYER));
        registerFarmerMode(new GroundClearingFarmerMode(Blocks.DOUBLE_PLANT));
        registerFarmerMode(new GTFOCropFarmerMode());
        registerFarmerMode(new GTFORootCropFarmerMode());
        if (Loader.isModLoaded(MODID_AC))
            registerFarmerMode(new GTFOAgriCraftFarmerMode());
    }
}
