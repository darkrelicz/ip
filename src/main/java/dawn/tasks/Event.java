package dawn.tasks;

import dawn.helpers.DateTimeUtil;

import java.time.LocalDateTime;

/**
 * Represents a task with start and end dates
 */
public class Event extends Task {
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    /**
     * Creates a Task object with the stated description, start date, and end date
     * @param description The description of the task
     * @param startDate The start date of the task
     * @param endDate The end date of the task
     */
    public Event(String description, String startDate, String endDate){
        super(description);
        this.startDate = DateTimeUtil.stringToDateTime(startDate);
        this.endDate = DateTimeUtil.stringToDateTime(endDate);
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
        this.startDate = DateTimeUtil.stringToDateTime(startDate);
        this.endDate = DateTimeUtil.stringToDateTime(endDate);
    }

    @Override
    public String getTaskType() {
        return "EVENT";
    }

    @Override
    public String toCsv() {
        return "E," + super.toCsv() + String.format(",%s,%s", 
                startDate.format(DateTimeUtil.FORMATTER), 
                endDate.format(DateTimeUtil.FORMATTER));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", 
                startDate.format(DateTimeUtil.FORMATTER), 
                endDate.format(DateTimeUtil.FORMATTER));
    }
}

