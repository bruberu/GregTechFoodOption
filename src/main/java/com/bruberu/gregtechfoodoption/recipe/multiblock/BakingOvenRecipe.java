package com.bruberu.gregtechfoodoption.recipe.multiblock;

import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gregtech.api.GTValues;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.crafttweaker.InputIngredient;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;

@ZenClass("mods.gregtechfoodoption.recipe.BakingOvenRecipe")

public class BakingOvenRecipe {
    private final CountableIngredient input;
    private final ItemStack output;

    private final int duration;
    private final int fuelAmount;

    public BakingOvenRecipe(CountableIngredient input, ItemStack output, int duration, int fuelAmount) {
        this.input = input;
        this.output = output;
        this.duration = duration;
        this.fuelAmount = fuelAmount;
    }

    public CountableIngredient getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    @ZenGetter("duration")
    public int getDuration() {
        return duration;
    }

    @ZenGetter("fuelAmount")
    public int getFuelAmount() {
        return fuelAmount;
    }

    @ZenGetter("input")
    @Optional.Method(modid = GTValues.MODID_CT)
    public InputIngredient ctGetInput() {
        return new InputIngredient(getInput());
    }

    @ZenGetter("output")
    @Optional.Method(modid = GTValues.MODID_CT)
    public IItemStack ctGetOutput() {
        return CraftTweakerMC.getIItemStack(getOutput());
    }

}
