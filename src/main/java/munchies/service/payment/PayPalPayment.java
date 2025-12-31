package munchies.service.payment;

import java.math.BigDecimal;

public class PayPalPayment implements PaymentStrategy {
    private final String email;

    // Email is collected by Dren's CLI logic and passed here
    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public boolean pay(BigDecimal amount) {
        System.out.println("🅿️ Redirecting to PayPal for account: " + email);
        System.out.println("   Processing payment of " + amount + " CZK...");
        return true;
    }
}