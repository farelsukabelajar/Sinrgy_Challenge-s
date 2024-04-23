package synrgy.binfod.model;

public abstract class Order {
    private MenuItem item;
    private int quantity;

    protected Order(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public MenuItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public abstract int getTotalPrice();
}
