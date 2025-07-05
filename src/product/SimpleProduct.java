package product;

public class SimpleProduct extends Product {
    public SimpleProduct(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    @Override
    public boolean isExpired() {
        return false;
    }
}
