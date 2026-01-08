package munchies.service.observer;

import munchies.model.Order;
import munchies.model.OrderStatus;

public class CliOrderStatusObserver implements OrderStatusObserver {
    @Override
    public void onStatusChanged(Order order, OrderStatus newStatus) {
        System.out.println("[NOTIFICATION] Order " + order.getOrderId()
                + " status is now: " + newStatus);
    }
}
