package Chalenges.Ch_2.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Chalenges.Ch_2.model.MenuItem;
import Chalenges.Ch_2.model.Order;

public class ReceiptUpdater {
    public static String generateReceipt(List<Order> orders) {
        StringBuilder receipt = new StringBuilder();
        int totalItems = 0;
        int totalPrice = 0;

        receipt.append("\n===========================================\n");
        receipt.append("             Struk Pembayaran             \n");
        receipt.append("===========================================\n\n");
        receipt.append(String.format("%-20s %-10s %-10s%n", "Menu", "Qty", "Harga"));
        receipt.append("\n");

        for (Order order : orders) {
            MenuItem item = order.getItem();
            receipt.append(String.format("%-20s %-10d %-10s%n", item.getName(), order.getQuantity(),
                    "Rp. " + order.getTotalPrice()));

            totalItems += order.getQuantity();
            totalPrice += order.getTotalPrice();
        }

        receipt.append("------------------------------------------- +\n");
        receipt.append(String.format("%n%-20s %-10d %-10s%n", "Total", totalItems, "Rp. " + totalPrice));
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
