package gregtechfoodoption.machines.multiblock.kitchen;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.metatileentity.WorkableTieredMetaTileEntity;
import gregtech.api.metatileentity.multiblock.IMaintenance;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.GTRecipeFluidInput;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.ingredients.GTRecipeItemInput;
import gregtech.api.util.GTTransferUtils;
import gregtech.common.ConfigHolder;
import gregtechfoodoption.utils.GTFOLog;
import it.unimi.dsi.fastutil.Stack;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class KitchenLogic {
    private final MetaTileEntityKitchen controller;
    private final boolean hasMaintenance;
    private final Set<WorkableTieredMetaTileEntity> controlledMTEs = new HashSet<>();
    private final List<KitchenRequestNode> requestNodes = new ObjectArrayList<>();
    final HashMap<GTRecipeInput, List<KitchenRequestNode>> leaves = new HashMap<>();
    public String info = "";
    boolean wasNotified = false;

    public KitchenLogic(MetaTileEntityKitchen controller) {
        this.controller = controller;

        this.hasMaintenance = ConfigHolder.machines.enableMaintenance && ((IMaintenance) controller).hasMaintenanceMechanics();
    }

    public void giveMetaTileEntity(WorkableTieredMetaTileEntity metaTileEntity) {
        controlledMTEs.add(metaTileEntity);
    }

    public void updateLogic() {
        if (hasMaintenance && ((IMaintenance) controller).getNumMaintenanceProblems() > 5) return;

        if (!controller.getNotifiedItemInputList().isEmpty() || !controller.getNotifiedFluidInputList().isEmpty()) {
            this.controller.getNotifiedItemInputList().clear();
            this.controller.getNotifiedFluidInputList().clear();
            wasNotified = true;
        } else {
            wasNotified = false;
        }
        controlledMTEs.removeIf(metaTileEntity -> !metaTileEntity.isValid());
        for (MetaTileEntity mte : controlledMTEs) {
            if (mte.getRecipeLogic().getProgress() == 0) {
                slurpInventory(mte.getExportItems());
                slurpFluids(mte.getExportFluids());
            }
        }

        for (int i = 0; i < requestNodes.size(); i++) {
            KitchenRequestNode node = requestNodes.get(i);
            node.checkDependencies(controller.getImportItems(), controller.getImportFluids());
            if (node.state == KitchenRequestNode.KitchenRequestState.RUNNABLE) {
                MetaTileEntity mte = getMachineAtPos(node.machineRunning);
                if (mte != null && mte.getRecipeLogic().getProgress() == 0) {
                    if (getMachineAtPos(node.machineRunning).getRecipeLogic().prepareRecipe(node.recipe, controller.getImportItems(), controller.getImportFluids())) {
                        node.state = KitchenRequestNode.KitchenRequestState.PROCESSING;
                    } else {
                        GTFOLog.logger.warn("Could not prepare recipe " + node.recipe.toString());
                    }
                }
            }
        }
    }

    public void slurpInventory(IItemHandler sourceInventory) {
        for (int srcIndex = 0; srcIndex < sourceInventory.getSlots(); ++srcIndex) {
            ItemStack sourceStack = sourceInventory.extractItem(srcIndex, Integer.MAX_VALUE, true);
            if (!sourceStack.isEmpty()) {
                ItemStack remainder = GTTransferUtils.insertItem(controller.getInputInventory(), sourceStack, true);
                int amountToInsert = sourceStack.getCount() - remainder.getCount();
                if (amountToInsert > 0) {
                    sourceStack = sourceInventory.extractItem(srcIndex, amountToInsert, false);
                    GTTransferUtils.insertItem(controller.getInputInventory(), sourceStack, false);
                }
            }
        }
    }

    public void slurpFluids(@NotNull IFluidHandler sourceHandler) {
        for (IFluidTankProperties prop : sourceHandler.getTankProperties()) {
            FluidStack currentFluid = prop.getContents();
            if (currentFluid != null && currentFluid.amount != 0) {
                FluidStack fluidStack = sourceHandler.drain(currentFluid, false);
                if (fluidStack != null && fluidStack.amount != 0) {
                    int canInsertAmount = controller.getInputFluidInventory().fill(fluidStack, false);
                    if (canInsertAmount > 0) {
                        fluidStack.amount = canInsertAmount;
                        fluidStack = sourceHandler.drain(fluidStack, true);
                        if (fluidStack != null && fluidStack.amount > 0) {
                            controller.getInputFluidInventory().fill(fluidStack, true);
                        }
                    }
                }
            }
        }
    }

    public BlockPos findRun(Recipe r, RecipeMap map) {
        BlockPos result = null;
        for (WorkableTieredMetaTileEntity mte : controlledMTEs) {
            if ((mte.getRecipeLogic().getProgress() == 0 || result == null) && mte.getRecipeLogic().getRecipeMap().equals(map) && mte.getRecipeLogic().getMaxVoltage() >= r.getEUt()) {
                result = mte.getPos();
            }
        }
        return result;
    }

    // Iterates through the recipes in recipeData to find ones that produce stack s.
    public Collection<RecipeAndMap> getRecipesFor(GTRecipeItemInput s, @NotNull NBTTagCompound recipeData) {
        List<RecipeAndMap> recipes = new ArrayList<>();
        int count = recipeData.getInteger("recipecount");
        for (int i = 0; i < count; i++) {
            NBTTagCompound recipe = recipeData.getCompoundTag("recipe" + i);
            NBTTagCompound outputs = recipe.getCompoundTag("outputs");
            int outputSize = outputs.getInteger("size");
            for (int j = 0; j < outputSize; j++) {
                ItemStack stack = new ItemStack(outputs.getCompoundTag("item" + j));
                if (s.acceptsStack(stack)) {
                    int hash = recipe.getInteger("hash");
                    RecipeMap map = RecipeMap.getByName(recipe.getString("map"));
                    Optional<Recipe> actualRecipe = map.getRecipeList().stream().filter((r) -> r.hashCode() == hash).findFirst();
                    if (actualRecipe.isPresent()) {
                        recipes.add(new RecipeAndMap(actualRecipe.get(), map));
                    } else {
                        GTFOLog.logger.warn("A recipe for " + s + " had an incorrect hash! Maybe it doesn't exist anymore?");
                    }
                }
            }

        }
        return recipes;
    }

    // Iterates through the recipes in recipeData to find ones that produce fluid s.
    public Collection<RecipeAndMap> getRecipesFor(GTRecipeFluidInput s, @NotNull NBTTagCompound recipeData) {
        List<RecipeAndMap> recipes = new ArrayList<>();
        int count = recipeData.getInteger("recipecount");
        for (int i = 0; i < count; i++) {
            NBTTagCompound recipe = recipeData.getCompoundTag("recipe" + i);
            NBTTagCompound outputs = recipe.getCompoundTag("fluidOutputs");
            int outputSize = outputs.getInteger("size");
            for (int j = 0; j < outputSize; j++) {
                FluidStack stack = FluidStack.loadFluidStackFromNBT(outputs.getCompoundTag("fluid" + j));
                if (s.acceptsFluid(stack)) {
                    int hash = recipe.getInteger("hash");
                    RecipeMap map = RecipeMap.getByName(recipe.getString("map"));
                    Optional<Recipe> actualRecipe = map.getRecipeList().stream().filter((r) -> r.hashCode() == hash).findFirst();
                    if (actualRecipe.isPresent()) {
                        recipes.add(new RecipeAndMap(actualRecipe.get(), map));
                    } else {
                        GTFOLog.logger.warn("A recipe for " + s + " had an incorrect hash! Maybe it doesn't exist anymore?");
                    }
                }
            }

        }
        return recipes;
    }

    public void setNodes(GTRecipeInput input) {
        if (leaves.get(input) == null) {
            List<KitchenRequestNode> nodes = new ArrayList<>();
            Collection<RecipeAndMap> rs = input instanceof GTRecipeItemInput ? getRecipesFor((GTRecipeItemInput) input, controller.getRecipeNBT()) : getRecipesFor((GTRecipeFluidInput) input, controller.getRecipeNBT());
            for (RecipeAndMap recipeAndMap : rs) {
                KitchenRequestNode node = new KitchenRequestNode(recipeAndMap.recipe, recipeAndMap.map, this);
                this.requestNodes.add(node);
                nodes.add(node);
            }
            this.leaves.put(input, nodes);
        } else {
            for (KitchenRequestNode node : leaves.get(input)) {
                node.state = KitchenRequestNode.KitchenRequestState.AWAITING_INGREDIENTS;
            }
        }
    }

    public WorkableTieredMetaTileEntity getMachineAtPos(BlockPos pos) {
        for (WorkableTieredMetaTileEntity mte : controlledMTEs) {
            if (mte.getPos().equals(pos)) {
                return mte;
            }
        }
        return null;
    }

    private static class RecipeAndMap {
        Recipe recipe;
        RecipeMap map;

        public RecipeAndMap(Recipe recipe, RecipeMap map) {
            this.recipe = recipe;
            this.map = map;
        }
    }
}
