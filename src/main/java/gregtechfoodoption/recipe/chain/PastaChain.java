package gregtechfoodoption.recipe.chain;

import gregtech.api.recipes.ModHandler;
import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.GTFOMaterialHandler;
import gregtechfoodoption.recipe.GTFORecipeMaps;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static gregtech.api.recipes.RecipeMaps.*;
import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.dust;
import static gregtech.api.unification.ore.OrePrefix.plate;
import static gregtechfoodoption.item.GTFOMetaItem.*;

public class PastaChain {
    public static void init() {
        CENTRIFUGE_RECIPES.recipeBuilder().EUt(120).duration(30)
                .input(dust, Wheat, 1)
                .fluidInputs(Water.getFluid(16))
                .circuitMeta(4)
                .outputs(PREMIXED_PASTA_DOUGH.getStackForm())
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder().EUt(120).duration(600)
                .inputs(PREMIXED_PASTA_DOUGH.getStackForm())
                .fluidInputs(GTFOMaterialHandler.Egg.getFluid(400))
                .fluidInputs(Air.getFluid(600))
                .circuitMeta(1)
                .outputs(EGG_PASTA_DOUGH.getStackForm(2))
                .buildAndRegister();
        MIXER_RECIPES.recipeBuilder().EUt(30).duration(600)
                .inputs(PREMIXED_PASTA_DOUGH.getStackForm())
                .fluidInputs(Air.getFluid(600))
                .circuitMeta(2)
                .outputs(PASTA_DOUGH.getStackForm())
                .buildAndRegister();

        if (!GTFOConfig.gtfoChainsConfig.makeChainsHarder) {
            MIXER_RECIPES.recipeBuilder().EUt(30).duration(1200)
                    .input(dust, Wheat, 2)
                    .fluidInputs(GTFOMaterialHandler.Egg.getFluid(400))
                    .fluidInputs(Air.getFluid(600))
                    .circuitMeta(2)
                    .outputs(EGG_PASTA_DOUGH.getStackForm())
                    .buildAndRegister();
            MIXER_RECIPES.recipeBuilder().EUt(30).duration(1200)
                    .input(dust, Wheat, 1)
                    .fluidInputs(Water.getFluid(32))
                    .fluidInputs(Air.getFluid(1000))
                    .circuitMeta(5)
                    .outputs(PASTA_DOUGH.getStackForm())
                    .buildAndRegister();
        }
        BENDER_RECIPES.recipeBuilder().EUt(24).duration(100)
                .input(plate, Bronze, 4)
                .circuitMeta(4)
                .outputs(BLANK_PASTA_DIE.getStackForm())
                .buildAndRegister();
        ModHandler.addShapedRecipe("tagliatelle_pasta_die", TAGLIATELLE_PASTA_DIE.getStackForm(), "f  ", " S ", "   ", 'S', BLANK_PASTA_DIE.getStackForm());
        ModHandler.addShapedRecipe("spaghetti_pasta_die", SPAGHETTI_PASTA_DIE.getStackForm(), " f ", " S ", "   ", 'S', BLANK_PASTA_DIE.getStackForm());
        ModHandler.addShapedRecipe("lasagna_pasta_die", LASAGNA_PASTA_DIE.getStackForm(), "  f", " S ", "   ", 'S', BLANK_PASTA_DIE.getStackForm());
        ModHandler.addShapedRecipe("rigatoni_pasta_die", RIGATONI_PASTA_DIE.getStackForm(), "   ", "fS ", "   ", 'S', BLANK_PASTA_DIE.getStackForm());
        ModHandler.addShapedRecipe("ditalini_pasta_die", DITALINI_PASTA_DIE.getStackForm(), "   ", " Sf", "   ", 'S', BLANK_PASTA_DIE.getStackForm());

        EXTRUDER_RECIPES.recipeBuilder().EUt(16).duration(300)
                .inputs(EGG_PASTA_DOUGH.getStackForm())
                .notConsumable(TAGLIATELLE_PASTA_DIE.getStackForm())
                .outputs(RAW_TAGLIATELLE.getStackForm())
                .buildAndRegister();
        EXTRUDER_RECIPES.recipeBuilder().EUt(16).duration(300)
                .inputs(PASTA_DOUGH.getStackForm())
                .notConsumable(SPAGHETTI_PASTA_DIE.getStackForm())
                .outputs(RAW_SPAGHETTI.getStackForm())
                .buildAndRegister();
        EXTRUDER_RECIPES.recipeBuilder().EUt(16).duration(300)
                .inputs(PASTA_DOUGH.getStackForm())
                .notConsumable(LASAGNA_PASTA_DIE.getStackForm())
                .outputs(RAW_LASAGNA.getStackForm())
                .buildAndRegister();
        EXTRUDER_RECIPES.recipeBuilder().EUt(16).duration(300)
                .inputs(PASTA_DOUGH.getStackForm())
                .notConsumable(RIGATONI_PASTA_DIE.getStackForm())
                .outputs(RAW_RIGATONI.getStackForm())
                .buildAndRegister();
        EXTRUDER_RECIPES.recipeBuilder().EUt(16).duration(300)
                .inputs(PASTA_DOUGH.getStackForm())
                .notConsumable(DITALINI_PASTA_DIE.getStackForm())
                .outputs(RAW_DITALINI.getStackForm())
                .buildAndRegister();

        FURNACE_RECIPES.recipeBuilder().EUt(16).duration(200)
                .inputs(RAW_TAGLIATELLE.getStackForm())
                .outputs(DRIED_TAGLIATELLE.getStackForm())
                .buildAndRegister();
        FURNACE_RECIPES.recipeBuilder().EUt(16).duration(200)
                .inputs(RAW_SPAGHETTI.getStackForm())
                .outputs(DRIED_SPAGHETTI.getStackForm())
                .buildAndRegister();
        FURNACE_RECIPES.recipeBuilder().EUt(16).duration(200)
                .inputs(RAW_LASAGNA.getStackForm())
                .outputs(DRIED_LASAGNA.getStackForm())
                .buildAndRegister();
        FURNACE_RECIPES.recipeBuilder().EUt(16).duration(200)
                .inputs(RAW_RIGATONI.getStackForm())
                .outputs(DRIED_RIGATONI.getStackForm())
                .buildAndRegister();
        FURNACE_RECIPES.recipeBuilder().EUt(16).duration(200)
                .inputs(RAW_DITALINI.getStackForm())
                .outputs(DRIED_DITALINI.getStackForm())
                .buildAndRegister();
        
        CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(16).duration(600)
                .inputs(DRIED_TAGLIATELLE.getStackForm())
                .fluidInputs(Water.getFluid(1000))
                .outputs(TAGLIATELLE.getStackForm())
                .buildAndRegister();
        CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(16).duration(600)
                .inputs(DRIED_SPAGHETTI.getStackForm())
                .fluidInputs(Water.getFluid(1000))
                .outputs(SPAGHETTI.getStackForm())
                .buildAndRegister();
        CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(16).duration(600)
                .inputs(DRIED_RIGATONI.getStackForm())
                .fluidInputs(Water.getFluid(1000))
                .outputs(RIGATONI.getStackForm())
                .buildAndRegister();
        CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(16).duration(600)
                .inputs(DRIED_DITALINI.getStackForm())
                .fluidInputs(Water.getFluid(1000))
                .outputs(DITALINI.getStackForm())
                .buildAndRegister();

        if (!GTFOConfig.gtfoChainsConfig.makeChainsHarder) {
            CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(24).duration(1500)
                    .inputs(RAW_TAGLIATELLE.getStackForm())
                    .fluidInputs(GTFOMaterialHandler.HeatedWater.getFluid(1000))
                    .outputs(TAGLIATELLE.getStackForm())
                    .buildAndRegister();
            CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(24).duration(1500)
                    .inputs(RAW_SPAGHETTI.getStackForm())
                    .fluidInputs(GTFOMaterialHandler.HeatedWater.getFluid(1000))
                    .outputs(SPAGHETTI.getStackForm())
                    .buildAndRegister();
            CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(24).duration(1500)
                    .inputs(RAW_RIGATONI.getStackForm())
                    .fluidInputs(GTFOMaterialHandler.HeatedWater.getFluid(1000))
                    .outputs(RIGATONI.getStackForm())
                    .buildAndRegister();
            CHEMICAL_BATH_RECIPES.recipeBuilder().EUt(24).duration(1500)
                    .inputs(RAW_DITALINI.getStackForm())
                    .fluidInputs(Water.getFluid(1000))
                    .outputs(DITALINI.getStackForm())
                    .buildAndRegister();
        }

        GTFORecipeMaps.CUISINE_ASSEMBLER_RECIPES.recipeBuilder().EUt(24).duration(100)
                .inputs(RAW_LASAGNA.getStackForm(), GTFOMaterialHandler.ShreddedParmesan.getItemStack(), GTFOMaterialHandler.Nutmeg.getItemStack())
                .input(Items.PORKCHOP)
                .outputs(TORTELLINI.getStackForm(8))
                .buildAndRegister();
        GTFORecipeMaps.MULTICOOKER_RECIPES.recipeBuilder().EUt(24).duration(5000)
                .input(Items.CHICKEN, 1)
                .fluidInputs(Water.getFluid(8000))
                .output(Items.COOKED_CHICKEN, 1)
                .fluidOutputs(GTFOMaterialHandler.ChickenBroth.getFluid(8000))
                .buildAndRegister();
        GTFORecipeMaps.MULTICOOKER_RECIPES.recipeBuilder().EUt(24).duration(5000)
                .notConsumable(new ItemStack(Items.COOKED_CHICKEN))
                .fluidInputs(Water.getFluid(8000))
                .fluidOutputs(GTFOMaterialHandler.ChickenBroth.getFluid(8000))
                .buildAndRegister();
    }
}
