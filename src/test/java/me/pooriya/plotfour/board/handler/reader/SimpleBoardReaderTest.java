package me.pooriya.plotfour.board.handler.reader;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.InputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertEquals;

public class SimpleBoardReaderTest {

	@Test
	public void itShouldReadNextLineAsInput() {
		InputStream input = IOUtils.toInputStream("\n", UTF_8);
		SimpleBoardReader reader = new SimpleBoardReader(input);
		String result = reader.readNextSpecification();
		assertEquals("", result);
	}

	@Test
	public void itShouldOnlyFirstLineAsInput() {
		InputStream input = IOUtils.toInputStream("1\n2", UTF_8);
		SimpleBoardReader reader = new SimpleBoardReader(input);
		String result = reader.readNextSpecification();
		assertEquals("1", result);
	}

	@Test
	public void itShouldIgnoreOtherWhitespacesAsDelimiter() {
		InputStream input = IOUtils.toInputStream("1 2 3 4\n", UTF_8);
		SimpleBoardReader reader = new SimpleBoardReader(input);
		String result = reader.readNextSpecification();
		assertEquals("1 2 3 4", result);
	}


}