package utilities.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
    //Initialize Log4j instance
    public static Logger Log = LoggerFactory.getLogger(Log.class);

    //Info Level Logs
    public static void info (String message) {
        Log.info(message);
    }

    //Warn Level Logs
    public static void warn (String message) {
        Log.warn(message);
    }

    //Error Level Logs
    public static void error (String message) {
        Log.error(message);
    }

    //Fatal Level Logs
    public static void fatal (String message) {
        Log.error(message);
    }

    //Debug Level Logs
    public static void debug (String message) {
        Log.debug(message);
    }
}
