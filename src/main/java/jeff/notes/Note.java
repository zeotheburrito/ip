package jeff.notes;

/**
 * Notes that the user wants to record for future reference.
 */
public class Note {
    private final String note;

    public Note(String note) {
        this.note = note;
    }
    @Override
    public String toString() {
        return this.note;
    }
}
