package munchies.model.toppings;

import munchies.model.Dish;

import java.math.BigDecimal;

// Adds Extra Sauce to the dish.
public class ExtraSauce extends ToppingDecorator {

    private final BigDecimal toppingPrice = new BigDecimal("25.00");

    public ExtraSauce(Dish dish) {
        super(dish);
    }

    @Override
    public BigDecimal getPrice() {
        return dish.getPrice().add(toppingPrice);
    }

    @Override
    protected String getToppingName() {
        return "Extra Sauce (+" + toppingPrice + " CZK)";
    }

}
