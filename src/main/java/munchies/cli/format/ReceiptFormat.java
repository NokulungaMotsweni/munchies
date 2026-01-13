package munchies.cli.format;

/**
 * Defines formatting constants used when printing receipts and order summaries in the CLI.
 */
public final class ReceiptFormat {
    private ReceiptFormat() {} // prevent instantiation

    // Width reserved for item numbering prefixes.
    public static final int PREFIX_WIDTH = 3;

    // Width allocated for item names and labels.
    public static final int NAME_WIDTH = 25;

    // Width allocated for price values.
    public static final int PRICE_WIDTH = 8;
}
