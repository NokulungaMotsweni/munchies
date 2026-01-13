package munchies.model;

import java.math.BigDecimal;

/**
 * Immutable value object representing a single topping applied to a Dish.
 * Used by topping decorators to expose, read-only information about applied toppings,
 * such as their name and individual price.
 */
public record ToppingInfo(String name, BigDecimal price) {

}
