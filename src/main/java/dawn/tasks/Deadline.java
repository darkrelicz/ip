package dawn.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a due date
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Creates a Task object with the stated description and deadline
     * @param description The description of the task
     * @param deadline The due date of the task
     */
    public Deadline(String description, String deadline){
        super(description);
        this.deadline = stringToDateTime(deadline);
    }

    /**
     * Creates a Task object with the stated description, deadline and completion status
     * @param isDone The completion status of the task
     * @param description The description of the task
     * @param deadline The due date of the task
     */
    public Deadline(boolean isDone, String description, String deadline){
        super(isDone, description);
        this.deadline = stringToDateTime(deadline);
    }

    /**
     * Converts a String value to LocalDateTime object
     * @param strDate The datetime value in String
     * @return The datetime in as a LocalDateTime object
     * @throws DateTimeParseException If datetime is not in the correct format
     */
    private LocalDateTime stringToDateTime(String strDate) throws DateTimeParseException {
        assert !strDate.toString().isEmpty() : "date time should not be empty";
        LocalDateTime dateTime = LocalDateTime.parse(strDate, FORMATTER);
        return dateTime;
    }

    @Override
    public String toCsv() {
        return "D," + super.toCsv() + "," + this.deadline.format(FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.deadline.format(FORMATTER));
    }
}

