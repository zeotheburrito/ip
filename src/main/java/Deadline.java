public class Deadline extends Task {
    public Deadline(String desc) {
        super(desc);
        String[] descSplit = desc.split(" /by ");
        this.desc = descSplit[0] + " (by: " + descSplit[1] + ")";
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[D][X] " + this.desc;
        }
        return "[D][ ] " + this.desc;
    }
}
