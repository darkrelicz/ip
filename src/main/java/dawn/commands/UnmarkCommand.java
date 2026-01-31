package dawn.commands;

import dawn.exceptions.DawnException;
import dawn.exceptions.InvalidCommandException;
import dawn.tasks.TaskList;
import dawn.ui.UserInterface;

/**
 * Represents the action to mark the target task as not completed
 */
public class UnmarkCommand extends Command {
    private int taskId;

    /**
     * Creates an action to mark the target task as not completed
     * @param taskId The index of the target task
     */
    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tasks, UserInterface ui) throws DawnException {
        if (tasks.getAllTasks().isEmpty()) {
            throw new InvalidCommandException("  List is empty!");
        }

        if (this.taskId < 0 || this.taskId >= tasks.getAllTasks().size()) {
            throw new InvalidCommandException("  Invalid task number");
        }
        tasks.getTask(this.taskId).unmarkDone();
        ui.printMarkUndone(tasks.getAllTasks(), taskId);

    }
}