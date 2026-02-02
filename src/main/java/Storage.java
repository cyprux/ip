import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final Path DATA_DIR = Paths.get("data");
    private static final Path FILE_PATH = DATA_DIR.resolve("bobby.txt");

    public static ArrayList<Task> loadTasks() throws BobbyException {
        ArrayList<Task> tasks = new ArrayList<>();

        // First run: no folder/file yet -> return empty list
        if (!Files.exists(FILE_PATH)) {
            return tasks;
        }

        try {
            List<String> lines = Files.readAllLines(FILE_PATH);
            for (String rawLine : lines) {
                String line = rawLine.trim();
                if (line.isEmpty()) {
                    continue;
                }
                tasks.add(parseLine(line));
            }
            return tasks;
        } catch (IOException e) {
            throw new BobbyException("Could not read saved tasks from disk.");
        }
    }

    public static void saveTasks(ArrayList<Task> tasks) throws BobbyException {
        try {
            // Ensure ./data exists
            Files.createDirectories(DATA_DIR);

            ArrayList<String> out = new ArrayList<>();
            for (Task t : tasks) {
                out.add(toStorageString(t));
            }

            Files.write(FILE_PATH, out);
        } catch (IOException e) {
            throw new BobbyException("Could not save tasks to disk.");
        }
    }

    private static Task parseLine(String line) throws BobbyException {
        // Expected formats:
        // T | 1 | read book
        // D | 0 | return book | June 6th
        // E | 0 | meeting | Aug 6th 2-4pm | Aug 6th 4-6pm
        String[] parts = line.split("\\s*\\|\\s*");
        if (parts.length < 3) {
            throw new BobbyException("Corrupted save file line: " + line);
        }

        String typeIcon = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String desc = parts[2].trim();

        if (typeIcon.equals("T")) {
            return new Todo(desc, isDone);
        }

        if (typeIcon.equals("D")) {
            if (parts.length < 4) {
                throw new BobbyException("Corrupted deadline line: " + line);
            }
            LocalDate by = DateTimeUtil.parseDate(parts[3].trim());
            return new Deadline(desc, by, isDone);
        }

        if (typeIcon.equals("E")) {
            if (parts.length < 5) {
                throw new BobbyException("Corrupted event line: " + line);
            }
            LocalDate from = DateTimeUtil.parseDate(parts[3].trim());
            LocalDate to = DateTimeUtil.parseDate(parts[4].trim());
            return new Event(desc, from, to, isDone);
        }

        throw new BobbyException("Unknown task type in save file: " + typeIcon);
    }

    private static String toStorageString(Task t) throws BobbyException {
        String done = t.isDone() ? "1" : "0";
        String type = t.getType().getIcon();
        String desc = t.getDescription();

        if (t instanceof Todo) {
            return type + " | " + done + " | " + desc;
        }

        if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            return type + " | " + done + " | " + desc + " | " + d.getBy();
        }

        if (t instanceof Event) {
            Event e = (Event) t;
            return type + " | " + done + " | " + desc + " | " + e.getFrom() + " | " + e.getTo();
        }

        throw new BobbyException("Cannot save unknown task type.");
    }
}
