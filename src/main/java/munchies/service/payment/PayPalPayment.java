package munchies.service.payment;

import java.math.BigDecimal;

public class PayPalPayment implements PaymentStrategy {

    @Override
    public boolean pay(BigDecimal amount) {
        System.out.println("   Processing payment of " + amount + " CZK...");
        return true;
    }
}