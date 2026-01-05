package munchies.model;

import java.math.BigDecimal;

public interface OrderItem {
    String getName();
    BigDecimal getLineTotal();
    void printItem();
}
