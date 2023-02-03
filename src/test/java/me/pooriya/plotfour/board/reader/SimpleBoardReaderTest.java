package me.pooriya.plotfour.board.reader;

import me.pooriya.plotfour.board.BoardSpecification;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static me.pooriya.plotfour.board.BoardSpecification.DEFAULT_SPECIFICATION;
import static org.junit.Assert.*;

public class SimpleBoardReaderTest {

	@Test
	public void readSpec_shouldReturnDefaultWhenInputIsEmpty() {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		InputStream input = IOUtils.toInputStream("\n", UTF_8);
		SimpleBoardReader reader = new SimpleBoardReader(input, output);
		BoardSpecification result = reader.readSpec();
		assertEquals("Set the board dimensions (Rows x Columns)\n" +
				"Press Enter for default (6 x 7)\n" +
				"> ", output.toString());
		assertSame(DEFAULT_SPECIFICATION, result);
	}

	@Test
	public void readSpec_shouldReturnBoardSpec() {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		InputStream input = IOUtils.toInputStream("5x5\n", UTF_8);
		SimpleBoardReader reader = new SimpleBoardReader(input, output);
		BoardSpecification result = reader.readSpec();
		assertEquals("Set the board dimensions (Rows x Columns)\n" +
				"Press Enter for default (6 x 7)\n" +
				"> ", output.toString());
		assertBoardSpecBySize(result, 5, 5);
	}

	@Test
	public void readSpec_shouldReadUntilInputIsValidInputIsNotInFormat() {
		InputStream input = IOUtils.toInputStream("123456\n12345*56\n7x 7", UTF_8);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		SimpleBoardReader reader = new SimpleBoardReader(input, output);
		BoardSpecification result = reader.readSpec();
		assertBoardSpecBySize(result, 7, 7);
		assertEquals("Set the board dimensions (Rows x Columns)\n" +
				"Press Enter for default (6 x 7)\n" +
				"> Invalid input\n" +
				"Set the board dimensions (Rows x Columns)\n" +
				"Press Enter for default (6 x 7)\n" +
				"> Invalid input\n" +
				"Set the board dimensions (Rows x Columns)\n" +
				"Press Enter for default (6 x 7)\n" +
				"> ", output.toString());
	}

	@Test
	public void readSpec_shouldReadUntilInputIsValid_whenInputIsNotInValidRange() {
		InputStream input = IOUtils.toInputStream("17X7\n7x17\n5X5", UTF_8);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		SimpleBoardReader reader = new SimpleBoardReader(input, output);
		BoardSpecification result = reader.readSpec();
		assertBoardSpecBySize(result, 5, 5);
		assertEquals("Set the board dimensions (Rows x Columns)\n" +
				"Press Enter for default (6 x 7)\n" +
				"> Board rows should be from 5 to 9\n" +
				"Set the board dimensions (Rows x Columns)\n" +
				"Press Enter for default (6 x 7)\n" +
				"> Board columns should be from 5 to 9\n" +
				"Set the board dimensions (Rows x Columns)\n" +
				"Press Enter for default (6 x 7)\n" +
				"> ", output.toString());
	}

	@Test
	public void parseInputLineShouldThrowIllegalArgumentException_whenInputIsInvalid() {
		try {
			SimpleBoardReader.parseInputLine("salam");
			fail("Test should not reach here");
		} catch (IllegalArgumentException ignored) {}

		try {
			SimpleBoardReader.parseInputLine("12345678");
			fail("Test should not reach here");
		} catch (IllegalArgumentException ignored) {}

		try {
			SimpleBoardReader.parseInputLine("14salam14");
			fail("Test should not reach here");
		} catch (IllegalArgumentException ignored) {}
	}

	@Test
	public void parseInputLineShouldReturnNormalSpecification() {
		assertBoardSpecBySize(SimpleBoardReader.parseInputLine("7x7"), 7, 7);
		assertBoardSpecBySize(SimpleBoardReader.parseInputLine("5x7"), 5, 7);
		assertBoardSpecBySize(SimpleBoardReader.parseInputLine("5x5"), 5, 5);
		assertBoardSpecBySize(SimpleBoardReader.parseInputLine("15x15"), 15, 15);
		assertBoardSpecBySize(SimpleBoardReader.parseInputLine("0x15"), 0, 15);


		assertBoardSpecBySize(SimpleBoardReader.parseInputLine("7X7"), 7, 7);
		assertBoardSpecBySize(SimpleBoardReader.parseInputLine("5X7"), 5, 7);
		assertBoardSpecBySize(SimpleBoardReader.parseInputLine("5X5"), 5, 5);
		assertBoardSpecBySize(SimpleBoardReader.parseInputLine("15X15"), 15, 15);
		assertBoardSpecBySize(SimpleBoardReader.parseInputLine("0X15"), 0, 15);


		assertBoardSpecBySize(SimpleBoardReader.parseInputLine("7 x 7"), 7, 7);
		assertBoardSpecBySize(SimpleBoardReader.parseInputLine("5 x7"), 5, 7);
		assertBoardSpecBySize(SimpleBoardReader.parseInputLine("5x 5"), 5, 5);
		assertBoardSpecBySize(SimpleBoardReader.parseInputLine("15  x15"), 15, 15);
		assertBoardSpecBySize(SimpleBoardReader.parseInputLine("0x  15"), 0, 15);
	}

	private void assertBoardSpecBySize(BoardSpecification spec, int rows, int columns) {
		assertNotNull(spec);
		assertEquals(rows, spec.getRows());
		assertEquals(columns, spec.getColumns());
	}


}