package munchies.cli;

import munchies.service.RestaurantRepository;
import munchies.model.Restaurant;
import munchies.model.MenuItem;

import java.util.List;
import java.util.Scanner;

public class MunchiesCLI {

    private final RestaurantRepository restaurantRepository;
    private final Scanner scanner = new Scanner(System.in);

    public MunchiesCLI(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("=== Munchies CLI ===");
            System.out.println("1. List restaurants");
            System.out.println("2. Browse restaurant menu");
            System.out.println("3. Create order (coming soon)");
            System.out.println("4. Exit");
            System.out.print("Select option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    listRestaurants();
                    waitForEnterToReturn();
                }
                case "2" -> {
                    browseMenu();
                    waitForEnterToReturn();
                }
                case "3" -> {
                    // placeholder for later F3/F5 work
                    System.out.println("Order creation will be added later.");
                    waitForEnterToReturn();
                }
                case "4" -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option, please try again.");
            }
        }
    }

    /**
     * F1 – List restaurants.
     */
    public void listRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.getAll();

        if (restaurants.isEmpty()) {
            System.out.println("No restaurants available.");
            return;
        }

        System.out.println("\n=== Available restaurants ===");
        for (int i = 0; i < restaurants.size(); i++) {
            Restaurant r = restaurants.get(i);
            System.out.printf("%d. %s%n", i + 1, r.getName());
        }
    }

    /**
     * F2/F4 – Browse menu for a selected restaurant.
     */
    public void browseMenu() {
        List<Restaurant> restaurants = restaurantRepository.getAll();

        if (restaurants.isEmpty()) {
            System.out.println("No restaurants available.");
            return;
        }

        // Show restaurants
        System.out.println("\nSelect a restaurant to view its menu:");
        for (int i = 0; i < restaurants.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, restaurants.get(i).getName());
        }
        System.out.print("Enter number (or 0 to go back): ");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input.");
            return;
        }

        if (choice == 0) {
            // Go back to main menu
            System.out.println("Returning to main menu.");
            return;
        }
        if (choice < 1 || choice > restaurants.size()) {
            System.out.println("Invalid restaurant selection.");
            return;
        }

        Restaurant selected = restaurants.get(choice - 1);
        List<MenuItem> menuItems = selected.getMenuItems(); // adjust if method name differs

        System.out.println("\n=== MENU: " + selected.getName() + " ===");

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

        System.out.println("\n(Dish selection will be implemented later as part of F3/F5.)");
    }

    /**
     * Small helper to make the "go back home" behaviour explicit.
     */
    private void waitForEnterToReturn() {
        System.out.println("\nPress Enter to return to the main menu...");
        scanner.nextLine();
    }

    /*
     * Future extension:
     * - selecting menu items
     * - creating orders
     * - displaying order summaries
     * - integrating with Ahmed’s payment + discount logic
     */
    public void createOrder() {
        // TODO Dren: Order creation flow (F3/F5) will be implemented after model is ready.
    }
}
