package jeff;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate byTime = null;
    private static final DateTimeFormatter DATE_FORMAT_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_FORMAT_OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Deadline(String desc) {
        super(desc);
        String[] descSplit = desc.split(" /by ");
        this.desc = descSplit[0];
        try {
            this.byTime = LocalDate.parse(descSplit[1], DATE_FORMAT_INPUT);
        } catch (DateTimeParseException e) {
            this.desc += " (by: " + descSplit[1] + ")";
        }
    }

    public static Task parseTask(String task) {
        String trimmed = task.substring(4);
        int byPos = trimmed.lastIndexOf("(by: ");
        String desc = trimmed.substring(0, byPos).trim();
        LocalDate time = LocalDate.parse(trimmed.substring(byPos + 5, trimmed.length() - 1).trim(), DATE_FORMAT_OUTPUT);

        Task newTask = new Deadline(desc + " /by " + time.format(DATE_FORMAT_INPUT));
        if (task.contains("[X]")) {
            newTask.setDone();
        }
        return newTask;
    }

    @Override
    public String toString() {
        return ((this.done) ? "[D][X] " : "[D][ ] ")
                + this.desc
                + ((this.byTime != null) ? " (by: " + this.byTime.format(DATE_FORMAT_OUTPUT) + ")" : "");
    }
}
