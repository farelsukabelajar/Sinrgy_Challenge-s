package synrgy.binfod.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {
    private Menu menu;

    @BeforeEach
    public void setUp() {
        menu = new Menu();
    }

    @Test
    public void testGetMenuItems() {
        List<MenuItem> menuItems = menu.getMenuItems();
        assertNotNull(menuItems);
        assertEquals(5, menuItems.size());
    }

    @Test
    public void testGetMenuItems_Contents() {
        List<MenuItem> menuItems = menu.getMenuItems();
        assertTrue(menuItems.get(0) instanceof FoodMenuItem);
        assertTrue(menuItems.get(1) instanceof FoodMenuItem);
        assertTrue(menuItems.get(2) instanceof FoodMenuItem);
        assertTrue(menuItems.get(3) instanceof DrinkMenuItem);
        assertTrue(menuItems.get(4) instanceof DrinkMenuItem);
    }
}
