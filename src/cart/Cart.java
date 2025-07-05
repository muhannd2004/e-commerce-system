package cart;

import java.util.ArrayList;
import java.util.List;

import product.Product;

public class Cart {
    // List to store all items in the cart
    private List<CartItem> items = new ArrayList<>();

    // Add a product to the cart with a specific quantity
    public void addItem(Product product, int quantity) {
        // Check for valid product and quantity
        if (product.getQuantity() < quantity && product != null) {
            System.out.println(String.format("Product :%s not available or insufficient stock.", product.getName()));
            System.out.println("Available quantity:" + product.getQuantity());
            return;
        }
        if (product == null || quantity <= 0) {
            System.out.println("Product not available ");
            return;
        }else if (!product.isAvailable(quantity)) {
           System.out.println("Product expired.");
           return;
        }
        items.add(new CartItem(product, quantity));
    }

    // Remove a product from the cart
    public void removeItem(Product product) {
        items.removeIf(item -> item.getProduct().equals(product));
    }

    // Get the list of all cart items
    public List<CartItem> getItems() {
        return items;
    }

    // Check if the cart is empty
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // Calculate the total price of all items in the cart
    public double getTotalPrice() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    // Remove all items from the cart
    public void clear() {
        items.clear();
    }

    // String representation of the cart contents
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Cart Items:\n");
        int number = 1;
        for (CartItem item : items) {
            sb.append(number++)
              .append("- ")
              .append(item.getProduct().getName())
              .append(" x")
              .append(item.getQuantity())
              .append("\n");
        }
        return sb.toString();
    }
}
