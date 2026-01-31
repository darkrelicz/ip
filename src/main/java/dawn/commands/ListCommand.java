package dawn.commands;

import dawn.tasks.TaskList;
import dawn.ui.UserInterface;

/**
 * Represents the action to list all tasks in the current session
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, UserInterface ui) {
        ui.printList(tasks.getAllTasks());
    }
}
