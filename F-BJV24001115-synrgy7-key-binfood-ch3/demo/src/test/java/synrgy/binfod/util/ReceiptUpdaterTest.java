package synrgy.binfod.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import synrgy.binfod.model.*;

class ReceiptUpdaterTest {
    @Test
    void testGenerateReceipt() {
        List<Order> orders = new ArrayList<>();
        orders.add(new FoodOrder(new FoodMenuItem("Nasi Goreng", 15000), 2));
        orders.add(new DrinkOrder(new DrinkMenuItem("Es Teh", 5000), 3));

        String receipt = ReceiptUpdater.generateReceipt(orders);

        String expectedReceipt = "\n===========================================\n" +
                "             Struk Pembayaran             \n" +
                "===========================================\n\n" +
                String.format("%-20s %-10s %-10s%n", "Menu", "Qty", "Harga") +
                "\n" +
                String.format("%-20s %-10d %-10s%n", "Nasi Goreng", 2, "Rp. 30000") +
                String.format("%-20s %-10d %-10s%n", "Es Teh", 3, "Rp. 15000") +
                "------------------------------------------- +\n" +
                String.format("%n%-20s %-10d %-10s%n", "Total", 5, "Rp. 45000") +
                "\n\npembayaran     : BinarCash\n" +
                "\nDipesan pada   : \\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}\n" +
                "\n\n===========================================\n" +
                "            Struk Telah Diperbarui        \n" +
                "===========================================\n";

        // Membandingkan string yang dihasilkan dengan string yang diharapkan
        assertEquals(expectedReceipt, receipt);
    }
}
