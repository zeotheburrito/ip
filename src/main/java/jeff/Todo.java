package jeff;

import java.util.Objects;

public class Todo extends Task {
    /**
     * Constructs a Todo object and assigns the description.
     *
     * @param desc String of task description.
     */
    public Todo(String desc) {
        this.desc = desc;
    }

    /**
     * Returns a Todo object based on the string representation of a task.
     *
     * @param task String representation of a task.
     * @return Todo object based on String task.
     */
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
