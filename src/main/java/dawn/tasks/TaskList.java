package dawn.tasks;

import java.util.ArrayList;

/**
 * Represents the list of current tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Create a new task manager with an empty list;
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a list of tasks for the current session
     */
    public TaskList(ArrayList<Task> loadedDb) {
        this.tasks = loadedDb;
    }

    /**
     * Returns the list of tasks for the current session
     * @return List of tasks in the current session
     */
    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    /**
     * Returns the task specified by the task index number
     * @param taskId The target task index number
     * @return Target task object
     */
    public Task getTask(int taskId) {
        assert taskId > -1 : "task index should be a valid integer";
        return this.tasks.get(taskId);
    }

    /**
     * Add new tasks to the list
     * @param newTask Task to be added
     */
    public void addTask(Task newTask) {
        assert newTask != null : "newTask should be populated";
        this.tasks.add(newTask);
    }

    /**
     * Returns the deleted task specified by the task index number
     * @param taskId The target task index number
     * @return Deleted task object
     */
    public Task removeTask(int taskId) {
        assert taskId > -1 : "task index should be a valid integer";
        return this.tasks.remove(taskId);
    }

    /**
     * Returns a list of tasks that contains the specified keyword
     * @param keyword The target keyword
     * @return TaskList of target tasks
     */
    public TaskList findTasks(String keyword) {
        assert !keyword.isEmpty() : "keyword should not be empty";
        TaskList foundTasks = new TaskList();
        for (Task t : this.tasks) {
            if (t.getDesc().contains(keyword)) {
                foundTasks.addTask(t);
            }
        }
        return foundTasks;
    }
}
