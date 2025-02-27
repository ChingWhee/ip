package task;

/**
 * A generic task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new task with a given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new task with a given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone Whether the task is completed or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a status icon for the task.
     *
     * @return "X" if the task is done, otherwise a space character.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isDone {@code true} if the task is completed, {@code false} otherwise.
     */
    public void setStatus(Boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a formatted string representation of the task.
     *
     * @return A string containing the task type, completion status, and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Returns the task data in a format suitable for file storage.
     *
     * @return A formatted string representing the task.
     */
    public abstract String toFileString();
}