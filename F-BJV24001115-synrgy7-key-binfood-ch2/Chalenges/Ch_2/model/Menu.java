package Chalenges.Ch_2.model;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<MenuItem> menuItems;

    public Menu() {
        menuItems = new ArrayList<>();
        menuItems.add(new FoodMenuItem("Nasi Goreng", 15000));
        menuItems.add(new FoodMenuItem("Mie Goreng", 13000));
        menuItems.add(new FoodMenuItem("Nasi + Ayam", 18000));
        menuItems.add(new DrinkMenuItem("Es Teh Manis", 3000));
        menuItems.add(new DrinkMenuItem("Es Jeruk", 5000));
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
}
