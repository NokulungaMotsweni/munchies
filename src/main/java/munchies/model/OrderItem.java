package munchies.model;

import java.math.BigDecimal;

/**
 * Represents a single line item within an Order.
 * Abstracts different types of order items,allowing the Order class to treat all items uniformly.
 */
public interface OrderItem {
    // Returns the total cost of this order item.
    BigDecimal getLineTotal();

    // Prints a formatted representation of the order item.
    void printItem();
}
