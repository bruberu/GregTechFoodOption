package com.bruberu.gregtechfoodoption.machines;

import com.bruberu.gregtechfoodoption.GregTechFoodOption;
import com.bruberu.gregtechfoodoption.client.GTFOClientHandler;
import com.bruberu.gregtechfoodoption.recipe.GTFORecipeMaps;
import gregicadditions.machines.GATileEntities;
import gregicadditions.machines.overrides.GASimpleMachineMetaTileEntity;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import net.minecraft.util.ResourceLocation;

import static gregicadditions.machines.GATileEntities.create;

/* Takes up IDs 8500 - 8599 */
public class GTFOTileEntities {
    public static MetaTileEntityBioReactor[] BIOREACTOR = new MetaTileEntityBioReactor[14];
    public static GATileEntities.MTE<?>[] SLICER = new GATileEntities.MTE[14];


    public static void init() {
        BIOREACTOR[2] = GregTechAPI.registerMetaTileEntity(8500, new MetaTileEntityBioReactor(location("bioreactor.hv"), 3));
        BIOREACTOR[3] = GregTechAPI.registerMetaTileEntity(8501, new MetaTileEntityBioReactor(location("bioreactor.ev"), 4));
        BIOREACTOR[4] = GregTechAPI.registerMetaTileEntity(8502, new MetaTileEntityBioReactor(location("bioreactor.iv"), 5));

        SLICER[0] = create(8503, new SimpleMachineMetaTileEntity(location("slicer.lv"), GTFORecipeMaps.SLICER_RECIPES, GTFOClientHandler.SLICER_OVERLAY, 1));
        SLICER[1] = create(8504, new SimpleMachineMetaTileEntity(location("slicer.mv"), GTFORecipeMaps.SLICER_RECIPES, GTFOClientHandler.SLICER_OVERLAY, 2));
        SLICER[2] = create(8505, new SimpleMachineMetaTileEntity(location("slicer.hv"), GTFORecipeMaps.SLICER_RECIPES, GTFOClientHandler.SLICER_OVERLAY, 3));
        SLICER[3] = create(8506, new SimpleMachineMetaTileEntity(location("slicer.ev"), GTFORecipeMaps.SLICER_RECIPES, GTFOClientHandler.SLICER_OVERLAY, 4));
        SLICER[4] = create(8507, new SimpleMachineMetaTileEntity(location("slicer.iv"), GTFORecipeMaps.SLICER_RECIPES, GTFOClientHandler.SLICER_OVERLAY, 5));
        SLICER[5] = create(8508, new SimpleMachineMetaTileEntity(location("slicer.luv"), GTFORecipeMaps.SLICER_RECIPES, GTFOClientHandler.SLICER_OVERLAY, 6));
        SLICER[6] = create(8509, new SimpleMachineMetaTileEntity(location("slicer.zpm"), GTFORecipeMaps.SLICER_RECIPES, GTFOClientHandler.SLICER_OVERLAY, 7));
        SLICER[7] = create(8510, new SimpleMachineMetaTileEntity(location("slicer.uv"), GTFORecipeMaps.SLICER_RECIPES, GTFOClientHandler.SLICER_OVERLAY, 8));
        SLICER[8] = create(8511, new GASimpleMachineMetaTileEntity(location("slicer.uhv"), GTFORecipeMaps.SLICER_RECIPES, GTFOClientHandler.SLICER_OVERLAY, 9));
        SLICER[9] = create(8512, new GASimpleMachineMetaTileEntity(location("slicer.uev"), GTFORecipeMaps.SLICER_RECIPES, GTFOClientHandler.SLICER_OVERLAY, 10));
        SLICER[10] = create(8513, new GASimpleMachineMetaTileEntity(location("slicer.uiv"), GTFORecipeMaps.SLICER_RECIPES, GTFOClientHandler.SLICER_OVERLAY, 11));
        SLICER[11] = create(8514, new GASimpleMachineMetaTileEntity(location("slicer.umv"), GTFORecipeMaps.SLICER_RECIPES, GTFOClientHandler.SLICER_OVERLAY, 12));
        SLICER[12] = create(8515, new GASimpleMachineMetaTileEntity(location("slicer.uxv"), GTFORecipeMaps.SLICER_RECIPES, GTFOClientHandler.SLICER_OVERLAY, 13));
    }

    public static ResourceLocation location(String name) {
        return new ResourceLocation(GregTechFoodOption.MODID, name);
    }
}
