package dawn.storage;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Arrays;

import dawn.exceptions.DawnException;
import dawn.exceptions.InvalidCommandException;
import dawn.tasks.Deadline;
import dawn.tasks.Event;
import dawn.tasks.Task;
import dawn.tasks.Todo;

/** 
 * Represents the interface to store the current session memory onto a database
 */
public class Storage {
    private static final int TASK_TYPE_INDEX = 0;
    private static final int IS_DONE_INDEX = 1;
    private static final int TASK_DESCRIPTION_INDEX = 0;
    private static final int TASK_START_DATE = 1;
    private static final int TASK_END_DATE = 2;
    private static final String TASK_TYPE_TODO = "T";
    private static final String TASK_TYPE_DEADLINE = "D";
    private static final String TASK_TYPE_EVENT = "E";

    public static final String DELIMITER = ",";

    private String filePath;

    /**
     * Creates a Storage object storing the file path to the database
     * @param filePath File path to the database
     * @throws IOException If there are issues accessing the database
     */
    public Storage(String filePath) throws IOException {
        assert !filePath.isEmpty() : "file path should not be empty";
        this.filePath = filePath;
        createFileIfNotExist();
    }

    /**
     * Creates the database if it does not yet exist
     * @throws IOException If there are issues with creating or accessing the database
     */
    private void createFileIfNotExist() throws IOException {
        File storage = new File(this.filePath);
        if (!storage.exists()) {
            storage.getParentFile().mkdirs();
            storage.createNewFile();
        }
    }

    private Task createTodo(boolean isDone, String body) {
        String[] todoParts = body.split(DELIMITER);
        Task newTodo = new Todo(isDone, todoParts[TASK_DESCRIPTION_INDEX]);
        return newTodo;
    }

    private Task createDeadline(boolean isDone, String body) {
        String[] deadlineParts = body.split(DELIMITER);
        Task newDeadline = new Deadline(
                isDone, 
                deadlineParts[TASK_DESCRIPTION_INDEX], 
                deadlineParts[TASK_START_DATE]);
        return newDeadline;
    }

    private Task createEvent(boolean isDone, String body) {
        String[] eventParts = body.split(DELIMITER);
        Task newEvent = new Event(
                isDone, 
                eventParts[TASK_DESCRIPTION_INDEX], 
                eventParts[TASK_START_DATE], 
                eventParts[TASK_END_DATE]);
        return newEvent;
    }

    private Task createTask(String taskType, boolean isDone, String body) throws DawnException {
        Task newTask;
        switch (taskType) {
        case TASK_TYPE_TODO:
            newTask = createTodo(isDone, body);
            break;
        
        case TASK_TYPE_DEADLINE:
            newTask = createDeadline(isDone, body);
            break;

        case TASK_TYPE_EVENT:
            newTask = createEvent(isDone, body);
            break;

        default:
            throw new InvalidCommandException("Unknown task type: " + taskType);
        }
        
        return newTask;
    }

    private String extractTaskType(String[] parts) {
        return parts[TASK_TYPE_INDEX];
    }

    private boolean extractDoneStatus(String[] parts) {
        return parts[IS_DONE_INDEX].equals("1");
    }

    private Task parseLine(String line) {
        String[] parts = line.split(DELIMITER);
        String taskType = extractTaskType(parts);
        boolean isDone = extractDoneStatus(parts);
        String body = String.join(",", Arrays.copyOfRange(parts, 2, parts.length));
        Task newTask = createTask(taskType, isDone, body);
        return newTask;
    }

    /**
     * Creates a local copy of the database
     * @return A list of Task objects populated with information from the database
     * @throws IOException If there are issues accessing the database
     */
    public ArrayList<Task> load() throws IOException, DawnException {
        ArrayList<Task> localDb = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            String line;   
            while ((line = br.readLine()) != null) {
                Task newTask = parseLine(line);
                localDb.add(newTask);
            }
        } 
        return localDb;
    }

    /**
     * Saves the list of tasks for the current session into the database
     * @param localDb List of tasks in the current session
     * @throws IOException If there are issues accessing the database
     */
    public void save(ArrayList<Task> localDb) throws IOException {
        assert localDb != null : "localDb should be populated";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.filePath))) {
            for (Task t : localDb) {
                bw.write(t.toCsv() + "\n");
            }
            bw.close();
        }     
    }
}
