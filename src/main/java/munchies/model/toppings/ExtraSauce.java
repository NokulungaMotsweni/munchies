package munchies.model.toppings;

import munchies.model.Dish;

import java.math.BigDecimal;

// Adds Extra Sauce to the dish.
public class ExtraSauce extends ToppingDecorator {

    public ExtraSauce(Dish dish) {
        super(dish);
    }

    @Override
    public String getName() {
        return dish.getName() + " + Extra Sauce";
    }

    @Override
    public BigDecimal getPrice() {
        return dish.getPrice().add(new BigDecimal("25.00"));
    }

    @Override
    protected String getToppingName() {
        return "Extra Sauce";
    }

}
