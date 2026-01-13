package munchies.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
/**
 * Represents a basic dish without any toppings.
 * This class wraps a MenuItem so that toppings can be added
 * using the Decorator pattern (F5).
 */
public class BaseDish implements Dish {

    // Display name of the dish
    private final String name;

    // Base price of the dish before any toppings are applied
    private final BigDecimal price;

    // Creates a base dish with a name and base price.
    public BaseDish(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    // Returns the name of the dish.
    @Override
    public String getName() {
        return name;
    }

    // Returns the base price of the dish.
    @Override
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Returns the list of toppings applied to this dish.
     * This is the Base dish, no toppings are present.
     *
     */
    @Override
    public List<ToppingInfo> getToppings() {
        return new ArrayList<>();
    }
}
