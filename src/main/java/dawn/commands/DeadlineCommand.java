package dawn.commands;

import dawn.exceptions.DawnException;
import dawn.exceptions.InvalidUsageException;
import dawn.tasks.Deadline;
import dawn.tasks.Task;
import dawn.tasks.TaskList;
import dawn.ui.UserInterface;

public class DeadlineCommand extends Command {
    private String body;

    public DeadlineCommand(String body) {
        this.body = body;
    }

    @Override 
    public void execute(TaskList tasks, UserInterface ui) throws DawnException {
        String[] deadlineParts = body.split(" /by ");
        if (deadlineParts.length < 2) {
            throw new InvalidUsageException("deadline <task> /by <deadline>");
        }
        Task deadline = new Deadline(deadlineParts[0], deadlineParts[1]); 
        tasks.addTask(deadline);
        ui.printTask(deadline, tasks.getAllTasks()); 
    }
}
