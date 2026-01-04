package munchies.service;

import munchies.model.Restaurant;
import java.util.List;

public interface RestaurantRepository {
    List<Restaurant> getAll();

    Restaurant findByName(String name);

    void add(Restaurant restaurant);
}
