package gregtechfoodoption.gui.widgets;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.AbstractWidgetGroup;
import gregtech.api.gui.widgets.PhantomFluidWidget;
import gregtech.api.gui.widgets.PhantomSlotWidget;
import gregtech.api.util.Position;
import gregtechfoodoption.machines.multiblock.kitchen.FluidStackInfo;
import gregtechfoodoption.machines.multiblock.kitchen.ItemStackInfo;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.items.ItemStackHandler;

public class PhantomRecipeWidget extends AbstractWidgetGroup {
    private ItemStackHandler inputs = new ItemStackHandler(9);
    private boolean[] nonConsumableItems = new boolean[9];
    private FluidTank[] fluidInputs = new FluidTank[9];
    private boolean[] nonConsumableFluids = new boolean[9];
    private ItemStackHandler outputs = new ItemStackHandler(9);
    private FluidTank[] fluidOutputs = new FluidTank[9];

    public PhantomRecipeWidget(int x, int y) {
        super(new Position(x, y));
        for (int i = 0; i < this.inputs.getSlots(); ++i) {
            this.addWidget(new PhantomSlotWidget(this.inputs, i, this.getPosition().x + i * 18, this.getPosition().y)
                    .setClearSlotOnRightClick(false).setBackgroundTexture(GuiTextures.SLOT));
        }
        for (int i = 0; i < this.fluidInputs.length; ++i) {
            fluidInputs[i] = new FluidTank(99999);
            this.addWidget(new PhantomFluidWidget(this.getPosition().x + this.inputs.getSlots() * 18 + i * 18, this.getPosition().y, 18, 18, this.fluidInputs[i]) {
                @Override
                public void handleClientAction(int id, PacketBuffer buffer) {
                    // Doesn't clear, at least
                }
            });
        }
        for (int i = 0; i < this.outputs.getSlots(); ++i) {
            this.addWidget(new PhantomSlotWidget(this.outputs, i, this.getPosition().x + i * 18, this.getPosition().y + 18)
                    .setClearSlotOnRightClick(false).setBackgroundTexture(GuiTextures.SLOT));
        }
        for (int i = 0; i < this.fluidOutputs.length; ++i) {
            fluidOutputs[i] = new FluidTank(99999);
            this.addWidget(new PhantomFluidWidget(this.getPosition().x + this.outputs.getSlots() * 18 + i * 18, this.getPosition().y + 18, 18, 18, this.fluidOutputs[i]) {
                @Override
                public void handleClientAction(int id, PacketBuffer buffer) {

                }
            });
        }
    }



    public PhantomRecipeWidget(int x, int y, ItemStackInfo[] inputs, FluidStackInfo[] fluidInputs, ItemStack[] outputs, FluidStack[] fluidOutputs) {
        super(new Position(x, y));
        this.inputs = new ItemStackHandler(inputs.length);
        this.fluidInputs = new FluidTank[fluidInputs.length];
        this.nonConsumableItems = new boolean[inputs.length];
        this.nonConsumableFluids = new boolean[fluidInputs.length];

        this.outputs = new ItemStackHandler(outputs.length);
        this.fluidOutputs = new FluidTank[fluidOutputs.length];

        for (int i = 0; i < inputs.length; ++i) {
            this.inputs.setStackInSlot(i, inputs[i].itemStack);
            this.nonConsumableItems[i] = inputs[i].nonConsumable;
        }
        for (int i = 0; i < fluidInputs.length; ++i) {
            this.fluidInputs[i] = new FluidTank(fluidInputs[i].fluidStack, 64000);
            this.nonConsumableFluids[i] = fluidInputs[i].nonConsumable;
        }

        for (int i = 0; i < outputs.length; ++i) {
            this.outputs.setStackInSlot(i, outputs[i]);
        }
        for (int i = 0; i < fluidOutputs.length; ++i) {
            this.fluidOutputs[i] = new FluidTank(fluidOutputs[i], 64000);
        }
    }

    public void deserializeNBT(NBTTagCompound nbt) {
        this.clearAllWidgets();

        NBTTagCompound inputs = nbt.getCompoundTag("inputs");
        NBTTagCompound fluidInputs = nbt.getCompoundTag("fluidInputs");
        NBTTagCompound outputs = nbt.getCompoundTag("outputs");
        NBTTagCompound fluidOutputs = nbt.getCompoundTag("fluidOutputs");

        clearSlots();

        for (int i = 0; i < this.inputs.getSlots(); ++i) {
            this.inputs.setStackInSlot(i, new ItemStack(inputs.getCompoundTag("item" + i)));
            this.nonConsumableItems[i] = inputs.getBoolean("nonConsumable" + i);
            this.addWidget(new PhantomSlotWidget(this.inputs, i, this.getPosition().x + i * 18, this.getPosition().y)
                    .setClearSlotOnRightClick(false).setBackgroundTexture(GuiTextures.SLOT));
        }
        for (int i = 0; i < this.fluidInputs.length; ++i) {
            this.fluidInputs[i] = new FluidTank(0).readFromNBT(fluidInputs.getCompoundTag("fluid" + i));
            this.nonConsumableFluids[i] = fluidInputs.getBoolean("nonConsumable" + i);
            this.addWidget(new PhantomFluidWidget(this.getPosition().x + this.inputs.getSlots() * 18 + i * 18, this.getPosition().y, 18, 18, this.fluidInputs[i]) {
                @Override
                public void handleClientAction(int id, PacketBuffer buffer) {
                    // Doesn't clear, at least
                }
            });
        }

        for (int i = 0; i < this.outputs.getSlots(); ++i) {
            this.outputs.setStackInSlot(i, new ItemStack(outputs.getCompoundTag("item" + i)));
            this.addWidget(new PhantomSlotWidget(this.outputs, i, this.getPosition().x + i * 18, this.getPosition().y + 18)
                    .setClearSlotOnRightClick(false).setBackgroundTexture(GuiTextures.SLOT));
        }
        for (int i = 0; i < this.fluidOutputs.length; ++i) {
            this.fluidOutputs[i] = new FluidTank(0).readFromNBT(fluidOutputs.getCompoundTag("fluid" + i));
            this.addWidget(new PhantomFluidWidget(this.getPosition().x + this.outputs.getSlots() * 18 + i * 18, this.getPosition().y + 18, 18, 18, this.fluidOutputs[i]) {
                @Override
                public void handleClientAction(int id, PacketBuffer buffer) {

                }
            });
        }

    }

    public void clearSlots() {
        for (int i = 0; i < inputs.getSlots(); ++i) {
            inputs.setStackInSlot(i, ItemStack.EMPTY);
        }
        for (int i = 0; i < outputs.getSlots(); ++i) {
            outputs.setStackInSlot(i, ItemStack.EMPTY);
        }
        for (int i = 0; i < fluidInputs.length; ++i) {
            fluidInputs[i].setFluid(null);
        }
        for (int i = 0; i < fluidOutputs.length; ++i) {
            fluidOutputs[i].setFluid(null);
        }
        nonConsumableItems = new boolean[9];
        nonConsumableFluids = new boolean[9];
    }
}
