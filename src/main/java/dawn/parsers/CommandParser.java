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
import dawn.commands.FindCommand;

import java.util.Arrays;
/**
 * Reads and performs the relevant actions depending on the input entered by the user
 */
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

    private Command parseFindCommand(String[] parts, String body) {
        if (parts.length < 2) {
            throw new InvalidUsageException("find [/type <task type>] <keywords>");
        }
        return new FindCommand(body);
    }

    /**
     * Return a Command object to be executed depending on the user input
     * @param input User input or instructions
     * @return Command to be executed
     * @throws DawnException If there are issues with the commands' usage
     */
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
        
        case "find":
            return parseFindCommand(parts, body);
            
        default:
            throw new InvalidCommandException("  Unknown command. Please try again!");
        }
    }
}