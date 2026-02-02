import java.time.LocalDate;

public class AddEventCommand extends Command {
    private final String description;
    private final LocalDate from;
    private final LocalDate to;

    public AddEventCommand(String description, LocalDate from, LocalDate to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        Task t = new Event(description, from, to);
        tasks.add(t);
        storage.save(tasks);
        ui.showTaskAdded(t, tasks.size());
    }
}
