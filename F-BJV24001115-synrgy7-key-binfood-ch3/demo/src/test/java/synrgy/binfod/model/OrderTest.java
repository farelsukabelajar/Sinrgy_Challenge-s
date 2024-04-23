package synrgy.binfod.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void testFoodOrder() {
        MenuItem menuItem = new FoodMenuItem("Nasi Goreng", 15000);
        Order order = new FoodOrder(menuItem, 2);

        assertTrue(order instanceof FoodOrder);
        assertEquals("Nasi Goreng", order.getItem().getName());
        assertEquals(2, order.getQuantity());
        assertEquals(30000, order.getTotalPrice());
    }

    @Test
    void testDrinkOrder() {
        MenuItem menuItem = new DrinkMenuItem("Es Teh", 5000);
        Order order = new DrinkOrder(menuItem, 3);

        assertTrue(order instanceof DrinkOrder);
        assertEquals("Es Teh", order.getItem().getName());
        assertEquals(3, order.getQuantity());
        assertEquals(15000, order.getTotalPrice());
    }
}
