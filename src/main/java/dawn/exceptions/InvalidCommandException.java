package dawn.exceptions;

/**
 * Represents an exception when commands are not used as per designed
 */
public class InvalidCommandException extends DawnException {
    /**
     * Creates an exception showing the usage guidelines
     * @param message The usage guidelines
     */
    public InvalidCommandException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
