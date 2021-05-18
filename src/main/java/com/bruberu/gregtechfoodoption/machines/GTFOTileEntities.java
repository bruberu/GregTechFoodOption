package com.bruberu.gregtechfoodoption.machines;

import com.bruberu.gregtechfoodoption.GregTechFoodOption;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityBioReactor;
import gregicadditions.client.ClientHandler;
import gregicadditions.gui.GAGuiTextures;
import gregicadditions.recipes.GARecipeMaps;
import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.metatileentity.ITieredMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import net.minecraft.util.ResourceLocation;

/* Takes up IDs 8500 - 8599 */

public class GTFOTileEntities {
    public static MetaTileEntityBioReactor[] BIOREACTOR = new MetaTileEntityBioReactor[14];

    public static void init() {
        BIOREACTOR[2] = GregTechAPI.registerMetaTileEntity(8500, new MetaTileEntityBioReactor(location("bioreactor.hv"), 3));
        BIOREACTOR[3] = GregTechAPI.registerMetaTileEntity(8501, new MetaTileEntityBioReactor(location("bioreactor.ev"), 4));
        BIOREACTOR[4] = GregTechAPI.registerMetaTileEntity(8502, new MetaTileEntityBioReactor(location("bioreactor.iv"), 5));

    }

    /*
    public static class MTE<T extends MetaTileEntity & ITieredMetaTileEntity> {

        private final T t;

        MTE(T t) {
            this.t = t;
        }

        public MetaTileEntity getMetaTileEntity() {
            return t;
        }

        public ITieredMetaTileEntity getITieredMetaTileEntity() {
            return t;
        }
    }


    public static <T extends MetaTileEntity & ITieredMetaTileEntity> MTE<T> create(int id, T sampleMetaTileEntity) {
        return new MTE<>(GregTechAPI.registerMetaTileEntity(id, sampleMetaTileEntity));
    }
    */
    public static ResourceLocation location(String name) {
        return new ResourceLocation(GregTechFoodOption.MODID, name);
    }

    private static ResourceLocation gregtechId(String name) {
        return new ResourceLocation(GTValues.MODID, name);
    }
}
