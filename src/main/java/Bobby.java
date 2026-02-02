import java.util.ArrayList;

public class Bobby {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

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

    public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();

                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (BobbyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Bobby("data/bobby.txt").run();
    }
}
