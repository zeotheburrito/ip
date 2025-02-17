public class Todo extends Task{
    public Todo(String desc) {
        super(desc);
    }

    public static Task parseTask(String task) {
        Task newTask = new Todo(task.substring(4));
        if (task.contains("[X]")) {
            newTask.setDone();
        }
        return newTask;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[T][X] " + this.desc;
        }
        return "[T][ ] " + this.desc;
    }
}
