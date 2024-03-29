package Chalenges.Ch_2.model;

public class FoodMenuItem implements MenuItem {
    private String name;
    private int price;

    public FoodMenuItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
