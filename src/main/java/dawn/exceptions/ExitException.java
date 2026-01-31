package dawn.exceptions;

/**
 * Represents the exception when exiting the program
 */
public class ExitException extends DawnException {
    /**
     * Creates an exception with the "exit" message
     */
    public ExitException() {
        super("exit");
    }
}
