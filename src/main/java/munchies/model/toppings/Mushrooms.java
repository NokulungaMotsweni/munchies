package munchies.model.toppings;

import munchies.model.Dish;
import java.math.BigDecimal;

// Adds Mushroom topping to a Dish.
public class Mushrooms extends ToppingDecorator {

    public Mushrooms(Dish dish) {
        super(dish);
    }

    @Override
    public String getName() {
        return dish.getName() + " + Mushrooms";
    }

    @Override
    public BigDecimal getPrice() {
        return dish.getPrice().add(new BigDecimal("18.00"));
    }

    @Override
    protected String getToppingName() {
        return "Mushrooms";
    }
}
