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

    private final String name;
    private final BigDecimal price;

    public BaseDish(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public List<String> getToppings() {
        return new ArrayList<>();
    }
}
