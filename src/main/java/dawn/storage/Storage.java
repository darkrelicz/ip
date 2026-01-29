package dawn.storage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import dawn.tasks.Deadline;
import dawn.tasks.Event;
import dawn.tasks.Task;
import dawn.tasks.Todo;

public class Storage {
    public static final String DELIMITER = ",";

    private String filePath;

    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        createFileIfNotExist();
    }

    private void createFileIfNotExist() throws IOException {
        File storage = new File(this.filePath);
        if (!storage.exists()) {
            storage.getParentFile().mkdirs();
            storage.createNewFile();
        }
    }

    public ArrayList<Task> readTasks() throws IOException {
        ArrayList<Task> localDb = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            String line;   
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                String body = String.join(",", Arrays.copyOfRange(parts, 2, parts.length));
                switch (taskType) {
                    case "T":
                        String[] todoParts = body.split(DELIMITER);
                        Task newTodo = new Todo(isDone, todoParts[0]);
                        localDb.add(newTodo);
                        break;
                    
                    case "D":
                        String[] deadlineParts = body.split(DELIMITER);
                        Task newDeadline = new Deadline(isDone, deadlineParts[0], deadlineParts[1]);
                        localDb.add(newDeadline);
                        break;

                    case "E":
                        String[] eventParts = body.split(DELIMITER);
                        Task newEvent = new Event(isDone, eventParts[0], eventParts[1], eventParts[2]);
                        localDb.add(newEvent);
                        break;
                }
            }
        } 

        return localDb;
    }

    public void updateStorage(ArrayList<Task> localDb) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.filePath))) {
            for (Task t : localDb) {
                bw.write(t.toCsv() + "\n");
            }
            bw.close();
        }     
    }
}
