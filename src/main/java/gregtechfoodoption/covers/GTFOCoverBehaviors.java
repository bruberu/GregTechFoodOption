package gregtechfoodoption.covers;

import static gregtech.common.covers.CoverBehaviors.registerBehavior;

import net.minecraft.util.ResourceLocation;

import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.item.GTFOMetaItem;

public class GTFOCoverBehaviors {

    public static void init() {
        registerBehavior(new ResourceLocation(GTFOValues.MODID, "sprinkler"), GTFOMetaItem.SPRINKLER_COVER,
                (def, tile, side) -> new CoverSprinkler(def, tile, side, 1));
    }
}
