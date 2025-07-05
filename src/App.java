import java.time.LocalDate;

import classes.Customer;
import product.ExpirableProduct;
import product.ShippableExpirableProduct;
import product.ShippableNotExpirableProduct;
import product.SimpleProduct;

public class App {
    public static void main(String[] args) throws Exception {
        
        ShippableNotExpirableProduct tv = new ShippableNotExpirableProduct("TV", 1000, 3, 5.0);
        ShippableExpirableProduct cheese = new ShippableExpirableProduct("Cheese", 200, 10, LocalDate.now().plusDays(10), 0.4);
        ExpirableProduct scratchCard = new ExpirableProduct("Scratch Card", 50, 20, LocalDate.now().plusDays(5));
        SimpleProduct mug = new SimpleProduct("Mug", 80, 15);
        Customer customer = new Customer("Muhannd", 5000);
        
        customer.getCart().addItem(mug, 1);
        customer.getCart().addItem(cheese, 2);
        System.out.println(customer.getCart().toString());
        customer.checkoutItem(1);


        // Add products to cart
        customer.getCart().addItem(tv, 4);
        customer.getCart().addItem(cheese, 7);
        customer.getCart().addItem(scratchCard, 3);
        customer.getCart().addItem(mug, 4);

        
        System.out.println(customer.getCart().toString());
        // Checkout
        customer.checkoutAll();

    }
}
