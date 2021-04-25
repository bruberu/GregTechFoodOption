package com.bruberu.gregtechfoodoption;

import com.bruberu.gregtechfoodoption.item.GTFOOredictItem;
import com.bruberu.gregtechfoodoption.material.GTFOFluidMaterial;
import gregtech.api.unification.material.IMaterialHandler;
import gregtech.api.unification.material.MaterialIconSet;
import gregtech.api.unification.ore.OrePrefix;

public class GTFOMaterialHandler implements IMaterialHandler {

    public static final GTFOFluidMaterial POPCORN_FLAVORING_MIXTURE = new GTFOFluidMaterial("popcorn_flavoring_mixture", 0xffcb21, 350);
    public static final GTFOFluidMaterial DIACETYL = new GTFOFluidMaterial("diacetyl", 0xf5fcae, "C4H6O2");
    public static final GTFOFluidMaterial II_ACETYLPYRIDINE = new GTFOFluidMaterial("2-acetylpyridine", 0xe0d5c3, "C7H7NO");
    public static final GTFOFluidMaterial ACETOIN = new GTFOFluidMaterial("acetoin", 0xfcffdb, "C4H8O2");
    public static final GTFOFluidMaterial ACETYL_CHLORIDE = new GTFOFluidMaterial("acetyl_chloride", 0xe0e0da, "CH3COCl");
    public static final GTFOFluidMaterial TETRAHYDROFURAN = new GTFOFluidMaterial("tetrahydrofuran", 0x7d9899, "C4H8O");
    public static final GTFOFluidMaterial II_BROMOPYRIDINE = new GTFOFluidMaterial("2-bromopyridine", 0xc0c0ca, "C5H4NBr");
    public static final GTFOFluidMaterial II_III_BUTANEDIOL = new GTFOFluidMaterial("2,3-butanediol", 0xedf5e1, "C4H8O");
    public static final GTFOFluidMaterial FUNGAL_GROWTH_MEDIUM = new GTFOFluidMaterial("fungal_growth_medium", 0xd9cfad);
    public static final GTFOFluidMaterial PEPTONE_MIXTURE = new GTFOFluidMaterial("peptone_mixture", 0xd1ad30);
    public static final GTFOFluidMaterial ISOPROPYL_CHLORIDE = new GTFOFluidMaterial("isopropyl_chloride", 0xa8a89d, "(CH3)2CHCl");


    public static final GTFOOredictItem.OreDictItem GRADED_POPCORN_KERNEL = new GTFOOredictItem.OreDictItem(1000, "popcorn_kernel_graded", 0xffea70, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem BARE_POPCORN_KERNEL = new GTFOOredictItem.OreDictItem(1001, "popcorn_kernel_bare", 0xfecb60, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem POPCORN_KERNEL = new GTFOOredictItem.OreDictItem(1002, "popcorn_kernel", 0xfecb60, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem PHYCOMYCES_BLAKESLEEANUS = new GTFOOredictItem.OreDictItem(1003, "phycomyces_blakeseeanus", 0x454442, MaterialIconSet.ROUGH, OrePrefix.dust);
    public static final GTFOOredictItem.OreDictItem BETA_CAROTENE = new GTFOOredictItem.OreDictItem((short) 1004, "beta_carotene", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "C40H56");
    public static final GTFOOredictItem.OreDictItem ISOPROPYLMAGNESIUM_CHLORIDE = new GTFOOredictItem.OreDictItem(1005, "isopropylmagnesium_chloride", 0x211210, MaterialIconSet.SHINY, OrePrefix.dust);
    public static final GTFOOredictItem.OreDictItem FERRIC_NITRATE = new GTFOOredictItem.OreDictItem(1006, "ferric_nitrate", 0xfcefed, MaterialIconSet.ROUGH, OrePrefix.dust);
    public static final GTFOOredictItem.OreDictItem SODIUM_AMIDE = new GTFOOredictItem.OreDictItem(1007, "sodium_amide", 0x999999, MaterialIconSet.DULL, OrePrefix.dust);
    public static final GTFOOredictItem.OreDictItem II_AMINOPYRIDINE = new GTFOOredictItem.OreDictItem(1008, "2-aminopyridine", 0xf0ede9, MaterialIconSet.LAPIS, OrePrefix.dust);


    @Override
    public void onMaterialsInit() {

    }
}
