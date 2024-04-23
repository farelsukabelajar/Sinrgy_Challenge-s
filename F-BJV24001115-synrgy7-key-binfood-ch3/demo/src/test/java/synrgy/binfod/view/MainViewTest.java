package synrgy.binfod.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MainViewTest {
    private MainView mainView;

    @BeforeEach
    void setUp() {
        mainView = new MainView();
    }

    @Test
    void testDisplayMessage() {
        mainView.displayMessage("Ini adalah pesan.");
        mainView.displayMessage("Ini pesan lain.");

        String expectedOutput = "Ini adalah pesan.\nIni pesan lain.\n";
        assertEquals(expectedOutput, mainView.getOutput());
    }
}

