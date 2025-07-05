package cart;

import product.Product;

public class CartItem {
    Product product;
    int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    
    public double getTotalPrice() {
        return (product.getPrice() * quantity);
    }
    
    public Product getProduct() {
        return product;
    } 
    public int getQuantity() {
        return quantity;
    }   

    public String toString() {
        return "CartItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", totalPrice=" + getTotalPrice() +
                '}';
    }
}
