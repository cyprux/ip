package bobby.task;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int index) {
        return tasks.remove(index);
    }

    public ArrayList<Task> asUnmodifiableList() {
        return new ArrayList<>(tasks);
    }

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
