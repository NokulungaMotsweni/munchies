package munchies.model.toppings;

import munchies.model.Dish;
import java.math.BigDecimal;

// Adds Mushroom topping to a Dish.
public class Mushrooms extends ToppingDecorator {

    public Mushrooms(Dish dish) {
        super(dish);
    }

    @Override
    protected String getToppingName() {
        return "Mushrooms";
    }

    @Override
    protected BigDecimal getToppingPrice() {
        return new BigDecimal("18.00");
    }
}
