package gregtechfoodoption.recipe.chain;

//Used for cross-chain materials.

import gregtechfoodoption.GTFOMaterialHandler;
import gregtechfoodoption.utils.RecipeUtils;
import gregtechfoodoption.block.GTFOMetaBlocks;
import gregtechfoodoption.recipe.builder.BakingOvenRecipeBuilder;
import gregicadditions.GAMaterials;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

import static gregtechfoodoption.block.GTFOBlockCasing.CasingType.ADOBE_BRICKS;
import static gregtechfoodoption.item.GTFOMetaItem.*;
import static gregtechfoodoption.recipe.GTFORecipeMaps.SLICER_RECIPES;
import static gregicadditions.GAMaterials.PiranhaSolution;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;

public class CoreChain {
    public static void init()
    {
        FLUID_HEATER_RECIPES.recipeBuilder()
                .fluidInputs(GTFOMaterialHandler.UnheatedCaneSyrup.getFluid(1000))
                .circuitMeta(0)
                .fluidOutputs(GTFOMaterialHandler.CaneSyrup.getFluid(1000))
                .EUt(120)
                .duration(80)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .inputs(GAMaterials.Glucose.getItemStack(24))
                .fluidInputs(Water.getFluid(2000))
                .fluidOutputs(GTFOMaterialHandler.UnheatedCaneSyrup.getFluid(2000))
                .EUt(80)
                .duration(260)
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(200)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(Propene.getFluid(1000))
                .fluidOutputs(GTFOMaterialHandler.IsopropylChloride.getFluid(1000))
                .buildAndRegister();

        ModHandler.addShapedRecipe("slicer_flat", SLICER_BLADE_FLAT.getStackForm(),
                "hPS", " M ", "fPs",
                'P', new UnificationEntry(plate, Iron),
                'S', new UnificationEntry(screw, Iron),
                'M', MetaItems.SHAPE_EXTRUDER_BLOCK);

        ModHandler.addShapedRecipe("slicer_stripes", SLICER_BLADE_STRIPES.getStackForm(),
                "hPS", "PMP", "fPs",
                'P', new UnificationEntry(plate, Iron),
                'S', new UnificationEntry(screw, Iron),
                'M', MetaItems.SHAPE_EXTRUDER_BLOCK);
        ModHandler.addShapedRecipe("slicer_octogonal", SLICER_BLADE_OCTAGONAL.getStackForm(),
                "PhP", "fMS", "PsP",
                'P', new UnificationEntry(plate, Iron),
                'S', new UnificationEntry(screw, Iron),
                'M', MetaItems.SHAPE_EXTRUDER_BLOCK);

        FLUID_HEATER_RECIPES.recipeBuilder()
                .fluidInputs(SeedOil.getFluid(16))
                .fluidOutputs(GTFOMaterialHandler.FryingOil.getFluid(16))
                .circuitMeta(0)
                .EUt(12)
                .duration(10)
                .buildAndRegister();

        FLUID_HEATER_RECIPES.recipeBuilder()
                .fluidInputs(SeedOil.getFluid(16))
                .fluidOutputs(GTFOMaterialHandler.HotFryingOil.getFluid(16))
                .circuitMeta(1)
                .EUt(60)
                .duration(25)
                .buildAndRegister();

        FLUID_HEATER_RECIPES.recipeBuilder()
                .fluidInputs(GTFOMaterialHandler.FryingOil.getFluid(16))
                .fluidOutputs(GTFOMaterialHandler.HotFryingOil.getFluid(16))
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

        Map<MetaItem<?>.MetaValueItem, MetaItem<?>.MetaValueItem> slicingArray = new HashMap<>();
        slicingArray.put(CUCUMBER, CUCUMBER_SLICE);
        slicingArray.put(OLIVE, OLIVE_SLICE);
        slicingArray.put(TOMATO, TOMATO_SLICE);
        slicingArray.put(ONION, ONION_SLICE);
        for(Map.Entry<MetaItem<?>.MetaValueItem, MetaItem<?>.MetaValueItem> entry : slicingArray.entrySet()) {
            ModHandler.addShapelessRecipe("gtfo_slice_" + entry.getKey().toString(), entry.getValue().getStackForm(4), 'k', entry.getKey());
            SLICER_RECIPES.recipeBuilder().EUt(18).duration(30)
                    .inputs(entry.getKey().getStackForm())
                    .notConsumable(SLICER_BLADE_FLAT.getStackForm())
                    .outputs(entry.getValue().getStackForm(8))
                    .buildAndRegister();

            // Since we already have our crops, we might as well register their green house recipes here
            RecipeUtils.addGreenHouseRecipes(entry.getKey().getStackForm(), entry.getKey());
        }
        // Get the mushroom done separately. And don't use red mushrooms.
        ModHandler.addShapelessRecipe("gtfo_slice_mushroom", MUSHROOM_SLICE.getStackForm(4), 'k', Blocks.BROWN_MUSHROOM);
        SLICER_RECIPES.recipeBuilder().EUt(18).duration(30)
                .input(Blocks.BROWN_MUSHROOM)
                .notConsumable(SLICER_BLADE_FLAT.getStackForm())
                .outputs(MUSHROOM_SLICE.getStackForm(8))
                .buildAndRegister();

        ModHandler.addShapelessRecipe("gtfo_bacon", UNCOOKED_BACON.getStackForm(3), 'k', Items.PORKCHOP);
        SLICER_RECIPES.recipeBuilder().EUt(18).duration(30)
                .input(Items.PORKCHOP)
                .notConsumable(SLICER_BLADE_FLAT.getStackForm())
                .outputs(UNCOOKED_BACON.getStackForm(6))
                .buildAndRegister();

        BakingOvenRecipeBuilder.start().duration(500).temperature(435).fuelAmount(200)
                .input(UNCOOKED_BACON.getStackForm())
                .output(BACON.getStackForm())
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(2).duration(10)
                .inputs(TOMATO_SLICE.getStackForm(4))
                .fluidOutputs(GTFOMaterialHandler.TomatoSauce.getFluid(100))
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder().EUt(27).duration(60)
                .inputs(OLIVE.getStackForm())
                .fluidOutputs(GTFOMaterialHandler.OliveOil.getFluid(100))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(60).EUt(32)
                .fluidInputs(Air.getFluid(900))
                .fluidInputs(Steam.getFluid(10))
                .fluidOutputs(GTFOMaterialHandler.MoistAir.getFluid(1000))
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(110).EUt(32)
                .fluidInputs(GTFOMaterialHandler.MoistAir.getFluid(4000))
                .fluidOutputs(GTFOMaterialHandler.ColdMoistAir.getFluid(4000))
                .buildAndRegister();

        // Chum

        MIXER_RECIPES.recipeBuilder().duration(100).EUt(24)
                .fluidInputs(GTFOMaterialHandler.Sludge.getFluid(100))
                .inputs(ROTTEN_FISH.getStackForm(1))
                .inputs(new ItemStack(Blocks.RED_MUSHROOM, 1), new ItemStack(Items.POISONOUS_POTATO, 1), new ItemStack(Items.FERMENTED_SPIDER_EYE, 1))
                .outputs(CHUM.getStackForm(3))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(100).EUt(24)
                .fluidInputs(GTFOMaterialHandler.Sludge.getFluid(100))
                .fluidInputs(GTFOMaterialHandler.PurpleDrink.getFluid(100))
                .inputs(ROTTEN_FISH.getStackForm(1))
                .inputs(new ItemStack(Blocks.RED_MUSHROOM, 1), new ItemStack(Items.POISONOUS_POTATO, 1), new ItemStack(Items.FERMENTED_SPIDER_EYE, 1))
                .outputs(CHUM.getStackForm(6))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(100).EUt(24)
                .fluidInputs(GTFOMaterialHandler.Sludge.getFluid(100))
                .inputs(ROTTEN_MEAT.getStackForm(1))
                .inputs(new ItemStack(Blocks.RED_MUSHROOM, 1), new ItemStack(Items.POISONOUS_POTATO, 1), new ItemStack(Items.FERMENTED_SPIDER_EYE, 1))
                .outputs(CHUM.getStackForm(3))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(100).EUt(24)
                .fluidInputs(GTFOMaterialHandler.Sludge.getFluid(100))
                .fluidInputs(GTFOMaterialHandler.PurpleDrink.getFluid(100))
                .inputs(ROTTEN_MEAT.getStackForm(1))
                .inputs(new ItemStack(Blocks.RED_MUSHROOM, 1), new ItemStack(Items.POISONOUS_POTATO, 1), new ItemStack(Items.FERMENTED_SPIDER_EYE, 1))
                .outputs(CHUM.getStackForm(6))
                .buildAndRegister();

        for (ItemStack stack : RecipeUtils.getFish()) {
            FERMENTING_RECIPES.recipeBuilder().duration(100).EUt(8)
                    .inputs(stack)
                    .outputs(ROTTEN_FISH.getStackForm())
                    .fluidInputs(Water.getFluid(100))
                    .fluidOutputs(Water.getFluid(100))
                    .buildAndRegister();
        }
        for (ItemStack stack : RecipeUtils.getMeat()) {
            FERMENTING_RECIPES.recipeBuilder().duration(100).EUt(8)
                    .inputs(stack)
                    .outputs(ROTTEN_MEAT.getStackForm())
                    .fluidInputs(Water.getFluid(100))
                    .fluidOutputs(Water.getFluid(100))
                    .buildAndRegister();
        }
        for (ItemStack stack : RecipeUtils.getAnimalProducts()) {
            MIXER_RECIPES.recipeBuilder().duration(500).EUt(16)
                    .inputs(stack)
                    .fluidInputs(Water.getFluid(400))
                    .fluidOutputs(GTFOMaterialHandler.Sludge.getFluid(100))
                    .buildAndRegister();
            MIXER_RECIPES.recipeBuilder().duration(250).EUt(16)
                    .inputs(stack)
                    .fluidInputs(SulfuricAcid.getFluid(200))
                    .fluidOutputs(GTFOMaterialHandler.Sludge.getFluid(200))
                    .buildAndRegister();
            MIXER_RECIPES.recipeBuilder().duration(125).EUt(16)
                    .inputs(stack)
                    .fluidInputs(PiranhaSolution.getFluid(100))
                    .fluidOutputs(GTFOMaterialHandler.Sludge.getFluid(400))
                    .buildAndRegister();
        }
        ModHandler.addShapelessRecipe("chum_on_a_stick", CHUM_ON_A_STICK.getStackForm(), CHUM.getStackForm(), new ItemStack(Items.STICK));
        ASSEMBLER_RECIPES.recipeBuilder().EUt(4).duration(5)
                .input(Items.STICK)
                .inputs(CHUM.getStackForm())
                .outputs(CHUM_ON_A_STICK.getStackForm())
                .buildAndRegister();

    }
}
