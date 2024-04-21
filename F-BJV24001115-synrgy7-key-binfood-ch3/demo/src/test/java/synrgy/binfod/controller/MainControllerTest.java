package synrgy.binfod.controller;

import org.junit.jupiter.api.Test;
import synrgy.binfod.controller.MainController;
import synrgy.binfod.model.Menu;
import synrgy.binfod.model.Order;
import synrgy.binfod.view.MainView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainControllerTest {
    @Test
    public void testChooseMenu_AddMoreOrders() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Persiapan
        Menu menu = new Menu();
        MainView view = new MainView();
        MainController mainController = new MainController(menu, view);
        List<Order> orders = new ArrayList<>();

        Method chooseMenuMethod = MainController.class.getDeclaredMethod("chooseMenu", List.class);
        chooseMenuMethod.setAccessible(true); // Mengatur aksesibilitas metode ke private

        // Aksi
        chooseMenuMethod.invoke(mainController, orders);

        // Pengecekan
        assertEquals(0, orders.size()); // Memeriksa apakah metode dengan benar memperbarui daftar pesanan
    }
}
