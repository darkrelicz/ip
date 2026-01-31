package dawn.commands;

import dawn.exceptions.DawnException;
import dawn.exceptions.ExitException;
import dawn.tasks.TaskList;
import dawn.ui.UserInterface;

/**
 * Represents the action to exit the program
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, UserInterface ui) throws DawnException {
        ui.printExit();
        throw new ExitException();
    }
}
