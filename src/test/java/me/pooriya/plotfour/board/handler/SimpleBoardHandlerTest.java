package me.pooriya.plotfour.board.handler;

import me.pooriya.plotfour.BoardObjectMother;
import me.pooriya.plotfour.board.Board;
import me.pooriya.plotfour.board.BoardSpecification;
import me.pooriya.plotfour.board.handler.creator.BoardCreator;
import me.pooriya.plotfour.board.handler.parser.BoardSpecificationParser;
import me.pooriya.plotfour.board.handler.reader.BoardReader;
import me.pooriya.plotfour.board.handler.writer.BoardWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SimpleBoardHandlerTest {

	@Mock BoardReader reader;

	@Mock BoardWriter writer;

	@Mock BoardCreator creator;

	@Mock BoardSpecificationParser parser;

	@InjectMocks SimpleBoardHandler handler;

	@Test
	public void handle_shouldReturnBoardSpec() {
		BoardSpecification spec = BoardSpecification.of(5, 5);
		Board board = BoardObjectMother.customBoard(spec);

		when(reader.readNextSpecification()).thenReturn("5x5");
		when(parser.parse("5x5")).thenReturn(spec);
		when(creator.createBoard(spec)).thenReturn(board);
		Board result = handler.handle();
		assertSame(result, board);
		verify(writer).printInputDimensions();
	}

	@Test
	public void handle_shouldReadUntilInputIsValidInputIsNotInFormat() {
		BoardSpecification spec = BoardSpecification.of(5, 5);
		Board board = BoardObjectMother.customBoard(spec);

		when(reader.readNextSpecification()).thenReturn("123456").thenReturn("5x5");
		when(parser.parse(anyString())).thenThrow(IllegalArgumentException.class).thenReturn(spec);
		when(creator.createBoard(spec)).thenReturn(board);
		Board result = handler.handle();
		assertSame(result, board);
		verify(writer, times(2)).printInputDimensions();
		verify(writer).printInvalidInput();
	}

	@Test
	public void handle_shouldReadUntilInputIsValid_whenInputIsNotInValidRange() {
		BoardSpecification spec = BoardSpecification.of(5, 5);
		Board board = BoardObjectMother.customBoard(spec);

		when(reader.readNextSpecification()).thenReturn("17x6").thenReturn("5x5");
		when(parser.parse(anyString())).thenReturn(BoardSpecification.of(17, 6)).thenReturn(spec);
		when(creator.createBoard(spec)).thenReturn(board);
		Board result = handler.handle();
		assertSame(result, board);
		verify(writer, times(2)).printInputDimensions();
		verify(writer).printInvalidRow();
	}

	@Test
	public void extractBoardSpecification_itShouldReturnNullWhenParserThrowsException() {
		when(parser.parse("123456")).thenThrow(IllegalArgumentException.class);
		BoardSpecification result = handler.extractBoardSpecification("123456");
		verify(writer).printInvalidInput();
		assertNull(result);
	}

	@Test
	public void extractBoardSpecification_itShouldValidateRow() {
		when(parser.parse("17x6")).thenReturn(BoardSpecification.of(17, 6));
		BoardSpecification result = handler.extractBoardSpecification("17x6");
		verify(writer).printInvalidRow();
		assertNull(result);
	}

	@Test
	public void extractBoardSpecification_itShouldValidateColumn() {
		when(parser.parse("6x17")).thenReturn(BoardSpecification.of(6, 17));
		BoardSpecification result = handler.extractBoardSpecification("6x17");
		verify(writer).printInvalidColumn();
		assertNull(result);
	}

	@Test
	public void isInvalidRange_shouldCheckRange() {
		assertTrue(SimpleBoardHandler.isInvalidRange(-1));
		assertTrue(SimpleBoardHandler.isInvalidRange(0));
		assertTrue(SimpleBoardHandler.isInvalidRange(1));
		assertTrue(SimpleBoardHandler.isInvalidRange(2));
		assertTrue(SimpleBoardHandler.isInvalidRange(3));
		assertTrue(SimpleBoardHandler.isInvalidRange(4));
		assertFalse(SimpleBoardHandler.isInvalidRange(5));
		assertFalse(SimpleBoardHandler.isInvalidRange(6));
		assertFalse(SimpleBoardHandler.isInvalidRange(7));
		assertFalse(SimpleBoardHandler.isInvalidRange(8));
		assertFalse(SimpleBoardHandler.isInvalidRange(9));
		assertTrue(SimpleBoardHandler.isInvalidRange(10));
		assertTrue(SimpleBoardHandler.isInvalidRange(11));
		assertTrue(SimpleBoardHandler.isInvalidRange(12));
		assertTrue(SimpleBoardHandler.isInvalidRange(13));
	}

}