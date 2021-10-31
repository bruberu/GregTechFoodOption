package com.bruberu.gregtechfoodoption.recipe;

import com.bruberu.gregtechfoodoption.block.GTFOBlockCasing;
import com.bruberu.gregtechfoodoption.block.GTFOMetaBlocks;
import com.bruberu.gregtechfoodoption.block.GTFOMetalCasing;
import gregtech.api.items.OreDictNames;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.blocks.VariantBlock;
import gregtech.common.items.MetaItems;
import net.minecraft.util.IStringSerializable;

import java.util.Arrays;

import static com.bruberu.gregtechfoodoption.machines.GTFOTileEntities.*;
import static gregicadditions.recipes.helper.GACraftingComponents.*;
import static gregicadditions.recipes.helper.HelperMethods.*;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
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
        registerMachineRecipe(CUISINE_ASSEMBLER,
                "AOC", "RHR", "AOC",
                'C', CIRCUIT,
                'A', CABLE_SINGLE,
                'R', ROBOT_ARM,
                'H', HULL,
                'O', CONVEYOR
        );

        ModHandler.addShapedRecipe("baking_oven", BAKING_OVEN.getStackForm(),
                "dSS", "RRA", "fSS",
                'S', OreDictUnifier.get(screw, WroughtIron),
                'R', OreDictUnifier.get(stick, WroughtIron),
                'A', GTFOMetaBlocks.GTFO_CASING.getItemVariant(GTFOBlockCasing.CasingType.ADOBE_BRICKS));

        ModHandler.addShapedRecipe("electric_baking_oven", ELECTRIC_BAKING_OVEN.getStackForm(),
                "CPC", "IWI", "CAC",
                'C', GTFOMetaBlocks.GTFO_METAL_CASING.getItemVariant(GTFOMetalCasing.CasingType.BISMUTH_BRONZE_CASING),
                'P', MetaItems.ELECTRIC_PUMP_MV,
                'I', new UnificationEntry(circuit, Good),
                'W', new UnificationEntry(wireGtQuadruple, Cupronickel),
                'A', MetaBlocks.WIRE_COIL.getItemVariant(CUPRONICKEL));

        Arrays.stream(GTFOMetalCasing.CasingType.values()).forEach(casing -> {
            registerMetalCasingRecipe(casing.getMaterial(), GTFOMetaBlocks.GTFO_METAL_CASING, casing);
        });
    }

    private static <T extends Enum<T> & IStringSerializable> void registerMetalCasingRecipe(Material inputMaterial, VariantBlock<T> outputCasingType, T outputCasing) {

        ModHandler.addShapedRecipe(String.format("metal_casing_%s", inputMaterial), outputCasingType.getItemVariant(outputCasing, 3),
                "PhP", "PFP", "PwP",
                'P', OreDictUnifier.get(plate, inputMaterial),
                'F', OreDictUnifier.get(frameGt, inputMaterial));

        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16)
                .input(plate, inputMaterial, 6)
                .input(frameGt, inputMaterial)
                .circuitMeta(0)
                .outputs(outputCasingType.getItemVariant(outputCasing, 3))
                .buildAndRegister();
    }
}
