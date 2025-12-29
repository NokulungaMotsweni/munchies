package munchies.model;

import java.math.BigDecimal;
import java.util.List;

/*
 * Provides value object representing topping metadata.
 * Read-only and display for CLI.

 */


public record ToppingInfo(String name, BigDecimal price) {
    /*
    * Read-only catalogue for the available toppings.
    */
    public static List<ToppingInfo> availableToppings() {
        return List.of(
                new ToppingInfo("Extra Cheese", new BigDecimal("20.00")),
                new ToppingInfo("Extra Sauce", new BigDecimal("10.00")),
                new ToppingInfo("Bacon", new BigDecimal("25.00")),
                new ToppingInfo("Mushrooms", new BigDecimal("30.00"))
        );
    }

}
