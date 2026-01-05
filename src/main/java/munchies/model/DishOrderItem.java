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



    private final Dish dish; // can be BaseDish or a decorated dish with topping

    public DishOrderItem(Dish dish) {

        this.dish = dish;
    }

    @Override
    public BigDecimal getLineTotal() {
        return dish.getPrice();
    }

    private void printLine(String label, BigDecimal price) {
        System.out.printf(
                "%-" + NAME_WIDTH + "s %" + PRICE_WIDTH + ".2f CZK%n",
                label,
                price
        );
    }

    private BigDecimal calculateBasePrice() {
        BigDecimal toppingsTotal = BigDecimal.ZERO;
        for (ToppingInfo topping : dish.getToppings()) {
            toppingsTotal = toppingsTotal.add(topping.price());
        }
        return dish.getPrice().subtract(toppingsTotal);
    }

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

    private static class ToppingGroup {
        private final String name;
        private final BigDecimal unitPrice;
        private int count = 0;

        ToppingGroup(String name, BigDecimal unitPrice) {
            this.name = name;
            this.unitPrice = unitPrice;
        }

        void increment() {
            count++;
        }

        BigDecimal totalPrice() {
            return unitPrice.multiply(BigDecimal.valueOf(count));
        }
    }


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
