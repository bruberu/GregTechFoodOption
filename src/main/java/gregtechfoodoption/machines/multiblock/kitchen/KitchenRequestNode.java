package gregtechfoodoption.machines.multiblock.kitchen;

import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.metatileentity.WorkableTieredMetaTileEntity;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.ingredients.GTRecipeFluidInput;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.ingredients.GTRecipeItemInput;
import gregtech.api.util.GTUtility;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.ArrayList;
import java.util.List;

public class KitchenRequestNode {
    final Recipe recipe;
    final RecipeMap map;
    final KitchenLogic logic;

    BlockPos machineRunning;
    KitchenRequestState state = KitchenRequestState.NEEDS_PROCESSING;
    final List<GTRecipeInput> dependencies = new ArrayList<>();
    public KitchenRequestNode(Recipe recipe, RecipeMap map, KitchenLogic logic) {
        this.recipe = recipe;
        this.map = map;
        this.logic = logic;
    }

    public void checkDependencies(IItemHandlerModifiable inputs, IMultipleTankHandler fluidInputs) {
        if (this.state == KitchenRequestState.PROCESSING) {
            WorkableTieredMetaTileEntity machine = logic.getMachineAtPos(machineRunning);
            if (machine == null) {
                this.state = KitchenRequestState.NOT_RUNNABLE;
            } else if (machine.getRecipeLogic().getProgress() == 0) {
                this.state = KitchenRequestState.DONE;
            }
        }
        if (this.state == KitchenRequestState.NEEDS_PROCESSING || ((this.state == KitchenRequestState.AWAITING_RESULTS || this.state == KitchenRequestState.AWAITING_INGREDIENTS) && logic.wasNotified)) {
            dependencies.clear();
            List<GTRecipeItemInput> missingItems = missingItems(GTUtility.itemHandlerToList(inputs));
            List<GTRecipeFluidInput> missingFluids = missingFluids(GTUtility.fluidHandlerToList(fluidInputs));
            if (missingItems.isEmpty() && missingFluids.isEmpty()) {
                // Run our recipe!
                this.machineRunning = logic.findRun(recipe, map);
                if (machineRunning == null) {
                    this.state = KitchenRequestState.NOT_RUNNABLE;
                } else {
                    this.state = KitchenRequestState.RUNNABLE;
                }
            } else {
                dependencies.addAll(missingItems);
                dependencies.addAll(missingFluids);
                this.state = KitchenRequestState.AWAITING_RESULTS;
                for (GTRecipeItemInput item : missingItems) {
                    logic.setNodes(item);
                    List<KitchenRequestNode> nodes = logic.getNodes(item);
                    if (nodes == null || nodes.isEmpty()) { // No recipes for this item, so we can't run it until we get said ingredients
                        this.state = KitchenRequestState.AWAITING_INGREDIENTS;
                    }
                }
                for (GTRecipeFluidInput fluid : missingFluids) {
                    logic.setNodes(fluid);
                    List<KitchenRequestNode> nodes = logic.getNodes(fluid);
                    if (nodes == null || nodes.isEmpty()) {
                        this.state = KitchenRequestState.AWAITING_INGREDIENTS;
                    }
                }
            }
        } else if (this.state == KitchenRequestState.AWAITING_RESULTS) {
            // We look through our dependencies to see if they are runnable, processing, or awaiting ingredients themselves. If a dependency's recipes are all not runnable, immediately exit.
            boolean certainToRun = true; // Only activates when all dependencies have a running recipe
            for (GTRecipeInput dependency : dependencies) {
                boolean isRunnable = false;
                boolean certainOnDependency = false;
                for (KitchenRequestNode node : logic.leaves.get(dependency)) {
                    if (node.state == KitchenRequestState.NOT_RUNNABLE) {
                        continue;
                    }
                    isRunnable = true;
                    if (node.state == KitchenRequestState.RUNNABLE || node.state == KitchenRequestState.PROCESSING) {
                        certainOnDependency = true;
                        return;
                    }
                }
                if (!isRunnable) {
                    this.state = KitchenRequestState.NOT_RUNNABLE;
                    return;
                }
                if (!certainOnDependency) {
                    certainToRun = false;
                }
            }
            if (certainToRun) {
                this.state = KitchenRequestState.AWAITING_INGREDIENTS;
            }
        }
    }

    private List<GTRecipeItemInput> missingItems(List<ItemStack> inputs) {
        int[] itemAmountInSlot = new int[inputs.size()];
        int indexed = 0;

        List<GTRecipeInput> gtRecipeInputs = this.recipe.getInputs();
        List<GTRecipeItemInput> missing = new ArrayList<>();
        for (GTRecipeInput ingredient : gtRecipeInputs) {
            int ingredientAmount = ingredient.getAmount();
            for (int j = 0; j < inputs.size(); j++) {
                ItemStack inputStack = inputs.get(j);

                if (j == indexed) {
                    itemAmountInSlot[j] = inputStack.isEmpty() ? 0 : inputStack.getCount();
                    indexed++;
                }

                if (inputStack.isEmpty() || !ingredient.acceptsStack(inputStack))
                    continue;
                int itemAmountToConsume = Math.min(itemAmountInSlot[j], ingredientAmount);
                ingredientAmount -= itemAmountToConsume;
                if (!ingredient.isNonConsumable()) itemAmountInSlot[j] -= itemAmountToConsume;
                if (ingredientAmount == 0) break;
            }
            if (ingredientAmount > 0)
                missing.add(new GTRecipeItemInput(ingredient, ingredientAmount));
        }

        return missing;
    }

    private List<GTRecipeFluidInput> missingFluids(List<FluidStack> fluidInputs) {
        int[] fluidAmountInTank = new int[fluidInputs.size()];
        int indexed = 0;

        List<GTRecipeInput> gtRecipeInputs = this.recipe.getFluidInputs();
        List<GTRecipeFluidInput> missing = new ArrayList<>();
        for (GTRecipeInput fluid : gtRecipeInputs) {
            int fluidAmount = fluid.getAmount();
            for (int j = 0; j < fluidInputs.size(); j++) {
                FluidStack tankFluid = fluidInputs.get(j);

                if (j == indexed) {
                    indexed++;
                    fluidAmountInTank[j] = tankFluid == null ? 0 : tankFluid.amount;
                }

                if (tankFluid == null || !fluid.acceptsFluid(tankFluid))
                    continue;
                int fluidAmountToConsume = Math.min(fluidAmountInTank[j], fluidAmount);
                fluidAmount -= fluidAmountToConsume;
                if (!fluid.isNonConsumable()) fluidAmountInTank[j] -= fluidAmountToConsume;
                if (fluidAmount == 0) break;
            }
            if (fluidAmount > 0)
                missing.add(new GTRecipeFluidInput(fluid.getInputFluidStack(), fluidAmount));
        }

        return missing;
    }

    public enum KitchenRequestState {
        NEEDS_PROCESSING,
        AWAITING_INGREDIENTS,
        AWAITING_RESULTS,
        NOT_RUNNABLE,
        RUNNABLE,
        PROCESSING,
        DONE
    }
}
