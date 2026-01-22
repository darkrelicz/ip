import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Dawn {
    private static void printTask(Task newTask, ArrayList<Task> db) {
        System.out.println("  Got it. I've added this task:");
        System.out.println("  "+newTask.toString());    
        System.out.println(String.format("  Now you have %d tasks in the list.", db.size()));
    }

    public static void main(String[] args) {
        ArrayList<Task> db = new ArrayList<>();
        Scanner s = new Scanner(System.in);

        System.out.println("""
        Hello! I'm Dawn!
        What can I do for you?
        """);

        boolean exit = false;
        while (!exit) {
            String input = s.nextLine().trim();
            String[] parts = input.split(" ");
            String cmd = parts[0];
            String body = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
            switch (cmd.toLowerCase()) {
                case "bye":
                    System.out.println("  Bye. Hope to see you again soon!");
                    exit = true;    
                    break; 

                case "list":
                    System.out.println("  Here are the tasks in your list:");
                    for (int i = 0; i < db.size(); i++) {
                        String res = String.format("%d. %s", i+1, db.get(i).toString());
                        System.out.println("  "+res);
                    }     
                    break;

                case "mark":  
                    if (parts.length < 2) {
                        System.out.println("  usage: mark <task number>");
                        break;
                    }
                    int taskNum = Integer.parseInt(parts[1])-1;
                    db.get(taskNum).markDone();
                    System.out.println("  Nice! I've marked this task as done:");
                    System.out.println("  "+db.get(taskNum).toString());
                    break;

                case "unmark":  
                    if (parts.length < 2) {
                        System.out.println("  usage: unmark <task number>");
                        break;
                    }
                    int taskIndex = Integer.parseInt(parts[1])-1;
                    db.get(taskIndex).unmarkDone();
                    System.out.println("  OK, I've marked this task as not done yet:");
                    System.out.println("  "+db.get(taskIndex).toString());
                    break;

                case "todo":
                    if (body.length() == 0) {
                        System.out.println("  usage: todo <task description>");
                        break;
                    }
                    Task todo = new Todo(body);
                    db.add(todo);
                    printTask(todo, db);
                    break;

                case "deadline":
                    String[] deadlineParts = body.split(" /by ");
                    if (deadlineParts.length < 2) {
                        System.out.println("  usage: deadline <task> /by <deadline>");
                        break;
                    }
                    Task deadline = new Deadline(deadlineParts[0], deadlineParts[1]); 
                    db.add(deadline);
                    printTask(deadline, db); 
                    break;

                case "event":
                    String[] eventParts = body.split(" /from ");
                    if (eventParts.length < 2) {
                        System.out.println("\tusage: event <task> /from <start date> /to <end date>");
                        break;
                    }
                    String eventDesc = eventParts[0];
                    String[] dates = eventParts[1].split(" /to ");
                    if (dates.length < 2) {
                        System.out.println("  usage: event <task> /from <start date> /to <end date>");
                        break;
                    }
                    String start = dates[0];
                    String end = dates[1];
                    Task event = new Event(eventDesc, start, end); 
                    db.add(event);
                    printTask(event, db);
                    break;
                    
                default:
                    System.out.println("  Unknown command, try again.");
                    break;
            }
           
        }        

        s.close();
    }
}
