package dawn.commands;

import dawn.tasks.TaskList;
import dawn.ui.UserInterface;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, UserInterface ui) {
        ui.printList(tasks.getAllTasks());
    }
}
