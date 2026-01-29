package dawn.commands;

import dawn.exceptions.DawnException;
import dawn.tasks.TaskList;
import dawn.ui.UserInterface;

public abstract class Command {
    public abstract void execute(TaskList tasks, UserInterface ui) throws DawnException;
}
