package dawn.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public Event(String description, String startDate, String endDate){
        super(description);
        this.startDate = stringToDateTime(startDate);
        this.endDate = stringToDateTime(endDate);
    }

    public Event(boolean isDone, String description, String startDate, String endDate){
        super(isDone, description);
        this.startDate = stringToDateTime(startDate);
        this.endDate = stringToDateTime(endDate);
    }

    private LocalDateTime stringToDateTime(String strDate) {
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

