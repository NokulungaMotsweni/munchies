package munchies.repository;

import munchies.model.Restaurant;
import java.util.List;

/**
 * Repository abstraction for accessing Restaurant data.
 * Defines the contract for retrieving and storing Restaurant objects,
 * decoupling the application logic from the underlying data storage mechanism.
 */
public interface RestaurantRepository {

    // Returns all available restaurants.
    List<Restaurant> getAll();

    // Finds a restaurant by its name.
    Restaurant findByName(String name);

    // Adds a restaurant to the repository.
    void add(Restaurant restaurant);
}
