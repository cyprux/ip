package bobby.ui;

import java.util.List;

import bobby.task.Task;
import bobby.task.TaskList;

/**
 * UI implementation that collects output into a String (for GUI use).
 */
public class GuiUi extends Ui {
    private final StringBuilder out = new StringBuilder();

    private void appendLine(String line) {
        out.append(line).append("\n");
    }

    public String getOutput() {
        return out.toString().trim();
    }

    @Override
    public void showLine() {
        appendLine("____________________________________________________________");
    }

    @Override
    public void showError(String message) {
        appendLine(" " + message);
    }

    @Override
    public void showGoodbye() {
        appendLine(" Bye. Hope to see you again soon!");
    }

    @Override
    public void showTaskList(TaskList tasks) {
        appendLine(tasks.formatForDisplay().trim());
    }

    @Override
    public void showTaskAdded(Task task, int newSize) {
        appendLine(" Got it. I've added this task:");
        appendLine("   " + task);
        appendLine(" Now you have " + newSize + " tasks in the list.");
    }

    @Override
    public void showTaskDeleted(Task task, int newSize) {
        appendLine(" Noted. I've removed this task:");
        appendLine("   " + task);
        appendLine(" Now you have " + newSize + " tasks in the list.");
    }

    @Override
    public void showTaskMarked(Task task) {
        appendLine(" Nice! I've marked this task as done:");
        appendLine("   " + task);
    }

    @Override
    public void showTaskUnmarked(Task task) {
        appendLine(" OK, I've marked this task as not done yet:");
        appendLine("   " + task);
    }

    @Override
    public void showFoundTasks(List<Task> matches) {
        showLine();

        if (matches.isEmpty()) {
            appendLine(" No matching tasks found.");
            showLine();
            return;
        }

        appendLine(" Here are the matching tasks in your list:");
        for (int i = 0; i < matches.size(); i++) {
            appendLine(" " + (i + 1) + "." + matches.get(i));
        }

        showLine();
    }
}
