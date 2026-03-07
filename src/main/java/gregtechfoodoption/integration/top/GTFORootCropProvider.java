package gregtechfoodoption.integration.top;

import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.block.GTFORootCrop;
import mcjty.theoneprobe.api.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class GTFORootCropProvider implements IProbeInfoProvider {
    @Override
    public String getID() {
        return String.format("%s:root_crop_provider", GTFOValues.MODID);
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, @Nonnull IBlockState blockState, IProbeHitData data) {
        if (blockState.getBlock() instanceof GTFORootCrop) {
            GTFORootCrop crop = (GTFORootCrop) blockState.getBlock();

            if (crop.cropHarvestable(blockState)) {
                probeInfo.text(TextStyleClass.OK +
                        I18n.format("gregtechfoodoption.top.crop_harvestable"));
            } else {
                probeInfo.text(TextStyleClass.ERROR +
                        I18n.format("gregtechfoodoption.top.crop_unharvestable"));
            }

            if (crop.seedHarvestable(blockState)) {
                probeInfo.text(TextStyleClass.OK +
                        I18n.format("gregtechfoodoption.top.seed_harvestable"));
            } else {
                probeInfo.text(TextStyleClass.ERROR +
                        I18n.format("gregtechfoodoption.top.seed_unharvestable"));
            }
        }
    }

}
