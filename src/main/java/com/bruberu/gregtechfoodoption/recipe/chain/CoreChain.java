package com.bruberu.gregtechfoodoption.recipe.chain;

//Used for cross-chain materials.

import gregicadditions.GAMaterials;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;
import net.minecraft.item.ItemStack;

import static com.bruberu.gregtechfoodoption.GTFOMaterialHandler.*;
import static com.bruberu.gregtechfoodoption.item.GTFOMetaItem.SLICER_BLADE_FLAT;
import static com.bruberu.gregtechfoodoption.item.GTFOMetaItem.SLICER_BLADE_STRIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.plate;
import static gregtech.api.unification.ore.OrePrefix.screw;

public class CoreChain {
    public static void init()
    {
        FLUID_HEATER_RECIPES.recipeBuilder()
                .fluidInputs(UnheatedCaneSyrup.getFluid(1000))
                .circuitMeta(0)
                .fluidOutputs(CaneSyrup.getFluid(1000))
                .EUt(120)
                .duration(80)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .inputs(GAMaterials.Glucose.getItemStack(24))
                .fluidInputs(Water.getFluid(2000))
                .fluidOutputs(UnheatedCaneSyrup.getFluid(2000))
                .EUt(80)
                .duration(260)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(200)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(Propene.getFluid(1000))
                .fluidOutputs(IsopropylChloride.getFluid(1000))
                .buildAndRegister();

        ModHandler.addShapedRecipe("slicer_flat", SLICER_BLADE_FLAT.getStackForm(),
                "hPS", " M ", "fPs",
                'P', new UnificationEntry(plate, StainlessSteel),
                'S', new UnificationEntry(screw, StainlessSteel),
                'M', MetaItems.SHAPE_EXTRUDER_BLOCK);

        ModHandler.addShapedRecipe("slicer_stripes", SLICER_BLADE_STRIPES.getStackForm(),
                "hPS", "PMP", "fPs",
                'P', new UnificationEntry(plate, StainlessSteel),
                'S', new UnificationEntry(screw, StainlessSteel),
                'M', MetaItems.SHAPE_EXTRUDER_BLOCK);
    }
}
