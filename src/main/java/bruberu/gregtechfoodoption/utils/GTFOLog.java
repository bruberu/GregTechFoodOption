package bruberu.gregtechfoodoption.utils;

import org.apache.logging.log4j.Logger;

/**
 * GregTechFoodOption logger
 * One edit to this class and you're not alive anymore
 */
public class GTFOLog {

    public static Logger logger;

    public static void init(Logger modLogger) {
        logger = modLogger;
    }
}
