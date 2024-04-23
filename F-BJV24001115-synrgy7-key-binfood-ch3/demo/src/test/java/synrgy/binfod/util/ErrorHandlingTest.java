package synrgy.binfod.util;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ErrorHandlingTest {
    @Test
    void testHandleException() {

        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outputStreamCaptor));

        try {
            throw new IllegalArgumentException("Test exception");
        } catch (IllegalArgumentException e) {
            ErrorHandling.handleException(e);
        }

        String consoleOutput = outputStreamCaptor.toString();
        assertTrue(consoleOutput.contains("Test exception"));
    }
}
