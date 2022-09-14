package gregtechfoodoption.utils;


import de.ellpeck.actuallyadditions.mod.config.values.ConfigBoolValues;
import gregtechfoodoption.GTFOConfig;
import gregtechfoodoption.GTFOValues;
import nc.Global;
import net.minecraftforge.fml.common.Loader;

//This particular class overrides particular config values depending on the environment the mod is played in.
public class GTFOConfigOverrider {
    public static void init() {
        if (GTFOConfig.gtfoncConfig.nuclearCompat) {
            if (!Loader.isModLoaded(GTFOValues.MODID_NC)) {
                GTFOLog.logger.warn("It appears you have NuclearCraft installed rather than NuclearCraft:Overhauled. This mod does not have compatibility with the first, so consider switching to the other if you want that. Otherwise, turn off the NC Compat config option.");
                GTFOConfig.gtfoncConfig.setAllToFalse();
            } else if (Global.VERSION.charAt(1) != 'o') {
                GTFOLog.logger.warn("It appears you don't have NuclearCraft:Overhauled installed, but you still have the config option for compatibility with it on. Consider turning it off, or installing NuclearCraft:Overhauled.");
                GTFOConfig.gtfoncConfig.setAllToFalse();
            }
        }
        if (GTFOConfig.gtfoaaConfig.actuallyCompat) {
            if (!Loader.isModLoaded(GTFOValues.MODID_AA)) {
                GTFOLog.logger.warn("It appears you don't have ActuallyAdditions installed, but you still have the config option for compatibility with it on. Consider turning it off, or installing ActuallyAdditions.");
                GTFOConfig.gtfoaaConfig.setAllToFalse();
            }
        }
        if (Loader.isModLoaded(GTFOValues.MODID_AA)) {
            ConfigBoolValues.DO_COFFEE_GEN.currentValue = false;
        }
        if (GTFOConfig.gtfoAppleCoreConfig.appleCoreCompat) {
            if (!Loader.isModLoaded("applecore")) {
                GTFOLog.logger.warn("It appears you don't have AppleCore installed, but you still have the config option for compatibility with it on. Consider turning it off, or installing AppleCore.");
                GTFOConfig.gtfoAppleCoreConfig.setAllToFalse();
            }
        }
    }
}
