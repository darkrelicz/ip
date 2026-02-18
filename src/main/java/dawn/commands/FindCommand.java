package dawn.commands;

import dawn.exceptions.DawnException;
import dawn.exceptions.InvalidCommandException;
import dawn.exceptions.InvalidUsageException;
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

    private String extractTaskType(String body) throws DawnException {
        if (body.isBlank()) {
            throw new InvalidUsageException("find [/type <task type>] <keywords>");
        }

        String result = body.trim().toUpperCase().split("\\s+")[0];

        if (!result.equals("TODO") &&
                !result.equals("EVENT") &&
                !result.equals("DEADLINE")) {
            throw new InvalidCommandException("Valid task types are todo, event and deadline");
        } 
        return result;
    }

    private String extractKeyword(String body, String taskType) {
        return body.trim().substring(taskType.length()).trim();
    }

    @Override
    public String execute(TaskList tasks, UserInterface ui, Storage db) throws DawnException {
        String taskType = "ALL";
        String searchKeyword = keyword;

        TaskList foundTasks = new TaskList();

        if (tasks.getAllTasks().isEmpty()) {
            throw new InvalidCommandException("  List is empty!");
        }

        if (keyword.contains("/type")) {
            String[] parts = keyword.split("/type", 2);
            if (parts.length < 2 || parts[1].isBlank()) {
                throw new InvalidUsageException("find [/type <task type>] <keywords>");
            }
            taskType = extractTaskType(parts[1]);
            searchKeyword = extractKeyword(parts[1], taskType);
            
        }

        foundTasks = tasks.findTasks(searchKeyword, taskType);
        return ui.formatFindTasks(foundTasks.getAllTasks());
    }
}
