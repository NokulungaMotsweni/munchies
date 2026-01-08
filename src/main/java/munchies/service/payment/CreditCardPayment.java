package munchies.service.payment;

import java.math.BigDecimal;

public class CreditCardPayment implements PaymentStrategy {

    @Override
    public boolean pay(BigDecimal amount) {
        System.out.println("💳 Processing Credit Card payment of " + amount + " CZK...");
        return true;
    }
}