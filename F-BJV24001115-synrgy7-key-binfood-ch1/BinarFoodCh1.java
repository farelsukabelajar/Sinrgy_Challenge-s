import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BinarFoodCh1 {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            printMenu();
            System.out.print("Pilihan Anda: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        pesanMakanan();
                        break;
                    case 2:
                        lihatStruk();
                        break;
                    case 0:
                        System.out.println("Terima kasih telah menggunakan Binar Food.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
                }
            } else {
                System.out.println("Masukkan pilihan yang valid (angka).");
                scanner.next();
                choice = -1;
            }

        } while (choice != 0);

    }

    public static void pesanMakanan() {

        int choice;
        int qty;
        boolean pesanLagi = true;
        int totalHarga = 0;
        int totalItems = 0;
        StringBuilder pesanan = new StringBuilder();

        try (PrintWriter printWriter = new PrintWriter(new FileWriter("struk_pembayaran.txt", true))) {
            printWriter.println("\n===========================================");
            printWriter.println("             Struk Pembayaran              ");
            printWriter.println("===========================================");
            printWriter.printf("\n%-20s %-10s %-10s%n", "Menu", "Qty", "Harga\n\n");

            while (pesanLagi) {
                printFoodMenu();
                System.out.print("\nMasukkan pilihan: ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    if (choice >= 1 && choice <= 5) {
                        System.out.print("Jumlah: ");
                        qty = scanner.nextInt();
                        if (qty >= 0) {
                            String menu = getMenu(choice);
                            int harga = getHarga(choice);
                            totalHarga += (harga * qty);
                            totalItems += qty;
                            pesanan.append(String.format("%-20s %-10d %-10s%n", menu, qty, "Rp. " + (harga * qty)));
                            System.out.println("Pesanan berhasil ditambahkan.");

                            printWriter.printf("%-20s %-10d %-10s%n", menu, qty, "Rp. " + (harga * qty));
                        } else {
                            System.out.println("Jumlah pesanan harus non-negatif.");
                        }
                    } else if (choice == 99) {
                        pesanLagi = false;
                        System.out.println("\n===========================================");
                        System.out.println("             Pesan dan Bayar              ");
                        System.out.println("===========================================");

                        System.out.println("\nAnda telah memilih pesanan berikut:\n");
                        System.out.println(pesanan.toString());
                        System.out.println("------------------------------------------- +");
                        System.out.printf("%n%-20s %-10d %-10s%n", "Total", totalItems, "Rp. " + totalHarga);

                        System.out.print("\nLanjutkan pembayaran? (y/t): ");
                        scanner.nextLine();
                        String confirmation = scanner.nextLine();

                        if (confirmation.equalsIgnoreCase("y")) {
                            printWriter.print("------------------------------------------------ +");
                            printWriter.printf("%n%-20s %-10d %-10s%n", "Total", totalItems, "Rp. " + totalHarga);
                            printWriter.println("\n\npembayaran     : BinarCash");
                            printWriter.println("\nWaktu          : " + getCurrentDateTime());
                            printWriter.println("\n\n===========================================");
                            printWriter.println("            Simpan Struk Sebagai           ");
                            printWriter.println("              Bukti Pembayaran             ");
                            printWriter.println("===========================================");
                            System.out.println("\nPesanan berhasil disimpan.");

                        } else {
                            System.out.println("Pesanan dibatalkan.");
                        }

                    } else {
                        System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
                    }

                } else {
                    System.out.println("Masukkan pilihan yang valid (angka).");
                    scanner.next();
                }
            }

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan dalam menyimpan pesanan.");
            e.printStackTrace();
        }
    }

    public static void lihatStruk() {
        try (BufferedReader reader = new BufferedReader(new FileReader("struk_pembayaran.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file.");
            e.printStackTrace();
        }
    }

    public static void printMenu() {

        System.out.println("\n=========================================");
        System.out.println("                  Menu                  ");
        System.out.println("=========================================\n");
        System.out.println("1. Pilih makanan");
        System.out.println("2. Lihat Struk");
        System.out.println("0. Keluar");

    }

    public static void printFoodMenu() {

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

    }

    public static String getMenu(int choice) {

        String[] menu = { "Nasi Goreng", "Mie Goreng", "Nasi + Ayam", "Es Teh Manis", "Es Jeruk" };
        return menu[choice - 1];

    }

    public static int getHarga(int choice) {

        int[] harga = { 15000, 13000, 18000, 3000, 5000 };
        return harga[choice - 1];

    }

    public static String getCurrentDateTime() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(new Date());

    }
}
