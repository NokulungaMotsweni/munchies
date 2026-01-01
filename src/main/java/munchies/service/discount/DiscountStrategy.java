package munchies.service.discount;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Interface for the Strategy Pattern handling discounts.
 */
public interface DiscountStrategy {
    BigDecimal apply(BigDecimal subtotal);
}



