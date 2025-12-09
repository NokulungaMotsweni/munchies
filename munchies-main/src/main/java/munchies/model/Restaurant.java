package munchies.model;

import java.util.List;

public class Restaurant {
    private final String id; // Unique identifier for the restaurant name
    private final String name; // Display name for the restaurant
    private final List<MenuItem> menuItems; // List of menu items offered by the restaurant

    public Restaurant(String id, String name, List<MenuItem> menuItems) {
        this.id = id; // Assigns the restaurant unique ID
        this.name = name; // Assigns the restaurant's name
        this.menuItems = menuItems; // Assigns the list of menu items
    }

    public String getId() {
        // Returns the restaurant's unique identifier
        return id;
    }

    public String getName() {
        // Returns the display name of the restaurant
        return name;
    }

    public List<MenuItem> getMenuItems() {
        // Returns the list of menu items available at this restaurant
        return menuItems;
    }
}
