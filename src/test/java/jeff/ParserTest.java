package jeff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import jeff.chatbot.Parser;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void getIndex_stringWithIntegers_success() throws NumberFormatException {
        Parser testParser = new Parser();
        for (int i = 0; i < 10; i++) {
            assertEquals(i - 1, testParser.getIndex(Integer.toString(i)));
        }
    }

    @Test
    public void getIndex_stringWithoutIntegers_exceptionThrown() {
        Parser testParser = new Parser();
        try {
            assertEquals(0, testParser.getIndex("apple"));
            fail();
        } catch (NumberFormatException e) {
            assertEquals("For input string: \"apple\"", e.getMessage());
        }
    }
}
