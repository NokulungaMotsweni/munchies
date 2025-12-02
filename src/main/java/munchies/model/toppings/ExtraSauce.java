package munchies.model.toppings;

import munchies.model.Dish;

import java.math.BigDecimal;

// Adds Extra Sauce to the dish.
public class ExtraSauce extends ToppingDecorator {

    private ExtraSauce(Dish dish) {
        super(dish);
    }

    @Override
    public String getName() {
        return dish.getName() + " + Extra Sauce";
    }

    @Override
    public BigDecimal getPrice() {
        return dish.getPrice().add(new BigDecimal("10.00")); // or whatever price you choose
    }

    @Override
    protected String getToppingName() {
        return "Extra Sauce";
    }

}
