package synrgy.binfod.model;

public class FoodOrder extends Order {
    public FoodOrder(MenuItem item, int quantity) {
        super(item, quantity);
    }

    @Override
    public int getTotalPrice() {
        return getItem().getPrice() * getQuantity();
    }
}