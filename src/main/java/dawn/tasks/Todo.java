package dawn.tasks;

/**
 * Represents a task with description
 */
public class Todo extends Task {
    /**
     * Creates a Task object with the stated description
     * @param description The description of the task
     */
    public Todo(String description){
        super(description);
    }

    /**
     * Creates a Task object with the stated description and its completion status
     * @param isDone The completion status of the task
     * @param description The description of the task
     */
    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    @Override 
    public String toCsv() {
        return "T," + super.toCsv();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
