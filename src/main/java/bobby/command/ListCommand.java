package bobby.command;

import bobby.storage.Storage;
import bobby.task.TaskList;
import bobby.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
