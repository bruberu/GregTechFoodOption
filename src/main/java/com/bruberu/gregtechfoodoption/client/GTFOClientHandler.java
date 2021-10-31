package com.bruberu.gregtechfoodoption.client;

import gregtech.api.render.OrientedOverlayRenderer;
import gregtech.api.render.SimpleCubeRenderer;

public class GTFOClientHandler {
    public static OrientedOverlayRenderer SLICER_OVERLAY = new OrientedOverlayRenderer("machines/slicer", OrientedOverlayRenderer.OverlayFace.FRONT, OrientedOverlayRenderer.OverlayFace.TOP);
    public static OrientedOverlayRenderer BAKING_OVEN_OVERLAY = new OrientedOverlayRenderer("machines/baking_oven", OrientedOverlayRenderer.OverlayFace.FRONT);
    public static OrientedOverlayRenderer CUISINE_ASSEMBLER_OVERLAY = new OrientedOverlayRenderer("machines/cuisine_assembler", OrientedOverlayRenderer.OverlayFace.FRONT, OrientedOverlayRenderer.OverlayFace.TOP);


    public static SimpleCubeRenderer ADOBE_BRICKS = new SimpleCubeRenderer("casings/solid/adobe_bricks");
    public static SimpleCubeRenderer BISMUTH_BRONZE_CASING = new SimpleCubeRenderer("casings/solid/bismuth_bronze");

}
