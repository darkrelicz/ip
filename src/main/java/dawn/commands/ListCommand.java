package dawn.commands;

import dawn.tasks.TaskList;
import dawn.ui.UserInterface;
import dawn.storage.Storage;

/**
 * Represents the action to list all tasks in the current session
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, UserInterface ui, Storage db) {
        return ui.formatList(tasks.getAllTasks());
    }
}
