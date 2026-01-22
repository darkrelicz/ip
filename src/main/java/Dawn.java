import java.util.ArrayList;
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
            switch (cmd.toLowerCase()) {
                case "bye":
                    System.out.println("\tBye. Hope to see you again soon!");
                    exit = true;    
                    break; 

                case "list":
                    System.out.println("\tHere are the tasks in your list:");
                    for (int i = 0; i < db.size(); i++) {
                        String res = String.format("%d.[%s] %s", i+1, db.get(i).getStatusIcon(), db.get(i).getDesc());
                        System.out.println("\t"+res);
                    }     
                    break;

                case "mark":  
                    if (parts.length < 2) {
                        System.out.println("usage: mark <task number>");
                        break;
                    }
                    int taskNum = Integer.parseInt(parts[1])-1;
                    db.get(taskNum).markDone();
                    System.out.println("\tNice! I've marked this task as done:");
                    String res = String.format("\t[%s] %s", db.get(taskNum).getStatusIcon(), db.get(taskNum).getDesc());
                    System.out.println(res);
                    break;

                case "unmark":  
                    if (parts.length < 2) {
                        System.out.println("usage: unmark <task number>");
                        break;
                    }
                    int taskIndex = Integer.parseInt(parts[1])-1;
                    db.get(taskIndex).unmarkDone();
                    System.out.println("\tOK, I've marked this task as not done yet:");
                    String result = String.format("\t[%s] %s", db.get(taskIndex).getStatusIcon(), db.get(taskIndex).getDesc());
                    System.out.println(result);
                    break;
                    
                default:
                    Task t = new Task(input);
                    db.add(t);
                    System.out.println("\tAdded: " + input);
                    break;
            }
           
        }        

        s.close();
    }
}
