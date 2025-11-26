package munchies.service.discount;

import java.math.BigDecimal;

public interface DiscountStrategy {
    BigDecimal apply(BigDecimal subtotal);
    //TODO Ahmed: Implement discount types
}
