package gregtechfoodoption.machines;

import gregtech.api.GTValues;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.util.GTUtility;
import gregtech.common.metatileentities.MetaTileEntities;
import gregtechfoodoption.GregTechFoodOption;
import gregtechfoodoption.client.GTFOClientHandler;
import gregtechfoodoption.machines.farmer.MetaTileEntityFarmer;
import gregtechfoodoption.machines.multiblock.MetaTileEntityBakingOven;
import gregtechfoodoption.machines.multiblock.MetaTileEntityElectricBakingOven;
import gregtechfoodoption.machines.multiblock.MetaTileEntityGreenhouse;
import gregtechfoodoption.machines.multiblock.MetaTileEntitySteamBakingOven;
import gregtechfoodoption.recipe.GTFORecipeMaps;
import net.minecraft.util.ResourceLocation;

import static gregtech.common.metatileentities.MetaTileEntities.*;

/* Takes up IDs 8500 - 8599 */
public class GTFOTileEntities {
    //public static MetaTileEntityBioReactor[] BIOREACTOR = new MetaTileEntityBioReactor[GTValues.V.length];
    public static SimpleMachineMetaTileEntity[] SLICER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static SimpleMachineMetaTileEntity[] CUISINE_ASSEMBLER = new SimpleMachineMetaTileEntity[GTValues.V.length - 1];
    public static MetaTileEntityMicrowave[] MICROWAVE = new MetaTileEntityMicrowave[GTValues.V.length - 1];

    public static final MetaTileEntityMobAgeSorter[] MOB_AGE_SORTER = new MetaTileEntityMobAgeSorter[4];
    public static final MetaTileEntityMobExterminator[] MOB_EXTERMINATOR = new MetaTileEntityMobExterminator[4];
    public static final MetaTileEntityMobExtractor[] MOB_EXTRACTOR = new MetaTileEntityMobExtractor[GTValues.UV];
    public static final MetaTileEntityFarmer[] FARMER = new MetaTileEntityFarmer[4];
    public static MetaTileEntityBakingOven BAKING_OVEN;
    public static MetaTileEntityElectricBakingOven ELECTRIC_BAKING_OVEN;
    public static MetaTileEntitySteamBakingOven STEAM_BAKING_OVEN;
    public static MetaTileEntityGreenhouse GREENHOUSE;


    public static void init() {
/*
        BIOREACTOR[2] = MetaTileEntities.registerMetaTileEntity(8500, new MetaTileEntityBioReactor(location("bioreactor.hv"), 3));
        BIOREACTOR[3] = MetaTileEntities.registerMetaTileEntity(8501, new MetaTileEntityBioReactor(location("bioreactor.ev"), 4));
        BIOREACTOR[4] = MetaTileEntities.registerMetaTileEntity(8502, new MetaTileEntityBioReactor(location("bioreactor.iv"), 5));
*/

        MetaTileEntities.registerSimpleMetaTileEntity(SLICER, 8503, "slicer", GTFORecipeMaps.SLICER_RECIPES, GTFOClientHandler.SLICER_OVERLAY, true, GTFOTileEntities::location, GTUtility.hvCappedTankSizeFunction);
        MetaTileEntities.registerSimpleMetaTileEntity(CUISINE_ASSEMBLER, 8518, "cuisine_assembler", GTFORecipeMaps.CUISINE_ASSEMBLER_RECIPES, GTFOClientHandler.CUISINE_ASSEMBLER_OVERLAY, true, GTFOTileEntities::location, GTUtility.hvCappedTankSizeFunction);
        MICROWAVE[1] = registerMetaTileEntity(8531, new MetaTileEntityMicrowave(location("microwave.lv"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 1));
        MICROWAVE[2] = registerMetaTileEntity(8532, new MetaTileEntityMicrowave(location("microwave.mv"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 2));
        MICROWAVE[3] = registerMetaTileEntity(8533, new MetaTileEntityMicrowave(location("microwave.hv"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 3));
        MICROWAVE[4] = registerMetaTileEntity(8534, new MetaTileEntityMicrowave(location("microwave.ev"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 4));
        MICROWAVE[5] = registerMetaTileEntity(8535, new MetaTileEntityMicrowave(location("microwave.iv"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 5));
        if (getMidTier("microwave")) {
            MICROWAVE[6] = registerMetaTileEntity(8536, new MetaTileEntityMicrowave(location("microwave.luv"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 6));
            MICROWAVE[7] = registerMetaTileEntity(8537, new MetaTileEntityMicrowave(location("microwave.zpm"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 7));
            MICROWAVE[8] = registerMetaTileEntity(8538, new MetaTileEntityMicrowave(location("microwave.uv"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 8));
        }
        if (getHighTier("microwave")) {
            MICROWAVE[9] = registerMetaTileEntity(8539, new MetaTileEntityMicrowave(location("microwave.uhv"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 9));
            MICROWAVE[10] = registerMetaTileEntity(8540, new MetaTileEntityMicrowave(location("microwave.uev"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 10));
            MICROWAVE[11] = registerMetaTileEntity(8541, new MetaTileEntityMicrowave(location("microwave.uiv"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 11));
            MICROWAVE[12] = registerMetaTileEntity(8542, new MetaTileEntityMicrowave(location("microwave.uxv"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 12));
            MICROWAVE[13] = registerMetaTileEntity(8543, new MetaTileEntityMicrowave(location("microwave.opv"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 13));
        }
        BAKING_OVEN = registerMetaTileEntity(8516, new MetaTileEntityBakingOven(location("baking_oven")));
        ELECTRIC_BAKING_OVEN = registerMetaTileEntity(8517, new MetaTileEntityElectricBakingOven(location("electric_baking_oven")));
        STEAM_BAKING_OVEN = registerMetaTileEntity(8544, new MetaTileEntitySteamBakingOven(location("steam_baking_oven"), GTFORecipeMaps.ELECTRIC_BAKING_OVEN_RECIPES, 10));

        // Mob Age Sorters, IDs 8545-8548
        MOB_AGE_SORTER[0] = registerMetaTileEntity(8545, new MetaTileEntityMobAgeSorter(location("mob_age_sorter.lv"), 1, 1));
        MOB_AGE_SORTER[1] = registerMetaTileEntity(8546, new MetaTileEntityMobAgeSorter(location("mob_age_sorter.mv"), 2, 3));
        MOB_AGE_SORTER[2] = registerMetaTileEntity(8547, new MetaTileEntityMobAgeSorter(location("mob_age_sorter.hv"), 3, 5));
        MOB_AGE_SORTER[3] = registerMetaTileEntity(8548, new MetaTileEntityMobAgeSorter(location("mob_age_sorter.ev"), 4, 9));

        // Mob Exterminators, IDs 8549-8552
        MOB_EXTERMINATOR[0] = registerMetaTileEntity(8549, new MetaTileEntityMobExterminator(location("mob_exterminator.lv"), 1));
        MOB_EXTERMINATOR[1] = registerMetaTileEntity(8550, new MetaTileEntityMobExterminator(location("mob_exterminator.mv"), 2));
        MOB_EXTERMINATOR[2] = registerMetaTileEntity(8551, new MetaTileEntityMobExterminator(location("mob_exterminator.hv"), 3));
        MOB_EXTERMINATOR[3] = registerMetaTileEntity(8552, new MetaTileEntityMobExterminator(location("mob_exterminator.ev"), 4));

        // Mob Extractor, IDs 8553-8560
        for (int i = 0; i < MOB_EXTRACTOR.length; i++) {
            if (i > 4 && !getMidTier("mob_extractor")) continue;

            String voltageName = GTValues.VN[i + 1].toLowerCase();
            MOB_EXTRACTOR[i] = registerMetaTileEntity(8553 + i,
                    new MetaTileEntityMobExtractor(location(String.format("%s.%s", "mob_extractor", voltageName)), GTFORecipeMaps.MOB_EXTRACTOR_RECIPES, GTFOClientHandler.MOB_EXTRACTOR_OVERLAY, i + 1, false, GTUtility.largeTankSizeFunction));
        }

        // Farmer, IDs 8561-8564
        FARMER[0] = registerMetaTileEntity(8561, new MetaTileEntityFarmer(location("farmer.lv"), 1, 20));
        FARMER[1] = registerMetaTileEntity(8562, new MetaTileEntityFarmer(location("farmer.mv"), 2, 10));
        FARMER[2] = registerMetaTileEntity(8563, new MetaTileEntityFarmer(location("farmer.hv"), 3, 5));
        FARMER[3] = registerMetaTileEntity(8564, new MetaTileEntityFarmer(location("farmer.ev"), 4, 2));

        GREENHOUSE = registerMetaTileEntity(8565, new MetaTileEntityGreenhouse(location("greenhouse")));

    }

    private static ResourceLocation location(String name) {
        return new ResourceLocation(GregTechFoodOption.MODID, name);
    }
}
