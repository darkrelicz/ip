package dawn.tasks;

import dawn.helpers.DateTimeUtil;

import java.time.LocalDateTime;

/**
 * Represents a task with a due date
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;

    /**
     * Creates a Task object with the stated description and deadline
     * @param description The description of the task
     * @param deadline The due date of the task
     */
    public Deadline(String description, String deadline){
        super(description);
        this.deadline = DateTimeUtil.stringToDateTime(deadline);
    }

    /**
     * Creates a Task object with the stated description, deadline and completion status
     * @param isDone The completion status of the task
     * @param description The description of the task
     * @param deadline The due date of the task
     */
    public Deadline(boolean isDone, String description, String deadline){
        super(isDone, description);
        this.deadline = DateTimeUtil.stringToDateTime(deadline);
    }

    @Override
    public String getTaskType() {
        return "DEADLINE";
    }

    @Override
    public String toCsv() {
        return "D," + super.toCsv() + "," + this.deadline.format(DateTimeUtil.FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.deadline.format(DateTimeUtil.FORMATTER));
    }
}

