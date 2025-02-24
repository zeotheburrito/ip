package jeff.chatbot;

import java.util.ArrayList;

import jeff.notes.Note;

/**
 * NoteList to track all notes.
 */
public class NoteList {
    private final ArrayList<Note> notes;

    /**
     * Constructs a NoteList object and adds Notes represented in String data
     * to ArrayList tasks.
     * @param data String representation of Notes.
     */
    public NoteList(String data) {
        this.notes = new ArrayList<>();
        String[] lines = data.split("\\r?\\n");
        for (String line : lines) {
            this.notes.add(new Note(line));
        }
    }
    public NoteList() {
        this.notes = new ArrayList<>();
    }

    /**
     * Returns Note after adding to ArrayList notes.
     * @param note String representation of a note.
     * @return Note represented by String note.
     */
    public Note addNote(String note) {
        Note newNote = new Note(note);
        notes.add(newNote);
        return newNote;
    }
    public Note get(int index) throws IndexOutOfBoundsException {
        return notes.get(index);
    }
    public int getLength() {
        return notes.size();
    }
    @Override
    public String toString() {
        StringBuilder items = new StringBuilder();
        for (int i = 0; i < notes.size() - 1; i++) {
            items.append(" ").append(i + 1).append(". ")
                    .append(notes.get(i)).append("\n");
        }
        items.append(" ").append(notes.size()).append(". ")
                .append(notes.get(notes.size() - 1));
        return items.toString();
    }
}
