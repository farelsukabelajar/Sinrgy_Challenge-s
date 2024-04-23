package synrgy.binfod.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MenuItemTest {
    @Test
    void testGetName() {
        MenuItem food = new FoodMenuItem("Nasi Goreng", 15000);
        assertEquals("Nasi Goreng", food.getName());
    }

    @Test
    void testGetPrice() {
        MenuItem food = new FoodMenuItem("Nasi Goreng", 15000);
        assertEquals(15000, food.getPrice());
    }
}
