package dawn.commands;

import java.io.IOException;

import dawn.exceptions.DawnException;
import dawn.exceptions.InvalidUsageException;
import dawn.tasks.Deadline;
import dawn.tasks.Task;
import dawn.tasks.TaskList;
import dawn.ui.UserInterface;
import dawn.storage.Storage;

/**
 * Represents the addition of a Deadline task
 */
public class DeadlineCommand extends Command {
    private static final int TASK_DESCRIPTION_INDEX = 0;
    private static final int TASK_END_DATE = 1;

    private String body;

    /**
     * Creates a new command to add a Deadline task into TaskList
     * @param body The required information for constituting a Deadline task
     */
    public DeadlineCommand(String body) {
        this.body = body;
    }

    @Override 
    public String execute(TaskList tasks, UserInterface ui, Storage db) throws DawnException {
        String result = "";
        String[] deadlineParts = body.split(" /by ");
        if (deadlineParts.length < 2) {
            throw new InvalidUsageException("deadline <task> /by <deadline>");
        }
        Task deadline = new Deadline(
                deadlineParts[TASK_DESCRIPTION_INDEX], 
                deadlineParts[TASK_END_DATE]); 
        tasks.addTask(deadline);
        
        try {
            this.saveToStorage(tasks, db);
            result = ui.formatTask(deadline, tasks.getAllTasks()); 
        } catch (IOException e) {
            result = "Task not saved to storage!";
        }

        return result;
    }
}
