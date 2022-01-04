package gregtechfoodoption.fluid;

import gregtechfoodoption.material.GTFOFluidMaterial;
import gregtech.api.util.FluidTooltipUtil;
import gregtech.api.util.GTUtility;
import gregtech.common.MetaFluids;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.HashMap;
import java.util.Map;

public class GTFOMetaFluids {

    private static final Map<String, GTFOFluidMaterial> fluidToMaterialMappings = new HashMap<>();

    public static void init() {
        for (GTFOFluidMaterial fluidMat : GTFOFluidMaterial.GTFO_FLUIDS.values()) {
            Fluid fluid = new Fluid(fluidMat.name, MetaFluids.FLUID_NONE_TEXTURE, MetaFluids.FLUID_NONE_TEXTURE, GTUtility.convertRGBtoOpaqueRGBA_MC(fluidMat.rgb));
            fluid.setTemperature(fluidMat.temperature);
            if (!FluidRegistry.isFluidRegistered(fluid.getName())) {
                FluidRegistry.registerFluid(fluid);
                FluidTooltipUtil.registerTooltip(fluid, fluidMat.getFormula());
                FluidRegistry.addBucketForFluid(fluid);
                fluidMat.fluid = fluid;
            } else {
                if (!FluidRegistry.hasBucket(FluidRegistry.getFluid(fluid.getName()))) {
                    FluidRegistry.addBucketForFluid(fluid);
                }
                fluidMat.fluid = FluidRegistry.getFluid(fluid.getName());
            }
            fluidToMaterialMappings.put(fluidMat.fluid.getName(), fluidMat);
        }
    }
}
