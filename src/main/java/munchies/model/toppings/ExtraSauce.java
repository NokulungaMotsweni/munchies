package munchies.model.toppings;

import munchies.model.Dish;

import java.math.BigDecimal;

/**
 * Adds Extra Sauce to the dish.
 * Forms part of the Decorator Pattern Implementation (F5).
 * allowing additional behaviour (extra cost and topping metadata) to be added dynamically,
 * without modifying the base Dish.
 */


public class ExtraSauce extends ToppingDecorator {


    public ExtraSauce(Dish dish) {
        super(dish);
    }

    @Override
    protected String getToppingName() {
        return "Extra Sauce";
    }

    @Override
    protected BigDecimal getToppingPrice() {
        return new BigDecimal("25.00");
    }
}

