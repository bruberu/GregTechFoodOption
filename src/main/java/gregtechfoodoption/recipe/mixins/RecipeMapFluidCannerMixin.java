package gregtechfoodoption.recipe.mixins;

import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.ingredients.GTRecipeItemInput;
import gregtech.api.recipes.machines.RecipeMapFluidCanner;
import gregtechfoodoption.item.GTFOFoodStats;
import gregtechfoodoption.item.GTFOOredictItem;
import gregtechfoodoption.utils.GTFOLog;
import gregtechfoodoption.utils.GTFOUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.List;

import static gregtech.api.recipes.RecipeMaps.CANNER_RECIPES;

@SuppressWarnings("unused")

@Mixin(value = RecipeMapFluidCanner.class, priority = 500, remap = false)
public class RecipeMapFluidCannerMixin {

    @Inject(method = "findRecipe",
            at = /*@At(value = "JUMP",
                    opcode = Opcodes.GOTO,
                    ordinal = 0),*/
            @At(value = "RETURN", ordinal = 1),
            locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private void checkLacingRecipes(long voltage, List inputs, List fluidInputs, boolean exactVoltage, CallbackInfoReturnable<Recipe> cir, Recipe recipe, Iterator var7, ItemStack input, ItemStack inputStack, ItemStack fluidHandlerItemStack, IFluidHandlerItem fluidHandlerItem) {
        findLacingRecipe(inputs, fluidInputs, cir);
    }

    @Inject(method = "findRecipe",
            at = /*@At(value = "JUMP",
                    opcode = Opcodes.GOTO,
                    ordinal = 0),*/
            @At(value = "RETURN", ordinal = 4),
            locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private void checkLacingRecipes2(long voltage, List inputs, List fluidInputs, boolean exactVoltage, CallbackInfoReturnable<Recipe> cir, Recipe recipe) {
        findLacingRecipe(inputs, fluidInputs, cir);
    }

    public void findLacingRecipe(List<ItemStack> inputs, List<FluidStack> fluidInputs, CallbackInfoReturnable<Recipe> cir) {
        ItemStack inputStack = ItemStack.EMPTY;
        ItemStack lacingWith = ItemStack.EMPTY;
        ItemStack outputStack;
        FluidStack inputFluid;
        for (ItemStack input : inputs) {
            GTFOFoodStats stats = GTFOUtils.getGTFOFoodStats(input);
            if (stats != null) {
                // For pushing about
                inputStack = input.copy();
                inputStack.setCount(1);
                continue;
            }
            GTFOLog.logger.info(input.getItem());
            if (input.getItem() instanceof GTFOOredictItem) { // Hoping there's only one
                lacingWith = input.copy();
            }
        }
        if (inputStack.isEmpty()) {
            return;
        }
        FluidStack potentialFluid = fluidInputs.get(0);
        if (potentialFluid != null && potentialFluid.amount != 0) {
            inputFluid = potentialFluid.copy();
            if (inputFluid.amount < 100)
                return;
            inputFluid.amount = 100; // For use in the recipe; it's fine since it's a copy
            outputStack = inputStack.copy();
            outputStack.setCount(1);

            NBTTagCompound overallTag = outputStack.getTagCompound();
            if (outputStack.getTagCompound() == null)
                overallTag = new NBTTagCompound();

            NBTTagCompound gtfoStatsTag = overallTag.getCompoundTag("gtfoStats");

            switch (inputFluid.getUnlocalizedName()) {
/*                case "material.gtfo_hydrogen_cyanide": {
                    gtfoStatsTag.setBoolean("5dkcap/2/4/", true);
                    break;
                }*/
                default: {
                    return;
                }
            }
/*
            overallTag.setTag("gtfoStats", gtfoStatsTag);
            outputStack.setTagCompound(overallTag);
            cir.setReturnValue(CANNER_RECIPES.recipeBuilder()
                    //we can reuse recipe as long as input container stack fully matches our one
                    .inputs(GTRecipeItemInput.getOrCreate(inputStack, 1))
                    .fluidInputs(inputFluid)
                    .outputs(outputStack)
                    .duration(Math.max(16, inputFluid.amount / 64)).EUt(4)
                    .build().getResult());
*/
        }

        if (!lacingWith.isEmpty()) {
            outputStack = inputStack.copy();
            outputStack.setCount(1);
            GTFOLog.logger.info("hello");

            NBTTagCompound overallTag = outputStack.getTagCompound();
            if (outputStack.getTagCompound() == null)
                overallTag = new NBTTagCompound();

            NBTTagCompound gtfoStatsTag = overallTag.getCompoundTag("gtfoStats");

            switch (lacingWith.getMetadata()) {
                case 1138:
                    gtfoStatsTag.setBoolean("5dkcap/2/4/", true);
                    GTFOLog.logger.info("applied");
                    break;
                default:
                    return;
            }
            overallTag.setTag("gtfoStats", gtfoStatsTag);
            outputStack.setTagCompound(overallTag);
            cir.setReturnValue(CANNER_RECIPES.recipeBuilder()
                    //we can reuse recipe as long as input container stack fully matches our one
                    .inputs(GTRecipeItemInput.getOrCreate(inputStack, 1), GTRecipeItemInput.getOrCreate(lacingWith, 1))
                    .outputs(outputStack)
                    .duration(16).EUt(4)
                    .build().getResult());
        }

    }
}
