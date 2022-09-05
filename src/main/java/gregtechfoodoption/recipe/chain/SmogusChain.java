package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.common.items.MetaItems;
import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.GTFOMaterialHandler;
import gregtechfoodoption.integration.applecore.GTFOAppleCoreCompat;
import gregtechfoodoption.utils.GTFOUtils;
import nc.init.NCItems;
import nc.recipe.AbstractRecipeHandler;
import nc.recipe.NCRecipes;
import nc.recipe.ingredient.FluidIngredient;
import nc.recipe.ingredient.ItemIngredient;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;
import java.util.Collections;

import static gregtech.api.recipes.GTRecipeHandler.removeRecipesByInputs;
import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtechfoodoption.GTFOMaterialHandler.CaneSyrup;
import static gregtechfoodoption.item.GTFOMetaItem.*;
import static nc.recipe.AbstractRecipeHandler.fluidStack;
import gregtechfoodoption.item.*;


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

        GTFOAppleCoreCompat.addToSparedItems(NCItems.smore);
        GTFOAppleCoreCompat.addToSparedItems(NCItems.moresmore);
        GTFOAppleCoreCompat.addToSparedItems(NCItems.foursmore);

        ItemStack[] smoresout = {
                SMORE_SMINGOT.getStackForm(),
                MORESMORE_DOUBLESMINGOT.getStackForm(),
                FOURSMORE_QUADSMINGOT.getStackForm(),
                EIGHT_SMORE.getStackForm(),
                SIXTEEN_SMORE.getStackForm(),
                THIRTY_TWO_SMORE.getStackForm(),
                SIXTY_FOUR_SMORE.getStackForm(),
                SMOGUS.getStackForm(),
                MORE_SMOGUS.getStackForm(),
                FOUR_SMOGUS.getStackForm(),
                HEART_SMOGUS.getStackForm()};
        ItemStack[] smoresin = {
                GRAHAM_CRACKER.getStackForm(2),
                SMORE_SMINGOT.getStackForm(2),
                MORESMORE_DOUBLESMINGOT.getStackForm(2),
                FOURSMORE_QUADSMINGOT.getStackForm(2),
                EIGHT_SMORE.getStackForm(2),
                SIXTEEN_SMORE.getStackForm(2),
                THIRTY_TWO_SMORE.getStackForm(2),
                SIXTY_FOUR_SMORE.getStackForm(2),
                SMOGUS.getStackForm(2),
                MORE_SMOGUS.getStackForm(2),
                FOUR_SMOGUS.getStackForm(2)};
        int euPerTick = 1920;
        int ticks = 25;
        for (int i = 0; i < 3 + 8 * GTFOUtils.boolToInt(GTFOConfig.gtfoncConfig.addSmogus); i++) {
            if (i < 7) {
                FORMING_PRESS_RECIPES.recipeBuilder()
                        .inputs(MARSHMALLOW.getStackForm())
                        .inputs(GTFOMaterialHandler.HOT_MILK_CHOCOLATE.getItemStack())
                        .inputs(smoresin[i])
                        .outputs(smoresout[i])
                        .EUt(euPerTick)
                        .duration(ticks)
                        .buildAndRegister();

            } else {
                GTFOUtils.stellarForgeProxy().recipeBuilder()
                        .inputs(MARSHMALLOW.getStackForm())
                        .inputs(GTFOMaterialHandler.HOT_MILK_CHOCOLATE.getItemStack())
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
                .inputs(MILK_CHOCOLATE.getStackForm())
                .outputs(GTFOMaterialHandler.HOT_MILK_CHOCOLATE.getItemStack())
                .blastFurnaceTemp(600)
                .EUt(120)
                .duration(200)
                .buildAndRegister();

        EXTRUDER_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.MATTER_MARSHMALLOW.getItemStack())
                .notConsumable(MetaItems.SHAPE_EXTRUDER_ROD)
                .outputs(MARSHMALLOW.getStackForm(2))
                .EUt(90)
                .duration(40)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.CRACKER_GRAHAM_UNGRADED.getItemStack())
                .notConsumable(MetaItems.SENSOR_HV.getStackForm())
                .chancedOutput(GRAHAM_CRACKER.getStackForm(), 7500, 100)
                .EUt(340)
                .duration(40)
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .fluidInputs(GTFOMaterialHandler.MoltenMilkChocolate.getFluid(144))
                .notConsumable(MetaItems.SHAPE_MOLD_INGOT)
                .outputs(MILK_CHOCOLATE.getStackForm())
                .EUt(32)
                .duration(200)
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .fluidInputs(GTFOMaterialHandler.MarshmallowFoam.getFluid(1000))
                .notConsumable(MetaItems.SHAPE_MOLD_BLOCK)
                .outputs(GTFOMaterialHandler.MATTER_MARSHMALLOW.getItemStack())
                .EUt(60)
                .duration(100)
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.WAFER_GRAHAM_HOT.getItemStack())
                .outputs(GTFOMaterialHandler.CRACKER_GRAHAM_UNGRADED.getItemStack())
                .EUt(60)
                .duration(20)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(GTFOMaterialHandler.MoltenDarkChocolate.getFluid(864))
                .fluidInputs(fluidStack("milk", 288).getStack())
                .fluidOutputs(GTFOMaterialHandler.MoltenMilkChocolate.getFluid(1152))
                .EUt(500)
                .duration(280)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, Materials.Sugar, 1)
                .fluidInputs(GTFOMaterialHandler.CocoaButter.getFluid(144))
                .fluidInputs(GTFOMaterialHandler.MoltenUnsweetenedChocolate.getFluid(1008))
                .fluidOutputs(GTFOMaterialHandler.MoltenDarkChocolate.getFluid(1152))
                .EUt(120)
                .duration(160)
                .buildAndRegister();

        FLUID_HEATER_RECIPES.recipeBuilder()
                .fluidInputs(GTFOMaterialHandler.MarshmallowSyrupMixture.getFluid(1000))
                .circuitMeta(1)
                .fluidOutputs(GTFOMaterialHandler.MarshmallowFoam.getFluid(5000))
                .EUt(240)
                .duration(300)
                .buildAndRegister();

        CUTTER_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.CHUNK_GRAHAM_HOT.getItemStack())
                .outputs(GTFOMaterialHandler.WAFER_GRAHAM_HOT.getItemStack(9))
                .EUt(60)
                .duration(200)
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, Materials.Cocoa)
                .fluidOutputs(GTFOMaterialHandler.MoltenUnsweetenedChocolate.getFluid(144))
                .EUt(180)
                .duration(20)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.PRESS_CAKE_GRADED.getItemStack())
                .output(OrePrefix.dust, Cocoa, 1)
                .EUt(120)
                .duration(120)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .inputs(GELATIN.getStackForm(2))
                .fluidInputs(GTFOMaterialHandler.Albumen.getFluid(500))
                .fluidInputs(GTFOMaterialHandler.SweetenedDilutedCaneSyrupMixture.getFluid(5000))
                .fluidOutputs(GTFOMaterialHandler.MarshmallowSyrupMixture.getFluid(5500))
                .notConsumable(IntCircuitIngredient.getIntegratedCircuit(2))
                .EUt(120)
                .duration(300)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.PRESS_CAKE.getItemStack())
                .notConsumable(MetaItems.SENSOR_HV.getStackForm())
                .chancedOutput(GTFOMaterialHandler.PRESS_CAKE_GRADED.getItemStack(), 5000, 100)
                .EUt(480)
                .duration(40)
                .buildAndRegister();

        ASSEMBLER_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.PRESS_CAKE_DUTCHED.getItemStack())
                .notConsumable(MetaItems.SENSOR_HV.getStackForm())
                .chancedOutput(GTFOMaterialHandler.PRESS_CAKE_GRADED.getItemStack(), 7500, 300)
                .EUt(480)
                .duration(40)
                .buildAndRegister();

        BENDER_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.MATTER_GRAHAM_HOT.getItemStack())
                .circuitMeta(1)
                .outputs(GTFOMaterialHandler.CHUNK_GRAHAM_HOT.getItemStack())
                .EUt(45)
                .duration(80)
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.CHOCOLATE_LIQUOR_PRESSED.getItemStack(2))
                .outputs(GTFOMaterialHandler.PRESS_CAKE.getItemStack())
                .fluidOutputs(GTFOMaterialHandler.CocoaButter.getFluid(144))
                .EUt(120)
                .duration(100)
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.CHOCOLATE_LIQUOR_DUTCHED_PRESSED.getItemStack(2))
                .outputs(GTFOMaterialHandler.PRESS_CAKE_DUTCHED.getItemStack())
                .fluidOutputs(GTFOMaterialHandler.CocoaButter.getFluid(144))
                .EUt(120)
                .duration(100)
                .buildAndRegister();

        GTFOUtils.addBakingOvenRecipes(GTFOMaterialHandler.MATTER_GRAHAM.getItemStack(), GTFOMaterialHandler.MATTER_GRAHAM_HOT.getItemStack(), 400, 450, 3);

        Item[] gelatins = {Items.BONE, Items.LEATHER, Items.PORKCHOP, Items.BEEF, Items.CHICKEN, Items.RABBIT, Items.MUTTON};

        for (Item gelatinProducer : gelatins){
            EXTRACTOR_RECIPES.recipeBuilder()
                    .input(gelatinProducer)
                    .outputs(GELATIN.getStackForm())
                    .EUt(120)
                    .duration(100)
                    .buildAndRegister();
        }

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder()
                .fluidInputs(FishOil.getFluid(500))
                .notConsumable(MetaItems.SHAPE_MOLD_PLATE)
                .outputs(GELATIN.getStackForm())
                .EUt(60)
                .duration(100)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .input(Items.SUGAR)
                .input(OrePrefix.dust, Wheat, 3)
                .input(OrePrefix.dust, SodiumBicarbonate)
                .fluidInputs(GTFOMaterialHandler.Butter.getFluid(2000))
                .fluidInputs(Milk.getFluid(500))
                .outputs(GTFOMaterialHandler.MATTER_GRAHAM.getItemStack(10))
                .EUt(80)
                .duration(200)
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.CHOCOLATE_LIQUOR_REFINED.getItemStack())
                .outputs(GTFOMaterialHandler.CHOCOLATE_LIQUOR_PRESSED.getItemStack())
                .EUt(360)
                .duration(80)
                .buildAndRegister();

        COMPRESSOR_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.CHOCOLATE_LIQUOR_DUTCHED.getItemStack())
                .outputs(GTFOMaterialHandler.CHOCOLATE_LIQUOR_DUTCHED_PRESSED.getItemStack())
                .EUt(360)
                .duration(80)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .input(Items.SUGAR)
                .fluidInputs(Water.getFluid(5000))
                .fluidInputs(CaneSyrup.getFluid(5000))
                .fluidOutputs(GTFOMaterialHandler.SweetenedDilutedCaneSyrupMixture.getFluid(10000))
                .notConsumable(IntCircuitIngredient.getIntegratedCircuit(1))
                .EUt(120)
                .duration(260)
                .buildAndRegister();

        CHEMICAL_BATH_RECIPES.recipeBuilder()
                .notConsumable(GTFOMaterialHandler.SodiumCarbonateSolution.getFluid(1000))
                .inputs(GTFOMaterialHandler.CHOCOLATE_LIQUOR_REFINED.getItemStack())
                .outputs(GTFOMaterialHandler.CHOCOLATE_LIQUOR_DUTCHED.getItemStack())
                .EUt(540)
                .duration(160)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .fluidInputs(Water.getFluid(1000))
                .input(OrePrefix.dust, SodaAsh, 4)
                .fluidOutputs(GTFOMaterialHandler.SodiumCarbonateSolution.getFluid(1000))
                .EUt(30)
                .duration(40)
                .buildAndRegister();

        LATHE_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.CHOCOLATE_LIQUOR.getItemStack())
                .outputs(GTFOMaterialHandler.CHOCOLATE_LIQUOR_REFINED.getItemStack())
                .EUt(200)
                .duration(60)
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(COCOA_NIBS.getStackForm())
                .outputs(GTFOMaterialHandler.CHOCOLATE_LIQUOR.getItemStack())
                .EUt(270)
                .duration(40)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.COCOA_NIB.getItemStack())
                .outputs(COCOA_NIBS.getStackForm())
                .EUt(30)
                .duration(40)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder()
                .inputs(GTFOMaterialHandler.COCOA_HULL.getItemStack())
                .output(OrePrefix.dustSmall, Wood, 2)
                .EUt(8)
                .duration(15)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .inputs(ROASTED_COCOA_BEANS.getStackForm(6))
                .fluidInputs(Steam.getFluid(10000))
                .outputs(GTFOMaterialHandler.COCOA_HULL.getItemStack(6))
                .outputs(GTFOMaterialHandler.COCOA_NIB.getItemStack(6))
                .EUt(360)
                .duration(90)
                .buildAndRegister();

        PYROLYSE_RECIPES.recipeBuilder()
                .input(Items.DYE, 8, 3)
                .circuitMeta(1)
                .outputs(ROASTED_COCOA_BEANS.getStackForm(8))
                .fluidOutputs(Water.getFluid(1000))
                .EUt(120)
                .duration(30)
                .buildAndRegister();
    }

    public static void removeRecipeByMapAndProducts(AbstractRecipeHandler handler, ItemIngredient... items) {
        handler.removeRecipe(handler.getRecipeFromProducts(Arrays.asList(items), Collections.emptyList()));
    }

    public static void removeRecipeByMapAndProducts(AbstractRecipeHandler handler, FluidIngredient... fluids) {
        handler.removeRecipe(handler.getRecipeFromProducts(Collections.emptyList(), Arrays.asList(fluids)));
    }

    public static void removeRecipeByMapAndProducts(AbstractRecipeHandler handler, ItemIngredient[] items, FluidIngredient[] fluids) {
        handler.removeRecipe(handler.getRecipeFromProducts(Arrays.asList(items), Arrays.asList(fluids)));
    }

    public static ItemIngredient convertToItemIngredient(Item item, int count) {
        return new ItemIngredient(new ItemStack(item, count));
    }

    public static FluidIngredient convertToFluidIngredient(String fluid, int count) {
        return new FluidIngredient(fluid, count);
    }


}
