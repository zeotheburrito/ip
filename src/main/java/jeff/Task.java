package jeff;

abstract public class Task {
    protected String desc;
    protected boolean done;
    public Task(String desc) {
        this.desc = desc;
        this.done = false;
    }

    public void setDone() {
        this.done = true;
    }

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