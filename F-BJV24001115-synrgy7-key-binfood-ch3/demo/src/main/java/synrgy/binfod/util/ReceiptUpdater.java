package synrgy.binfod.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import synrgy.binfod.model.MenuItem;
import synrgy.binfod.model.Order;

public class ReceiptUpdater {

    private ReceiptUpdater() {

    }

    public static String generateReceipt(List<Order> orders) {
        StringBuilder receipt = new StringBuilder();
        int[] totalItems = { 0 };
        int totalPrice = orders.stream()
                .mapToInt(Order::getTotalPrice)
                .sum();
        receipt.append("\n===========================================\n");
        receipt.append("             Struk Pembayaran             \n");
        receipt.append("===========================================\n\n");
        receipt.append(String.format("%-20s %-10s %-10s%n", "Menu", "Qty", "Harga"));
        receipt.append("\n");

        orders.forEach(order -> {
            MenuItem item = order.getItem();
            receipt.append(String.format("%-20s %-10d %-10s%n", item.getName(), order.getQuantity(),
                    "Rp. " + order.getTotalPrice()));

            totalItems[0] += order.getQuantity();
        });

        receipt.append("------------------------------------------- +\n");
        receipt.append(String.format("%n%-20s %-10d %-10s%n", "Total", totalItems[0], "Rp. " + totalPrice));
        receipt.append("\n\npembayaran     : BinarCash\n");
        receipt.append("\nDipesan pada   : " + getCurrentDateTime() + "\n");
        receipt.append("\n\n===========================================\n");
        receipt.append("            Struk Telah Diperbarui        \n");
        receipt.append("===========================================\n");

        return receipt.toString();
    }

    private static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(new Date());
    }
}
