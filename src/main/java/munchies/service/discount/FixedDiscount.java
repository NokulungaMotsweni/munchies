package munchies.service.discount;

import java.math.BigDecimal;

/**
 * Implementation: Deducts a fixed monetary amount from the subtotal.
 */
public class FixedDiscount implements DiscountStrategy {
    private final BigDecimal discountAmount;

    // The CLI collects the fixed amount and passes it here
    public FixedDiscount(double amount) {
        this.discountAmount = BigDecimal.valueOf(amount);
    }

    @Override
    public BigDecimal apply(BigDecimal subtotal) {
        BigDecimal result = subtotal.subtract(discountAmount);
        // Ensure the total doesn't go below zero
        return result.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : result;
    }
}