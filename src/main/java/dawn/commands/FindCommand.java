package dawn.commands;

import dawn.exceptions.DawnException;
import dawn.exceptions.InvalidCommandException;
import dawn.tasks.TaskList;
import dawn.ui.UserInterface;
import dawn.storage.Storage;

/**
 * Represents the action to retrieve a task based on given keywords
 */
public class FindCommand extends Command {
    private String keyword;
    public FindCommand(String body) {
        this.keyword = body;
    }   

    @Override
    public String execute(TaskList tasks, UserInterface ui, Storage db) throws DawnException {
        TaskList foundTasks = new TaskList();

        if (tasks.getAllTasks().isEmpty()) {
            throw new InvalidCommandException("  List is empty!");
        }

        foundTasks = tasks.findTasks(keyword);
        return ui.formatFindTasks(foundTasks.getAllTasks());
    }
}
