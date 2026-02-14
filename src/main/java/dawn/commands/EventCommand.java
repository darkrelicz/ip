package dawn.commands;

import java.io.IOException;

import dawn.exceptions.DawnException;
import dawn.exceptions.InvalidUsageException;
import dawn.tasks.Event;
import dawn.tasks.Task;
import dawn.tasks.TaskList;
import dawn.ui.UserInterface;
import dawn.storage.Storage;

/**
 * Represents the addition of a Event task
 */
public class EventCommand extends Command {
    private static final int TASK_DESCRIPTION_INDEX = 0;
    private static final int TASK_BODY_INDEX = 1;
    private static final int TASK_START_DATE_INDEX = 0;
    private static final int TASK_END_DATE_INDEX = 1;


    private String body;

    /**
     * Creates a new command to add a Event task into TaskList
     * @param body The required information for constituting a Event task
     */
    public EventCommand(String body) {
        this.body = body;
    }

    @Override 
    public String execute(TaskList tasks, UserInterface ui, Storage db) throws DawnException {
        String result = "";
        String[] eventParts = body.split(" /from ");
        if (eventParts.length < 2) {
            throw new InvalidUsageException("event <task> /from <start date> /to <end date>");
        }
        String eventDesc = eventParts[TASK_DESCRIPTION_INDEX];
        String[] dates = eventParts[TASK_BODY_INDEX].split(" /to ");
        if (dates.length < 2) {
            throw new InvalidUsageException("event <task> /from <start date> /to <end date>");
        }
        String start = dates[TASK_START_DATE_INDEX];
        String end = dates[TASK_END_DATE_INDEX];
        Task event = new Event(eventDesc, start, end); 
        tasks.addTask(event);

        try {
            this.saveToStorage(tasks, db);
            result = ui.formatTask(event, tasks.getAllTasks());
        } catch (IOException e) {
            result = "Task not saved to storage!";
        }
    
        return result;
    }
}