package fetch.test.message;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fetch.message.Messages;

public class TestMessages {

    private Messages messages = new Messages("test_messages");

    @Test
    public void testSimpleMessage() {
        String value = messages.get("simple");
        assertEquals("message!", value);
    }

    @Test
    public void testParameterizedMessage() {
        String arg0 = "parameterized";
        String arg1 = "message!";
        String value = messages.get("parameterized", arg0, arg1);
        String expected = "This is a parameterized message!!";
        assertEquals(expected, value);
    }
}
