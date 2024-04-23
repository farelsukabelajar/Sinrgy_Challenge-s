package synrgy.binfod.model;

public class DrinkOrder extends Order {
    public DrinkOrder(MenuItem item, int quantity) {
        super(item, quantity);
    }

    @Override
    public int getTotalPrice() {
        return getItem().getPrice() * getQuantity();
    }
}
