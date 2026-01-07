package munchies.model;

import munchies.service.observer.OrderStatusObserver;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static munchies.cli.format.ReceiptFormat.*;

public class Order {

    private static int NEXT_ID = 1;

    private final String orderId;
    private final List<OrderItem> items = new ArrayList<>();
    private OrderStatus status = OrderStatus.NEW;

    // Observer Pattern: Subject holds observers
    private final List<OrderStatusObserver> observers = new ArrayList<>();

    public Order() {
        this.orderId = "ORD-" + NEXT_ID++;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    // ----------------------------
    // Observer methods
    // ----------------------------

    public void addObserver(OrderStatusObserver observer) {
        if (observer != null) observers.add(observer);
    }

    public void removeObserver(OrderStatusObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(OrderStatus newStatus) {
        for (OrderStatusObserver o : observers) {
            o.onStatusChanged(this, newStatus);
        }
    }

    // ----------------------------
    // Status methods
    // ----------------------------

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus newStatus) {
        if (newStatus == null || newStatus == this.status) return;

        if (!isValidTransition(this.status, newStatus)) {
            throw new IllegalStateException(
                    "Invalid status transition: " + this.status + " -> " + newStatus
            );
        }

        this.status = newStatus;
        notifyObservers(newStatus);
    }

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

    public void removeItem(int index) {
        if (index < 0 || index >= items.size()) {
            throw new IllegalArgumentException("Invalid item index: " + index);
        }
        items.remove(index);
    }

    public BigDecimal calculateSubtotal() {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (OrderItem item : items) {
            subtotal = subtotal.add(item.getLineTotal());
        }
        return subtotal;
    }

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
