package bobby;

import java.util.ArrayList;

import bobby.command.Command;
import bobby.exception.BobbyException;
import bobby.parser.Parser;
import bobby.storage.Storage;
import bobby.task.Task;
import bobby.task.TaskList;
import bobby.ui.Ui;

/**
 * The main application class for the Bobby task manager.
 * This class initializes the UI, storage, and task list, and
 * controls the main program loop.
 */
public class Bobby {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Bobby application instance with the given data file path.
     * Attempts to load tasks from storage; if loading fails, starts with an empty list.
     *
     * @param filePath The path to the data file used for storing tasks.
     */
    public Bobby(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        TaskList loadedTasks;
        try {
            ArrayList<Task> fromDisk = storage.load();
            loadedTasks = new TaskList(fromDisk);
        } catch (BobbyException e) {
            ui.showLoadingError();
            loadedTasks = new TaskList();
        }

        tasks = loadedTasks;
    }

    /**
     * Runs the main application loop. It repeatedly reads user input,
     * parses it into commands, executes the commands, and handles errors
     * until an exit command is received.
     */
    public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();

            isExit = processInput(fullCommand, ui);

            ui.showLine();
        }
    }

    private boolean processInput(String input, Ui ui) {
        assert ui != null : "UI should not be null";

        try {
            Command command = Parser.parse(input);
            command.execute(tasks, ui, storage);
            return command.isExit();
        } catch (BobbyException e) {
            ui.showError(e.getMessage());
            return false;
        }
    }

    public String getWelcomeMessage() {
        return " Hello! I'm Bobby\n What can I do for you?";
    }

    public String getResponse(String input) {
        bobby.ui.GuiUi guiUi = new bobby.ui.GuiUi();
        processInput(input, guiUi);
        return guiUi.getOutput();
    }

    /**
     * The entry point of the Bobby application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Bobby("data/bobby.txt").run();
    }
}
