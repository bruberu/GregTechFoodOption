package gregtechfoodoption.gui.widgets;

import gregtech.api.gui.impl.ModularUIContainer;
import gregtech.api.gui.ingredient.IRecipeTransferHandlerWidget;
import gregtech.api.gui.widgets.*;
import gregtech.api.recipes.Recipe;
import gregtech.api.util.Position;
import gregtech.api.util.Size;
import gregtech.integration.jei.recipe.GTRecipeWrapper;
import gregtechfoodoption.client.GTFOGuiTextures;
import gregtechfoodoption.item.GTFOMetaItem;
import gregtechfoodoption.item.GTFOMetaItems;
import gregtechfoodoption.machines.multiblock.kitchen.FluidStackInfo;
import gregtechfoodoption.machines.multiblock.kitchen.ItemStackInfo;
import gregtechfoodoption.utils.GTFOLog;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.gui.recipes.RecipeLayout;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class KitchenRecipeWidget extends AbstractWidgetGroup implements IRecipeTransferHandlerWidget {

    private Function<Integer, NBTTagCompound> loadingFunction;
    private Consumer<NBTTagCompound> savingFunction;
    private int recipeCount;
    private int recipeShown;
    private PhantomRecipeWidget recipeWidget;
    private ClickButtonWidget leftArrowWidget;
    private ClickButtonWidget rightArrowWidget;
    private SimpleTextWidget recipeCountLabel;
    public KitchenRecipeWidget(int x, int y, int width, int height, int recipeCount, Consumer<NBTTagCompound> savingFunction, Function<Integer, NBTTagCompound> loadingFunction) {
        super(new Position(x, y), new Size(width, height));
        recipeWidget = new PhantomRecipeWidget(x, y);

        addWidget(recipeWidget);
        this.savingFunction = savingFunction;
        this.loadingFunction = loadingFunction;
        this.recipeCount = recipeCount;

        this.recipeWidget.deserializeNBT(loadingFunction.apply(0));

        recipeShown = 0;

        leftArrowWidget = new ClickButtonWidget(x, y + 100, 9, 9, "", (data) -> {
            recipeShown = Math.max(recipeShown - 1, 0);
            this.recipeWidget.deserializeNBT(loadingFunction.apply(recipeShown));
            this.detectAndSendChanges();
        }).setButtonTexture(GTFOGuiTextures.BUTTON_LEFT);
        addWidget(leftArrowWidget);
        rightArrowWidget = new ClickButtonWidget(x + width - 9, y + 100, 9, 9, "", (data) -> {
            recipeShown = Math.min(recipeShown + 1, recipeCount - 1);
            this.recipeWidget.deserializeNBT(loadingFunction.apply(recipeShown));
            this.detectAndSendChanges();
        }).setButtonTexture(GTFOGuiTextures.BUTTON_RIGHT);
        addWidget(rightArrowWidget);
        recipeCountLabel = new SimpleTextWidget(x + width / 2 - 20, y + 100, "", 0x666666, () -> "Recipe: " + (recipeShown + 1) + "/" + recipeCount);
        addWidget(recipeCountLabel);
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

    @Override
    public String transferRecipe(ModularUIContainer modularUIContainer, IRecipeLayout recipeLayout, EntityPlayer entityPlayer, boolean maxTransfer, boolean doTransfer) {
        Recipe recipe; // Only way to get the wrapper :P
        if (recipeLayout instanceof RecipeLayout) {
            IRecipeWrapper recipeWrapper = ObfuscationReflectionHelper.getPrivateValue(RecipeLayout.class, (RecipeLayout) recipeLayout, "recipeWrapper");
            if (recipeWrapper instanceof GTRecipeWrapper) {
                recipe = ((GTRecipeWrapper) recipeWrapper).getRecipe();
            } else {
                return "This only works on GTCEu recipes!";
            }
        } else {
            return "Uh I don't know how you got here!";
        }

        List<ItemStack> outputs = recipe.getOutputs(); // No need to worry about chanced outputs.

        boolean isGTFO = false;
        if (outputs.stream().anyMatch(itemStack -> itemStack.getItem() instanceof GTFOMetaItem || itemStack.getItem().equals(GTFOMetaItems.SHAPED_ITEM))) {
            isGTFO = true;
        }

        List<FluidStack> fluidOutputs = recipe.getFluidOutputs();
        if (fluidOutputs.stream().anyMatch(fluidStack -> fluidStack.getFluid().getName().startsWith("gtfo_"))) {
            isGTFO = true;
        }
        if (!isGTFO) {
            return "Kitchens only process GTFO recipes!";
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


        writeClientAction(2, buf -> {
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
            buf.writeCompoundTag(tag);
        });
        return null;
    }
}
