package munchies.model;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents a single ordered dish, including toppings.
 * Works with the Decorator Pattern (F5).
 */
public class DishOrderItem implements OrderItem {

    public static final int NAME_WIDTH = 20;
    public static final int PRICE_WIDTH = 8;
    private static final int PREFIX_WIDTH = 4;


    private final Dish dish; // can be BaseDish or a decorated dish with topping

    public DishOrderItem(Dish dish) {

        this.dish = dish;
    }

    @Override
    public String getName() {
        return dish.getName();
    }

    @Override
    public BigDecimal getLineTotal() {
        return dish.getPrice();
    }

    private void printLine(String label, BigDecimal price, int nameWidth) {
        System.out.printf(
                "%-" + nameWidth + "s %" + PRICE_WIDTH + ".2f CZK%n",
                label,
                price
        );
    }



    @Override
    public void printItem() {

        // Calculate total price of toppings
        BigDecimal toppingsTotal = BigDecimal.ZERO;
        for (ToppingInfo topping : dish.getToppings()) {
            toppingsTotal = toppingsTotal.add(topping.price());
        }

        // Derive base dish price
        BigDecimal basePrice = dish.getPrice().subtract(toppingsTotal);

        // Print base dish (base price only)
        printLine(dish.getName(), basePrice, NAME_WIDTH);


        // Print toppings aligned to same price column
        Map<String, Integer> toppingCounts = new LinkedHashMap<>();
        Map<String, BigDecimal> toppingPrices = new LinkedHashMap<>();

        for (ToppingInfo topping : dish.getToppings()) {
            toppingCounts.merge(topping.name(), 1, Integer::sum);
            toppingPrices.putIfAbsent(topping.name(), topping.price());
        }

        for (String toppingName : toppingCounts.keySet()) {
            int count = toppingCounts.get(toppingName);
            BigDecimal unitPrice = toppingPrices.get(toppingName);

            BigDecimal totalPrice = unitPrice.multiply(BigDecimal.valueOf(count));

            String label = "  + " + toppingName;
            if (count > 1) {
                label += " x" + count;
            }

            printLine(label, totalPrice, NAME_WIDTH + PREFIX_WIDTH);

        }

    }


}
