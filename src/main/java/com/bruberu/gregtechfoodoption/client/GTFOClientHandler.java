package com.bruberu.gregtechfoodoption.client;

import com.bruberu.gregtechfoodoption.GregTechFoodOption;
import gregtech.api.render.OrientedOverlayRenderer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = GregTechFoodOption.MODID, value = Side.CLIENT)

public class GTFOClientHandler {
    public static OrientedOverlayRenderer SLICER_OVERLAY = new OrientedOverlayRenderer("machines/slicer", OrientedOverlayRenderer.OverlayFace.FRONT, OrientedOverlayRenderer.OverlayFace.TOP);
}
