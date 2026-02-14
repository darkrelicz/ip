package dawn.storage;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Arrays;

import dawn.tasks.Deadline;
import dawn.tasks.Event;
import dawn.tasks.Task;
import dawn.tasks.Todo;

/** 
 * Represents the interface to store the current session memory onto a database
 */
public class Storage {
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

    /**
     * Creates a local copy of the database
     * @return A list of Task objects populated with information from the database
     * @throws IOException If there are issues accessing the database
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> localDb = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            String line;   
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                assert parts.length >= 2 : "there should be at least 2 elements in parts";
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                String body = String.join(",", Arrays.copyOfRange(parts, 2, parts.length));
                switch (taskType) {
                    case "T":
                        String[] todoParts = body.split(DELIMITER);
                        assert todoParts.length >= 1 : "there should be at least 1 element in todoParts";
                        Task newTodo = new Todo(isDone, todoParts[0]);
                        localDb.add(newTodo);
                        break;
                    
                    case "D":
                        String[] deadlineParts = body.split(DELIMITER);
                        assert deadlineParts.length >= 2 : "there should be at least 2 elements in deadlineParts";
                        Task newDeadline = new Deadline(isDone, deadlineParts[0], deadlineParts[1]);
                        localDb.add(newDeadline);
                        break;

                    case "E":
                        String[] eventParts = body.split(DELIMITER);
                        assert eventParts.length >= 3 : "there should be at least 3 elements in eventParts";
                        Task newEvent = new Event(isDone, eventParts[0], eventParts[1], eventParts[2]);
                        localDb.add(newEvent);
                        break;
                }
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
