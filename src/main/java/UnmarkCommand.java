public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        ensureIndexInRange(index, tasks.size());
        Task t = tasks.get(index);
        t.markAsNotDone();
        storage.save(tasks);
        ui.showTaskUnmarked(t);
    }

    private static void ensureIndexInRange(int index, int size) throws BobbyException {
        if (index < 0 || index >= size) {
            throw new BobbyException("That task number doesn't exist. Use 'list' to see the numbers.");
        }
    }
}
