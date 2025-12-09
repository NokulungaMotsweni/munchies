package munchies.cli;

import munchies.service.RestaurantRepository;
import munchies.model.Restaurant;
import munchies.model.MenuItem;
import munchies.model.Order;
import munchies.model.OrderItem;
import munchies.model.Dish;
import munchies.model.BaseDish;
import munchies.model.toppings.ExtraCheese;
import munchies.model.toppings.ExtraSauce;
import munchies.model.toppings.Bacon;
import munchies.model.toppings.Mushrooms;

import java.util.List;
import java.util.Scanner;

/**
 * Command-line interface for the Munchies application.
 * All user interaction lives here; we only call the domain classes,
 * we never change them.
 */
public class MunchiesCLI {

    private final RestaurantRepository restaurantRepository;
    private final Scanner scanner = new Scanner(System.in);

    // Current order for this CLI session
    // Order(String orderId) – we provide a simple ID "CLI-ORDER"
    private final Order currentOrder = new Order("CLI-ORDER");
    private boolean hasOrderItems = false; // we track if anything was added

    public MunchiesCLI(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Main CLI loop.
     *
     * 1 - Restaurants (browse / build order)
     * 2 - Checkout (print order summary)
     * 0 - Exit
     */
    public void run() {
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("=== Munchies CLI ===");
            System.out.println("1. Restaurants");
            System.out.println("2. Checkout current order");
            System.out.println("0. Exit");
            System.out.print("Select option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> browseRestaurants();
                case "2" -> checkout();
                case "0" -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> ErrorHandler.invalidOption();
            }
        }
    }

    /**
     * Shows all restaurants and lets the user pick one.
     * 0 always takes the user back to the main menu.
     */
    private void browseRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.getAll();

        if (restaurants.isEmpty()) {
            System.out.println("No restaurants available.");
            return;
        }

        boolean inRestaurants = true;
        while (inRestaurants) {
            System.out.println("\n=== Restaurants ===");
            for (int i = 0; i < restaurants.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, restaurants.get(i).getName());
            }
            System.out.println("0. Back to main menu");

            int choice = readIntSafely("Select restaurant: ", 0, restaurants.size());
            if (choice == 0) {
                inRestaurants = false; // back to main menu
            } else {
                Restaurant selected = restaurants.get(choice - 1);
                handleRestaurantMenu(selected);
            }
        }
    }

    /**
     * Menu flow for a single restaurant:
     * - show menu items
     * - let user pick a dish
     * - let user configure toppings
     * - add the dish to the current order
     */
    private void handleRestaurantMenu(Restaurant restaurant) {
        boolean inRestaurantMenu = true;

        while (inRestaurantMenu) {
            List<MenuItem> menuItems = restaurant.getMenuItems();

            System.out.println("\n=== MENU: " + restaurant.getName() + " ===");
            if (menuItems.isEmpty()) {
                System.out.println("This restaurant currently has no menu items.");
                return;
            }

            for (int i = 0; i < menuItems.size(); i++) {
                MenuItem item = menuItems.get(i);
                String description = item.getDescription();
                if (description == null) {
                    description = "";
                } else {
                    description = " — " + description;
                }
                System.out.printf(
                        "%d. %s (%.2f)%s%n",
                        i + 1,
                        item.getName(),
                        item.getPrice(),
                        description
                );
            }
            System.out.println("0. Back to restaurants");

            int choice = readIntSafely("Select dish (or 0 to go back): ", 0, menuItems.size());
            if (choice == 0) {
                // back to restaurant list
                inRestaurantMenu = false;
            } else {
                MenuItem selectedItem = menuItems.get(choice - 1);
                buildDishAndAddToOrder(selectedItem);
                // after a dish is added, we stay in this restaurant so the user can add more
            }
        }
    }

    /**
     * Builds a Dish using the topping decorators and adds it to the order.
     */
    private void buildDishAndAddToOrder(MenuItem baseItem) {
        Dish dish = new BaseDish(baseItem.getName(), baseItem.getPrice());
        System.out.println("\nConfiguring toppings for: " + baseItem.getName());

        boolean addingToppings = true;
        while (addingToppings) {
            System.out.println("\nChoose toppings (0 = done):");
            System.out.println("1. Extra cheese");
            System.out.println("2. Extra sauce");
            System.out.println("3. Bacon");
            System.out.println("4. Mushrooms");
            System.out.println("0. Done");

            int choice = readIntSafely("Select topping: ", 0, 4);
            switch (choice) {
                case 1 -> dish = new ExtraCheese(dish);
                case 2 -> dish = new ExtraSauce(dish);
                case 3 -> dish = new Bacon(dish);
                case 4 -> dish = new Mushrooms(dish);
                case 0 -> addingToppings = false;
                default -> ErrorHandler.invalidOption();
            }
        }

        currentOrder.addItem(new OrderItem(dish));
        hasOrderItems = true;
        System.out.println("✅ Dish added to order.");
    }

    /**
     * Checkout flow: prints order summary using Order.printOrderSummary().
     */
    private void checkout() {
        if (!hasOrderItems) {
            ErrorHandler.orderEmpty();
            return;
        }

        System.out.println();
        currentOrder.printOrderSummary();   // <-- this matches your Order.java
        System.out.println();
    }

    /**
     * Reads an integer safely from the user.
     * Uses ErrorHandler + custom InputValidationException so
     * invalid input never crashes the CLI.
     */
    private int readIntSafely(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String raw = scanner.nextLine();

            try {
                int value = parseIntInRange(raw, min, max);
                return value;
            } catch (InputValidationException e) {
                // message already printed by ErrorHandler; loop again
            }
        }
    }

    private int parseIntInRange(String raw, int min, int max)
            throws InputValidationException {
        int value;
        try {
            value = Integer.parseInt(raw.trim());
        } catch (NumberFormatException e) {
            ErrorHandler.invalidNumber(raw);
            throw new InputValidationException("Not a number: " + raw);
        }

        if (value < min || value > max) {
            ErrorHandler.outOfRange(min, max);
            throw new InputValidationException("Out of range: " + value);
        }

        return value;
    }

    /*
     * Kept only for compatibility with earlier design; not used directly.
     */
    public void createOrder() {
        // Not used directly now.
    }
}
