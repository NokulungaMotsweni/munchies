package munchies.model;

import java.math.BigDecimal;

public class MenuItem {
    private String id; // MenuItem unique identifier
    private String name; // e.g "Cheese Burger"
    private String description; // May or may not be added depending on the restaurant
    private BigDecimal price; // The cost of the MenuItem
    private boolean available; // Shows the availability of the itm
}
