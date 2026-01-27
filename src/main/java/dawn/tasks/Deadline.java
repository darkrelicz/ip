package dawn.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime deadline;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public Deadline(String description, String deadline){
        super(description);
        this.deadline = stringToDateTime(deadline);
    }

    public Deadline(boolean isDone, String description, String deadline){
        super(isDone, description);
        this.deadline = stringToDateTime(deadline);
    }

    private LocalDateTime stringToDateTime(String strDate) throws DateTimeParseException {
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

