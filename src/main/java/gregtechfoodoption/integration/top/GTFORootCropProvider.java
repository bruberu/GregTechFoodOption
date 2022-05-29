package gregtechfoodoption.integration.top;

import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.block.GTFORootCrop;
import mcjty.theoneprobe.api.*;
import net.minecraft.block.state.IBlockState;
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
                probeInfo.text(TextStyleClass.OK + "Crop may be harvested");
            } else {
                probeInfo.text(TextStyleClass.ERROR + "Crop cannot be harvested");
            }

            if (crop.seedHarvestable(blockState)) {
                probeInfo.text(TextStyleClass.OK + "Seed may be harvested");
            } else {
                probeInfo.text(TextStyleClass.ERROR + "Seed cannot be harvested");
            }
        }
    }

}
