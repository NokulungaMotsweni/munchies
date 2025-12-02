package munchies.model.toppings;

import munchies.model.Dish;

import java.math.BigDecimal;

/**
 * Topping that adds extra cheese to a dish.
 */
public class ExtraCheese extends ToppingDecorator {

    private final BigDecimal toppingPrice = new BigDecimal("20.00");

    // Wraps the chose dish with an ExtraCheese topping
    public ExtraCheese(Dish dish) {
        super(dish);
    }

    // Returns the name of the dish with the added topping
    @Override
    public String getName() {
        return dish.getName();
    }

    // Returns the price of the dish, including the topping cost
    @Override
    public BigDecimal getPrice() {
        // Add the topping price to the wrapped dish's price in CZK
        return dish.getPrice().add(toppingPrice);
    }

    @Override
    protected String getToppingName() {
        return "Extra Cheese (+" + toppingPrice + " CZK)";
    }
}
