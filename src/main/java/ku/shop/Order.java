package ku.shop;

import java.util.LinkedHashMap;
import java.util.Map;

public class Order {
    private final Map<Product, Integer> items = new LinkedHashMap<>();
    private double total = 0.0;

    public void addItem(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        product.cutStock(quantity);
        items.merge(product, quantity, Integer::sum);
        total += product.getPrice() * quantity;
    }

    public double getTotal() {
        return total;
    }
}
