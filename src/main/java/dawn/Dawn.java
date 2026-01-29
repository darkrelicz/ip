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
            storage = new Storage(filePath);
            parser = new CommandParser();
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            this.ui.showError("  IO issues, please try again!");
        }
    }

    public void run() {
        this.ui.printWelcome();

        while (true) {
            try {
                String input = this.ui.readCommand();
                Command cmd = this.parser.parse(input);
                cmd.execute(this.tasks, this.ui);
            } catch (ExitException e) {
                saveAndExit();
                break;
            } catch (DateTimeParseException e) {
                this.ui.showError("  Please enter dates in this format: dd-MM-yyyy HH:mm");
            } catch (DawnException e) {
                this.ui.showError(e.toString());
            } catch (Exception e) {
                this.ui.showError("  Something went wrong! Please try again.");
            }            
        }       
        
    }

    private void saveAndExit() {
        try {
            this.storage.save(this.tasks.getAllTasks());
        } catch (IOException e) {
            this.ui.showError("Could not save tasks!");
        }
    }

    public static void main(String[] args) {
        new Dawn("data/data.csv").run();
    }
}
