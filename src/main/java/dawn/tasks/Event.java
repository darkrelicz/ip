package dawn.tasks;
public class Event extends Task {
    protected String startDate;
    protected String endDate;

    public Event(String description, String startDate, String endDate){
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(boolean isDone, String description, String startDate, String endDate){
        super(isDone, description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toCsv() {
        return "E," + super.toCsv() + String.format(",%s,%s", startDate, endDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format("(from: %s to: %s)", startDate, endDate);
    }
}

