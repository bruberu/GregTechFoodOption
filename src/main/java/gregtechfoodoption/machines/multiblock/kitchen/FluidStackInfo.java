package gregtechfoodoption.machines.multiblock.kitchen;

import net.minecraftforge.fluids.FluidStack;

public class FluidStackInfo {
    public FluidStack fluidStack;
    public boolean nonConsumable;

    public FluidStackInfo(FluidStack fluidStack, boolean nonConsumable) {
        this.fluidStack = fluidStack;
        this.nonConsumable = nonConsumable;
    }
}