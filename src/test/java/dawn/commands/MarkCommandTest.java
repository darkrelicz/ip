package dawn.commands;  //same package as the class being tested

import dawn.exceptions.DawnException;
import dawn.exceptions.InvalidCommandException;
import dawn.tasks.Deadline;
import dawn.tasks.Event;
import dawn.tasks.Task;
import dawn.tasks.TaskList;
import dawn.tasks.Todo;
import dawn.ui.UserInterface;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class MarkCommandTest {
    private TaskList tasks;
    private UserInterface ui;

    @BeforeEach
    public void setUp() {
        tasks = new TaskList(new ArrayList<Task>());
        ui = new UserInterface();
    }

    @Test
    public void execute_markAllTasks() {
        Task task1 = new Todo("read book");
        Task task2 = new Deadline("finish report", "10-10-2020 10:11");
        Task task3 = new Event("meeting", "01-03-2020 10:00", "01-03-2020 11:00");
        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);

        MarkCommand cmd1 = new MarkCommand(0);
        MarkCommand cmd2 = new MarkCommand(1);
        MarkCommand cmd3 = new MarkCommand(2);
        cmd1.execute(tasks, ui);
        cmd2.execute(tasks, ui);
        cmd3.execute(tasks, ui);
        assertTrue(tasks.getTask(0).getIsDone().equals("1")); 
        assertTrue(tasks.getTask(1).getIsDone().equals("1")); 
        assertTrue(tasks.getTask(2).getIsDone().equals("1")); 
    }

    @Test
    public void execute_missingTasks_throwsInvalidCommandException() throws DawnException {
        MarkCommand cmd = new MarkCommand(0);
        assertThrows(InvalidCommandException.class, () -> cmd.execute(tasks, ui)); 
    }

    @Test
    public void execute_outOfRangeTaskIndex_throwsInvalidCommandException() throws DawnException {
        Task task1 = new Todo("read book");
        Task task2 = new Deadline("finish report", "10-10-2020 10:11");
        Task task3 = new Event("meeting", "01-03-2020 10:00", "01-03-2020 11:00");
        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);

        MarkCommand cmd1 = new MarkCommand(0);
        MarkCommand cmd2 = new MarkCommand(1);
        MarkCommand cmd3 = new MarkCommand(2);
        MarkCommand cmd4 = new MarkCommand(3);
        cmd1.execute(tasks, ui);
        cmd2.execute(tasks, ui);
        cmd3.execute(tasks, ui);

        assertThrows(InvalidCommandException.class, () -> cmd4.execute(tasks, ui)); 
    }
}