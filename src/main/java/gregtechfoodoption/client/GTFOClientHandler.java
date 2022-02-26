package gregtechfoodoption.client;

import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;
import gregtechfoodoption.GTFOValues;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class GTFOClientHandler {
    public static OrientedOverlayRenderer SLICER_OVERLAY = new OrientedOverlayRenderer("machines/slicer", OrientedOverlayRenderer.OverlayFace.FRONT, OrientedOverlayRenderer.OverlayFace.TOP);
    public static OrientedOverlayRenderer BAKING_OVEN_OVERLAY = new OrientedOverlayRenderer("machines/baking_oven", OrientedOverlayRenderer.OverlayFace.FRONT);
    public static OrientedOverlayRenderer CUISINE_ASSEMBLER_OVERLAY = new OrientedOverlayRenderer("machines/cuisine_assembler", OrientedOverlayRenderer.OverlayFace.FRONT, OrientedOverlayRenderer.OverlayFace.TOP);
    public static OrientedOverlayRenderer MICROWAVE_OVERLAY = new OrientedOverlayRenderer("machines/microwave", OrientedOverlayRenderer.OverlayFace.FRONT);

    public static SimpleOverlayRenderer ADOBE_BRICKS = new SimpleOverlayRenderer("casings/solid/adobe_bricks");
    public static SimpleOverlayRenderer REINFORCED_ADOBE_BRICKS = new SimpleOverlayRenderer("casings/solid/reinforced_adobe_bricks");
    public static SimpleOverlayRenderer BISMUTH_BRONZE_CASING = new SimpleOverlayRenderer("casings/solid/bismuth_bronze");

    public static SoundEvent MICROWAVE_FINISH;

    public static void registerSounds() {
        MICROWAVE_FINISH = registerSound("microwave.finish");
    }

    private static SoundEvent registerSound(String soundNameIn) {
        ResourceLocation location = new ResourceLocation(GTFOValues.MODID, soundNameIn);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(location);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }
}
