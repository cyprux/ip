package bobby.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks managed by the Bobby application.
 * This class provides operations to add, remove, retrieve, and
 * display tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList using an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task.
     * @return The task at the given index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes and returns the task at the specified index.
     *
     * @param index The index of the task to remove.
     * @return The removed task.
     */
    public Task delete(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns a copy of the internal task list to prevent external modification.
     *
     * @return A new ArrayList containing all tasks.
     */
    public ArrayList<Task> asUnmodifiableList() {
        return new ArrayList<>(tasks);
    }

    /**
     * Formats the task list into a numbered string representation
     * for display to the user.
     *
     * @return A formatted string of all tasks, or a message if empty.
     */
    public String formatForDisplay() {
        if (tasks.isEmpty()) {
            return "No tasks yet!";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1)
              .append(". ")
              .append(tasks.get(i))
              .append("\n");
        }
        return sb.toString().trim();
    }
}
