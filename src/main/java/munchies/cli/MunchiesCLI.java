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
 * Command-line interface (CLI) for the Munchies application.*
 * All user interaction lives here; we only call the domain / service classes,
 * we never change them.
 */
public class MunchiesCLI {

    private final RestaurantRepository restaurantRepository;
    private final Scanner scanner = new Scanner(System.in);

    // One order per CLI session
    private final Order currentOrder = new Order("CLI-ORDER");

    public MunchiesCLI(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Main loop.*
     * 1 - Restaurants (browse / build order)
     * 2 - View current order
     * 0 - Exit
     */
    public void run() {
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("=== Munchies CLI ===");
            System.out.println("1. Restaurants (browse / add dish)");
            System.out.println("2. View current order");
            System.out.println("0. Exit");

            int choice = readInt("Select option: ", 0, 2);

            switch (choice) {
                case 1 -> browseRestaurants();
                case 2 -> viewOrder();
                case 0 -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> MunchiesErrors.invalidOption();
            }
        }
    }

    // ---------------------------------------------------------------------
    //  MAIN FLOWS
    // ---------------------------------------------------------------------

    private void browseRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.getAll();

        if (restaurants.isEmpty()) {
            System.out.println("No restaurants available.");
            return;
        }

        System.out.println();
        System.out.println("=== Restaurants ===");
        for (int i = 0; i < restaurants.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, restaurants.get(i).getName());
        }
        System.out.println("0. Back to main menu");

        int choice = readInt("Select restaurant: ", 0, restaurants.size());

        if (choice == 0) return;

        Restaurant selected = restaurants.get(choice - 1);
        browseMenu(selected);
    }

    private void browseMenu(Restaurant restaurant) {
        List<MenuItem> menuItems = restaurant.getMenuItems();

        System.out.println();
        System.out.println("=== MENU: " + restaurant.getName() + " ===");

        if (menuItems.isEmpty()) {
            System.out.println("This restaurant has no menu items.");
            return;
        }

        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem item = menuItems.get(i);
            String desc = (item.getDescription() == null ? "" : " — " + item.getDescription());
            System.out.printf("%d. %s (%.2f)%s%n", i + 1, item.getName(), item.getPrice(), desc);
        }
        System.out.println("0. Back to restaurants");

        int choice = readInt("Select dish: ", 0, menuItems.size());

        if (choice == 0) return;

        addDishToOrder(menuItems.get(choice - 1));
    }

    private void viewOrder() {
        if (currentOrder.getItems().isEmpty()) {
            MunchiesErrors.orderEmpty();
            return;
        }

        System.out.println();
        currentOrder.printOrderSummary();
    }

    // ---------------------------------------------------------------------
    //  DISH + TOPPINGS
    // ---------------------------------------------------------------------

    private void addDishToOrder(MenuItem menuItem) {
        Dish dish = new BaseDish(menuItem.getName(), menuItem.getPrice());

        boolean choosingToppings = true;

        while (choosingToppings) {
            System.out.println();
            System.out.println("Selected base: " + menuItem.getName() + " (" + menuItem.getPrice() + ")");
            System.out.println("Choose toppings:");
            System.out.println("1. Extra cheese");
            System.out.println("2. Extra sauce");
            System.out.println("3. Bacon");
            System.out.println("4. Mushrooms");
            System.out.println("0. Done");

            int choice = readInt("Select topping: ", 0, 4);

            switch (choice) {
                case 1 -> dish = new ExtraCheese(dish);
                case 2 -> dish = new ExtraSauce(dish);
                case 3 -> dish = new Bacon(dish);
                case 4 -> dish = new Mushrooms(dish);
                case 0 -> choosingToppings = false;
                default -> MunchiesErrors.invalidOption();
            }
        }

        OrderItem orderItem = new OrderItem(dish);
        currentOrder.addItem(orderItem);

        System.out.println();
        System.out.println("Added to order: " + dish.getName());
    }

    // ---------------------------------------------------------------------
    //  INPUT HELPERS
    // ---------------------------------------------------------------------

    private int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();

            int value;
            try {
                value = Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                MunchiesErrors.invalidNumber();
                continue;
            }

            if (value < min || value > max) {
                MunchiesErrors.outOfRange();
                continue;
            }

            return value;
        }
    }
}
