package munchies.model;

public enum OrderStatus {
    NEW,                // Order has been created
    PROCESSING,         // Restaurant is preparing the order
    OUT_FOR_DELIVERY,   // Driver has collected the order
    COMPLETED,          // Order has been successfully delivered
    CANCELLED           // Order was cancelled by the user or the restaurant
}
