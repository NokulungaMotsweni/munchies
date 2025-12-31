package munchies.service.payment;

import java.math.BigDecimal;

public class CreditCardPayment implements PaymentStrategy {
    private final String cardNumber;
    private final String cvv;
    private final String expiryDate;

    // The CLI collects these details and passes them here
    public CreditCardPayment(String cardNumber, String cvv, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean pay(BigDecimal amount) {
        System.out.println("💳 Processing Credit Card payment of " + amount + " CZK...");
        System.out.println("   [Authorization Successful for card ending in " +
                cardNumber.substring(cardNumber.length() - 4) + "]");
        return true;
    }
}