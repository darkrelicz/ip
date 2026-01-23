import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Dawn {
    private static void printTask(Task newTask, ArrayList<Task> db) {
        System.out.println("  Got it. I've added this task:");
        System.out.println("  "+newTask.toString());    
        System.out.println(String.format("  Now you have %d tasks in the list.", db.size()));
    }

    private static void handleCommand(String input, ArrayList<Task> db) 
            throws InvalidUsageException, InvalidCommandException, ExitException {
        String[] parts = input.split(" ");
        String cmd = parts[0].toLowerCase();
        String body = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
        switch (cmd) {
            case "exit":
            case "bye":
                System.out.println("  Bye. Hope to see you again soon!"); 
                throw new ExitException();

            case "list":
                System.out.println("  Here are the tasks in your list:");
                for (int i = 0; i < db.size(); i++) {
                    String res = String.format("  %d. %s", i+1, db.get(i).toString());
                    System.out.println(res);
                }     
                break;

            case "mark":  
                if (db.isEmpty()) {
                    throw new InvalidCommandException("  List is empty!");
                }
                if (parts.length < 2) {
                    throw new InvalidUsageException("mark <task number>");
                }

                int taskNum = Integer.parseInt(parts[1])-1;
                if (taskNum < 0 || taskNum >= db.size()) {
                    throw new InvalidCommandException("  Invalid task number");
                }
                db.get(taskNum).markDone();
                System.out.println("  Nice! I've marked this task as done:");
                System.out.println("  "+db.get(taskNum).toString());
                break;

            case "unmark": 
                if (db.isEmpty()) {
                    throw new InvalidCommandException("  List is empty!");
                }
                if (parts.length < 2) {
                    throw new InvalidUsageException("unmark <task number>");
                }
                int taskIndex = Integer.parseInt(parts[1])-1;
                if (taskIndex < 0 || taskIndex >= db.size()) {
                    throw new InvalidCommandException("  Invalid task number");
                }
                db.get(taskIndex).unmarkDone();
                System.out.println("  OK, I've marked this task as not done yet:");
                System.out.println("  "+db.get(taskIndex).toString());
                break;

            case "todo":
                if (body.length() == 0) {
                    throw new InvalidUsageException("todo <task description>");
                }
                Task todo = new Todo(body);
                db.add(todo);
                printTask(todo, db);
                break;

            case "deadline":
                String[] deadlineParts = body.split(" /by ");
                if (deadlineParts.length < 2) {
                    throw new InvalidUsageException("deadline <task> /by <deadline>");
                }
                Task deadline = new Deadline(deadlineParts[0], deadlineParts[1]); 
                db.add(deadline);
                printTask(deadline, db); 
                break;

            case "event":
                String[] eventParts = body.split(" /from ");
                if (eventParts.length < 2) {
                    throw new InvalidUsageException("event <task> /from <start date> /to <end date>");
                }
                String eventDesc = eventParts[0];
                String[] dates = eventParts[1].split(" /to ");
                if (dates.length < 2) {
                    throw new InvalidUsageException("event <task> /from <start date> /to <end date>");
                }
                String start = dates[0];
                String end = dates[1];
                Task event = new Event(eventDesc, start, end); 
                db.add(event);
                printTask(event, db);
                break;

            case "delete":
                if (db.isEmpty()) {
                    throw new InvalidCommandException("  List is empty!");
                }
                if (parts.length < 2) {
                    throw new InvalidUsageException("delete <task number>");
                }
                int taskID = Integer.parseInt(parts[1])-1;
                if (taskID < 0 || taskID >= db.size()) {
                    throw new InvalidCommandException("  Invalid task number");
                }
                Task target = db.remove(taskID);
                System.out.println("  Noted. I've deleted the following item:");
                System.out.println("  "+target.toString());
                System.out.println(String.format("  Now you have %d tasks in the list.", db.size()));
                break;
                
            default:
                throw new InvalidCommandException("  Unknown command. Please try again!");
            }
    }

    public static void main(String[] args) {
        ArrayList<Task> db = new ArrayList<>();
        Scanner s = new Scanner(System.in);

        System.out.println("""
        Hello! I'm Dawn!
        What can I do for you?
        """);

        try {        
            while (true) {
                try {
                    String input = s.nextLine().trim();
                    handleCommand(input, db);
                } catch (ExitException e) {
                    break;
                } catch (RuntimeException e) {
                    System.out.println(e.toString());
                } catch (Exception e) {
                    System.out.println("  Something went wrong! Please try again.");
                }            
            }       
        } finally {
            s.close();
        }
    }
}
