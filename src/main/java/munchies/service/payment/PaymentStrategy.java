package munchies.service.payment;

import java.math.BigDecimal;

/**
 * Interface for the Strategy Pattern.
 * The pay method remains, but input collection is moved to the CLI layer.
 */
public interface PaymentStrategy {
    boolean pay(BigDecimal amount);
}