package gregtechfoodoption.integration.agricraft;

import com.infinityraider.agricraft.api.v1.misc.IAgriRegistry;
import com.infinityraider.agricraft.api.v1.plant.IAgriPlant;
import com.infinityraider.agricraft.api.v1.plugin.AgriPlugin;
import com.infinityraider.agricraft.api.v1.plugin.IAgriPlugin;
import gregtechfoodoption.block.GTFOCrop;

import javax.annotation.Nonnull;

@AgriPlugin
public class GTFOAgriCraftPlugin implements IAgriPlugin {
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getId() {
        return "gregtechfoodoption";
    }

    @Override
    public String getName() {
        return "GregTech Food Option AgriCraft Plugin";
    }

    @Override
    public void registerPlants(@Nonnull IAgriRegistry<IAgriPlant> plantRegistry) {
        for (GTFOCrop crop : GTFOCrop.CROP_BLOCKS) {
            plantRegistry.add(new GTFOAgriPlant(crop));
        }
    }
}
