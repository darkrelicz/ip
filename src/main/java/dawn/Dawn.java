package dawn;

import dawn.exceptions.ExitException;
import dawn.exceptions.InvalidCommandException;
import dawn.exceptions.InvalidUsageException;
import dawn.storage.Storage;
import dawn.tasks.Task;
import dawn.tasks.Deadline;
import dawn.tasks.Event;
import dawn.tasks.Todo;
import dawn.ui.UserInterface;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Dawn {
    private static void handleCommand(String input, ArrayList<Task> db) 
            throws InvalidUsageException, InvalidCommandException, ExitException, IOException, DateTimeParseException {
        String[] parts = input.split(" ");
        String cmd = parts[0].toLowerCase();
        String body = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
        switch (cmd) {
            case "exit":
            case "bye":
                Storage.updateStorage(db);
                UserInterface.printExit();
                throw new ExitException();

            case "list":
                UserInterface.printList(db);
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
                UserInterface.printMarkDone(db, taskNum);
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
                UserInterface.printMarkUndone(db, taskIndex);
                break;

            case "todo":
                if (body.length() == 0) {
                    throw new InvalidUsageException("todo <task description>");
                }
                Task todo = new Todo(body);
                db.add(todo);
                UserInterface.printTask(todo, db);
                break;

            case "deadline":
                String[] deadlineParts = body.split(" /by ");
                if (deadlineParts.length < 2) {
                    throw new InvalidUsageException("deadline <task> /by <deadline>");
                }
                Task deadline = new Deadline(deadlineParts[0], deadlineParts[1]); 
                db.add(deadline);
                UserInterface.printTask(deadline, db); 
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
                UserInterface.printTask(event, db);
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
                UserInterface.printTaskDeleted(db, target);
                break;
                
            default:
                throw new InvalidCommandException("  Unknown command. Please try again!");
            }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        try { 
            ArrayList<Task> db = Storage.readTasks();
            UserInterface.printWelcome();

            while (true) {
                try {
                    String input = s.nextLine().trim();
                    handleCommand(input, db);
                } catch (ExitException e) {
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("  Please enter dates in this format: dd-MM-yyyy HH:mm");
                } catch (RuntimeException e) {
                    System.out.println(e.toString());
                } catch (IOException e) {
                    System.out.println("  IO issues");
                } catch (Exception e) {
                    System.out.println("  Something went wrong! Please try again.");
                }            
            }       
        } catch (IOException e) {
            System.out.println("  File not found!");
        } finally {
            s.close();
        }
    }
}
