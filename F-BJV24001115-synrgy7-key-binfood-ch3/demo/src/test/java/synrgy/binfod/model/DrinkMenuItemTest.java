package synrgy.binfod.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DrinkMenuItemTest {
    @Test
    void testGetName() {
        DrinkMenuItem drink = new DrinkMenuItem("Es Teh", 5000);
        assertEquals("Es Teh", drink.getName());
    }

    @Test
    void testGetPrice() {
        DrinkMenuItem drink = new DrinkMenuItem("Es Teh", 5000);
        assertEquals(5000, drink.getPrice());
    }
}
