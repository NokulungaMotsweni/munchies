package munchies.cli;

import munchies.service.RestaurantRepository;
import munchies.model.Restaurant;
import munchies.model.MenuItem;
import munchies.model.Order;
import munchies.model.OrderItem;
import munchies.model.Dish;
import munchies.model.BaseDish;
import munchies.model.ToppingInfo;
import munchies.model.toppings.ExtraCheese;
import munchies.model.toppings.ExtraSauce;
import munchies.model.toppings.Bacon;
import munchies.model.toppings.Mushrooms;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;

/**
 * Command-line interface (CLI) for the Munchies application.
 * All user interaction lives here; we only call the domain / service classes.
 */
public class MunchiesCLI {

    private final RestaurantRepository restaurantRepository;
    private final Scanner scanner = new Scanner(System.in);

    // One order per CLI session
    private final Order currentOrder = new Order();

    public MunchiesCLI(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Main loop.
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
            String desc = (item.getDescription() == null || item.getDescription().isBlank())
                    ? ""
                    : " — " + item.getDescription();

            System.out.printf(
                    "%d. %s (%s CZK)%s%n",
                    i + 1,
                    item.getName(),
                    money(item.getPrice()),
                    desc
            );
        }
        System.out.println("0. Back to restaurants");

        int choice = readInt("Select dish: ", 0, menuItems.size());
        if (choice == 0) return;

        addDishToOrder(menuItems.get(choice - 1));
    }

    /**
     * View order:
     * - show items with numbers
     * - option to remove an item
     * - call order.removeItem(index)
     * - catch IllegalArgumentException
     * - reprint updated order
     */
    private void viewOrder() {
        if (currentOrder.getItems().isEmpty()) {
            MunchiesErrors.orderEmpty();
            return;
        }

        boolean viewing = true;

        while (viewing) {
            System.out.println();
            System.out.println("=== Current Order ===");
            printNumberedOrderItems();

            System.out.println();
            System.out.println("1. Remove item");
            System.out.println("2. Print full summary");
            System.out.println("0. Back");

            int choice = readInt("Select option: ", 0, 2);

            switch (choice) {
                case 1 -> removeItemFlow();
                case 2 -> {
                    System.out.println();
                    currentOrder.printOrderSummary();
                }
                case 0 -> viewing = false;
                default -> MunchiesErrors.invalidOption();
            }

            if (currentOrder.getItems().isEmpty()) {
                System.out.println();
                MunchiesErrors.orderEmpty();
                viewing = false;
            }
        }
    }

    private void removeItemFlow() {
        int count = currentOrder.getItems().size();
        if (count == 0) {
            MunchiesErrors.orderEmpty();
            return;
        }

        int itemNumber = readInt("Enter item number to remove (0 to cancel): ", 0, count);
        if (itemNumber == 0) return;

        try {
            currentOrder.removeItem(itemNumber - 1);
            System.out.println("✅ Item removed.");
        } catch (IllegalArgumentException ex) {
            System.out.println("❌ Could not remove item. Please choose a valid item number.");
        }

        // Reprint updated order
        if (!currentOrder.getItems().isEmpty()) {
            System.out.println();
            printNumberedOrderItems();
        }
    }

    private void printNumberedOrderItems() {
        List<OrderItem> items = currentOrder.getItems(); // copy is fine for printing
        BigDecimal subtotal = BigDecimal.ZERO;

        for (int i = 0; i < items.size(); i++) {
            OrderItem item = items.get(i);

            System.out.printf("%d. %s (%s CZK)%n",
                    i + 1,
                    item.getName(),
                    money(item.getLineTotal())
            );

            for (ToppingInfo topping : item.getToppings()) {
                System.out.println("    • " + topping.name() + " (+" + money(topping.price()) + " CZK)");
            }

            subtotal = subtotal.add(item.getLineTotal());
        }

        System.out.println("---------------------------------");
        System.out.println("Subtotal: " + money(subtotal) + " CZK");
        System.out.println("---------------------------------");
    }

    // ---------------------------------------------------------------------
    //  DISH + TOPPINGS
    // ---------------------------------------------------------------------

    private void addDishToOrder(MenuItem menuItem) {
        Dish dish = new BaseDish(menuItem.getName(), menuItem.getPrice());

        // Read topping prices directly from the topping classes (always accurate)
        BigDecimal cheesePrice = new ExtraCheese(new BaseDish("tmp", BigDecimal.ZERO)).getPrice();
        BigDecimal saucePrice  = new ExtraSauce(new BaseDish("tmp", BigDecimal.ZERO)).getPrice();
        BigDecimal baconPrice  = new Bacon(new BaseDish("tmp", BigDecimal.ZERO)).getPrice();
        BigDecimal mushPrice   = new Mushrooms(new BaseDish("tmp", BigDecimal.ZERO)).getPrice();

        boolean choosingToppings = true;

        while (choosingToppings) {
            System.out.println();
            System.out.println("Selected base: " + dish.getName());

            // ✅ Show current subtotal (base + toppings) - always correct
            System.out.println("Current subtotal: " +
                    dish.getPrice().setScale(2, RoundingMode.HALF_UP) +
                    " CZK");

            // ✅ Show toppings added so far
            if (!dish.getToppings().isEmpty()) {
                System.out.println("Toppings:");
                dish.getToppings().forEach(t ->
                        System.out.println("  • " + t.name() + " (+" +
                                t.price().setScale(2, RoundingMode.HALF_UP) + " CZK)")
                );
            }

            System.out.println();
            System.out.println("Choose toppings:");
            System.out.println("1. Extra cheese (+" + cheesePrice.setScale(2, RoundingMode.HALF_UP) + " CZK)");
            System.out.println("2. Extra sauce  (+" + saucePrice.setScale(2, RoundingMode.HALF_UP) + " CZK)");
            System.out.println("3. Bacon        (+" + baconPrice.setScale(2, RoundingMode.HALF_UP) + " CZK)");
            System.out.println("4. Mushrooms    (+" + mushPrice.setScale(2, RoundingMode.HALF_UP) + " CZK)");
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

        currentOrder.addItem(new OrderItem(dish));

        System.out.println();
        System.out.println("✅ Added to order:");
        System.out.println(dish.getName() + " — " +
                dish.getPrice().setScale(2, RoundingMode.HALF_UP) +
                " CZK");
    }


    // ---------------------------------------------------------------------
    //  INPUT + FORMAT HELPERS
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

    private String money(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }
}
