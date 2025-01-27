public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void setStatus(Boolean isDone) {
        this.isDone = isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}