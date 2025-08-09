package me.pooriya.plotfour.util;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.*;

public class OutputStreamUtilTest {

    @Test
    public void shouldPrintMessageToOutputStream() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String message = "Hello World";

        OutputStreamUtil.printMsgToOutput(outputStream, message);

        assertEquals(message, outputStream.toString(UTF_8.name()));
    }

    @Test
    public void shouldPrintEmptyString() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String message = "";

        OutputStreamUtil.printMsgToOutput(outputStream, message);

        assertEquals("", outputStream.toString(UTF_8.name()));
    }

    @Test
    public void shouldPrintUnicodeCharacters() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String message = "Hello 世界 🌍";

        OutputStreamUtil.printMsgToOutput(outputStream, message);

        assertEquals(message, outputStream.toString(UTF_8.name()));
    }

    @Test
    public void shouldPrintMessageWithNewline() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String message = "Hello World";

        OutputStreamUtil.printlnMsgToOutput(outputStream, message);

        assertEquals(message + "\n", outputStream.toString(UTF_8.name()));
    }

    @Test
    public void shouldPrintEmptyStringWithNewline() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String message = "";

        OutputStreamUtil.printlnMsgToOutput(outputStream, message);

        assertEquals("\n", outputStream.toString(UTF_8.name()));
    }

    @Test
    public void shouldPrintMultipleMessages() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
        OutputStreamUtil.printMsgToOutput(outputStream, "Hello");
        OutputStreamUtil.printMsgToOutput(outputStream, " ");
        OutputStreamUtil.printMsgToOutput(outputStream, "World");

        assertEquals("Hello World", outputStream.toString(UTF_8.name()));
    }

    @Test
    public void shouldPrintMultipleMessagesWithNewlines() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
        OutputStreamUtil.printlnMsgToOutput(outputStream, "Line 1");
        OutputStreamUtil.printlnMsgToOutput(outputStream, "Line 2");

        assertEquals("Line 1\nLine 2\n", outputStream.toString(UTF_8.name()));
    }

    @Test
    public void shouldHandleSpecialCharacters() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String message = "Tab:\t, Newline:\n, Carriage Return:\r";

        OutputStreamUtil.printMsgToOutput(outputStream, message);

        assertEquals(message, outputStream.toString(UTF_8.name()));
    }

    @Test
    public void shouldHandleLongStrings() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        StringBuilder longMessage = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            longMessage.append("A");
        }
        String message = longMessage.toString();

        OutputStreamUtil.printMsgToOutput(outputStream, message);

        assertEquals(message, outputStream.toString(UTF_8.name()));
        assertEquals(1000, outputStream.toString(UTF_8.name()).length());
    }

    @Test
    public void shouldBehaveLikePrintln() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String message = "Test message";

        // Using printlnMsgToOutput should be equivalent to printMsgToOutput + "\n"
        OutputStreamUtil.printlnMsgToOutput(outputStream, message);

        ByteArrayOutputStream expectedStream = new ByteArrayOutputStream();
        OutputStreamUtil.printMsgToOutput(expectedStream, message);
        OutputStreamUtil.printMsgToOutput(expectedStream, "\n");

        assertEquals(expectedStream.toString(UTF_8.name()), outputStream.toString(UTF_8.name()));
    }
}