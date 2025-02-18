package jeff;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Todo other = (Todo) obj;
        return Objects.equals(this.desc, other.desc);
    }
}
