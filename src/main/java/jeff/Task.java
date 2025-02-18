package jeff;

abstract public class Task {
    protected String desc;
    protected boolean done;

    /**
     * Constructs a Task object and sets whether it is done to false.
     */
    public Task() {
        this.done = false;
    }

    /**
     * Sets whether this Task is done to true.
     */
    public void setDone() {
        this.done = true;
    }

    /**
     * Sets whether this Task is done to false.
     */
    public void setNotDone() {
        this.done = false;
    }

    public boolean descContains(String keyword) {
        return this.desc.contains(keyword);
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + this.desc;
        }
        return "[ ] " + this.desc;
    }
}