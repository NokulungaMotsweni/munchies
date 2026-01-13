package munchies.model;

import munchies.service.observer.OrderStatusObserver;
import munchies.service.payment.PaymentType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static munchies.cli.format.ReceiptFormat.*;

/**
 * Represents a customer order within the system.
 * The Order acts as the central aggregate root, managing ordered items,
 * payment state, and lifecycle transitions.
 * Subject in the Observer Pattern (F7), notifying interested observers
 * when the order status changes.
 */
public class Order {

    // Used to generate simple unique order identifiers.
    private static int NEXT_ID = 1;

    // // Unique identifier for the order.
    private final String orderId;

    // Collection of items added to the order.
    private final List<OrderItem> items = new ArrayList<>();

    // Current lifecycle status of the order.
    private OrderStatus status = OrderStatus.NEW;

    // Selected payment method for the order.
    private PaymentType paymentType;

    // Indicates whether payment has been completed.
    private boolean paid = false;


    // Observer Pattern: Subject holds observers
    private final List<OrderStatusObserver> observers = new ArrayList<>();

    // Creates a new Order with a generated identifier.
    public Order() {
        this.orderId = "ORD-" + NEXT_ID++;
    }

    // Returns the unique order identifier.
    public String getOrderId() {
        return orderId;
    }

    // Returns a defensive copy of the order items.
    public List<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    // Adds an item to the order.
    public void addItem(OrderItem item) {
        items.add(item);
    }

    /**
     * Selects the payment method for this order.
     * Payment type can only be chosen while the order is NEW.
     */
    public void selectPaymentType(PaymentType paymentType) {
        if (paymentType == null) {
            throw new IllegalArgumentException("Payment type cannot be null.");
        }

        if (this.status != OrderStatus.NEW) {
            throw new IllegalStateException(
                    "Payment type can only be selected while the order is NEW."
            );
        }

        if (this.paid) {
            throw new IllegalStateException(
                    "Payment type cannot be changed after payment has been made."
            );
        }

        this.paymentType = paymentType;
    }

    // Returns the selected payment type.
    public PaymentType getPaymentType() {
        return paymentType;
    }

    /**
     * Marks the order as paid.
     * Cash on delivery orders are excluded from upfront payment.
     */
    public void markPaid() {
        if (paymentType == null) {
            throw new IllegalStateException("Cannot mark order as paid: no payment type selected.");
        }

        if (paymentType == PaymentType.CASH_ON_DELIVERY) {
            throw new IllegalStateException("Cash on delivery orders are not paid upfront.");
        }

        if (paid) {
            return; // already paid
        }

        this.paid = true;
    }




    // ----------------------------
    // Observer methods
    // ----------------------------

    // Registers an observer to receive order status updates.
    public void addObserver(OrderStatusObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    // Removes a previously registered observer.
    public void removeObserver(OrderStatusObserver observer) {
        observers.remove(observer);
    }

    // Notifies all registered observers of a status change.
    private void notifyObservers(OrderStatus newStatus) {
        // Iterate over a snapshot to avoid ConcurrentModificationException
        List<OrderStatusObserver> snapshot = new ArrayList<>(observers);

        for (OrderStatusObserver o : snapshot) {
            try {
                o.onStatusChanged(this, newStatus);
            } catch (RuntimeException e) {
                // Ensure one faulty observer does not prevent others from being notified
                // Logging could be added here if needed
            }
        }
    }

    // ----------------------------
    // Status methods
    // ----------------------------

    // Returns the current order status.
    public OrderStatus getStatus() {
        return status;
    }

    // Indicates whether the order has been paid.
    public boolean isPaid() {
        return paid;
    }

    // Updates the order status while enforcing valid lifecycle transitions.
    public void setStatus(OrderStatus newStatus) {
        // Additional validation for processing state.
        if (newStatus == null || newStatus == this.status) {
            return;
        }

        if (!isValidTransition(this.status, newStatus)) {
            throw new IllegalStateException(
                    "Invalid status transition: " + this.status + " -> " + newStatus
            );
        }

        // Generic state transition validation
        if (newStatus == OrderStatus.PROCESSING) {

            if (paymentType == null) {
                throw new IllegalStateException(
                        "Payment type must be selected before processing the order."
                );
            }

            if (paymentType != PaymentType.CASH_ON_DELIVERY && !paid) {
                throw new IllegalStateException(
                        "Order must be paid before processing."
                );
            }
        }

        // Apply state change and notify observers
        this.status = newStatus;
        notifyObservers(newStatus);
    }

    // Validates allowed order status transitions.
    private boolean isValidTransition(OrderStatus from, OrderStatus to) {
        return switch (from) {
            case NEW -> (to == OrderStatus.PROCESSING || to == OrderStatus.CANCELLED);
            case PROCESSING -> (to == OrderStatus.OUT_FOR_DELIVERY || to == OrderStatus.CANCELLED);
            case OUT_FOR_DELIVERY -> (to == OrderStatus.COMPLETED || to == OrderStatus.CANCELLED);
            case COMPLETED, CANCELLED -> false;
        };
    }

    // ----------------------------
    // Order item methods
    // ----------------------------

    // Removes an item from the order by index.
    public void removeItem(int index) {
        if (index < 0 || index >= items.size()) {
            throw new IllegalArgumentException("Invalid item index: " + index);
        }
        items.remove(index);
    }

    // Calculates the subtotal of the order.
    public BigDecimal calculateSubtotal() {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (OrderItem item : items) {
            subtotal = subtotal.add(item.getLineTotal());
        }
        return subtotal;
    }

    // Prints a formatted summary of the order contents.
    public void printOrderSummary() {
        System.out.println("===========================================");
        System.out.println(" Order ID: " + orderId);
        System.out.println(" Order Status: " + status);
        System.out.println("===========================================");

        int index = 1;

        for (OrderItem item : items) {
            System.out.printf("%-" + PREFIX_WIDTH + "s", (index++) + ".");
            item.printItem();
            System.out.println();
        }

        System.out.println("-------------------------------------------");
        System.out.printf(
                "%-" + (PREFIX_WIDTH + NAME_WIDTH) + "s %" + PRICE_WIDTH + ".2f CZK%n",
                " Subtotal:",
                calculateSubtotal().setScale(2, RoundingMode.HALF_UP)
        );
        System.out.println("-------------------------------------------");
    }
}
