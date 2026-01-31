package dawn.commands;

import dawn.exceptions.DawnException;
import dawn.exceptions.InvalidUsageException;
import dawn.tasks.Event;
import dawn.tasks.Task;
import dawn.tasks.TaskList;
import dawn.ui.UserInterface;

/**
 * Represents the addition of a Event task
 */
public class EventCommand extends Command {
    private String body;

    /**
     * Creates a new command to add a Event task into TaskList
     * @param body The required information for constituting a Event task
     */
    public EventCommand(String body) {
        this.body = body;
    }

    @Override 
    public void execute(TaskList tasks, UserInterface ui) throws DawnException {
        String[] eventParts = body.split(" /from ");
        if (eventParts.length < 2) {
            throw new InvalidUsageException("event <task> /from <start date> /to <end date>");
        }
        String eventDesc = eventParts[0];
        String[] dates = eventParts[1].split(" /to ");
        if (dates.length < 2) {
            throw new InvalidUsageException("event <task> /from <start date> /to <end date>");
        }
        String start = dates[0];
        String end = dates[1];
        Task event = new Event(eventDesc, start, end); 
        tasks.addTask(event);
        ui.printTask(event, tasks.getAllTasks());
    }
}