package munchies.model.toppings;

import munchies.model.Dish;

import java.math.BigDecimal;

// Adds Extra Sauce to the dish.
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

