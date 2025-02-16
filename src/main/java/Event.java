public class Event extends Task {
    public Event(String desc) {
        super(desc);
        this.desc = formatDesc(desc);
    }

    private String formatDesc(String desc) {
        String[] firstSplit = desc.split(" /from ");
        String task = firstSplit[0];
        String[] secondSplit = firstSplit[1].split(" /to ");
        String startTime = secondSplit[0];
        String endTime = secondSplit[1];

        return task + " (from: " + startTime + " to: " + endTime + ")";
    }

    public static Task parseTask(String task) {
        String trimmed = task.substring(4);
        int fromPos = trimmed.lastIndexOf("(from: ");
        int toPos = trimmed.lastIndexOf(" to: ");
        String desc = trimmed.substring(0, fromPos).trim();
        String startTime = trimmed.substring(fromPos + 7, toPos).trim();
        String endTime = trimmed.substring(toPos + 4, trimmed.length() -1).trim();

        Task newTask = new Event(desc + " /from " + startTime + " /to " + endTime);
        if (task.contains("[X]")) {
            newTask.setDone();
        }
        return newTask;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[E][X] " + this.desc;
        }
        return "[E][ ] " + this.desc;
    }
}
