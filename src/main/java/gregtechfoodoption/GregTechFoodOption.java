package gregtechfoodoption;

import gregtechfoodoption.entity.GTFOEntities;
import gregtechfoodoption.integration.applecore.GTFOAppleCoreCompat;
import gregtechfoodoption.utils.GTFOConfigOverrider;
import gregtechfoodoption.utils.GTFOLog;
import gregtechfoodoption.block.GTFOMetaBlocks;
import gregtechfoodoption.machines.GTFOTileEntities;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.IOException;

@Mod(modid = GregTechFoodOption.MODID, name = GregTechFoodOption.NAME, version = GregTechFoodOption.VERSION,
        dependencies = "required-after:gregtech@[2.0.2-beta,);after:gcy_science")
public class GregTechFoodOption {
    public static final String MODID = "gregtechfoodoption";
    public static final String NAME = "GregTech Food Option";
    public static final String VERSION = "@VERSION@";

    @Mod.Instance
    public static GregTechFoodOption instance;


    @SidedProxy(modId = MODID, clientSide = "gregtechfoodoption.ClientProxy", serverSide = "gregtechfoodoption.CommonProxy")
    public static CommonProxy proxy;


    @Mod.EventHandler
    public void onStartup(FMLConstructionEvent event) {
        GTFOConfigOverrider.init();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preLoad();

        MinecraftForge.EVENT_BUS.register(new GTFODropsEventHandler());

        GTFOMetaBlocks.init();
        GTFOTileEntities.init();

        MinecraftForge.EVENT_BUS.register(new GTFOEntities()); // For entity registration through EntityEntries!

        if(GTFOConfig.gtfoAppleCoreConfig.appleCoreCompat)
            MinecraftForge.EVENT_BUS.register(new GTFOAppleCoreCompat());


        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        try {
            proxy.onLoad();
        } catch (Exception e) {
        }
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.onPostLoad();
    }
}
