package synrgy.binfod.controller;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import synrgy.binfod.model.*;
import synrgy.binfod.view.MainView;

class MainControllerTest {
    private MainController mainController;
    private Menu menu;
    private MainView view;

    @BeforeEach
    void setUp() {
        menu = new Menu();
        view = new MainView();
        mainController = new MainController(menu, view);
    }

    @Test
    void testChooseMenu() {
        ByteArrayInputStream in = new ByteArrayInputStream("1\n2\nn\n".getBytes());
        System.setIn(in);

        List<Order> orders = new ArrayList<>();
        mainController.chooseMenu(orders);

        assertEquals(1, orders.size());
        assertTrue(orders.get(0) instanceof FoodOrder);
        assertEquals("Nasi Goreng", orders.get(0).getItem().getName());
        assertEquals(2, orders.get(0).getQuantity());
    }

    @Test
    void testConfirmOrder() {
        List<Order> orders = List.of(
                new FoodOrder(new FoodMenuItem("Nasi Goreng", 15000), 2),
                new DrinkOrder(new DrinkMenuItem("Es Teh", 5000), 3));

        mainController.confirmOrder(orders);

        assertEquals("Pesanan telah dikonfirmasi.", view.getOutput());
    }

    @Test
    void testCreateOrder() {
        MenuItem foodMenuItem = new FoodMenuItem("Nasi Goreng", 15000);
        Order order = mainController.createOrder(foodMenuItem, 2);

        assertTrue(order instanceof FoodOrder);
        assertEquals("Nasi Goreng", order.getItem().getName());
        assertEquals(2, order.getQuantity());
    }
}
