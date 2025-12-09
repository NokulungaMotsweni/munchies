package munchies.cli;

/**
 * Combined error-handling class for the CLI.
 */
public class MunchiesErrors {

    // ---------- Custom Exception ----------

    public static class InputValidationException extends Exception {
        public InputValidationException(String message) {
            super(message);
        }
    }

    // ---------- Helper methods ----------

    public static void invalidNumber() {
        System.out.println("❌ Invalid number. Please enter digits only.");
    }

    public static void outOfRange() {
        System.out.println("❌ Selection is out of range. Try again.");
    }

    public static void invalidOption() {
        System.out.println("❌ Invalid option. Please choose a valid menu item.");
    }

    public static void orderEmpty() {
        System.out.println("❌ Your order is empty. Add items first.");
    }

    public static void error(String message) {
        System.out.println("❌ Error: " + message);
    }
}