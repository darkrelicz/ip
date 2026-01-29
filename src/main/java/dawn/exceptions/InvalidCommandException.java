package dawn.exceptions;

public class InvalidCommandException extends DawnException {
    public InvalidCommandException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
