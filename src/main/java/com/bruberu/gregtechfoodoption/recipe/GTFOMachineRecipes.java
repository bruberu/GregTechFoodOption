package com.bruberu.gregtechfoodoption.recipe;

import gregtech.api.items.OreDictNames;
import gregtech.api.unification.stack.UnificationEntry;

import static com.bruberu.gregtechfoodoption.machines.GTFOTileEntities.BIOREACTOR;
import static com.bruberu.gregtechfoodoption.machines.GTFOTileEntities.SLICER;
import static gregicadditions.recipes.helper.GACraftingComponents.*;
import static gregicadditions.recipes.helper.HelperMethods.*;
import static gregtech.api.unification.material.Materials.Polytetrafluoroethylene;
import static gregtech.api.unification.ore.OrePrefix.plate;

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
    }
}
