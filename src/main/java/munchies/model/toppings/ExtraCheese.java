package munchies.model.toppings;

import munchies.model.Dish;

import java.math.BigDecimal;

/**
 * Topping that adds extra cheese to a dish.
 * Forms part of the Decorator Pattern Implementation (F5).
 * allowing additional behaviour (extra cost and topping metadata) to be added dynamically,
 * without modifying the base Dish.
 */
public class ExtraCheese extends ToppingDecorator {

    // Wraps the chose dish with an ExtraCheese topping
    public ExtraCheese(Dish dish) {
        super(dish);
    }

    // Returns the display name of this topping.
    @Override
    protected String getToppingName() {
        return "Extra Cheese";
    }

    // Returns the price of the Extra Cheese topping.
    @Override
    protected BigDecimal getToppingPrice() {
        return new BigDecimal("20.00");
    }
}