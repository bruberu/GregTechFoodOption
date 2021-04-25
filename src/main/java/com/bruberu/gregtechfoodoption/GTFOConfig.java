package com.bruberu.gregtechfoodoption;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Config(modid = GregTechFoodOption.MODID)

public class GTFOConfig {
    @Config.Comment("Add NuclearCraft S'more recipes?")
    public static boolean addSmore = true;
    @Config.Comment("Add NuclearCraft S'more extensions?")
    public static boolean addSmogus = true;

    @Config.Comment("Config options of miscellaneous features")
    public static Misc Misc = new Misc();
    public static class Misc {

    }
}
