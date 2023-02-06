package gregtechfoodoption.materials;

import gregtech.api.unification.material.properties.IMaterialProperty;
import gregtech.api.unification.material.properties.MaterialProperties;
import gregtech.api.unification.material.properties.PropertyKey;

public class FertilizerProperty implements IMaterialProperty<FertilizerProperty> {
    private int boostPercentage;

    public FertilizerProperty(int boostPercentage) {
        this.boostPercentage = boostPercentage;
    }

    public FertilizerProperty() {
        this(2);
    }

    public int getBoostPercentage() {
        return boostPercentage;
    }

    public void setBoostPercentage(int boostPercentage) {
        this.boostPercentage = boostPercentage;
    }

    @Override
    public void verifyProperty(MaterialProperties properties) {
        properties.ensureSet(PropertyKey.FLUID, true);
    }
}
