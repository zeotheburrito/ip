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

    @Override
    public String toString() {
        if (this.done) {
            return "[E][X] " + this.desc;
        }
        return "[E][ ] " + this.desc;
    }
}
