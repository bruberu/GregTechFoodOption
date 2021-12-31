package gregtechfoodoption;

import gregtechfoodoption.integration.GTFOAAMaterialHandler;
import gregtechfoodoption.integration.GTFONCMaterialHandler;
import gregtechfoodoption.machines.GTFOTileEntities;
import gregtechfoodoption.utils.GTFOLog;
import gregtechfoodoption.block.GTFOMetaBlocks;
import gregtech.api.unification.material.Materials;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.init.Bootstrap;
import org.apache.logging.log4j.LogManager;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SimpleMaterialTest {

    /**
     * Initialize registries
     */
    @BeforeClass
    public static void bootStrap() {

        // Bootstrap basic Forge registries
        Bootstrap.register();

        // Bootstrap Loggers
        GTFOLog.init(LogManager.getLogger(GregTechFoodOption.MODID));

        // Run Early handlers
        Materials.register();
        GAEnums.onConstruction();

        // Bootstrap Gregicality Materials
        GAMaterials gaMaterials = new GAMaterials();
        gaMaterials.onMaterialsInit();

        // Bootstrap GTFO Materials
        new GTFOMaterialHandler().onMaterialsInit();
        new GTFOAAMaterialHandler().onMaterialsInit();
        new GTFONCMaterialHandler().onMaterialsInit();
        Material.freezeRegistry();

        // Bootstrap GTCE Blocks
        MetaBlocks.init();
        GAMetaBlocks.init();
        GTFOMetaBlocks.init();

        // Bootstrap MTEs
        MetaTileEntities.init();
        GTFOTileEntities.init();
    }

    /**
     * Basic Nonnull test to try.
     *
     * The real test is in the bootStrap, where if there are conflicting material IDs registered,
     * it will throw an {@link IllegalArgumentException} and fail the test.
     */
    @Test
    public void areMaterialsGenerated() {
        assertNotNull(
                "OreDictUnifier failed to gather a GTCE Material ItemStack",
                Materials.Carbon
        );
        assertNotNull(
                "OreDictUnifier failed to gather a GTFO Material ItemStack",
                GTFOMaterialHandler.AppleCandySyrup
        );
        assertNotNull(
                "OreDictUnifier failed to gather a GTFO AA Compat Material ItemStack",
                GTFOAAMaterialHandler.Coffee
        );
        assertNotNull(
                "OreDictUnifier failed to gather a GTFO NC Compat Material ItemStack",
                GTFONCMaterialHandler.Butter
        );
    }

    /**
     * Basic Nonnull test to try.
     *
     * The real test is in bootStrap, where if there are conflicting MTE ID's,
     * it will throw an {@link IllegalArgumentException} and fail the test.
     */
    @Test
    public void areMTEsGenerated() {
        assertNotNull(
                "GTCE MetaTileEntity is still null!",
                MetaTileEntities.DIESEL_ENGINE
        );
        assertNotNull(
                "GTFO MetaTileEntity is still null!",
                GTFOTileEntities.BIOREACTOR
        );
    }
}
