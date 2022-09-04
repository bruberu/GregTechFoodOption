package gregtechfoodoption.recipe.chain;

import gregtech.api.unification.material.Materials;
import gregtech.api.util.GTUtility;
import gregtech.common.items.MetaItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static gregtechfoodoption.recipe.GTFORecipeMaps.GREENHOUSE_RECIPES;

public class GreenhouseChain {

    public static void init() {
        registerVanillaTreeRecipes(new ItemStack(Blocks.SAPLING, 1, 0), new ItemStack(Blocks.LOG, 1, 0),
                new ItemStack(Blocks.LEAVES, 1, 0), new ItemStack(Items.APPLE));
        registerVanillaTreeRecipes(new ItemStack(Blocks.SAPLING, 1, 1), new ItemStack(Blocks.LOG, 1, 1),
                new ItemStack(Blocks.LEAVES, 1, 1), new ItemStack(Items.STICK));
        registerVanillaLargeTreeRecipe(new ItemStack(Blocks.SAPLING, 1, 1), new ItemStack(Blocks.LOG, 1, 1),
                new ItemStack(Items.STICK));
        registerVanillaTreeRecipes(new ItemStack(Blocks.SAPLING, 1, 2), new ItemStack(Blocks.LOG, 1, 2),
                new ItemStack(Blocks.LEAVES, 1, 2), new ItemStack(Items.STICK));
        registerVanillaTreeRecipes(new ItemStack(Blocks.SAPLING, 1, 3), new ItemStack(Blocks.LOG, 1, 3),
                new ItemStack(Blocks.LEAVES, 1, 3), new ItemStack(Items.STICK));
        registerVanillaTreeRecipes(new ItemStack(Blocks.SAPLING, 1, 4), new ItemStack(Blocks.LOG2, 1, 0),
                new ItemStack(Blocks.LEAVES2, 1, 0), new ItemStack(Items.STICK));
        registerVanillaTreeRecipes(new ItemStack(Blocks.SAPLING, 1, 5), new ItemStack(Blocks.LOG2, 1, 1),
                new ItemStack(Blocks.LEAVES2, 1, 1), new ItemStack(Items.STICK));
        registerVanillaLargeTreeRecipe(new ItemStack(Blocks.SAPLING, 1, 5), new ItemStack(Blocks.LOG2, 1, 1),
                new ItemStack(Items.STICK));


    }

    public static void registerVanillaTreeRecipes(ItemStack sapling, ItemStack log, ItemStack leaves, ItemStack crop) {
        GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(2000)
                .inputs(sapling)
                .circuitMeta(0)
                .fluidInputs(Materials.Water.getFluid(10000))
                .outputs(GTUtility.copyAmount(6, log), crop, sapling)
                .chancedOutput(sapling, 2000, 1000)
                .buildAndRegister();
        GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(2000)
                .inputs(sapling)
                .circuitMeta(1)
                .fluidInputs(Materials.Water.getFluid(10000))
                .outputs(GTUtility.copyAmount(5, log), crop)
                .chancedOutput(sapling, 1000, 1000)
                .outputs(GTUtility.copyAmount(20, leaves))
                .buildAndRegister();
        GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(2000)
                .inputs(sapling)
                .circuitMeta(2)
                .fluidInputs(Materials.Water.getFluid(20000))
                .outputs(GTUtility.copyAmount(5, log))
                .chancedOutput(sapling, 8000, 200)
                .outputs(GTUtility.copyAmount(3, crop))
                .chancedOutput(GTUtility.copyAmount(2, crop), 4000, 500)
                .buildAndRegister();
    }

    public static void registerVanillaLargeTreeRecipe(ItemStack sapling, ItemStack log, ItemStack crop) {
        GREENHOUSE_RECIPES.recipeBuilder().EUt(60).duration(6000)
                .inputs(GTUtility.copyAmount(4, sapling), MetaItems.FERTILIZER.getStackForm(4))
                .circuitMeta(3)
                .fluidInputs(Materials.Water.getFluid(40000))
                .outputs(GTUtility.copyAmount(64, log))
                .outputs(GTUtility.copyAmount(6, sapling))
                .chancedOutput(GTUtility.copyAmount(4, sapling), 1000, 500)
                .outputs(GTUtility.copyAmount(4,  crop))
                .buildAndRegister();
    }
}
