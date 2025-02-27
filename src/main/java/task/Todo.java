package task;

/**
 * A simple to-do task with a description.
 */
public class Todo extends Task {
    /**
     * Constructs a new to-do task.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a new to-do task with a completion status.
     *
     * @param description The description of the task.
     * @param isDone Whether the task is completed or not.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a formatted string representation of the To-Do task.
     *
     * @return A string showing task type and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the to-do task data in a format suitable for file storage.
     *
     * @return The to-do task in a formatted string representation.
     */
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
