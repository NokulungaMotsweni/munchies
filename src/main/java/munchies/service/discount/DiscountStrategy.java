package munchies.service.discount;

import java.math.BigDecimal;

/**
 * Interface for the Strategy Pattern handling discounts.
 */
public interface DiscountStrategy {
    BigDecimal apply(BigDecimal subtotal);
}