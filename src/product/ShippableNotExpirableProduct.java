package product;

public class ShippableNotExpirableProduct extends Product implements interfaces.Shippable {
    private double weight;

    public ShippableNotExpirableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }
    
    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public boolean isExpired() {
      return false;
    }

}
