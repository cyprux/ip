package bobby.task;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Returns tasks whose descriptions contain the given keyword (case-insensitive).
     *
     * @param keyword Keyword to search for.
     * @return List of matching tasks. Empty list if none match.
     */
    public List<Task> findByKeyword(String keyword) {
        assert keyword != null : "keyword should not be null";

        String needle = keyword.trim().toLowerCase();
        List<Task> matches = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String haystack = task.getDescription().toLowerCase();
            if (haystack.contains(needle)) {
                matches.add(task);
            }
        }

        return matches;
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
