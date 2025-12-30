package munchies.service.discount;

import java.math.BigDecimal;

/**
 * Implementation: No discount applied to the subtotal.
 */
public class NoDiscount implements DiscountStrategy {
    @Override
    public BigDecimal apply(BigDecimal subtotal) {
        return subtotal;
    }
}