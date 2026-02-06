package dawn.commands;

import java.io.IOException;

import dawn.exceptions.DawnException;
import dawn.exceptions.InvalidCommandException;
import dawn.tasks.TaskList;
import dawn.ui.UserInterface;
import dawn.storage.Storage;

/**
 * Represents the action to mark the target task as completed
 */
public class MarkCommand extends Command {
    private int taskId;

    /**
     * Creates a command to mark the target task as completed
     * @param taskId The index of the target task
     */
    public MarkCommand(int taskId) {
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
        tasks.getTask(this.taskId).markDone();

        try {
            this.saveToStorage(tasks, db);
            result = ui.formatMarkDone(tasks.getAllTasks(), taskId);
        } catch (IOException e) {
            result = "Task not updated to storage!";
        }
    
        return result;
    }
}
