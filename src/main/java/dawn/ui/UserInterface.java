package dawn.ui;

import dawn.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encompasses the commandline user interface
 */
public class UserInterface {
    /**
     * Prints welcome message to commandline terminal
     */
    public void printWelcome() {
        System.out.println("""
            Hello! I'm Dawn!
            What can I do for you?
            """);
    }

    /**
     * Returns a message indicating the new task added 
     * @param newTask The new task added, possible task types include Todo, Event, and Deadline
     * @param db The local database responsible for storing tasks for the current session
     * @return String message indicating the new task added
     */
    public String formatTask(Task newTask, ArrayList<Task> db) {
        String msg = "";
        msg += "  Got it. I've added this task:\n";
        msg += "  " + newTask.toString() + "\n";    
        msg += String.format("  Now you have %d tasks in the list.", db.size());
        return msg;
    }

    /**
     * Returns a message indicating all tasks in the current session
     * @param db The local database responsible for storing tasks for the current session
     * @return String message indicating all tasks in the current session
     */
    public String formatList(ArrayList<Task> db) {
        String msg = "";
        msg += "  Here are the tasks in your list:\n";
        for (int i = 0; i < db.size(); i++) {
            msg += String.format("  %d. %s\n", i+1, db.get(i).toString());
        }     
        return msg;
    }

    /**
     * Returns a message indicating the task marked as done
     * @param db The local database responsible for storing tasks for the current session
     * @param taskId The index of the task marked as done or completed
     * @return String message indicating the task marked as done
     */
    public String formatMarkDone(ArrayList<Task> db, int taskId) {
        String msg = "";
        msg += "  Nice! I've marked this task as done:\n";
        msg += "  " + db.get(taskId).toString();
        return msg;
    }

    /**
     * Returns a message indicating the task marked as undone
     * @param db The local database responsible for storing tasks for the current session
     * @param taskId The index of the task marked as undone or uncompleted
     * @return String message indicating the task marked as undone
     */
    public String formatMarkUndone(ArrayList<Task> db, int taskId) {
        String msg = "";
        msg += "  OK, I've marked this task as not done yet:\n";
        msg += "  " + db.get(taskId).toString();
        return msg;
    }

    /**
     * Returns a message indicating the task deleted
     * @param db The local database responsible for storing tasks for the current session
     * @param target The task to be deleted
     * @return String message indicating the task deleted
     */
    public String formatTaskDeleted(ArrayList<Task> db, Task target) {
        String msg = "";
        msg += "  Noted. I've deleted the following item:\n";
        msg += "  " + target.toString() + "\n";
        msg += String.format("  Now you have %d tasks in the list.", db.size());
        return msg;
    }

    /**
     * Returns a message indicating the tasks with matching keywords
     * @param foundTasks The list of tasks with matching keywords
     * @return String message indicating the tasks with matching keywords
     */
    public String formatFindTasks(ArrayList<Task> foundTasks) {
        String msg = "";
        msg += "  Here are the matching tasks in your list:\n";
        for (int i = 0; i < foundTasks.size(); i++) {
            msg += String.format("  %d. %s\n", i+1, foundTasks.get(i).toString());
        }    
        return msg;
    }

    /**
     * Returns a error message
     * @param msg The error message to be displayed on commandline terminal
     * @return String message of the error message
     */
    public String formatError(String msg) {
        return "  " + msg;
    }
}