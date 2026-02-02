package bobby.command;

import bobby.ui.Ui;
import bobby.task.TaskList;
import bobby.storage.Storage;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
