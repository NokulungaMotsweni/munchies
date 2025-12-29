package munchies.service.payment;

import java.math.BigDecimal;

/**
 * Concrete implementations of the PaymentStrategy interface.
 * Implemented by Ahmed (F6 - Strategy Pattern).
 */

// 1. Credit Card Implementation
class CreditCardPayment implements PaymentStrategy {
    private final String cardNumber;
    private final String cvv;
    private final String expiryDate;

    public CreditCardPayment(String cardNumber, String cvv, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean pay(BigDecimal amount) {
        System.out.println("💳 Processing Credit Card payment of " + amount + " CZK...");
        //
        System.out.println("   [Authorization Successful for card ending in " +
                cardNumber.substring(cardNumber.length() - 4) + "]");
        return true;
    }
}

// 2. PayPal Implementation
class PayPalPayment implements PaymentStrategy {
    private final String email;

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

// 3. Cash on Delivery Implementation
class CashOnDelivery implements PaymentStrategy {
    @Override
    public boolean pay(BigDecimal amount) {
        System.out.println("💵 Order confirmed. Total of " + amount +
                " CZK to be paid in cash upon delivery.");
        return true;
    }
}}
