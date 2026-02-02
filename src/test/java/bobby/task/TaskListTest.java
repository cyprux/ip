package bobby.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class TaskListTest {

    @Test
    public void formatForDisplay_emptyList_returnsNoTasksMessage() {
        TaskList tasks = new TaskList();
        assertEquals("No tasks yet!", tasks.formatForDisplay());
    }

    @Test
    public void formatForDisplay_nonEmptyList_numbersAndFormatsTasks() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("read book"));
        tasks.add(new Deadline("return book", LocalDate.of(2026, 2, 10)));

        String out = tasks.formatForDisplay();

        assertTrue(out.startsWith("1."), "Expected numbering to start at 1, got: " + out);
        assertTrue(out.contains("1. [T][ ] read book"), "Missing or incorrect todo line: " + out);
        assertTrue(out.contains("2. [D][ ] return book (by: Feb 10 2026)"), "Missing or incorrect deadline line: " + out);
        assertEquals(2, out.split("\\R").length, "Expected 2 lines, got: " + out);
    }
}
