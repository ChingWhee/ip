package task;

/**
 *  An event task with a start and end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Creates an Event task with a specified start and end time.
     *
     * @param description The event description.
     * @param from The event start time.
     * @param to The event end time.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an Event task with a specified completion status, start time, and end time.
     *
     * @param description The event description.
     * @param isDone Whether the task is completed.
     * @param from The event start time.
     * @param to The event end time.
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a formatted string representation of the Event task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Converts the Event task into a string suitable for file storage.
     *
     * @return The Event task in a formatted string representation.
     */
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}

