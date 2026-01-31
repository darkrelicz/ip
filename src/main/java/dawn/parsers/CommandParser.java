package dawn.parsers;

import dawn.exceptions.DawnException;
import dawn.exceptions.InvalidCommandException;
import dawn.exceptions.InvalidUsageException;
import dawn.commands.Command;
import dawn.commands.ExitCommand;
import dawn.commands.ListCommand;
import dawn.commands.MarkCommand;
import dawn.commands.UnmarkCommand;
import dawn.commands.TodoCommand;
import dawn.commands.DeadlineCommand;
import dawn.commands.DeleteCommand;
import dawn.commands.EventCommand;

import java.util.Arrays;

public class CommandParser {
    private Command parseMarkCommand(String[] parts) {
        if (parts.length < 2) {
            throw new InvalidUsageException("mark <task number>");
        }
        int taskId = Integer.parseInt(parts[1]) - 1;
        return new MarkCommand(taskId);
    }

    private Command parseUnmarkCommand(String[] parts) {
        if (parts.length < 2) {
            throw new InvalidUsageException("unmark <task number>");
        }
        int taskId = Integer.parseInt(parts[1]) - 1;
        return new UnmarkCommand(taskId);
    }

    private Command parseDeleteCommand(String[] parts) {
        if (parts.length < 2) {
            throw new InvalidUsageException("delete <task number>");
        }
        int taskId = Integer.parseInt(parts[1]) - 1;
        return new DeleteCommand(taskId);
    }

    public Command parse(String input) throws DawnException {
        String[] parts = input.split(" ");
        String cmd = parts[0].toLowerCase();
        String body = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
        switch (cmd) {
        case "exit":
        case "bye":
            return new ExitCommand();
        
        case "list":
            return new ListCommand();

        case "mark":  
            return parseMarkCommand(parts);

        case "unmark": 
            return parseUnmarkCommand(parts);

        case "todo":
            return new TodoCommand(body);

        case "deadline":
            return new DeadlineCommand(body);

        case "event":
            return new EventCommand(body);

        case "delete":
            return parseDeleteCommand(parts);
            
        default:
            throw new InvalidCommandException("  Unknown command. Please try again!");
        }
    }
}