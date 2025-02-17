import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate fromTime = null;
    private LocalDate toTime = null;
    private static final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Event(String desc) {
        super(desc);

        String[] firstSplit = desc.split(" /from ");
        this.desc = firstSplit[0];

        String[] secondSplit = firstSplit[1].split(" /to ");
        try {
            this.fromTime = LocalDate.parse(secondSplit[0], inputFormat);
            this.toTime = LocalDate.parse(secondSplit[1], inputFormat);
        } catch (DateTimeParseException e) {
            this.desc += " from: " + secondSplit[0] + " to: " + secondSplit[1];
        }
    }

    public static Task parseTask(String task) {
        String trimmed = task.substring(4);
        int fromPos = trimmed.lastIndexOf("(from: ");
        int toPos = trimmed.lastIndexOf(" to: ");
        String desc = trimmed.substring(0, fromPos).trim();
        LocalDate startTime = LocalDate.parse(trimmed.substring(fromPos + 7, toPos).trim(), outputFormat);
        LocalDate endTime = LocalDate.parse(trimmed.substring(toPos + 4, trimmed.length() -1).trim(), outputFormat);

        Task newTask = new Event(desc + " /from " + startTime.format(inputFormat) + " /to " + endTime.format(inputFormat));
        if (task.contains("[X]")) {
            newTask.setDone();
        }
        return newTask;
    }

    @Override
    public String toString() {
        return ((this.done) ? "[E][X] " : "[E][ ] ")
                + this.desc
                + ((this.fromTime != null) ? " (from: " + this.fromTime.format(outputFormat) : "")
                + ((this.toTime != null) ? " to: " + this.toTime.format(outputFormat) + ")" : "");
    }
}
