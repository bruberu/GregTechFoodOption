package gregtechfoodoption.recipe;

import gregtech.api.GTValues;
import gregtech.api.block.VariantBlock;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.block.GTFOBlockCasing;
import gregtechfoodoption.block.GTFOMetaBlocks;
import gregtechfoodoption.block.GTFOMetalCasing;
import gregtechfoodoption.item.GTFOMetaItem;
import gregtechfoodoption.machines.GTFOTileEntities;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.common.Loader;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//import static gregicality.science.api.unification.materials.GCYSMaterials.Adamantium;
//import static gregicality.science.api.unification.materials.GCYSMaterials.Orichalcum;
import static gregtech.api.GTValues.L;
import static gregtech.api.recipes.RecipeMaps.ASSEMBLER_RECIPES;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.blocks.BlockWireCoil.CoilType.CUPRONICKEL;
import static gregtech.loaders.recipe.CraftingComponent.*;
import static gregtech.loaders.recipe.MetaTileEntityLoader.registerMachineRecipe;

public class GTFOMachineRecipes {
    public static void init() {
        Component DENSE_PLATE = new Component(Stream.of(new Object[][]{

                {0, new UnificationEntry(OrePrefix.plate, Materials.WroughtIron)},
                {1, new UnificationEntry(OrePrefix.plate, Materials.Steel)},
                {2, new UnificationEntry(OrePrefix.plateDense, Materials.Aluminium)},
                {3, new UnificationEntry(OrePrefix.plateDense, Materials.StainlessSteel)},
                {4, new UnificationEntry(OrePrefix.plateDense, Materials.Titanium)},
                {5, new UnificationEntry(OrePrefix.plateDense, Materials.TungstenSteel)},
                {6, new UnificationEntry(OrePrefix.plateDense, Materials.RhodiumPlatedPalladium)},
                {7, new UnificationEntry(OrePrefix.plateDense, Materials.NaquadahAlloy)},
                {8, new UnificationEntry(OrePrefix.plateDense, Materials.Darmstadtium)},
                {9, new UnificationEntry(OrePrefix.plateDense, Materials.Neutronium)},

        }).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));

        if (Loader.isModLoaded(GTFOValues.MODID_GCYS)) {
            DENSE_PLATE.appendIngredients(Stream.of(new Object[][]{
//                    {9, new UnificationEntry(OrePrefix.plateDense, Orichalcum)},
//                    {10, new UnificationEntry(OrePrefix.plateDense, Adamantium)},
//                {11, new UnificationEntry(OrePrefix.plateDense, Trinium)},
//                {12, new UnificationEntry(OrePrefix.plateDense, Trinium)},
//                {13, new UnificationEntry(OrePrefix.plateDense, Trinium)},
            }).collect(Collectors.toMap(data -> (Integer) data[0], data -> data[1])));
        }

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
        registerMachineRecipe(GTFOTileEntities.SLICER, "PCA", "SHC", "LOA", 'P', PISTON, 'C', CIRCUIT, 'A', CABLE, 'S', SAWBLADE, 'H', HULL, 'L', DENSE_PLATE, 'O', CONVEYOR);
        registerMachineRecipe(GTFOTileEntities.CUISINE_ASSEMBLER, "AOC", "RHR", "AOC", 'C', CIRCUIT, 'A', CABLE, 'R', ROBOT_ARM, 'H', HULL, 'O', CONVEYOR);
        registerMachineRecipe(GTFOTileEntities.MICROWAVE, "LAC", "LHE", "LMC", 'H', HULL, 'M', MOTOR, 'E', EMITTER, 'C', CIRCUIT, 'A', CABLE, 'L', new UnificationEntry(OrePrefix.plate, Materials.Lead));
        registerMachineRecipe(GTFOTileEntities.MOB_AGE_SORTER, "OWS", "OHW", "OCW", 'O', CONVEYOR, 'W', CABLE, 'H', HULL, 'C', CIRCUIT, 'S', SENSOR);
        registerMachineRecipe(GTFOTileEntities.MOB_EXTERMINATOR, "EIE", "WHW", "CSC", 'E', EMITTER, 'W', CABLE, 'I', WIRE_QUAD, 'H', HULL, 'C', CIRCUIT, 'S', SENSOR);
        registerMachineRecipe(GTFOTileEntities.MOB_EXTRACTOR, "BCE", "PME", "WCW", 'M', HULL, 'E', PISTON, 'P', PUMP, 'C', CIRCUIT, 'W', CABLE, 'B', SAWBLADE);
        registerMachineRecipe(GTFOTileEntities.FARMER, "BEP", "WMW", "CWC", 'M', HULL, 'E', EMITTER, 'P', PISTON, 'C', CIRCUIT, 'W', CABLE_QUAD, 'B', SENSOR);


        ModHandler.addShapedRecipe("baking_oven", GTFOTileEntities.BAKING_OVEN.getStackForm(),
                "dSS", "RRA", "fSS",
                'S', new UnificationEntry(screw, Iron),
                'R', new UnificationEntry(stick, Iron),
                'A', GTFOMetaBlocks.GTFO_CASING.getItemVariant(GTFOBlockCasing.CasingType.ADOBE_BRICKS));
        ModHandler.addShapedRecipe("baking_oven2", GTFOTileEntities.BAKING_OVEN.getStackForm(),
                "dRR", "RRA", "fRR",
                'R', new UnificationEntry(stick, Iron),
                'A', GTFOMetaBlocks.GTFO_CASING.getItemVariant(GTFOBlockCasing.CasingType.ADOBE_BRICKS));

        ModHandler.addShapedRecipe("steam_baking_oven", GTFOTileEntities.STEAM_BAKING_OVEN.getStackForm(),
                "dSG", "PAR", "fSG",
                'S', new UnificationEntry(screw, Steel),
                'P', GTFOMetaBlocks.GTFO_CASING.getItemVariant(GTFOBlockCasing.CasingType.REINFORCED_ADOBE_BRICKS),
                'R', new UnificationEntry(pipeSmallFluid, Bronze),
                'G', new UnificationEntry(gear, Invar),
                'A', GTFOTileEntities.BAKING_OVEN.getStackForm());

        ModHandler.addShapedRecipe("electric_baking_oven", GTFOTileEntities.ELECTRIC_BAKING_OVEN.getStackForm(),
                "CPC", "IWI", "CAC",
                'C', GTFOMetaBlocks.GTFO_METAL_CASING.getItemVariant(GTFOMetalCasing.CasingType.BISMUTH_BRONZE_CASING),
                'P', MetaItems.ELECTRIC_PUMP_MV,
                'I', new UnificationEntry(circuit, MarkerMaterials.Tier.MV),
                'W', new UnificationEntry(wireGtQuadruple, Cupronickel),
                'A', MetaBlocks.WIRE_COIL.getItemVariant(CUPRONICKEL));

        Arrays.stream(GTFOMetalCasing.CasingType.values()).forEach(casing -> {
            registerMetalCasingRecipe(casing.getMaterial(), GTFOMetaBlocks.GTFO_METAL_CASING, casing);
        });

        ASSEMBLER_RECIPES.recipeBuilder().EUt(120).duration(400)
                .inputs(MetaTileEntities.HULL[GTValues.MV].getStackForm(), MetaItems.ELECTRIC_PUMP_MV.getStackForm(2))
                .input(circuit, MarkerMaterials.Tier.HV, 4)
                .inputs(MetaBlocks.FRAMES.get(Steel).getItem(Steel))
                .input(OrePrefix.plate, SterlingSilver, 6)
                .circuitMeta(3)
                .outputs(GTFOTileEntities.GREENHOUSE.getStackForm())
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(200)
                .input(plate, Iron)
                .input(pipeTinyFluid, Steel)
                .inputs(MetaItems.FLUID_FILTER.getStackForm())
                .fluidInputs(Tin.getFluid(L))
                .outputs(GTFOMetaItem.SPRINKLER_COVER.getStackForm())
                .buildAndRegister();
        ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(200)
                .input(plate, Iron)
                .input(pipeTinyFluid, Steel)
                .inputs(MetaItems.FLUID_FILTER.getStackForm())
                .fluidInputs(SolderingAlloy.getFluid(L / 2))
                .outputs(GTFOMetaItem.SPRINKLER_COVER.getStackForm())
                .buildAndRegister();

    }

    private static <T extends Enum<T> & IStringSerializable> void registerMetalCasingRecipe(Material inputMaterial, VariantBlock<T> outputCasingType, T outputCasing) {

        ModHandler.addShapedRecipe(String.format("metal_casing_%s", inputMaterial), outputCasingType.getItemVariant(outputCasing, 3),
                "PhP", "PFP", "PwP",
                'P', new UnificationEntry(plate, inputMaterial),
                'F', new UnificationEntry(frameGt, inputMaterial));

        ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16)
                .input(plate, inputMaterial, 6)
                .input(frameGt, inputMaterial)
                .circuitMeta(6)
                .outputs(outputCasingType.getItemVariant(outputCasing, 3))
                .buildAndRegister();
    }
}
