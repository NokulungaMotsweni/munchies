package munchies.config;

import munchies.model.MenuItem;
import munchies.model.Restaurant;
import munchies.service.RestaurantRepository;

import java.math.BigDecimal;
import java.util.List;

public class DemoDataLoader {

    private DemoDataLoader() {
        // Prevent instantiation — this is a utility class
    }

    public static void loadDemoData(RestaurantRepository restaurantRepository) {
        // Loads predefined restaurants into the repository
        restaurantRepository.add(createMamasPizza());
        restaurantRepository.add(createBurgerShack());
    }

    private static Restaurant createMamasPizza() {
        // Sample menu items for Mama's Pizza
        MenuItem p1 = new MenuItem(
                "PZ1",
                "Margherita Pizza",
                "Classic pizza with tomato and cheese",
                new BigDecimal("150"),
                true
        );

        MenuItem p2 = new MenuItem(
                "PZ2",
                "Pepperoni Pizza",
                "Pepperoni slices and cheese",
                new BigDecimal("180"),
                true
        );

        // Returns a demo restaurant with the pizza menu items
        return new Restaurant(
                "R1",
                "Mama's Pizza",
                List.of(p1, p2)
        );
    }

    private static Restaurant createBurgerShack() {
        // Sample menu items for Burger Shack
        MenuItem b1 = new MenuItem(
                "BG1",
                "Beef Burger",
                "Juicy beef patty with cheese",
                new BigDecimal("130"),
                true
        );

        MenuItem b2 = new MenuItem(
                "BG2",
                "Chicken Burger",
              "Crispy chicken fillet burger",
                new BigDecimal("120"),
                true
        );

        // Returns a demo restaurant with burger menu items
        return new Restaurant(
                "R2",
                "Burger Shack",
                List.of(b1, b2)
        );
    }
}
