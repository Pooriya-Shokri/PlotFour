package me.pooriya.plotfour.board.handler.parser;

import me.pooriya.plotfour.board.BoardSpecification;
import org.junit.Before;
import org.junit.Test;

import static me.pooriya.plotfour.board.BoardSpecification.DEFAULT_SPECIFICATION;
import static org.junit.Assert.*;

public class SimpleBoardSpecificationParserTest {

	private SimpleBoardSpecificationParser parser;

	@Before
	public void setup() {
		parser = new SimpleBoardSpecificationParser();
	}

	@Test
	public void parse_itShouldReturnDefaultSpec() {
		assertSame(DEFAULT_SPECIFICATION, parser.parse(""));
		assertSame(DEFAULT_SPECIFICATION, parser.parse("   "));
		assertSame(DEFAULT_SPECIFICATION, parser.parse( "   \n"));
	}

	@Test
	public void parse_itShouldThrowIllegalArgumentException_whenInputIsInvalid() {
		try {
			parser.parse("salam");
			fail("Test should not reach here");
		} catch (IllegalArgumentException ignored) {}

		try {
			parser.parse("12345678");
			fail("Test should not reach here");
		} catch (IllegalArgumentException ignored) {}

		try {
			parser.parse("14salam14");
			fail("Test should not reach here");
		} catch (IllegalArgumentException ignored) {}
	}

	@Test
	public void parse_itShouldReturnNormalSpecification() {
		assertBoardSpecBySize(parser.parse("7x7"), 7, 7);
		assertBoardSpecBySize(parser.parse("5x7"), 5, 7);
		assertBoardSpecBySize(parser.parse("5x5"), 5, 5);
		assertBoardSpecBySize(parser.parse("15x15"), 15, 15);
		assertBoardSpecBySize(parser.parse("0x15"), 0, 15);


		assertBoardSpecBySize(parser.parse("7X7"), 7, 7);
		assertBoardSpecBySize(parser.parse("5X7"), 5, 7);
		assertBoardSpecBySize(parser.parse("5X5"), 5, 5);
		assertBoardSpecBySize(parser.parse("15X15"), 15, 15);
		assertBoardSpecBySize(parser.parse("0X15"), 0, 15);


		assertBoardSpecBySize(parser.parse("7 x 7"), 7, 7);
		assertBoardSpecBySize(parser.parse("5 x7"), 5, 7);
		assertBoardSpecBySize(parser.parse("5x 5"), 5, 5);
		assertBoardSpecBySize(parser.parse("15  x15"), 15, 15);
		assertBoardSpecBySize(parser.parse("0x  15"), 0, 15);
	}

	private void assertBoardSpecBySize(BoardSpecification spec, int rows, int columns) {
		assertNotNull(spec);
		assertEquals(rows, spec.getRows());
		assertEquals(columns, spec.getColumns());
	}

}