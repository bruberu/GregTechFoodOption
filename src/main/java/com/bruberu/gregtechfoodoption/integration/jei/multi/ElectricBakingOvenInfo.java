package com.bruberu.gregtechfoodoption.integration.jei.multi;

import com.bruberu.gregtechfoodoption.block.GTFOMetalCasing;
import com.bruberu.gregtechfoodoption.machines.GTFOTileEntities;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.jei.GAMultiblockShapeInfo;
import gregicadditions.machines.GATileEntities;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtech.integration.jei.multiblock.MultiblockInfoPage;
import gregtech.integration.jei.multiblock.MultiblockShapeInfo;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.List;

import static com.bruberu.gregtechfoodoption.block.GTFOMetaBlocks.GTFO_METAL_CASING;
import static gregtech.api.unification.material.Materials.*;

public class ElectricBakingOvenInfo extends MultiblockInfoPage {
    @Override
    public MultiblockControllerBase getController() {
        return GTFOTileEntities.ELECTRIC_BAKING_OVEN;
    }

    @Override
    public List<MultiblockShapeInfo> getMatchingShapes() {
        List<MultiblockShapeInfo> shapes = new ArrayList<>();
        for (int i = 1; i < 14; i++) {
            GAMultiblockShapeInfo.Builder builder = GAMultiblockShapeInfo.builder();
            builder.aisle("XXXX", "YmXX", "hXXX", "####");
            for (int j = 0; j < i; j++)
                builder.aisle("XXXX", "GFFX", "G##X", "XXXX");
            builder.aisle("XXXX", "GFFX", "G##X", "XXXX")
                    .aisle("XXXX", "iXXX", "eXXX", "####")
                    .where('X', GTFO_METAL_CASING.getState(GTFOMetalCasing.CasingType.BISMUTH_BRONZE_CASING))
                    .where('F', MetaBlocks.FRAMES.get(Steel).getDefaultState())
                    .where('G', GAMetaBlocks.TRANSPARENT_CASING.getState(GATransparentCasing.CasingType.REINFORCED_GLASS))
                    .where('Y', GTFOTileEntities.ELECTRIC_BAKING_OVEN, EnumFacing.WEST)
                    .where('m', GATileEntities.MAINTENANCE_HATCH[0], EnumFacing.NORTH)
                    .where('i', MetaTileEntities.ITEM_IMPORT_BUS[2], EnumFacing.WEST)
                    .where('e', MetaTileEntities.ITEM_EXPORT_BUS[2], EnumFacing.WEST)
                    .where('h', MetaTileEntities.ENERGY_INPUT_HATCH[2], EnumFacing.NORTH);
            shapes.add(builder.build());
        }
        return shapes;
    }

    @Override
    public String[] getDescription() {
        return new String[]{
                "Runs at a constant EU/t based on its temperature."
        };
    }
}
