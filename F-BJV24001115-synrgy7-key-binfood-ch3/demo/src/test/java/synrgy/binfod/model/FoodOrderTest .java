package synrgy.binfod.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodOrderTest {
    @Test
    void testGetTotalPrice() {
        FoodMenuItem food = new FoodMenuItem("Nasi Goreng", 15000);
        FoodOrder order = new FoodOrder(food, 2);
        assertEquals(30000, order.getTotalPrice());
    }
}
