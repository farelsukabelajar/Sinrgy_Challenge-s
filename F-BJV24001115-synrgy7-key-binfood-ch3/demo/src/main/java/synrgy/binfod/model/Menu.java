package synrgy.binfod.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

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

    public Optional<MenuItem> findItemByName(String name) {
        return menuItems.stream()
                        .filter(item -> item.getName().equalsIgnoreCase(name))
                        .findFirst();
    }

    public List<MenuItem> findItemsByType(String type) {
        return menuItems.stream() 
                        .filter(item -> item.getClass().getSimpleName().contains(type)) 
                        .collect(Collectors.toList()); 
    }
}
