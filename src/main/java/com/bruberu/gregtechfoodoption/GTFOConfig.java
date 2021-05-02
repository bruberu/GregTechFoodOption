package com.bruberu.gregtechfoodoption;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Config(modid = GregTechFoodOption.MODID)

public class GTFOConfig {

    @Config.Comment("Show tooltips on shift?")
    public static boolean showTooltipsOnShift = true;

    @Config.Comment("Show tooltips always, regardless of what keys are held?")
    public static boolean showTooltipsAlways = false;

    @Config.Comment("NuclearCraft compatibility")
    public static GTFONCConfig gtfoncConfig = new GTFONCConfig();

    public static class GTFONCConfig {
        @Config.Comment("Add NuclearCraft S'more recipes?")
        public static boolean smoreChain = true;
        @Config.Comment("Add NuclearCraft S'more extensions?")
        public static boolean addSmogus = true;
    }

    @Config.Comment("ActuallyAdditions compatibility")
    public static GTFOAAConfig gtfoaaConfig = new GTFOAAConfig();

    public static class GTFOAAConfig {
        @Config.Comment("Disable AA Coffee Maker's recipe?")
        public static boolean disableCoffeeMaker = true;
        @Config.Comment("Add AA Coffee Chain?")
        public static boolean coffeeChain = true;
    }

    @Config.Comment("Add Popcorn Chain?")
    public static boolean popcornChain = true;

    @Config.Comment("Add Hunger and Saturation values for GTFO foods")
    public static GTFOFoodConfig gtfoFoodConfig = new GTFOFoodConfig();
    public static class GTFOFoodConfig {
        @Config.Comment("Popcorn hunger")
        public static int popcornHunger = 5;
        @Config.Comment("Popcorn saturation")
        public static float popcornSaturaion = 2;
        @Config.Comment("Return bag after eating popcorn?")
        public static boolean popcornReturnsContainer = true;
    }
}
