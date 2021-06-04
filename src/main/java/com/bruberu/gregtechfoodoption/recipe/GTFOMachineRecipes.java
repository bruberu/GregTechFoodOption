package com.bruberu.gregtechfoodoption.recipe;

import com.bruberu.gregtechfoodoption.block.GTFOBlockCasing;
import com.bruberu.gregtechfoodoption.block.GTFOMetaBlocks;
import gregicadditions.item.GAMetaBlocks;
import gregtech.api.items.OreDictNames;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;

import static com.bruberu.gregtechfoodoption.machines.GTFOTileEntities.*;
import static gregicadditions.GAEnums.GAOrePrefix.gtMetalCasing;
import static gregicadditions.recipes.helper.GACraftingComponents.*;
import static gregicadditions.recipes.helper.HelperMethods.*;
import static gregtech.api.unification.material.MarkerMaterials.Tier.Good;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.blocks.BlockWireCoil.CoilType.CUPRONICKEL;

public class GTFOMachineRecipes {
    public static void init()
    {
        registerMachineRecipe(0, BIOREACTOR,
                "RUR", "SHG", "CIP",
                'R', CIRCUIT,
                'C', CABLE_SINGLE,
                'U', PUMP,
                'P', PLATE,
                'S', SENSOR,
                'H', HULL,
                'G', GLASS,
                'I', new UnificationEntry(plate, Polytetrafluoroethylene)
        );
        registerMachineRecipe(SLICER,
                "PCA", "SHC", "LOA",
                'P', PISTON,
                'C', CIRCUIT,
                'A', CABLE_SINGLE,
                'S', OreDictNames.craftingDiamondBlade,
                'H', HULL,
                'L', PLATE_DENSE,
                'O', CONVEYOR
        );

        ModHandler.addShapedRecipe("baking_oven", BAKING_OVEN.getStackForm(),
                "dSS", "RRA", "fSS",
                'S', OreDictUnifier.get(screw, WroughtIron),
                'R', OreDictUnifier.get(stick, WroughtIron),
                'A', GTFOMetaBlocks.GTFO_CASING.getItemVariant(GTFOBlockCasing.CasingType.ADOBE_BRICKS));

        ModHandler.addShapedRecipe("electric_baking_oven", ELECTRIC_BAKING_OVEN.getStackForm(),
                "CPC", "IWI", "CAC",
                'C', new UnificationEntry(gtMetalCasing, BismuthBronze),
                'P', MetaItems.ELECTRIC_PUMP_MV,
                'I', new UnificationEntry(circuit, Good),
                'W', new UnificationEntry(wireGtQuadruple, Cupronickel),
                'A', MetaBlocks.WIRE_COIL.getItemVariant(CUPRONICKEL));
    }
}
