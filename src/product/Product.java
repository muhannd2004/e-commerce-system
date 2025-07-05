package product;

import java.util.UUID;

public abstract class Product {
    private final String id;
    private String name;
    private double price;
    private int quantity;
    
    public Product(String name, double price, int quantity) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public abstract boolean isExpired();
    

    public boolean isAvailable(int requestedQty) {
        return requestedQty <= quantity && !isExpired();
    }
    
    public void reduceQuantity(int qty) {
        quantity -= qty;
    }

    public void increaseQuantity(int qty) {
        quantity += qty;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + ", quantity=" + quantity + "}";
    }
}
