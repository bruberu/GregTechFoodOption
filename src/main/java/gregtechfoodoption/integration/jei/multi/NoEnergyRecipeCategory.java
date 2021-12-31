package gregtechfoodoption.integration.jei.multi;

import gregtechfoodoption.GregTechFoodOption;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.gui.BlankUIHolder;
import gregtech.api.gui.IRenderContext;
import gregtech.api.gui.ModularUI;
import gregtech.api.gui.Widget;
import gregtech.api.gui.widgets.SlotWidget;
import gregtech.api.gui.widgets.TankWidget;
import gregtech.api.recipes.RecipeMap;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;


public class NoEnergyRecipeCategory implements IRecipeCategory<NoEnergyRecipeWrapper> {
    private final RecipeMap<?> recipeMap;
    private final ModularUI modularUI;
    private ItemStackHandler importItems, exportItems;
    private FluidTankList importFluids, exportFluids;
    private final IDrawable backgroundDrawable;

    public NoEnergyRecipeCategory(RecipeMap<?> recipeMap, IGuiHelper guiHelper) {
        this.recipeMap = recipeMap;
        FluidTank[] importFluidTanks = new FluidTank[recipeMap.getMaxFluidInputs()];
        for (int i = 0; i < importFluidTanks.length; i++)
            importFluidTanks[i] = new FluidTank(16000);
        FluidTank[] exportFluidTanks = new FluidTank[recipeMap.getMaxFluidOutputs()];
        for (int i = 0; i < exportFluidTanks.length; i++)
            exportFluidTanks[i] = new FluidTank(16000);
        this.modularUI = recipeMap.createJeiUITemplate(
                (importItems = new ItemStackHandler(recipeMap.getMaxInputs())),
                (exportItems = new ItemStackHandler(recipeMap.getMaxOutputs())),
                (importFluids = new FluidTankList(false, importFluidTanks)),
                (exportFluids = new FluidTankList(false, exportFluidTanks))
        ).build(new BlankUIHolder(), Minecraft.getMinecraft().player);
        this.modularUI.initWidgets();
        this.backgroundDrawable = guiHelper.createBlankDrawable(modularUI.getWidth(), modularUI.getHeight() * 2 / 3);
    }

    @Override
    public String getUid() {
        return GregTechFoodOption.MODID + ":" + recipeMap.unlocalizedName;
    }

    @Override
    public String getTitle() {
        return recipeMap.getLocalizedName();
    }

    @Override
    public String getModName() {
        return GregTechFoodOption.MODID;
    }

    @Override
    public IDrawable getBackground() {
        return backgroundDrawable;
    }

    public void setRecipe(IRecipeLayout recipeLayout, NoEnergyRecipeWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();
        IGuiFluidStackGroup fluidStackGroup = recipeLayout.getFluidStacks();
        for (Widget uiWidget : modularUI.guiWidgets.values()) {

            if (uiWidget instanceof SlotWidget) {
                SlotWidget slotWidget = (SlotWidget) uiWidget;
                if (!(slotWidget.getHandle() instanceof SlotItemHandler)) {
                    continue;
                }
                SlotItemHandler handle = (SlotItemHandler) slotWidget.getHandle();
                if (handle.getItemHandler() == importItems) {
                    //this is input item stack slot widget, so add it to item group
                    itemStackGroup.init(handle.getSlotIndex(), true,
                            slotWidget.getPosition().x,
                            slotWidget.getPosition().y);
                } else if (handle.getItemHandler() == exportItems) {
                    //this is output item stack slot widget, so add it to item group
                    itemStackGroup.init(importItems.getSlots() + handle.getSlotIndex(), false,
                            slotWidget.getPosition().x,
                            slotWidget.getPosition().y);
                }
            } else if (uiWidget instanceof TankWidget) {
                TankWidget tankWidget = (TankWidget) uiWidget;
                if (importFluids.getFluidTanks().contains(tankWidget.fluidTank)) {
                    int importIndex = importFluids.getFluidTanks().indexOf(tankWidget.fluidTank);
                    List<List<FluidStack>> inputsList = ingredients.getInputs(FluidStack.class);
                    int fluidAmount = 0;
                    if (inputsList.size() > importIndex && !inputsList.get(importIndex).isEmpty())
                        fluidAmount = inputsList.get(importIndex).get(0).amount;
                    //this is input tank widget, so add it to fluid group
                    fluidStackGroup.init(importIndex, true,
                            tankWidget.getPosition().x + tankWidget.fluidRenderOffset,
                            tankWidget.getPosition().y + tankWidget.fluidRenderOffset,
                            tankWidget.getSize().width - (2 * tankWidget.fluidRenderOffset),
                            tankWidget.getSize().height - (2 * tankWidget.fluidRenderOffset),
                            fluidAmount, false, null);

                } else if (exportFluids.getFluidTanks().contains(tankWidget.fluidTank)) {
                    int exportIndex = exportFluids.getFluidTanks().indexOf(tankWidget.fluidTank);
                    List<List<FluidStack>> inputsList = ingredients.getOutputs(FluidStack.class);
                    int fluidAmount = 0;
                    if (inputsList.size() > exportIndex && !inputsList.get(exportIndex).isEmpty())
                        fluidAmount = inputsList.get(exportIndex).get(0).amount;
                    //this is output tank widget, so add it to fluid group
                    fluidStackGroup.init(importFluids.getFluidTanks().size() + exportIndex, false,
                            tankWidget.getPosition().x + tankWidget.fluidRenderOffset,
                            tankWidget.getPosition().y + tankWidget.fluidRenderOffset,
                            tankWidget.getSize().width - (2 * tankWidget.fluidRenderOffset),
                            tankWidget.getSize().height - (2 * tankWidget.fluidRenderOffset),
                            fluidAmount, false, null);

                }
            }
        }
        itemStackGroup.addTooltipCallback(recipeWrapper::addTooltip);
        fluidStackGroup.addTooltipCallback(recipeWrapper::addTooltip);
        itemStackGroup.set(ingredients);
        fluidStackGroup.set(ingredients);
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        for (Widget widget : modularUI.guiWidgets.values()) {
            widget.drawInBackground(0, 0, new IRenderContext() {
            });
            widget.drawInForeground(0, 0);
        }
    }
}

