# E-Commerce System (Java OOP Project)

## Project Overview

This project is a simple e-commerce system built in Java, focusing on Object-Oriented Programming (OOP) principles. It demonstrates how to model real-world entities like products, customers, and carts using classes, interfaces, and inheritance.

---

## How I Designed the Project

- **OOP Principles:**
  - **Encapsulation:** Each class manages its own data and exposes only what is necessary.
  - **Inheritance:** Product types extend a base `Product` class, and shippable/expirable behaviors are modeled via inheritance and interfaces.
  - **Polymorphism:** The system can handle different product types (shippable, expirable, both, or neither) using the same interface.
  - **Abstraction:** Interfaces like `Shippable` define contracts for shipping-related behavior.

- **Assumptions (Things Ignored or Simplified):**
  - No user authentication or login system is implemented.
  - No persistent storage (database or files); all data is in-memory and lost on exit.
  - No concurrency or multi-user handling.
  - No graphical user interface (GUI); all interaction is via console output.
  - Shipping is always calculated per kilogram for shippable products only.

---

## Main Classes and Interactions

- **Product (abstract):**
  - Base class for all products. Stores name, price, and quantity.
  - Subclasses:
    - `ShippableNotExpirableProduct`: Can be shipped, does not expire.
    - `ShippableExpirableProduct`: Can be shipped and can expire.
    - `ExpirableProduct`: Can expire, not shippable.
    - `SimpleProduct`: Neither shippable nor expirable.

- **Shippable (interface):**
  - Implemented by products that can be shipped. Requires `getWeight()` and `getName()`.

- **Cart & CartItem:**
  - `Cart` holds a list of `CartItem` objects, each representing a product and its quantity.
  - Handles adding, removing, and listing items, as well as calculating the total price.

- **Customer:**
  - Each customer has a unique ID, name, balance, and a cart.
  - Can checkout individual items or the whole cart.
  - Handles balance checks, product availability, and shipping calculations.

- **ShippingService:**
  - Handles the shipping process for shippable products, printing shipment details and total weight.

---

## Example Class Diagram

```
Customer
  |-- Cart
        |-- CartItem
  |-- Product (abstract)
        |-- ShippableNotExpirableProduct
        |-- ShippableExpirableProduct
        |-- ExpirableProduct
        |-- SimpleProduct
  |-- ShippingService
  |-- Shippable (interface)
```

---

## Example Usage

```java
// Create products
ShippableNotExpirableProduct tv = new ShippableNotExpirableProduct("TV", 1000, 3, 5.0);
ShippableExpirableProduct cheese = new ShippableExpirableProduct("Cheese", 200, 10, LocalDate.now().plusDays(10), 0.4);
ExpirableProduct scratchCard = new ExpirableProduct("Scratch Card", 50, 20, LocalDate.now().plusDays(5));
SimpleProduct mug = new SimpleProduct("Mug", 80, 15);
// Create customer
Customer customer = new Customer("Muhannd", 5000);
// Add products to cart
customer.getCart().addItem(mug, 1);
customer.getCart().addItem(cheese, 2);
System.out.println(customer.getCart().toString());
//checkout one item by index
customer.checkoutItem(1);
// Add products to cart
customer.getCart().addItem(tv, 1);
customer.getCart().addItem(cheese, 7);
customer.getCart().addItem(scratchCard, 3);
customer.getCart().addItem(mug, 4);
System.out.println(customer.getCart().toString());
// Checkout
customer.checkoutAll();

```

---

## Example Output

```
Cart Items:
1- Mug x1
2- Cheese x2

Checkout successful!
Item: Mug x1
Subtotal: 80.00
Shipping: 0.00
Total Weight: 0.00kg
Total Paid: 80.00
Remaining balance: 4920.00
 
Cart Items:
1- Cheese x2
2- TV x1
3- Cheese x7
4- Scratch Card x3
5- Mug x4

Checkout successful!
Subtotal: 3270.00
Shipping: 258.00
Total Weight: 8.60kg
Total Paid: 3528.00
Remaining balance: 1392.00
```
---

```java
ShippableNotExpirableProduct tv = new ShippableNotExpirableProduct("TV", 1000, 3, 5.0);
Customer customer = new Customer("Muhannd", 1999);
customer.getCart().addItem(tv, 2);
System.out.println(customer.getCart().toString());
// Checkout
customer.checkoutAll();


```

---

## Example Output

```
Cart Items:
1- TV x2

Insufficient balance for checkout.
```

## How to Run

1. Make sure you have Java installed (JDK 17+ recommended).
2. Compile the project:
   ```sh
   javac -d bin src/**/*.java
   ```
3. Run the main application:
   ```sh
   java -cp bin App
   ```
