package synrgy.binfod.util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ErrorHandling {

    private ErrorHandling() {

    }

    private static final Logger logger = Logger.getLogger(ErrorHandling.class.getName());

    public static void handleException(Exception e) {
        logger.log(Level.SEVERE, "Terjadi kesalahan: " + e.getMessage(), e);
        System.err.println("Terjadi kesalahan: " + e.getMessage());
    }

    // Method untuk menangani IOException
    public static void handleException(IOException e) {
        logger.log(Level.SEVERE, "Terjadi kesalahan IO: " + e.getMessage(), e);
        System.err.println("Terjadi kesalahan IO: " + e.getMessage());
    }
}
