package gregtechfoodoption.machines;

import gregtechfoodoption.GregTechFoodOption;
import gregtechfoodoption.client.GTFOClientHandler;
import gregtechfoodoption.recipe.GTFORecipeMaps;
import gregtechfoodoption.machines.multiblock.MetaTileEntityBakingOven;
import gregtechfoodoption.machines.multiblock.MetaTileEntityElectricBakingOven;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.util.GTUtility;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.util.ResourceLocation;

/* Takes up IDs 8500 - 8599 */
public class GTFOTileEntities {
    public static MetaTileEntityBioReactor[] BIOREACTOR = new MetaTileEntityBioReactor[14];
    public static SimpleMachineMetaTileEntity[] SLICER = new SimpleMachineMetaTileEntity[14];
    public static SimpleMachineMetaTileEntity[] CUISINE_ASSEMBLER = new SimpleMachineMetaTileEntity[14];

    public static MetaTileEntityBakingOven BAKING_OVEN;
    public static MetaTileEntityElectricBakingOven ELECTRIC_BAKING_OVEN;


    public static void init() {
        BIOREACTOR[2] = MetaTileEntities.registerMetaTileEntity(8500, new MetaTileEntityBioReactor(location("bioreactor.hv"), 3));
        BIOREACTOR[3] = MetaTileEntities.registerMetaTileEntity(8501, new MetaTileEntityBioReactor(location("bioreactor.ev"), 4));
        BIOREACTOR[4] = MetaTileEntities.registerMetaTileEntity(8502, new MetaTileEntityBioReactor(location("bioreactor.iv"), 5));

        MetaTileEntities.registerSimpleMetaTileEntity(SLICER, 8503, "slicer", GTFORecipeMaps.SLICER_RECIPES, GTFOClientHandler.SLICER_OVERLAY, false, GTFOTileEntities::location, GTUtility.hvCappedTankSizeFunction);
        MetaTileEntities.registerSimpleMetaTileEntity(SLICER, 8518, "cuisine_assembler", GTFORecipeMaps.CUISINE_ASSEMBLER_RECIPES, GTFOClientHandler.CUISINE_ASSEMBLER_OVERLAY, false, GTFOTileEntities::location, GTUtility.hvCappedTankSizeFunction);


        BAKING_OVEN = MetaTileEntities.registerMetaTileEntity(8516, new MetaTileEntityBakingOven(location("baking_oven")));
        ELECTRIC_BAKING_OVEN = MetaTileEntities.registerMetaTileEntity(8517, new MetaTileEntityElectricBakingOven(location("electric_baking_oven")));


    }

    public static ResourceLocation location(String name) {
        return new ResourceLocation(GregTechFoodOption.MODID, name);
    }
}
