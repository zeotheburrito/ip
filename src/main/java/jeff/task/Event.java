package jeff.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event
 */
public class Event extends Task {
    private static final DateTimeFormatter DATE_FORMAT_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_FORMAT_OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    private LocalDate fromTime = null;
    private LocalDate toTime = null;
    /**
     * Constructs an Event object and assigns the description, the start time and the end time.
     *
     * @param desc String of task description.
     */
    public Event(String desc) {
        String[] firstSplit = desc.split(" /from ");
        this.desc = firstSplit[0];

        String[] secondSplit = firstSplit[1].split(" /to ");
        try {
            this.fromTime = LocalDate.parse(secondSplit[0], DATE_FORMAT_INPUT);
            this.toTime = LocalDate.parse(secondSplit[1], DATE_FORMAT_INPUT);
        } catch (DateTimeParseException e) {
            this.desc += " from: " + secondSplit[0] + " to: " + secondSplit[1];
        }
    }

    /**
     * Returns an Event object based on the string representation of a task.
     *
     * @param task String representation of a task.
     * @return EVent object based on String task.
     */
    public static Task parseTask(String task) {
        String trimmed = task.substring(4);
        int fromPos = trimmed.lastIndexOf("(from: ");
        int toPos = trimmed.lastIndexOf(" to: ");
        String desc = trimmed.substring(0, fromPos).trim();
        LocalDate startTime = LocalDate.parse(trimmed.substring(fromPos + 7, toPos).trim(), DATE_FORMAT_OUTPUT);
        LocalDate endTime = LocalDate.parse(trimmed.substring(toPos + 4, trimmed.length() - 1).trim(),
                DATE_FORMAT_OUTPUT);

        Task newTask = new Event(desc + " /from " + startTime.format(DATE_FORMAT_INPUT)
                + " /to " + endTime.format(DATE_FORMAT_INPUT));
        if (task.contains("[X]")) {
            newTask.setDone();
        }
        return newTask;
    }

    @Override
    public String toString() {
        return ((this.done) ? "[E][X] " : "[E][ ] ")
                + this.desc
                + ((this.fromTime != null) ? " (from: " + this.fromTime.format(DATE_FORMAT_OUTPUT) : "")
                + ((this.toTime != null) ? " to: " + this.toTime.format(DATE_FORMAT_OUTPUT) + ")" : "");
    }
}
