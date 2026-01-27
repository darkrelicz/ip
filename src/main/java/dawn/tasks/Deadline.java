package dawn.tasks;

public class Deadline extends Task {
    protected String deadline;

    public Deadline(String description, String deadline){
        super(description);
        this.deadline = deadline;
    }

    public Deadline(boolean isDone, String description, String deadline){
        super(isDone, description);
        this.deadline = deadline;
    }

    @Override
    public String toCsv() {
        return "D," + super.toCsv() + "," + this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.deadline);
    }
}

