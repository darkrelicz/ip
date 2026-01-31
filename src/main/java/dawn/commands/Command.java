package dawn.commands;

import dawn.exceptions.DawnException;
import dawn.tasks.TaskList;
import dawn.ui.UserInterface;

/**
 * Represents the action to be exeuted based on user input
 */
public abstract class Command {
    /**
     * Performs the action as indicated through the user input
     * @param tasks The list of tasks for the current session
     * @param ui The user interface 
     * @throws DawnException If there are issues with the commands' usage
     */
    public abstract void execute(TaskList tasks, UserInterface ui) throws DawnException;
}
