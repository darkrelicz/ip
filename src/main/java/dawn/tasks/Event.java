package dawn.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with start and end dates
 */
public class Event extends Task {
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Creates a Task object with the stated description, start date, and end date
     * @param description The description of the task
     * @param startDate The start date of the task
     * @param endDate The end date of the task
     */
    public Event(String description, String startDate, String endDate){
        super(description);
        this.startDate = stringToDateTime(startDate);
        this.endDate = stringToDateTime(endDate);
    }

    /**
     * Creates a Task object with the stated description, start date and end date, and completion status
     * @param isDone The completion status of the task
     * @param description The description of the task
     * @param startDate The start date of the task
     * @param endDate The end date of the task
     */
    public Event(boolean isDone, String description, String startDate, String endDate){
        super(isDone, description);
        this.startDate = stringToDateTime(startDate);
        this.endDate = stringToDateTime(endDate);
    }

    /**
     * Converts a String value to LocalDateTime object
     * @param strDate The datetime value in String
     * @return The datetime in as a LocalDateTime object
     * @throws DateTimeParseException If datetime is not in the correct format
     */
    private LocalDateTime stringToDateTime(String strDate) {
        assert !strDate.toString().isEmpty() : "date time should not be empty";
        LocalDateTime dateTime = LocalDateTime.parse(strDate, FORMATTER);
        return dateTime;
    }

    @Override
    public String toCsv() {
        return "E," + super.toCsv() + String.format(",%s,%s", startDate.format(FORMATTER), endDate.format(FORMATTER));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format("(from: %s to: %s)", startDate.format(FORMATTER), endDate.format(FORMATTER));
    }
}

