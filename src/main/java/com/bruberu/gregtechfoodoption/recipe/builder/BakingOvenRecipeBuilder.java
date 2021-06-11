package com.bruberu.gregtechfoodoption.recipe.builder;

import com.bruberu.gregtechfoodoption.recipe.GTFORecipeMaps;
import com.bruberu.gregtechfoodoption.recipe.multiblock.BakingOvenRecipe;
import com.bruberu.gregtechfoodoption.utils.GTFOLog;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gregtech.api.GTValues;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.crafttweaker.CTRecipeBuilder;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.Material;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.util.EnumValidationResult;
import gregtech.api.util.ValidationResult;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.fml.common.Optional;
import stanhebben.zenscript.annotations.ZenMethod;

public class BakingOvenRecipeBuilder {
    private CountableIngredient input;
    private ItemStack output;

    private int duration = -1;
    private int fuelAmount = -1;
    private int temperature = -1;

    private BakingOvenRecipeBuilder() {
    }

    @ZenMethod
    public static BakingOvenRecipeBuilder start() {
        return new BakingOvenRecipeBuilder();
    }

    @ZenMethod
    public BakingOvenRecipeBuilder input(Ingredient input, int amount) {
        this.input = new CountableIngredient(input, amount);
        return this;
    }

    @ZenMethod
    public BakingOvenRecipeBuilder input(ItemStack itemStack) {
        this.input = CountableIngredient.from(itemStack);
        return this;
    }

    @ZenMethod
    public BakingOvenRecipeBuilder input(Item item) {
        return this.input(new ItemStack(item));
    }

    @ZenMethod
    public BakingOvenRecipeBuilder input(Item item, int count) {
        return this.input(new ItemStack(item, count));
    }

    @ZenMethod
    public BakingOvenRecipeBuilder input(OrePrefix orePrefix, Material material) {
        this.input = CountableIngredient.from(orePrefix, material);
        return this;
    }

    @ZenMethod
    public BakingOvenRecipeBuilder input(OrePrefix orePrefix, Material material, int amount) {
        this.input = CountableIngredient.from(orePrefix, material, amount);
        return this;
    }

    @ZenMethod
    public BakingOvenRecipeBuilder duration(int duration) {
        this.duration = duration;
        return this;
    }

    @ZenMethod
    public BakingOvenRecipeBuilder fuelAmount(int fuelAmount) {
        this.fuelAmount = fuelAmount;
        return this;
    }

    @ZenMethod
    public BakingOvenRecipeBuilder temperature(int temperature) {
        this.temperature = temperature;
        return this;
    }

    @ZenMethod
    public BakingOvenRecipeBuilder output(ItemStack output) {
        this.output = output;
        return this;
    }

    @ZenMethod
    public BakingOvenRecipeBuilder output(Item output) {
        return this.output(new ItemStack(output));
    }

    @ZenMethod
    public BakingOvenRecipeBuilder output(Item output, int count) {
        return this.output(new ItemStack(output, count));
    }

    @ZenMethod
    public BakingOvenRecipeBuilder output(OrePrefix orePrefix, Material material) {
        this.output = OreDictUnifier.get(orePrefix, material);
        return this;
    }

    public ValidationResult<BakingOvenRecipe> build() {
        return ValidationResult.newResult(validate(),
                new BakingOvenRecipe(input, output, duration, fuelAmount));
    }

    protected EnumValidationResult validate() {
        EnumValidationResult result = EnumValidationResult.VALID;

        if (input == null) {
            GTFOLog.logger.error("Input Ingredient cannot be null", new IllegalArgumentException());
            result = EnumValidationResult.INVALID;
        }

        if (output == null || output.isEmpty()) {
            GTFOLog.logger.error("Output ItemStack cannot be null or empty", new IllegalArgumentException());
            result = EnumValidationResult.INVALID;
        }

        if (fuelAmount <= 0) {
            GTFOLog.logger.error("FuelAmount cannot be less or equal to 0", new IllegalArgumentException());
            result = EnumValidationResult.INVALID;
        }
        if (duration <= 0) {
            GTFOLog.logger.error("Duration cannot be less or equal to 0", new IllegalArgumentException());
            result = EnumValidationResult.INVALID;
        }

        return result;
    }

    @ZenMethod
    public void buildAndRegister() {
        ValidationResult<BakingOvenRecipe> result = build();

        if (result.getType() == EnumValidationResult.VALID) {
            BakingOvenRecipe recipe = result.getResult();
            GTFORecipeMaps.BAKING_OVEN_RECIPES.add(recipe);

            // Attempt to convert into a new ElectricBakingOvenRecipe

            if (temperature != -1)
                GTFORecipeMaps.ELECTRIC_BAKING_OVEN_RECIPES.recipeBuilder()
                        .duration(duration)
                        .inputs(this.input)
                        .outputs(this.output)
                        .setTemp(temperature)
                        .buildAndRegister();
        }
    }

    @ZenMethod
    @Optional.Method(modid = GTValues.MODID_CT)
    public BakingOvenRecipeBuilder input(IIngredient ingredient) {
        return input(new CTRecipeBuilder.CraftTweakerIngredientWrapper(ingredient), ingredient.getAmount());
    }

    @ZenMethod
    @Optional.Method(modid = GTValues.MODID_CT)
    public BakingOvenRecipeBuilder output(IItemStack itemStack) {
        return output(CraftTweakerMC.getItemStack(itemStack));
    }
}
