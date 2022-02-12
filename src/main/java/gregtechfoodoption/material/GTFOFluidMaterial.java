package gregtechfoodoption.material;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.HashMap;
import java.util.Map;

public class GTFOFluidMaterial extends GTFOMaterial {
    public static Map<String, GTFOFluidMaterial> GTFO_FLUIDS = new HashMap<>();
    public int temperature;
    public Fluid fluid;

    public GTFOFluidMaterial(String name, int rgb) {
        this(name, rgb, 300);
    }

    public GTFOFluidMaterial(String name, int rgb, String formula) {
        this(name, rgb, formula, 300);
    }

    public GTFOFluidMaterial(String name, int rgb, int temperature) {
        this(name, rgb, "", temperature);
    }

    public GTFOFluidMaterial(String name, int rgb, String formula, int temperature) {
        this.name = "gtfo_" + name; // To prevent conflicts
        this.rgb = rgb;
        this.temperature = temperature;
        GTFO_FLUIDS.put(name, this);
        chemicalFormula = calculateChemicalFormula(formula);
    }

    public FluidStack getFluid(int amount) {
        return new FluidStack(fluid, amount);
    }

}
