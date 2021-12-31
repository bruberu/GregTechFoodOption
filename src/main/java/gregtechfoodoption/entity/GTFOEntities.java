package gregtechfoodoption.entity;

import gregtechfoodoption.GregTechFoodOption;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

@Mod.EventBusSubscriber(modid = GregTechFoodOption.MODID)
public class GTFOEntities {
    @SubscribeEvent
    public static void onEntityRegistry(RegistryEvent.Register<EntityEntry> event)
    {
        event.getRegistry().register(EntityEntryBuilder.create().entity(EntityItalianBuffalo.class)
                .id(new ResourceLocation(GregTechFoodOption.MODID, "italian_buffalo"), 28)
                .name("italian_buffalo")
                .tracker(80, 3, true)
                .spawn(EnumCreatureType.CREATURE, 50, 1, 3, EntityItalianBuffalo.POSSIBLE_BIOME_SPAWNS)
                .egg(0x3d352f, 0xf0ded1).build());
    }
    public static void registerRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityItalianBuffalo.class, RenderItalianBuffalo::new);
    }
}
