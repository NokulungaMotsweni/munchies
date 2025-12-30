package munchies.service.payment;

import munchies.model.Order;
import munchies.service.discount.DiscountStrategy;
import java.math.BigDecimal;

/**
 * Context Class: Communicates with strategies via the interface.
 */
public class CheckoutService {

    // The service doesn't care which strategy it uses
    public void processCheckout(Order order, DiscountStrategy discount, PaymentStrategy payment) {
        // 1. Calculate Subtotal
        BigDecimal subtotal = order.calculateSubtotal();

        // 2. Apply Discount
        BigDecimal finalTotal = discount.apply(subtotal);

        // 3. Collect specific details based on chosen strategy
        payment.collectPaymentDetails();

        // 4. Process the payment
        boolean success = payment.pay(finalTotal);

        if (success) {
            System.out.println("Payment processed successfully for: " + finalTotal + " CZK");
        } else {
            System.out.println("Payment failed.");
        }
    }
}