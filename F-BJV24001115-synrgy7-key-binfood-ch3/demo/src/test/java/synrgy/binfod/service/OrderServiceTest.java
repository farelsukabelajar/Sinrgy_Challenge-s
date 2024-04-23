package synrgy.binfod.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import synrgy.binfod.model.*;
import synrgy.binfod.view.MainView;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    private OrderService orderService;
    private Menu menu;
    private MainView view;

    @BeforeEach
    void setUp() {
        menu = new Menu();
        view = new MainView();
        orderService = new OrderService();
    }

    @Test
    void testCalculateTotalPrice() {
        List<Order> orders = List.of(
                new FoodOrder(new FoodMenuItem("Nasi Goreng", 15000), 2),
                new DrinkOrder(new DrinkMenuItem("Es Teh", 5000), 3)
        );

        int totalPrice = orderService.calculateTotalPrice(orders);
        assertEquals(45000, totalPrice);
    }

    @Test
    void testCreateOrderProcess() {
        ByteArrayInputStream in = new ByteArrayInputStream("1\n2\nn\n".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        List<Order> orders = orderService.createOrderProcess(menu, view, scanner);
        assertEquals(1, orders.size());
        assertTrue(orders.get(0) instanceof FoodOrder);
        assertEquals("Nasi Goreng", orders.get(0).getItem().getName());
        assertEquals(2, orders.get(0).getQuantity());
    }

    @Test
    void testSaveReceiptToFile() {
        List<Order> orders = List.of(
                new FoodOrder(new FoodMenuItem("Nasi Goreng", 15000), 2),
                new DrinkOrder(new DrinkMenuItem("Es Teh", 5000), 3)
        );

        orderService.saveReceiptToFile(orders);
    }
}
