package dawn.exceptions;

/**
 * Represents the general exception that the program may encounter
 */
public class DawnException extends RuntimeException {
    /**
     * Creates an exception with a general error message
     */
    public DawnException() {
        super("General exception message");
    }

    /**
     * Creates an exception with the specified error message
     * @param msg Error message
     */
    public DawnException(String msg) {
        super(msg);
    }
}
