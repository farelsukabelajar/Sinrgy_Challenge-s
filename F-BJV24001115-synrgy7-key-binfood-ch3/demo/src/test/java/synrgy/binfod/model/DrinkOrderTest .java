package synrgy.binfod.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DrinkOrderTest {
    @Test
    void testGetTotalPrice() {
        DrinkMenuItem drink = new DrinkMenuItem("Es Teh", 5000);
        DrinkOrder order = new DrinkOrder(drink, 3);
        assertEquals(15000, order.getTotalPrice());
    }
}
