package munchies.model.toppings;

import munchies.model.Dish;

import java.math.BigDecimal;

public class Bacon  extends ToppingDecorator {



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
