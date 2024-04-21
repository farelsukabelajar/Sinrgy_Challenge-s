package synrgy.binfod;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import synrgy.binfod.model.Menu;
import synrgy.binfod.view.MainView;
import synrgy.binfod.controller.MainController;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    private Menu menu;
    private MainView view;
    private MainController controller;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final ByteArrayInputStream inputStream = new ByteArrayInputStream("0".getBytes());

    @BeforeEach
     public void setUp() {
        menu = new Menu();
        view = new MainView();
        controller = new MainController(menu, view);
        System.setOut(new PrintStream(outputStream));
        System.setIn(inputStream);
    }

    @Test
    public void testMain() {
        // Prepare input (simulate user input)
        String input = "0";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Run the main method
        Main.main(new String[]{});

        // Verify the output
        String expectedOutput = "=========================================\n" +
                "                  Menu                  \n" +
                "=========================================\n" +
                "\n" +
                "1. Pilih Menu\n" +
                "2. Lihat Struk\n" +
                "0. Keluar\n" +
                "\n" +
                "Terima kasih telah menggunakan Binar Food.\n\n";
        assertEquals(expectedOutput, outputStream.toString());
    }
}
