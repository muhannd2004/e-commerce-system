import java.time.LocalDate;

import classes.Customer;
import product.ExpirableProduct;
import product.ShippableExpirableProduct;
import product.ShippableNotExpirableProduct;
import product.SimpleProduct;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("=== E-Commerce System Test ===\n");

        // Create products
        ShippableNotExpirableProduct tv = new ShippableNotExpirableProduct("TV", 1000, 3, 5.0);
        ShippableExpirableProduct cheese = new ShippableExpirableProduct("Cheese", 200, 10, LocalDate.now().plusDays(10), 0.4);
        SimpleProduct mug = new SimpleProduct("Mug", 80, 15);

        // Create customer
        Customer customer = new Customer("Muhannd", 5000);

        // Add one of each product
        System.out.println("Adding 1 Mug, 1 Cheese, 1 TV to cart");
        customer.getCart().addItem(mug, 1);
        customer.getCart().addItem(cheese, 1);
        customer.getCart().addItem(tv, 1);
        System.out.println("Cart:");
        System.out.println(customer.getCart().toString());

        // Checkout one item
        System.out.println("\nChecking out item at index 1 (Mug)...");
        customer.checkoutItem(1);
        System.out.println("Cart after checkout:");
        System.out.println(customer.getCart().toString());

        // Add more cheese and checkout all
        System.out.println("\nAdding 2 more Cheese to cart");
        customer.getCart().addItem(cheese, 2);
        System.out.println("Cart before final checkout:");
        System.out.println(customer.getCart().toString());

        System.out.println("\nChecking out all items...");
        customer.checkoutAll();
        System.out.println("Cart after checkout:");
        System.out.println(customer.getCart().toString());

        System.out.println("\n=== End of Test ===");
    }
}
