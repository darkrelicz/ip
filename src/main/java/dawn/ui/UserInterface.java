package dawn.ui;

import dawn.tasks.Task;

import java.util.ArrayList;

public class UserInterface {
    public static void printWelcome() {
        System.out.println("""
            Hello! I'm Dawn!
            What can I do for you?
            """);
    }

    public static void printTask(Task newTask, ArrayList<Task> db) {
        System.out.println("  Got it. I've added this task:");
        System.out.println("  "+newTask.toString());    
        System.out.println(String.format("  Now you have %d tasks in the list.", db.size()));
    }

    public static void printList(ArrayList<Task> db) {
        System.out.println("  Here are the tasks in your list:");
        for (int i = 0; i < db.size(); i++) {
            String res = String.format("  %d. %s", i+1, db.get(i).toString());
            System.out.println(res);
        }     
    }

    public static void printMarkDone(ArrayList<Task> db, int taskId) {
        System.out.println("  Nice! I've marked this task as done:");
        System.out.println("  " + db.get(taskId).toString());
    }

    public static void printMarkUndone(ArrayList<Task> db, int taskId) {
        System.out.println("  OK, I've marked this task as not done yet:");
        System.out.println("  " + db.get(taskId).toString());
    }

    public static void printTaskDeleted(ArrayList<Task> db, Task target) {
        System.out.println("  Noted. I've deleted the following item:");
        System.out.println("  " + target.toString());
        System.out.println(String.format("  Now you have %d tasks in the list.", db.size()));
    }

    public static void printExit() {
        System.out.println("  Bye. Hope to see you again soon!"); 
    }
}