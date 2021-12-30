package com.bruberu.gregtechfoodoption.integration.jei.multi;

import com.bruberu.gregtechfoodoption.block.GTFOOtherCasing;
import com.bruberu.gregtechfoodoption.machines.GTFOTileEntities;
import com.bruberu.gregtechfoodoption.machines.multiblock.MetaTileEntityCulinaryGenerator;
import com.google.common.collect.Lists;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.item.components.EmitterCasing;
import gregicadditions.item.components.SensorCasing;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumFacing;
import net.minecraft.init.Blocks;

import java.util.ArrayList;
import java.util.List;

import static com.bruberu.gregtechfoodoption.block.GTFOMetaBlocks.GTFO_OTHER_CASING;
import static gregtech.api.unification.material.Materials.StainlessSteel;

public class CulinaryGeneratorInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GTFOTileEntities.CULINARY_GENERATOR;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder()
                .aisle("  F  ", "  F  ", "  F  ", "  C  ", "     ", "     ")
                .aisle("     ", " PPP ", "PRRRP", "PRRRP", "PRRRP", " PPP ")
                .aisle("     ", " XXX ", "X   0", "X   2", "X   3", " XXX ")
                .aisle("     ", " XXX ", "X   1", "X   3", "X   3", " XXX ")
                .aisle("     ", " XXX ", "XX XX", "#e sX", "mX XX", " XXX ")
                .aisle("     ", " XXX ", "X   X", "X   X", "X   X", " XXX ")
                .aisle("     ", " XXX ", "X   X", "X   4", "X   X", " XXX ")
                .aisle("     ", " PPP ", "PRRRP", "PRRRP", "PRRRP", " PPP ")
                .aisle("  F  ", "  F  ", "  F  ", "  C  ", "     ", "     ")
                .where('#', this.getController(), EnumFacing.WEST)
                .where('X', GTFO_OTHER_CASING.getState(GTFOOtherCasing.CasingType.BIOCHEMICAL))
                .where(' ', Blocks.AIR.getDefaultState())
                .where('R', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.BOROSILICATE_GLASS))
                .where('C', MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.STAINLESS_CLEAN))
                .where('F', MetaTileEntityCulinaryGenerator.getFrameState())
                .where('P', GAMetaBlocks.MUTLIBLOCK_CASING.getState(GAMultiblockCasing.CasingType.PTFE_PIPE))
                .where('s', GAMetaBlocks.SENSOR_CASING.getState(SensorCasing.CasingType.SENSOR_IV))
                .where('e', GAMetaBlocks.EMITTER_CASING.getState(EmitterCasing.CasingType.EMITTER_IV))
                .where('m', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.WEST)
                .where('0', MetaTileEntities.ITEM_IMPORT_BUS[2], EnumFacing.EAST)
                .where('1', MetaTileEntities.ITEM_EXPORT_BUS[2], EnumFacing.EAST)
                .where('2', MetaTileEntities.FLUID_IMPORT_HATCH[0], EnumFacing.EAST)
                .where('3', MetaTileEntities.FLUID_EXPORT_HATCH[0], EnumFacing.EAST)
                .where('4', MetaTileEntities.ENERGY_OUTPUT_HATCH[5], EnumFacing.EAST)
                .build();
        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[] {I18n.format("The framework casing and component blocks define the maximum voltage the multiblock is able to overclock to")};
    }
}
