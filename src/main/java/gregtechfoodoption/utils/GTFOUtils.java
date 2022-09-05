package gregtechfoodoption.utils;

import gregicality.science.api.recipes.GCYSRecipeMaps;
import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.util.RandomPotionEffect;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.item.GTFOFoodStats;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static gregtech.api.unification.material.Materials.*;
import static gregtech.api.unification.ore.OrePrefix.gem;
import static gregtechfoodoption.GTFOMaterialHandler.FryingOil;
import static gregtechfoodoption.GTFOMaterialHandler.OliveOil;
import static gregtechfoodoption.item.GTFOMetaItem.SKEWER;
import static gregtechfoodoption.recipe.GTFORecipeMaps.BAKING_OVEN_RECIPES;

//A not small amount of code from here was yoinked from other places. I'll give credit wherever I can!

public class GTFOUtils {
    /*
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

    public static void addBioReactorRecipes(Material growthMedium, MetaItem<?>.MetaValueItem culture, ItemStack organism, ItemStack organismSource) {
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

    public static void addBioReactorRecipes(Material growthMedium, MetaItem<?>.MetaValueItem culture, ItemStack organism, Material organismSource) {
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

     */

    public static int boolToInt(boolean boole) {
        return boole ? 1 : 0;
    }

    public static int average(float divisor, int... inputs) {
        int result = 0;
        for (int i : inputs) {
            result += i;
        }
        return (int) (result / divisor);
    }

    public static int averageRGB(float divisor, int... inputs) {
        int red = 0;
        int green = 0;
        int blue = 0;

        for (int input : inputs) {
            red += (input - (input % ((int) Math.pow(256, 2)))) >> 16 % 256; // Make sure to account for opacity
            green += ((input - (input % 256)) >> 8) % 256; // Removes the last chunk, shifts it out, and removes the first chunk
            blue += input % 256;
        }

        int result = (int) (blue / divisor);
        result += (int) (green / divisor) << 8;
        result += (int) (red / divisor) << 16;

        return result;
    }

    public static ItemStack wildcardize(ItemStack itemStack) {
        itemStack.setItemDamage(GTValues.W);
        return itemStack;
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
        for (ItemStack stack : removingItems) {
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

    public static List<Fluid> getOrganicOils() {
        return Arrays.asList(FishOil.getFluid(), SeedOil.getFluid(), OliveOil.getFluid(), FryingOil.getFluid());
    }

    public static RecipeMap<?> chemicalDehydratorProxy() {
        return Loader.isModLoaded(GTFOValues.MODID_GCYS) ? GCYSRecipeMaps.DRYER_RECIPES : RecipeMaps.CHEMICAL_RECIPES;
    }

    public static RecipeMap<?> stellarForgeProxy() {
        return RecipeMaps.FORMING_PRESS_RECIPES;
    }


    public static void addBakingOvenRecipes(ItemStack input, ItemStack output, int duration, int temperature, int fuelAmount) {
        BAKING_OVEN_RECIPES.recipeBuilder().duration(duration).temperature(temperature)
                .inputs(input)
                .input(gem, Charcoal, fuelAmount)
                .outputs(output)
                .buildAndRegister();
        BAKING_OVEN_RECIPES.recipeBuilder().duration(duration)
                .inputs(input)
                .input(gem, Coal, fuelAmount)
                .outputs(output)
                .buildAndRegister();
        BAKING_OVEN_RECIPES.recipeBuilder().duration(duration)
                .inputs(input)
                .input(gem, Coke, Math.max(fuelAmount / 2, 1))
                .outputs(output)
                .buildAndRegister();
    }

    public static GTFOFoodStats getKebabFood(int hunger, float sat) {
        return new GTFOFoodStats(hunger, sat, false, false, SKEWER.getStackForm(1)).setEatingDuration(12);
    }

    @SideOnly(Side.CLIENT)
    public static void addPotionTooltip(List<RandomPotionEffect> effects, List<String> list) {
        list.add(new TextComponentTranslation("gregtechfoodoption.tooltip.potion.header").getFormattedText());
        effects.forEach((effect) -> list.add(new TextComponentTranslation("gregtechfoodoption.tooltip.potion.each",
                new TextComponentTranslation(effect.effect.getEffectName()).getFormattedText(),
                new TextComponentTranslation("enchantment.level." + (effect.effect.getAmplifier() + 1)),
                effect.effect.getDuration(),
                100 - effect.chance).getFormattedText()));
    }

    public static int getFirstUnemptyItemSlot(IItemHandler handler, int startSlot) {
        for (int i = startSlot; i < handler.getSlots(); i++) {
            if (!handler.getStackInSlot(i).isEmpty())
                return i;
        }
        for (int i = 0; i < startSlot; i++) {
            if (!handler.getStackInSlot(i).isEmpty())
                return i;
        }
        return 0;
    }

    public static Vec3d getScaledFacingVec(EnumFacing facing, double scale) {
        Vec3i facingOrdinaryVec = facing.getDirectionVec();
        return new Vec3d(facingOrdinaryVec.getX(), facingOrdinaryVec.getY(), facingOrdinaryVec.getZ()).scale(scale);
    }

    public static BlockPos.MutableBlockPos copy(BlockPos pos) {
        return new BlockPos.MutableBlockPos(pos.toImmutable());
    }

    public static boolean isFull(IItemHandler handler) {
        for (int i = 0; i < handler.getSlots(); i++) {
            if (handler.getStackInSlot(i).getCount() != handler.getStackInSlot(i).getMaxStackSize())
                return false;
        }
        return true;
    }
}
