package munchies;

import munchies.cli.MunchiesCLI;
import munchies.config.DemoDataLoader;
import munchies.service.RestaurantRepository;
import munchies.service.InMemoryRestaurantRepository;

public class Main {
    public static void main(String[] args) {
        // Creates repository
        RestaurantRepository restaurantRepository = new InMemoryRestaurantRepository();

        // Loads restaurants and menu items
        DemoDataLoader.loadDemoData(restaurantRepository);

        // Starts the CLI skeleton
        MunchiesCLI cli = new MunchiesCLI(restaurantRepository);
        cli.run();
    }
}
