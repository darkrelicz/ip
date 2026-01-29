package dawn.exceptions;

public class DawnException extends RuntimeException {
    public DawnException() {
        super("General exception message");
    }

    public DawnException(String msg) {
        super(msg);
    }
}
