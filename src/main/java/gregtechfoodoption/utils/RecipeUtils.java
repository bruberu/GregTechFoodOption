package gregtechfoodoption.utils;

import gregicadditions.GAConfig;
import gregicadditions.recipes.GARecipeMaps;
import gregicadditions.utils.GALog;
import gregtech.api.GTValues;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.material.type.FluidMaterial;
import gregtech.api.unification.ore.OrePrefix;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static gregicadditions.GAMaterials.DepletedGrowthMedium;
import static gregicadditions.GAMaterials.OrganicFertilizer;
import static gregicadditions.item.GAMetaItems.CLEAN_CULTURE;
import static gregicadditions.item.GAMetaItems.CONTAMINATED_PETRI_DISH;
import static gregicadditions.recipes.GARecipeMaps.BIO_REACTOR_RECIPES;
import static gregicadditions.recipes.GARecipeMaps.GREEN_HOUSE_RECIPES;
import static gregtech.api.unification.material.Materials.Water;

//A not small amount of code from here was yoinked from other places. I'll give credit wherever I can!

public class RecipeUtils {
    public static void addGreenHouseRecipes(ItemStack seed, Item crop) {
        GREEN_HOUSE_RECIPES.recipeBuilder().duration(1000).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Water.getFluid(2000)).notConsumable(seed).outputs(new ItemStack(crop)).buildAndRegister();
        GREEN_HOUSE_RECIPES.recipeBuilder().duration(900).notConsumable(new IntCircuitIngredient(1)).inputs(new ItemStack(Items.DYE, 1, 15)).fluidInputs(Water.getFluid(2000)).notConsumable(seed).outputs(new ItemStack(crop, 2)).chancedOutput(seed, 100, 50).buildAndRegister();
        GREEN_HOUSE_RECIPES.recipeBuilder().duration(600).notConsumable(new IntCircuitIngredient(2)).input(OrePrefix.dust, OrganicFertilizer).fluidInputs(Water.getFluid(2000)).notConsumable(seed).outputs(new ItemStack(crop, 3)).chancedOutput(seed, 100, 50).buildAndRegister();
    }

    public static void addGreenHouseRecipes(ItemStack seed, MetaItem<?>.MetaValueItem crop) {
        GREEN_HOUSE_RECIPES.recipeBuilder().duration(1000).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Water.getFluid(2000)).notConsumable(seed).outputs(crop.getStackForm()).buildAndRegister();
        GREEN_HOUSE_RECIPES.recipeBuilder().duration(900).notConsumable(new IntCircuitIngredient(1)).inputs(new ItemStack(Items.DYE, 1, 15)).fluidInputs(Water.getFluid(2000)).notConsumable(seed).outputs(crop.getStackForm(2)).chancedOutput(seed, 100, 50).buildAndRegister();
        GREEN_HOUSE_RECIPES.recipeBuilder().duration(600).notConsumable(new IntCircuitIngredient(2)).input(OrePrefix.dust, OrganicFertilizer).fluidInputs(Water.getFluid(2000)).notConsumable(seed).outputs(crop.getStackForm(3)).chancedOutput(seed, 100, 50).buildAndRegister();
    }

    public static void addBioReactorRecipes(FluidMaterial growthMedium, MetaItem<?>.MetaValueItem culture, ItemStack organism, ItemStack organismSource)
    {
        ItemStack twoOrganism = organism.copy();
        twoOrganism.setCount(2);
        BIO_REACTOR_RECIPES.recipeBuilder().EUt(480).duration(2400)
                .inputs(CLEAN_CULTURE.getStackForm())
                .inputs(organismSource)
                .fluidInputs(growthMedium.getFluid(1000))
                .outputs(culture.getStackForm())
                .buildAndRegister();
        BIO_REACTOR_RECIPES.recipeBuilder().EUt(480).duration(200)
                .inputs(culture.getStackForm())
                .fluidInputs(growthMedium.getFluid(1000))
                .outputs(organism)
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .buildAndRegister();
        BIO_REACTOR_RECIPES.recipeBuilder().EUt(480).duration(50)
                .inputs(organism)
                .fluidInputs(growthMedium.getFluid(250))
                .outputs(twoOrganism)
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .buildAndRegister();
    }
    public static void addBioReactorRecipes(FluidMaterial growthMedium, MetaItem<?>.MetaValueItem culture, ItemStack organism, FluidMaterial organismSource)
    {
        ItemStack twoOrganism = organism.copy();
        twoOrganism.setCount(2);
        BIO_REACTOR_RECIPES.recipeBuilder().EUt(480).duration(2400)
                .inputs(CLEAN_CULTURE.getStackForm())
                .fluidInputs(organismSource.getFluid(1000))
                .fluidInputs(growthMedium.getFluid(1000))
                .outputs(culture.getStackForm())
                .buildAndRegister();
        BIO_REACTOR_RECIPES.recipeBuilder().EUt(480).duration(200)
                .inputs(culture.getStackForm())
                .fluidInputs(growthMedium.getFluid(1000))
                .outputs(organism)
                .outputs(CONTAMINATED_PETRI_DISH.getStackForm())
                .fluidOutputs(DepletedGrowthMedium.getFluid(1000))
                .buildAndRegister();
        BIO_REACTOR_RECIPES.recipeBuilder().EUt(480).duration(50)
                .inputs(organism)
                .fluidInputs(growthMedium.getFluid(250))
                .outputs(twoOrganism)
                .fluidOutputs(DepletedGrowthMedium.getFluid(250))
                .buildAndRegister();
    }

    public static int boolToInt(boolean boole)
    {
        return boole ? 1 : 0;
    }

    public static int average(float divisor, int... inputs)
    {
        int result = 0;
        for(int i : inputs)
        {
            result += i;
        }
        return result /= divisor;
    }

    public static ItemStack wildcardize(ItemStack itemStack)
    {
        itemStack.setItemDamage(GTValues.W);
        return itemStack;
    }

    public static RecipeMap<SimpleRecipeBuilder> clusterMillBuilderProxy() {
        return GAConfig.GT6.BendingFoilsAutomatic ? GARecipeMaps.CLUSTER_MILL_RECIPES : RecipeMaps.LATHE_RECIPES;
    }

    public static boolean removeAllSmelting(ItemStack input) {
        if (input.isEmpty()) {
            GTFOLog.logger.error("Cannot remove furnace recipe with empty input.");
            GTFOLog.logger.error("Stacktrace:", new IllegalArgumentException());
            RecipeMap.setFoundInvalidRecipe(true);
            return false;
        }
        List<ItemStack> removingItems = new ArrayList<>();
        for (ItemStack stack : FurnaceRecipes.instance().getSmeltingList().keySet()) {
            if (ItemStack.areItemsEqualIgnoreDurability(input, stack)) {
                removingItems.add(stack); // No CMEs today!
            }
        }
        for (ItemStack stack: removingItems) {
            FurnaceRecipes.instance().getSmeltingList().remove(stack);
        }
        return removingItems.isEmpty();
    }

    public static List<ItemStack> getFish() {
        return Arrays.asList(new ItemStack(Items.FISH), new ItemStack(Items.FISH, 1, 1), new ItemStack(Items.FISH, 1, 2));
    }

    public static List<ItemStack> getMeat() {
        return Arrays.asList(new ItemStack(Items.BEEF), new ItemStack(Items.CHICKEN), new ItemStack(Items.MUTTON), new ItemStack(Items.PORKCHOP), new ItemStack(Items.RABBIT));
    }

    public static List<ItemStack> getAnimalProducts() {
        List<ItemStack> result = new ArrayList<>();
        result.addAll(getMeat());
        result.addAll(getFish());
        return result;
    }
}
