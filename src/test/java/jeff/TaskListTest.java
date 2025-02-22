package jeff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import jeff.chatbot.TaskList;
import jeff.task.Todo;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void get_validIndex_success() throws IndexOutOfBoundsException {
        int len = 10;
        TaskList testTasks = new TaskList();

        for (int i = 0; i < len; i++) {
            testTasks.addTodo("todo " + i);
        }

        for (int i = 0; i < len; i++) {
            assertEquals(new Todo("todo " + i), testTasks.get(i));
        }
    }

    @Test
    public void get_invalidIndex_exceptionThrown() {
        int len = 10;
        TaskList testTasks = new TaskList();

        for (int i = 0; i < len; i++) {
            testTasks.addTodo("todo " + i);
        }

        try {
            assertEquals(new Todo("todo 10"), testTasks.get(10));
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 10 out of bounds for length 10", e.getMessage());
        }
    }

    @Test
    public void delete_validIndex_success() throws IndexOutOfBoundsException {
        int len = 10;
        TaskList testTasks = new TaskList();

        for (int i = 0; i < len; i++) {
            testTasks.addTodo("todo " + i);
        }

        for (int i = len - 1; i >= 0; i--) {
            assertEquals(new Todo("todo " + i), testTasks.delete(i));
        }

        assertEquals(0, testTasks.getLength());
    }

    @Test
    public void delete_invalidIndex_exceptionThrown() {
        int len = 10;
        TaskList testTasks = new TaskList();

        for (int i = 0; i < len; i++) {
            testTasks.addTodo("todo " + i);
        }

        try {
            assertEquals(new Todo("todo 10"), testTasks.delete(10));
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index 10 out of bounds for length 10", e.getMessage());
        }
    }
}
