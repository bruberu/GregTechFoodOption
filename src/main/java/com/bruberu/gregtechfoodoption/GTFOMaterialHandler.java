package com.bruberu.gregtechfoodoption;

import com.bruberu.gregtechfoodoption.item.GTFOOredictItem;
import com.bruberu.gregtechfoodoption.material.GTFOFluidMaterial;
import gregicadditions.GAEnums;
import gregtech.api.unification.material.IMaterialHandler;
import gregtech.api.unification.material.MaterialIconSet;
import gregtech.api.unification.ore.OrePrefix;

@IMaterialHandler.RegisterMaterialHandler
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

    public static final GTFOFluidMaterial Iodomethane = new GTFOFluidMaterial("iodomethane", 0xa8a89d, "CH3I");
    public static final GTFOFluidMaterial DimethylSulfoxide = new GTFOFluidMaterial("dimethyl_sulfoxide", 0xa8a89d, "C2H6OS");
    public static final GTFOFluidMaterial IodineCxxixTrichloride = new GTFOFluidMaterial("iodine_129_trichloride", 0xa8a89d, "ICl3");
    public static final GTFOFluidMaterial ChlorineFluoride = new GTFOFluidMaterial("chlorine_fluoride", 0xa8a89d, "ClF");
    public static final GTFOFluidMaterial ChlorineTrifluoride = new GTFOFluidMaterial("chlorine_trifluoride", 0xa8a89d, "ClF3");
    public static final GTFOFluidMaterial XenonTetrachloride = new GTFOFluidMaterial("xenon_tetrachloride", 0xa8a89d, "XeCl4");
    public static final GTFOFluidMaterial TribromochloromoscoviumTrichloroxenonComplex = new GTFOFluidMaterial("tribromochloromoscovium_trichloroxenon_complex", 0xa8a89d, "XeCl3McBr3Cl");
    public static final GTFOFluidMaterial TetrabromomoscoviumTrichloroxenonComplex = new GTFOFluidMaterial("tribromochloromoscovium_trichloroxenon_complex", 0xa8a89d, "XeCl3McBr4");
    public static final GTFOFluidMaterial HydroastaticAcid = new GTFOFluidMaterial("hydroastatic_acid", 0xa8a89d, "HAt");
    public static final GTFOFluidMaterial EntangledGluons = new GTFOFluidMaterial("entangled_gluons", 0xa8a89d);
    public static final GTFOFluidMaterial HighlyPolymerizedMoscoviumPentahalides = new GTFOFluidMaterial("highly_polymerized_moscovium_pentahalides", 0xa8a89);
    public static final GTFOFluidMaterial Trimethylmoscovide = new GTFOFluidMaterial("trimethylmoscovide", 0xa8a89d, "(CH3)3Mc");
    public static final GTFOFluidMaterial Bromomethane = new GTFOFluidMaterial("bromomethane", 0xa8a89d, "CH3Br");


    public static final GTFOOredictItem.OreDictItem GradedPopcornKernel = new GTFOOredictItem.OreDictItem(1000, "popcorn_kernel_graded", 0xffea70, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem BarePopcornKernel = new GTFOOredictItem.OreDictItem(1001, "popcorn_kernel_bare", 0xfecb60, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem PopcornKernel = new GTFOOredictItem.OreDictItem(1002, "popcorn_kernel", 0xfecb60, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem PhycomycesBlakesleeanus = new GTFOOredictItem.OreDictItem(1003, "phycomyces_blakesleeanus", 0x454442, MaterialIconSet.ROUGH, OrePrefix.dust);
    public static final GTFOOredictItem.OreDictItem BetaCarotene = new GTFOOredictItem.OreDictItem(1004, "beta_carotene", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "C40H56");
    public static final GTFOOredictItem.OreDictItem IsopropylmagnesiumChloride = new GTFOOredictItem.OreDictItem(1005, "isopropylmagnesium_chloride", 0x211210, MaterialIconSet.SHINY, OrePrefix.dust, "C3H7ClMg");
    public static final GTFOOredictItem.OreDictItem IiAminopyridine = new GTFOOredictItem.OreDictItem(1006, "2-aminopyridine", 0xf0ede9, MaterialIconSet.LAPIS, OrePrefix.dust, "C5H6N2");


    public static final GTFOOredictItem.OreDictItem MoscoviumPentafluoride = new GTFOOredictItem.OreDictItem(1004, "moscovium_pentafluoride", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "McF5");
    public static final GTFOOredictItem.OreDictItem MoscoviumPentachloride = new GTFOOredictItem.OreDictItem(1004, "moscovium_pentachloride", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "McCl5");
    public static final GTFOOredictItem.OreDictItem MoscoviumPentabromide = new GTFOOredictItem.OreDictItem(1004, "moscovium_pentabromide", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "McBr5");
    public static final GTFOOredictItem.OreDictItem MoscoviumPentaiodide = new GTFOOredictItem.OreDictItem(1004, "moscovium_pentaiodide", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "McI5");
    public static final GTFOOredictItem.OreDictItem MoscoviumPentaastatide = new GTFOOredictItem.OreDictItem(1004, "moscovium_pentaastitide", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "McAt5");
    public static final GTFOOredictItem.OreDictItem MoscoviumPentatennesside = new GTFOOredictItem.OreDictItem(1004, "moscovium_pentatenneside", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "McTs5");

    public static final GTFOOredictItem.OreDictItem HydrotennessiticAcid = new GTFOOredictItem.OreDictItem(1004, "hydrotennessitic_acid", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "HTs");
    public static final GTFOOredictItem.OreDictItem SupercooledHydrotennessiticAcid = new GTFOOredictItem.OreDictItem(1004, "supercooled_hydrotennessitic_acid", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "HTs");
    public static final GTFOOredictItem.OreDictItem SupercooledTennessine = new GTFOOredictItem.OreDictItem(1004, "supercooled_tennessine", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "Ts");
    public static final GTFOOredictItem.OreDictItem MoscoviumIOxide = new GTFOOredictItem.OreDictItem(1004, "moscovium(I)_oxide", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "Mc2O");
    public static final GTFOOredictItem.OreDictItem MoscoviumTennesside = new GTFOOredictItem.OreDictItem(1004, "moscovium_tennesside", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "McTs");
    public static final GTFOOredictItem.OreDictItem SupercooledMoscoviumTennesside = new GTFOOredictItem.OreDictItem(1004, "supercooled_moscovium_tennesside", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "McTs");
    public static final GTFOOredictItem.OreDictItem ThalliumSulfate = new GTFOOredictItem.OreDictItem(1004, "thallium_sulfate", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "Tl2SO4");
    public static final GTFOOredictItem.OreDictItem MoscoviumAstatide = new GTFOOredictItem.OreDictItem(1004, "moscovium_astatide", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "McAt");
    public static final GTFOOredictItem.OreDictItem ThalliumAstatide = new GTFOOredictItem.OreDictItem(1004, "thallium_astatide", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "TlAt");
    public static final GTFOOredictItem.OreDictItem DopedThalliumAstatide = new GTFOOredictItem.OreDictItem(1004, "doped_thallium_astatide", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "TlAtMcAt");
    public static final GTFOOredictItem.OreDictItem DopedThalliumAstatidePlate = new GTFOOredictItem.OreDictItem(1004, "doped_thallium_astatide", 0xab341f, MaterialIconSet.SAND, OrePrefix.plate, "TlAtMcAt");
    public static final GTFOOredictItem.OreDictItem HeatedDopedThalliumAstatide = new GTFOOredictItem.OreDictItem(1004, "heated_doped_thallium_astatide", 0xab341f, MaterialIconSet.SAND, OrePrefix.plate, "TlAtMcAt");
    public static final GTFOOredictItem.OreDictItem ThalliumAstatideConductor = new GTFOOredictItem.OreDictItem(1004, "thallium_astatide_conductor", 0xab341f, MaterialIconSet.SAND, OrePrefix.plate, "TlAtMcAt");
    public static final GTFOOredictItem.OreDictItem ShockedThalliumAstatideConductor = new GTFOOredictItem.OreDictItem(1004, "shocked_thallium_astatide", 0xab341f, MaterialIconSet.SAND, OrePrefix.plate, "TlAtMcAt5");
    public static final GTFOOredictItem.OreDictItem ShockedThalliumAstatideDust = new GTFOOredictItem.OreDictItem(1004, "shocked_thallium_astatide", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "TlAtMcAt5");
    public static final GTFOOredictItem.OreDictItem LithiumMoscovide = new GTFOOredictItem.OreDictItem(1004, "lithium_moscovide", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "LiMc");
    public static final GTFOOredictItem.OreDictItem Methyllithium = new GTFOOredictItem.OreDictItem(1004, "methyllithium", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "CH3Li");
    public static final GTFOOredictItem.OreDictItem MethylMoscoviumDiiodide = new GTFOOredictItem.OreDictItem(1004, "methyl_moscovium_diiodide", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "CH3McI2");
    public static final GTFOOredictItem.OreDictItem MoscoviumIodide = new GTFOOredictItem.OreDictItem(1004, "moscovium_iodide", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "McI");
    public static final GTFOOredictItem.OreDictItem FissionedUraniumCcxxxv = new GTFOOredictItem.OreDictItem(1004, "fissioned_uranium_235", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "???????");
    public static final GTFOOredictItem.OreDictItem IodineCxxix = new GTFOOredictItem.OreDictItem(1004, "iodine_129", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "I");
    public static final GTFOOredictItem.OreDictItem MoscoviumTribromide = new GTFOOredictItem.OreDictItem(1004, "moscovium_tribromide", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "McBr3");
    public static final GTFOOredictItem.OreDictItem MoscoviumIiiOxide = new GTFOOredictItem.OreDictItem(1004, "moscovium(III)_oxide", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "Mc2O3");
    public static final GTFOOredictItem.OreDictItem MoscoviumTrichloride = new GTFOOredictItem.OreDictItem(1004, "moscovium_trichloride", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "McCl3");
    public static final GTFOOredictItem.OreDictItem MoscoviumHafniumOctadecachloride = new GTFOOredictItem.OreDictItem(1004, "moscovium_hafnium_octadecachloride", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "Mc10Hf3Cl18");
    public static final GTFOOredictItem.OreDictItem TitaniumCarbide = new GTFOOredictItem.OreDictItem(1004, "titanium_carbide", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "TiC");
    public static final GTFOOredictItem.OreDictItem SaltyTitaniumDichlorideMixture = new GTFOOredictItem.OreDictItem(1004, "salty_titanium_dichloride_mixture", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "Na2Ti3Cl8");
    public static final GTFOOredictItem.OreDictItem DecayedMoscovium = new GTFOOredictItem.OreDictItem(1004, "decayed_moscovium", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "Mc3?");
    public static final GTFOOredictItem.OreDictItem HighlyIrradiatedHafniumCarbide = new GTFOOredictItem.OreDictItem(1004, "highly_irradiated_hafnium_carbide", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "(HfC)?2");
    public static final GTFOOredictItem.OreDictItem TitaniumIiiOxide = new GTFOOredictItem.OreDictItem(1004, "titanium(III)_oxide", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "Ti2O3");
    public static final GTFOOredictItem.OreDictItem TitaniumIiOxide = new GTFOOredictItem.OreDictItem(1004, "titanium(II)_oxide", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "TiO");
    public static final GTFOOredictItem.OreDictItem MoscoviumTrifluoride = new GTFOOredictItem.OreDictItem(1004, "moscovium_trifluoride", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "McF3");
    public static final GTFOOredictItem.OreDictItem WellMixedMoscoviumPentahalides = new GTFOOredictItem.OreDictItem(1004, "well_mixed_moscovium_pentahalides", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "(McF5)(McCl5)(McBr5)(McI5)(McAt5)(McTs5)");
    public static final GTFOOredictItem.OreDictItem OligoPolymerizedMoscoviumPentahalides = new GTFOOredictItem.OreDictItem(1004, "oligopolymerized_moscovium_pentahalides", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "(McF5)(McCl5)(McBr5)(McI5)(McAt5)(McTs5)");
    public static final GTFOOredictItem.OreDictItem PolymoscoviumPentahalide = new GTFOOredictItem.OreDictItem(1004, "polymoscovium_pentahalide", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "(McF5)(McCl5)(McBr5)(McI5)(McAt5)(McTs5)");
    public static final GTFOOredictItem.OreDictItem PolymoscoviumPentahalideSheet = new GTFOOredictItem.OreDictItem(1004, "polymoscovium_pentahalide", 0xab341f, MaterialIconSet.SAND, OrePrefix.plate, "(McF5)(McCl5)(McBr5)(McI5)(McAt5)(McTs5)");
    public static final GTFOOredictItem.OreDictItem NonToxicPolymoscoviumPentahalideSheet = new GTFOOredictItem.OreDictItem(1004, "nontoxic_polymoscovium_pentahalide", 0xab341f, MaterialIconSet.SAND, OrePrefix.plate);
    public static final GTFOOredictItem.OreDictItem NonToxicPolymoscoviumPentahalideCurvedSheet = new GTFOOredictItem.OreDictItem(1004, "nontoxic_polymoscovium_pentahalide", 0xab341f, MaterialIconSet.SAND, GAEnums.GAOrePrefix.plateCurved);



    /* Material IDs 1017 to 1030 reserved for Coffee chain */
    /* Material IDs 1035 to 1051 reserved for S'mogus chain */

    @Override
    public void onMaterialsInit() {
    }
}
