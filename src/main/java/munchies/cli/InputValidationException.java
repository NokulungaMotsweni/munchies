package munchies.cli;

/**
 * Custom exception used when user input cannot be parsed or validated.
 * Required so that invalid input never crashes the CLI.
 */
public class InputValidationException extends Exception {
    public InputValidationException(String message) {
        super(message);
    }
}
