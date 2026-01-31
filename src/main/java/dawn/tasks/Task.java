package dawn.tasks;

/**
 * Represents the task to be completed 
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task object with the stated description
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a Task object with the stated description and its completion status
     * @param isDone The completion status of the task
     * @param description The description of the task
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns an "X" symbol if the task is marked as completed
     * @return "X" if task is completed, empty otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X": " ");
    }

    /**
     * Returns a "1" symbol if the task is marked as completed
     * @return "1" if task is completed, "0" otherwise
     */
    public String getIsDone() {
        return (isDone ? "1" : "0");
    }

    /**
     * Returns the task description
     * @return Task description
     */
    public String getDesc() {
        return description;
    }

    /**
     * Marks the task as completed
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Return the task information in a Comma Separated format for storage
     * @return Task information in Comma Separated format
     */
    public String toCsv() {
        return String.format("%s,%s", getIsDone(), getDesc());
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}
