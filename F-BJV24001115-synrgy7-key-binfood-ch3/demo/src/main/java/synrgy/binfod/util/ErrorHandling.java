package synrgy.binfod.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ErrorHandling {
    private static final Logger logger = Logger.getLogger(ErrorHandling.class.getName());

    public static void handleException(Exception e) {
        logger.log(Level.SEVERE, "Terjadi kesalahan: " + e.getMessage(), e);
        System.err.println("Terjadi kesalahan: " + e.getMessage());
    }
}
