package munchies.model.toppings;

import munchies.model.Dish;

import java.math.BigDecimal;

/**
 * Forms part of the Decorator Pattern Implementation (F5).
 * allowing additional behaviour (extra cost and topping metadata) to be added dynamically,
 * without modifying the base Dish.
 */
public class Bacon  extends ToppingDecorator {


    // Wraps an existing Dish with the Bacon topping.
    public Bacon(Dish dish) {
        super(dish);
    }

    // Returns the display name of this topping.
    @Override
    protected String getToppingName() {
        return "Bacon";
    }

    // Returns the price of the Bacon topping.
    @Override
    public BigDecimal getToppingPrice() {
        return new BigDecimal("25.00");
    }

}
