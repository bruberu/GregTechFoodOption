package com.bruberu.gregtechfoodoption.utils;

import org.apache.logging.log4j.Logger;

public class GTFOLog {
    public static Logger logger;
    public static void init(Logger modLogger) {
        logger = modLogger;
    }

}
