package com.bruberu.gregtechfoodoption;

import com.bruberu.gregtechfoodoption.item.GTFOOredictItem;
import com.bruberu.gregtechfoodoption.material.GTFOFluidMaterial;
import gregtech.api.unification.material.IMaterialHandler;
import gregtech.api.unification.material.MaterialIconSet;
import gregtech.api.unification.ore.OrePrefix;

public class GTFOMaterialHandler implements IMaterialHandler {

    public static final GTFOFluidMaterial PopcornFlavoringMixture = new GTFOFluidMaterial("popcorn_flavoring_mixture", 0xffcb21, 350);
    public static final GTFOFluidMaterial Diacetyl = new GTFOFluidMaterial("diacetyl", 0xf5fcae, "C4H6O2");
    public static final GTFOFluidMaterial IiAcetylpyridine = new GTFOFluidMaterial("2-acetylpyridine", 0xe0d5c3, "C7H7NO");
    public static final GTFOFluidMaterial Acetoin = new GTFOFluidMaterial("acetoin", 0xfcffdb, "C4H8O2");
    public static final GTFOFluidMaterial AcetylChloride = new GTFOFluidMaterial("acetyl_chloride", 0xe0e0da, "CH3COCl");
    public static final GTFOFluidMaterial Tetrahydrofuran = new GTFOFluidMaterial("tetrahydrofuran", 0x7d9899, "C4H8O");
    public static final GTFOFluidMaterial IiBromopyridine = new GTFOFluidMaterial("2-bromopyridine", 0xc0c0ca, "C5H4NBr");
    public static final GTFOFluidMaterial IiIiiButanediol = new GTFOFluidMaterial("2,3-butanediol", 0xedf5e1, "C4H8O");
    public static final GTFOFluidMaterial IIvButanediol = new GTFOFluidMaterial("1,4-butanediol", 0xedf5e1, "C4H10O2");
    public static final GTFOFluidMaterial IIvButynediol = new GTFOFluidMaterial("1,4-butynediol", 0xedf5e1, "C4H6O2");
    public static final GTFOFluidMaterial FungalGrowthMedium = new GTFOFluidMaterial("fungal_growth_medium", 0xd9cfad);
    public static final GTFOFluidMaterial PeptoneMixture = new GTFOFluidMaterial("peptone_mixture", 0xd1ad30);
    public static final GTFOFluidMaterial IsopropylChloride = new GTFOFluidMaterial("isopropyl_chloride", 0xa8a89d, "(CH3)2CHCl");


    public static final GTFOOredictItem.OreDictItem GRADED_POPCORN_KERNEL = new GTFOOredictItem.OreDictItem(1000, "popcorn_kernel_graded", 0xffea70, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem BARE_POPCORN_KERNEL = new GTFOOredictItem.OreDictItem(1001, "popcorn_kernel_bare", 0xfecb60, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem POPCORN_KERNEL = new GTFOOredictItem.OreDictItem(1002, "popcorn_kernel", 0xfecb60, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem PHYCOMYCES_BLAKESLEEANUS = new GTFOOredictItem.OreDictItem(1003, "phycomyces_blakesleeanus", 0x454442, MaterialIconSet.ROUGH, OrePrefix.dust);
    public static final GTFOOredictItem.OreDictItem BETA_CAROTENE = new GTFOOredictItem.OreDictItem(1004, "beta_carotene", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "C40H56");
    public static final GTFOOredictItem.OreDictItem ISOPROPYLMAGNESIUM_CHLORIDE = new GTFOOredictItem.OreDictItem(1005, "isopropylmagnesium_chloride", 0x211210, MaterialIconSet.SHINY, OrePrefix.dust, "C3H7ClMg");
    public static final GTFOOredictItem.OreDictItem II_AMINOPYRIDINE = new GTFOOredictItem.OreDictItem(1006, "2-aminopyridine", 0xf0ede9, MaterialIconSet.LAPIS, OrePrefix.dust, "C5H6N2");


    @Override
    public void onMaterialsInit() {

    }
}
