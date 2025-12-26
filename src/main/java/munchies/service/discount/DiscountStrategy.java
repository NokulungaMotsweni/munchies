package munchies.service.discount;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Interface for the Strategy Pattern handling discounts.
 */
public interface DiscountStrategy {
    BigDecimal apply(BigDecimal subtotal);
}

/**
 * Implementation: No discount applied to the subtotal.
 */
class NoDiscount implements DiscountStrategy {
    @Override
    public BigDecimal apply(BigDecimal subtotal) {
        return subtotal;
    }
}

/**
 * Implementation: Deducts a percentage from the subtotal.
 */
class PercentageDiscount implements DiscountStrategy {
    private final BigDecimal percentage;

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

/**
 * Implementation: Deducts a fixed monetary amount from the subtotal.
 */
class FixedDiscount implements DiscountStrategy {
    private final BigDecimal discountAmount;

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