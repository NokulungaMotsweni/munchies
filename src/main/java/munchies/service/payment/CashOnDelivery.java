package munchies.service.payment;

import java.math.BigDecimal;

public class CashOnDelivery implements PaymentStrategy {
    @Override
    public boolean pay(BigDecimal amount) {
        System.out.println("💵 Order confirmed. Total of " + amount +
                " CZK to be paid in cash upon delivery.");
        return true;
    }
}