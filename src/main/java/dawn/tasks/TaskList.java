package dawn.tasks;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> loadedDb) {
        this.tasks = loadedDb;
    }

    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    public Task getTask(int taskId) {
        return this.tasks.get(taskId);
    }

    public void addTask(Task newTask) {
        this.tasks.add(newTask);
    }

    public Task removeTask(int taskId) {
        return this.tasks.remove(taskId);
    }
}
