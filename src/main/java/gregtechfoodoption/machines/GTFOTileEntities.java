package gregtechfoodoption.machines;

import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeMaps;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.metatileentities.electric.MetaTileEntityMacerator;
import gregtechfoodoption.GregTechFoodOption;
import gregtechfoodoption.client.GTFOClientHandler;
import gregtechfoodoption.recipe.GTFORecipeMaps;
import gregtechfoodoption.machines.multiblock.MetaTileEntityBakingOven;
import gregtechfoodoption.machines.multiblock.MetaTileEntityElectricBakingOven;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.util.GTUtility;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.util.ResourceLocation;

import static gregtech.common.metatileentities.MetaTileEntities.*;

/* Takes up IDs 8500 - 8599 */
public class GTFOTileEntities {
    //public static MetaTileEntityBioReactor[] BIOREACTOR = new MetaTileEntityBioReactor[GTValues.V.length];
    public static SimpleMachineMetaTileEntity[] SLICER = new SimpleMachineMetaTileEntity[GTValues.V.length];
    public static SimpleMachineMetaTileEntity[] CUISINE_ASSEMBLER = new SimpleMachineMetaTileEntity[GTValues.V.length];
    public static MetaTileEntityMicrowave[] MICROWAVE = new MetaTileEntityMicrowave[GTValues.V.length];


    public static MetaTileEntityBakingOven BAKING_OVEN;
    public static MetaTileEntityElectricBakingOven ELECTRIC_BAKING_OVEN;


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
            MICROWAVE[12] = registerMetaTileEntity(8542, new MetaTileEntityMicrowave(location("microwave.umv"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 12));
            MICROWAVE[13] = registerMetaTileEntity(8543, new MetaTileEntityMicrowave(location("microwave.uxv"), GTFORecipeMaps.MICROWAVE_RECIPES, GTFOClientHandler.MICROWAVE_OVERLAY, 13));
        }
        BAKING_OVEN = registerMetaTileEntity(8516, new MetaTileEntityBakingOven(location("baking_oven")));
        ELECTRIC_BAKING_OVEN = registerMetaTileEntity(8517, new MetaTileEntityElectricBakingOven(location("electric_baking_oven")));
    }

    public static ResourceLocation location(String name) {
        return new ResourceLocation(GregTechFoodOption.MODID, name);
    }
}
