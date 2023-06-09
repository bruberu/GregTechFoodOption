package gregtechfoodoption.materials;

import gregtech.api.unification.material.properties.IMaterialProperty;
import gregtech.api.unification.material.properties.MaterialProperties;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.util.FluidTooltipUtil;
import gregtech.api.util.LocalizationUtils;

public class LacingProperty implements IMaterialProperty {
    @Override
    public void verifyProperty(MaterialProperties properties) {
        properties.ensureSet(PropertyKey.FLUID, true);
        FluidTooltipUtil.registerTooltip(properties.getMaterial().getFluid(), LocalizationUtils.format("gregtechfoodoption.fluid.lacing"));
    }
}
