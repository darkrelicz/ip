package dawn.exceptions;

/**
 * Represents an exception when commands are not used as per designed
 */
public class InvalidUsageException extends DawnException {
    /**
     * Creates an exception showing the usage guidelines
     * @param message The usage guidelines
     */
    public InvalidUsageException(String message) {
        super("  usage: " + message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
