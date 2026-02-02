package bobby.ui;

import java.util.Scanner;

import bobby.task.Task;
import bobby.task.TaskList;

/**
 * Handles all user interface interactions for the Bobby application.
 * This class is responsible for displaying messages to the user and
 * reading user input from the console.
 */
public class Ui {
    private static final String LINE = "____________________________________________________________";
    private final Scanner scanner;

    /**
     * Constructs a Ui object and initializes the input scanner.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message and ASCII art logo.
     */
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

    /**
     * Displays a separator line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The input command string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays an error message related to loading tasks.
     */
    public void showLoadingError() {
        showError("Could not load tasks from disk. Starting with an empty list.");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(" " + message);
    }

    /**
     * Displays a goodbye message.
     */
    public void showGoodbye() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The TaskList to display.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println(tasks.formatForDisplay());
    }

    /**
     * Displays a message when a task is added.
     *
     * @param task The task that was added.
     * @param newSize The new number of tasks in the list.
     */
    public void showTaskAdded(Task task, int newSize) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + newSize + " tasks in the list.");
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param task The task that was removed.
     * @param newSize The new number of tasks in the list.
     */
    public void showTaskDeleted(Task task, int newSize) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + newSize + " tasks in the list.");
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that was marked.
     */
    public void showTaskMarked(Task task) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
    }

    /**
     * Displays a message when a task is marked as not done.
     *
     * @param task The task that was unmarked.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
    }
}
