package munchies.model;

import java.math.BigDecimal;

public interface OrderItem {
    BigDecimal getLineTotal();
    void printItem();
}
