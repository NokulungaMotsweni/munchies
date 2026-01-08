package munchies.service.payment;

import munchies.model.Order;
import munchies.service.discount.DiscountStrategy;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Context Class: Ahmed's implementation of order total and checkout logic.
 * This class performs: subtotal -> apply discount -> final total -> process payment.
 */
public class CheckoutService {

    public void processCheckout(Order order, DiscountStrategy discount, PaymentType paymentType) {

        // Lock in payment type (domain validation happens inside Order)
        order.selectPaymentType(paymentType);

        // Get the subtotal from the Order (Model)
        BigDecimal subtotal = order.calculateSubtotal();
        System.out.println("Subtotal: " + subtotal.setScale(2, RoundingMode.HALF_UP) + " CZK");

        // Apply discount (if applicable)
        BigDecimal finalTotal = discount.apply(subtotal);

        // Safety check: Ensure total is not negative and scale for currency
        if (finalTotal.compareTo(BigDecimal.ZERO) < 0) {
            finalTotal = BigDecimal.ZERO;
        }
        finalTotal = finalTotal.setScale(2, RoundingMode.HALF_UP);

        System.out.println("Final Total (after discount): " + finalTotal + " CZK");

        //  Process the payment strategy with the final total
        // NOTE: No 'collectPaymentDetails' here. The 'payment' object
        // already has the details inside it from the CLI.
        PaymentStrategy paymentStrategy = createPaymentStrategy(paymentType);
        boolean success = paymentStrategy.pay(finalTotal);

        if (!success) {
            System.out.println("❌ Payment failed.");
            return;
        }

        // 5. Mark paid only if NOT cash
        if (paymentType != PaymentType.CASH_ON_DELIVERY) {
            order.markPaid();
        }

        System.out.println("✅ Checkout completed.");
    }

    private PaymentStrategy createPaymentStrategy(PaymentType paymentType) {
        return switch (paymentType) {
            case CREDIT_CARD -> new CreditCardPayment();
            case PAYPAL -> new PayPalPayment();
            case CASH_ON_DELIVERY -> new CashOnDelivery();
        };
    }

}
