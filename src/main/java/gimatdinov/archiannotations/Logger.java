package gimatdinov.archiannotations;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class Logger {
    public static boolean enable = true;

    private Logger() {
    }

    public static boolean isEnable() {
        return enable;
    }

    public static void setEnable(boolean enable) {
        Logger.enable = enable;
    }

    public static void log(int severity, String message, Throwable throwable) {
        if (enable) {
            ArchiAnnotationsPlugin.getDefault().getLog()
                    .log(new Status(severity, ArchiAnnotationsPlugin.PLUGIN_ID, IStatus.OK, message, throwable));
        }
    }

    public static void info(String message) {
        log(IStatus.INFO, message, null);
    }

    public static void logWarning(String message) {
        log(IStatus.WARNING, message, null);
    }

    public static void warning(String message, Throwable throwable) {
        log(IStatus.WARNING, message, throwable);
    }

    public static void eror(String message) {
        log(IStatus.ERROR, message, null);
    }

    public static void error(String message, Throwable throwable) {
        log(IStatus.ERROR, message, throwable);
    }

}
