package gregtechfoodoption;

import gregtech.GregTechMod;
import gregtech.api.GregTechAPI;
import gregtech.api.unification.material.Material;
import gregtech.api.util.GTLog;
import gregtech.common.CommonProxy;
import gregtech.common.MetaFluids;
import gregtech.common.items.MetaItems;
import gregtechfoodoption.integration.GTFOAAMaterialHandler;
import gregtechfoodoption.integration.GTFONCMaterialHandler;
import gregtechfoodoption.machines.GTFOTileEntities;
import gregtechfoodoption.utils.GTFOLog;
import gregtechfoodoption.block.GTFOMetaBlocks;
import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.init.Bootstrap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import org.apache.logging.log4j.LogManager;
import org.junit.BeforeClass;
import org.junit.Test;

import static gregtech.api.GregTechAPI.MATERIAL_REGISTRY;
import static org.junit.Assert.assertNotNull;

public class SimpleMaterialTest {

    /**
     * Initialize registries
     */
    @BeforeClass
    public static void bootStrap() {
/*
        // Bootstrap basic Forge registries
        Bootstrap.register();

        // Run Early handlers
        ObfuscationReflectionHelper.setPrivateValue(FMLCommonHandler.class, FMLCommonHandler.instance(), new FMLClientHandler(), "sidedDelegate");
        new GregTechMod();
        GregTechMod.proxy = new CommonProxy();
        new GregTechAPI();

        MATERIAL_REGISTRY.unfreeze();
        GTLog.logger.info("Registering GTCEu Materials");
        Materials.register();

        // Then, register addon Materials
        GTLog.logger.info("Registering addon Materials");
        MinecraftForge.EVENT_BUS.post(new GregTechAPI.MaterialEvent());

        // Bootstrap GTCE Blocks
        try {
            MetaBlocks.init();
            MetaItems.init();
            MetaFluids.init();
        } catch (Exception ignored) {

        }
        GTFOMetaBlocks.init();

        // Bootstrap MTEs
        MetaTileEntities.init();
        GTFOTileEntities.init();
    }

    *//**
     * Basic Nonnull test to try.
     *
     * The real test is in the bootStrap, where if there are conflicting material IDs registered,
     * it will throw an {@link IllegalArgumentException} and fail the test.
     *//*
    @Test
    public void areMaterialsGenerated() {
        assertNotNull(
                "OreDictUnifier failed to gather a GTCE Material ItemStack",
                Materials.Carbon
        );
        assertNotNull(
                "OreDictUnifier failed to gather a GTFO Material ItemStack",
                GTFOMaterialHandler.CookedCurd
        );
        assertNotNull(
                "OreDictUnifier failed to gather a GTFO AA Compat Material ItemStack",
                GTFOAAMaterialHandler.Coffee
        );
        assertNotNull(
                "OreDictUnifier failed to gather a GTFO NC Compat Material ItemStack",
                GTFONCMaterialHandler.Butter
        );*/
    }

    /**
     * Basic Nonnull test to try.
     *
     * The real test is in bootStrap, where if there are conflicting MTE ID's,
     * it will throw an {@link IllegalArgumentException} and fail the test.
     */
    @Test
    public void areMTEsGenerated() {
        /*assertNotNull(
                "GTCE MetaTileEntity is still null!",
                MetaTileEntities.COMBUSTION_GENERATOR
        );
        assertNotNull(
                "GTFO MetaTileEntity is still null!",
                GTFOTileEntities.SLICER
        );*/
    }
}
