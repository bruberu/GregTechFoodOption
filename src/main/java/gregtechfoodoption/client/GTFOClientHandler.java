package gregtechfoodoption.client;

import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.client.renderer.texture.cube.SimpleCubeRenderer;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.GregTechFoodOption;
import gregtechfoodoption.potion.AntiSchizoPotion;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Mod.EventBusSubscriber(modid = GregTechFoodOption.MODID)
public class GTFOClientHandler {
    public static OrientedOverlayRenderer SLICER_OVERLAY = new OrientedOverlayRenderer("machines/slicer");
    public static OrientedOverlayRenderer BAKING_OVEN_OVERLAY = new OrientedOverlayRenderer("machines/baking_oven");
    public static OrientedOverlayRenderer CUISINE_ASSEMBLER_OVERLAY = new OrientedOverlayRenderer("machines/cuisine_assembler");
    public static OrientedOverlayRenderer MICROWAVE_OVERLAY = new OrientedOverlayRenderer("machines/microwave");
    public static OrientedOverlayRenderer MULTICOOKER_OVERLAY = new OrientedOverlayRenderer("machines/multicooker");
    public static final OrientedOverlayRenderer MOB_AGE_SORTER_OVERLAY = new OrientedOverlayRenderer("machines/mob_age_sorter");
    public static final OrientedOverlayRenderer MOB_EXTERMINATOR_OVERLAY = new OrientedOverlayRenderer("machines/mob_exterminator");
    public static final OrientedOverlayRenderer MOB_EXTRACTOR_OVERLAY = new OrientedOverlayRenderer("machines/mob_extractor");
    public static final OrientedOverlayRenderer FARMER_OVERLAY = new OrientedOverlayRenderer("machines/farmer");
    public static final OrientedOverlayRenderer SPRINKLER_OVERLAY = new OrientedOverlayRenderer("machines/sprinkler");


    public static SimpleCubeRenderer ADOBE_BRICKS = new SimpleCubeRenderer("blocks/casings/solid/adobe_bricks");
    public static SimpleCubeRenderer REINFORCED_ADOBE_BRICKS = new SimpleCubeRenderer("blocks/casings/solid/reinforced_adobe_bricks");
    public static SimpleCubeRenderer BISMUTH_BRONZE_CASING = new SimpleCubeRenderer("blocks/casings/solid/bismuth_bronze");

    public static SoundEvent MICROWAVE_FINISH;
    public static SoundEvent FARMER_LASER;
    public static SoundEvent AMOGUS_VENT;

    public static void registerSounds() {
        MICROWAVE_FINISH = registerSound("microwave.finish");
        FARMER_LASER = registerSound("farmer.laser");
        AMOGUS_VENT = registerSound("amogus.vent");
    }

    private static SoundEvent registerSound(String soundNameIn) {
        ResourceLocation location = new ResourceLocation(GTFOValues.MODID, soundNameIn);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(location);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }

    public static void renderPlayerList(RenderGameOverlayEvent.Pre event) {
        if (!Minecraft.getMinecraft().player.isPotionActive(AntiSchizoPotion.INSTANCE)) {
            return;
        }
        if (event.getType() == RenderGameOverlayEvent.ElementType.PLAYER_LIST) {
            event.setCanceled(true);
        }
    }


}
