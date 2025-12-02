package munchies.cli;

import munchies.service.RestaurantRepository;
import munchies.model.Restaurant;
import munchies.model.MenuItem;

import java.util.List;
import java.util.Scanner;
import java.math.BigDecimal

public class MunchiesCLI {

    private final RestaurantRepository restaurantRepository;
    private final Scanner scanner = new Scanner(System.in);

    public MunchiesCLI(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Main loop:
     * 1 - Restaurants
     * 0 - Exit
     */
    public void run() {
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("=== Munchies CLI ===");
            System.out.println("1. Restaurants");
            System.out.println("0. Exit");
            System.out.print("Select option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> browseRestaurants();
                case "0" -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option, please try again.");
            }
        }
    }

    /**
     * Shows all restaurants and lets the user pick one by number.
     * 0 always goes back to the main menu.
     */
    private void browseRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.getAll();

        if (restaurants.isEmpty()) {
            System.out.println("No restaurants available.");
            return;
        }

        boolean inRestaurantMenu = true;
        while (inRestaurantMenu) {
            System.out.println("\n=== Restaurants ===");
            for (int i = 0; i < restaurants.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, restaurants.get(i).getName());
            }
            System.out.println("0. Back to main menu");
            System.out.print("Select restaurant: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input.");
                continue;
            }

            if (choice == 0) {
                // back to main menu
                inRestaurantMenu = false;
            } else if (choice < 0 || choice > restaurants.size()) {
                System.out.println("Invalid restaurant selection.");
            } else {
                Restaurant selected = restaurants.get(choice - 1);
                showMenuFor(selected);
                // after showing the menu, loop again to let user pick another restaurant or 0
            }
        }
    }

    /**
     * Shows menu items for a given restaurant.
     * 0 goes back to the restaurant list.
     */
    private void showMenuFor(Restaurant restaurant) {
        List<MenuItem> menuItems = restaurant.getMenuItems(); // adjust name if needed

        System.out.println("\n=== MENU: " + restaurant.getName() + " ===");

        if (menuItems.isEmpty()) {
            System.out.println("This restaurant currently has no menu items.");
            return;
        }

        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem item = menuItems.get(i);
            String description = item.getDescription();
            if (description == null || description.isEmpty())  {
                description = "";
            } else {
                description = " — " + description;
            }

            BigDecimal price = item.getPrice();
            String priceText = price != null ? price.toPlainString() + " CZK" : "N/A";

            System.out.printf(
                    "%d. %s (%.2f)%s%n",
                    i + 1,
                    item.getName(),
                    item.getPrice(),
                    description
            );
        }

        System.out.println("0. Back to restaurants");
        System.out.print("Select 0 to go back: ");
        // we just read and ignore the value; any input returns to restaurant list
        scanner.nextLine();
    }

    // placeholder for future F3/F5 order creation etc.
    public void createOrder() {
        // TODO: will be implemented later
    }
}
