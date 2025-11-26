package munchies.service;

import munchies.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantRepository {
    private final List<Restaurant> restaurants = new ArrayList<>();
    // In memory storage for all restaurants on Munchies

    public List<Restaurant> getAll() {
        // Returns a copy of all restaurants to avoid exposing the internal list
        return new ArrayList<>(restaurants);
    }

    public Restaurant findByName(String name) {
        // Searches for a restaurant by name (case-insensitive)
        for (Restaurant r : restaurants) {
            if (r.getName().equalsIgnoreCase(name)) {
                return r;
            }
        }
        return null; // Returns null when no matching restaurant is found
    }

    public void add(Restaurant restaurant) {
        // Adds a restaurant to the in-memory list
        restaurants.add(restaurant);
    }
}
