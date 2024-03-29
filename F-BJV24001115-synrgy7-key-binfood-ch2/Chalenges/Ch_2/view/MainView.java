package Chalenges.Ch_2.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Chalenges.Ch_2.model.MenuItem;
import Chalenges.Ch_2.model.Order;

public class MainView {
    public void displayMenu() {
        System.out.println("\n=========================================");
        System.out.println("                  Menu                  ");
        System.out.println("=========================================\n");
        System.out.println("1. Pilih Menu");
        System.out.println("2. Lihat Struk");
        System.out.println("0. Keluar");
    }

    public void displayItems(List<? extends MenuItem> items) {
        System.out.println("\n=========================================");
        System.out.println("       Selamat datang di BinarFud       ");
        System.out.println("=========================================\n");
        System.out.println("Menu\t\t\tHarga");
        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            System.out.println((i + 1) + ". " + item.getName() + "\t\tRp. " + item.getPrice());
        }
    }

    public void displayReceipt(List<Order> orders) {
        int totalItems = 0;
        int totalPrice = 0;

        System.out.println("\n===========================================");
        System.out.println("             Struk Pembayaran              ");
        System.out.println("===========================================");
        System.out.printf("\n%-20s %-10s %-10s%n", "Menu", "Qty", "Harga\n\n");

        for (Order order : orders) {
            MenuItem item = order.getItem();
            System.out.printf("%-20s %-10d %-10s%n", item.getName(), order.getQuantity(),
                    "Rp. " + order.getTotalPrice());

            totalItems += order.getQuantity();
            totalPrice += order.getTotalPrice();
        }

        System.out.println("------------------------------------------- +");
        System.out.printf("%n%-20s %-10d %-10s%n", "Total", totalItems, "Rp. " + totalPrice);
        System.out.println("\n\npembayaran     : BinarCash");
        System.out.println("\nDipesan pada     : " + getCurrentDateTime());
        System.out.println("\n\n===========================================");
        System.out.println("            Simpan Struk Sebagai           ");
        System.out.println("              Bukti Pembayaran             ");
        System.out.println("===========================================");
    }

    public void displayConfirm() {
        System.out.println("\n=========================================");
        System.out.println("            Konfirmasi Pemesanan            ");
        System.out.println("=========================================");
    }

    public void displayAddMenu() {
        System.out.println("\n=========================================");
        System.out.println("               Tambah Pesanan              ");
        System.out.println("=========================================");
    }

    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(new Date());
    }
}
