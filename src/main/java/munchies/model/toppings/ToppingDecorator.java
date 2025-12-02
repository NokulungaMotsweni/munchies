package munchies.model.toppings;

import munchies.model.Dish;

import java.math.BigDecimal;

/**
 * A base class for toppings that wrap another Dish.
 * Provides default behaviour by delegating to the wrapped dish.
 */

public abstract class ToppingDecorator implements Dish {

    // The Dish being wrapped by the ToppingDecorator
    protected final Dish dish;

    // Creates a ToppingDecorator
    public ToppingDecorator(Dish dish) {
        this.dish = dish;
    }

    // Returns the dish name
    @Override
    public String getName() {
        return dish.getName();
    }

    // Returns the dish price in CZK
    @Override
    public BigDecimal getPrice() {
        return dish.getPrice();
    }
}
