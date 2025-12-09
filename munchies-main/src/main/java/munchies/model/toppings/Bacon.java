package munchies.model.toppings;

import munchies.model.Dish;

import java.math.BigDecimal;

public class Bacon  extends ToppingDecorator {

    private final BigDecimal toppingPrice = new BigDecimal("25.00");

    public Bacon(Dish dish) {
        super(dish);
    }


    public BigDecimal getPrice() {
        return dish.getPrice().add(toppingPrice);
    }

    @Override
    protected String getToppingName() {
        return "Bacon";
    }
}
