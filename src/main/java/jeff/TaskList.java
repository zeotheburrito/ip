package jeff;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(String data) {
        this.tasks = new ArrayList<>();
        String[] lines = data.split("\\r?\\n");

        for (String line : lines) {
            Task task = sortTask(line);
            if (task != null) {
                this.tasks.add(task);
            }
        }
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    private Task sortTask(String line) {
        if (line.startsWith("[T]")) {
            return Todo.parseTask(line.substring(3));
        } else if (line.startsWith("[D]")) {
            return Deadline.parseTask(line.substring(3));
        } else if (line.startsWith("[E]")) {
            return Event.parseTask(line.substring(3));
        }
        return null;
    }

    public Task mark(int index) {
        Task task = tasks.get(index);
        task.setDone();
        return task;
    }

    public Task unmark(int index) {
        Task task = tasks.get(index);
        task.setNotDone();
        return task;
    }

    public Task addTodo(String task) {
        Todo newTodo = new Todo(task);
        tasks.add(newTodo);
        return newTodo;
    }

    public Task addDeadline(String task) {
        Deadline newDeadline = new Deadline(task);
        tasks.add(newDeadline);
        return newDeadline;
    }

    public Task addEvent(String task) {
        Event newEvent = new Event(task);
        tasks.add(newEvent);
        return newEvent;
    }

    public Task delete(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index);
    }

    public int getLength() {
        return tasks.size();
    }

    public Task get(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    public TaskList findTasks(String keyword) {
        StringBuilder subtasks = new StringBuilder();

        for (Task task : tasks) {
            if (task.descContains(keyword)) {
                subtasks.append(task).append("\n");
            }
        }

        return new TaskList(subtasks.toString());
    }

    public boolean checkIsEmpty() {
        return getLength() == 0;
    }

    @Override
    public String toString() {
        StringBuilder items = new StringBuilder();
        for (int i = 0; i < tasks.size() - 1; i++) {
            items.append(" ").append(i + 1).append(". ")
                    .append(tasks.get(i)).append("\n");
        }
        items.append(" ").append(tasks.size()).append(". ")
                .append(tasks.get(tasks.size() - 1));
        return items.toString();
    }
}
