package com.bruberu.gregtechfoodoption;

import com.bruberu.gregtechfoodoption.utils.GTFOLog;
import com.bruberu.gregtechfoodoption.CommonProxy;
import gregtech.api.GTValues;
import gregtech.api.capability.SimpleCapabilityManager;
import gregtech.api.net.NetworkHandler;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;

@Mod(modid = GregTechFoodOption.MODID, name = GregTechFoodOption.NAME, version = GregTechFoodOption.VERSION,
        dependencies = "required-after:gregtech@[1.14.0.689,);" +
                "after:exnihilocreatio;")
public class GregTechFoodOption {
    public static final String MODID = "gregtechfoodoption";
    public static final String NAME = "GregTech Food Option";
    public static final String VERSION = "@1.12@";

    /*
    static {
        if (FMLCommonHandler.instance().getSide().isClient()) {

        }
    }
    */



    @SidedProxy(modId = MODID, clientSide = "com.bruberu.gregtechfoodoption.ClientProxy", serverSide = "com.bruberu.gregtechfoodoption.CommonProxy")
    public static CommonProxy proxy;

    public GregTechFoodOption() {

    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        GTFOLog.init(event.getModLog());
        proxy.preLoad();
        MinecraftForge.EVENT_BUS.register(new GTFOEventHandler());
        MinecraftForge.EVENT_BUS.register(new GTFOSeedDropsEventHandler());

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) throws IOException {
        proxy.onLoad();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @Mod.EventHandler
    public void serverStarted(FMLServerStartedEvent event) {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
            World world = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld();
        }
    }
}
