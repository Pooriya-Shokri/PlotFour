package me.pooriya.plotfour.board.creator;

import me.pooriya.plotfour.board.BoardSpecification;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleBoardCreatorTest {

	@Test
	public void shouldReturnEmptyBoard_whenColOrRowAreZero() {
		SimpleBoardCreator creator = new SimpleBoardCreator();
		int[][] result1 = creator.createBoard(BoardSpecification.of(0, 0));
		assertEquals(0, result1.length);

		int[][] result2 = creator.createBoard(BoardSpecification.of(10, 0));
		assertEquals(0, result2.length);

		int[][] result3 = creator.createBoard(BoardSpecification.of(0, 10));
		assertEquals(0, result3.length);
	}

	@Test
	public void shouldReturnNormalBoard() {
		SimpleBoardCreator creator = new SimpleBoardCreator();
		int[][] result = creator.createBoard(BoardSpecification.of(2, 3));
		assertEquals(2, result.length);
		assertEquals(3, result[0].length);
		assertEquals(3, result[1].length);
	}

}