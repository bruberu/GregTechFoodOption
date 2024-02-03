package gregtechfoodoption.materials;

import gregtech.api.unification.material.properties.IMaterialProperty;
import gregtech.api.unification.material.properties.MaterialProperties;
import gregtech.api.unification.material.properties.PropertyKey;

public class CleanerProperty implements IMaterialProperty {

    private int cleaningPower;

    public CleanerProperty(int cleaningPower) {
        this.cleaningPower = cleaningPower;
    }

    public CleanerProperty() {
        this(1);
    }

    @Override
    public void verifyProperty(MaterialProperties properties) {
        properties.ensureSet(PropertyKey.FLUID, true);
    }

    public int getCleaningPower() {
        return cleaningPower;
    }
}
