package gregtechfoodoption.utils;

import gregtech.api.unification.material.properties.IMaterialProperty;
import gregtech.api.unification.material.properties.MaterialProperties;
import gregtech.api.unification.material.properties.PropertyKey;

public class GTFOFireSuppressantProperty implements IMaterialProperty {

    public static final PropertyKey<GTFOFireSuppressantProperty> FIRE_SUPPRESSANT = new PropertyKey<>(
            "fire_suppressant", GTFOFireSuppressantProperty.class);

    @Override
    public void verifyProperty(MaterialProperties properties) {}
}
