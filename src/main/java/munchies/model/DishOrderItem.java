package munchies.model;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import static munchies.cli.format.ReceiptFormat.*;

/**
 * Represents a single ordered dish, including toppings.
 * Works with the Decorator Pattern (F5).
 */
public class DishOrderItem implements OrderItem {

    // Can be BaseDish or a decorated dish with topping, the dish being ordered.
    private final Dish dish;

    // Creates a new order item for the given dish.
    public DishOrderItem(Dish dish) {

        this.dish = dish;
    }

    /**
    * Returns the total price of this order item.
    * The Dish already accounts for toppings via decorators.
    */
    @Override
    public BigDecimal getLineTotal() {
        return dish.getPrice();
    }

    // Prints a formatted line item for the receipt.
    private void printLine(String label, BigDecimal price) {
        System.out.printf(
                "%-" + NAME_WIDTH + "s %" + PRICE_WIDTH + ".2f CZK%n",
                label,
                price
        );
    }

    /**
    * Calculates the base price of the dish by subtracting the
    * total cost of all toppings from the final dish price.
    */
    private BigDecimal calculateBasePrice() {
        BigDecimal toppingsTotal = BigDecimal.ZERO;
        for (ToppingInfo topping : dish.getToppings()) {
            toppingsTotal = toppingsTotal.add(topping.price());
        }
        return dish.getPrice().subtract(toppingsTotal);
    }

    /**
     * Groups toppings by name so that repeated toppings
     * (e.g. Extra Sauce x2) can be displayed compactly
     * on the receipt.
     */
    private Map<String, ToppingGroup> groupToppings() {
        Map<String, ToppingGroup> groups = new LinkedHashMap<>();

        for (ToppingInfo topping : dish.getToppings()) {
            groups
                    .computeIfAbsent(
                            topping.name(),
                            name -> new ToppingGroup(name, topping.price())
                    )
                    .increment();
        }

        return groups;
    }

    /**
     * Helper class used to group identical toppings together
     * for receipt formatting purposes.
     */
    private static class ToppingGroup {
        private final String name;
        private final BigDecimal unitPrice;
        private int count = 0;

        ToppingGroup(String name, BigDecimal unitPrice) {
            this.name = name;
            this.unitPrice = unitPrice;
        }

        // Increments the number of times this topping appears.
        void increment() {
            count++;
        }

        // Calculates the total price for this topping group.
        BigDecimal totalPrice() {
            return unitPrice.multiply(BigDecimal.valueOf(count));
        }
    }



    /**
     * Prints this order item in a receipt friendly format,
     * showing the base dish followed by any grouped toppings.
     */
    @Override
    public void printItem() {

        BigDecimal basePrice = calculateBasePrice();

        // Base dish line
        printLine(dish.getName(), basePrice);

        // Group toppings by name (e.g. Extra Sauce x2)
        Map<String, ToppingGroup> groups = groupToppings();

        for (ToppingGroup group : groups.values()) {
            String label = "  + " + group.name;
            if (group.count > 1) {
                label += " x" + group.count;
            }

            System.out.print(" ".repeat(PREFIX_WIDTH));
            printLine(label, group.totalPrice());
        }

    }


}
