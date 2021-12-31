package gregtechfoodoption.recipe;

import gregtechfoodoption.block.GTFOMetalCasing;
import gregtechfoodoption.machines.GTFOTileEntities;
import gregtechfoodoption.block.GTFOBlockCasing;
import gregtechfoodoption.block.GTFOMetaBlocks;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.blocks.VariantBlock;
import gregtech.common.items.MetaItems;
import net.minecraft.util.IStringSerializable;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.unification.material.MarkerMaterials.Tier.Good;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.blocks.BlockWireCoil.CoilType.CUPRONICKEL;
import static gregtech.loaders.recipe.CraftingComponent.*;
import static gregtech.loaders.recipe.MetaTileEntityLoader.registerMachineRecipe;

public class GTFOMachineRecipes {
    public static void init()
    {
        Component DENSE_PLATE = new Component(Stream.of(new Object[][]{

                {0, new UnificationEntry(OrePrefix.plate, Materials.WroughtIron)},
                {1, new UnificationEntry(OrePrefix.plate, Materials.Steel)},
                {2, new UnificationEntry(OrePrefix.plate, Materials.Aluminium)},
                {3, new UnificationEntry(OrePrefix.plate, Materials.StainlessSteel)},
                {4, new UnificationEntry(OrePrefix.plate, Materials.Titanium)},
                {5, new UnificationEntry(OrePrefix.plate, Materials.TungstenSteel)},
                {6, new UnificationEntry(OrePrefix.plate, Materials.RhodiumPlatedPalladium)},
                {7, new UnificationEntry(OrePrefix.plate, Materials.NaquadahAlloy)},
                {8, new UnificationEntry(OrePrefix.plate, Materials.Darmstadtium)},
                {9, new UnificationEntry(OrePrefix.plate, Materials.Neutronium)},

        }).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));

/*
        registerMachineRecipe(GTFOTileEntities.BIOREACTOR,
                "RUR", "SHG", "CIP",
                'R', CIRCUIT,
                'C', CABLE,
                'U', PUMP,
                'P', PLATE,
                'S', SENSOR,
                'H', HULL,
                'G', GLASS,
                'I', new UnificationEntry(plate, Polytetrafluoroethylene)
        );
*/
        registerMachineRecipe(GTFOTileEntities.SLICER,
                "PCA", "SHC", "LOA",
                'P', PISTON,
                'C', CIRCUIT,
                'A', CABLE,
                'S', SAWBLADE,
                'H', HULL,
                'L', DENSE_PLATE,
                'O', CONVEYOR
        );
        registerMachineRecipe(GTFOTileEntities.CUISINE_ASSEMBLER,
                "AOC", "RHR", "AOC",
                'C', CIRCUIT,
                'A', CABLE,
                'R', ROBOT_ARM,
                'H', HULL,
                'O', CONVEYOR
        );

        ModHandler.addShapedRecipe("baking_oven", GTFOTileEntities.BAKING_OVEN.getStackForm(),
                "dSS", "RRA", "fSS",
                'S', OreDictUnifier.get(screw, WroughtIron),
                'R', OreDictUnifier.get(stick, WroughtIron),
                'A', GTFOMetaBlocks.GTFO_CASING.getItemVariant(GTFOBlockCasing.CasingType.ADOBE_BRICKS));

        ModHandler.addShapedRecipe("electric_baking_oven", GTFOTileEntities.ELECTRIC_BAKING_OVEN.getStackForm(),
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
