package gregtechfoodoption.entity;

import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.GregTechFoodOption;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowMan;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = GregTechFoodOption.MODID)
public class GTFOEntities {
    @SubscribeEvent
    public static void onEntityRegistry(RegistryEvent.Register<EntityEntry> event)
    {
        event.getRegistry().register(EntityEntryBuilder.create().entity(EntityItalianBuffalo.class)
                .id(new ResourceLocation(GregTechFoodOption.MODID, "italian_buffalo"), 28)
                .name("italian_buffalo")
                .tracker(80, 3, true)
                .spawn(EnumCreatureType.CREATURE, 2, 1, 3, EntityItalianBuffalo.POSSIBLE_BIOME_SPAWNS)
                .egg(0x3d352f, 0xf0ded1).build());

        EntityRegistry.registerModEntity(new ResourceLocation(GTFOValues.MODID, "strong_snowman"), EntityStrongSnowman.class, "Snowman", 1, GregTechFoodOption.instance, 64, 5, true);
        EntityRegistry.registerModEntity(new ResourceLocation(GTFOValues.MODID, "strong_snowball"), EntityStrongSnowball.class, "Snowball", 2, GregTechFoodOption.instance, 64, 5, true);

    }

    @SideOnly(Side.CLIENT)
    public static void registerRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityItalianBuffalo.class, RenderItalianBuffalo::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityStrongSnowman.class, RenderSnowMan::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityStrongSnowball.class, manager -> new RenderStrongSnowball(manager, Minecraft.getMinecraft().getRenderItem()));
    }
}
