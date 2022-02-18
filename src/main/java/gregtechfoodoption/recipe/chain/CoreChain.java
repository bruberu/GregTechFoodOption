package gregtechfoodoption.recipe.chain;

//Used for cross-chain materials.

import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;
import gregtechfoodoption.GTFOMaterialHandler;
import gregtechfoodoption.block.GTFOMetaBlocks;
import gregtechfoodoption.utils.GTFOUtils;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.*;
import static gregtech.common.items.MetaItems.*;
import static gregtechfoodoption.GTFOMaterialHandler.*;
import static gregtechfoodoption.block.GTFOBlockCasing.CasingType.ADOBE_BRICKS;
import static gregtechfoodoption.item.GTFOMetaItem.*;
import static gregtechfoodoption.recipe.GTFORecipeMaps.SLICER_RECIPES;

public class CoreChain {
    public static void init() {
        zest();
        caneSyrup();
        meatAndFat();
        airRecipes();
        bakingOvenRecipes();
        chum();
        liquidFoodExtracts();
        generalChemicals();
        misc();
        slicerBlades();
        slicingRecipes();
    }

    public static void zest() {
        EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(LEMON.getStackForm())
                .outputs(GTFOMaterialHandler.Zest.getItemStack(1))
                .fluidOutputs(GTFOMaterialHandler.LemonExtract.getFluid(100))
                .EUt(5)
                .duration(100)
                .buildAndRegister();
        EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(LIME.getStackForm())
                .outputs(GTFOMaterialHandler.Zest.getItemStack(1))
                .fluidOutputs(GTFOMaterialHandler.LimeExtract.getFluid(100))
                .EUt(5)
                .duration(100)
                .buildAndRegister();
        EXTRACTOR_RECIPES.recipeBuilder()
                .inputs(ORANGE.getStackForm())
                .outputs(GTFOMaterialHandler.Zest.getItemStack(1))
                .fluidOutputs(GTFOMaterialHandler.OrangeExtract.getFluid(100))
                .EUt(5)
                .duration(100)
                .buildAndRegister();
        //Hand recipe for early game (veeeery ineficient) P.S: i don't think anyone should use this tbh but i don't want people to feel forced, so have a crappy yield recipe :D
        ModHandler.addShapelessRecipe("gtfo_hand_zest1", GTFOMaterialHandler.Zest.getItemStack(), LEMON, LEMON, LEMON, LEMON, OreDictUnifier.get("craftingToolMortar"));
        ModHandler.addShapelessRecipe("gtfo_hand_zest2", GTFOMaterialHandler.Zest.getItemStack(), LIME, LIME, LIME, LIME, OreDictUnifier.get("craftingToolMortar"));
        ModHandler.addShapelessRecipe("gtfo_hand_zest3", GTFOMaterialHandler.Zest.getItemStack(), ORANGE, ORANGE, ORANGE, ORANGE, OreDictUnifier.get("craftingToolMortar"));
    }

    public static void caneSyrup() {
        FLUID_HEATER_RECIPES.recipeBuilder()
                .fluidInputs(GTFOMaterialHandler.UnheatedCaneSyrup.getFluid(1000))
                .circuitMeta(0)
                .fluidOutputs(GTFOMaterialHandler.CaneSyrup.getFluid(1000))
                .EUt(120)
                .duration(80)
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder()
                .input(OrePrefix.dust, Sugar, 24)
                .fluidInputs(Water.getFluid(2000))
                .fluidOutputs(GTFOMaterialHandler.UnheatedCaneSyrup.getFluid(2000))
                .EUt(80)
                .duration(260)
                .buildAndRegister();
    }

    public static void generalChemicals() {
        CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(200)
                .fluidInputs(HydrochloricAcid.getFluid(1000))
                .fluidInputs(Propene.getFluid(1000))
                .fluidOutputs(GTFOMaterialHandler.IsopropylChloride.getFluid(1000))
                .buildAndRegister();

        ELECTROLYZER_RECIPES.recipeBuilder().duration(300).EUt(480)
                .input(dust, Gold, 2)
                .fluidInputs(HydrochloricAcid.getFluid(8000))
                .fluidOutputs(GTFOMaterialHandler.ChloroauricAcid.getFluid(2000), Hydrogen.getFluid(3000))
                .buildAndRegister();
    }

    public static void slicerBlades() {

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
    }

    public static void liquidFoodExtracts() {

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

        EXTRACTOR_RECIPES.recipeBuilder().EUt(2).duration(10)
                .inputs(TOMATO_SLICE.getStackForm(4))
                .fluidOutputs(GTFOMaterialHandler.TomatoSauce.getFluid(100))
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().EUt(27).duration(60)
                .inputs(OLIVE.getStackForm())
                .fluidOutputs(GTFOMaterialHandler.OliveOil.getFluid(100))
                .buildAndRegister();

        EXTRACTOR_RECIPES.recipeBuilder().EUt(2).duration(10)
                .inputs(APPLE_SLICE.getStackForm(4))
                .fluidOutputs(AppleExtract.getFluid(100))
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder()
                .inputs(APPLE_JUICE.getStackForm())
                .fluidOutputs(AppleExtract.getFluid(100))
                .output(Items.GLASS_BOTTLE, 1)
                .EUt(12)
                .duration(30)
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder()
                .inputs(ORANGE_JUICE.getStackForm())
                .fluidOutputs(OrangeExtract.getFluid(100))
                .output(Items.GLASS_BOTTLE, 1)
                .EUt(12)
                .duration(30)
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder()
                .outputs(APPLE_JUICE.getStackForm())
                .fluidInputs(AppleExtract.getFluid(100))
                .input(Items.GLASS_BOTTLE, 1)
                .EUt(12)
                .duration(30)
                .buildAndRegister();

        CANNER_RECIPES.recipeBuilder()
                .outputs(ORANGE_JUICE.getStackForm())
                .fluidInputs(OrangeExtract.getFluid(100))
                .input(Items.GLASS_BOTTLE, 1)
                .EUt(12)
                .duration(30)
                .buildAndRegister();

        // the distillation are temporary
        DISTILLATION_RECIPES.recipeBuilder().EUt(120).duration(40)
                .fluidInputs(AppleExtract.getFluid(1000))
                .fluidOutputs(Biomass.getFluid(200))
                .fluidOutputs(AceticAcid.getFluid(800))
                .output(dust, Sugar)
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().EUt(120).duration(40)
                .fluidInputs(OrangeExtract.getFluid(1000))
                .fluidOutputs(Biomass.getFluid(300))
                .fluidOutputs(CitricAcid.getFluid(30))
                .fluidOutputs(Water.getFluid(700))
                .output(PLANT_BALL)
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().EUt(120).duration(40)
                .fluidInputs(LimeExtract.getFluid(1000))
                .fluidOutputs(Biomass.getFluid(300))
                .fluidOutputs(CitricAcid.getFluid(100))
                .fluidOutputs(Water.getFluid(600))
                .output(PLANT_BALL)
                .buildAndRegister();

        DISTILLATION_RECIPES.recipeBuilder().EUt(120).duration(40)
                .fluidInputs(LemonExtract.getFluid(1000))
                .fluidOutputs(Biomass.getFluid(300))
                .fluidOutputs(CitricAcid.getFluid(100))
                .fluidOutputs(Water.getFluid(600))
                .output(PLANT_BALL)
                .buildAndRegister();
    }

    public static void bakingOvenRecipes() {
        // Adobe Bricks for the Baking Oven
        ModHandler.addShapedRecipe("mud_bricks1", MUD_BRICK.getStackForm(5),
                "SCS", "SMS", "GCG",
                'C', Items.CLAY_BALL,
                'S', Blocks.SAND,
                'G', Blocks.GRAVEL,
                'M', MetaItems.WOODEN_FORM_BRICK);

        ModHandler.addShapedRecipe("mud_bricks2", MUD_BRICK.getStackForm(10),
                "SBS", "SMS", "GTG",
                'S', Blocks.SAND,
                'G', Blocks.GRAVEL,
                'B', OreDictUnifier.get(dust, Bentonite),
                'T', OreDictUnifier.get(dust, Talc),
                'M', MetaItems.WOODEN_FORM_BRICK);

        ModHandler.addShapedRecipe("mud_bricks3", MUD_BRICK.getStackForm(8),
                "SCS", "SMW", "GCG",
                'C', Items.CLAY_BALL,
                'S', Blocks.SAND,
                'G', Blocks.GRAVEL,
                'W', Items.WHEAT,
                'M', MetaItems.WOODEN_FORM_BRICK);

        ModHandler.addShapedRecipe("mud_bricks4", MUD_BRICK.getStackForm(16),
                "SBS", "SMW", "GTG",
                'S', Blocks.SAND,
                'G', Blocks.GRAVEL,
                'B', OreDictUnifier.get(dust, Bentonite),
                'T', OreDictUnifier.get(dust, Talc),
                'W', Items.WHEAT,
                'M', MetaItems.WOODEN_FORM_BRICK);

        ModHandler.addSmeltingRecipe(MUD_BRICK.getStackForm(), ADOBE_BRICK.getStackForm());
        ModHandler.addShapedRecipe("casing_adobe_bricks", GTFOMetaBlocks.GTFO_CASING.getItemVariant(ADOBE_BRICKS, 1), "XX", "XX", 'X', ADOBE_BRICK);
    }

    public static void slicingRecipes() {
        Map<MetaItem<?>.MetaValueItem, MetaItem<?>.MetaValueItem> slicingArray = new HashMap<>();
        slicingArray.put(CUCUMBER, CUCUMBER_SLICE);
        slicingArray.put(OLIVE, OLIVE_SLICE);
        slicingArray.put(TOMATO, TOMATO_SLICE);
        slicingArray.put(ONION, ONION_SLICE);
        for (Map.Entry<MetaItem<?>.MetaValueItem, MetaItem<?>.MetaValueItem> entry : slicingArray.entrySet()) {
            ModHandler.addShapelessRecipe("gtfo_slice_" + entry.getKey().toString(), entry.getValue().getStackForm(4), 'k', entry.getKey());
            SLICER_RECIPES.recipeBuilder().EUt(18).duration(30)
                    .inputs(entry.getKey().getStackForm())
                    .notConsumable(SLICER_BLADE_FLAT.getStackForm())
                    .outputs(entry.getValue().getStackForm(8))
                    .buildAndRegister();

            // TODO: Since we already have our crops, we might as well register their green house recipes here
            //RecipeUtils.addGreenHouseRecipes(entry.getKey().getStackForm(), entry.getKey());
        }
        // Do The Minecraft fruit slicing
        {
            ModHandler.addShapelessRecipe("gtfo_slice_carrot", CARROT_SLICE.getStackForm(4), 'k', Items.CARROT);
            ModHandler.addShapelessRecipe("gtfo_slice_apple", APPLE_SLICE.getStackForm(4), 'k', Items.APPLE);
            SLICER_RECIPES.recipeBuilder().EUt(18).duration(30)
                    .input(Items.CARROT)
                    .notConsumable(SLICER_BLADE_FLAT.getStackForm())
                    .outputs(CARROT_SLICE.getStackForm(8))
                    .buildAndRegister();
            SLICER_RECIPES.recipeBuilder().EUt(18).duration(30)
                    .input(Items.APPLE)
                    .notConsumable(SLICER_BLADE_OCTAGONAL.getStackForm())
                    .outputs(APPLE_SLICE.getStackForm(8))
                    .buildAndRegister();
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

        GTFOUtils.addBakingOvenRecipes(UNCOOKED_BACON.getStackForm(), BACON.getStackForm(), 500, 435, 1);
    }


    public static void airRecipes() {
        MIXER_RECIPES.recipeBuilder().duration(60).EUt(32)
                .fluidInputs(Air.getFluid(900))
                .fluidInputs(Steam.getFluid(10))
                .fluidOutputs(GTFOMaterialHandler.MoistAir.getFluid(1000))
                .buildAndRegister();

        VACUUM_RECIPES.recipeBuilder().duration(110).EUt(32)
                .fluidInputs(GTFOMaterialHandler.MoistAir.getFluid(4000))
                .fluidOutputs(GTFOMaterialHandler.ColdMoistAir.getFluid(4000))
                .buildAndRegister();
    }

    // Chum
    public static void chum() {
        MIXER_RECIPES.recipeBuilder().duration(100).EUt(24)
                .fluidInputs(GTFOMaterialHandler.Sludge.getFluid(100))
                .inputs(ROTTEN_FISH.getStackForm(1))
                .inputs(new ItemStack(Blocks.RED_MUSHROOM, 1), new ItemStack(Items.POISONOUS_POTATO, 1), new ItemStack(Items.FERMENTED_SPIDER_EYE, 1))
                .outputs(CHUM.getStackForm(3))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().duration(100).EUt(24)
                .fluidInputs(GTFOMaterialHandler.Sludge.getFluid(100))
                .fluidInputs(PurpleDrink.getFluid(100))
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
                .fluidInputs(PurpleDrink.getFluid(100))
                .inputs(ROTTEN_MEAT.getStackForm(1))
                .inputs(new ItemStack(Blocks.RED_MUSHROOM, 1), new ItemStack(Items.POISONOUS_POTATO, 1), new ItemStack(Items.FERMENTED_SPIDER_EYE, 1))
                .outputs(CHUM.getStackForm(6))
                .buildAndRegister();

        for (ItemStack stack : GTFOUtils.getFish()) {
            FERMENTING_RECIPES.recipeBuilder().duration(100).EUt(8)
                    .inputs(stack)
                    .outputs(ROTTEN_FISH.getStackForm())
                    .fluidInputs(Water.getFluid(100))
                    .fluidOutputs(Water.getFluid(100))
                    .buildAndRegister();
        }
        for (ItemStack stack : GTFOUtils.getMeat()) {
            FERMENTING_RECIPES.recipeBuilder().duration(100).EUt(8)
                    .inputs(stack)
                    .outputs(ROTTEN_MEAT.getStackForm())
                    .fluidInputs(Water.getFluid(100))
                    .fluidOutputs(Water.getFluid(100))
                    .buildAndRegister();
        }
        for (ItemStack stack : GTFOUtils.getAnimalProducts()) {
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
            /*MIXER_RECIPES.recipeBuilder().duration(125).EUt(16) TODO
                    .inputs(stack)
                    .fluidInputs(PiranhaSolution.getFluid(100))
                    .fluidOutputs(GTFOMaterialHandler.Sludge.getFluid(400))
                    .buildAndRegister();*/
        }


        ModHandler.addShapelessRecipe("chum_on_a_stick", CHUM_ON_A_STICK.getStackForm(), CHUM.getStackForm(), new ItemStack(Items.STICK));
        ASSEMBLER_RECIPES.recipeBuilder().EUt(4).duration(5)
                .input(Items.STICK)
                .inputs(CHUM.getStackForm())
                .outputs(CHUM_ON_A_STICK.getStackForm())
                .buildAndRegister();
    }

    public static void meatAndFat() {
        GTFOUtils.getMeat().forEach(itemStack -> {
            itemStack.setCount(16);
            CENTRIFUGE_RECIPES.recipeBuilder().EUt(60).duration(100)
                    .inputs(itemStack)
                    .output(dust, Meat, 8)
                    .output(dustSmall, Bone, 8)
                    .chancedOutput(Fat.getItemStack(4), 5000, 2000)
                    .chancedOutput(Fat.getItemStack(4), 5000, 2000)
                    .notConsumable(new IntCircuitIngredient(0))
                    .buildAndRegister();
            LARGE_CHEMICAL_RECIPES.recipeBuilder().EUt(256).duration(1000)
                    .input(itemStack.getItem(), 32)
                    .fluidInputs(Methanol.getFluid(8000), Chloroform.getFluid(8000))
                    .output(dust, Meat, 20)
                    .output(dustSmall, Bone, 20)
                    .fluidOutputs(Stearin.getFluid(4000), Sludge.getFluid(4000))
                    .buildAndRegister();
        });

        LARGE_CHEMICAL_RECIPES.recipeBuilder().EUt(256).duration(1000)
                .input(SCRAP_MEAT, 32)
                .fluidInputs(Methanol.getFluid(8000), Chloroform.getFluid(8000))
                .output(dust, Meat, 25)
                .output(dustSmall, Bone, 20)
                .fluidOutputs(Stearin.getFluid(6000), Sludge.getFluid(8000))
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().EUt(60).duration(80)
                .input(SCRAP_MEAT, 8)
                .output(dust, Meat, 4)
                .output(dustSmall, Bone, 8)
                .chancedOutput(Fat.getItemStack(2), 7000, 2000)
                .chancedOutput(Fat.getItemStack(2), 6000, 2000)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder().EUt(30).duration(300)
                .input(dust, Meat, 3)
                .fluidOutputs(Biomass.getFluid(200), Stearin.getFluid(100))
                .buildAndRegister();

        FERMENTING_RECIPES.recipeBuilder().EUt(32).duration(1200)
                .input(SCRAP_MEAT, 1)
                .fluidInputs(Chloroform.getFluid(100))
                .chancedOutput(dust, Meat, 1, 2000, 10)
                .fluidOutputs(Stearin.getFluid(100))
                .buildAndRegister();

        GTFOUtils.getOrganicOils().forEach(f -> {
            // turning Plant Oil into Animal Oil?! Magik!
            CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(300)
                    .input(dustTiny, SodaAsh)
                    .fluidInputs(new FluidStack(f, 1000), Hydrogen.getFluid(1000))
                    .fluidOutputs(Stearin.getFluid(1000))
                    .notConsumable(new IntCircuitIngredient(0))
                    .buildAndRegister();
        });

        Arrays.asList(Methanol, Ethanol).forEach(f -> {
            CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(600)
                    .input(dustTiny, SodiumHydroxide)
                    .fluidInputs(Stearin.getFluid(6000), f.getFluid(1000))
                    .fluidOutputs(Glycerol.getFluid(1000), BioDiesel.getFluid(6000))
                    .buildAndRegister();
            LARGE_CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(5400)
                    .input(dust, SodiumHydroxide)
                    .fluidInputs(Stearin.getFluid(54000), f.getFluid(9000))
                    .fluidOutputs(Glycerol.getFluid(9000), BioDiesel.getFluid(54000))
                    .buildAndRegister();
        });

        EXTRACTOR_RECIPES.recipeBuilder().EUt(16).duration(10)
                .inputs(Fat.getItemStack())
                .fluidOutputs(Stearin.getFluid(100))
                .buildAndRegister();

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(16).duration(60)
                .notConsumable(MetaItems.SHAPE_MOLD_INGOT)
                .fluidInputs(Stearin.getFluid(100))
                .outputs(Fat.getItemStack())
                .buildAndRegister();

        CHEMICAL_RECIPES.recipeBuilder().EUt(120).duration(40)
                .fluidInputs(Stearin.getFluid(1000), Water.getFluid(2000))
                .input(dust, SodiumHydroxide, 3)
                .fluidOutputs(SodiumStearate.getFluid(3000), Glycerol.getFluid(1000))
                .buildAndRegister();

        //"Stearic acid is used along with simple sugar or corn syrup as a hardener in candies. In fireworks, stearic acid is often used to coat metal powders such as aluminium and iron. This prevents oxidation, allowing compositions to be stored for a longer period of time"
        DISTILLERY_RECIPES.recipeBuilder().EUt(32).duration(10)
                .fluidInputs(SodiumStearate.getFluid(100))
                .fluidOutputs(StearicAcid.getFluid(100))
                .output(dustTiny, SodiumHydroxide, 1)
                .circuitMeta(0)
                .buildAndRegister();

        /*FLUID_SOLIDFICATION_RECIPES.recipeBuilder().EUt(32).duration(100)
                .fluidInputs(StearicAcidSoap.getFluid(1000))
                .output(soap)
                .buildAndRegister();
                //TODO: Maybe do soaps with this :p (this works as a food addtive soap? with stearin atleast idk)
                */

        //TODO: make it refine to Fertilizer Later! & Biosolids
        LARGE_CHEMICAL_RECIPES.recipeBuilder().EUt(30).duration(800)
                .fluidInputs(Sludge.getFluid(24000))
                .fluidInputs(Bacteria.getFluid(1000))
                .fluidOutputs(Biomass.getFluid(10000))
                .fluidOutputs(Water.getFluid(14000))
                .fluidOutputs(SulfurDioxide.getFluid(8000))
                .fluidOutputs(Methane.getFluid(8000))
                .input(dust, Calcite, 3)
                .notConsumable(CARBON_MESH)
                .notConsumable(new IntCircuitIngredient(1))
                .buildAndRegister();
    }

    public static void misc() {
        CANNER_RECIPES.recipeBuilder()
                .inputs(BOTTLE_PURPLE_DRINK.getStackForm())
                .fluidOutputs(PurpleDrink.getFluid(500))
                .output(Items.GLASS_BOTTLE, 1)
                .EUt(30)
                .duration(20)
                .buildAndRegister();

        FERMENTING_RECIPES.recipeBuilder()
                .fluidInputs(Milk.getFluid(10000))
                .fluidOutputs(GTFOMaterialHandler.Butter.getFluid(9000))
                .EUt(15)
                .duration(1200)
                .buildAndRegister();

        CENTRIFUGE_RECIPES.recipeBuilder()
                .input(Items.EGG)
                .fluidOutputs(GTFOMaterialHandler.Albumen.getFluid(100))
                .fluidOutputs(GTFOMaterialHandler.Yolk.getFluid(100))
                .EUt(45)
                .duration(60)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder().EUt(4).duration(40)
                .input(Items.POTATO)
                .outputs(MashedPotato.getItemStack())
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().EUt(16).duration(90)
                .input(dust, Wheat)
                .input(dust, Meat)
                .fluidInputs(Water.getFluid(500))
                .outputs(GTFOMaterialHandler.ToughMeat.getItemStack(2))
                .buildAndRegister();

        ModHandler.addSmeltingRecipe(GTFOMaterialHandler.ToughMeat.getItemStack(), OreDictUnifier.get(ingot, Meat));

        EXTRUDER_RECIPES.recipeBuilder().EUt(28).duration(20)
                .input(dust, Meat)
                .notConsumable(MetaItems.SHAPE_EXTRUDER_INGOT)
                .output(ingot, Meat)
                .buildAndRegister();

        MACERATOR_RECIPES.recipeBuilder().EUt(4).duration(40)
                .inputs(SCRAP_MEAT.getStackForm())
                .output(dust, Meat)
                .buildAndRegister();
    }
}
