package munchies.model.toppings;

import munchies.model.Dish;
import java.math.BigDecimal;

/**
 * Adds Mushrooms to the dish.
 * Forms part of the Decorator Pattern Implementation (F5).
 * allowing additional behaviour (extra cost and topping metadata) to be added dynamically,
 * without modifying the base Dish.
 */
public class Mushrooms extends ToppingDecorator {

        // Wraps an existing Dish with the Mushrooms topping.
    public Mushrooms(Dish dish) {
        super(dish);
    }

    // Returns the display name of this topping.
    @Override
    protected String getToppingName() {
        return "Mushrooms";
    }

    // Returns the price of the Mushrooms topping.
    @Override
    protected BigDecimal getToppingPrice() {
        return new BigDecimal("18.00");
    }
}
