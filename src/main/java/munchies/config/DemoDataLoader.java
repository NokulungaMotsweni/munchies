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

        restaurantRepository.add(createSushiZen());
        restaurantRepository.add(createTacoFiesta());
        restaurantRepository.add(createGreenGarden());
        restaurantRepository.add(createCurryCorner());
        restaurantRepository.add(createDessertDen());
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

    private static Restaurant createSushiZen() {
        MenuItem s1 = new MenuItem(
                "SZ1",
                "Salmon Nigiri",
                "Fresh salmon over seasoned rice",
                new BigDecimal("140"),
                "Non-Veg",
                true
        );

        MenuItem s2 = new MenuItem(
                "SZ2",
                "Avocado Maki",
                "Classic avocado roll",
                new BigDecimal("90"),
                "Vegan",
                true
        );

        MenuItem s3 = new MenuItem(
                "SZ3",
                "Chicken Katsu Roll",
                "Crispy chicken with sushi rice",
                new BigDecimal("120"),
                "Non-Veg",
                true
        );

        MenuItem s4 = new MenuItem(
                "SZ4",
                "Miso Soup",
                "Traditional Japanese soup",
                new BigDecimal("45"),
                "Veg",
                true
        );

        MenuItem s5 = new MenuItem(
                "SZ5",
                "Tempura Prawns",
                "Lightly battered prawns",
                new BigDecimal("160"),
                "Non-Veg",
                false
        );

        return new Restaurant(
                "R3",
                "Sushi Zen",
                List.of(s1, s2, s3, s4, s5)
        );
    }

    private static Restaurant createTacoFiesta() {
        MenuItem t1 = new MenuItem(
                "TF1",
                "Beef Taco",
                "Spiced beef with salsa",
                new BigDecimal("85"),
                "Non-Veg",
                true
        );

        MenuItem t2 = new MenuItem(
                "TF2",
                "Chicken Burrito",
                "Grilled chicken with rice and beans",
                new BigDecimal("110"),
                "Non-Veg",
                true
        );

        MenuItem t3 = new MenuItem(
                "TF3",
                "Veggie Quesadilla",
                "Cheese and grilled vegetables",
                new BigDecimal("95"),
                "Veg",
                true
        );

        MenuItem t4 = new MenuItem(
                "TF4",
                "Nachos Supreme",
                "Tortilla chips with toppings",
                new BigDecimal("100"),
                "Veg",
                true
        );

        return new Restaurant(
                "R4",
                "Taco Fiesta",
                List.of(t1, t2, t3, t4)
        );
    }

    private static Restaurant createGreenGarden() {
        MenuItem g1 = new MenuItem(
                "GG1",
                "Falafel Bowl",
                "Falafel with hummus and salad",
                new BigDecimal("105"),
                "Vegan",
                true
        );

        MenuItem g2 = new MenuItem(
                "GG2",
                "Quinoa Salad",
                "Quinoa with roasted vegetables",
                new BigDecimal("95"),
                "Vegan",
                true
        );

        MenuItem g3 = new MenuItem(
                "GG3",
                "Grilled Halloumi Wrap",
                "Halloumi with fresh greens",
                new BigDecimal("110"),
                "Veg",
                true
        );

        MenuItem g4 = new MenuItem(
                "GG4",
                "Smoothie Bowl",
                "Fruit smoothie with seeds",
                new BigDecimal("80"),
                "Vegan",
                false
        );

        return new Restaurant(
                "R5",
                "Green Garden",
                List.of(g1, g2, g3, g4)
        );
    }

    private static Restaurant createCurryCorner() {
        MenuItem c1 = new MenuItem(
                "CC1",
                "Butter Chicken",
                "Creamy tomato-based curry",
                new BigDecimal("150"),
                "Non-Veg",
                true
        );

        MenuItem c2 = new MenuItem(
                "CC2",
                "Chickpea Curry",
                "Spiced chickpeas in curry sauce",
                new BigDecimal("120"),
                "Vegan",
                true
        );

        MenuItem c3 = new MenuItem(
                "CC3",
                "Lamb Rogan Josh",
                "Slow-cooked lamb curry",
                new BigDecimal("170"),
                "Non-Veg",
                true
        );

        MenuItem c4 = new MenuItem(
                "CC4",
                "Garlic Naan",
                "Freshly baked naan bread",
                new BigDecimal("35"),
                "Veg",
                true
        );

        return new Restaurant(
                "R6",
                "Curry Corner",
                List.of(c1, c2, c3, c4)
        );
    }

    private static Restaurant createDessertDen() {
        MenuItem d1 = new MenuItem(
                "DD1",
                "Cheesecake",
                "Creamy vanilla cheesecake",
                new BigDecimal("85"),
                "Veg",
                true
        );

        MenuItem d2 = new MenuItem(
                "DD2",
                "Ice Cream Sundae",
                "Vanilla ice cream with toppings",
                new BigDecimal("70"),
                "Veg",
                true
        );

        MenuItem d3 = new MenuItem(
                "DD3",
                "Brownie",
                "Chocolate brownie",
                new BigDecimal("65"),
                "Veg",
                true
        );

        MenuItem d4 = new MenuItem(
                "DD4",
                "Fruit Salad",
                "Seasonal fresh fruits",
                new BigDecimal("55"),
                "Vegan",
                false
        );

        return new Restaurant(
                "R7",
                "Dessert Den",
                List.of(d1, d2, d3, d4)
        );
    }

}
