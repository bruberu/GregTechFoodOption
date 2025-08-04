package gregtechfoodoption.client;

import dev.tianmi.sussypatches.client.renderer.textures.cube.VisualStateRenderer;

import static dev.tianmi.sussypatches.client.renderer.textures.ConnectedTextures.SOLID_STEEL_CASING_CTM;
import static dev.tianmi.sussypatches.client.renderer.textures.cube.VisualStateRenderer.from;
import static gregtechfoodoption.block.GTFOBlockCasing.CasingType.ADOBE_BRICKS;
import static gregtechfoodoption.block.GTFOBlockCasing.CasingType.REINFORCED_ADOBE_BRICKS;
import static gregtechfoodoption.block.GTFOMetaBlocks.GTFO_CASING;
import static gregtechfoodoption.block.GTFOMetaBlocks.GTFO_METAL_CASING;
import static gregtechfoodoption.block.GTFOMetalCasing.CasingType.BISMUTH_BRONZE_CASING;
import static gregtechfoodoption.machines.GTFOTileEntities.location;

public class GTFOConnectedTextures {

    public static final VisualStateRenderer ADOBE_BRICKS_CTM;
    public static final VisualStateRenderer BISMUTH_BRONZE_CASING_CTM;
    public static final VisualStateRenderer REINFORCED_ADOBE_BRICKS_CTM;

    static {
        ADOBE_BRICKS_CTM = from(GTFO_CASING.getState(ADOBE_BRICKS));
        BISMUTH_BRONZE_CASING_CTM = from(GTFO_METAL_CASING.getState(BISMUTH_BRONZE_CASING));
        REINFORCED_ADOBE_BRICKS_CTM = from(GTFO_CASING.getState(REINFORCED_ADOBE_BRICKS));
    }

    public static void init() {
        ADOBE_BRICKS_CTM.override(location("baking_oven"));
        BISMUTH_BRONZE_CASING_CTM.override(location("electric_baking_oven"));
        REINFORCED_ADOBE_BRICKS_CTM.override(location("steam_baking_oven"));
        SOLID_STEEL_CASING_CTM.override(location("greenhouse"),
                location("kitchen"));
    }
}
