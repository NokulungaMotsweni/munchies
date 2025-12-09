package munchies.cli;

/**
 * Centralised error messages for invalid user input.
 * Use these methods whenever the user enters something incorrect.
 */
public class ErrorHandler {

    public static void invalidNumber(String rawInput) {
        System.out.println("❌ Invalid number: '" + rawInput + "'. Please enter digits only.");
    }

    public static void outOfRange(int min, int max) {
        System.out.println("❌ Number out of range. Please choose between " + min + " and " + max + ".");
    }

    public static void invalidOption() {
        System.out.println("❌ Invalid option. Please choose one of the listed menu options.");
    }

    public static void orderEmpty() {
        System.out.println("❌ Your order is empty. Add at least one dish before checkout.");
    }
}
