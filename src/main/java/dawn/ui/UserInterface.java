package dawn.ui;

import dawn.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return this.scanner.nextLine().trim();
    }

    public void printWelcome() {
        System.out.println("""
            Hello! I'm Dawn!
            What can I do for you?
            """);
    }

    public void printTask(Task newTask, ArrayList<Task> db) {
        System.out.println("  Got it. I've added this task:");
        System.out.println("  " + newTask.toString());    
        System.out.println(String.format("  Now you have %d tasks in the list.", db.size()));
    }

    public void printList(ArrayList<Task> db) {
        System.out.println("  Here are the tasks in your list:");
        for (int i = 0; i < db.size(); i++) {
            String res = String.format("  %d. %s", i+1, db.get(i).toString());
            System.out.println(res);
        }     
    }

    public void printMarkDone(ArrayList<Task> db, int taskId) {
        System.out.println("  Nice! I've marked this task as done:");
        System.out.println("  " + db.get(taskId).toString());
    }

    public void printMarkUndone(ArrayList<Task> db, int taskId) {
        System.out.println("  OK, I've marked this task as not done yet:");
        System.out.println("  " + db.get(taskId).toString());
    }

    public void printTaskDeleted(ArrayList<Task> db, Task target) {
        System.out.println("  Noted. I've deleted the following item:");
        System.out.println("  " + target.toString());
        System.out.println(String.format("  Now you have %d tasks in the list.", db.size()));
    }

    public void printExit() {
        System.out.println("  Bye. Hope to see you again soon!"); 
    }

    public void showError(String msg) {
        System.out.println("  " + msg);
    }
}