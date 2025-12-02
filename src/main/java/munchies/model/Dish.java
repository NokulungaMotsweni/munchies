package munchies.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Represents a dish that can be ordered from a restaurant.
 * Provides the name and price of the dish.
 */

public interface Dish {
    String getName();
    BigDecimal getPrice();

    /**
     * Returns a list of topping names applied to this dish.
     * Base dishes return an empty list.
     */
    List<String> getToppings();
}
