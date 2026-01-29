package dawn.commands;

import dawn.exceptions.DawnException;
import dawn.exceptions.InvalidUsageException;
import dawn.tasks.Task;
import dawn.tasks.TaskList;
import dawn.tasks.Todo;
import dawn.ui.UserInterface;

public class TodoCommand extends Command {
    private String body;

    public TodoCommand(String body) {
        this.body = body;
    }

    @Override 
    public void execute(TaskList tasks, UserInterface ui) throws DawnException {
        if (this.body.length() == 0) {
            throw new InvalidUsageException("todo <task description>");
        }
        Task todo = new Todo(body);
        tasks.addTask(todo);
        ui.printTask(todo, tasks.getAllTasks());
    }
}
