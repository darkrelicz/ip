import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Dawn {
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
                    System.out.println("\tBye. Hope to see you again soon!");
                    exit = true;    
                    break; 

                case "list":
                    System.out.println("\tHere are the tasks in your list:");
                    for (int i = 0; i < db.size(); i++) {
                        String res = String.format("%d. %s", i+1, db.get(i).toString());
                        System.out.println("\t"+res);
                    }     
                    break;

                case "mark":  
                    if (parts.length < 2) {
                        System.out.println("\tusage: mark <task number>");
                        break;
                    }
                    int taskNum = Integer.parseInt(parts[1])-1;
                    db.get(taskNum).markDone();
                    System.out.println("\tNice! I've marked this task as done:");
                    System.out.println("\t"+db.get(taskNum).toString());
                    break;

                case "unmark":  
                    if (parts.length < 2) {
                        System.out.println("\tusage: unmark <task number>");
                        break;
                    }
                    int taskIndex = Integer.parseInt(parts[1])-1;
                    db.get(taskIndex).unmarkDone();
                    System.out.println("\tOK, I've marked this task as not done yet:");
                    System.out.println("\t"+db.get(taskIndex).toString());
                    break;

                case "todo":
                    if (body.length() == 0) {
                        System.out.println("\tusage: todo <task description>");
                        break;
                    }
                    Task todo = new Todo(body);
                    db.add(todo);
                    System.out.println(String.format("""
                        \tGot it. I've added this task:
                        \t%s
                        \tNow you have %d tasks in the list.""", 
                        todo.toString(), db.size()));
                    break;

                case "deadline":
                    String[] deadlineParts = body.split(" /by ");
                    if (deadlineParts.length < 2) {
                        System.out.println("\tusage: deadline <task> /by <deadline>");
                        break;
                    }
                    Task deadline = new Deadline(deadlineParts[0], deadlineParts[1]); 
                    db.add(deadline);
                    System.out.println(String.format("""
                        \tGot it. I've added this task:
                        \t%s
                        \tNow you have %d tasks in the list.""", 
                        deadline.toString(), db.size()));
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
                        System.out.println("\tusage: event <task> /from <start date> /to <end date>");
                        break;
                    }
                    String start = dates[0];
                    String end = dates[1];

                    Task event = new Event(eventDesc, start, end); 
                    db.add(event);
                    System.out.println(String.format("""
                        \tGot it. I've added this task:
                        \t%s
                        \tNow you have %d tasks in the list.""", 
                        event.toString(), db.size()));
                    break;
                    
                default:
                    System.out.println("\tUnknown command, try again.");
                    break;
            }
           
        }        

        s.close();
    }
}
