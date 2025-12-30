package munchies.service.discount;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Implementation: Deducts a percentage from the subtotal.
 */
public class PercentageDiscount implements DiscountStrategy {
    private final BigDecimal percentage;

    // The CLI determines the percentage (e.g., from a promo code) and passes it here
    public PercentageDiscount(double percentage) {
        this.percentage = BigDecimal.valueOf(percentage);
    }

    @Override
    public BigDecimal apply(BigDecimal subtotal) {
        // subtotal - (subtotal * percentage / 100)
        BigDecimal discountAmount = subtotal.multiply(percentage)
                .divide(new BigDecimal("100"), RoundingMode.HALF_UP);
        return subtotal.subtract(discountAmount);
    }
}