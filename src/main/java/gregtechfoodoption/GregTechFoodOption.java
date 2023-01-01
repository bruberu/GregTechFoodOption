package gregtechfoodoption;

import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtechfoodoption.block.GTFOMetaBlocks;
import gregtechfoodoption.client.GTFOClientHandler;
import gregtechfoodoption.covers.GTFOCoverBehaviors;
import gregtechfoodoption.entity.GTFOEntities;
import gregtechfoodoption.integration.applecore.GTFOAppleCoreCompat;
import gregtechfoodoption.integration.tfc.GTFOTFCCompatibility;
import gregtechfoodoption.integration.top.GTFOTOPCompatibility;
import gregtechfoodoption.machines.GTFOTileEntities;
import gregtechfoodoption.machines.farmer.FarmerModeRegistry;
import gregtechfoodoption.network.PacketAppleCoreFoodDivisorUpdate;
import gregtechfoodoption.utils.GTFOConfigOverrider;
import gregtechfoodoption.worldgen.GTFOWorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = GregTechFoodOption.MODID, name = GregTechFoodOption.NAME, version = GregTechFoodOption.VERSION,
        dependencies = "required-after:gregtech@(2.3.4,);" + "after:gcy_science")
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
        GregTechAPI.networkHandler.registerPacket(PacketAppleCoreFoodDivisorUpdate.class);
        proxy.preLoad();

        MinecraftForge.EVENT_BUS.register(new GTFODropsEventHandler());

        GTFOMetaBlocks.init();
        GTFOTileEntities.init();

        MinecraftForge.EVENT_BUS.register(new GTFOEntities()); // For entity registration through EntityEntries!

        if(GTFOConfig.gtfoAppleCoreConfig.appleCoreCompat)
            MinecraftForge.EVENT_BUS.register(new GTFOAppleCoreCompat());

        GTFOClientHandler.registerSounds();

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        FarmerModeRegistry.registerDefaultModes();
        try {
            proxy.onLoad();
        } catch (Exception e) {
        }
        if (Loader.isModLoaded(GTValues.MODID_TOP)) {
            GTFOTOPCompatibility.registerCompatibility();
        }
        if (Loader.isModLoaded(GTFOValues.MODID_GF) && Loader.isModLoaded(GTFOValues.MODID_TFC)) {
            GTFOTFCCompatibility.init();
        }
        GTFOCoverBehaviors.init();
        GameRegistry.registerWorldGenerator(GTFOWorldGenerator.INSTANCE, 1);
        GTFOMaterialHandler.registerFertilizerTooltips();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.onPostLoad();
    }
}
