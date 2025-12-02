package munchies.model.toppings;

import munchies.model.Dish;

import java.math.BigDecimal;

public class Bacon  extends ToppingDecorator {

    public Bacon(Dish dish) {
        super(dish);
    }

    @Override
    public String getName() {
        return dish.getName();
    }

    public BigDecimal getPrice() {
        return dish.getPrice().add(new BigDecimal("50.00"));
    }

    @Override
    protected String getToppingName() {
        return "Bacon";
    }
}
