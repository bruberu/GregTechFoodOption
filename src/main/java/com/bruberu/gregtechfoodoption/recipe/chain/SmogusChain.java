package com.bruberu.gregtechfoodoption.recipe.chain;

import com.bruberu.gregtechfoodoption.GTFOConfig;
import com.bruberu.gregtechfoodoption.utils.RecipeUtils;
import gregicadditions.GAMaterials;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import nc.init.NCItems;
import nc.recipe.BasicRecipeHandler;
import nc.recipe.NCRecipes;
import nc.recipe.ingredient.FluidIngredient;
import nc.recipe.ingredient.ItemIngredient;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;
import java.util.Collections;

import static com.bruberu.gregtechfoodoption.GTFOMaterialHandler.CaneSyrup;
import static com.bruberu.gregtechfoodoption.integration.GTFONCMaterialHandler.*;
import static com.bruberu.gregtechfoodoption.item.GTFOMetaItem.*;
import static gregicadditions.recipes.GARecipeMaps.*;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static nc.recipe.AbstractRecipeHandler.fluidStack;
import static gregicadditions.recipes.helper.HelperMethods.removeRecipesByInputs;


public class SmogusChain {
    public static void init() {
        removeRecipeByMapAndProducts(NCRecipes.ingot_former, convertToItemIngredient(NCItems.milk_chocolate, 1));
        removeRecipeByMapAndProducts(NCRecipes.ingot_former, convertToItemIngredient(NCItems.dark_chocolate, 1));
        removeRecipeByMapAndProducts(NCRecipes.ingot_former, convertToItemIngredient(NCItems.unsweetened_chocolate, 1));
        removeRecipeByMapAndProducts(NCRecipes.ingot_former, convertToItemIngredient(NCItems.marshmallow, 1));
        removeRecipeByMapAndProducts(NCRecipes.salt_mixer, convertToFluidIngredient("milk_chocolate", 288));
        removeRecipeByMapAndProducts(NCRecipes.salt_mixer, convertToFluidIngredient("dark_chocolate", 144));
        removeRecipeByMapAndProducts(NCRecipes.salt_mixer, convertToFluidIngredient("unsweetened_chocolate", 144));
        removeRecipeByMapAndProducts(NCRecipes.extractor, new ItemIngredient[]{convertToItemIngredient(NCItems.cocoa_solids, 1)}, new FluidIngredient[]{convertToFluidIngredient("cocoa_butter", 144)});
        removeRecipeByMapAndProducts(NCRecipes.manufactory, convertToItemIngredient(NCItems.flour, 1));
        removeRecipeByMapAndProducts(NCRecipes.manufactory, convertToItemIngredient(NCItems.gelatin, 4));
        removeRecipeByMapAndProducts(NCRecipes.manufactory, convertToItemIngredient(NCItems.gelatin, 8));
        removeRecipeByMapAndProducts(NCRecipes.manufactory, convertToItemIngredient(NCItems.ground_cocoa_nibs, 1));
        removeRecipeByMapAndProducts(NCRecipes.pressurizer, convertToItemIngredient(NCItems.graham_cracker, 1));
        ModHandler.removeFurnaceSmelting(new ItemStack(Items.DYE, 1, 3));
        ModHandler.removeRecipeByName(new ResourceLocation("nuclearcraft:smore"));
        ModHandler.removeRecipeByName(new ResourceLocation("nuclearcraft:moresmore"));
        ModHandler.removeRecipeByName(new ResourceLocation("nuclearcraft:foursmore"));
        removeRecipesByInputs(MACERATOR_RECIPES, new ItemStack(Items.DYE, 1, 3));

        ItemStack[] smoresout = {
                new ItemStack(NCItems.smore),
                new ItemStack(NCItems.moresmore),
                new ItemStack(NCItems.foursmore),
                EIGHT_SMORE.getStackForm(),
                SIXTEEN_SMORE.getStackForm(),
                THIRTY_TWO_SMORE.getStackForm(),
                SIXTY_FOUR_SMORE.getStackForm(),
                SMOGUS.getStackForm(),
                MORE_SMOGUS.getStackForm(),
                FOUR_SMOGUS.getStackForm(),
                HEART_SMOGUS.getStackForm()};
        ItemStack[] smoresin = {
                new ItemStack(NCItems.graham_cracker, 2),
                new ItemStack(NCItems.smore, 2),
                new ItemStack(NCItems.moresmore, 2),
                new ItemStack(NCItems.foursmore, 2),
                EIGHT_SMORE.getStackForm(2),
                SIXTEEN_SMORE.getStackForm(2),
                THIRTY_TWO_SMORE.getStackForm(2),
                SIXTY_FOUR_SMORE.getStackForm(2),
                SMOGUS.getStackForm(2),
                MORE_SMOGUS.getStackForm(2),
                FOUR_SMOGUS.getStackForm(2)};
        int euPerTick = 1920;
        int ticks = 25;
        for (int i = 0; i < 3 + 8 * RecipeUtils.boolToInt(GTFOConfig.gtfoncConfig.addSmogus); i++) {
            if (i < 7) {
                FORMING_PRESS_RECIPES.recipeBuilder()
                        .inputs(new ItemStack(NCItems.marshmallow))
                        .inputs(HOT_MILK_CHOCOLATE.getItemStack())
                        .inputs(smoresin[i])
                        .outputs(smoresout[i])
                        .EUt(euPerTick)
                        .duration(ticks)
                        .buildAndRegister();

            } else {
                STELLAR_FORGE_RECIPES.recipeBuilder()
                        .inputs(new ItemStack(NCItems.marshmallow))
                        .inputs(HOT_MILK_CHOCOLATE.getItemStack())
                        .inputs(smoresin[i])
                        .outputs(smoresout[i])
                        .EUt(euPerTick)
                        .duration(ticks)
                        .buildAndRegister();
            }
            euPerTick *= 4;
            ticks *= 2;
        }


        BLAST_RECIPES.recipeBuilder()
                .input(NCItems.milk_chocolate)
                .outputs(HOT_MILK_CHOCOLATE.getItemStack())
                .blastFurnaceTemp(600)
                .EUt(120)
                .duration(200)
                .buildAndRegister();

        EXTRUDER_RECIPES.recipeBuilder()
                .inputs(MATTER_MARSHMALLOW.getItemStack())
                .notConsumable(MetaItems.SHAPE_EXTRUDER_ROD)
                .outputs(new ItemStack(NCItems.marshmallow, 2))
                .EUt(90)
                .duration(40)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(CRACKER_GRAHAM_UNGRADED.getItemStack())
                .notConsumable(MetaItems.SENSOR_HV.getStackForm())
                .chancedOutput(new ItemStack(NCItems.graham_cracker), 7500, 100)
                .EUt(340)
                .duration(40)
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .fluidInputs(fluidStack("dark_chocolate", 144).getStack())
	            .notConsumable(MetaItems.SHAPE_MOLD_INGOT)
	            .output(NCItems.dark_chocolate)
	            .EUt(32)
                .duration(200)
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .fluidInputs(fluidStack("milk_chocolate", 144).getStack())
	            .notConsumable(MetaItems.SHAPE_MOLD_INGOT)
	            .output(NCItems.milk_chocolate)
            	.EUt(32)
                .duration(200)
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .fluidInputs(MarshmallowFoam.getFluid(1000))
	            .notConsumable(MetaItems.SHAPE_MOLD_BLOCK)
	            .outputs(MATTER_MARSHMALLOW.getItemStack())
	            .EUt(60)
                .duration(100)
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder()
                .inputs(WAFER_GRAHAM_HOT.getItemStack())
	            .outputs(CRACKER_GRAHAM_UNGRADED.getItemStack())
	            .EUt(60)
                .duration(20)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(fluidStack("dark_chocolate", 864).getStack())
	            .fluidInputs(fluidStack("milk", 288).getStack())
	            .fluidOutputs(fluidStack("milk_chocolate", 1152).getStack())
	            .EUt(500)
                .duration(280)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, Materials.Sugar, 1)
	            .fluidInputs(fluidStack("cocoa_butter", 144).getStack())
	            .fluidInputs(fluidStack("unsweetened_chocolate", 1008).getStack())
            	.fluidOutputs(fluidStack("dark_chocolate", 1152).getStack())
            	.EUt(120)
                .duration(160)
                .buildAndRegister();

        FLUID_HEATER_RECIPES.recipeBuilder()
                .fluidInputs(MarshmallowSyrupMixture.getFluid(1000))
	            .circuitMeta(0)
                .fluidOutputs(MarshmallowFoam.getFluid(5000))
            	.EUt(240)
                .duration(300)
                .buildAndRegister();

        CUTTER_RECIPES.recipeBuilder()
                .inputs(CHUNK_GRAHAM_HOT.getItemStack())
            	.outputs(WAFER_GRAHAM_HOT.getItemStack(9))
            	.EUt(60)
                .duration(200)
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, Materials.Cocoa)
            	.fluidOutputs(fluidStack("unsweetened_chocolate", 144).getStack())
            	.EUt(180)
                .duration(20)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .inputs(PRESS_CAKE_GRADED.getItemStack())
                .output(OrePrefix.dust, Cocoa, 1)
	            .EUt(120)
                .duration(120)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .input(NCItems.gelatin, 2)
            	.fluidInputs(Albumen.getFluid(500))
          	    .fluidInputs(SweetenedDilutedCaneSyrupMixture.getFluid(5000))
            	.fluidOutputs(MarshmallowSyrupMixture.getFluid(5500))
            	.EUt(120)
                .duration(300)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(PRESS_CAKE.getItemStack())
            	.notConsumable(MetaItems.SENSOR_HV.getStackForm())
                .chancedOutput(PRESS_CAKE_GRADED.getItemStack(), 5000, 100)
	            .EUt(480)
                .duration(40)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(PRESS_CAKE_DUTCHED.getItemStack())
	            .notConsumable(MetaItems.SENSOR_HV.getStackForm())
                .chancedOutput(PRESS_CAKE_GRADED.getItemStack(), 7500, 300)
	            .EUt(480)
                .duration(40)
                .buildAndRegister();

        BENDER_RECIPES.recipeBuilder()
                .inputs(MATTER_GRAHAM_HOT.getItemStack())
	            .circuitMeta(0)
                .outputs(CHUNK_GRAHAM_HOT.getItemStack())
	            .EUt(45)
                .duration(80)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(Items.EGG)
	            .fluidOutputs(Albumen.getFluid(100))
            	.fluidOutputs(Yolk.getFluid(100))
            	.EUt(45)
                .duration(60)
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder()
                .inputs(CHOCOLATE_LIQUOR_PRESSED.getItemStack(2))
            	.outputs(PRESS_CAKE.getItemStack())
            	.fluidOutputs(fluidStack("cocoa_butter", 144).getStack())
            	.EUt(120)
                .duration(100)
                .buildAndRegister();

        FLUID_EXTRACTION_RECIPES.recipeBuilder()
                .inputs(CHOCOLATE_LIQUOR_DUTCHED_PRESSED.getItemStack(2))
	            .outputs(PRESS_CAKE_DUTCHED.getItemStack())
	            .fluidOutputs(fluidStack("cocoa_butter", 144).getStack())
	            .EUt(120)
                .duration(100)
                .buildAndRegister();

        ModHandler.addSmeltingRecipe(MATTER_GRAHAM.getItemStack(), MATTER_GRAHAM_HOT.getItemStack());

        Item[] gelatins = {Items.BONE, Items.LEATHER, Items.PORKCHOP, Items.BEEF, Items.CHICKEN, Items.RABBIT, Items.MUTTON};

        for (Item gelatinProducer : gelatins){
            EXTRACTOR_RECIPES.recipeBuilder()
                    .input(gelatinProducer)
                    .output(NCItems.gelatin)
		            .EUt(120)
                    .duration(100)
                    .buildAndRegister();
        }

           FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .fluidInputs(GAMaterials.FishOil.getFluid(500))
	            .notConsumable(MetaItems.SHAPE_MOLD_PLATE)
	            .output(NCItems.gelatin)
	            .EUt(60)
                .duration(100)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .input(Items.SUGAR)
	            .input(OrePrefix.dust, Wheat, 3)
    	        .inputs(GAMaterials.SodiumBicarbonate.getItemStack(6))
	            .fluidInputs(Butter.getFluid(2000))
	            .fluidInputs(Milk.getFluid(500))
	            .outputs(MATTER_GRAHAM.getItemStack(10))
	            .EUt(80)
                .duration(200)
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(CHOCOLATE_LIQUOR_REFINED.getItemStack())
	            .outputs(CHOCOLATE_LIQUOR_PRESSED.getItemStack())
	            .EUt(360)
                .duration(80)
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(CHOCOLATE_LIQUOR_DUTCHED.getItemStack())
	            .outputs(CHOCOLATE_LIQUOR_DUTCHED_PRESSED.getItemStack())
	            .EUt(360)
                .duration(80)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .input(Items.SUGAR)
	            .fluidInputs(Water.getFluid(5000))
            	.fluidInputs(CaneSyrup.getFluid(5000))
            	.fluidOutputs(SweetenedDilutedCaneSyrupMixture.getFluid(10000))
            	.EUt(120)
                .duration(260)
                .buildAndRegister();

        FERMENTING_RECIPES.recipeBuilder()
                .fluidInputs(Milk.getFluid(10000))
	            .fluidOutputs(Butter.getFluid(9000))
	            .EUt(15)
                .duration(1200)
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder()
                .notConsumable(PotassiumCarbonateSolution.getFluid(1000))
	            .inputs(CHOCOLATE_LIQUOR_REFINED.getItemStack())
	            .outputs(CHOCOLATE_LIQUOR_DUTCHED.getItemStack())
	            .EUt(540)
                .duration(160)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Water.getFluid(1000))
	            .inputs(GAMaterials.PotassiumCarbonate.getItemStack(6))
                .fluidOutputs(PotassiumCarbonateSolution.getFluid(1000))
            	.EUt(30)
                .duration(40)
                .buildAndRegister();

        CLUSTER_MILL_RECIPES.recipeBuilder()
                .inputs(CHOCOLATE_LIQUOR.getItemStack())
	            .outputs(CHOCOLATE_LIQUOR_REFINED.getItemStack())
	            .EUt(200)
                .duration(60)
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder()
                .input(NCItems.ground_cocoa_nibs)
	            .outputs(CHOCOLATE_LIQUOR.getItemStack())
	            .EUt(270)
                .duration(40)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .inputs(COCOA_NIB.getItemStack())
	            .output(NCItems.ground_cocoa_nibs)
	            .EUt(120)
                .duration(40)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .inputs(COCOA_HULL.getItemStack())
	            .output(OrePrefix.dustSmall, Wood, 2)
	            .EUt(8)
                .duration(15)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(NCItems.roasted_cocoa_beans, 6)
                .fluidInputs(Steam.getFluid(10000))
	            .outputs(COCOA_HULL.getItemStack(6))
	            .outputs(COCOA_NIB.getItemStack(6))
            	.EUt(360)
                .duration(90)
                .buildAndRegister();

        PYROLYSE_RECIPES.recipeBuilder()
                .input(Items.DYE, 8, 3)
            	.circuitMeta(0)
                .outputs(new ItemStack(NCItems.roasted_cocoa_beans, 8))
                .fluidOutputs(Water.getFluid(1000))
	            .EUt(120)
                .duration(30)
                .buildAndRegister();
    }

    public static void removeRecipeByMapAndProducts(BasicRecipeHandler handler, ItemIngredient... items) {
        handler.removeRecipe(handler.getRecipeFromProducts(Arrays.asList(items), Collections.emptyList()));
    }

    public static void removeRecipeByMapAndProducts(BasicRecipeHandler handler, FluidIngredient... fluids) {
        handler.removeRecipe(handler.getRecipeFromProducts(Collections.emptyList(), Arrays.asList(fluids)));
    }

    public static void removeRecipeByMapAndProducts(BasicRecipeHandler handler, ItemIngredient[] items, FluidIngredient[] fluids) {
        handler.removeRecipe(handler.getRecipeFromProducts(Arrays.asList(items), Arrays.asList(fluids)));
    }

    public static ItemIngredient convertToItemIngredient(Item item, int count) {
        return new ItemIngredient(new ItemStack(item, count));
    }

    public static FluidIngredient convertToFluidIngredient(String fluid, int count) {
        return new FluidIngredient(fluid, count);
    }


}
