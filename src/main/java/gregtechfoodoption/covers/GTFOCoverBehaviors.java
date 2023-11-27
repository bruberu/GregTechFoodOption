package gregtechfoodoption.covers;

import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.item.GTFOMetaItem;
import net.minecraft.util.ResourceLocation;

import static gregtech.common.covers.CoverBehaviors.registerBehavior;

public class GTFOCoverBehaviors {
    public static void init() {
        registerBehavior(new ResourceLocation(GTFOValues.MODID, "sprinkler"), GTFOMetaItem.SPRINKLER_COVER, (def, tile, side) ->
                new CoverSprinkler(def, tile, side, 1));
    }
}
