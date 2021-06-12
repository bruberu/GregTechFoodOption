package com.bruberu.gregtechfoodoption.recipe.chain;

//Used for cross-chain materials.

import com.bruberu.gregtechfoodoption.block.GTFOBlockCasing;
import com.bruberu.gregtechfoodoption.block.GTFOMetaBlocks;
import com.bruberu.gregtechfoodoption.recipe.builder.BakingOvenRecipeBuilder;
import gregicadditions.GAMaterials;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;

import static com.bruberu.gregtechfoodoption.GTFOMaterialHandler.*;
import static com.bruberu.gregtechfoodoption.block.GTFOBlockCasing.CasingType.ADOBE_BRICKS;
import static com.bruberu.gregtechfoodoption.item.GTFOMetaItem.*;
import static com.bruberu.gregtechfoodoption.recipe.GTFORecipeMaps.BAKING_OVEN_RECIPES;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

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

        FLUID_HEATER_RECIPES.recipeBuilder()
                .fluidInputs(SeedOil.getFluid(16))
                .fluidOutputs(FryingOil.getFluid(16))
                .circuitMeta(0)
                .EUt(12)
                .duration(10)
                .buildAndRegister();

        FLUID_HEATER_RECIPES.recipeBuilder()
                .fluidInputs(SeedOil.getFluid(16))
                .fluidOutputs(HotFryingOil.getFluid(16))
                .circuitMeta(1)
                .EUt(60)
                .duration(25)
                .buildAndRegister();

        FLUID_HEATER_RECIPES.recipeBuilder()
                .fluidInputs(FryingOil.getFluid(16))
                .fluidOutputs(HotFryingOil.getFluid(16))
                .circuitMeta(0)
                .EUt(18)
                .duration(15)
                .buildAndRegister();

        // Adobe Bricks for the Baking Oven
        ModHandler.addShapedRecipe("mud_bricks1", MUD_BRICK.getStackForm(5),
                "SCS", "SMS", "GCG",
                'C', Items.CLAY_BALL,
                'S', Blocks.SAND,
                'G', Blocks.GRAVEL,
                'M', MetaItems.WOODEN_FORM_BRICK);

        ModHandler.addShapedRecipe("mud_bricks2", MUD_BRICK.getStackForm(10),
                "SBS", "SMS", "GKG",
                'S', Blocks.SAND,
                'G', Blocks.GRAVEL,
                'K', OreDictUnifier.get(dust, Kaolinite),
                'B', OreDictUnifier.get(dust, Bentonite),
                'M', MetaItems.WOODEN_FORM_BRICK);

        ModHandler.addShapedRecipe("mud_bricks3", MUD_BRICK.getStackForm(8),
                "SCS", "SMW", "GCG",
                'C', Items.CLAY_BALL,
                'S', Blocks.SAND,
                'G', Blocks.GRAVEL,
                'W', Items.WHEAT,
                'M', MetaItems.WOODEN_FORM_BRICK);

        ModHandler.addShapedRecipe("mud_bricks4", MUD_BRICK.getStackForm(16),
                "SBS", "SMW", "GKG",
                'S', Blocks.SAND,
                'G', Blocks.GRAVEL,
                'B', OreDictUnifier.get(dust, Bentonite),
                'K', OreDictUnifier.get(dust, Kaolinite),
                'W', Items.WHEAT,
                'M', MetaItems.WOODEN_FORM_BRICK);

        ModHandler.addSmeltingRecipe(MUD_BRICK.getStackForm(), ADOBE_BRICK.getStackForm());
        ModHandler.addShapedRecipe("casing_adobe_bricks", GTFOMetaBlocks.GTFO_CASING.getItemVariant(ADOBE_BRICKS, 1), "XX", "XX", 'X', ADOBE_BRICK);
    }
}
