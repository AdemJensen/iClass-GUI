package top.chorg.system;

import java.util.Formatter;

/**
 * Contains all the system utilities.
 * Fully static, no need to make instance.
 */
public class Sys {
    /**
     * Send master warning message to the console or log.
     * If under dev environment or cmd line environment, the message will be sent to console.
     * Under Normal GUI mode, this message will be transferred into log file.
     *
     * @param sender Message sender name.
     * @param msg Message content.
     */
    public static void warn(String sender, String msg) {
        String content = String.format("[ WARN ] %s: %s", sender, msg);
        if (isDevEnv()) {
            System.out.println(content);
        }
    }

    /**
     * Send master warning message with format to the console or log.
     * If under dev environment or cmd line environment, the message will be sent to console.
     * Under Normal GUI mode, this message will be transferred into log file.
     *
     * @param sender Message sender name.
     * @param format Message content format.
     * @param args Parameters to be filled into format blanks.
     */
    public static void warnF(String sender, String format, Object ... args) {
        warn(sender, new Formatter().format(format, args).toString());
    }

    /**
     * Send master error message to the console or log.
     * If under dev environment or cmd line environment, the message will be sent to console.
     * Under Normal GUI mode, this message will be transferred into log file.
     *
     * @param sender Message sender name.
     * @param msg Message content.
     */
    public static void err(String sender, String msg) {
        String content = String.format("[ ERROR ] %s: %s", sender, msg);
        if (isDevEnv()) {
            System.out.println(content);
        }
    }

    /**
     * Send master error message with format to the console or log.
     * If under dev environment or cmd line environment, the message will be sent to console.
     * Under Normal GUI mode, this message will be transferred into log file.
     *
     * @param sender Message sender name.
     * @param format Message content format.
     * @param args Parameters to be filled into format blanks.
     */
    public static void errF(String sender, String format, Object ... args) {
        err(sender, new Formatter().format(format, args).toString());
    }

    /**
     * Send information to the console or log.
     * If under dev environment or cmd line environment, the message will be sent to console.
     * Under Normal GUI mode, this message will be transferred into log file.
     *
     * @param sender Message sender name.
     * @param msg Message content.
     */
    public static void info(String sender, String msg) {
        String content = String.format("[ NOTE ] %s: %s", sender, msg);
        if (isDevEnv()) {
            System.out.println(content);
        }
    }

    /**
     * Send information with format to the console or log.
     * If under dev environment or cmd line environment, the message will be sent to console.
     * Under Normal GUI mode, this message will be transferred into log file.
     *
     * @param sender Message sender name.
     * @param format Message content format.
     * @param args Parameters to be filled into format blanks.
     */
    public static void infoF(String sender, String format, Object ... args) {
        info(sender, new Formatter().format(format, args).toString());
    }


    /**
     * Send development information output message to the console or log.
     * If under dev environment or cmd line environment, the message will be sent to console.
     * Under Normal GUI mode, this message will be transferred into log file.
     *
     * @param sender Message sender name.
     * @param msg Message content.
     */
    public static void devInfo(String sender, String msg) {
        String content = String.format("[-DEV-] %s: %s", sender, msg);
        if (isDevEnv()) {
            System.out.println(content);
        }
    }

    /**
     * Send development information output message with format to the console or log.
     * If under dev environment or cmd line environment, the message will be sent to console.
     * Under Normal GUI mode, this message will be transferred into log file.
     *
     * @param sender Message sender name.
     * @param format Message content format.
     * @param args Parameters to be filled into format blanks.
     */
    public static void devInfoF(String sender, String format, Object ... args) {
        devInfo(sender, new Formatter().format(format, args).toString());
    }

    /**
     * To judge if current environment is development env or not.
     * Default value is false.
     *
     * @return True if current environment is development env.
     */
    public static boolean isDevEnv() {
        return true;
    }

    /**
     * To judge if current environment is development env or not.
     * Default value is false.
     *
     * @return True if current environment is development env.
     */
    public static boolean isGuiDevEnv() {
        return true;
    }

    /**
     * Exit the system with a return value.
     * If in Command Line mode, this method will exit the program directly with a value.
     * If in GUI mode, a window will be popped up to indicate problems.
     *
     * @param returnValue The exit value.
     */
    public static void exit(int returnValue) {
        if (isDevEnv()) System.out.println("System is exiting with return value " + returnValue + "!");
        System.exit(returnValue);
    }

}
