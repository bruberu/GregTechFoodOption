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
            this.addWidget(new PhantomFluidWidget(this.getPosition().x + i * 18, this.getPosition().y + 20, 18, 18, this.fluidInputs[i]) {
                @Override
                public void handleClientAction(int id, PacketBuffer buffer) {
                    // Doesn't clear, at least
                }
            });
        }
        for (int i = 0; i < this.outputs.getSlots(); ++i) {
            this.addWidget(new PhantomSlotWidget(this.outputs, i, this.getPosition().x + i * 18, this.getPosition().y + 40)
                    .setClearSlotOnRightClick(false).setBackgroundTexture(GuiTextures.SLOT));
        }
        for (int i = 0; i < this.fluidOutputs.length; ++i) {
            fluidOutputs[i] = new FluidTank(99999);
            this.addWidget(new PhantomFluidWidget(this.getPosition().x + i * 18, this.getPosition().y + 60, 18, 18, this.fluidOutputs[i]) {
                @Override
                public void handleClientAction(int id, PacketBuffer buffer) {

                }
            });
        }
    }

    public void deserializeNBT(NBTTagCompound nbt) {
        if (nbt == null)
            return;
        NBTTagCompound inputs = nbt.getCompoundTag("inputs");
        NBTTagCompound fluidInputs = nbt.getCompoundTag("fluidInputs");
        NBTTagCompound outputs = nbt.getCompoundTag("outputs");
        NBTTagCompound fluidOutputs = nbt.getCompoundTag("fluidOutputs");

        clearSlots();

        for (int i = 0; i < this.inputs.getSlots(); ++i) {
            this.inputs.setStackInSlot(i, new ItemStack(inputs.getCompoundTag("item" + i)));
            this.nonConsumableItems[i] = inputs.getBoolean("nonConsumable" + i);
        }
        for (int i = 0; i < this.fluidInputs.length; ++i) {
            this.fluidInputs[i] = new FluidTank(0).readFromNBT(fluidInputs.getCompoundTag("fluid" + i));
            this.nonConsumableFluids[i] = fluidInputs.getBoolean("nonConsumable" + i);
        }

        for (int i = 0; i < this.outputs.getSlots(); ++i) {
            this.outputs.setStackInSlot(i, new ItemStack(outputs.getCompoundTag("item" + i)));
        }
        for (int i = 0; i < this.fluidOutputs.length; ++i) {
            this.fluidOutputs[i] = new FluidTank(0).readFromNBT(fluidOutputs.getCompoundTag("fluid" + i));
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
