package munchies.model.toppings;

import munchies.model.Dish;
import java.math.BigDecimal;

// Adds Mushroom topping to a Dish.
public class Mushrooms extends ToppingDecorator {

    private final BigDecimal toppingPrice = new BigDecimal("18.00");

    public Mushrooms(Dish dish) {
        super(dish);
    }

    @Override
    public BigDecimal getPrice() {
        return dish.getPrice().add(toppingPrice);
    }

    @Override
    protected String getToppingName() {
        return "Mushrooms (+" + toppingPrice + " CZK)";
    }
}
