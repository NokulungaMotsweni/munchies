package munchies.repository;

import munchies.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRestaurantRepository implements RestaurantRepository {
    private final List<Restaurant> restaurants = new ArrayList<>();
    // In memory storage for all restaurants on Munchies

    @Override
    public List<Restaurant> getAll() {
        // Returns a copy of all restaurants to avoid exposing the internal list
        return new ArrayList<>(restaurants);
    }

    @Override
    public Restaurant findByName(String name) {
        // Searches for a restaurant by name (case-insensitive)
        for (Restaurant r : restaurants) {
            if (r.getName().equalsIgnoreCase(name)) {
                return r;
            }
        }
        return null; // Returns null when no matching restaurant is found
    }

    @Override
    public void add(Restaurant restaurant) {
        // Adds a restaurant to the in-memory list
        restaurants.add(restaurant);
    }
}
