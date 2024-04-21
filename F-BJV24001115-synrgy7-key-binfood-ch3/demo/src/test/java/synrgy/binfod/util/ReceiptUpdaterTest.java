package synrgy.binfod.util;

import org.junit.jupiter.api.Test;
import synrgy.binfod.model.DrinkMenuItem;
import synrgy.binfod.model.DrinkOrder;
import synrgy.binfod.model.FoodMenuItem;
import synrgy.binfod.model.FoodOrder;
import synrgy.binfod.model.MenuItem;
import synrgy.binfod.model.Order;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ReceiptUpdaterTest {

    @Test
    public void testGenerateReceipt() {
        // Create a list of orders
        List<Order> orders = new ArrayList<>();
        orders.add(new FoodOrder(new FoodMenuItem("Nasi Goreng", 15000), 2));
        orders.add(new DrinkOrder(new DrinkMenuItem("Es Teh Manis", 3000), 3));

        // Generate the receipt
        String receipt = ReceiptUpdater.generateReceipt(orders);

        // Verify the receipt content
        assertTrue(receipt.contains("Nasi Goreng"));
        assertTrue(receipt.contains("Es Teh Manis"));
        assertTrue(receipt.contains("Total"));
        assertTrue(receipt.contains("pembayaran     : BinarCash"));
    }

    @Test
    public void testGenerateReceipt_EmptyOrders() {
        // Create an empty list of orders
        List<Order> orders = new ArrayList<>();

        // Generate the receipt
        String receipt = ReceiptUpdater.generateReceipt(orders);

        // Verify that the receipt is empty
        assertEquals("", receipt.trim());
    }
}
