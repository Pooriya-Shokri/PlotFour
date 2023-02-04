package me.pooriya.plotfour.board.handler.creator;

import me.pooriya.plotfour.board.Board;
import me.pooriya.plotfour.board.BoardSpecification;
import me.pooriya.plotfour.board.handler.creator.SimpleBoardCreator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleBoardCreatorTest {

	@Test
	public void shouldReturnEmptyBoard_whenColOrRowAreZero() {
		SimpleBoardCreator creator = new SimpleBoardCreator();
		Board result1 = creator.createBoard(BoardSpecification.of(0, 0));
		assertEquals(0, result1.getState().length);

		Board result2 = creator.createBoard(BoardSpecification.of(10, 0));
		assertEquals(0, result2.getState().length);

		Board result3 = creator.createBoard(BoardSpecification.of(0, 10));
		assertEquals(0, result3.getState().length);
	}

	@Test
	public void shouldReturnNormalBoard() {
		SimpleBoardCreator creator = new SimpleBoardCreator();
		Board result = creator.createBoard(BoardSpecification.of(2, 3));
		assertEquals(2, result.getState().length);
		assertEquals(3, result.getState()[0].length);
		assertEquals(3, result.getState()[1].length);
	}

}