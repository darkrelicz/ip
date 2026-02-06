package dawn.commands;

import java.io.IOException;

import dawn.exceptions.DawnException;
import dawn.exceptions.InvalidUsageException;
import dawn.tasks.Task;
import dawn.tasks.TaskList;
import dawn.tasks.Todo;
import dawn.ui.UserInterface;
import dawn.storage.Storage;

/**
 * Represents the addition of a Todo task object
 */
public class TodoCommand extends Command {
    private String body;

    /**
     * Creates a new command to add a Todo task into TaskList
     * @param body The required information for constituting a Todo task
     */
    public TodoCommand(String body) {
        this.body = body;
    }

    @Override 
    public String execute(TaskList tasks, UserInterface ui, Storage db) throws DawnException {
        String result = "";
        if (this.body.length() == 0) {
            throw new InvalidUsageException("todo <task description>");
        }
        Task todo = new Todo(body);
        tasks.addTask(todo);

        try {
            this.saveToStorage(tasks, db);
            result = ui.formatTask(todo, tasks.getAllTasks());
        } catch (IOException e) {
            result = "Task not added to storage!";
        }

        return result;
    }
}
