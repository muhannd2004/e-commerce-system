package classes;

import java.util.ArrayList;
import java.util.List;

import cart.Cart;
import cart.CartItem;
import interfaces.Shippable;
import product.Product;


import java.util.UUID;

public class Customer {
    private final String id;
    private String name;
    private double balance;
    private Cart cart;

    private static final double SHIPPING_COST_PER_KG = 30.0;


    public Customer(String name, double balance) {
        // Generate a unique ID for each customer
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.balance = balance;
        this.cart = new Cart();
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public Cart getCart() {
        return cart;
    }

    // Checkout a single item by its index (1-based for user, 0-based for code)
    public void checkoutItem(int itemIndex) {
        itemIndex--; // Adjust for 0-based index    
        if (itemIndex < 0 || itemIndex >= cart.getItems().size()) {
            System.out.println("Invalid item index.");
            return;
        }
        CartItem cartItem = cart.getItems().get(itemIndex);
        Product product = cartItem.getProduct();
        // Check if product is expired
        if (product.isExpired()) {
            System.out.println("Product " + product.getName() + " is expired and cannot be purchased.");
            return;
        }
        // Check if enough stock is available
        if (cartItem.getQuantity() > product.getQuantity()) {
            System.out.println("Not enough stock for " + product.getName());
            return;
        }
        double itemPrice = cartItem.getTotalPrice();
        double totalWeight = 0;
        double shippingFee = 0;

        List<Shippable> toShip = new ArrayList<>();

        // If the product is shippable, calculate total weight and shipping fee
        if (product instanceof Shippable shippable) {
            for (int i = 0; i < cartItem.getQuantity(); i++) {
                toShip.add(shippable);
                totalWeight += shippable.getWeight();
            }
            shippingFee = totalWeight * SHIPPING_COST_PER_KG;
        }

        double totalPrice = itemPrice + shippingFee;
        // Check if customer has enough balance
        if (totalPrice <= balance) {
            balance -= totalPrice;
            product.reduceQuantity(cartItem.getQuantity());
            cart.removeItem(product);
            // Print checkout summary
            System.out.printf(
                "Checkout successful!%n" +
                "Item: %s x%d%n" +
                "Subtotal: %.2f%n" +
                "Shipping: %.2f%n" +
                "Total Weight: %.2fkg%n" +
                "Total Paid: %.2f%n" +
                "Remaining balance: %.2f%n \n",
                product.getName(), cartItem.getQuantity(),
                itemPrice, shippingFee, totalWeight,
                totalPrice, balance
            );
        } else {
            System.out.println("Insufficient balance for checkout.");
        }
    }
    // Checkout all items in the cart
    public void checkoutAll() {
        double subtotal = cart.getTotalPrice();
        double totalWeight = 0;
        List<Shippable> itemsToShip = new ArrayList<>();
        // Loop through all cart items to check validity and calculate shipping
        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();
            // Check if product is expired
            if (product.isExpired()) {
                System.out.println("Product " + product.getName() + " is expired and cannot be purchased.");
                return;
            }
            // Check if enough stock is available
            if (cartItem.getQuantity() > product.getQuantity()) {
                System.out.println("Not enough stock for " + product.getName());
                return;
            }
            // If product is shippable, add to shipping list and sum weight
            if (product instanceof Shippable shippable) {
                for (int i = 0; i < cartItem.getQuantity(); i++) {
                    itemsToShip.add(shippable);
                    totalWeight += shippable.getWeight();
                }
            }
        }

        double shippingFee = totalWeight > 0 ? totalWeight * SHIPPING_COST_PER_KG : 0;
        double totalPrice = subtotal + shippingFee;

        // Check if customer has enough balance
        if (totalPrice <= balance) {
            balance -= totalPrice;
            for (CartItem cartItem : cart.getItems()) {
                cartItem.getProduct().reduceQuantity(cartItem.getQuantity());
            }
            cart.clear();
            // Print checkout summary
            System.out.printf(
                "Checkout successful!%n" +
                "Subtotal: %.2f%n" +
                "Shipping: %.2f%n" +
                "Total Weight: %.2fkg%n" +
                "Total Paid: %.2f%n" +
                "Remaining balance: %.2f%n\n",
                subtotal, shippingFee, totalWeight, totalPrice, balance
            );
        } else {
            System.out.println("Insufficient balance for checkout.");
        }
    }
}

