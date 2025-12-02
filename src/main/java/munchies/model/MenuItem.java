package munchies.model;

import java.math.BigDecimal;

public class MenuItem {
    private final String id; // MenuItem unique identifier
    private final String name; // e.g "Cheese Burger"
    private final String description; // May or may not be added depending on the restaurant
    private final BigDecimal price; // The cost of the MenuItem
    private final String type; // The type of dietary category of the MenuItem
    private final boolean available; // Shows the availability of the itm

    public MenuItem(String id, String name, String description, BigDecimal price, boolean available) {
        this.id = id; // Assigns the menu item ID
        this.name = name; // Assigns the display name of the MenuItem
        this.description = description; // Assign description of the MenuItem (if applicable)
        this.price = price; // Assigns the price of the MenuItem
        this.available = available; // Assigns the availability of said MenuItem
    }

    public String getId() {
        // Returns the identifier for this MenuItem
        return id;
    }

    public String getName() {
        // Returns the display name of the MenuItem
        return name;
    }

    public String getDescription() {
        // Returns the description text, which may be optional depending on the restaurant
        return description;
    }

    public BigDecimal getPrice() {
        // Returns the price of the MenuItem returned as a BigDecimal for currency accuracy
        return price;
    }

    // Setters will be added later if the MenuItem should support updates or configuration changes.
}
