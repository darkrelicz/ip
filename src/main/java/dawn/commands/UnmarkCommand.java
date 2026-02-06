package dawn.commands;

import java.io.IOException;

import dawn.exceptions.DawnException;
import dawn.exceptions.InvalidCommandException;
import dawn.tasks.TaskList;
import dawn.ui.UserInterface;
import dawn.storage.Storage;

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
    public String execute(TaskList tasks, UserInterface ui, Storage db) throws DawnException {
        String result = "";
        if (tasks.getAllTasks().isEmpty()) {
            throw new InvalidCommandException("  List is empty!");
        }

        if (this.taskId < 0 || this.taskId >= tasks.getAllTasks().size()) {
            throw new InvalidCommandException("  Invalid task number");
        }
        tasks.getTask(this.taskId).unmarkDone();

        try {
            this.saveToStorage(tasks, db);
            result = ui.formatMarkUndone(tasks.getAllTasks(), taskId);
        } catch (IOException e) {
            result = "Task not updated to storage!";
        }
    
        return result;
    }
}