package gregtechfoodoption.recipe.chain;

import codechicken.lib.util.ItemUtils;
import gregtech.api.unification.material.Materials;
import gregtech.common.items.MetaItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static gregtechfoodoption.recipe.GTFORecipeMaps.GREENHOUSE_RECIPES;

public class GreenhouseChain {

    public static void init() {
        GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(2000)
                .inputs(new ItemStack(Blocks.SAPLING, 1, 0))
                .circuitMeta(0)
                .fluidInputs(Materials.Water.getFluid(10000))
                .outputs(new ItemStack(Blocks.LOG, 6, 0))
                .output(Items.APPLE)
                .outputs(new ItemStack(Blocks.SAPLING, 1, 0))
                .chancedOutput(new ItemStack(Blocks.SAPLING, 1, 0), 2000, 1000)
                .buildAndRegister();
        GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(2000)
                .inputs(new ItemStack(Blocks.SAPLING, 1, 0))
                .circuitMeta(1)
                .fluidInputs(Materials.Water.getFluid(10000))
                .outputs(new ItemStack(Blocks.LOG, 5, 0))
                .output(Items.APPLE)
                .chancedOutput(new ItemStack(Blocks.SAPLING, 1, 0), 1000, 1000)
                .outputs(new ItemStack(Blocks.LEAVES, 20, 0))
                .buildAndRegister();
        GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(2000)
                .inputs(new ItemStack(Blocks.SAPLING, 1, 0))
                .circuitMeta(2)
                .fluidInputs(Materials.Water.getFluid(20000))
                .outputs(new ItemStack(Blocks.LOG, 5, 0))
                .chancedOutput(new ItemStack(Blocks.SAPLING, 1, 0), 8000, 200)
                .outputs(new ItemStack(Items.APPLE, 5, 0))
                .chancedOutput(new ItemStack(Items.APPLE, 2, 0), 4000, 500)
                .buildAndRegister();
        GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(6000)
                .inputs(new ItemStack(Blocks.SAPLING, 4, 0), MetaItems.FERTILIZER.getStackForm(4))
                .circuitMeta(3)
                .fluidInputs(Materials.Water.getFluid(40000))
                .outputs(new ItemStack(Blocks.LOG, 64, 0))
                .outputs(new ItemStack(Blocks.SAPLING, 6, 0))
                .chancedOutput(new ItemStack(Blocks.SAPLING, 4, 0), 1000, 500)
                .outputs(new ItemStack(Items.APPLE, 3, 0))
                .buildAndRegister();

        GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(2000)
                .inputs(new ItemStack(Blocks.SAPLING, 1, 0))
                .circuitMeta(0)
                .fluidInputs(Materials.Water.getFluid(10000))
                .outputs(new ItemStack(Blocks.LOG, 6, 0))
                .output(Items.APPLE)
                .outputs(new ItemStack(Blocks.SAPLING, 1, 0))
                .chancedOutput(new ItemStack(Blocks.SAPLING, 1, 0), 2000, 1000)
                .buildAndRegister();

        GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(2000)
                .inputs(new ItemStack(Blocks.SAPLING, 1, 1))
                .circuitMeta(1)
                .fluidInputs(Materials.Water.getFluid(10000))
                .outputs(new ItemStack(Blocks.LOG, 5, 1))
                .output(Items.APPLE)
                .chancedOutput(new ItemStack(Blocks.SAPLING, 1, 0), 1000, 1000)
                .outputs(new ItemStack(Blocks.LEAVES, 20, 1))
                .buildAndRegister();

        GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(2000)
                .inputs(new ItemStack(Blocks.SAPLING, 1, 0))
                .circuitMeta(2)
                .fluidInputs(Materials.Water.getFluid(20000))
                .outputs(new ItemStack(Blocks.LOG, 5, 0))
                .chancedOutput(new ItemStack(Blocks.SAPLING, 1, 0), 8000, 200)
                .outputs(new ItemStack(Items.APPLE, 5, 0))
                .chancedOutput(new ItemStack(Items.APPLE, 2, 0), 4000, 500)
                .buildAndRegister();

        GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(6000)
                .inputs(new ItemStack(Blocks.SAPLING, 4, 0), MetaItems.FERTILIZER.getStackForm(4))
                .circuitMeta(3)
                .fluidInputs(Materials.Water.getFluid(40000))
                .outputs(new ItemStack(Blocks.LOG, 64, 0))
                .outputs(new ItemStack(Blocks.SAPLING, 6, 0))
                .chancedOutput(new ItemStack(Blocks.SAPLING, 4, 0), 1000, 500)
                .outputs(new ItemStack(Items.APPLE, 3, 0))
                .buildAndRegister();
    }

    public static void registerVanillaTreeRecipes(ItemStack sapling, ItemStack log, ItemStack leaves, ItemStack crop) {


        GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(2000)
                .inputs(sapling)
                .circuitMeta(0)
                .fluidInputs(Materials.Water.getFluid(10000))
                .outputs(new ItemStack(Blocks.LOG, 6, 0))
                .output(Items.APPLE)
                .outputs(new ItemStack(Blocks.SAPLING, 1, 0))
                .chancedOutput(new ItemStack(Blocks.SAPLING, 1, 0), 2000, 1000)
                .buildAndRegister();
        GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(2000)
                .inputs(sapling)
                .circuitMeta(1)
                .fluidInputs(Materials.Water.getFluid(10000))
                .outputs(ItemUtils.copyStack())
                .output(Items.APPLE)
                .chancedOutput(new ItemStack(Blocks.SAPLING, 1, 0), 1000, 1000)
                .outputs(new ItemStack(Blocks.LEAVES, 20, 0))
                .buildAndRegister();
        GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(2000)
                .inputs(sapling)
                .circuitMeta(2)
                .fluidInputs(Materials.Water.getFluid(20000))
                .outputs(new ItemStack(Blocks.LOG, 5, 0))
                .chancedOutput(new ItemStack(Blocks.SAPLING, 1, 0), 8000, 200)
                .outputs(new ItemStack(Items.APPLE, 5, 0))
                .chancedOutput(new ItemStack(Items.APPLE, 2, 0), 4000, 500)
                .buildAndRegister();

    }

}
