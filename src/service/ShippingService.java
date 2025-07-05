package service;

import java.util.List;

import interfaces.Shippable;

public class ShippingService {
        public static void shipItems(List<Shippable> items) {
        double totalWeight = 0;
        System.out.println("** Shipment notice **");
        for (Shippable item : items) {
            System.out.printf("Shipping %s, weight %.1fkg\n", item.getName(), item.getWeight());
            totalWeight += item.getWeight();
        }
        System.out.printf("Total package weight %.1fkg\n\n", totalWeight);
    }
}
