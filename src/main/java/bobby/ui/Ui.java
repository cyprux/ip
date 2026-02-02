package bobby.ui;
import java.util.Scanner;

import bobby.task.Task;
import bobby.task.TaskList;

public class Ui {
    private static final String LINE = "____________________________________________________________";
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____   ___  ____  ____  __   __\n"
                + "| __ ) / _ \\| __ )| __ ) \\ \\ / /\n"
                + "|  _ \\| | | |  _ \\|  _ \\  \\ V / \n"
                + "| |_) | |_| | |_) | |_) |  | |  \n"
                + "|____/ \\___/|____/|____/   |_|  \n";

        System.out.println("Hello from\n" + logo);
        System.out.println(LINE);
        System.out.println(" Hello! I'm Bobby");
        System.out.println(" What can I do for you?");
        System.out.println(LINE);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLoadingError() {
        showError("Could not load tasks from disk. Starting with an empty list.");
    }

    public void showError(String message) {
        System.out.println(" " + message);
    }

    public void showGoodbye() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    public void showTaskList(TaskList tasks) {
        System.out.println(tasks.formatForDisplay());
    }

    public void showTaskAdded(Task task, int newSize) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + newSize + " tasks in the list.");
    }

    public void showTaskDeleted(Task task, int newSize) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + newSize + " tasks in the list.");
    }

    public void showTaskMarked(Task task) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
    }
}
