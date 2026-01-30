public class Todo extends Task {
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    public Todo(String description, boolean isDone) {
        super(description, TaskType.TODO, isDone);
    }
}