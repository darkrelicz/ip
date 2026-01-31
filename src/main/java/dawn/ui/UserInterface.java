package dawn.ui;

import dawn.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encompasses the commandline user interface
 */
public class UserInterface {
    private Scanner scanner;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads and return user input from the commandline terminal
     * 
     * @return Input from user
     */
    public String readCommand() {
        return this.scanner.nextLine().trim();
    }

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
     * Displays new tasks added 
     * @param newTask The new task added, possible task types include Todo, Event, and Deadline
     * @param db The local database responsible for storing tasks for the current session
     */
    public void printTask(Task newTask, ArrayList<Task> db) {
        System.out.println("  Got it. I've added this task:");
        System.out.println("  " + newTask.toString());    
        System.out.println(String.format("  Now you have %d tasks in the list.", db.size()));
    }

    /**
     * Display all tasks in the current session
     * @param db The local database responsible for storing tasks for the current session
     */
    public void printList(ArrayList<Task> db) {
        System.out.println("  Here are the tasks in your list:");
        for (int i = 0; i < db.size(); i++) {
            String res = String.format("  %d. %s", i+1, db.get(i).toString());
            System.out.println(res);
        }     
    }

    /**
     * Display the task marked as done
     * @param db The local database responsible for storing tasks for the current session
     * @param taskId The index of the task marked as done or completed
     */
    public void printMarkDone(ArrayList<Task> db, int taskId) {
        System.out.println("  Nice! I've marked this task as done:");
        System.out.println("  " + db.get(taskId).toString());
    }

    /**
     * Display the task marked as undone
     * @param db The local database responsible for storing tasks for the current session
     * @param taskId The index of the task marked as undone or uncompleted
     */
    public void printMarkUndone(ArrayList<Task> db, int taskId) {
        System.out.println("  OK, I've marked this task as not done yet:");
        System.out.println("  " + db.get(taskId).toString());
    }

    /**
     * Display the task deleted
     * @param db The local database responsible for storing tasks for the current session
     * @param target The task to be deleted
     */
    public void printTaskDeleted(ArrayList<Task> db, Task target) {
        System.out.println("  Noted. I've deleted the following item:");
        System.out.println("  " + target.toString());
        System.out.println(String.format("  Now you have %d tasks in the list.", db.size()));
    }

    /**
     * Display the exit message
     */
    public void printExit() {
        System.out.println("  Bye. Hope to see you again soon!"); 
    }

    /**
     * Display error messages
     * @param msg The error message to be displayed on commandline terminal
     */
    public void showError(String msg) {
        System.out.println("  " + msg);
    }
}