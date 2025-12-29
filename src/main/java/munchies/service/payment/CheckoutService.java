package munchies.service.payment;

import munchies.model.Order;
import munchies.service.discount.DiscountStrategy;
import java.math.BigDecimal;

/**
 * Ahmed: Implement order total and checkout logic.
 * This class performs: subtotal -> apply discount -> final total -> apply payment.
 */
public class CheckoutService {

    public void processCheckout(Order order, DiscountStrategy discount, PaymentStrategy payment) {
        // 1. Get the subtotal from the Order (Model)
        BigDecimal subtotal = order.calculateSubtotal();

        // 2. Apply the chosen Discount Strategy
        BigDecimal finalTotal = discount.apply(subtotal);

        // 3. Process the payment with the final total
        boolean success = payment.pay(finalTotal);

        if (success) {
            System.out.println("Payment processed successfully for: " + finalTotal + " CZK");
        } else {
            System.out.println("Payment failed.");
        }
    }
}