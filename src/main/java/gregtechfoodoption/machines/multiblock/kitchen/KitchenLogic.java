package gregtechfoodoption.machines.multiblock.kitchen;

import gregtech.api.GregTechAPI;
import gregtech.api.capability.GregtechDataCodes;
import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.IControllable;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.metatileentity.MTETrait;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.WorkableTieredMetaTileEntity;
import gregtech.api.metatileentity.multiblock.IMaintenance;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.ingredients.GTRecipeFluidInput;
import gregtech.api.recipes.ingredients.GTRecipeInput;
import gregtech.api.recipes.ingredients.GTRecipeItemInput;
import gregtech.api.unification.material.Material;
import gregtech.api.util.GTTransferUtils;
import gregtech.api.util.GTUtility;
import gregtech.common.ConfigHolder;
import gregtechfoodoption.GTFOMaterialHandler;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.materials.CleanerProperty;
import gregtechfoodoption.materials.FertilizerProperty;
import gregtechfoodoption.utils.GTFOLog;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class KitchenLogic extends MTETrait implements IControllable {
    private final boolean hasMaintenance;
    private final Set<WorkableTieredMetaTileEntity> controlledMTEs = new HashSet<>();
    private final List<KitchenRequestNode> requestNodes = new ObjectArrayList<>();
    final HashMap<GTRecipeInput, List<KitchenRequestNode>> leaves = new HashMap<>();
    public String info = "";
    boolean wasNotified = true;
    boolean recheckOutputs = true;
    private boolean workingEnabled = true;
    private ItemStack resultItem;
     int dirtiness;
    public KitchenLogicState state;

    public KitchenLogic(MetaTileEntityKitchen controller) {
        super(controller);

        this.hasMaintenance = ConfigHolder.machines.enableMaintenance && ((IMaintenance) controller).hasMaintenanceMechanics();
    }

    public @NotNull MetaTileEntityKitchen getMetaTileEntity() {
        return (MetaTileEntityKitchen) super.getMetaTileEntity();
    }

    public void giveMetaTileEntity(WorkableTieredMetaTileEntity metaTileEntity) {
        controlledMTEs.add(metaTileEntity);
    }

    public void update() {
        if (FMLLaunchHandler.side() != Side.CLIENT || !isWorkingEnabled() || hasMaintenance && ((IMaintenance) getMetaTileEntity()).getNumMaintenanceProblems() > 5)
            return;

        KitchenLogicState previousState = state;
        controlledMTEs.removeIf(metaTileEntity -> !metaTileEntity.isValid());

        boolean consumedEnergy = this.getMetaTileEntity().drainEnergy(true);
        if (consumedEnergy) {
            this.getMetaTileEntity().drainEnergy(false);
            cleanSelf();
        }
        if (this.getMetaTileEntity().getOffsetTimer() % Math.max(4, operationSlowdown()) == 0) {
            // Check if order is fulfilled
            if (recheckOutputs || !getMetaTileEntity().getNotifiedItemOutputList().isEmpty()) {
                this.getMetaTileEntity().getNotifiedItemOutputList().clear();
                if (resultItem != null && this.checkOrder()) {
                    state = KitchenLogicState.ORDER_COMPLETE;
                } else {
                    state = KitchenLogicState.PROBABLY_FINE;
                }
                this.wasNotified = true;
            }
            if (this.state != KitchenLogicState.ORDER_COMPLETE && previousState != KitchenLogicState.ORDER_COMPLETE) {
                this.state = KitchenLogicState.PROBABLY_FINE; // The default.
                if (consumedEnergy) {
                    operate();
                }
            }
        }
        for (MetaTileEntity mte : controlledMTEs) {
            if (mte.getRecipeLogic().getProgress() > 0) {
                dirtiness += GTUtility.getFloorTierByVoltage(mte.getRecipeLogic().getProgress()) + 1;
            }
        }
        if (previousState != state) {
            this.writeCustomData(GTFOValues.UPDATE_KITCHEN_STATUS, buf -> buf.writeByte(state.ordinal()));
        }
        wasNotified = false;
    }

    private int operationSlowdown() {
        return 20 / this.getMetaTileEntity().getEnergyTier() + (int) Math.ceil(Math.log(dirtiness));
    }

    private boolean dirtinessChance() {
        return Math.random() * dirtiness < 10;
    }

    public void operate() {
        boolean areAnyRunning = false;
        for (MetaTileEntity mte : controlledMTEs) {
            if (mte.getRecipeLogic().getProgress() == 0) {
                areAnyRunning |= slurpInventory(mte.getExportItems());
                areAnyRunning |= slurpFluids(mte.getExportFluids());
            } else {
                areAnyRunning = true;
            }
        }

        if (!areAnyRunning && state == KitchenLogicState.PROBABLY_FINE) {
            state = KitchenLogicState.NO_INGREDIENTS; // Since nothing was actually happening in the machines and all the nodes have had their chance to make more progress, we can assume that we're out of ingredients
            // If the order's complete, no one cares.
        }

        if (!getMetaTileEntity().getNotifiedItemInputList().isEmpty() || !getMetaTileEntity().getNotifiedFluidInputList().isEmpty()) {
            this.getMetaTileEntity().getNotifiedItemInputList().clear();
            this.getMetaTileEntity().getNotifiedFluidInputList().clear();
            wasNotified = true;
        }

        handleNodes();
    }

    public void handleNodes() {
        NBTTagCompound data = getMetaTileEntity().getRecipeNBT();
        if (data != null && !data.isEmpty()) {
            resultItem = new ItemStack(data.getCompoundTag("finalresult"));
            if (!resultItem.isEmpty()) {
                GTRecipeInput resultItemIn = new GTRecipeItemInput(resultItem);
                setNodes(resultItemIn);
                for (int i = 0; i < requestNodes.size(); i++) {
                    KitchenRequestNode node = requestNodes.get(i);
                    node.checkDependencies(getMetaTileEntity().getInputInventory(), getMetaTileEntity().getInputFluidInventory());
                    if (node.state == KitchenRequestNode.KitchenRequestState.RUNNABLE) {
                        MetaTileEntity mte = getMachineAtPos(node.machineRunning);
                        if (dirtinessChance()) {
                            if (mte != null && mte.getRecipeLogic().getProgress() == 0) {
                                if (getMachineAtPos(node.machineRunning).getRecipeLogic().prepareRecipe(node.recipe, getMetaTileEntity().getInputInventory(), getMetaTileEntity().getInputFluidInventory())) {
                                    node.state = KitchenRequestNode.KitchenRequestState.PROCESSING;
                                } else {
                                    this.state = KitchenLogicState.MACHINES_NOT_WORKING;
                                }
                            }
                        } else {
                            this.state = KitchenLogicState.PROBABLY_FINE; // At least one of the nodes can run, so we're not in the no-ingredients state
                        }
                    }
                }
                List<KitchenRequestNode> finalNodes = getNodes(resultItemIn);
                boolean canRunAny = false;
                for (KitchenRequestNode node : finalNodes) {
                    if (node.state != KitchenRequestNode.KitchenRequestState.NOT_RUNNABLE) {
                        canRunAny = true;
                        break;
                    }
                }
                if (!canRunAny) { // All of the nodes ended up not having the right machine.
                    state = KitchenLogicState.BAD_MACHINES;
                }
                return;
            }
        }
        // The kitchen recipe was invalid or missing
        state = KitchenLogicState.NO_RECIPE;
        reset();
    }

    public void reset() {
        this.resultItem = null;
        requestNodes.clear();
        leaves.clear();
        recheckOutputs = true;
    }

    public void cleanSelf() {
        for (IMultipleTankHandler.MultiFluidTankEntry tank : this.getMetaTileEntity().getInputFluidInventory().getFluidTanks()) {
            if (dirtiness <= 0)
                continue;
            FluidStack fluid = tank.getFluid();
            if (fluid == null || fluid.amount <= 0) {
                continue;
            }
            Material mat = GregTechAPI.materialManager.getMaterial(fluid.getFluid().getName());
            if (mat != null) {
                CleanerProperty property = mat.getProperty(GTFOMaterialHandler.CLEANER);
                if (property != null) {
                    tank.drain(1, true);
                    dirtiness -= property.getCleaningPower();
                }
            }
        }
    }

    private boolean checkOrder() {
        int countLeft = getMetaTileEntity().getOrderSize();

        for (int i = 0; i < getMetaTileEntity().getOutputInventory().getSlots(); i++) {
            ItemStack itemStack = getMetaTileEntity().getOutputInventory().getStackInSlot(i);
            if (!itemStack.isEmpty() && itemStack.isItemEqual(resultItem) && ItemStack.areItemStackTagsEqual(itemStack, resultItem)) {
                countLeft -= itemStack.getCount();
            }
            if (countLeft <= 0) {
                return true;
            }
        }
        return false;
    }

    public boolean slurpInventory(IItemHandler sourceInventory) {
        boolean didAnything = false;
        for (int srcIndex = 0; srcIndex < sourceInventory.getSlots(); ++srcIndex) {
            ItemStack sourceStack = sourceInventory.extractItem(srcIndex, Integer.MAX_VALUE, true);
            if (!sourceStack.isEmpty() && dirtinessChance()) {
                IItemHandlerModifiable inventory = getNodes(new GTRecipeItemInput(sourceStack)) == null || resultItem.isItemEqual(sourceStack) ?
                        getMetaTileEntity().getOutputInventory() : getMetaTileEntity().getInputInventory();
                ItemStack remainder = GTTransferUtils.insertItem(inventory, sourceStack, true);
                int amountToInsert = sourceStack.getCount() - remainder.getCount();
                if (remainder.getCount() > 0) {
                    this.state = KitchenLogicState.BUSES_FULL;
                }
                if (amountToInsert > 0) {
                    sourceStack = sourceInventory.extractItem(srcIndex, amountToInsert, false);
                    GTTransferUtils.insertItem(inventory, sourceStack, false);
                    didAnything = true;
                }
            }
        }
        return didAnything;
    }

    public boolean slurpFluids(@NotNull IFluidHandler sourceHandler) {
        boolean didAnything = false;
        for (IFluidTankProperties prop : sourceHandler.getTankProperties()) {
            FluidStack currentFluid = prop.getContents();
            if (currentFluid != null && currentFluid.amount != 0 && dirtinessChance()) {
                FluidStack fluidStack = sourceHandler.drain(currentFluid, false);
                if (fluidStack != null && fluidStack.amount != 0) {
                    IMultipleTankHandler handler = getNodes(new GTRecipeFluidInput(fluidStack)) == null ?
                            getMetaTileEntity().getOutputFluidInventory() : getMetaTileEntity().getInputFluidInventory();
                    int canInsertAmount = handler.fill(fluidStack, false);

                    if (canInsertAmount > 0) {
                        fluidStack.amount = canInsertAmount;
                        fluidStack = sourceHandler.drain(fluidStack, true);
                        if (fluidStack != null && fluidStack.amount > 0) {
                            handler.fill(fluidStack, true);
                        }
                        didAnything = true;
                    }
                }
                if (currentFluid.amount > 0) {
                    this.state = KitchenLogicState.HATCHES_FULL;
                }
            }
        }
        return didAnything;
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
    protected Collection<RecipeAndMap> getRecipesFor(GTRecipeItemInput s, @NotNull NBTTagCompound recipeData) {
        List<RecipeAndMap> recipes = new ArrayList<>();
        int count = recipeData.getInteger("recipecount");
        for (int i = 0; i < count; i++) {
            NBTTagCompound recipe = recipeData.getCompoundTag("recipe" + i);
            NBTTagCompound outputs = recipe.getCompoundTag("outputs");
            int outputSize = outputs.getInteger("size");
            for (int j = 0; j < outputSize; j++) {
                ItemStack stack = new ItemStack(outputs.getCompoundTag("item" + j));
                if (s.acceptsStack(stack)) {
                    RecipeMap map = RecipeMap.getByName(recipe.getString("map"));
                    Recipe actualRecipe = getRecipeFromMap(recipe, map);
                    if (actualRecipe != null) {
                        recipes.add(new RecipeAndMap(actualRecipe, map));
                    } else {
                        GTFOLog.logger.warn("A recipe for " + s + " had bad NBT! Maybe it doesn't exist anymore?");
                    }
                }
            }

        }
        return recipes;
    }

    protected Recipe getRecipeFromMap(NBTTagCompound tag, RecipeMap<?> map) {
        List<ItemStack> inputs = new ArrayList<>();
        List<FluidStack> fluidInputs = new ArrayList<>();
        NBTTagCompound inputsTag = tag.getCompoundTag("inputs");
        for (int i = 0; i < inputsTag.getInteger("size"); i++) {
            inputs.add(new ItemStack(inputsTag.getCompoundTag("item" + i)));
        }
        NBTTagCompound fluidInputsTag = tag.getCompoundTag("fluidInputs");
        for (int i = 0; i < fluidInputsTag.getInteger("size"); i++) {
            fluidInputs.add(FluidStack.loadFluidStackFromNBT(fluidInputsTag.getCompoundTag("fluid" + i)));
        }
        int eut = tag.getInteger("EUt");
        return map.findRecipe(eut, inputs, fluidInputs);
    }

    // Iterates through the recipes in recipeData to find ones that produce fluid s.
    protected Collection<RecipeAndMap> getRecipesFor(GTRecipeFluidInput s, @NotNull NBTTagCompound recipeData) {
        List<RecipeAndMap> recipes = new ArrayList<>();
        int count = recipeData.getInteger("recipecount");
        for (int i = 0; i < count; i++) {
            NBTTagCompound recipe = recipeData.getCompoundTag("recipe" + i);
            NBTTagCompound outputs = recipe.getCompoundTag("fluidOutputs");
            int outputSize = outputs.getInteger("size");
            for (int j = 0; j < outputSize; j++) {
                FluidStack stack = FluidStack.loadFluidStackFromNBT(outputs.getCompoundTag("fluid" + j));
                if (s.acceptsFluid(stack)) {
                    RecipeMap map = RecipeMap.getByName(recipe.getString("map"));
                    Recipe actualRecipe = getRecipeFromMap(recipe, map);
                    if (actualRecipe != null) {
                        recipes.add(new RecipeAndMap(actualRecipe, map));
                    } else {
                        GTFOLog.logger.warn("A recipe for " + s + " had bad NBT! Maybe it doesn't exist anymore?");
                    }
                }
            }

        }
        return recipes;
    }

    public void setNodes(GTRecipeInput sizedInput) {
        GTRecipeInput input = sizedInput.copyWithAmount(1);
        if (leaves.get(input) == null) {
            List<KitchenRequestNode> nodes = new ArrayList<>();
            Collection<RecipeAndMap> rs = input instanceof GTRecipeItemInput ? getRecipesFor((GTRecipeItemInput) input, getMetaTileEntity().getRecipeNBT()) : getRecipesFor((GTRecipeFluidInput) input, getMetaTileEntity().getRecipeNBT());
            for (RecipeAndMap recipeAndMap : rs) {
                KitchenRequestNode node = new KitchenRequestNode(recipeAndMap.recipe, recipeAndMap.map, this);
                this.requestNodes.add(node);
                nodes.add(node);
            }
            this.leaves.put(input, nodes);
            wasNotified = true;
        } else {
            for (KitchenRequestNode node : leaves.get(input)) {
                if (node.state != KitchenRequestNode.KitchenRequestState.NOT_RUNNABLE) {
                    node.state = KitchenRequestNode.KitchenRequestState.AWAITING_INGREDIENTS;
                }
            }
        }
    }

    public List<KitchenRequestNode> getNodes(GTRecipeInput sizedInput) {
        GTRecipeInput input = sizedInput.copyWithAmount(1);
        return leaves.get(input);
    }

    public WorkableTieredMetaTileEntity getMachineAtPos(BlockPos pos) {
        for (WorkableTieredMetaTileEntity mte : controlledMTEs) {
            if (mte.getPos().equals(pos)) {
                return mte;
            }
        }
        return null;
    }

    @Override
    public boolean isWorkingEnabled() {
        return this.workingEnabled;
    }

    @Override
    public void setWorkingEnabled(boolean b) {
        workingEnabled = b;
    }

    @Override
    public @NotNull String getName() {
        return "ExternalMachineRecipeLogic";
    }

    public void receiveCustomData(int dataId, @NotNull PacketBuffer buf) {
        if (dataId == GregtechDataCodes.WORKING_ENABLED) {
            this.workingEnabled = buf.readBoolean();
            this.getMetaTileEntity().scheduleRenderUpdate();
        } else if (dataId == GTFOValues.UPDATE_KITCHEN_STATUS) {
            this.state = KitchenLogicState.values()[buf.readByte()];
            this.getMetaTileEntity().scheduleRenderUpdate();
        }
    }

    @Override
    public @NotNull NBTTagCompound serializeNBT() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setBoolean("WorkEnabled", this.workingEnabled);
        tag.setInteger("Status", this.state.ordinal());
        tag.setInteger("Dirtiness", this.dirtiness);
        return tag;
    }

    @Override
    public void deserializeNBT(@NotNull NBTTagCompound compound) {
        this.workingEnabled = compound.getBoolean("WorkEnabled");
        this.state = KitchenLogicState.values()[compound.getInteger("Status")];
        this.dirtiness = compound.getInteger("Dirtiness");
    }

    @Override
    public void writeInitialSyncData(@NotNull PacketBuffer buf) {
        buf.writeBoolean(workingEnabled);
        buf.writeInt(dirtiness);
    }

    @Override
    public void receiveInitialSyncData(@NotNull PacketBuffer buf) {
        this.workingEnabled = buf.readBoolean();
        this.dirtiness = buf.readInt();
    }

    @Override
    public <T> T getCapability(Capability<T> capability) {
        if (capability == GregtechTileCapabilities.CAPABILITY_CONTROLLABLE) {
            return GregtechTileCapabilities.CAPABILITY_CONTROLLABLE.cast(this);
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

    public enum KitchenLogicState {
        NO_RECIPE,
        NO_INGREDIENTS,
        BAD_MACHINES,
        MACHINES_NOT_WORKING,
        PROBABLY_FINE,
        BUSES_FULL,
        HATCHES_FULL,
        ORDER_COMPLETE
    }
}
