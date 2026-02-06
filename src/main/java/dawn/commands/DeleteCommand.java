package dawn.commands;

import java.io.IOException;

import dawn.exceptions.DawnException;
import dawn.exceptions.InvalidCommandException;
import dawn.tasks.Task;
import dawn.tasks.TaskList;
import dawn.ui.UserInterface;
import dawn.storage.Storage;

/**
 * Represents the action to delete a task
 */
public class DeleteCommand extends Command {
    private int taskId;

    /**
     * Creates a command to delete task from the current list of tasks
     * @param taskId The index of the target task
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String execute(TaskList tasks, UserInterface ui, Storage db) throws DawnException {
        String result = "";
        if (tasks.getAllTasks().isEmpty()) {
            throw new InvalidCommandException("  List is empty!");
        }

        if (this.taskId < 0 || this.taskId >= tasks.getAllTasks().size()) {
            throw new InvalidCommandException("  Invalid task number");
        }
        Task target = tasks.removeTask(taskId);

        try {
            this.saveToStorage(tasks, db);
            result = ui.formatTaskDeleted(tasks.getAllTasks(), target);
        } catch (IOException e) {
            result = "Task not removed from storage!";
        }

        return result;
    }
}
