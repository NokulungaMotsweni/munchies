package munchies.service.payment;

import java.math.BigDecimal;

public interface PaymentStrategy {
    boolean pay(BigDecimal amount);
    //TODO Ahmed: Implement logic in concrete classes

}
