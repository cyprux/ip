package bobby.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import bobby.exception.BobbyException;

/**
 * Provides utility methods for parsing and formatting dates used
 * throughout the Bobby application.
 */
public class DateTimeUtil {

    /**
     * Formatter for parsing input dates provided by the user.
     * Expected format: yyyy-MM-dd
     */
    private static final DateTimeFormatter INPUT_DATE =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Formatter for displaying dates to the user.
     * Display format: MMM dd yyyy (e.g., Feb 02 2026)
     */
    private static final DateTimeFormatter DISPLAY_DATE =
            DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Parses a raw date string into a LocalDate using the expected input format.
     *
     * @param raw The raw date string entered by the user.
     * @return The parsed LocalDate.
     * @throws BobbyException If the date format is invalid.
     */
    public static LocalDate parseDate(String raw) throws BobbyException {
        try {
            return LocalDate.parse(raw.trim(), INPUT_DATE);
        } catch (Exception e) {
            throw new BobbyException("Invalid date! Use yyyy-mm-dd format.");
        }
    }

    /**
     * Formats a LocalDate into a user-friendly display string.
     *
     * @param date The LocalDate to format.
     * @return The formatted date string.
     */
    public static String formatDate(LocalDate date) {
        return date.format(DISPLAY_DATE);
    }
}
