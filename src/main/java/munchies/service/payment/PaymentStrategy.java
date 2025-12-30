package munchies.service.payment;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Strategy Interface: Defines a family of payment algorithms.
 */
public interface PaymentStrategy {
    void collectPaymentDetails(); // New: Handles specific input for the strategy
    boolean pay(BigDecimal amount);
}

// 1. Credit Card Implementation
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cvv;
    private String expiryDate;

    @Override
    public void collectPaymentDetails() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Card Number: ");
        this.cardNumber = sc.nextLine();
        System.out.print("Enter CVV: ");
        this.cvv = sc.nextLine();
        System.out.print("Enter Expiry (MM/YY): ");
        this.expiryDate = sc.nextLine();
    }

    @Override
    public boolean pay(BigDecimal amount) {
        System.out.println("💳 Processing Credit Card payment of " + amount + " CZK...");
        System.out.println("   [Authorization Successful for card ending in " +
                cardNumber.substring(cardNumber.length() - 4) + "]");
        return true;
    }
}

// 2. PayPal Implementation
class PayPalPayment implements PaymentStrategy {
    private String email;

    @Override
    public void collectPaymentDetails() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter PayPal Email: ");
        this.email = sc.nextLine();
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
    public void collectPaymentDetails() {
        System.out.println("No details needed for Cash on Delivery.");
    }

    @Override
    public boolean pay(BigDecimal amount) {
        System.out.println("💵 Order confirmed. Total of " + amount +
                " CZK to be paid in cash upon delivery.");
        return true;
    }
}