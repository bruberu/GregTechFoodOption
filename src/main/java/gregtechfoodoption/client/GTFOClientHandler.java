package gregtechfoodoption.client;

import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;

public class GTFOClientHandler {
    public static OrientedOverlayRenderer SLICER_OVERLAY = new OrientedOverlayRenderer("machines/slicer", OrientedOverlayRenderer.OverlayFace.FRONT, OrientedOverlayRenderer.OverlayFace.TOP);
    public static OrientedOverlayRenderer BAKING_OVEN_OVERLAY = new OrientedOverlayRenderer("machines/baking_oven", OrientedOverlayRenderer.OverlayFace.FRONT);
    public static OrientedOverlayRenderer CUISINE_ASSEMBLER_OVERLAY = new OrientedOverlayRenderer("machines/cuisine_assembler", OrientedOverlayRenderer.OverlayFace.FRONT, OrientedOverlayRenderer.OverlayFace.TOP);

    public static SimpleOverlayRenderer ADOBE_BRICKS = new SimpleOverlayRenderer("casings/solid/adobe_bricks");
    public static SimpleOverlayRenderer BISMUTH_BRONZE_CASING = new SimpleOverlayRenderer("casings/solid/bismuth_bronze");

}
