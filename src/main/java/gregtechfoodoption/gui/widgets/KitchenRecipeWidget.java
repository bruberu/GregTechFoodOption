package gregtechfoodoption.gui.widgets;

import gregtech.api.fluids.GTFluid;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.impl.ModularUIContainer;
import gregtech.api.gui.ingredient.IRecipeTransferHandlerWidget;
import gregtech.api.gui.widgets.*;
import gregtech.api.items.metaitem.FoodUseManager;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.recipes.Recipe;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.category.GTRecipeCategory;
import gregtech.api.unification.material.registry.MaterialRegistry;
import gregtech.api.util.Position;
import gregtech.api.util.Size;
import gregtech.integration.jei.recipe.GTRecipeWrapper;
import gregtechfoodoption.GTFOMaterialHandler;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.client.GTFOGuiTextures;
import gregtechfoodoption.integration.applecore.GTFOAppleCoreCompat;
import gregtechfoodoption.item.GTFOMetaItem;
import gregtechfoodoption.item.GTFOMetaItems;
import gregtechfoodoption.machines.multiblock.kitchen.FluidStackInfo;
import gregtechfoodoption.machines.multiblock.kitchen.ItemStackInfo;
import gregtechfoodoption.utils.GTFOLog;
import mezz.jei.api.gui.IGhostIngredientHandler;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.gui.recipes.RecipeCatalysts;
import mezz.jei.gui.recipes.RecipeLayout;
import mezz.jei.runtime.JeiHelpers;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.FoodStats;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class KitchenRecipeWidget extends AbstractWidgetGroup implements IRecipeTransferHandlerWidget {

    private Function<Integer, NBTTagCompound> loadingFunction;
    private Consumer<NBTTagCompound> savingFunction;
    private Consumer<NBTTagCompound> deletingFunction;
    private int recipeCount;
    private int recipeShown;
    private PhantomRecipeWidget recipeWidget;
    private ClickButtonWidget leftArrowWidget;
    private ClickButtonWidget rightArrowWidget;
    private ClickButtonWidget deleteButtonWidget;
    private SimpleTextWidget recipeCountLabel;
    private SlotWidget finalResultSlot;
    private ItemStackHandler finalResult = new ItemStackHandler(1) {
        @NotNull
        @Override
        public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
            if (isAcceptableItem(stack)) {
                return super.insertItem(slot, stack, simulate);
            }
            return stack;
        }

        @Override
        protected int getStackLimit(int slot, @NotNull ItemStack stack) {
            return 1;
        }

        @Override
        public int getSlotLimit(int slot) {
            return 1;
        }
    };

    private List<ItemStack> neededInputs = new ArrayList<>();
    private List<FluidStack> neededFluidInputs = new ArrayList<>();
    public KitchenRecipeWidget(int x, int y, int width, int height, int recipeCount,
                               Consumer<NBTTagCompound> savingFunction,
                               Function<Integer, NBTTagCompound> loadingFunction,
                               Consumer<ItemStack> resultItemConsumer,
                               Consumer<Integer> deletingFunction,
                               ItemStack finalResultStack) {
        super(new Position(x, y), new Size(width, height));
        if (finalResultStack != null) {
            finalResult.setStackInSlot(0, finalResultStack);
        }
        this.addWidget(new LabelWidget(x, y + 5, "Recipe for:"));
        finalResultSlot = new PhantomSlotWidget(finalResult, 0, x + 63, y) {
            @Override
            public List<IGhostIngredientHandler.Target<?>> getPhantomTargets(Object ingredient) {
                return Collections.emptyList();
            }
        }.setTooltipText("gregtechfoodoption.kitchen.recipe.warn");
        finalResultSlot.setChangeListener(() -> resultItemConsumer.accept(finalResult.getStackInSlot(0)));
        finalResultSlot.setBackgroundTexture(GuiTextures.SLOT);
        addWidget(finalResultSlot);
        recipeWidget = new PhantomRecipeWidget(x, y + 10);

        addWidget(recipeWidget);
        this.savingFunction = savingFunction;
        this.loadingFunction = loadingFunction;
        this.recipeCount = recipeCount;

        this.recipeWidget.deserializeNBT(loadingFunction.apply(0));

        recipeShown = 0;

        leftArrowWidget = new ClickButtonWidget(x, y + 120, 9, 9, "", (data) -> {
            setRecipeShown(Math.max(getRecipeShown() - 1, 0));
            this.recipeWidget.deserializeNBT(loadingFunction.apply(getRecipeShown()));
        }).setButtonTexture(GTFOGuiTextures.BUTTON_LEFT).setShouldClientCallback(true);
        addWidget(leftArrowWidget);
        rightArrowWidget = new ClickButtonWidget(x + width - 9, y + 120, 9, 9, "", (data) -> {
            setRecipeShown(Math.min(getRecipeShown() + 1, getRecipeCount() - 1));
            this.recipeWidget.deserializeNBT(loadingFunction.apply(getRecipeShown()));
        }).setButtonTexture(GTFOGuiTextures.BUTTON_RIGHT).setShouldClientCallback(true);
        addWidget(rightArrowWidget);
        recipeCountLabel = new SimpleTextWidget(x + width / 2, y + 120, "", 0x666666, () -> "Recipe: " + (getRecipeShown() + 1) + "/" + getRecipeCount(), true);
        addWidget(recipeCountLabel);
        deleteButtonWidget = new ClickButtonWidget(x + width - 50, y + 120, 9, 9, "", (data) -> {
            deletingFunction.accept(getRecipeShown());
            this.recipeWidget.deserializeNBT(loadingFunction.apply(getRecipeShown()));
        }).setButtonTexture(GuiTextures.BUTTON_CLEAR_GRID).setShouldClientCallback(true);
        addWidget(deleteButtonWidget);
        getNeededInputs();
        getNeededFluidInputs();
    }

    @Override
    public void handleClientAction(int id, PacketBuffer buf) {
        super.handleClientAction(id, buf);
        if (id == 2) { // Create NBT for the recipe
            try {
                NBTTagCompound tag = buf.readCompoundTag();
                this.recipeWidget.deserializeNBT(tag);
                savingFunction.accept(tag);
                recipeCount++;
                recipeShown = recipeCount - 1;
            } catch (IOException e) {
                GTFOLog.logger.warn("Failed to read recipe NBT for the kitchen", e);
            }
        }
    }

    protected void getNeededInputs() {
        neededInputs.clear();
        for (int i = 0; i < recipeCount; i++) {
            NBTTagCompound recipe = loadingFunction.apply(i);
            NBTTagCompound inputs = recipe.getCompoundTag("inputs");
            for (int item = 0; item < inputs.getInteger("size"); item++) {
                ItemStack stack = new ItemStack(inputs.getCompoundTag("item" + item));
                stack.setCount(1);
                neededInputs.add(stack);
            }
        }
        neededInputs.add(finalResult.getStackInSlot(0));
    }

    protected void getNeededFluidInputs() {
        neededFluidInputs.clear();
        for (int i = 0; i < recipeCount; i++) {
            NBTTagCompound recipe = loadingFunction.apply(i);
            NBTTagCompound fluidInputs = recipe.getCompoundTag("fluidInputs");
            for (int item = 0; item < fluidInputs.getInteger("size"); item++) {
                FluidStack stack = FluidStack.loadFluidStackFromNBT(fluidInputs.getCompoundTag("fluid" + item));
                neededFluidInputs.add(stack);
            }
        }
    }

    public int getRecipeCount() {
        return recipeCount;
    }

    public int getRecipeShown() {
        return recipeShown;
    }

    public void setRecipeShown(int recipeShown) {
        this.recipeShown = recipeShown;
    }

    @Override
    public String transferRecipe(ModularUIContainer modularUIContainer, IRecipeLayout recipeLayout, EntityPlayer entityPlayer, boolean maxTransfer, boolean doTransfer) {
        getNeededInputs();
        getNeededFluidInputs();
        Recipe recipe; // Only way to get the wrapper :P
        String map;
        if (recipeLayout instanceof RecipeLayout) {
            IRecipeWrapper recipeWrapper = ObfuscationReflectionHelper.getPrivateValue(RecipeLayout.class, (RecipeLayout) recipeLayout, "recipeWrapper");
            if (recipeWrapper instanceof GTRecipeWrapper) {
                recipe = ((GTRecipeWrapper) recipeWrapper).getRecipe();
                String uidString = recipeLayout.getRecipeCategory().getUid();
                map = GTRecipeCategory.getByName(uidString.substring(uidString.indexOf(":") + 1)).getRecipeMap().getUnlocalizedName();
            } else {
                return "This only works on GTCEu singleblock recipes!";
            }
        } else {
            return "Uh I don't know how you got here, but apparently you're not looking at a recipe?!";
        }

        List<ItemStack> outputs = recipe.getOutputs(); // No need to worry about chanced outputs.

        boolean isGTFO = false;
        boolean isUseful = false; // Will be true if the recipe actually has an item in it that is in the current recipe list.
        if (outputs.stream().anyMatch(this::isAcceptableItem)) {
            isGTFO = true;
        }
        if (outputs.parallelStream().anyMatch(itemStack -> this.neededInputs.stream().anyMatch(itemStack::isItemEqual)))
            isUseful = true;

        List<FluidStack> fluidOutputs = recipe.getFluidOutputs();
        if (fluidOutputs.stream().anyMatch(this::isAcceptableFluid)) {
            isGTFO = true;
        }
        if (fluidOutputs.parallelStream().anyMatch(fluidStack -> this.neededFluidInputs.stream().anyMatch(fluidStack1 -> fluidStack1.isFluidEqual(fluidStack))))
            isUseful = true;
        if (!isGTFO) {
            return "Kitchens only process food-related recipes!";
        }
        if (!isUseful) {
            return "This doesn't output anything used in the target item or another recipe!";
        }

        if (!doTransfer)
            return null;

        List<ItemStackInfo> inputs = new ArrayList<>();
        for (int i = 0; i < recipe.getInputs().size(); i++) {
            inputs.add(new ItemStackInfo(recipeLayout.getItemStacks().getGuiIngredients().get(i).getDisplayedIngredient(), recipe.getInputs().get(i).isNonConsumable()));
        }

        List<FluidStackInfo> fluidInputs = new ArrayList<>();
        for (int i = 0; i < recipe.getFluidInputs().size(); i++) {
            fluidInputs.add(new FluidStackInfo(recipeLayout.getFluidStacks().getGuiIngredients().get(i).getDisplayedIngredient(), recipe.getFluidInputs().get(i).isNonConsumable()));
        }

        NBTTagCompound tag = new NBTTagCompound();
        NBTTagCompound inputTag = new NBTTagCompound();
        inputTag.setInteger("size", inputs.size());

        for (int i = 0; i < inputs.size(); i++) {
            NBTTagCompound nbt = new NBTTagCompound();
            inputs.get(i).itemStack.writeToNBT(nbt);
            inputTag.setTag("item" + i, nbt);
            inputTag.setBoolean("nonConsumable" + i, inputs.get(i).nonConsumable);
        }

        NBTTagCompound fluidInputTag = new NBTTagCompound();
        fluidInputTag.setInteger("size", fluidInputs.size());

        for (int i = 0; i < fluidInputs.size(); i++) {
            NBTTagCompound nbt = new NBTTagCompound();
            fluidInputs.get(i).fluidStack.writeToNBT(nbt);
            fluidInputTag.setTag("fluid" + i, nbt);
            fluidInputTag.setBoolean("nonConsumable" + i, fluidInputs.get(i).nonConsumable);
        }

        NBTTagCompound outputsTag = new NBTTagCompound();
        outputsTag.setInteger("size", outputs.size());
        for (int i = 0; i < outputs.size(); i++) {
            NBTTagCompound nbt = new NBTTagCompound();
            outputs.get(i).writeToNBT(nbt);
            outputsTag.setTag("item" + i, nbt);
        }

        NBTTagCompound fluidOutputsTag = new NBTTagCompound();
        fluidOutputsTag.setInteger("size", fluidOutputs.size());
        for (int i = 0; i < fluidOutputs.size(); i++) {
            NBTTagCompound nbt = new NBTTagCompound();
            fluidOutputs.get(i).writeToNBT(nbt);
            fluidOutputsTag.setTag("fluid" + i, nbt);
        }

        tag.setTag("inputs", inputTag);
        tag.setTag("fluidInputs", fluidInputTag);
        tag.setTag("outputs", outputsTag);
        tag.setTag("fluidOutputs", fluidOutputsTag);
        tag.setLong("EUt", recipe.getEUt());

        tag.setString("map", map);
        this.recipeWidget.deserializeNBT(tag);
        savingFunction.accept(tag);
        recipeCount++;
        recipeShown = recipeCount - 1;

        writeClientAction(2, buf -> {
            buf.writeCompoundTag(tag);
        });
        return null;
    }

    private boolean isAcceptableItem(ItemStack stack) {
        return (stack.getItem() instanceof GTFOMetaItem && !((GTFOMetaItem) stack.getItem()).getItem(stack).isKitchenBlacklisted())
                || stack.getItem().equals(GTFOMetaItems.SHAPED_ITEM) && GTFOMetaItems.SHAPED_ITEM.isFoodRelated(stack)
                || stack.getItem() instanceof ItemFood
                || (stack.getItem() instanceof MetaItem<?> && ((MetaItem<?>) stack.getItem()).getItem(stack).getUseManager() instanceof FoodUseManager)
                || Loader.isModLoaded(GTFOValues.MODID_AP) && GTFOAppleCoreCompat.isAppleCoreEdible(stack);
    }

    private boolean isAcceptableFluid(FluidStack stack) {
        Fluid fluid = stack.getFluid();
        if (fluid instanceof GTFluid.GTMaterialFluid) {
            return GTFOMaterialHandler.isFoodRelated(((GTFluid.GTMaterialFluid) fluid).getMaterial());
        } else {
            return false;
        }
    }
}
