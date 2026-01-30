package dawn.commands;  //same package as the class being tested

import dawn.exceptions.DawnException;
import dawn.exceptions.InvalidUsageException;
import dawn.tasks.Deadline;
import dawn.tasks.Task;
import dawn.tasks.TaskList;
import dawn.ui.UserInterface;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class DeadlineCommandTest {
    private TaskList tasks;
    private UserInterface ui;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList(new ArrayList<Task>());
        ui = new UserInterface();
    }

    @Test
    public void execute_validDeadline_addsTaskSuccessfully() {
        String body = "submit report /by 10-10-2020 10:00";
        DeadlineCommand cmd = new DeadlineCommand(body);
        cmd.execute(tasks, ui);
        assertEquals(1, tasks.getAllTasks().size());
        assertTrue(tasks.getAllTasks().get(0) instanceof Deadline); 
    }

    @Test
    public void execute_missingDeadline_throwsInvalidUsageException() throws DawnException {
        String body = "submit report";
        DeadlineCommand cmd = new DeadlineCommand(body);
        assertThrows(InvalidUsageException.class, () -> cmd.execute(tasks, ui)); 
    }

    @Test
    public void execute_multipleDeadlines_addsAllTasks() throws DawnException {
        DeadlineCommand cmd1 = new DeadlineCommand("task 1 /by 10-10-2020 11:11");
        DeadlineCommand cmd2 = new DeadlineCommand("task 2 /by 20-10-2020 11:11");
        cmd1.execute(tasks, ui);
        cmd2.execute(tasks, ui);

        assertEquals(2, tasks.getAllTasks().size());
    }
}