public class Deadline extends Task {
    public Deadline(String desc) {
        super(desc);
        String[] descSplit = desc.split(" /by ");
        this.desc = descSplit[0] + " (by: " + descSplit[1] + ")";
    }

    public static Task parseTask(String task) {
        String trimmed = task.substring(4);
        int byPos = trimmed.lastIndexOf("(by: ");
        String desc = trimmed.substring(0, byPos).trim();
        String time = trimmed.substring(byPos + 5, trimmed.length() - 1).trim();

        Task newTask = new Deadline(desc + " /by " + time);
        if (task.contains("[X]")) {
            newTask.setDone();
        }
        return newTask;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[D][X] " + this.desc;
        }
        return "[D][ ] " + this.desc;
    }
}
