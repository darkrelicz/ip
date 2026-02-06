package dawn.commands;

import dawn.exceptions.DawnException;
import dawn.exceptions.ExitException;
import dawn.tasks.TaskList;
import dawn.ui.UserInterface;
import dawn.storage.Storage;

/**
 * Represents the action to exit the program
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, UserInterface ui, Storage db) throws DawnException {
        throw new ExitException();
    }
}
