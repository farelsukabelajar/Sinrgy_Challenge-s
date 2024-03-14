import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BinarFoodCh1 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=========================================");
            System.out.println("                  Menu                  ");
            System.out.println("=========================================\n");
            System.out.println("1. Pilih makanan");
            System.out.println("2. Lihat Struk");
            System.out.println("0. Keluar");

            System.out.print("Pilihan Anda: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    pesanMakanan();
                    break;
                case 2:
                    lihatStruk();
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan layanan kami.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }

        } while (choice != 0);
    }

    public static void pesanMakanan() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        int qty;
        boolean pesanLagi = true;
        int totalHarga = 0;
        int totalItems = 0;
        StringBuilder pesanan = new StringBuilder();

        try {
            FileWriter fileWriter = new FileWriter("struk_pembayaran.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.printf("%-20s %-10s %-10s%n", "Menu", "Qty", "Harga\n");

            while (pesanLagi) {
                System.out.println("\n=========================================");
                System.out.println("       Selamat datang di BinarFud       ");
                System.out.println("=========================================\n");
                System.out.println("Menu\t\t\tHarga");
                System.out.println("1. Nasi Goreng\t\tRp. 15,000");
                System.out.println("2. Mie Goreng\t\tRp. 13,000");
                System.out.println("3. Nasi + Ayam\t\tRp. 18,000");
                System.out.println("4. Es Teh Manis\t\tRp. 3,000");
                System.out.println("5. Es Jeruk\t\tRp. 5,000");
                System.out.println("99. Pesan dan Bayar");

                System.out.print("\nMasukan pilihan: ");
                choice = scanner.nextInt();

                if (choice >= 1 && choice <= 5) {
                    System.out.print("Jumlah: ");
                    qty = scanner.nextInt();
                    String menu = getMenu(choice);
                    int harga = getHarga(choice);
                    totalHarga += (harga * qty);
                    totalItems += qty;
                    pesanan.append(String.format("%-20s %-10d %-10s%n", menu, qty, "Rp. " + (harga * qty)));
                    System.out.println("Pesanan berhasil ditambahkan.");
                   
                    printWriter.printf("%-20s %-10d %-10s%n", menu, qty, "Rp. " + (harga * qty));
                } else if (choice == 99) {
                    pesanLagi = false;
                    System.out.println("\nAnda telah memilih pesanan berikut:");
                    System.out.println(pesanan.toString());
                    System.out.println("------------------------------------------------ +");
                    System.out.printf("%n%-20s %-10d %-10s%n", "Total", totalItems, "Rp. " + totalHarga);

                    System.out.print("\nApakah Anda ingin melanjutkan pembayaran? (y/n): ");
                    scanner.nextLine();
                    String confirmation = scanner.nextLine();

                    if (confirmation.equalsIgnoreCase("y")) {
                        printWriter.print("------------------------------------------------ +");
                        printWriter.printf("%n%-20s %-10d %-10s%n", "Total", totalItems, "Rp. " + totalHarga);
                        printWriter.close();
                        System.out.println("Pesanan berhasil disimpan. Terima kasih telah menggunakan Binar Fuud.");

                    } else {
                        System.out.println("Pesanan dibatalkan.");
                    }

                } else {
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
                }
            }

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan dalam menyimpan pesanan.");
            e.printStackTrace();
        }
    }

    public static void lihatStruk() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("struk_pembayaran.txt"));
            String line;
            System.out.println("\n=========================================");
            System.out.println("            Struk Pembayaran            ");
            System.out.println("=========================================");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan dalam membaca file.");
            e.printStackTrace();
        }
    }

    public static String getMenu(int choice) {
        String[] menu = { "Nasi Goreng", "Mie Goreng", "Nasi + Ayam", "Es Teh Manis", "Es Jeruk" };
        return menu[choice - 1];
    }

    public static int getHarga(int choice) {
        int[] harga = { 15000, 13000, 18000, 3000, 5000 };
        return harga[choice - 1];
    }

}