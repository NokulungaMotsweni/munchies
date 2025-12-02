package munchies.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Represents a single ordered dish, including toppings.
 * Works with the Decorator Pattern (F5).
 */
public class OrderItem {

    private final Dish dish; // can be BaseDish or a decorated dish with topping

    public OrderItem(Dish dish) {
        this.dish = dish;
    }

    public String getName() {
        return dish.getName();
    }

    public BigDecimal getLineTotal() {
        return dish.getPrice();
    }

    public List<String> getToppings() {
        return dish.getToppings();
    }

    // Prints the dish name & price, followed by its toppings indented beneath.
    public void printItem() {
        System.out.println(getName() + " (" + getLineTotal() + " CZK)");

        for (String topping : getToppings()) {
            System.out.println("    • " + topping);
        }
    }
}
