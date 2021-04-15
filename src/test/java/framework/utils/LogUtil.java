package framework.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LogUtil {

    private final static Logger LOGGER = LogManager.getLogger(LogUtil.class.getName());

    private LogUtil() {
    }

    public static void info(String info) {
        LOGGER.info(info);
    }

    public static void error(String error) {
        LOGGER.error(error);
    }

    public static void step(int stepNumber, String message) {
        LOGGER.info(String.format("%s. %s", stepNumber, message));
    }
}
