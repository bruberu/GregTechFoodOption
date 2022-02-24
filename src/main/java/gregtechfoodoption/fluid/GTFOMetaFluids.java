package gregtechfoodoption.fluid;

import gregtech.api.fluids.MetaFluids;
import gregtech.api.util.FluidTooltipUtil;
import gregtech.api.util.GTUtility;
import gregtech.api.util.LocalizationUtils;
import gregtechfoodoption.material.GTFOFluidMaterial;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.ArrayList;
import java.util.List;

public class GTFOMetaFluids {

    public static void init() {
        for (GTFOFluidMaterial fluidMat : GTFOFluidMaterial.GTFO_FLUIDS.values()) {
            Fluid fluid = new Fluid(fluidMat.name, MetaFluids.AUTO_GENERATED_PLASMA_TEXTURE, MetaFluids.AUTO_GENERATED_PLASMA_TEXTURE, GTUtility.convertRGBtoOpaqueRGBA_MC(fluidMat.rgb));
            fluid.setTemperature(fluidMat.temperature);
            if (!FluidRegistry.isFluidRegistered(fluid.getName())) {
                FluidRegistry.registerFluid(fluid);

                // Fluid Tooltips
                List<String> tooltip = new ArrayList<>();
                if (!fluidMat.getFormula().isEmpty()) {
                    tooltip.add(TextFormatting.YELLOW + fluidMat.getFormula());
                }
                tooltip.add(LocalizationUtils.format("gregtech.fluid.temperature", fluidMat.temperature));
                tooltip.add(LocalizationUtils.format(fluid.isGaseous() ? "gregtech.fluid.state_gas" : "gregtech.fluid.state_liquid"));

                FluidTooltipUtil.registerTooltip(fluid, tooltip);
                FluidRegistry.addBucketForFluid(fluid);
                fluidMat.fluid = fluid;
            } else {
                if (!FluidRegistry.hasBucket(FluidRegistry.getFluid(fluid.getName()))) {
                    FluidRegistry.addBucketForFluid(fluid);
                }
                fluidMat.fluid = FluidRegistry.getFluid(fluid.getName());
            }
        }
    }
}
