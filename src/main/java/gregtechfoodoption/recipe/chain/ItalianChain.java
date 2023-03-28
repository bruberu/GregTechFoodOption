package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.BlockCleanroomCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtechfoodoption.GTFOMaterialHandler;
import gregtechfoodoption.item.GTFOMetaItem;
import gregtechfoodoption.utils.GTFOUtils;
import net.minecraft.init.Items;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.dustSmall;
import static gregtechfoodoption.item.GTFOMetaItem.*;
import static gregtechfoodoption.recipe.GTFORecipeMaps.*;

public class ItalianChain {
    public static void init() {
        MIXER_RECIPES.recipeBuilder().EUt(24).duration(100)
                .inputs(GTFOMaterialHandler.ShreddedParmesan.getItemStack(), GTFOMetaItem.BASIL.getStackForm(3), GTFOMetaItem.GARLIC_CLOVE.getStackForm())
                .fluidInputs(GTFOMaterialHandler.OliveOil.getFluid(500))
                .fluidOutputs(GTFOMaterialHandler.Pesto.getFluid(500))
                .buildAndRegister();
        MULTICOOKER_RECIPES.recipeBuilder().EUt(24).duration(100)
                .inputs(GTFOMaterialHandler.Nutmeg.getItemStack())
                .input(dust, Wheat)
                .fluidInputs(GTFOMaterialHandler.Butter.getFluid(50), Materials.Milk.getFluid(450))
                .fluidOutputs(GTFOMaterialHandler.BechamelSauce.getFluid(500))
                .buildAndRegister();

        MIXER_RECIPES.recipeBuilder().EUt(8).duration(300)
                .input(dust, Materials.Sugar)
                .fluidInputs(AceticAcid.getFluid(1000))
                .fluidOutputs(GTFOMaterialHandler.Agrodolce.getFluid(1000))
                .buildAndRegister();


        CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(16).duration(400)
                .inputs(GTFOMetaItem.CORN_EAR.getStackForm(10))
                .fluidInputs(GTFOMaterialHandler.HeatedWater.getFluid(250))
                .fluidOutputs(GTFOMaterialHandler.Polenta.getFluid(250))
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder().EUt(16).duration(40)
                .inputs(GTFOMetaItem.CERAMIC_BOWL.getStackForm())
                .fluidInputs(GTFOMaterialHandler.Polenta.getFluid(250))
                .outputs(GTFOMetaItem.POLENTA.getStackForm())
                .buildAndRegister();

        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(24).duration(300)
                .circuitMeta(1)
                .inputs(GTFOMetaItem.CERAMIC_BOWL.getStackForm(), GTFOMetaItem.CARROT_SLICE.getStackForm(3), GTFOMetaItem.OLIVE_SLICE.getStackForm(3), GTFOMetaItem.EGGPLANT_SLICE.getStackForm(3))
                .fluidInputs(GTFOMaterialHandler.Agrodolce.getFluid(250), GTFOMaterialHandler.OliveOil.getFluid(250))
                .outputs(GTFOMetaItem.CAPONATA.getStackForm());

        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(24).duration(300)
                .circuitMeta(1)
                .inputs(GTFOMetaItem.TOMATO_SLICE.getStackForm(3), GTFOMetaItem.BUN.getStackForm(1), GTFOMetaItem.GARLIC_CLOVE.getStackForm(1))
                .input(dustSmall, Salt)
                .fluidInputs(GTFOMaterialHandler.OliveOil.getFluid(250))
                .outputs(GTFOMetaItem.BRUSCHETTA.getStackForm());

        MIXER_RECIPES.recipeBuilder().EUt(24).duration(40)
                .input(Items.PORKCHOP)
                .input(dustSmall, Salt)
                .inputs(GTFOMetaItem.GARLIC_CLOVE.getStackForm())
                .outputs(GTFOMetaItem.SEASONED_PORK.getStackForm())
                .buildAndRegister();

        GTFOUtils.addBakingOvenRecipes(GTFOMetaItem.SEASONED_PORK.getStackForm(), GTFOMetaItem.PORCHETTA.getStackForm(), 2000, 470, 4);
        SLICER_RECIPES.recipeBuilder().EUt(18).duration(30)
                .inputs(GTFOMetaItem.PORCHETTA.getStackForm())
                .notConsumable(GTFOMetaItem.SLICER_BLADE_FLAT.getStackForm())
                .outputs(GTFOMetaItem.PORCHETTA_SLICE.getStackForm(8))
                .buildAndRegister();
        ModHandler.addShapelessRecipe("gtfo_slice_porchetta", PORCHETTA_SLICE.getStackForm(4), 'k', PORCHETTA.getStackForm());

        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(64).duration(400)
                .inputs(GTFOMetaItem.DRIED_SPAGHETTI.getStackForm(), GTFOMetaItem.PLATE.getStackForm())
                .fluidInputs(GTFOMaterialHandler.TomatoSauce.getFluid(500))
                .outputs(GTFOMetaItem.SPAGHETTI_ALLASSASSINA.getStackForm())
                .buildAndRegister();

        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(64).duration(400)
                .inputs(GTFOMetaItem.RIGATONI.getStackForm(), GTFOMetaItem.PLATE.getStackForm(), MetaBlocks.CLEANROOM_CASING.getItemVariant(BlockCleanroomCasing.CasingType.FILTER_CASING))
                .fluidInputs(GTFOMaterialHandler.TomatoSauce.getFluid(500))
                .outputs(GTFOMetaItem.PASTA_ALLAMOGUS.getStackForm())
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(64).duration(400)
                .inputs(GTFOMetaItem.RIGATONI.getStackForm(), GTFOMetaItem.PLATE.getStackForm(), GTFOMetaItem.EGGPLANT_SLICE.getStackForm(3), GTFOMetaItem.BASIL.getStackForm())
                .fluidInputs(GTFOMaterialHandler.TomatoSauce.getFluid(200))
                .outputs(GTFOMetaItem.PASTA_ALLA_NORMA.getStackForm())
                .buildAndRegister();

        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(400)
                .inputs(GTFOMetaItem.RAW_LASAGNA.getStackForm(4), GTFOMetaItem.BAKING_TRAY.getStackForm(), GTFOMaterialHandler.ShreddedParmesan.getItemStack(3))
                .input(dust, Meat, 2)
                .fluidInputs(GTFOMaterialHandler.TomatoSauce.getFluid(500))
                .outputs(GTFOMetaItem.LASAGNA_NAPOLETANA_RAW.getStackForm())
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(400)
                .inputs(GTFOMetaItem.RAW_LASAGNA.getStackForm(4), GTFOMetaItem.BAKING_TRAY.getStackForm(), GTFOMetaItem.MOZZARELLA_BALL.getStackForm(1))
                .input(dust, Meat, 2)
                .fluidInputs(GTFOMaterialHandler.Pesto.getFluid(250), GTFOMaterialHandler.BechamelSauce.getFluid(250))
                .outputs(GTFOMetaItem.LASAGNA_PESTO_RAW.getStackForm())
                .buildAndRegister();
        CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(16).duration(400)
                .inputs(GTFOMetaItem.RAW_LASAGNA.getStackForm(4), GTFOMetaItem.BAKING_TRAY.getStackForm(), GTFOMetaItem.CHUM.getStackForm(2), GTFOMetaItem.CHEDDAR_SLICE.getStackForm(1))
                .fluidInputs(GTFOMaterialHandler.TomatoSauce.getFluid(200))
                .outputs(GTFOMetaItem.LASAGNA_CHUM_RAW.getStackForm())
                .buildAndRegister();

        GTFOUtils.addBakingOvenRecipes(GTFOMetaItem.LASAGNA_NAPOLETANA_RAW.getStackForm(), GTFOMetaItem.LASAGNA_NAPOLETANA_COOKED.getStackForm(), 1500, 465, 2);
        GTFOUtils.addBakingOvenRecipes(GTFOMetaItem.LASAGNA_PESTO_RAW.getStackForm(), GTFOMetaItem.LASAGNA_PESTO_COOKED.getStackForm(), 1750, 465, 2);
        GTFOUtils.addBakingOvenRecipes(GTFOMetaItem.LASAGNA_CHUM_RAW.getStackForm(), GTFOMetaItem.LASAGNA_CHUM_COOKED.getStackForm(), 2000, 1570, 3);

        CANNER_RECIPES.recipeBuilder().EUt(8).duration(80)
                .inputs(GTFOMetaItem.PLATE.getStackForm(), GTFOMetaItem.LASAGNA_NAPOLETANA_COOKED.getStackForm())
                .outputs(GTFOMetaItem.LASAGNA_NAPOLETANA.getStackForm(), GTFOMetaItem.BAKING_TRAY.getStackForm())
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder().EUt(8).duration(80)
                .inputs(GTFOMetaItem.PLATE.getStackForm(), GTFOMetaItem.LASAGNA_PESTO_COOKED.getStackForm())
                .outputs(GTFOMetaItem.LASAGNA_PESTO.getStackForm(), GTFOMetaItem.BAKING_TRAY.getStackForm())
                .buildAndRegister();
        CANNER_RECIPES.recipeBuilder().EUt(8).duration(80)
                .inputs(GTFOMetaItem.PLATE.getStackForm(), GTFOMetaItem.LASAGNA_CHUM_COOKED.getStackForm())
                .outputs(GTFOMetaItem.LASAGNA_CHUM.getStackForm(), GTFOMetaItem.BAKING_TRAY.getStackForm())
                .buildAndRegister();

        MULTICOOKER_RECIPES.recipeBuilder().EUt(16).duration(1000)
                .inputs(TORTELLINI.getStackForm(6), CERAMIC_BOWL.getStackForm())
                .fluidInputs(GTFOMaterialHandler.ChickenBroth.getFluid(1000))
                .outputs(TORTELLINI_IN_BRODO.getStackForm())
                .buildAndRegister();

    }
}
