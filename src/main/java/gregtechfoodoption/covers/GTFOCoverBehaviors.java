package gregtechfoodoption.covers;

import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.item.GTFOMetaItem;
import net.minecraft.util.ResourceLocation;

import static gregtech.common.covers.CoverBehaviors.registerBehavior;

public class GTFOCoverBehaviors {
    public static void init() {
        registerBehavior(7265, new ResourceLocation(GTFOValues.MODID, "sprinkler"), GTFOMetaItem.SPRINKLER_COVER, (tile, side) -> new CoverSprinkler(tile, side, 1));
    }
}
