package munchies.cli;

import munchies.service.RestaurantRepository;

public class MunchiesCLI {

    private final RestaurantRepository restaurantRepository;

    public MunchiesCLI(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /*
    Entry point for the CLI.
     */

    public void run() {
        System.out.println("=== Munchies CLI ===");
        System.out.println("CLI started (skeleton).");

        // TODO Dren: Implement main menu navigation
        // Example:
        // 1. List restaurants  (F1)
        // 2. Browse restaurant menu (F2, F4)
        // 3. Create order
        // 4. Exit
    }

    /*
    F1: List restaurant.
     */
    public void listRestaurants() {
        // TODO Dren: Use restaurantRepository.getAll() to display restaurants
    }

    /*
    F2 + F4: Browse menu for selected restaurant.
     */
    public void browseMenu() {
        // TODO Dren: Prompt user to select a restaurant.
        // TODO Dren: Display the selected restaurant's menu items (restaurant.getMenuItems()).
    }

    /*
     * Future extension for Dren:
     * - selecting menu items
     * - creating orders
     * - displaying order summaries
     * - integrating with Ahmed’s payment + discount logic
     */
    public void createOrder() {
        // TODO Dren: Order creation flow
    }
}
