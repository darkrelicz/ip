package dawn;

import dawn.commands.Command;
import dawn.parsers.CommandParser;
import dawn.exceptions.DawnException;
import dawn.exceptions.ExitException;
import dawn.storage.Storage;
import dawn.tasks.TaskList;
import dawn.ui.UserInterface;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Dawn {
    private CommandParser parser;
    private UserInterface ui;
    private Storage storage;
    private TaskList tasks;

    public Dawn(String filePath) {
        this.ui = new UserInterface();
        try {
            assert (filePath != null && !filePath.isEmpty()) : "filePath should contain a file path string";
            storage = new Storage(filePath);
            parser = new CommandParser();
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            this.ui.formatError("  IO issues, please try again!");
        }
    }

    /**
     * Process commands received via GUI and return the corresponding output
     * @param input The command received via GUI
     * @return String message of the corresponding output
     */
    public String processCommand(String input) throws ExitException {
        try {
            Command cmd = this.parser.parse(input);
            return cmd.execute(this.tasks, this.ui, this.storage);
        } catch (DateTimeParseException e) {
            return this.ui.formatError("  Please enter dates in this format: dd-MM-yyyy HH:mm");
        } catch (ExitException e) {
            throw e;
        } catch (DawnException e) {
            return this.ui.formatError(e.toString());
        }     
    }
}
