package task;

/**
 * A task with a deadline.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a Deadline task with a description and a due date.
     *
     * @param description The description of the task.
     * @param by The deadline date and time.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline task with a description, completion status, and a due date.
     *
     * @param description The description of the task.
     * @param isDone Whether the task is completed or not.
     * @param by The deadline date and time.
     */
    public Deadline(String description, Boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns a formatted string representation of the Deadline task.
     *
     * @return A string showing task type, description, and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Converts the Deadline task into a string suitable for file storage.
     *
     * @return The Deadline task in a formatted string representation.
     */
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
