package gregtechfoodoption.integration;

import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.info.MaterialIconSet;
import gregtech.api.unification.ore.OrePrefix;
import gregtechfoodoption.item.GTFOOredictItem;
import gregtechfoodoption.material.GTFOFluidMaterial;
import gregtechfoodoption.utils.RecipeUtils;

import static gregtechfoodoption.GTFOMaterialHandler.*;
import static gregtechfoodoption.utils.RecipeUtils.average;

public class GTFOGAMaterialHandler {

    /* Will be added back at a later date, when Gregicality: Science comes out. TODO
        public static final GTFOFluidMaterial FungalRennetSolution = new GTFOFluidMaterial("fungal_rennet_solution", CrudeRennetSolution.rgb/2 + Yeast.getMaterialRGB()/4 + 0x2a7b5a/4);

        public static final GTFOFluidMaterial Iodomethane = new GTFOFluidMaterial("iodomethane", average(2, GAMaterials.Iodine.getMaterialRGB(), Materials.Methane.getMaterialRGB()), "CH3I");
        public static final GTFOFluidMaterial DimethylSulfoxide = new GTFOFluidMaterial("dimethyl_sulfoxide", average(2, Materials.Oxygen.getMaterialRGB(), GAMaterials.Dimethylsulfide.getMaterialRGB()), "C2H6OS");
        public static final GTFOFluidMaterial IodineCxxixTrichloride = new GTFOFluidMaterial("iodine_129_trichloride", 0xd8d8e6, "ICl3", 600);
        public static final GTFOFluidMaterial ChlorineFluoride = new GTFOFluidMaterial("chlorine_fluoride", average(2, Materials.Fluorine.getMaterialRGB(), Materials.Chlorine.getMaterialRGB()), "ClF");
        public static final GTFOFluidMaterial ChlorineTrifluoride = new GTFOFluidMaterial("chlorine_trifluoride", average(4, Materials.Fluorine.getMaterialRGB() * 3, Materials.Chlorine.getMaterialRGB()), "ClF3");
        public static final GTFOFluidMaterial XenonTetrachloride = new GTFOFluidMaterial("xenon_tetrachloride", 0xb0b0ff, "XeCl4");
        public static final GTFOFluidMaterial TribromochloromoscoviumTrichloroxenonComplex = new GTFOFluidMaterial("tribromochloromoscovium_trichloroxenon_complex", 0x4d2d5c, "XeCl3McBr3Cl");
        public static final GTFOFluidMaterial TetrabromomoscoviumTrichloroxenonComplex = new GTFOFluidMaterial("tetrabromomoscovium_trichloroxenon_complex", 0x5c2051, "XeCl3McBr4");
        public static final GTFOFluidMaterial HydroastaticAcid = new GTFOFluidMaterial("hydroastatic_acid", average(2, Materials.Hydrogen.getMaterialRGB(), GAMaterials.Astatine.getMaterialRGB()), "HAt", 2000);
        public static final GTFOFluidMaterial EntangledGluons = new GTFOFluidMaterial("entangled_gluons", GAMaterials.Gluons.getMaterialRGB() + 8, 1500);
        public static final GTFOFluidMaterial HighlyPolymerizedMoscoviumPentahalides = new GTFOFluidMaterial("highly_polymerized_moscovium_pentahalides", 0xa971f0, 6000);
        public static final GTFOFluidMaterial Trimethylmoscovide = new GTFOFluidMaterial("trimethylmoscovide", average(4, Materials.Methane.getMaterialRGB() * 3, GAMaterials.Moscovium.getMaterialRGB()), "(CH3)3Mc");
        public static final GTFOFluidMaterial Bromomethane = new GTFOFluidMaterial("bromomethane", average(2, GAMaterials.Bromine.getMaterialRGB(), Materials.Methane.getMaterialRGB()), "CH3Br");
        public static final GTFOFluidMaterial BromineMonochloride = new GTFOFluidMaterial("bromine_monochloride", average(2, GAMaterials.Bromine.getMaterialRGB(), Materials.Chlorine.getMaterialRGB()), "BrCl");

        public static final GTFOFluidMaterial HFCSLv = new GTFOFluidMaterial("hfcs_55", 0xcfa500);
        public static final GTFOFluidMaterial HFCSXlii = new GTFOFluidMaterial("hfcs_42", 0xc4a837);
        public static final GTFOFluidMaterial HFCSXc = new GTFOFluidMaterial("hfcs_90", 0xc9b977);
        public static final GTFOFluidMaterial LemonLimeSolution = new GTFOFluidMaterial("lemon_lime_solution", 0xbddb5a);
        public static final GTFOFluidMaterial LemonLimeSludge = new GTFOFluidMaterial("lemon_lime_sludge", 0x779906);
        public static final GTFOFluidMaterial CornStarchSolution = new GTFOFluidMaterial("corn_starch_solution", 0xd4d3c5);
        public static final GTFOFluidMaterial OligosaccharideSolution = new GTFOFluidMaterial("oligosaccharide_solution", 0xa8a59d);
        public static final GTFOFluidMaterial GlucoseSolution = new GTFOFluidMaterial("glucose_solution", 0xf5f3ed);
        public static final GTFOFluidMaterial HighFructoseCornSyrupSolution = new GTFOFluidMaterial("hfcs_solution", 0xe3bc20);
        public static final GTFOFluidMaterial XPhenothiazineIiPropylChloride = new GTFOFluidMaterial("10-phenothiazine-2-propyl_chloride", average(2, 0x67735c, IsopropylChloride.rgb), "C15H16NSCl");
        public static final GTFOFluidMaterial AppleSyrup = new GTFOFluidMaterial("apple_syrup", 0xf2e1ac);
        public static final GTFOFluidMaterial AppleCandySyrup = new GTFOFluidMaterial("apple_candy_syrup", 0xe7f5ae);
        public static final GTFOFluidMaterial Etirps = new GTFOFluidMaterial("etirps", 0xb0ff73);
        public static final GTFOFluidMaterial LemonLimeSodaSyrup = new GTFOFluidMaterial("lemon_lime_soda_syrup", 0x76ff0d);
        public static final GTFOFluidMaterial CarbonatedWater = new GTFOFluidMaterial("carbonated_water", 0xf5ffff);
        public static final GTFOFluidMaterial CoughSyrup = new GTFOFluidMaterial("cough_syrup", 0x5c1b5e);

    public static final GTFOOredictItem.OreDictItem GradedPopcornKernel = new GTFOOredictItem.OreDictItem(1000, "popcorn_kernel_graded", 0xffea70, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);
    public static final GTFOOredictItem.OreDictItem BarePopcornKernel = new GTFOOredictItem.OreDictItem(1001, "popcorn_kernel_bare", 0xfecb60, MaterialIconSet.GEM_HORIZONTAL, OrePrefix.gemChipped);

    public static final GTFOOredictItem.OreDictItem PhycomycesBlakesleeanus = new GTFOOredictItem.OreDictItem(1003, "phycomyces_blakesleeanus", 0x454442, MaterialIconSet.ROUGH, OrePrefix.dust);
    public static final GTFOOredictItem.OreDictItem BetaCarotene = new GTFOOredictItem.OreDictItem(1004, "beta_carotene", 0xab341f, MaterialIconSet.SAND, OrePrefix.dust, "C40H56");
    public static final GTFOOredictItem.OreDictItem IsopropylmagnesiumChloride = new GTFOOredictItem.OreDictItem(1005, "isopropylmagnesium_chloride", 0x211210, MaterialIconSet.SHINY, OrePrefix.dust, "C3H7ClMg");
    public static final GTFOOredictItem.OreDictItem IiAminopyridine = new GTFOOredictItem.OreDictItem(1006, "2-aminopyridine", 0xf0ede9, MaterialIconSet.LAPIS, OrePrefix.dust, "C5H6N2");

        public static final GTFOOredictItem.OreDictItem MoscoviumPentafluoride = new GTFOOredictItem.OreDictItem(1007, "moscovium_pentafluoride", (GAMaterials.Moscovium.getMaterialRGB() + 5 * Materials.Fluorine.getMaterialRGB())/6, MaterialIconSet.SAND, OrePrefix.dust, "McF5");
        public static final GTFOOredictItem.OreDictItem MoscoviumPentachloride = new GTFOOredictItem.OreDictItem(1008, "moscovium_pentachloride", (GAMaterials.Moscovium.getMaterialRGB() + 5 * Materials.Chlorine.getMaterialRGB())/6, MaterialIconSet.SAND, OrePrefix.dust, "McCl5");
        public static final GTFOOredictItem.OreDictItem MoscoviumPentabromide = new GTFOOredictItem.OreDictItem(1009, "moscovium_pentabromide", (GAMaterials.Moscovium.getMaterialRGB() + 5 * GAMaterials.Bromine.getMaterialRGB())/6, MaterialIconSet.SAND, OrePrefix.dust, "McBr5");
        public static final GTFOOredictItem.OreDictItem MoscoviumPentaiodide = new GTFOOredictItem.OreDictItem(1010, "moscovium_pentaiodide", (GAMaterials.Moscovium.getMaterialRGB() + 5 * GAMaterials.Iodine.getMaterialRGB())/6, MaterialIconSet.SAND, OrePrefix.dust, "McI5");
        public static final GTFOOredictItem.OreDictItem MoscoviumPentaastatide = new GTFOOredictItem.OreDictItem(1011, "moscovium_pentaastatide", (GAMaterials.Moscovium.getMaterialRGB() + 5 * GAMaterials.Astatine.getMaterialRGB())/6, MaterialIconSet.SAND, OrePrefix.dust, "McAt5");
        public static final GTFOOredictItem.OreDictItem MoscoviumPentatennesside = new GTFOOredictItem.OreDictItem(1012, "moscovium_pentatennesside", (GAMaterials.Moscovium.getMaterialRGB() + 5 * GAMaterials.Tennessine.getMaterialRGB())/6, MaterialIconSet.SAND, OrePrefix.dust, "McTs5");
        public static final GTFOOredictItem.OreDictItem HydrotennessiticAcid = new GTFOOredictItem.OreDictItem(1013, "hydrotennessitic_acid", RecipeUtils.average(2, Materials.Hydrogen.getMaterialRGB(), GAMaterials.Tennessine.getMaterialRGB()), MaterialIconSet.SAND, OrePrefix.dust, "HTs");
        public static final GTFOOredictItem.OreDictItem SupercooledHydrotennessiticAcid = new GTFOOredictItem.OreDictItem(1014, "supercooled_hydrotennessitic_acid", RecipeUtils.average(2, HydrotennessiticAcid.getMaterialRGB(), GAMaterials.SupercooledCryotheum.getMaterialRGB()), MaterialIconSet.SAND, OrePrefix.dust, "HTs");
        public static final GTFOOredictItem.OreDictItem SupercooledTennessine = new GTFOOredictItem.OreDictItem(1015, "supercooled_tennessine", RecipeUtils.average(2, GAMaterials.Tennessine.getMaterialRGB(), GAMaterials.SupercooledCryotheum.getMaterialRGB()), MaterialIconSet.SAND, OrePrefix.dust, "Ts");
        public static final GTFOOredictItem.OreDictItem MoscoviumIOxide = new GTFOOredictItem.OreDictItem(1016, "moscovium(I)_oxide", GAMaterials.Moscovium.getMaterialRGB() + 10, MaterialIconSet.SAND, OrePrefix.dust, "Mc2O");
        */
    /* Material IDs 1017 to 1030 reserved for Coffee chain *//*

    public static final GTFOOredictItem.OreDictItem MoscoviumTennesside = new GTFOOredictItem.OreDictItem(1031, "moscovium_tennesside", RecipeUtils.average(2, GAMaterials.Moscovium.getMaterialRGB(), GAMaterials.Tennessine.getMaterialRGB()), MaterialIconSet.SAND, OrePrefix.dust, "McTs");
    public static final GTFOOredictItem.OreDictItem SupercooledMoscoviumTennesside = new GTFOOredictItem.OreDictItem(1032, "supercooled_moscovium_tennesside", RecipeUtils.average(2, MoscoviumTennesside.getMaterialRGB(), GAMaterials.SupercooledCryotheum.getMaterialRGB()), MaterialIconSet.SAND, OrePrefix.dust, "McTs");
    public static final GTFOOredictItem.OreDictItem ThalliumSulfate = new GTFOOredictItem.OreDictItem(1033, "thallium_sulfate", RecipeUtils.average(3, GAMaterials.Thallium.getMaterialRGB() * 2, Materials.SulfuricAcid.getMaterialRGB()), MaterialIconSet.SAND, OrePrefix.dust, "Tl2SO4");
    public static final GTFOOredictItem.OreDictItem MoscoviumAstatide = new GTFOOredictItem.OreDictItem(1034, "moscovium_astatide", RecipeUtils.average(2, GAMaterials.Moscovium.getMaterialRGB(), GAMaterials.Astatine.getMaterialRGB()), MaterialIconSet.SAND, OrePrefix.dust, "McAt");
    */
    /* Material IDs 1035 to 1051 reserved for S'mogus chain *//*

    public static final GTFOOredictItem.OreDictItem ThalliumAstatide = new GTFOOredictItem.OreDictItem(1052, "thallium_astatide", RecipeUtils.average(2, GAMaterials.Thallium.getMaterialRGB(), GAMaterials.Astatine.getMaterialRGB()), MaterialIconSet.SAND, OrePrefix.dust, "TlAt");
    public static final GTFOOredictItem.OreDictItem DopedThalliumAstatide = new GTFOOredictItem.OreDictItem(1053, "doped_thallium_astatide", RecipeUtils.average(2, ThalliumAstatide.getMaterialRGB(), MoscoviumAstatide.getMaterialRGB()), MaterialIconSet.DULL, OrePrefix.dust, "TlAtMcAt");
    public static final GTFOOredictItem.OreDictItem DopedThalliumAstatidePlate = new GTFOOredictItem.OreDictItem(1054, "doped_thallium_astatide", DopedThalliumAstatide.getMaterialRGB(), MaterialIconSet.DULL, OrePrefix.plate, "TlAtMcAt");
    public static final GTFOOredictItem.OreDictItem HeatedDopedThalliumAstatide = new GTFOOredictItem.OreDictItem(1055, "heated_doped_thallium_astatide", DopedThalliumAstatide.getMaterialRGB() + 20, MaterialIconSet.DULL, OrePrefix.plate, "TlAtMcAt");
    public static final GTFOOredictItem.OreDictItem ThalliumAstatideConductor = new GTFOOredictItem.OreDictItem(1056, "thallium_astatide_conductor", DopedThalliumAstatide.getMaterialRGB(), MaterialIconSet.SHINY, OrePrefix.plate, "TlAtMcAt");
    public static final GTFOOredictItem.OreDictItem ShockedThalliumAstatideConductor = new GTFOOredictItem.OreDictItem(1057, "shocked_thallium_astatide", DopedThalliumAstatide.getMaterialRGB() - 50, MaterialIconSet.DULL, OrePrefix.plate, "TlAtMcAt5");
    public static final GTFOOredictItem.OreDictItem ShockedThalliumAstatideDust = new GTFOOredictItem.OreDictItem(1058, "shocked_thallium_astatide", DopedThalliumAstatide.getMaterialRGB() - 60, MaterialIconSet.SAND, OrePrefix.dust, "TlAtMcAt5");
    public static final GTFOOredictItem.OreDictItem LithiumMoscovide = new GTFOOredictItem.OreDictItem(1059, "lithium_moscovide", RecipeUtils.average(2, Materials.Lithium.getMaterialRGB(), GAMaterials.Moscovium.getMaterialRGB()), MaterialIconSet.SAND, OrePrefix.dust, "LiMc");
    public static final GTFOOredictItem.OreDictItem Methyllithium = new GTFOOredictItem.OreDictItem(1060, "methyllithium", RecipeUtils.average(2, Materials.Methane.getMaterialRGB(), Materials.Lithium.getMaterialRGB()), MaterialIconSet.SAND, OrePrefix.dust, "CH3Li");
    public static final GTFOOredictItem.OreDictItem MethylMoscoviumDiiodide = new GTFOOredictItem.OreDictItem(1061, "methyl_moscovium_diiodide", 0xad6aba, MaterialIconSet.SAND, OrePrefix.dust, "CH3McI2");
    public static final GTFOOredictItem.OreDictItem MoscoviumIodide = new GTFOOredictItem.OreDictItem(1062, "moscovium_iodide", RecipeUtils.average(2, GAMaterials.Iodine.getMaterialRGB(), GAMaterials.Moscovium.getMaterialRGB()), MaterialIconSet.SAND, OrePrefix.dust, "McI");
    public static final GTFOOredictItem.OreDictItem FissionedUraniumCcxxxv = new GTFOOredictItem.OreDictItem(1063, "fissioned_uranium_235", Materials.Uranium235.getMaterialRGB() - 100, MaterialIconSet.ROUGH, OrePrefix.dust, "??????");
    public static final GTFOOredictItem.OreDictItem IodineCxxix = new GTFOOredictItem.OreDictItem(1064, "iodine_129", GAMaterials.Iodine.getMaterialRGB(), MaterialIconSet.SHINY, OrePrefix.dust, "I");
    public static final GTFOOredictItem.OreDictItem MoscoviumTribromide = new GTFOOredictItem.OreDictItem(1065, "moscovium_tribromide", RecipeUtils.average(4, GAMaterials.Moscovium.getMaterialRGB(), GAMaterials.Bromine.getMaterialRGB() * 3), MaterialIconSet.SAND, OrePrefix.dust, "McBr3");
    public static final GTFOOredictItem.OreDictItem MoscoviumIiiOxide = new GTFOOredictItem.OreDictItem(1066, "moscovium(III)_oxide", GAMaterials.Moscovium.getMaterialRGB() - 15, MaterialIconSet.SAND, OrePrefix.dust, "Mc2O3");
    public static final GTFOOredictItem.OreDictItem MoscoviumTrichloride = new GTFOOredictItem.OreDictItem(1067, "moscovium_trichloride", RecipeUtils.average(4, GAMaterials.Moscovium.getMaterialRGB(), Materials.Chlorine.getMaterialRGB() * 3), MaterialIconSet.SAND, OrePrefix.dust, "McCl3");
    public static final GTFOOredictItem.OreDictItem MoscoviumHafniumOctadecachloride = new GTFOOredictItem.OreDictItem(1068, "moscovium_hafnium_octadecachloride", RecipeUtils.average(2, GAMaterials.Moscovium.getMaterialRGB(), GAMaterials.Hafnium.getMaterialRGB()), MaterialIconSet.SAND, OrePrefix.dust, "Mc10Hf3Cl18");
    public static final GTFOOredictItem.OreDictItem TitaniumCarbide = new GTFOOredictItem.OreDictItem(1069, "titanium_carbide", Materials.Titanium.getMaterialRGB() - 50, MaterialIconSet.SAND, OrePrefix.dust, "TiC");
    public static final GTFOOredictItem.OreDictItem SaltyTitaniumDichlorideMixture = new GTFOOredictItem.OreDictItem(1070, "salty_titanium_dichloride_mixture", RecipeUtils.average(3, Materials.Salt.getMaterialRGB() * 2, Materials.Titanium.getMaterialRGB()), MaterialIconSet.SAND, OrePrefix.dust, "Na2Ti3Cl8");
    public static final GTFOOredictItem.OreDictItem DecayedMoscovium = new GTFOOredictItem.OreDictItem(1071, "decayed_moscovium", RecipeUtils.average(4, GAMaterials.Moscovium.getMaterialRGB() * 3, GAMaterials.Nihonium.getMaterialRGB()), MaterialIconSet.SAND, OrePrefix.dust, "Mc3?");
    public static final GTFOOredictItem.OreDictItem HighlyIrradiatedHafniumCarbide = new GTFOOredictItem.OreDictItem(1072, "highly_irradiated_hafnium_carbide", RecipeUtils.average(2, GAMaterials.Hafnium.getMaterialRGB(), Materials.Carbon.getMaterialRGB()), MaterialIconSet.SAND, OrePrefix.dust, "(HfC)?2");
    public static final GTFOOredictItem.OreDictItem TitaniumIiiOxide = new GTFOOredictItem.OreDictItem(1073, "titanium(III)_oxide", RecipeUtils.average(5, Materials.Titanium.getMaterialRGB() * 2, Materials.Oxygen.getMaterialRGB() * 3), MaterialIconSet.SAND, OrePrefix.dust, "Ti2O3");
    public static final GTFOOredictItem.OreDictItem TitaniumIiOxide = new GTFOOredictItem.OreDictItem(1074, "titanium(II)_oxide", RecipeUtils.average(3, Materials.Titanium.getMaterialRGB(), Materials.Oxygen.getMaterialRGB() * 2), MaterialIconSet.SAND, OrePrefix.dust, "TiO");
    public static final GTFOOredictItem.OreDictItem MoscoviumTrifluoride = new GTFOOredictItem.OreDictItem(1075, "moscovium_trifluoride", RecipeUtils.average(4, GAMaterials.Moscovium.getMaterialRGB(), Materials.Fluorine.getMaterialRGB() * 3), MaterialIconSet.SAND, OrePrefix.dust, "McF3");
    public static final GTFOOredictItem.OreDictItem WellMixedMoscoviumPentahalides = new GTFOOredictItem.OreDictItem(1076, "well_mixed_moscovium_pentahalides", RecipeUtils.average(6, MoscoviumPentaastatide.getMaterialRGB(), MoscoviumPentabromide.getMaterialRGB(), MoscoviumPentachloride.getMaterialRGB(), MoscoviumPentafluoride.getMaterialRGB(), MoscoviumPentaiodide.getMaterialRGB(), MoscoviumPentatennesside.getMaterialRGB()), MaterialIconSet.SAND, OrePrefix.dust, "(McF5)(McCl5)(McBr5)(McI5)(McAt5)(McTs5)");
    public static final GTFOOredictItem.OreDictItem OligoPolymerizedMoscoviumPentahalides = new GTFOOredictItem.OreDictItem(1077, "oligopolymerized_moscovium_pentahalides", 0x793ac7, MaterialIconSet.SAND, OrePrefix.dust, "(McF5)(McCl5)(McBr5)(McI5)(McAt5)(McTs5)");
    public static final GTFOOredictItem.OreDictItem PolymoscoviumPentahalide = new GTFOOredictItem.OreDictItem(1078, "polymoscovium_pentahalide", 0x7300ff, MaterialIconSet.SHINY, OrePrefix.dust, "(McF5)(McCl5)(McBr5)(McI5)(McAt5)(McTs5)");
    public static final GTFOOredictItem.OreDictItem PolymoscoviumPentahalideSheet = new GTFOOredictItem.OreDictItem(1079, "polymoscovium_pentahalide", PolymoscoviumPentahalide.getMaterialRGB(), MaterialIconSet.SHINY, OrePrefix.plate, "(McF5)(McCl5)(McBr5)(McI5)(McAt5)(McTs5)");
    public static final GTFOOredictItem.OreDictItem NonToxicPolymoscoviumPentahalideSheet = new GTFOOredictItem.OreDictItem(1080, "nontoxic_polymoscovium_pentahalide", RecipeUtils.average(2,Materials.Polytetrafluoroethylene.getMaterialRGB() + 10, PolymoscoviumPentahalide.getMaterialRGB()), MaterialIconSet.SHINY, OrePrefix.plate);
    public static final GTFOOredictItem.OreDictItem NonToxicPolymoscoviumPentahalideCurvedSheet = new GTFOOredictItem.OreDictItem(1081, "nontoxic_polymoscovium_pentahalide", NonToxicPolymoscoviumPentahalideSheet.getMaterialRGB(), MaterialIconSet.SHINY, GAEnums.GAOrePrefix.plateCurved);
    public static final GTFOOredictItem.OreDictItem TitaniumDichloride = new GTFOOredictItem.OreDictItem(1082, "titanium_dichloride", RecipeUtils.average(3, Materials.Chlorine.getMaterialRGB() * 2, Materials.Titanium.getMaterialRGB()), MaterialIconSet.SAND, OrePrefix.dust, "Na2Ti3Cl8");
    public static final GTFOOredictItem.OreDictItem LithiumBromide = new GTFOOredictItem.OreDictItem(1083, "lithium_bromide", RecipeUtils.average(2, Materials.Lithium.getMaterialRGB(), GAMaterials.Bromine.getMaterialRGB()), MaterialIconSet.SAND, OrePrefix.dust, "Na2Ti3Cl8");

        public static final GTFOFluidMaterial PopcornFlavoringMixture = new GTFOFluidMaterial("popcorn_flavoring_mixture", 0xffcb21, 350);
    public static final GTFOFluidMaterial Diacetyl = new GTFOFluidMaterial("diacetyl", 0xf5fcae, "C4H6O2");
    public static final GTFOFluidMaterial IiAcetylpyridine = new GTFOFluidMaterial("2-acetylpyridine", 0xe0d5c3, "C7H7NO");
    public static final GTFOFluidMaterial Acetoin = new GTFOFluidMaterial("acetoin", 0xfcffdb, "C4H8O2");
    public static final GTFOFluidMaterial AcetylChloride = new GTFOFluidMaterial("acetyl_chloride", 0xe0e0da, "CH3COCl");
    public static final GTFOFluidMaterial IiBromopyridine = new GTFOFluidMaterial("2-bromopyridine", 0xc0c0ca, "C5H4NBr");
    public static final GTFOFluidMaterial IiIiiButanediol = new GTFOFluidMaterial("2,3-butanediol", 0xedf5e1, "C4H8O");
    public static final GTFOFluidMaterial IIvButanediol = new GTFOFluidMaterial("1,4-butanediol", 0xedf5e1, "C4H10O2");
    public static final GTFOFluidMaterial IIvButynediol = new GTFOFluidMaterial("1,4-butynediol", 0xedf5e1, "C4H6O2");
    public static final GTFOFluidMaterial FungalGrowthMedium = new GTFOFluidMaterial("fungal_growth_medium", 0xd9cfad);
    public static final GTFOFluidMaterial PeptoneMixture = new GTFOFluidMaterial("peptone_mixture", 0xd1ad30);


    public static final GTFOOredictItem.OreDictItem HotAppleHardCandy = new GTFOOredictItem.OreDictItem(1084, "hot_apple_hard_candy", 0x78e32b, MaterialIconSet.DULL, OrePrefix.plate);
    public static final GTFOOredictItem.OreDictItem BacillusSubtilis = new GTFOOredictItem.OreDictItem(1085, "bacillus_subtilis", 0xe0386b, MaterialIconSet.ROUGH, OrePrefix.dust, "Bacteria");
    public static final GTFOOredictItem.OreDictItem LactobacillusPentosis = new GTFOOredictItem.OreDictItem(1086, "lactobacillus_pentosis", 0x87316f, MaterialIconSet.ROUGH, OrePrefix.dust, "Bacteria");
    public static final GTFOOredictItem.OreDictItem FructoseConversionPlate = new GTFOOredictItem.OreDictItem(1087, "fructose_conversion_plate", 0xe0e0dc, MaterialIconSet.SHINY, OrePrefix.plate);
    public static final GTFOOredictItem.OreDictItem XyloseIsomerase = new GTFOOredictItem.OreDictItem(1088, "xylose_isomerase", 0x9718ea, MaterialIconSet.SAND, OrePrefix.dust);
    public static final GTFOOredictItem.OreDictItem AlphaAmylase = new GTFOOredictItem.OreDictItem(1089, "alpha_amylase", 0x69D992, MaterialIconSet.SAND, OrePrefix.dust);
    public static final GTFOOredictItem.OreDictItem GammaAmylase = new GTFOOredictItem.OreDictItem(1090, "gamma_amylase", 0x4FCE67, MaterialIconSet.SAND, OrePrefix.dust);
    public static final GTFOOredictItem.OreDictItem CornStarch = new GTFOOredictItem.OreDictItem(1091, "corn_starch", 0xfff5f5, MaterialIconSet.ROUGH, OrePrefix.dust);
    public static final GTFOOredictItem.OreDictItem CrushedHardCandy = new GTFOOredictItem.OreDictItem(1093, "crushed_hard_candy", 0x52a302, MaterialIconSet.DULL, OrePrefix.crushed);
    public static final GTFOOredictItem.OreDictItem Promethazine = new GTFOOredictItem.OreDictItem(1094, "promethazine", 0xf8fade, MaterialIconSet.SAND, OrePrefix.dust, "C17H20N2S");
    public static final GTFOOredictItem.OreDictItem Codeine = new GTFOOredictItem.OreDictItem(1095, "codeine", 0xfadef2, MaterialIconSet.SAND, OrePrefix.dust, "C18H21NO3");
    public static final GTFOOredictItem.OreDictItem Phenothiazine = new GTFOOredictItem.OreDictItem(1096, "phenothiazine", 0x67735c, MaterialIconSet.SAND, OrePrefix.dust, "C12H9NS");
    public static final GTFOOredictItem.OreDictItem Diphenylamine = new GTFOOredictItem.OreDictItem(1097, "diphenylamine", 0xe3932b, MaterialIconSet.SAND, OrePrefix.dust, "C12H11N");
    public static final GTFOOredictItem.OreDictItem CrushedPoppy = new GTFOOredictItem.OreDictItem(1098, "crushed_poppy", 0x940801, MaterialIconSet.ROUGH, OrePrefix.dust, "You monster.");
    public static final GTFOOredictItem.OreDictItem HardCandyPlate = new GTFOOredictItem.OreDictItem(1099, "hard_candy", 0x78e32b, MaterialIconSet.ROUGH, OrePrefix.plate);
    public static final GTFOOredictItem.OreDictItem HardCandyResin = new GTFOOredictItem.OreDictItem(1100, "hard_candy", 0x78e32b, MaterialIconSet.ROUGH, OrePrefix.plateDense);

*/
    public static void onMaterialsInit() {

    }
}
