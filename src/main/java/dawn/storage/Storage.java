package dawn.storage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import dawn.tasks.Deadline;
import dawn.tasks.Event;
import dawn.tasks.Task;
import dawn.tasks.Todo;




public class Storage {
    public static final String FILE_PATH = "data/data.csv";
    public static final String DELIMITER = ",";

    private static void createFileIfNotExist() throws IOException {
        File storage = new File(FILE_PATH);
        if (!storage.exists()) {
            storage.getParentFile().mkdirs();
            storage.createNewFile();
        }
    }

    private boolean validateTodo(String body) {
        return true;
    }

    public static ArrayList<Task> readTasks() throws IOException {
        createFileIfNotExist();
        ArrayList<Task> localDb = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
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

    public static void updateStorage(ArrayList<Task> localDb) throws IOException {
        createFileIfNotExist();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task t : localDb) {
                bw.write(t.toCsv() + "\n");
            }
            bw.close();
        }     
    }
}
