package com.bruberu.gregtechfoodoption.integration.jei.multi;

import com.bruberu.gregtechfoodoption.block.GTFOOtherCasing;
import com.bruberu.gregtechfoodoption.machines.GTFOTileEntities;
import com.google.common.collect.Lists;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.item.components.EmitterCasing;
import gregicadditions.item.components.SensorCasing;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;

import static com.bruberu.gregtechfoodoption.block.GTFOMetaBlocks.GTFO_OTHER_CASING;

public class CulinaryGeneratorInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GTFOTileEntities.CULINARY_GENERATOR;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder()
                .aisle("0XX", "2XX", "2XX", "XXX", "XXX")
                .aisle("YX5", "RX4", "RXX", "RXX", "mXX")
                .aisle("1XX", "3XX", "3XX", "XXX", "XXX")
                .where('Y', this.getController(), EnumFacing.WEST)
                .where('X', GTFO_OTHER_CASING.getState(GTFOOtherCasing.CasingType.BIOCHEMICAL))
                //.where('~', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.TIERED_HULL_IV))
                .where('R', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.REINFORCED_GLASS))
                //.where('s', GAMetaBlocks.SENSOR_CASING.getState(SensorCasing.CasingType.SENSOR_IV))
                //.where('e', GAMetaBlocks.EMITTER_CASING.getState(EmitterCasing.CasingType.EMITTER_IV))
                .where('m', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.WEST)
                .where('0', MetaTileEntities.ITEM_IMPORT_BUS[3], EnumFacing.WEST)
                .where('1', MetaTileEntities.ITEM_EXPORT_BUS[3], EnumFacing.WEST)
                .where('2', MetaTileEntities.FLUID_IMPORT_HATCH[3], EnumFacing.WEST)
                .where('3', MetaTileEntities.FLUID_EXPORT_HATCH[3], EnumFacing.WEST)
                .where('4', MetaTileEntities.ENERGY_INPUT_HATCH[5], EnumFacing.EAST)
                .where('5', MetaTileEntities.ENERGY_OUTPUT_HATCH[5], EnumFacing.EAST)
                .build();
        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[] {I18n.format("The framework casing and component blocks define the maximum voltage the multiblock is able to overclock to")};
    }
}
