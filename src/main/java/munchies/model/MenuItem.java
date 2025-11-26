package munchies.model;

import java.math.BigDecimal;

public class MenuItem {
    private String id; // MenuItem unique identifier
    private String name; // e.g "Cheese Burger"
    private String description; // May or may not be added depending on the restaurant
    private BigDecimal price; // The cost of the MenuItem
    private boolean available; // Shows the availability of the itm


    public String getId() {
        // Unique identifier for this MenuItem
        return id;
    }

    public String getName() {
        // Display name of the MenuItem
        return name;
    }

    public String getDescription() {
        // Returns the description text, which may be optional depending on the restaurant
        return description;
    }

    public BigDecimal getPrice() {
        // Price if the MenuItem returned as a BigDecimal for currency accuracy
        return price;
    }

    // Setters will be added later if the MenuItem should support updates or configuration changes.
}
