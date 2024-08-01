package gregtechfoodoption.integration.enderio;

import com.enderio.core.common.util.NNList;
import crazypants.enderio.api.farm.FarmNotification;
import crazypants.enderio.api.farm.FarmingAction;
import crazypants.enderio.api.farm.IFarmer;
import crazypants.enderio.api.farm.IHarvestResult;
import crazypants.enderio.base.farming.FarmingTool;
import crazypants.enderio.base.farming.farmers.CustomSeedFarmer;
import crazypants.enderio.base.farming.farmers.HarvestResult;
import crazypants.enderio.util.Prep;
import gregtechfoodoption.block.GTFOBerryBush;
import gregtechfoodoption.block.GTFOCrop;
import gregtechfoodoption.block.GTFORootCrop;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class GTFOBerryFarmer extends CustomSeedFarmer {
    public GTFOBerryFarmer(@NotNull Block plantedBlock, @NotNull ItemStack seeds) {
        super(plantedBlock, seeds);
    }

    @Nullable
    @Override
    public IHarvestResult harvestBlock(@Nonnull IFarmer farm, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        if (!canHarvest(farm, pos, state) || !farm.checkAction(FarmingAction.HARVEST, getHarvestTool())) {
            return null;
        }
        if (!farm.hasTool(getHarvestTool())) {
            farm.setNotification(getNoHarvestToolNotification());
            return null;
        }

        final World world = farm.getWorld();
        final EntityPlayerMP joe = farm.startUsingItem(getHarvestTool());
        final int fortune = farm.getLootingValue(getHarvestTool());
        final IHarvestResult res = new HarvestResult(pos);
        GTFOCrop crop = (GTFOCrop) state.getBlock();

        NNList<ItemStack> drops = new NNList<>();
        state.getBlock().getDrops(drops, world, pos, state, fortune);
        float chance = ForgeEventFactory.fireBlockHarvesting(drops, joe.world, pos, state, fortune, 1.0F, false, joe);
        farm.registerAction(FarmingAction.HARVEST, getHarvestTool(), state, pos);
        for (ItemStack stack : drops) {
            if (world.rand.nextFloat() <= chance) {
                res.addDrop(pos, stack.copy());
            }
        }

        NNList.wrap(farm.endUsingItem(getHarvestTool())).apply(drop -> {
            res.addDrop(pos, drop.copy());
        });

        world.setBlockState(pos, state.withProperty(crop.getAgeProperty(), Integer.valueOf(crop.getMaxAge() - 1)), 3);
        return res;
    }

    @Override
    public boolean canPlant(@NotNull ItemStack stack) {
        return false;
    }

    @Override
    public boolean canHarvest(@Nonnull IFarmer farm, @Nonnull BlockPos bc, @Nonnull IBlockState state) {
        return state.getBlock() instanceof GTFOCrop crop && crop == getPlantedBlock() && crop.isMaxAge(state);
    }
}
