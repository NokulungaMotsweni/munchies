package munchies.model;

import java.math.BigDecimal;

/**
 * Represents a menu item offered by a restaurant.
 */
public class MenuItem {

    // MenuItem unique identifier.
    private final String id;

    // Display name shown to the user.
    private final String name;

    // May or may not be added depending on the restaurant.
    private final String description;

    // The cost of the MenuItem.
    private final BigDecimal price;

    // The type of dietary category of the MenuItem.
    private final String type;

    // Shows the availability of the item
    private final boolean available;


    public MenuItem(String id, String name, String description, BigDecimal price, String type,
                    boolean available) {

        // Assigns the menu item ID.
        this.id = id;

        // Assigns the display name of the MenuItem.
        this.name = name;

        // Assign description of the MenuItem (if applicable).
        this.description = description;

        // Assigns the price of the MenuItem
        this.price = price;

        // Assigns Type to the MenuItem.
        this.type = type;

        // Assigns the availability of said MenuItem.
        this.available = available;
    }

    public String getId() {
        // Returns the identifier for this MenuItem.
        return id;
    }

    public String getName() {
        // Returns the display name of the MenuItem.
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

    // Returns the dietary category of the menu item.
    public boolean isAvailable() {
        return available;
    }

    // Returns the dietary category of the menu item.
    public String getType() {
        return type;
    }

}
