package munchies.service.observer;

import munchies.model.Order;
import munchies.model.OrderStatus;

public interface OrderStatusObserver {
    void onStatusChanged(Order order, OrderStatus newStatus);
    // TODO Dren: Implement CLI observer later
}
