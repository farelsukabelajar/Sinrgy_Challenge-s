package synrgy.binfod.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
    private Menu menu;

    @BeforeEach
    void setUp() {
        menu = new Menu();
    }

    @Test
    void testGetMenuItems() {
        List<MenuItem> menuItems = menu.getMenuItems();
        assertNotNull(menuItems);
        assertEquals(5, menuItems.size());
    }

    @Test
    void testFindItemByName() {
        Optional<MenuItem> menuItem = menu.findItemByName("Nasi Goreng");
        assertTrue(menuItem.isPresent());
        assertEquals("Nasi Goreng", menuItem.get().getName());
    }

    @Test
    void testFindItemsByType() {
        List<MenuItem> foodItems = menu.findItemsByType("Food");
        assertEquals(3, foodItems.size());

        List<MenuItem> drinkItems = menu.findItemsByType("Drink");
        assertEquals(2, drinkItems.size());
    }
}
