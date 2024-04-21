package synrgy.binfod.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import synrgy.binfod.model.*;
import synrgy.binfod.util.ReceiptUpdater;
import synrgy.binfod.view.MainView;

public class OrderService {

    private List<Order> orders;

    public int calculateTotalPrice(List<Order> orders) {
        return orders.stream()
                .mapToInt(Order::getTotalPrice)
                .sum();
    }

    public OrderService() {
        this.orders = new ArrayList<>();
    }

    public List<Order> createOrderProcess(Menu menu, MainView view, Scanner scanner) {
        boolean addMore = true;
        do {
            List<MenuItem> menuItems = menu.getMenuItems();
            view.displayItems(menuItems);
            System.out.print("\nPilih Menu: ");
            int choice = scanner.nextInt();
            if (choice >= 1 && choice <= menuItems.size()) {
                MenuItem menuItem = menuItems.get(choice - 1);
                System.out.print("Jumlah Pesanan: ");
                int qty = scanner.nextInt();
                if (qty >= 0) {
                    Order order = createOrder(menuItem, qty);
                    orders.add(order);
                    view.displayAddMenu();
                    System.out.print("\nApakah Anda ingin menambah pesanan lagi? (y/t): ");
                    addMore = scanner.next().equalsIgnoreCase("y");
                } else {
                    System.out.println("\nJumlah pesanan harus non-negatif.");
                }
            } else {
                System.out.println("\nPilihan tidak valid. Silakan pilih lagi.");
            }
        } while (addMore);
        return orders;
    }

    private Order createOrder(MenuItem menuItem, int quantity) {
        if (menuItem instanceof FoodMenuItem) {
            return new FoodOrder(menuItem, quantity);
        } else if (menuItem instanceof DrinkMenuItem) {
            return new DrinkOrder(menuItem, quantity);
        } else {
            throw new IllegalArgumentException("MenuItem tidak valid.");
        }
    }

    public void saveReceiptToFile(List<Order> orders) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("struk.txt", true))) {
            String receipt = ReceiptUpdater.generateReceipt(orders);
            writer.write(receipt);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getOrders() {
        return orders;
    }
}
