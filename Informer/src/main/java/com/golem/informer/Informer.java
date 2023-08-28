package com.golem.informer;

import org.apache.logging.log4j.LogManager;

public class Informer {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Informer.class);

    public static void log (Level level, String str) {
        switch (level) {
            case ERROR -> logger.error(str);
            case INFO -> logger.info(str);
        }
    }
    public static void log (Level level, Throwable throwable) {
        switch (level) {
            case ERROR -> logger.error("", throwable);
            case INFO -> logger.info("", throwable);
        }
    }
}
