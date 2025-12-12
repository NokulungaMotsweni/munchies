package munchies.model.toppings;

import munchies.model.Dish;

import java.math.BigDecimal;

/**
 * Topping that adds extra cheese to a dish.
 */
public class ExtraCheese extends ToppingDecorator {

    // Wraps the chose dish with an ExtraCheese topping
    public ExtraCheese(Dish dish) {
        super(dish);
    }

    @Override
    protected String getToppingName() {
        return "Extra Cheese";
    }

    @Override
    protected BigDecimal getToppingPrice() {
        return new BigDecimal("20.00");
    }
}
