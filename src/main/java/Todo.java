public class Todo extends Task{
    public Todo(String desc) {
        super(desc);
    }
    @Override
    public String toString() {
        if (this.done) {
            return "[T][X] " + this.desc;
        }
        return "[T][ ] " + this.desc;
    }
}
