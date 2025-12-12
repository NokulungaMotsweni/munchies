package munchies.model.toppings;

import munchies.model.Dish;

import java.math.BigDecimal;

public class Bacon  extends ToppingDecorator {



    public Bacon(Dish dish) {
        super(dish);
    }


    @Override
    protected String getToppingName() {
        return "Bacon";
    }

    @Override
    public BigDecimal getToppingPrice() {
        return new BigDecimal("25.00");
    }

}
