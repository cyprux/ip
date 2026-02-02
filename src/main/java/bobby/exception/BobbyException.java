package bobby.exception;

/**
 * Represents a custom exception used in the Bobby application
 * to indicate user-facing or application-specific errors.
 * This allows meaningful error messages to be passed up the call stack.
 */
public class BobbyException extends Exception {

    /**
     * Constructs a BobbyException with the specified error message.
     *
     * @param message The detail message describing the error.
     */
    public BobbyException(String message) {
        super(message);
    }
}
