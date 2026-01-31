package dawn.tasks;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Create a new task manager with an empty list;
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

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

    /**
     * Returns a list of tasks that contains the specified keyword
     * @param keyword The target keyword
     * @return TaskList of target tasks
     */
    public TaskList findTasks(String keyword) {
        TaskList foundTasks = new TaskList();
        for (Task t : this.tasks) {
            if (t.getDesc().contains(keyword)) {
                foundTasks.addTask(t);
            }
        }
        return foundTasks;
    }
}
