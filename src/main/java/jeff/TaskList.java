package jeff;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object and adds Tasks represented in String data
     * to ArrayList<Task> tasks.
     *
     * @param data String representation of Tasks.
     */
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

    /**
     * Constructs a TaskList object with an empty ArrayList<Task> tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns a Task (one of Todo, Deadline and Event) based on task represented in String line.
     *
     * @param line String representation of a task.
     * @return Task represented by String line.
     */
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

    /**
     * Returns the Task marked as done after marking it.
     *
     * @param index Index of Task to be marked in TaskList.
     * @return Task marked as done.
     */
    public Task mark(int index) {
        Task task = tasks.get(index);
        task.setDone();
        return task;
    }

    /**
     * Returns the Task unmarked as done (i.e. marked as undone) after marking it.
     *
     * @param index Index of Task to be unmarked in TaskList.
     * @return Task unmarked as done (i.e. marked as undone).
     */
    public Task unmark(int index) {
        Task task = tasks.get(index);
        task.setNotDone();
        return task;
    }

    /**
     * Returns Todo after adding it to ArrayList<Task> tasks.
     *
     * @param task String representation of Todo to be added.
     * @return Todo added.
     */
    public Task addTodo(String task) {
        Todo newTodo = new Todo(task);
        tasks.add(newTodo);
        return newTodo;
    }

    /**
     * Returns Deadline after adding it to ArrayList<Task> tasks.
     *
     * @param task String representation of Deadline to be added.
     * @return Deadline added.
     */
    public Task addDeadline(String task) {
        Deadline newDeadline = new Deadline(task);
        tasks.add(newDeadline);
        return newDeadline;
    }

    /**
     * Returns Event after adding it to ArrayList<Task> tasks.
     *
     * @param task String representation of Event to be added.
     * @return Event added.
     */
    public Task addEvent(String task) {
        Event newEvent = new Event(task);
        tasks.add(newEvent);
        return newEvent;
    }

    /**
     * Returns Task from ArrayList<Task> tasks at specified index.
     *
     * @param index Index of Task in ArrayList<Tasks> tasks.
     * @return Task at specified index.
     * @throws IndexOutOfBoundsException If the index is not within the bounds of ArrayList<Task> tasks.
     */
    public Task get(int index) throws IndexOutOfBoundsException {
        return tasks.get(index);
    }

    /**
     * Returns Task deleted from ArrayList<Task> tasks at specified index.
     *
     * @param index Index of Task to be deleted.
     * @return Task deleted from ArrayList<Task> tasks.
     * @throws IndexOutOfBoundsException If the index is not within the bounds of ArrayList<Task> tasks.
     */
    public Task delete(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index);
    }

    /**
     * Returns the number of Tasks in ArrayList<Task> tasks.
     *
     * @return Number of Tasks in ArrayList<Task> tasks
     */
    public int getLength() {
        return tasks.size();
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
