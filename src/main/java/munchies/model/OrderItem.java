package munchies.model;

import java.math.BigDecimal;

public class OrderItem {

    private final MenuItem menuItem; // MenuItem being ordered
    private final int quantity; // How many units of the menuItem are ordered

    public OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem; // Assigns the menuItem.
        this.quantity = quantity; // Assigns the quantity
    }

    public MenuItem getMenuItem() {
        // Returns the menu item associated with this order line
        return menuItem;
    }

    public int getQuantity() {
        // Returns how many units of the menu item were ordered
        return quantity;
    }

    public BigDecimal getLineTotal() {
        // Calculates the total price for this order line (price * quantity)
        return menuItem.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

}
