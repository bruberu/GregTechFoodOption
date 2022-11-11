package gregtechfoodoption.client;

import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;
import gregtechfoodoption.GTFOValues;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import static gregtech.client.renderer.texture.cube.OrientedOverlayRenderer.OverlayFace.*;

public class GTFOClientHandler {
    public static OrientedOverlayRenderer SLICER_OVERLAY = new OrientedOverlayRenderer("machines/slicer", FRONT, TOP);
    public static OrientedOverlayRenderer BAKING_OVEN_OVERLAY = new OrientedOverlayRenderer("machines/baking_oven", FRONT);
    public static OrientedOverlayRenderer CUISINE_ASSEMBLER_OVERLAY = new OrientedOverlayRenderer("machines/cuisine_assembler", FRONT, TOP);
    public static OrientedOverlayRenderer MICROWAVE_OVERLAY = new OrientedOverlayRenderer("machines/microwave", FRONT);
    public static final OrientedOverlayRenderer MOB_AGE_SORTER_OVERLAY = new OrientedOverlayRenderer("machines/mob_age_sorter", FRONT, BACK);
    public static final OrientedOverlayRenderer MOB_EXTERMINATOR_OVERLAY = new OrientedOverlayRenderer("machines/mob_exterminator", FRONT, TOP);
    public static final OrientedOverlayRenderer MOB_EXTRACTOR_OVERLAY = new OrientedOverlayRenderer("machines/mob_extractor", FRONT, TOP);
    public static final OrientedOverlayRenderer FARMER_OVERLAY = new OrientedOverlayRenderer("machines/farmer", FRONT);
    public static final OrientedOverlayRenderer SPRINKLER_OVERLAY = new OrientedOverlayRenderer("machines/sprinkler", FRONT);


    public static SimpleOverlayRenderer ADOBE_BRICKS = new SimpleOverlayRenderer("casings/solid/adobe_bricks");
    public static SimpleOverlayRenderer REINFORCED_ADOBE_BRICKS = new SimpleOverlayRenderer("casings/solid/reinforced_adobe_bricks");
    public static SimpleOverlayRenderer BISMUTH_BRONZE_CASING = new SimpleOverlayRenderer("casings/solid/bismuth_bronze");

    public static SoundEvent MICROWAVE_FINISH;
    public static SoundEvent FARMER_LASER;

    public static void registerSounds() {
        MICROWAVE_FINISH = registerSound("microwave.finish");
        FARMER_LASER = registerSound("farmer.laser");
    }

    private static SoundEvent registerSound(String soundNameIn) {
        ResourceLocation location = new ResourceLocation(GTFOValues.MODID, soundNameIn);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(location);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }
}
