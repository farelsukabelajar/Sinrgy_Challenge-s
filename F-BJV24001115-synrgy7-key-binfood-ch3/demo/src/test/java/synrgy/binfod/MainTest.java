package synrgy.binfod;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    private ByteArrayOutputStream outputStreamCaptor;

    @BeforeEach
    void setUp() {
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testMain() {
        // Simulasikan input yang diharapkan oleh program
        String simulatedUserInput = "2\n0\n";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        // Panggil metode main
        Main.main(new String[]{});

        // Periksa apakah output yang dihasilkan sesuai dengan yang diharapkan
        String expectedOutput = "===========================================\n" +
                                "             Struk Pembayaran             \n" +
                                "===========================================\n\n" +
                                "===========================================\n" +
                                "            Struk Telah Diperbarui        \n" +
                                "===========================================\n\n" +
                                "Terima kasih telah menggunakan Binar Food.\n\n";
        assertEquals(expectedOutput, outputStreamCaptor.toString());
    }
}
