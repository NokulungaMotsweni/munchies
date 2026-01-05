package munchies.config;

import munchies.model.MenuItem;
import munchies.model.Restaurant;
import munchies.repository.RestaurantRepository;

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
                "Classic tomato, mozzarella and basil",
                new BigDecimal("150"),
                "Veg",
                true
        );

        MenuItem p2 = new MenuItem(
                "PZ2",
                "Pepperoni Pizza",
                "Tomato sauce, mozzarella and spicy pepperoni",
                new BigDecimal("170"),
                "Non-Veg",
                true
        );

        MenuItem p3 = new MenuItem(
                "PZ3",
                "Four Cheese Pizza",
                "Mozzarella, gorgonzola, parmesan and cheddar",
                new BigDecimal("180"),
                "Veg",
                true
        );

        MenuItem p4 = new MenuItem(
                "PZ4",
                "Veggie Supreme Pizza",
                "Peppers, onions, olives and sweetcorn",
                new BigDecimal("165"),
                "Vegan",
                true
        );

        MenuItem p5 = new MenuItem(
                "PZ5",
                "Meat Feast Pizza",
                "Beef, bacon and pepperoni",
                new BigDecimal("190"),
                "Non-Veg",
                false   // <- unavailable
        );

        MenuItem s1 = new MenuItem(
                "S2",
                "Garlic Bread",
                "Oven-baked bread with garlic butter",
                new BigDecimal("60"),
                "Veg",
                true
        );

        MenuItem d1 = new MenuItem(
                "D2",
                "Tiramisu",
                "Coffee-soaked ladyfingers with mascarpone",
                new BigDecimal("80"),
                "Veg",
                true
        );

        return new Restaurant(
                "R1",
                "Mama's Pizza",
                List.of(p1, p2, p3, p4, p5, s1, d1)
        );
    }

    private static Restaurant createBurgerShack() {
        // Sample menu items for Burger Shack
        MenuItem b1 = new MenuItem(
                "BG1",
                "Beef Burger",
                "Juicy beef patty with cheese",
                new BigDecimal("130"),
                "Non-Veg",
                true
        );

        MenuItem b2 = new MenuItem(
                "BG2",
                "Chicken Burger",
                "Crispy chicken fillet burger",
                new BigDecimal("120"),
                "Non-Veg",
                true
        );

        MenuItem p3 = new MenuItem(
                "PZ3",
                "BBQ Chicken Pizza Slice",
                "Smoky BBQ sauce with grilled chicken",
                new BigDecimal("75"),
                "Non-Veg",
                true
        );

        MenuItem pa1 = new MenuItem(
                "PA1",
                "Alfredo Pasta Bowl",
                "Creamy Alfredo sauce with parmesan",
                new BigDecimal("90"),
                "Veg",
                true
        );

        MenuItem s1 = new MenuItem(
                "S1",
                "Cheesy Fries",
                "Crispy fries with melted cheese",
                new BigDecimal("55"),
                "Veg",
                true
        );

        MenuItem d1 = new MenuItem(
                "D1",
                "Chocolate Lava Cake",
                "Warm chocolate cake with molten center",
                new BigDecimal("60"),
                "Veg",
                false  // <-- unavailable item
        );

        MenuItem dr1 = new MenuItem(
                "DR1",
                "Fresh Lemonade",
                "Homemade lemonade with mint",
                new BigDecimal("30"),
                "Vegan",
                true
        );

        // Returns a demo restaurant with burger menu items
        return new Restaurant(
                "R2",
                "Burger Shack",
                List.of(b1, b2, p3, pa1, s1, d1, dr1)
        );
    }
}
