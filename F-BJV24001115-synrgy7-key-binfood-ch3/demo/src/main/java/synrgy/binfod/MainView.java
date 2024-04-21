package synrgy.binfod.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import synrgy.binfod.model.MenuItem;
import synrgy.binfod.model.Order;

public class MainView {

    private void lineWithSpace() {
        System.out.println("\n=========================================");
    }

    private void lineNoSpace() {
        System.out.println("=========================================");
    }

    public void displayMenu() {
        lineWithSpace();
        System.out.println("                  Menu                  ");
        lineNoSpace();
        System.out.println("\n1. Pilih Menu");
        System.out.println("2. Lihat Struk");
        System.out.println("0. Keluar");
    }

    public void displayItems(List<? extends MenuItem> items) {
        lineWithSpace();
        System.out.println("       Selamat datang di BinarFud       ");
        lineNoSpace();
        System.out.println("\nMenu\t\t\tHarga");

        items.stream()
                .forEach(item -> System.out.println(item.getName() + "\t\tRp. " + item.getPrice()));
    }

    public void displayReceipt(List<Order> orders) {
        int totalItems = 0;
        int totalPrice = 0;

        lineWithSpace();
        System.out.println("             Struk Pembayaran              ");
        lineNoSpace();
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
        lineNoSpace();
    }

    public void displayConfirm() {
        lineWithSpace();
        System.out.println("            Konfirmasi Pemesanan            ");
        lineNoSpace();
    }

    public void displayAddMenu() {
        lineWithSpace();
        System.out.println("               Tambah Pesanan              ");
        lineNoSpace();
    }

    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(new Date());
    }
}
