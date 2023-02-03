package me.pooriya.plotfour.board.reader;

import me.pooriya.plotfour.board.BoardSpecification;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.InputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.*;

public class InputStreamBoardReaderTest {

	@Test
	public void readSpec_shouldReturnDefaultWhenInputIsEmpty() {
		InputStream input = IOUtils.toInputStream("\n", UTF_8);
		InputStreamBoardReader reader = new InputStreamBoardReader(input);
		BoardSpecification result = reader.readSpec();
		assertSame(BoardSpecification.DEFAULT_SPECIFICATION, result);
	}

	@Test
	public void readSpec_shouldReturnBoardSpec() {
		InputStream input = IOUtils.toInputStream("5x5\n", UTF_8);
		InputStreamBoardReader reader = new InputStreamBoardReader(input);
		BoardSpecification result = reader.readSpec();
		assertBoardSpecBySize(result, 5, 5);
	}

	@Test
	public void readSpec_shouldReadUntilInputIsValid() {
		assertBoardSpecReadByInputStream("123456\n12345*56\n7x 7", 7, 7);
		assertBoardSpecReadByInputStream("123456\n12345*56\n7x17\n5X5", 5, 5);
	}

	private void assertBoardSpecReadByInputStream(String inputLine, int rows, int columns) {
		InputStream input = IOUtils.toInputStream(inputLine, UTF_8);
		InputStreamBoardReader reader = new InputStreamBoardReader(input);
		BoardSpecification result = reader.readSpec();
		assertBoardSpecBySize(result, rows, columns);
	}

	@Test
	public void parseInputLineShouldReturnNull_whenInputStringIsEmpty() {
		assertNull(InputStreamBoardReader.parseInputLine(null));
		assertNull(InputStreamBoardReader.parseInputLine(""));
		assertNull(InputStreamBoardReader.parseInputLine("     "));
		assertNull(InputStreamBoardReader.parseInputLine("\n \n \n"));
	}

	@Test
	public void parseInputLineShouldThrowIllegalArgumentException_whenInputIsInvalid() {
		try {
			InputStreamBoardReader.parseInputLine("salam");
			fail("Test should not reach here");
		} catch (IllegalArgumentException ignored) {}

		try {
			InputStreamBoardReader.parseInputLine("12345678");
			fail("Test should not reach here");
		} catch (IllegalArgumentException ignored) {}

		try {
			InputStreamBoardReader.parseInputLine("14salam14");
			fail("Test should not reach here");
		} catch (IllegalArgumentException ignored) {}
	}

	@Test
	public void parseInputLineShouldReturnNormalSpecification() {
		assertBoardSpecBySize(InputStreamBoardReader.parseInputLine("7x7"), 7, 7);
		assertBoardSpecBySize(InputStreamBoardReader.parseInputLine("5x7"), 5, 7);
		assertBoardSpecBySize(InputStreamBoardReader.parseInputLine("5x5"), 5, 5);
		assertBoardSpecBySize(InputStreamBoardReader.parseInputLine("15x15"), 15, 15);
		assertBoardSpecBySize(InputStreamBoardReader.parseInputLine("0x15"), 0, 15);


		assertBoardSpecBySize(InputStreamBoardReader.parseInputLine("7X7"), 7, 7);
		assertBoardSpecBySize(InputStreamBoardReader.parseInputLine("5X7"), 5, 7);
		assertBoardSpecBySize(InputStreamBoardReader.parseInputLine("5X5"), 5, 5);
		assertBoardSpecBySize(InputStreamBoardReader.parseInputLine("15X15"), 15, 15);
		assertBoardSpecBySize(InputStreamBoardReader.parseInputLine("0X15"), 0, 15);


		assertBoardSpecBySize(InputStreamBoardReader.parseInputLine("7 x 7"), 7, 7);
		assertBoardSpecBySize(InputStreamBoardReader.parseInputLine("5 x7"), 5, 7);
		assertBoardSpecBySize(InputStreamBoardReader.parseInputLine("5x 5"), 5, 5);
		assertBoardSpecBySize(InputStreamBoardReader.parseInputLine("15  x15"), 15, 15);
		assertBoardSpecBySize(InputStreamBoardReader.parseInputLine("0x  15"), 0, 15);
	}

	private void assertBoardSpecBySize(BoardSpecification spec, int rows, int columns) {
		assertNotNull(spec);
		assertEquals(rows, spec.getRows());
		assertEquals(columns, spec.getColumns());
	}


}