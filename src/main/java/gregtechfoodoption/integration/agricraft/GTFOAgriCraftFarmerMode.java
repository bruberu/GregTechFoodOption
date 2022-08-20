package gregtechfoodoption.integration.agricraft;

import com.infinityraider.agricraft.api.v1.AgriApi;
import com.infinityraider.agricraft.api.v1.crop.IAgriCrop;
import com.infinityraider.agricraft.api.v1.util.MethodResult;
import com.infinityraider.agricraft.tiles.TileEntityCrop;
import com.infinityraider.infinitylib.utility.WorldHelper;
import gregtechfoodoption.machines.farmer.FarmerMode;
import gregtechfoodoption.machines.farmer.FarmerModeRegistry;
import gregtechfoodoption.machines.farmer.MetaTileEntityFarmer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class GTFOAgriCraftFarmerMode implements FarmerMode {
    public GTFOAgriCraftFarmerMode() {
        FarmerModeRegistry.canUseAirOptimization = false;
    }

    @Override
    public boolean canOperate(IBlockState state, MetaTileEntityFarmer farmer, BlockPos pos, World world) {
        return WorldHelper.getTile(world, pos, IAgriCrop.class).isPresent() && WorldHelper.getTile(world, pos, IAgriCrop.class).get().isMature();
    }

    @Override
    public boolean canPlaceAt(BlockPos.MutableBlockPos operationPos, BlockPos.MutableBlockPos farmerPos, EnumFacing facing, World world) {
        return WorldHelper.getTile(world, operationPos, IAgriCrop.class).isPresent() &&
                !WorldHelper.getTile(world, operationPos, IAgriCrop.class).get().hasSeed();
    }

    @Override
    public boolean canPlaceItem(ItemStack stack) {
        return AgriApi.getSeedRegistry().valueOf(stack).isPresent();
    }

    @Override
    public EnumActionResult place(ItemStack stack, World world, BlockPos.MutableBlockPos pos, MetaTileEntityFarmer farmer) {
        final MethodResult result = WorldHelper.getTile(world, pos, IAgriCrop.class).get()
                .onApplySeeds(AgriApi.getSeedRegistry().valueOf(stack).get(), farmer.fakePlayer);
        return convertMethodResult(result);
    }

    @Override
    public void harvest(IBlockState state, World world, BlockPos.MutableBlockPos pos, MetaTileEntityFarmer farmer) {
        final List<ItemStack> products = new ArrayList<>();
        WorldHelper.getTile(world, pos, IAgriCrop.class).get().onHarvest(products::add, farmer.fakePlayer);
    }

    @Override
    public List<ItemStack> getDrops(IBlockState state, World world, BlockPos.MutableBlockPos pos, MetaTileEntityFarmer farmer) {
        final List<ItemStack> products = new ArrayList<>();
        ((TileEntityCrop)(WorldHelper.getTile(world, pos, IAgriCrop.class).get()))
                .getDrops(products::add, false, false, true);
        return products;
    }

    // I am at a loss of words at this class.
    public static EnumActionResult convertMethodResult(MethodResult result) {
        switch (result) {
            case SUCCESS:
                return EnumActionResult.SUCCESS;
            case FAIL:
                return EnumActionResult.FAIL;
            case PASS:
                return EnumActionResult.PASS;
        }
        return EnumActionResult.FAIL;
    }
}
