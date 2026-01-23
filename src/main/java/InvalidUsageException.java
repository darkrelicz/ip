public class InvalidUsageException extends RuntimeException {
    public InvalidUsageException(String message) {
        super("  usage: " + message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
