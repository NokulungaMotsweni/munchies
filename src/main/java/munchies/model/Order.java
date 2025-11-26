package munchies.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private final String orderId;                   // Unique identifier for the order
    private final List<OrderItem> items = new ArrayList<>(); // Items that have been added to the order
    private OrderStatus status = OrderStatus.NEW; // Current lifecyle state of the order

    public Order(String orderId) {
        this.orderId = orderId;                 // Assigns the order an identifier
    }

    public List<OrderItem> getItems() {
        // Returns a copy of the items to prevent outside classes from changing the internal list
        return new ArrayList<>(items);
    }

    public void addItem(OrderItem item) {
        // Adds an OrderItem to the order
        items.add(item);
    }

    public OrderStatus getStatus() {
        // Returns the current status if the order
        return status;
    }

    public void setStatus(OrderStatus status) {
        // Updates the order status (used by the Observer Pattern later (Dren))
        this.status = status;
    }

    /*
    * Basic subtotal calculation without discounts.
    * Discount strategies will be applied in the service layer (Ahmed)
     */
    public BigDecimal calculateSubtotal() {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (OrderItem item : items) {
            subtotal = subtotal.add(item.getLineTotal());
        }
        return subtotal;
    }
}
