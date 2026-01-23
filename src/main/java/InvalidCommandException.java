public class InvalidCommandException extends RuntimeException{
    public InvalidCommandException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
