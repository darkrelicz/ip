package dawn.commands;

import java.io.IOException;

import dawn.exceptions.DawnException;
import dawn.tasks.TaskList;
import dawn.ui.UserInterface;
import dawn.storage.Storage;

/**
 * Represents the action to be exeuted based on user input
 */
public abstract class Command {
    /**
     * Performs the action as indicated through the user input 
     * @param tasks The list of tasks for the current session
     * @param ui The user interface 
     * @throws DawnException If there are issues with the commands' usage
     * @return String message of the action completed
     */
    public abstract String execute(TaskList tasks, UserInterface ui, Storage db) throws DawnException;

    protected void saveToStorage(TaskList tasks, Storage db) throws IOException {
        db.save(tasks.getAllTasks());
    }
}
