package com.bruberu.gregtechfoodoption.integration.jei.multi;

import com.bruberu.gregtechfoodoption.block.GTFOBlockCasing;
import com.bruberu.gregtechfoodoption.block.GTFOMetaBlocks;
import com.bruberu.gregtechfoodoption.machines.GTFOTileEntities;
import com.google.common.collect.Lists;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GATransparentCasing;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;

import java.util.List;

import static gregtech.api.unification.material.Materials.*;

public class ElectricBakingOvenInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GTFOTileEntities.BAKING_OVEN;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        MultiblockShapeInfo shapeInfo = MultiblockShapeInfo.builder()
                .aisle("XXXX", "XXXX", "XXXX", "#XX#")
                .aisle("XXXX", "XFFX", "X##X", "#XX#")
                .aisle("XXXX", "XFFX", "X##X", "#XX#")
                .aisle("hXie", "YGGX", "XGGX", "#XX#")
                .where('X', GAMetaBlocks.getMetalCasingBlockState(BismuthBronze))
                .where('F', MetaBlocks.FRAMES.get(Steel).getDefaultState())
                .where('G', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.REINFORCED_GLASS))
                .where('Y', GTFOTileEntities.ELECTRIC_BAKING_OVEN, EnumFacing.SOUTH)
                .where('i', MetaTileEntities.ITEM_IMPORT_BUS[2], EnumFacing.SOUTH)
                .where('e', MetaTileEntities.ITEM_EXPORT_BUS[2], EnumFacing.SOUTH)
                .where('h', MetaTileEntities.ENERGY_INPUT_HATCH[2], EnumFacing.WEST)
                .build();
        return Lists.newArrayList(shapeInfo);
    }

    @Override
    public String[] getDescription() {
        return new String[]{
                "Runs at a constant EU/t based on its temperature."
        };
    }
}
