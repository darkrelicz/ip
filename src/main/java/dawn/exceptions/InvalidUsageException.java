package dawn.exceptions;

public class InvalidUsageException extends DawnException {
    public InvalidUsageException(String message) {
        super("  usage: " + message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
