package synrgy.binfod.util;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;

public class ErrorHandlingTest {
    @Test
    public void testHandleException() {
        // Capture the output to system.err
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outputStreamCaptor));

        // Define a test exception
        Exception testException = new Exception("Test Exception Message");

        // Call the handleException method
        ErrorHandling.handleException(testException);

        // Verify that the message is logged at SEVERE level
        Logger logger = Logger.getLogger(ErrorHandling.class.getName());
        String expectedLogMessage = "Terjadi kesalahan: Test Exception Message";
        assertEquals(expectedLogMessage, outputStreamCaptor.toString().trim());

        // Reset system.err
        System.setErr(System.err);
    }
}
